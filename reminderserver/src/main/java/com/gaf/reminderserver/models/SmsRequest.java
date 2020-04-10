package com.gaf.reminderserver.models;

public class SmsRequest {
    private String api_token;
    private String from;
    private String to;
    private String body;
    private String dnd;

    public String getApi_token() {
        return api_token;
    }

    public void setApi_token(String api_token) {
        this.api_token = api_token;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDnd() {
        return dnd;
    }

    public void setDnd(String dnd) {
        this.dnd = dnd;
    }
}
