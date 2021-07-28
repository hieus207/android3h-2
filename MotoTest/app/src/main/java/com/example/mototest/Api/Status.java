package com.example.mototest.Api;

import java.io.Serializable;

public class Status implements Serializable {
    private String status;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Status(String status) {
        this.status = status;
    }
}
