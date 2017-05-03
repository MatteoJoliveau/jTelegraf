package com.matteojoliveau.jtelegraf.core.types;

public class TelegramResultObj {
    private String code;
    private Object result;

    public TelegramResultObj(String code, Object result) {
        this.code = code;
        this.result = result;
    }

    public String getCode() {
        return code;
    }

    public Object getResult() {
        return result;
    }
}
