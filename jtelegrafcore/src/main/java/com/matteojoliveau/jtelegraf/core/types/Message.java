package com.matteojoliveau.jtelegraf.core.types;

import lombok.ToString;

import java.util.Date;
import java.util.List;

@ToString
public class Message {
    private Long id;
    private User user;
    private Chat chat;
    private Date date;
    private User forwardFrom;
    private Chat forwardFromChat;
    private Long forwardFromMessageId;
    private Date forwardDate;
    private Message replyToMessage;
    private Date editDate;
    private String text;
    private List<MessageEntity> entities;

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

    public Date getDate() {
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

    public Date getForwardDate() {
        return forwardDate;
    }

    public Message getReplyToMessage() {
        return replyToMessage;
    }

    public Date getEditDate() {
        return editDate;
    }

    public String getText() {
        return text;
    }

    public List<MessageEntity> getEntities() {
        return entities;
    }

    public static class Builder {
        private Long id;
        private User user;
        private Chat chat;
        private Date date;
        private User forwardFrom;
        private Chat forwardFromChat;
        private Long forwardFromMessageId;
        private Date forwardDate;
        private Message replyToMessage;
        private Date editDate;
        private String text;
        private List<MessageEntity> entities;

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

        public Builder date(Date date) {
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

        public Builder forwardDate(Date forwardDate) {
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

        public Builder entities(List<MessageEntity> entities) {
            this.entities = entities;
            return this;
        }

        public Message build() {
            return new Message(this);
        }
    }
}
