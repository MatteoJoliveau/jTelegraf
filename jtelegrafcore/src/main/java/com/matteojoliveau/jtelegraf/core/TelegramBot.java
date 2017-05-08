package com.matteojoliveau.jtelegraf.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.matteojoliveau.telegram.api.Telegram;
import com.matteojoliveau.telegram.api.types.Update;
import okhttp3.OkHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public final class TelegramBot {
    private static final Logger LOGGER = LoggerFactory.getLogger(TelegramBot.class);
    private final Telegram telegram;
    private final UpdateDispatcher dispatcher;
    private final Map<String, ContextCallbackMethod> updateTypes;
    private final Map<String, ContextCallbackMethod> commands;
    private final Map<String, ContextCallbackMethod> textListeners;
    private final Map<String, ContextCallbackMethod> actions;

    public TelegramBot(String token) {
        this.telegram = new Telegram(token, new OkHttpClient(), new ObjectMapper());
        this.updateTypes = new HashMap<>(0);
        this.commands = new HashMap<>(0);
        this.textListeners = new HashMap<>(0);
        this.actions = new HashMap<>(0);
        this.dispatcher = new UpdateDispatcher(telegram, this);
        LOGGER.info("Created TelegramBot");
    }

    public void hears(String regex, ContextCallbackMethod callback) {
        textListeners.put(regex, callback);
        LOGGER.debug("Registered listener for regex: {}", regex);
    }

    public void on(String updateType, ContextCallbackMethod callback) {
        updateTypes.put(updateType, callback);
        LOGGER.debug("Registered action for update type: {}", updateType);
    }

    public void command(String command, ContextCallbackMethod callback) {
        commands.put("/" + command, callback);
        LOGGER.debug("Regitered command: {}", command);
    }

    public void action(String callbackData, ContextCallbackMethod callback) {
        actions.put(callbackData, callback);
        LOGGER.debug("Registered action for callback data: {}", callbackData);
    }

    synchronized ContextCallbackMethod getCallbackQueryHandler() {
        return updateTypes.get("callback_query");
    }

    synchronized Set<Map.Entry<String, ContextCallbackMethod>> getTextListeners() { return textListeners.entrySet(); }

    synchronized Set<Map.Entry<String, ContextCallbackMethod>> getCallbackActions() { return actions.entrySet(); }

    synchronized Optional<ContextCallbackMethod> getCommandHandler(String command) {
        return Optional.ofNullable(commands.get(command));
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
