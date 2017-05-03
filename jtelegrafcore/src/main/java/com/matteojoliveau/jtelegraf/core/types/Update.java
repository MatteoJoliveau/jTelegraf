package com.matteojoliveau.jtelegraf.core.types;

import lombok.ToString;

@ToString
public class Update {
    private Long id;
    private Message message;
    private Message editedMessage;
    private Message channelPost;
    private Message editedChannelPost;
    private InlineQuery inlineQuery;
    private ChosenInlineResult chosenInlineResult;
    private CallbackQuery callbackQuery;

    public Update(Long id, Message message) {
        this.id = id;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public Message getMessage() {
        return message;
    }


}
