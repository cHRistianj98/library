package pl.distributed.library.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class AuthorDto {
    @NotNull
    private String id;

    @NotNull
    private String forename;

    @NotNull
    private String surname;
}
