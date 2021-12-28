package pl.distributed.library.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
public class BookCreateDto {
    @NotNull
    private String title;

    @NotNull
    private String description;

    @NotNull
    private int releaseYear;

    @NotNull
    private List<AuthorCreateDto> authors;
}
