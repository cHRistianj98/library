package pl.distributed.library.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class AuthorUpdateDto {
    private Long id;
    private String forename;
    private String surname;
}
