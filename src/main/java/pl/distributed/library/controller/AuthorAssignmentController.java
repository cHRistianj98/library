package pl.distributed.library.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.distributed.library.dto.*;
import pl.distributed.library.entity.AuthorAssignment;
import pl.distributed.library.exception.ResourceNotFoundException;
import pl.distributed.library.mapper.AuthorAssignmentMapper;
import pl.distributed.library.service.AuthorAssignmentService;

import javax.validation.Valid;
import java.net.URI;
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
    public ResponseEntity<AuthorAssignmentDto> getAuthorAssignment(@PathVariable String id) {
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

    @ApiOperation("Add author assignment")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 200, message = "Successful deleted", response = AddressDto.class)
    })
    @PostMapping
    public ResponseEntity<AuthorAssignmentDto> addAuthorAssignment(
            @RequestBody @Valid AuthorAssignmentCreateDto authorAssignmentCreateDto) {
        AuthorAssignmentDto authorAssignmentDto =
                authorAssignmentService.addAuthorAssignment(authorAssignmentCreateDto);
        return ResponseEntity.created(
                URI.create("/" + authorAssignmentDto.getAuthorAssignmentId())).body(authorAssignmentDto);
    }

    @ApiOperation("Delete author assignment")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 200, message = "Successful deleted", response = Long.class)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAuthorAssignment(@PathVariable String id) {
        return ResponseEntity.ok(authorAssignmentService.deleteAuthorAssignment(id));
    }
}
