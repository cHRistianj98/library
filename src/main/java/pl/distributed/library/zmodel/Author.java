package pl.distributed.library.zmodel;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Author {
    private Long authorId;

    private String forename;

    private String surname;

    public Author(Long authorId, String forename, String surname) {
        this.authorId = authorId;
        this.forename = forename;
        this.surname = surname;
    }
}