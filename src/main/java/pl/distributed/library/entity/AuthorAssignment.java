package pl.distributed.library.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("author_assignment")
@NoArgsConstructor
public class AuthorAssignment {
    @Id
    private String id;
    private Author author;
    private Book book;
    public AuthorAssignment(Author author, Book book) {
        this.author = author;
        this.book = book;
    }
}
