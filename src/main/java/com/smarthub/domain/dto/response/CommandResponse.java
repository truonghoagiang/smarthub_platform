package com.smarthub.domain.dto.response;

public class CommandResponse {
    private boolean success;
    private Long t;
    private Object result;
    private String message;

    public CommandResponse() {
    }

    public CommandResponse(boolean success, Long t, Object result, String message) {
        this.success = success;
        this.t = t;
        this.result = result;
        this.message = message;
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

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
