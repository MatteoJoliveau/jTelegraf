package com.matteojoliveau.jtelegraf.core.config;

public class TelegramInfo {
    private String token;

    public TelegramInfo(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
