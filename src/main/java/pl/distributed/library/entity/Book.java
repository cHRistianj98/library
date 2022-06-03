package pl.distributed.library.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "title", nullable = false)
    private String title;

    @NotNull
    @Column(name = "description", nullable = false)
    private String description;

    @NotNull
    @Column(name = "release_year", nullable = false)
    private int releaseYear;

    @Column(name = "availability")
    private boolean availability;

    @OneToMany(mappedBy = "book")
    private Set<AuthorAssignment> authorAssignments;

    @OneToMany(mappedBy = "book")
    private Set<Borrowing> borrowings;

    public Book(@NotNull String title, @NotNull String description, @NotNull int releaseYear, boolean availability,
                Set<AuthorAssignment> authorAssignments, Set<Borrowing> borrowings) {
        this.title = title;
        this.description = description;
        this.releaseYear = releaseYear;
        this.availability = availability;
        this.authorAssignments = authorAssignments;
        this.borrowings = borrowings;
    }
}
