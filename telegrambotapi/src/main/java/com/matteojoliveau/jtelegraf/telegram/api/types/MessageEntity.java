package com.matteojoliveau.jtelegraf.telegram.api.types;

public class MessageEntity {

    private EntityTypes types;
    private Integer offset;
    private Integer length;
    private String url;
    private User user;

    private enum EntityTypes {
        MENTION,
        HASHTAG,
        BOTCOMMAND,
        URL,
        EMAIL,
        BOLD,
        ITALIC,
        CODE,
        PRE,
        TEXTLINK,
        TEXTMENTION
    }
}
