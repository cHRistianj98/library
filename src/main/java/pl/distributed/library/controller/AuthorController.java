package pl.distributed.library.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.distributed.library.dto.*;
import pl.distributed.library.entity.Author;
import pl.distributed.library.exception.ResourceNotFoundException;
import pl.distributed.library.mapper.AuthorMapper;
import pl.distributed.library.service.AuthorService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @ApiOperation("Get address")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 200, message = "Successful returned", response = AuthorDto.class)
    })
    @GetMapping("/{id}")
    public ResponseEntity<AuthorDto> getAuthor(@PathVariable Long id) {
            return ResponseEntity.ok(authorService.findById(id));
    }

    @GetMapping
    @ApiOperation("Get authors")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 200, message = "Successful returned", response = AuthorDto.class)
    })
    public ResponseEntity<List<AuthorDto>> getAuthors() {
        return ResponseEntity.ok(authorService.findAll());
    }

    @ApiOperation("Add author")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 200, message = "Successful added", response = Author.class)
    })
    @PostMapping
    private AuthorDto saveAuthor(@RequestBody @Valid AuthorCreateDto author) {
        return authorService.saveAuthor(author);
    }

    @ApiOperation("Update author")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 200, message = "Successful updated", response = AuthorDto.class)
    })
    @PutMapping
    public ResponseEntity<AuthorDto> updateAuthor(@RequestBody @Valid AuthorUpdateDto addressUpdateDto) {
        return ResponseEntity.ok(authorService.updateAuthor(addressUpdateDto));
    }

    @ApiOperation("Delete authors")
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
