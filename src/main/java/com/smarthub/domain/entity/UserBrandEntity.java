package com.smarthub.domain.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = "UserBrandEntity.findByUserId",
                procedureName = "f_get_brand_connect_by_user",
                resultClasses = UserBrandEntity.class,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "i_user_id", type = Long.class)
                }
        )
})

@Entity
@Table(name = "t_user_device")
public class UserBrandEntity {

    private Long Id;
    private Long userId;
    private String brandName;
    private String authType;
    private String accountIdentifier;
    private String displayName;
    private String accessToken;
    private String refreshToken;
    private LocalDateTime expiresTime;
    private String apiKey;
    private String apiSecret;
    private String additionalConfig;
    private String isActive;
    private String isEnableSynced;
    private LocalDateTime lastSyncedTime;

    @Id
    @Column(name = "id")
    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    @Column(name = "user_id")
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Column(name = "brand_name")
    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    @Column(name = "auth_type")
    public String getAuthType() {
        return authType;
    }

    public void setAuthType(String authType) {
        this.authType = authType;
    }

    @Column(name = "account_identifier")
    public String getAccountIdentifier() {
        return accountIdentifier;
    }

    public void setAccountIdentifier(String accountIdentifier) {
        this.accountIdentifier = accountIdentifier;
    }

    @Column(name = "display_name")
    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Column(name = "access_token")
    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @Column(name = "refresh_token")
    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    @Column(name = "expired_date")
    public LocalDateTime getExpiresTime() {
        return expiresTime;
    }

    public void setExpiresTime(LocalDateTime expiresTime) {
        this.expiresTime = expiresTime;
    }

    @Column(name = "api_key")
    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    @Column(name = "api_secret")
    public String getApiSecret() {
        return apiSecret;
    }

    public void setApiSecret(String apiSecret) {
        this.apiSecret = apiSecret;
    }

    @Column(name = "additional_config")
    public String getAdditionalConfig() {
        return additionalConfig;
    }

    public void setAdditionalConfig(String additionalConfig) {
        this.additionalConfig = additionalConfig;
    }

    @Column(name = "actived")
    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    @Column(name = "synced_enable")
    public String getIsEnableSynced() {
        return isEnableSynced;
    }

    public void setIsEnableSynced(String isEnableSynced) {
        this.isEnableSynced = isEnableSynced;
    }

    @Column(name = "last_synced_at")
    public LocalDateTime getLastSyncedTime() {
        return lastSyncedTime;
    }

    public void setLastSyncedTime(LocalDateTime lastSyncedTime) {
        this.lastSyncedTime = lastSyncedTime;
    }

    public boolean isTokenExpired(){
        return getExpiresTime() != null && LocalDateTime.now().isAfter(getExpiresTime());
    }

    public UserBrandEntity() {
    }

    public UserBrandEntity(Long id, Long userId, String brandName, String authType, String accountIdentifier, String displayName, String accessToken, String refreshToken, LocalDateTime expiresTime, String apiKey, String apiSecret, String additionalConfig, String isActive, String isEnableSynced, LocalDateTime lastSyncedTime) {
        Id = id;
        this.userId = userId;
        this.brandName = brandName;
        this.authType = authType;
        this.accountIdentifier = accountIdentifier;
        this.displayName = displayName;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.expiresTime = expiresTime;
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
        this.additionalConfig = additionalConfig;
        this.isActive = isActive;
        this.isEnableSynced = isEnableSynced;
        this.lastSyncedTime = lastSyncedTime;
    }
}
