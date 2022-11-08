package sandwich.model;

import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority {

    ADMIN, USER, TEACHER;

    @Override
    public String getAuthority() {
        return this.name();
    }

}
