package com.matteojoliveau.jtelegraf.core;

import com.matteojoliveau.jtelegraf.telegram.api.Telegram;
import com.matteojoliveau.jtelegraf.telegram.api.types.Chat;
import com.matteojoliveau.jtelegraf.telegram.api.types.Update;
import com.matteojoliveau.jtelegraf.telegram.api.types.User;
import com.matteojoliveau.jtelegraf.core.config.TelegramInfo;
import okhttp3.OkHttpClient;
import org.codehaus.jackson.map.ObjectMapper;

public class Context {
    private TelegramInfo telegramInfo;
    private Update update;
    private final Telegram telegram;

    public Context(TelegramInfo telegramInfo, Update update) {
        this.telegramInfo = telegramInfo;
        this.update = update;
        this.telegram = new Telegram(telegramInfo.getToken(), new OkHttpClient(), new ObjectMapper());
    }

    public Update update() { return update; }

    public User from() {
        return update.getMessage().getUser();
    }

    public Chat chat() {
        return update.getMessage().getChat();
    }

    public User me() { return telegram.getMe(); }

}
