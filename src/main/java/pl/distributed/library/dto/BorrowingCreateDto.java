package pl.distributed.library.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class BorrowingCreateDto {
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
    private Long bookId;

    @NotNull
    private Long id;

    @NotNull
    private Long employeeId;
}
