package pl.distributed.library.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@NoArgsConstructor
public class ClientDto {

    @NotNull
    private Long clientId;

    @NotNull
    private String forename;

    @NotNull
    private String surname;

    @NotNull
    @Email
    private String email;

    private int debt;
}
