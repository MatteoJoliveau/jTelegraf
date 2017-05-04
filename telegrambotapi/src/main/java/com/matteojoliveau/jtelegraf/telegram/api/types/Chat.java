package com.matteojoliveau.jtelegraf.telegram.api.types;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.codehaus.jackson.annotate.JsonProperty;

@EqualsAndHashCode
@ToString
public class Chat {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("type")
    private String type;
    @JsonProperty("title")
    private String title;
    @JsonProperty("username")
    private String username;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("all_members_are_administrators")
    private Boolean allMembersAdmin;

    private Chat(Builder builder) {
        this.id = builder.id;
        this.type = builder.type;
        switch (type) {
            case "private":
                this.firstName = builder.firstName;
                this.lastName = builder.lastName;
                this.username = builder.username;
                break;
            case "supergroup":
                this.title = builder.title;
                this.username = builder.username;
                this.allMembersAdmin = builder.allMembersAdmin;
                break;
            case "group":
                this.title = builder.title;
                this.allMembersAdmin = builder.allMembersAdmin;
                break;
            case "channel":
                this.title = builder.title;
                this.username = builder.username;
                break;
        }
    }

    public Chat() {
    }

    public Long getId() {
        return id;
    }

    public String getType() {
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
        private String type;
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

        public Builder type(String type) {
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

}
