package com.matteojoliveau.jtelegraf.telegram.api.types;

import lombok.ToString;
import org.codehaus.jackson.annotate.JsonProperty;

@ToString
public class Update {
    @JsonProperty("update_id")
    private Long id;
    @JsonProperty("message")
    private Message message;
    @JsonProperty("edited_message")
    private Message editedMessage;
    @JsonProperty("channel_post")
    private Message channelPost;
    @JsonProperty("edited_channel_post")
    private Message editedChannelPost;
    @JsonProperty("inline_query")
    private InlineQuery inlineQuery;
    @JsonProperty("chosen_inline_query")
    private ChosenInlineResult chosenInlineResult;
    @JsonProperty("callback_query")
    private CallbackQuery callbackQuery;

    public Update(Long id, Message message) {
        this.id = id;
        this.message = message;
    }

    public Update() {
    }

    public Long getId() {
        return id;
    }

    public Message getMessage() {
        return message;
    }


}
