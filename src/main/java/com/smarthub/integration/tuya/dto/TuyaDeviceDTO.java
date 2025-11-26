package com.smarthub.integration.tuya.dto;

import java.util.List;

public class TuyaDeviceDTO {
    public static class TuyaDeviceStatusResponse{
        private boolean success;
        private Long t;
        private Result result;

        public TuyaDeviceStatusResponse() {
        }

        public TuyaDeviceStatusResponse(boolean success, Long t, Result result) {
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

        public static class Result{
            private List<statusItem> statusItems;
        }

        public static class statusItem{
            private String code;
            private Object value;

            public statusItem() {
            }

            public statusItem(String code, Object value) {
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
        }
    }

    public static class TuyaDeviceListResponse{
        private boolean success;
        private Long t;
        private Result result;

        public TuyaDeviceListResponse() {
        }

        public TuyaDeviceListResponse(boolean success, Long t, Result result) {
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

        public static class Result{
            private List<TuyaDevice> devices;
            private Boolean hasMore;
            private String lasRowKey;

            public Result() {
            }

            public Result(List<TuyaDevice> devices, Boolean hasMore, String lasRowKey) {
                this.devices = devices;
                this.hasMore = hasMore;
                this.lasRowKey = lasRowKey;
            }

            public List<TuyaDevice> getDevices() {
                return devices;
            }

            public void setDevices(List<TuyaDevice> devices) {
                this.devices = devices;
            }

            public Boolean getHasMore() {
                return hasMore;
            }

            public void setHasMore(Boolean hasMore) {
                this.hasMore = hasMore;
            }

            public String getLasRowKey() {
                return lasRowKey;
            }

            public void setLasRowKey(String lasRowKey) {
                this.lasRowKey = lasRowKey;
            }
        }
    }
}
