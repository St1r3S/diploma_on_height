package ua.com.diploma.onheight.model.user;

public enum Permission {

    GUEST_READ("guest:read"),
    GUEST_WRITE("guest:write"),
    USER_READ("user:read"),
    USER_WRITE("user:write"),
    ADMIN_READ("admin:read"),
    ADMIN_WRITE("admin:write");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
