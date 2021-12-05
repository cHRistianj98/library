package pl.distributed.library.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "author_assignment")
public class AuthorAssignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_assignment_id")
    private Long authorAssignmentId;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    public AuthorAssignment() {
    }

    public AuthorAssignment(Long authorAssignmentId, Author author, Book book) {
        this.authorAssignmentId = authorAssignmentId;
        this.author = author;
        this.book = book;
    }

    public Long getAuthorAssignmentId() {
        return authorAssignmentId;
    }

    public void setAuthorAssignmentId(Long authorAssignmentId) {
        this.authorAssignmentId = authorAssignmentId;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "AuthorAssignment{" +
                "authorAssignmentId=" + authorAssignmentId +
                ", author=" + author +
                ", book=" + book +
                '}';
    }
}
