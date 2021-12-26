package pl.distributed.library.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.distributed.library.dto.ClientDto;
import pl.distributed.library.dto.NewClientDto;
import pl.distributed.library.service.ClientService;

import java.net.URI;

@RestController
@RequestMapping("/clients")
public class ClientController {
    private ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    @ApiOperation("Add client")
    public ResponseEntity<ClientDto> addClient(@RequestBody NewClientDto newClientDto) {
        ClientDto clientDto = clientService.addClient(newClientDto);
        return ResponseEntity.created(URI.create("/" + clientDto.getClientId())).body(clientDto);
    }
}
