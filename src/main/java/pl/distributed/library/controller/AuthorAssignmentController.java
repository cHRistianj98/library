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
import pl.distributed.library.dto.AuthorAssignmentDto;
import pl.distributed.library.dto.BookDto;
import pl.distributed.library.entity.AuthorAssignment;
import pl.distributed.library.exception.ResourceNotFoundException;
import pl.distributed.library.mapper.AuthorAssignmentMapper;
import pl.distributed.library.service.AuthorAssignmentService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/author-assignments")
public class AuthorAssignmentController {
    private AuthorAssignmentService authorAssignmentService;

    @Autowired
    public AuthorAssignmentController(AuthorAssignmentService authorAssignmentService) {
        this.authorAssignmentService = authorAssignmentService;
    }

    @ApiOperation("Get author assignment")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 200, message = "Successful deleted", response = AuthorAssignmentDto.class)
    })
    @GetMapping("/{id}")
    public ResponseEntity<AuthorAssignmentDto> getAuthorAssignment(@PathVariable Long id) {
        Optional<AuthorAssignment> authorAssignment = authorAssignmentService.findById(id);
        if (authorAssignment.isEmpty()) {
            throw new ResourceNotFoundException();
        } else {
            return ResponseEntity.ok(
                    AuthorAssignmentMapper.authorAssignmentToAuthorAssignmentDto(authorAssignment.get()));
        }
    }

    @GetMapping
    @ApiOperation("Get author assignments")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 200, message = "Successful deleted", response = AuthorAssignmentDto.class)
    })
    public ResponseEntity<List<AuthorAssignmentDto>> getAuthorAssignments() {
        return ResponseEntity.ok(authorAssignmentService.findAll());
    }
}
