package com.matteojoliveau.jtelegraf.telegram.api.types;

import lombok.Getter;
import lombok.ToString;
import org.codehaus.jackson.annotate.JsonProperty;

@Getter
@ToString
public class User {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("username")
    private String username;

    public User() {
    }

    public User(Long id, String firstName) {
        this.id = id;
        this.firstName = firstName;
    }

    private User(Builder builder) {
        this.id = builder.id;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.username = builder.username;
    }

    public String getName() {
        return firstName;
    }

    public String getSurname() {
        return lastName;
    }

    public static class Builder {
        private Long id;
        private String firstName;
        private String lastName;
        private String username;

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

        public User build() {
            return new User(this);
        }
    }
}
