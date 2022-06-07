package pl.distributed.library.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class BorrowingBookDto {
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
    private Long libraryId;

    @NotNull
    private CustomerBorrowingDto customer;
}
