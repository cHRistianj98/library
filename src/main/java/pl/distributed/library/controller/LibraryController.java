package pl.distributed.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.distributed.library.dto.AddressDto;
import pl.distributed.library.entity.Library;
import pl.distributed.library.service.LibraryService;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/libraries")
public class LibraryController {
    private LibraryService libraryService;

    @Autowired
    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @PostMapping
    public ResponseEntity<Library> addLibrary(@RequestBody @Valid AddressDto addressDto) {
        Library library = libraryService.addLibrary(addressDto);
       return ResponseEntity.created(URI.create("/" + library.getLibraryId())).body(library);
    }
}
