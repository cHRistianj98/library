package pl.distributed.library.zcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.distributed.library.zdao.AuthorDAO;
import pl.distributed.library.zmodel.Author;

import java.util.List;
import java.util.Objects;

//@RestController
public class ZAuthorController {

    private AuthorDAO authorDAO;

    @Autowired
    public ZAuthorController(AuthorDAO authorDAO) {
        this.authorDAO = authorDAO;
    }

    @GetMapping("/authors/v2")
    List<Author> getAllAuthors() {
        return authorDAO.getAllAuthors();
    }

    @GetMapping("/authors/v2/{id}")
    Author findAuthor(@PathVariable Long id) throws Exception {
        Author author = authorDAO.findByAuthorId(id);
        if (Objects.isNull(author)) {
            throw new Exception("No author found for id: " + id);
        }
        return author;
    }

    @PostMapping("/authors/v2")
    Author addAuthor(@RequestBody Author newAuthor) {
        return authorDAO.addAuthor(newAuthor);
    }
}
