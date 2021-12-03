package pl.distributed.library.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private int bookId;

    @NotNull
    @Column(name = "title")
    private String title;

    @NotNull
    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "release_year")
    private int releaseYear;

    @Column(name = "availability")
    private boolean availability;

    @OneToMany(mappedBy = "book")
    private Set<AuthorAssignment> authorAssignments;

    public Book() {
    }

    public Book(int bookId, @NotNull String title, @NotNull String description,
                @NotNull int releaseYear, boolean availability, Set<AuthorAssignment> authorAssignments) {
        this.bookId = bookId;
        this.title = title;
        this.description = description;
        this.releaseYear = releaseYear;
        this.availability = availability;
        this.authorAssignments = authorAssignments;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public Set<AuthorAssignment> getAuthorAssignments() {
        return authorAssignments;
    }

    public void setAuthorAssignments(Set<AuthorAssignment> authorAssignments) {
        this.authorAssignments = authorAssignments;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", releaseYear=" + releaseYear +
                ", availability=" + availability +
                ", authorAssignments=" + authorAssignments +
                '}';
    }
}
