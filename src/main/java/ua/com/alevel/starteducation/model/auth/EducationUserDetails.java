package ua.com.alevel.starteducation.model.auth;

import org.springframework.security.core.userdetails.User;

import java.util.EnumSet;

public class EducationUserDetails extends User {

    private final EducationUser src;

    public EducationUserDetails(EducationUser src) {
        super(src.getEmail(),
                src.getPassword(),
                true,
                true,
                true,
                true,
                EnumSet.copyOf(src.getAuthorities().keySet()));
        this.src = src;
    }

    public EducationUser getSource() {
        return src;
    }
}
