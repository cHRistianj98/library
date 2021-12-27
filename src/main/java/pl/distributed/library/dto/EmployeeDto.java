package pl.distributed.library.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@NoArgsConstructor
public class EmployeeDto {
    @NotNull
    private Long employeeId;

    @NotNull
    private int salary;

    @NotNull
    private String forename;

    @NotNull
    private String surname;

    @NotNull
    @Email
    private String email;

    @NotNull
    private LibraryDto library;

    @NotNull
    private Set<BorrowingDto> borrowings;
}
