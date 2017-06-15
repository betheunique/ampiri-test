package com.glispa.ampiri.model;

/**
 * @author abhishekrai
 * @since 10/05/2017
 */
public class GenericResponse implements DefaultResponse {

    private int status;
    private String message;

    public int getStatus() {
        return status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "GenericResponse{" +
                "status=" + status +
                ", message='" + message + '\'' +
                '}';
    }
}
