package com.smarthub.domain.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Map;

@Entity
@Table(name = "t_user_devices")
public class UserDeviceEntity {

    private Long Id;
    private String brandName;
    private String name;
    private String deviceType;
    private String room;
    private Boolean isOnline;
    private Boolean isActive;
    private Map<String, Object> capabilities;
    private Map<String, Object> currentState;
    private Map<String, Object> properties;
    private String model;
    private String firmwareVersion;
    private LocalDateTime lastActived;

    @Id
    @Column(name = "id")
    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    @Column(name = "brand_name")
    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "device_type")
    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    @Column(name = "room")
    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    @Column(name = "is_online")
    public Boolean getOnline() {
        return isOnline;
    }

    public void setOnline(Boolean online) {
        isOnline = online;
    }

    @Column(name = "is_actived")
    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    @Column(name = "capabilities")
    public Map<String, Object> getCapabilities() {
        return capabilities;
    }

    public void setCapabilities(Map<String, Object> capabilities) {
        this.capabilities = capabilities;
    }

    @Column(name = "current_state")
    public Map<String, Object> getCurrentState() {
        return currentState;
    }

    public void setCurrentState(Map<String, Object> currentState) {
        this.currentState = currentState;
    }

    @Column(name = "properties")
    public Map<String, Object> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }

    @Column(name = "model")
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Column(name = "firmware_version")
    public String getFirmwareVersion() {
        return firmwareVersion;
    }

    public void setFirmwareVersion(String firmwareVersion) {
        this.firmwareVersion = firmwareVersion;
    }

    @Column(name = "last_actived")
    public LocalDateTime getLastActived() {
        return lastActived;
    }

    public void setLastActived(LocalDateTime lastActived) {
        this.lastActived = lastActived;
    }

    public void updateState(Map<String, Object> newState){
        this.currentState = newState;
        this.lastActived = LocalDateTime.now();
    }

    public UserDeviceEntity() {
    }

    public UserDeviceEntity(Long id, String brandName, String name, String deviceType, String room, Boolean isOnline, Boolean isActive, Map<String, Object> capabilities, Map<String, Object> currentState, Map<String, Object> properties, String model, String firmwareVersion, LocalDateTime lastActived) {
        Id = id;
        this.brandName = brandName;
        this.name = name;
        this.deviceType = deviceType;
        this.room = room;
        this.isOnline = isOnline;
        this.isActive = isActive;
        this.capabilities = capabilities;
        this.currentState = currentState;
        this.properties = properties;
        this.model = model;
        this.firmwareVersion = firmwareVersion;
        this.lastActived = lastActived;
    }
}
