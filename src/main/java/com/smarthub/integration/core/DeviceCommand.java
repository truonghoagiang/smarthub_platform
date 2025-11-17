package com.smarthub.integration.core;

import com.smarthub.utils.Constant;

import java.util.Map;

public class DeviceCommand {

    private String type;
    private Map<String, Object> parameters;

    public DeviceCommand() {
    }

    public DeviceCommand(String type, Map<String, Object> parameters) {
        this.type = type;
        this.parameters = parameters;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, Object> parameters) {
        this.parameters = parameters;
    }

    public static class Builder{
        private String type;
        private Map<String, Object> parameters;

        public Builder type(String type) {
            this.type = type;
            return this;
        }

        public Builder parameters(Map<String, Object> parameters) {
            this.parameters = parameters;
            return this;
        }

        public DeviceCommand build() {
            return new DeviceCommand(type, parameters);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public static DeviceCommand turnOn(){
        return DeviceCommand.builder()
                .type(Constant.COMMAND_TURN_ON)
                .build();
    }

    public static DeviceCommand turnOff(){
        return DeviceCommand.builder()
                .type(Constant.COMMAND_TURN_OFF)
                .build();
    }

    public static DeviceCommand setBrightness(int brightness){
        return DeviceCommand.builder()
                .type(Constant.COMMAND_SET_BRIGHTNESS)
                .parameters(Map.of("brightness",brightness))
                .build();
    }

    public static DeviceCommand setColor(int red, int green, int blue){
        return DeviceCommand.builder()
                .type(Constant.COMMAND_SET_COLOR)
                .parameters(Map.of("red",red, "green", green, "blue", blue))
                .build();
    }
}
