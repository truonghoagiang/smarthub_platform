package com.smarthub.integration.core;

import java.time.LocalDateTime;

public class BrandAuthResult {
    private String accessToken;
    private String refreshToken;
    private Long expiresIn; //in seconds
    private LocalDateTime expiresAt;
    private Long userId;

    public BrandAuthResult(String accessToken, String refreshToken, Long expiresIn, LocalDateTime expiresAt, Long userId) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.expiresIn = expiresIn;
        this.expiresAt = expiresAt;
        this.userId = userId;
    }

    public static class Builder{
        private String accessToken;
        private String refreshToken;
        private Long expiresIn;
        private LocalDateTime expiresAt;
        private Long userId;

        public Builder accessToken(String accessToken){
            this.accessToken = accessToken;
            return this;
        }

        public Builder refreshToken(String refreshToken){
            this.refreshToken = refreshToken;
            return this;
        }

        public Builder expiresIn(Long expiresIn){
            this.expiresIn = expiresIn;
            return this;
        }

        public Builder expiresAt(LocalDateTime expiresAt){
            this.expiresAt = expiresAt;
            return this;
        }

        public Builder userId(Long userId){
            this.userId = userId;
            return this;
        }

        public BrandAuthResult build(){
            return new BrandAuthResult(accessToken, refreshToken, expiresIn, expiresAt, userId);
        }
    }

    public static Builder builder(){
        return new Builder();
    }
}
