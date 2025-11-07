package com.smarthub.domain.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RefrestTokenRequest {

    @JsonProperty("refresh_token")
    private String refrestToken;

    public RefrestTokenRequest() {
    }

    public RefrestTokenRequest(String refrestToken) {
        this.refrestToken = refrestToken;
    }

    public String getRefrestToken() {
        return refrestToken;
    }

    public void setRefrestToken(String refrestToken) {
        this.refrestToken = refrestToken;
    }

    @Override
    public String toString() {
        return "RefrestTokenRequest{" +
                "refrestToken='" + refrestToken + '\'' +
                '}';
    }
}
