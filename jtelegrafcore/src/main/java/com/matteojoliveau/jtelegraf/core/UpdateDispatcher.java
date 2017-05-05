package com.matteojoliveau.jtelegraf.core;

import com.matteojoliveau.telegram.api.Telegram;
import com.matteojoliveau.telegram.api.types.Update;

import java.util.List;
import java.util.Map;

public class UpdateDispatcher {

    private Telegram telegram;
    private Map<String, ContextCallbackMethod> actions;

    public UpdateDispatcher(Telegram telegram, Map<String, ContextCallbackMethod> actions) {
        this.telegram = telegram;
        this.actions = actions;
    }

    public void poll() {
        while (true) {
            List<Update> updates = telegram.getUpdates();
            updates.forEach(update -> {
                if (update.hasMessage()) {
                    new Context(update, telegram);
//                    actions.get("text").execute();
                }
            });
        }
    }
}
