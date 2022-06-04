package pl.distributed.library.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@Document("author")
@NoArgsConstructor
public class Author {
    @Id
    private String id;
    @NotNull
    private String forename;
    @NotNull
    private String surname;
    private Set<AuthorAssignment> authorAssignments;

    public Author(@NotNull String forename, @NotNull String surname, Set<AuthorAssignment> authorAssignments) {
        this.forename = forename;
        this.surname = surname;
        this.authorAssignments = authorAssignments;
    }
}
