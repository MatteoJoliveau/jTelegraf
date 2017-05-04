package com.matteojoliveau.jtelegraf.telegram.api.types.results;

import com.matteojoliveau.jtelegraf.telegram.api.types.Chat;
import org.codehaus.jackson.annotate.JsonProperty;

public class ChatResultObject {
    @JsonProperty("ok")
    private String ok;
    @JsonProperty("result")
    private Chat chat;

    public ChatResultObject(String ok, Chat chat) {
        this.ok = ok;
        this.chat = chat;
    }

    public ChatResultObject() {
    }

    public String getOk() {
        return ok;
    }

    public Chat getChat() {
        return chat;
    }
}
