package pl.distributed.library.entity;

import lombok.*;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("Book")
public class Book implements Serializable {

    private static final long serialVersionUID = 5862045935357146348L;

    @Id
    private Long id;

    @NonNull
    private String title;

    @NonNull
    private String description;

    @NonNull
    private int releaseYear;

    private boolean availability;

//    @OneToMany(fetch = FetchType.EAGER, mappedBy = "book", cascade = CascadeType.ALL)
//    private Set<AuthorAssignment> authorAssignments;
//
//    @OneToMany(fetch = FetchType.EAGER, mappedBy = "book", cascade = CascadeType.ALL)
//    private Set<Borrowing> borrowings;

}
