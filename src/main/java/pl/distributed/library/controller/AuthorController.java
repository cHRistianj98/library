package pl.distributed.library.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.distributed.library.dto.AuthorDto;
import pl.distributed.library.entity.Author;
import pl.distributed.library.service.AuthorService;

@RestController
public class AuthorController {
    private AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping("/authors")
    private Author saveAuthor(@RequestBody AuthorDto author) {
        return authorService.saveAuthor(author);
    }

    @DeleteMapping("/authors")
    private void deleteAuthor(Long authorId) {
        authorService.deleteAuthor(authorId);
    }
}
