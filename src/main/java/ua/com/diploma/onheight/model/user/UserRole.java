package ua.com.diploma.onheight.model.user;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum UserRole {


    USER("USER", Set.of(Permission.USER_READ, Permission.USER_WRITE)),
    ADMIN("ADMIN", Set.of(Permission.ADMIN_READ, Permission.ADMIN_WRITE, Permission.USER_READ, Permission.USER_WRITE));

    private final String key;
    private final Set<Permission> permissions;

    UserRole(String key, Set<Permission> permissions) {
        this.key = key;
        this.permissions = permissions;
    }

    public static UserRole get(String key) {
        for (UserRole r : values()) {
            if (r.getKey().equals(key)) {
                return r;
            }
        }
        return null;
    }

    public String getKey() {
        return key;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getAuthorities() {
        return getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }
}

