package com.matteojoliveau.jtelegraf.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.matteojoliveau.telegram.api.Telegram;
import com.matteojoliveau.telegram.api.types.Message;
import com.matteojoliveau.telegram.api.types.Update;
import okhttp3.OkHttpClient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class TelegramBot {
    private final Telegram telegram;
    private final UpdateDispatcher dispatcher;
    private final Map<String, ContextCallbackMethod> actions;
    private final Map<String, ContextCallbackMethod> commands;

    public TelegramBot(String token) {
        this.telegram = new Telegram(token, new OkHttpClient(), new ObjectMapper());
        this.actions = new HashMap<>(0);
        commands = new HashMap<>();
        this.dispatcher = new UpdateDispatcher(telegram, this);
    }

    public void on(String event, ContextCallbackMethod callback) {
        actions.put(event, callback);
    }

    public void command(String command, ContextCallbackMethod callback) {
        commands.put("/" + command, callback);
    }

    synchronized ContextCallbackMethod getTextHandler() {
        return actions.get("text");
    }

    synchronized ContextCallbackMethod getCommandHandler(String command) {
        return commands.get(command);
    }

    public void startPolling() {
        dispatcher.poll();
    }

    public void stopPolling() {
        dispatcher.stop();
    }

    UpdateHandler createUpdateHandler(Update update, ContextCallbackMethod callback) {
        return new UpdateHandler(update, callback);
    }


    private class UpdateHandler implements Runnable {

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
