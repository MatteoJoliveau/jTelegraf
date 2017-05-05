package com.matteojoliveau.jtelegraf.core;

import com.matteojoliveau.telegram.api.Telegram;
import com.matteojoliveau.telegram.api.types.Message;
import com.matteojoliveau.telegram.api.types.Update;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class UpdateDispatcher {

        private final Telegram telegram;
        private final TelegramBot bot;
        private final ExecutorService executor;
        private boolean poll = false;
        private Integer lastUpdateId;

        UpdateDispatcher(Telegram telegram, TelegramBot bot) {
            this.telegram = telegram;
            this.bot = bot;
            this.executor = Executors.newFixedThreadPool(5);
        }

        void poll() {
            poll = true;
            while (poll) {
                List<Update> updates = telegram.getUpdates(lastUpdateId++);
                updates.forEach(update -> {
                    lastUpdateId = update.getId();
                    if (update.hasMessage()) {
                        Message message = update.getMessage();
                        if (Objects.equals('/', message.getText().charAt(0))) {
                            ContextCallbackMethod commandHandler = bot.getCommandHandler(message.getText());
                            if (!Objects.isNull(commandHandler)) {
                                executor.execute(bot.createUpdateHandler(update, commandHandler));
                            }
                        }
                        ContextCallbackMethod textHandler = bot.getTextHandler();
                        if (!Objects.isNull(textHandler)) {
                            executor.execute(bot.createUpdateHandler(update, textHandler));
                        }
                    }
                });


                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }

        void stop() {
            poll = false;
        }
    }