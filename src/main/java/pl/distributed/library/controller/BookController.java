package pl.distributed.library.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.distributed.library.dto.*;
import pl.distributed.library.entity.Book;
import pl.distributed.library.exception.ResourceNotFoundException;
import pl.distributed.library.mapper.BookMapper;
import pl.distributed.library.service.BookService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @ApiOperation("Get book")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 200, message = "Successful deleted", response = BookDto.class)
    })
    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBook(@PathVariable String id) {
            return ResponseEntity.ok(bookService.findById(id));
    }

    @GetMapping
    @ApiOperation("Get books")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 200, message = "Successful deleted", response = BookDto.class)
    })
    public ResponseEntity<List<BookDto>> getBooks() {
        return ResponseEntity.ok(bookService.findAll());
    }

    @GetMapping("/customers/{id}")
    @ApiOperation("Get books borrowed by customer")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 200, message = "Successful deleted", response = BookDto.class)
    })
    public ResponseEntity<List<BookDto>> getBooksByCustomerId(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.findBooksByCustomerId(id));
    }

    @ApiOperation("Add book")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 200, message = "Successful deleted", response = BookDto.class)
    })
    @PostMapping
    public ResponseEntity<BookDto> addBook(@RequestBody @Valid BookCreateDto bookCreateDto) {
        BookDto bookDto = bookService.addBook(bookCreateDto);
        return ResponseEntity.created(URI.create("/" + bookDto.getId())).body(bookDto);
    }

    @ApiOperation("Update book")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 200, message = "Successful updated", response = BookDto.class)
    })
    @PutMapping
    public ResponseEntity<BookDto> updateBook(@RequestBody @Valid BookUpdateDto bookUpdateDto) {
        return ResponseEntity.ok(bookService.updateBook(bookUpdateDto));
    }

    // TODO fix deleting
    @ApiOperation("Delete book")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 200, message = "Successful deleted", response = Long.class)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable String id) {
        return ResponseEntity.ok(bookService.deleteBook(id));
    }
}
