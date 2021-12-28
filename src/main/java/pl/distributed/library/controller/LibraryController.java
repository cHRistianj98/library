package pl.distributed.library.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.distributed.library.dto.AddressCreateDto;
import pl.distributed.library.dto.AddressDto;
import pl.distributed.library.dto.LibraryCreateDto;
import pl.distributed.library.dto.LibraryDto;
import pl.distributed.library.entity.Library;
import pl.distributed.library.exception.ResourceNotFoundException;
import pl.distributed.library.mapper.LibraryMapper;
import pl.distributed.library.service.LibraryService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/libraries")
public class LibraryController {
    private final LibraryService libraryService;

    @Autowired
    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping
    @ApiOperation("Get libraries")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 200, message = "Successful deleted", response = LibraryDto.class)
    })
    public ResponseEntity<List<LibraryDto>> getLibraries() {
        return ResponseEntity.ok(libraryService.findAll());
    }

    @GetMapping("/{id}")
    @ApiOperation("Get library")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 200, message = "Successful deleted", response = LibraryDto.class)
    })
    public ResponseEntity<LibraryDto> getLibrary(@PathVariable Long id) {
        Optional<Library> library = libraryService.findById(id);
        if (library.isEmpty()) {
            throw new ResourceNotFoundException();
        } else {
            return ResponseEntity.ok(LibraryMapper.libraryToLibraryDto(library.get()));
        }
    }

    @PostMapping
    @ApiOperation("Add library")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 200, message = "Successful deleted", response = LibraryDto.class)
    })
    public ResponseEntity<LibraryCreateDto> addLibrary(@RequestBody @Valid AddressCreateDto addressCreateDto) {
        LibraryCreateDto libraryCreateDto = libraryService.addLibrary(addressCreateDto);
        return ResponseEntity.ok(libraryCreateDto);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("delete library")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 200, message = "Successful deleted", response = Long.class)
    })
    public ResponseEntity<Long> deleteLibrary(@PathVariable Long id) {
        return ResponseEntity.ok(libraryService.deleteLibrary(id));
    }
}
