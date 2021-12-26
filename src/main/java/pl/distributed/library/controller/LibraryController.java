package pl.distributed.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.distributed.library.dto.AddressDto;
import pl.distributed.library.dto.LibraryDto;
import pl.distributed.library.entity.Library;
import pl.distributed.library.service.LibraryService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/libraries")
public class LibraryController {
    private LibraryService libraryService;

    @Autowired
    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping
    public ResponseEntity<List<LibraryDto>> getLibraries() {
        return ResponseEntity.ok(libraryService.findAll());
    }

    @PostMapping
    public ResponseEntity<Library> addLibrary(@RequestBody @Valid AddressDto addressDto) {
        Library library = libraryService.addLibrary(addressDto);
        return ResponseEntity.created(URI.create("/" + library.getLibraryId())).body(library);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteLibrary(@PathVariable Long id) {
        return ResponseEntity.ok(libraryService.deleteLibrary(id));
    }
}
