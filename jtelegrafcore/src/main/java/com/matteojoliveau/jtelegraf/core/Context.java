package com.matteojoliveau.jtelegraf.core;

import com.matteojoliveau.telegram.api.Telegram;
import com.matteojoliveau.telegram.api.types.Chat;
import com.matteojoliveau.telegram.api.types.Message;
import com.matteojoliveau.telegram.api.types.Update;
import com.matteojoliveau.telegram.api.types.User;
import com.matteojoliveau.telegram.api.types.requests.SendMessage;

public class Context {
    private Update update;
    private final Telegram telegram;

    public Context(Update update, Telegram telegram) {
        this.update = update;
        this.telegram = telegram;
    }

    public Update update() { return update; }

    public User from() {
        return update.getMessage().getUser();
    }

    public Chat chat() {
        return update.getMessage().getChat();
    }

    public User me() { return telegram.getMe(); }

    public Message reply(String text) {
        return telegram.sendMessage(update.getMessage().getChat().getId(), text);
    }

    public Message replyWithHtml(String text) {
        SendMessage message = new SendMessage.Builder().chatId(update.getMessage().getChat().getId()).text(text).parseMode("HTML").build();
        return telegram.sendMessage(message);
    }

    public Message replyWithMarkdown(String text) {
        SendMessage message = new SendMessage.Builder().chatId(update.getMessage().getChat().getId()).text(text).parseMode("Markdown").build();
        return telegram.sendMessage(message);
    }

}
