package pl.distributed.library.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

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

}
