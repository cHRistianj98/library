package pl.distributed.library.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerUpdateDto {
    private String id;
    private String forename;
    private String surname;
    private String email;
}
