package pl.distributed.library.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class BorrowingUpdateDto {
    private Long id;
    private LocalDate validTo;
    private LocalDate returnDate;
}
