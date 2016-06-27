package com.pwa.builder;

import com.pwa.model.User;
import org.joda.time.DateTime;

public class UserBuilder {

    private String id;
    private String username;
    private String password;
    private String role;
    private Long version;
    private String createdBy;
    private DateTime createdDate;
    private String lastModifiedBy;
    private DateTime lastModifiedDate;

    public User build() {
        return new User(id, username, password, role, version, createdBy, createdDate, lastModifiedBy, lastModifiedDate);
    }

    public UserBuilder withId(String id) {
        this.id = id;
        return this;
    }

    public UserBuilder withUsername(String username) {
        this.username = username;
        return this;
    }

    public UserBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder withRole(String role) {
        this.role = role;
        return this;
    }

    public UserBuilder withVersion(Long version) {
        this.version = version;
        return this;

    }

    public UserBuilder withCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        return this;

    }

    public UserBuilder withCreatedDate(DateTime createdDate) {
        this.createdDate = createdDate;
        return this;

    }

    public UserBuilder withLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
        return this;

    }

    public UserBuilder withLastModifiedDate(DateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
        return this;

    }
}