package pl.distributed.library.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("Author")
public class Author implements Serializable {
    private static final long serialVersionUID = -3537984978633334466L;

    @Id
    @NonNull
    private Long id;

    @NonNull
    private String forename;

    @NonNull
    private String surname;

//    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    private Set<AuthorAssignment> authorAssignments;

//    public Author(@NotNull String forename, @NotNull String surname, Set<AuthorAssignment> authorAssignments) {
//        this.forename = forename;
//        this.surname = surname;
//        this.authorAssignments = authorAssignments;
//    }
}
