package ua.com.alevel.starteducation.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.starteducation.Routes;
import ua.com.alevel.starteducation.model.auth.EducationUser;
import ua.com.alevel.starteducation.model.auth.KnownAuthority;

import java.time.OffsetDateTime;
import java.util.EnumSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
public class UserDtoResponse {

    private Long id;
    private String email;
    private OffsetDateTime createdAt;
    private Set<KnownAuthority> authorities;
    private EducationUser user;

    public UserDtoResponse(Long id, String email, OffsetDateTime createdAt, Set<KnownAuthority> authorities) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.createdAt = user.getCreatedAt();
        var knownAuthorities = EnumSet.copyOf(user.getAuthorities().keySet());
    }

    public static UserDtoResponse fromUserWithBasicAttributes(EducationUser user) {
        return new UserDtoResponse(
                user.getId(),
                user.getEmail(),
                user.getCreatedAt(),
                null);
    }

    public static UserDtoResponse fromUser(EducationUser user) {
        return new UserDtoResponse(
                user.getId(),
                user.getEmail(),
                user.getCreatedAt(),
                EnumSet.copyOf(user.getAuthorities().keySet()));
    }

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public String path() {
        return Routes.user(id);
    }


}
