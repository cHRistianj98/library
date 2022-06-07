package pl.distributed.library.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
public class BookDto {
    @NotNull
    private Long id;

    @NotNull
    private String title;

    @NotNull
    private String description;

    @NotNull
    private int releaseYear;

    private boolean availability;

    private List<AuthorUpdateDto> authors;

    //TODO: borrowings
    //private List<BorrowingDto> borrowings;
}
