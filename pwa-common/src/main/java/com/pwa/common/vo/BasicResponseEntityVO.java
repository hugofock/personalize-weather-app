package com.pwa.common.vo;

public final class BasicResponseEntityVO<T> extends BaseVO {

    private String status;
    private String message;
    private String url;
    private T object;

    public BasicResponseEntityVO() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }

}
