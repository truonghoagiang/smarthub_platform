package com.smarthub.integration.tuya;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smarthub.integration.tuya.dto.TuyaToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.time.Duration;

@Component
public class TuyaAPIClient {

    private final WebClient.Builder webClientBuilder;
    private final ObjectMapper objectMapper;

    @Value("{tuya.api.base-url:https://openapi.tuyaus.com}")
    private String baseUrl;

    private static final String SIGN_METHOD = "HMAC-SHA256";

    private final Logger logger = LoggerFactory.getLogger(TuyaAPIClient.class);

    public TuyaAPIClient(WebClient.Builder webClientBuilder, ObjectMapper objectMapper) {
        this.webClientBuilder = webClientBuilder;
        this.objectMapper = objectMapper;
    }

    /**
     * Get access token using client credentials
     */
    public Mono<TuyaToken.tokenResponse> getAccessToken(String clientId, String clientSecret) {
        try {
            long timestamp = System.currentTimeMillis();
            String path = "/v1.0/token?grant_type=1";

            String sign = generateSignature(clientId, clientSecret, timestamp, "GET", path, null, null);

            WebClient webClient = webClientBuilder
                    .baseUrl(baseUrl)
                    .build();

            return webClient.get()
                    .uri(path)
                    .header("client-id", clientId)
                    .header("sign", sign)
                    .header("t", String.valueOf(timestamp))
                    .header("sign_method", SIGN_METHOD)
                    .retrieve()

                    //handle error before body extraction
                    .onStatus(
                            HttpStatusCode::is4xxClientError,
                            response ->{
                                logger.error("Tuya return error: {}", response.statusCode());
                                return Mono.error(new RuntimeException("Client error from Tuya API"));
                            }
                    )
                    .onStatus(
                            HttpStatusCode::is5xxServerError,
                            response -> {
                                logger.error("Tuya return server error: {}", response.statusCode());
                                return Mono.error(new RuntimeException("Tuya return server error"));
                            }
                    )
                    //convert Json to Object
                    .bodyToMono(TuyaToken.tokenResponse.class)
                    //retry
                    .retryWhen(
                            Retry.backoff(3, Duration.ofSeconds(2)) //retry 3 times, wait 2 seconds increase backoff
                                    .filter(err -> !(err instanceof WebClientResponseException)) //skip retry on bad result
                                    .doAfterRetry(retry ->
                                            logger.info("Retry attemp #{} due to {}", retry.totalRetries()+1, retry.failure().getMessage()
                                    ))
                    )
                    //timeout - auto fail if slow response
                    .timeout(Duration.ofSeconds(10))
                    //response
                    .doOnNext(response -> {
                        if(response.isSuccess())
                            logger.info("Successfull obtained Tuya access token");
                        else
                            logger.error("Tuya obtained Tuya access token failed");
                    })
                    .doOnError(error -> logger.error("Tuya obtained Tuya access token failed {}", error.getMessage()));
        }catch (Exception e){
            logger.error(Thread.currentThread().getStackTrace()[1].getMethodName(), "Error getting access token---{}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private String generateSignature(String clientId, String clientSecret, long timestamp, String method, String path,
                                     String body, String accessToken){
        try{
            StringBuilder stringToSign = new StringBuilder();
            stringToSign.append(clientId);

            if(accessToken != null && !accessToken.isEmpty()){
                stringToSign.append(accessToken);
            }
            stringToSign.append(timestamp);
            stringToSign.append(method);

            //calculate content hash if body exists
            String contentHash = "";
            if(body != null && !body.isEmpty()){
                MessageDigest md = MessageDigest.getInstance(SIGN_METHOD);
                byte[] hash = md.digest(body.getBytes(StandardCharsets.UTF_8));
                contentHash =byteToHex(hash);
            }

            //build final string
            String signString = String.format("%s%s%s%s%s",
                    clientId,
                    accessToken != null ? accessToken : "",
                    timestamp,
                    method,
                    contentHash
                    );

            // add path and query
            stringToSign.append("\n");
            stringToSign.append(contentHash);
            stringToSign.append("\n\n");
            stringToSign.append(path);

            //generate HMAC-SHA256
            Mac mac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKeySpec = new SecretKeySpec(clientSecret.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
            mac.init(secretKeySpec);
            byte[] signatureBytes = mac.doFinal(stringToSign.toString().getBytes(StandardCharsets.UTF_8));

            return byteToHex(signatureBytes).toUpperCase();
        }catch (Exception e){
            logger.error("Error generate Tuya Signature.---" + e.getLocalizedMessage());
            throw new RuntimeException("Failed to generate Tuya Signature.---" + e.getMessage());
        }
    }

    private String byteToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);
            if(hex.length() == 1){
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
