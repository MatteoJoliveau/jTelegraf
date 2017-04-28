package com.matteojoliveau.jtelegraf.core.types;

import java.util.Date;
import java.util.List;

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
}
