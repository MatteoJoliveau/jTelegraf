package com.matteojoliveau.jtelegraf.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.matteojoliveau.telegram.api.Telegram;
import com.matteojoliveau.telegram.api.types.Update;
import okhttp3.OkHttpClient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class TelegramBot {
    private final Telegram telegram;
    private final UpdateDispatcher dispatcher;
    private final ExecutorService executor;
    private final Map<String, ContextCallbackMethod> actions;

    public TelegramBot(String token) {
        this.telegram = new Telegram(token, new OkHttpClient(), new ObjectMapper());
        this.executor = Executors.newFixedThreadPool(5);
        this.actions = new HashMap<>(0);
        this.dispatcher = new UpdateDispatcher(telegram, actions);
    }

    public void on(String event, ContextCallbackMethod callback) {
        actions.put(event, callback);
        List<Update> updates = telegram.getUpdates();
        for (Update update : updates) {
            UpdateHandler updateHandler = new UpdateHandler(update, callback);
            executor.execute(updateHandler);

        }
        System.out.println("Doing stuff");
    }

    public class UpdateHandler implements Runnable {

        private final Update update;
        private ContextCallbackMethod callback;

        public UpdateHandler(Update update, ContextCallbackMethod callback) {
            this.update = update;
            this.callback = callback;
        }

        @Override
        public void run() {
            Context context = new Context(update, telegram);
            callback.execute(context);
        }
    }
}
