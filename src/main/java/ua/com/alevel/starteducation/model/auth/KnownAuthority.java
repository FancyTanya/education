package ua.com.alevel.starteducation.model.auth;

import org.springframework.security.core.GrantedAuthority;

public enum KnownAuthority implements GrantedAuthority {

    ROLE_ADMIN,

    ROLE_USER,

    ROLE_TEACHER,

    ROLE_STUDENT;

    @Override
    public String getAuthority() {
        return name();
    }
}
