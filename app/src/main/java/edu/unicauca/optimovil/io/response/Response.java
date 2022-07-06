package edu.unicauca.optimovil.io.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Response<T> {
    @Expose
    @SerializedName("api_status")
    private Integer status;
    @Expose
    @SerializedName("api_message")
    private String message;
    @Expose
    @SerializedName("data")
    private T data;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
}
