package pl.distributed.library.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.distributed.library.dto.AddressDto;
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
    private LibraryService libraryService;

    @Autowired
    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping
    @ApiOperation("Get libraries")
    public ResponseEntity<List<LibraryDto>> getLibraries() {
        return ResponseEntity.ok(libraryService.findAll());
    }

    @GetMapping("/{id}")
    @ApiOperation("Get library")
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
    public ResponseEntity<LibraryDto> addLibrary(@RequestBody @Valid AddressDto addressDto) {
        LibraryDto libraryDto = libraryService.addLibrary(addressDto);
        return ResponseEntity.created(URI.create("/" + libraryDto.getId())).body(libraryDto);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("delete library")
    public ResponseEntity<Long> deleteLibrary(@PathVariable Long id) {
        return ResponseEntity.ok(libraryService.deleteLibrary(id));
    }
}
