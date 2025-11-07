package com.smarthub.domain.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserInfoResponse {

    private Integer id;
    private String email;

    @JsonProperty("full_name")
    private String fullname;

    @JsonProperty("phone_number")
    private String phoneNumber;

    private String Role;

    @JsonProperty("avatar_url")
    private String avatarUrl;

    @JsonProperty("email_verified")
    private Boolean emailVerified;

    @JsonProperty("phone_verified")
    private Boolean phoneVerified;

    public UserInfoResponse() {
    }

    public UserInfoResponse(Integer id, String email, String fullname, String phoneNumber, String role, String avatarUrl, Boolean emailVerified, Boolean phoneVerified) {
        this.id = id;
        this.email = email;
        this.fullname = fullname;
        this.phoneNumber = phoneNumber;
        Role = role;
        this.avatarUrl = avatarUrl;
        this.emailVerified = emailVerified;
        this.phoneVerified = phoneVerified;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public Boolean getEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(Boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public Boolean getPhoneVerified() {
        return phoneVerified;
    }

    public void setPhoneVerified(Boolean phoneVerified) {
        this.phoneVerified = phoneVerified;
    }

    public static class Builder {
        private Integer id;
        private String email;
        private String fullName;
        private String phoneNumber;
        private String role;
        private String avatarUrl;
        private Boolean emailVerified;
        private Boolean phoneVerified;

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder fullName(String fullName) {
            this.fullName = fullName;
            return this;
        }

        public Builder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder role(String role) {
            this.role = role;
            return this;
        }

        public Builder avatarUrl(String avatarUrl) {
            this.avatarUrl = avatarUrl;
            return this;
        }
        public Builder emailVerified(Boolean emailVerified) {
            this.emailVerified = emailVerified;
            return this;
        }

        public Builder phoneVerified(Boolean phoneVerified) {
            this.phoneVerified = phoneVerified;
            return this;
        }

        public UserInfoResponse build() {
            return new UserInfoResponse(id, email, fullName, phoneNumber, role, avatarUrl, emailVerified, phoneVerified);
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}
