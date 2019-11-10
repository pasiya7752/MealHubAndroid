package com.example.mealhubandroid.Models;


public class NotificationVM {
    String id;
    String Header;
    String Message;

    public NotificationVM(String header, String message) {
        Header = header;
        Message = message;
    }

    public String getHeader() {
        return Header;
    }

    public void setHeader(String header) {
        Header = header;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }
}