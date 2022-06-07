package pl.distributed.library.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Document("author_assignment")
@NoArgsConstructor
public class AuthorAssignment {
    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    public AuthorAssignment(Author author, Book book) {
        this.author = author;
        this.book = book;
    }
}