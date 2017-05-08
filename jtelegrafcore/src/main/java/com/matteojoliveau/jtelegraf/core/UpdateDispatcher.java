package com.matteojoliveau.jtelegraf.core;

import com.matteojoliveau.telegram.api.Telegram;
import com.matteojoliveau.telegram.api.types.CallbackQuery;
import com.matteojoliveau.telegram.api.types.Message;
import com.matteojoliveau.telegram.api.types.Update;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class UpdateDispatcher {
    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateDispatcher.class);
    private final Telegram telegram;
    private final TelegramBot bot;
    private final ExecutorService executor;
    private boolean poll = false;
    private Integer lastUpdateId = 0;

    UpdateDispatcher(Telegram telegram, TelegramBot bot) {
        this.telegram = telegram;
        this.bot = bot;
        this.executor = Executors.newFixedThreadPool(5);
        LOGGER.debug("Created UpdateDispatcher");
    }

    void poll() {
        poll = true;
        while (poll) {
            List<Update> updates = telegram.getUpdates(lastUpdateId++);
            updates.forEach((Update update) -> {
                LOGGER.debug("Received update: {}", update);
                lastUpdateId = update.getId() + 1;
                if (update.hasMessage()) {
                    Message message = update.getMessage();
                    if (message.getText().startsWith("/")) {
                        bot.getCommandHandler("X").ifPresent(commandHandler -> executor.execute(bot.createUpdateHandler(update, commandHandler)));
                    } else {
                        Set<Map.Entry<String, ContextCallbackMethod>> textHandlers = bot.getTextListeners();
                        if (!textHandlers.isEmpty()) {
                            textHandlers.forEach(entry -> {
                                if (message.getText().matches(entry.getKey())) {
                                    executor.execute(bot.createUpdateHandler(update, entry.getValue()));
                                }
                            });
                        }
                    }
                } else if (update.hasCallbackQuery()) {
                    CallbackQuery callbackQuery = update.getCallbackQuery();
                    Set<Map.Entry<String, ContextCallbackMethod>> callbackActions = bot.getCallbackActions();
                    if (!callbackActions.isEmpty()) {
                        callbackActions.forEach(entry -> {
                            if (entry.getKey().equals(callbackQuery.getData())) {
                                executor.execute(bot.createUpdateHandler(update, entry.getValue()));
                            } else {
                                ContextCallbackMethod callbackQueryHandler = bot.getCallbackQueryHandler();
                                if (!Objects.isNull(callbackQueryHandler)) {
                                    executor.execute(bot.createUpdateHandler(update, callbackQueryHandler));
                                }
                            }
                        });
                    }
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });


                /*try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/

        }
    }

    void stop() {
        poll = false;
    }
}