package com.globits.Backend.response;

import com.globits.Backend.constant.ErrorCodeEnum;

public class NIMPEResponse<T> {
    private int code;
    private String message;
    private T data;
    private long total;

    public NIMPEResponse(T data) {
        this.data = data;
        this.code = 200;
    }

    public NIMPEResponse(int code, String message, T data, long total) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.total = total;
    }

    public NIMPEResponse(ErrorCodeEnum errorEnum) {
        this.code = errorEnum.getCode();
        this.message = errorEnum.getMessage();
    }

    public NIMPEResponse(T data, long total) {
        this.data = data;
        this.code = 200;
        this.total = total;
    }

    public NIMPEResponse(int code, T data, long total) {
        this.code = code;
        this.data = data;
        this.total = total;
    }
    public NIMPEResponse(String message) {
        this.message = message;
    }
    
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
