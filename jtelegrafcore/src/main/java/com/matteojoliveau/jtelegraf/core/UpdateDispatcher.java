package com.matteojoliveau.jtelegraf.core;

import com.matteojoliveau.telegram.api.Telegram;
import com.matteojoliveau.telegram.api.types.Message;
import com.matteojoliveau.telegram.api.types.Update;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Objects;
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
            updates.forEach(update -> {
                LOGGER.debug("Received update: {}", update);
                lastUpdateId = update.getId() + 1;
                if (update.hasMessage()) {
                    Message message = update.getMessage();
                    if (Objects.equals('/', message.getText().charAt(0))) {
                        ContextCallbackMethod commandHandler = bot.getCommandHandler(message.getText());
                        if (!Objects.isNull(commandHandler)) {
                            executor.execute(bot.createUpdateHandler(update, commandHandler));
                        }
                    } else {
                        ContextCallbackMethod textHandler = bot.getTextHandler();
                        if (!Objects.isNull(textHandler)) {
                            executor.execute(bot.createUpdateHandler(update, textHandler));
                        }
                    }
                } else if (update.hasCallbackQuery()) {
                    ContextCallbackMethod callbackQueryHandler = bot.getCallbackQueryHandler();
                    if (!Objects.isNull(callbackQueryHandler)) {
                        executor.execute(bot.createUpdateHandler(update, callbackQueryHandler));
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