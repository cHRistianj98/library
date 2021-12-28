package pl.distributed.library.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class AuthorAssignmentCreateDto {

    @NotNull
    private Long bookId;

    @NotNull
    private Long authorId;
}
