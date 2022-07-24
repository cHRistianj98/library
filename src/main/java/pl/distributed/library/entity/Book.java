package pl.distributed.library.entity;

import lombok.*;
import org.springframework.data.annotation.Reference;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

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

    private Set<AuthorAssignment> authorAssignments = new HashSet<>();
//
//    @OneToMany(fetch = FetchType.EAGER, mappedBy = "book", cascade = CascadeType.ALL)
//    private Set<Borrowing> borrowings;

}
