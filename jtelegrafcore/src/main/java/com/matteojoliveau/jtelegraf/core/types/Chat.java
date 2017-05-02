package com.matteojoliveau.jtelegraf.core.types;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class Chat {
    private Long id;
    private ChatType type;
    private String title;
    private String username;
    private String firstName;
    private String lastName;
    private Boolean allMembersAdmin;

    private Chat(Builder builder) {
        this.id = builder.id;
        this.type = builder.type;
        switch (type) {
            case PRIVATE:
                this.firstName = builder.firstName;
                this.lastName = builder.lastName;
                this.username = builder.username;
                break;
            case SUPERGROUP:
                this.title = builder.title;
                this.username = builder.username;
                this.allMembersAdmin = builder.allMembersAdmin;
                break;
            case GROUP:
                this.title = builder.title;
                this.allMembersAdmin = builder.allMembersAdmin;
                break;
            case CHANNEL:
                this.title = builder.title;
                this.username = builder.username;
                break;
        }
    }

    public Long getId() {
        return id;
    }

    public ChatType getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Boolean getAllMembersAdmin() {
        return allMembersAdmin;
    }

    public String getUsername() {
        return username;
    }

    public static class Builder {
        private Long id;
        private ChatType type;
        private String title;
        private String username;
        private String firstName;
        private String lastName;
        private Boolean allMembersAdmin;


        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder type(ChatType type) {
            this.type = type;
            return this;
        }

        public Builder allMembersAdmin(boolean allMembersAdmin) {
            this.allMembersAdmin = allMembersAdmin;
            return this;
        }

        public Chat build() {
            return new Chat(this);
        }
    }

    public enum ChatType {
        PRIVATE("private"),
        GROUP("group"),
        SUPERGROUP("supergroup"),
        CHANNEL("channel");

        private final String label;

        ChatType(String label) {
            this.label = label;
        }


        @Override
        public String toString() {
            return label;
        }
    }
}
