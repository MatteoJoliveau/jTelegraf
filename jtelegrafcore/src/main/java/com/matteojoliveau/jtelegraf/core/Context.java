package com.matteojoliveau.jtelegraf.core;

import com.matteojoliveau.telegram.api.Telegram;
import com.matteojoliveau.telegram.api.types.*;
import com.matteojoliveau.telegram.api.types.markups.Markup;
import com.matteojoliveau.telegram.api.types.requests.SendMessage;

public class Context {
    private Update update;
    private final Telegram telegram;
    private final Integer chatId;
    private final User user;

    public Context(Update update, Telegram telegram) {
        this.update = update;
        this.telegram = telegram;
        if (update.hasMessage()) {
            this.chatId = update.getMessage().getChat().getId();
            this.user = update.getMessage().getUser();
        } else if (update.hasCallbackQuery()) {
            this.chatId = update.getCallbackQuery().getMessage().getChat().getId();
            this.user = update.getCallbackQuery().getFrom();
        } else {
            this.chatId = null;
            this.user = null;
        }
    }

    public Update update() { return update; }

    public User from() {
        return update.getMessage().getUser();
    }

    public Chat chat() {
        return update.getMessage().getChat();
    }

    public User me() { return telegram.getMe(); }

    public CallbackQuery callbackQuery() { return update.getCallbackQuery(); }

    public Message reply(String text) {
            return telegram.sendMessage(chatId, text);
    }

    public Message reply(String text, Markup markup) {
        SendMessage message = new SendMessage.Builder()
                .chatId(update.getMessage().getChat().getId())
                .text(text)
                .replyMarkup(markup)
                .build();
        return telegram.sendMessage(message);
    }

    public Message replyWithHtml(String text) {
        SendMessage message = new SendMessage.Builder()
                .chatId(update.getMessage().getChat().getId())
                .text(text)
                .parseMode("HTML")
                .build();
        return telegram.sendMessage(message);
    }

    public Message replyWithHtml(String text, Markup markup) {
        SendMessage message = new SendMessage.Builder()
                .chatId(update.getMessage().getChat().getId())
                .text(text)
                .replyMarkup(markup)
                .parseMode("HTML")
                .build();
        return telegram.sendMessage(message);
    }

    public Message replyWithMarkdown(String text) {
        SendMessage message = new SendMessage.Builder().chatId(update.getMessage().getChat().getId()).text(text).parseMode("Markdown").build();
        return telegram.sendMessage(message);
    }

    public Message replyWithMarkdown(String text, Markup markup) {
        SendMessage message = new SendMessage.Builder()
                .chatId(update.getMessage().getChat().getId())
                .text(text)
                .replyMarkup(markup)
                .parseMode("Markdown")
                .build();
        return telegram.sendMessage(message);
    }

}
