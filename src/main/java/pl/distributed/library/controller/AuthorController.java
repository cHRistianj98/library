package pl.distributed.library.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import pl.distributed.library.dto.AuthorDto;
import pl.distributed.library.entity.Author;
import pl.distributed.library.service.AuthorService;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    private AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping
    @ApiOperation("Add author")
    private Author saveAuthor(@RequestBody AuthorDto author) {
        return authorService.saveAuthor(author);
    }

    @DeleteMapping
    @ApiOperation("remove authors")
    private void deleteAuthor(Long authorId) {
        authorService.deleteAuthor(authorId);
    }
}
