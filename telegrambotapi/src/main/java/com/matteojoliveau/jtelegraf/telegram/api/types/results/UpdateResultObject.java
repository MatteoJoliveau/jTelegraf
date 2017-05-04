package com.matteojoliveau.jtelegraf.telegram.api.types.results;

import com.matteojoliveau.jtelegraf.telegram.api.types.Update;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.Arrays;
import java.util.List;

public class UpdateResultObject {
    @JsonProperty("ok")
    private String ok;
    @JsonProperty("result")
    private Update[] updates;

    public UpdateResultObject(String ok, Update[] updates) {
        this.ok = ok;
        this.updates = updates;
    }

    public UpdateResultObject() {
    }

    public String getOk() {
        return ok;
    }

    public List<Update> getUpdates() {
        return Arrays.asList(updates);
    }
}
