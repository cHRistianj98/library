package pl.distributed.library.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.distributed.library.dto.*;
import pl.distributed.library.entity.Book;
import pl.distributed.library.entity.Borrowing;
import pl.distributed.library.exception.ResourceNotFoundException;
import pl.distributed.library.mapper.BookMapper;
import pl.distributed.library.mapper.BorrowingMapper;
import pl.distributed.library.service.BookService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {
    private BookService bookService;

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
    public ResponseEntity<BookDto> getBook(@PathVariable Long id) {
        Optional<Book> book = bookService.findById(id);
        if (book.isEmpty()) {
            throw new ResourceNotFoundException();
        } else {
            return ResponseEntity.ok(BookMapper.bookToBookDto(book.get()));
        }
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
        return ResponseEntity.created(URI.create("/" + bookDto.getBookId())).body(bookDto);
    }
}
