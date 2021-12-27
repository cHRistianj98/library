package pl.distributed.library.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.distributed.library.entity.Book;
import pl.distributed.library.entity.Client;
import pl.distributed.library.entity.Employee;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class BorrowingDto {
    @NotNull
    private Long borrowingId;

    @NotNull
    @JsonFormat
    private LocalDate validFrom;

    @NotNull
    @JsonFormat
    private LocalDate validTo;

    @NotNull
    @JsonFormat
    private LocalDate returnDate;

    @NotNull
    private BookDto book;

    @NotNull
    private ClientDto client;

    @NotNull
    private EmployeeDto employee;
}
