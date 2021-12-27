package pl.distributed.library.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;
import pl.distributed.library.dto.AuthorDto;
import pl.distributed.library.entity.Author;
import pl.distributed.library.service.AuthorService;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @ApiOperation("Add author")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 200, message = "Successful deleted", response = Author.class)
    })
    @PostMapping
    private Author saveAuthor(@RequestBody AuthorDto author) {
        return authorService.saveAuthor(author);
    }

    @ApiOperation("Remove authors")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 200, message = "Successful deleted")
    })
    @DeleteMapping
    private void deleteAuthor(Long authorId) {
        authorService.deleteAuthor(authorId);
    }
}
