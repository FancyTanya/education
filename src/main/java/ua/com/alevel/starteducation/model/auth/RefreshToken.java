package ua.com.alevel.starteducation.model.auth;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Setter
@Getter
@NoArgsConstructor

@Entity
@Table(name = "refresh_tokens")
public class RefreshToken {

    @Id
    @GeneratedValue
    private UUID value;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private EducationUser user;

    @Column(name = "issued_at", nullable = false)
    private OffsetDateTime issuedAt;

    @Column(name = "expire_at", nullable = false)
    private OffsetDateTime expireAt;

    @SuppressWarnings("FieldMayBeFinal")
    @OneToMany(mappedBy = "next", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RefreshToken> previous = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "next")
    @Access(AccessType.PROPERTY)
    private RefreshToken next;

}
