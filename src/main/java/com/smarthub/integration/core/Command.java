package com.smarthub.integration.core;

public class Command {
    private String code;
    private Object value;

    public Command() {
    }

    public Command(String code, Object value) {
        this.code = code;
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public static class Builder{
        private String code;
        private Object value;

        public Builder code(String code){
            this.code = code;
            return this;
        }
        public Builder value(Object value){
            this.value = value;
            return this;
        }

        public Command build(){
            return new Command(code,value);
        }
    }

    public static Builder builder(){
        return new Builder();
    }
}
