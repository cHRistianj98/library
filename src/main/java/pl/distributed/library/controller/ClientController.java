package pl.distributed.library.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.distributed.library.dto.ClientDto;
import pl.distributed.library.dto.NewClientDto;
import pl.distributed.library.entity.Client;
import pl.distributed.library.exception.ResourceNotFoundException;
import pl.distributed.library.mapper.ClientMapper;
import pl.distributed.library.service.ClientService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clients")
public class ClientController {
    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @ApiOperation("Get client")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 200, message = "Successful deleted", response = ClientDto.class)
    })
    @GetMapping("/{id}")
    public ResponseEntity<ClientDto> getLibrary(@PathVariable Long id) {
        Optional<Client> client = clientService.findById(id);
        if (client.isEmpty()) {
            throw new ResourceNotFoundException();
        } else {
            return ResponseEntity.ok(ClientMapper.clientToClientDto(client.get()));
        }
    }

    @ApiOperation("Get clients")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 200, message = "Successful deleted", response = ClientDto.class)
    })
    @GetMapping
    public ResponseEntity<List<ClientDto>> getLibraries() {
        return ResponseEntity.ok(clientService.findAll());
    }

    @ApiOperation("Add client")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 200, message = "Successful deleted", response = ClientDto.class)
    })
    @PostMapping
    public ResponseEntity<ClientDto> addClient(@RequestBody NewClientDto newClientDto) {
        ClientDto clientDto = clientService.addClient(newClientDto);
        return ResponseEntity.created(URI.create("/" + clientDto.getClientId())).body(clientDto);
    }

    @ApiOperation("delete client")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 200, message = "Successful deleted", response = Long.class)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteClient(@PathVariable Long id) {
        return ResponseEntity.ok(clientService.deleteClient(id));
    }
}
