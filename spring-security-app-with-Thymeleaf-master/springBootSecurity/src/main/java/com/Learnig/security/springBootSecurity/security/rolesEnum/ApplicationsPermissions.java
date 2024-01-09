package com.Learnig.security.springBootSecurity.security.rolesEnum;

public enum ApplicationsPermissions {
    USER_READ("user:read"),
    USER_WRITE("user:write"),
    ADMIN_READ("admin:read"),
    ADMIN_WRITE("admin:write");

    private final String permission;

    ApplicationsPermissions(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
