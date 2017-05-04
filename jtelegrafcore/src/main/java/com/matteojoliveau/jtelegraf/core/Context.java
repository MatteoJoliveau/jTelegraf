package com.matteojoliveau.jtelegraf.core;

import com.matteojoliveau.jtelegraf.telegram.api.types.Chat;
import com.matteojoliveau.jtelegraf.telegram.api.types.Update;
import com.matteojoliveau.jtelegraf.telegram.api.types.User;
import com.matteojoliveau.jtelegraf.core.config.TelegramInfo;

public class Context {
    private TelegramInfo telegramInfo;
    private Update update;

    public Context(TelegramInfo telegramInfo, Update update) {
        this.telegramInfo = telegramInfo;
        this.update = update;
    }

    public User from() {
        return update.getMessage().getUser();
    }

    public Chat chat() {
        return update.getMessage().getChat();
    }


}
