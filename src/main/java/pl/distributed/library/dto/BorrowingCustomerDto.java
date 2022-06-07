package pl.distributed.library.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class BorrowingCustomerDto {
    @NotNull
    private String borrowingId;

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
    private String libraryId;

    @NotNull
    private BookBorrowingDto book;
}
