package com.pwa.model;

import com.pwa.common.model.BaseEntity;
import org.joda.time.DateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "USERS")
public class User extends BaseEntity implements Serializable {

    @Id
    @Column(name = "USER_ID", length = 70, unique = true, nullable = false)
    private String id;

    @Column(name = "USERNAME", length = 70, unique = true, nullable = false)
    private String username;

    @Column(name = "PASSWORD", length = 70, nullable = false)
    private String password;

    @Column(name = "ROLE", length = 70, nullable = false)
    private String role;

    public User() {

    }

    public User(String id, String username, String password, String role, Long version, String createdBy, DateTime createdDate, String lastModifiedBy,
        DateTime lastModifiedDate) {
        super(version, createdBy, createdDate, lastModifiedBy, lastModifiedDate);
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
