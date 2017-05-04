package com.matteojoliveau.jtelegraf.telegram.api.types.results;

import com.matteojoliveau.jtelegraf.telegram.api.types.User;
import org.codehaus.jackson.annotate.JsonProperty;

public class UserResultObject {
    @JsonProperty("ok")
    private String ok;
    @JsonProperty("result")
    private User user;

    public UserResultObject(String ok, User user) {
        this.ok = ok;
        this.user = user;
    }

    public UserResultObject() {
    }

    public String getOk() {
        return ok;
    }

    public User getUser() {
        return user;
    }
}
