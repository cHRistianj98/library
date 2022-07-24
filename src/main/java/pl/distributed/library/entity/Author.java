package pl.distributed.library.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

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

    private Set<AuthorAssignment> authorAssignments = new HashSet<>();
}
