package com.pwa.common.constant;

public enum UserRoleCode {

    ROLE_ADMIN("Admin");

    private String name;

    UserRoleCode(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}