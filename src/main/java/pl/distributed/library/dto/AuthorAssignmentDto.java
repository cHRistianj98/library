package pl.distributed.library.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class AuthorAssignmentDto {
    @NotNull
    private String id;

    @NotNull
    private AuthorDto author;

    @NotNull
    private BookDto book;
}
