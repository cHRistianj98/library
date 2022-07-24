package pl.distributed.library.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Reference;
import org.springframework.data.redis.core.RedisHash;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("AuthorAssignment")
public class AuthorAssignment {

    @Id
    @NonNull
    private Long id;

    @Reference
    private Author author;

    @Reference
    private Book book;

    public AuthorAssignment(Author author, Book book) {
        this.author = author;
        this.book = book;
    }
}
