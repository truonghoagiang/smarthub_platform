package com.smarthub.integration.core;

public class CommandResult {
    private boolean success;
    private String message;
    private Object data;

    public CommandResult() {
    }

    public CommandResult(boolean success, String message, Object data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public static class Builder{
        private boolean success;
        private String message;
        private Object data;

        public Builder isuccess(boolean success) {
            this.success = success;
            return this;
        }

        public Builder message(String message){
            this.message = message;
            return this;
        }

        public Builder data(Object data){
            this.data = data;
            return this;
        }

        public CommandResult build(){
            return new CommandResult(success, message, data);
        }
    }

    public static Builder builder(){
        return new Builder();
    }

    public static CommandResult success() {
        return CommandResult.builder()
                .isuccess(true)
                .message("Command executed successfully")
                .build();
    }

    public static CommandResult success(String message){
        return CommandResult.builder()
                .isuccess(true)
                .message(message)
                .build();
    }

    public static CommandResult failure(String message){
        return CommandResult.builder()
                .isuccess(false)
                .message(message)
                .build();
    }
}
