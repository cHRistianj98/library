package pl.distributed.library.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.distributed.library.entity.AuthorAssignment;
import pl.distributed.library.entity.Borrowing;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@NoArgsConstructor
public class BookDto {
    @NotNull
    private Long bookId;

    @NotNull
    private String title;

    @NotNull
    private String description;

    @NotNull
    private int releaseYear;

    private boolean availability;

    private Set<AuthorAssignmentDto> authorAssignments;

    private Set<BorrowingDto> borrowings;
}
