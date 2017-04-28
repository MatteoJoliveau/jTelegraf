package com.matteojoliveau.jtelegraf.core.types;

public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private String username;

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

    public Long getId() {
        return id;
    }

    public String getName() {
        return firstName;
    }

    public String getSurname() {
        return lastName;
    }

    public String getUsername() {
        return username;
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
