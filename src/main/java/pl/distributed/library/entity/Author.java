package pl.distributed.library.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id")
    private Long authorId;

    @NotNull
    @Column(name = "forename", nullable = false)
    private String forename;

    @NotNull
    @Column(name = "surname", nullable = false)
    private String surname;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<AuthorAssignment> authorAssignments;

    public Author() {
    }

    public Author(@NotNull String forename, @NotNull String surname, Set<AuthorAssignment> authorAssignments) {
        this.forename = forename;
        this.surname = surname;
        this.authorAssignments = authorAssignments;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Set<AuthorAssignment> getAuthorAssignments() {
        return authorAssignments;
    }

    public void setAuthorAssignments(Set<AuthorAssignment> authorAssignments) {
        this.authorAssignments = authorAssignments;
    }

    @Override
    public String toString() {
        return "Author{" +
                "authorId=" + authorId +
                ", forename='" + forename + '\'' +
                ", surname='" + surname + '\'' +
                ", authorAssignments=" + authorAssignments +
                '}';
    }
}
