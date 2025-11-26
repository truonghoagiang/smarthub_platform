package com.smarthub.integration.tuya.dto;

import java.util.Map;

public class TuyaDevice {

    private String id;
    private String name;
    private String localKey;
    private String category;
    private String productId;
    private String sub;
    private String assetId;
    private String uuid;
    private String ownerId;
    private Boolean online;
    private String ipAddress;
    private Long activeTime;
    private Long createdTime;
    Map<String, Object> status;

    public TuyaDevice() {
    }

    public TuyaDevice(String id, String name, String localKey, String category, String productId, String sub, String assetId, String uuid, String ownerId, Boolean online, String ipAddress, Long activeTime, Long createdTime, Map<String, Object> status) {
        this.id = id;
        this.name = name;
        this.localKey = localKey;
        this.category = category;
        this.productId = productId;
        this.sub = sub;
        this.assetId = assetId;
        this.uuid = uuid;
        this.ownerId = ownerId;
        this.online = online;
        this.ipAddress = ipAddress;
        this.activeTime = activeTime;
        this.createdTime = createdTime;
        this.status = status;
    }

    public static class Builder{
        private String id;
        private String name;
        private String localKey;
        private String category;
        private String productId;
        private String sub;
        private String assetId;
        private String uuid;
        private String ownerId;
        private Boolean online;
        private String ipAddress;
        private Long activeTime;
        private Long createdTime;
        Map<String, Object> status;

        public Builder id(String id) {
            this.id = id;
            return this;
        }
        public Builder name(String name) {
            this.name = name;
            return this;
        }
        public Builder localKey(String localKey) {
            this.localKey = localKey;
            return this;
        }
        public Builder category(String category) {
            this.category = category;
            return this;
        }
        public Builder productId(String productId) {
            this.productId = productId;
            return this;
        }
        public Builder sub(String sub) {
            this.sub = sub;
            return this;
        }
        public Builder assetId(String assetId) {
            this.assetId = assetId;
            return this;
        }
        public Builder uuid(String uuid) {
            this.uuid = uuid;
            return this;
        }
        public Builder ownerId(String ownerId) {
            this.ownerId = ownerId;
            return this;
        }
        public Builder online(Boolean online) {
            this.online = online;
            return this;
        }
        public Builder ipAddress(String ipAddress) {
            this.ipAddress = ipAddress;
            return this;
        }
        public Builder activeTime(Long activeTime) {
            this.activeTime = activeTime;
            return this;
        }
        public Builder createdTime(Long createdTime) {
            this.createdTime = createdTime;
            return this;
        }
        public Builder status(Map<String, Object> status) {
            this.status = status;
            return this;
        }
        public TuyaDevice build() {
            return new TuyaDevice(id,name,localKey,category,productId,sub,assetId,uuid,ownerId,online,ipAddress,activeTime,createdTime,status);
        }
    }
    public static Builder builder() {
        return new Builder();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocalKey() {
        return localKey;
    }

    public void setLocalKey(String localKey) {
        this.localKey = localKey;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public Boolean getOnline() {
        return online;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Long getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(Long activeTime) {
        this.activeTime = activeTime;
    }

    public Long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Long createdTime) {
        this.createdTime = createdTime;
    }

    public Map<String, Object> getStatus() {
        return status;
    }

    public void setStatus(Map<String, Object> status) {
        this.status = status;
    }
}
