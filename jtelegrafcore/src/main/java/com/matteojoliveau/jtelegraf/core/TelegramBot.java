package com.matteojoliveau.jtelegraf.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.matteojoliveau.telegram.api.Telegram;
import com.matteojoliveau.telegram.api.types.Message;
import com.matteojoliveau.telegram.api.types.Update;
import okhttp3.OkHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class TelegramBot {
    private static final Logger LOGGER = LoggerFactory.getLogger(TelegramBot.class);
    private final Telegram telegram;
    private final UpdateDispatcher dispatcher;
    private final Map<String, ContextCallbackMethod> actions;
    private final Map<String, ContextCallbackMethod> commands;

    public TelegramBot(String token) {
        this.telegram = new Telegram(token, new OkHttpClient(), new ObjectMapper());
        this.actions = new HashMap<>(0);
        commands = new HashMap<>();
        this.dispatcher = new UpdateDispatcher(telegram, this);
        LOGGER.info("Created TelegramBot");
    }

    public void on(String event, ContextCallbackMethod callback) {
        actions.put(event, callback);
        LOGGER.debug("Registered action for event: {}", event);
    }

    public void command(String command, ContextCallbackMethod callback) {
        LOGGER.debug("Regitered command: {}", command);
        commands.put("/" + command, callback);
    }

    synchronized ContextCallbackMethod getTextHandler() {
        return actions.get("text");
    }

    synchronized ContextCallbackMethod getCallbackQueryHandler() {
        return actions.get("callback_query");
    }

    synchronized ContextCallbackMethod getCommandHandler(String command) {
        return commands.get(command);
    }

    public void startPolling() {
        LOGGER.info("Started polling");
        dispatcher.poll();
    }

    public void stopPolling() {
        LOGGER.info("Stopped polling");
        dispatcher.stop();
    }

    UpdateHandler createUpdateHandler(Update update, ContextCallbackMethod callback) {
        LOGGER.debug("Created update handler for update: {} and with callback: {}", update, callback);
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

    @Override
    public String toString() {
        return "TelegramBot{" +
                "telegram=" + telegram +
                ", dispatcher=" + dispatcher +
                '}';
    }
}
