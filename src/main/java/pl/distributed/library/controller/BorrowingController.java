package pl.distributed.library.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.distributed.library.dto.*;
import pl.distributed.library.entity.Borrowing;
import pl.distributed.library.exception.ResourceNotFoundException;
import pl.distributed.library.mapper.BorrowingMapper;
import pl.distributed.library.service.BorrowingService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/borrowings")
public class BorrowingController {
    private BorrowingService borrowingService;

    @Autowired
    public BorrowingController(BorrowingService borrowingService) {
        this.borrowingService = borrowingService;
    }

    @ApiOperation("Get borrowing")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 200, message = "Successfully returned", response = BorrowingDto.class)
    })
    @GetMapping("/{id}")
    public ResponseEntity<BorrowingDto> getBorrowing(@PathVariable Long id) {
            return ResponseEntity.ok(borrowingService.findById(id));
    }

    @GetMapping
    @ApiOperation("Get borrowings")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 200, message = "Successfully returned", response = BorrowingDto.class)
    })
    public ResponseEntity<List<BorrowingDto>> getBorrowings() {
        return ResponseEntity.ok(borrowingService.findAll());
    }

    @ApiOperation("Add borrowing")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 200, message = "Successful deleted", response = BorrowingDto.class)
    })
    @PostMapping
    public ResponseEntity<BorrowingDto> addBorrowing(@RequestBody @Valid BorrowingCreateDto borrowingCreateDto) {
        BorrowingDto borrowingDto = borrowingService.addBorrowing(borrowingCreateDto);
        return ResponseEntity.created(URI.create("/" + borrowingDto.getBorrowingId())).body(borrowingDto);
    }

    @ApiOperation("Update borrowing")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 200, message = "Successful updated", response = BorrowingDto.class)
    })
    @PutMapping
    public ResponseEntity<BorrowingDto> updateBorrowing(@RequestBody @Valid BorrowingUpdateDto borrowingUpdateDto) {
        return ResponseEntity.ok(borrowingService.updateBorrowing(borrowingUpdateDto));
    }

    @ApiOperation("Delete borrowing")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 200, message = "Successful deleted", response = Long.class)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteBorrowing(@PathVariable Long id) {
        return ResponseEntity.ok(borrowingService.deleteBorrowing(id));
    }
}
