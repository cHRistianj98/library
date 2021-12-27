package pl.distributed.library.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.distributed.library.entity.Employee;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@NoArgsConstructor
public class LibraryDto {
    @NotNull
    private Long id;

    @NotNull
    private AddressDto addressDto;

    private Set<EmployeeDto> employees;
}
