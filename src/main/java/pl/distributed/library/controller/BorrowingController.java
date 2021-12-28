package pl.distributed.library.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.distributed.library.dto.BorrowingDto;
import pl.distributed.library.dto.EmployeeDto;
import pl.distributed.library.entity.Borrowing;
import pl.distributed.library.exception.ResourceNotFoundException;
import pl.distributed.library.mapper.BorrowingMapper;
import pl.distributed.library.service.BorrowingService;

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
            @ApiResponse(code = 200, message = "Successful deleted", response = EmployeeDto.class)
    })
    @GetMapping("/{id}")
    public ResponseEntity<BorrowingDto> getBorrowing(@PathVariable Long id) {
        Optional<Borrowing> borrowing = borrowingService.findById(id);
        if (borrowing.isEmpty()) {
            throw new ResourceNotFoundException();
        } else {
            return ResponseEntity.ok(BorrowingMapper.borrowingToBorrowingDto(borrowing.get()));
        }
    }

    @GetMapping
    @ApiOperation("Get borrowings")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 200, message = "Successful deleted", response = EmployeeDto.class)
    })
    public ResponseEntity<List<BorrowingDto>> getBorrowings() {
        return ResponseEntity.ok(borrowingService.findAll());
    }
}
