package pl.distributed.library.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
public class AuthorDto {
    @NotNull
    private Long authorId;

    @NotNull
    private String forename;

    @NotNull
    private String surname;

    private List<BookDto> books;
}
