package com.smarthub.integration.tuya.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TuyaToken {

    public static class tokenRequest{
        @JsonProperty("grand_type")
        private String grandType;

        public tokenRequest() {
        }

        public tokenRequest(String grandType) {
            this.grandType = grandType;
        }

        public String getGrandType() {
            return grandType;
        }

        public void setGrandType(String grandType) {
            this.grandType = grandType;
        }

        public static class Builder {
            private String grandType;

            public Builder grandType(String grandType) {
                this.grandType = grandType;
                return this;
            }

            public tokenRequest build() {
                return new tokenRequest(grandType);
            }
        }

        public static Builder builder() {
            return new Builder();
        }

    }

    public static class tokenResponse{
        private boolean success;
        private Long t;
        private Result result;

        public tokenResponse() {
        }

        public tokenResponse(boolean success, Long t, Result result) {
            this.success = success;
            this.t = t;
            this.result = result;
        }

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public Long getT() {
            return t;
        }

        public void setT(Long t) {
            this.t = t;
        }

        public Result getResult() {
            return result;
        }

        public void setResult(Result result) {
            this.result = result;
        }

        public static class Builder {
            private boolean success;
            private Long t;
            private Result result;

            public Builder success(boolean success) {
                this.success = success;
                return this;
            }
            public Builder t(Long t) {
                this.t = t;
                return this;
            }
            public Builder result(Result result) {
                this.result = result;
                return this;
            }

            public tokenResponse build() {
                return new tokenResponse(success, t, result);
            }
        }

        public static Builder builder() {
            return new Builder();
        }

        public static class Result{

            @JsonProperty("access_token")
            String accessToken;

            @JsonProperty("expire_time")
            private Long expireTime;

            @JsonProperty("refresh_token")
            String refreshToken;

            private String uid;

            public Result(String accessToken, Long expireTime, String refreshToken, String uid) {
                this.accessToken = accessToken;
                this.expireTime = expireTime;
                this.refreshToken = refreshToken;
                this.uid = uid;
            }

            public Result() {
            }
        }
    }

}
