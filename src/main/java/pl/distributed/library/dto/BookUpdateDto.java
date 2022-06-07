package pl.distributed.library.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookUpdateDto {
    private Long id;
    private String title;
    private String description;
    private int releaseYear;
    private boolean availability;
}
