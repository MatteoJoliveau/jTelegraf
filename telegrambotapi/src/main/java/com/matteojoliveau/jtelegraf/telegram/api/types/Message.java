package com.matteojoliveau.jtelegraf.telegram.api.types;

import lombok.ToString;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.Arrays;
import java.util.List;

@ToString
public class Message {
    @JsonProperty("message_id")
    private Long id;
    @JsonProperty("from")
    private User user;
    @JsonProperty("chat")
    private Chat chat;
    @JsonProperty("date")
    private Integer date;
    @JsonProperty("forward_from")
    private User forwardFrom;
    @JsonProperty("forward_from_chat")
    private Chat forwardFromChat;
    @JsonProperty("forward_from_message_id")
    private Long forwardFromMessageId;
    @JsonProperty("forward_date")
    private Integer forwardDate;
    @JsonProperty("reply_to_message")
    private Message replyToMessage;
    @JsonProperty("edit_date")
    private Integer editDate;
    @JsonProperty("text")
    private String text;
    @JsonProperty("entities")
    private MessageEntity[] entities;

    public Message() {
    }

    private Message(Builder builder) {
        this.id = builder.id;
        this.user = builder.user;
        this.chat = builder.chat;
        this.date = builder.date;
        this.forwardFrom = builder.forwardFrom;
        this.forwardFromChat = builder.forwardFromChat;
        this.forwardFromMessageId = builder.forwardFromMessageId;
        this.forwardDate = builder.forwardDate;
        this.replyToMessage = builder.replyToMessage;
        this.editDate = builder.editDate;
        this.text = builder.text;
        this.entities = builder.entities;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Chat getChat() {
        return chat;
    }

    public Integer getDate() {
        return date;
    }

    public User getForwardFrom() {
        return forwardFrom;
    }

    public Chat getForwardFromChat() {
        return forwardFromChat;
    }

    public Long getForwardFromMessageId() {
        return forwardFromMessageId;
    }

    public Integer getForwardDate() {
        return forwardDate;
    }

    public Message getReplyToMessage() {
        return replyToMessage;
    }

    public Integer getEditDate() {
        return editDate;
    }

    public String getText() {
        return text;
    }

    public List<MessageEntity> getEntities() {
        return Arrays.asList(entities);
    }

    public static class Builder {
        private Long id;
        private User user;
        private Chat chat;
        private Integer date;
        private User forwardFrom;
        private Chat forwardFromChat;
        private Long forwardFromMessageId;
        private Integer forwardDate;
        private Message replyToMessage;
        private Integer editDate;
        private String text;
        private MessageEntity[] entities;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder user(User user) {
            this.user = user;
            return this;
        }

        public Builder chat(Chat chat) {
            this.chat = chat;
            return this;
        }

        public Builder date(Integer date) {
            this.date = date;
            return this;
        }

        public Builder forwardFrom(User forwardFrom) {
            this.forwardFrom = forwardFrom;
            return this;
        }

        public Builder forwardFromChat(Chat forwardFromChat) {
            this.forwardFromChat = forwardFromChat;
            return this;
        }

        public Builder forwardFromMessageId(Long id) {
            this.forwardFromMessageId = id;
            return this;
        }

        public Builder forwardDate(Integer forwardDate) {
            this.forwardDate = forwardDate;
            return this;
        }

        public Builder replyToMessage(Message message) {
            this.replyToMessage = message;
            return this;
        }

        public Builder text(String text) {
            this.text = text;
            return this;
        }

        public Builder entities(MessageEntity[] entities) {
            this.entities = entities;
            return this;
        }

        public Message build() {
            return new Message(this);
        }
    }
}
