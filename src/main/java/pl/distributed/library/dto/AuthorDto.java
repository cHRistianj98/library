package pl.distributed.library.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.distributed.library.entity.AuthorAssignment;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@NoArgsConstructor
public class AuthorDto {
    @NotNull
    private Long authorId;

    @NotNull
    private String forename;

    @NotNull
    private String surname;
}
