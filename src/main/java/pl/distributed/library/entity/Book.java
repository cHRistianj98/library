package pl.distributed.library.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@Document("book")
@NoArgsConstructor
public class Book {
    @Id
    private String id;
    @NotNull
    private String title;
    @NotNull
    private String description;
    @NotNull
    private int releaseYear;
    private boolean availability;
    private Set<AuthorAssignment> authorAssignments;
    private Set<Borrowing> borrowings;

    public Book(@NotNull String title, @NotNull String description, @NotNull int releaseYear, boolean availability,
                Set<AuthorAssignment> authorAssignments, Set<Borrowing> borrowings) {
        this.title = title;
        this.description = description;
        this.releaseYear = releaseYear;
        this.availability = availability;
        this.authorAssignments = authorAssignments;
        this.borrowings = borrowings;
    }
}
