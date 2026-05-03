package br.com.desafio.geozip.adapter.in.rest;

import br.com.desafio.geozip.application.port.in.AddressByCepUseCase;
import br.com.desafio.geozip.adapter.AddressMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cep")
public class AddressController {

    private final AddressByCepUseCase addressByCepUseCase;
    private final AddressMapper addressMapper;

    public AddressController(AddressByCepUseCase addressByCepUseCase, AddressMapper addressMapper) {
        this.addressByCepUseCase = addressByCepUseCase;
        this.addressMapper = addressMapper;
    }

    @GetMapping
    public ResponseEntity<AddressResponseDTO> getAddress(@RequestParam(required = false) String cep) {
        return addressByCepUseCase.execute(cep)
                .map(addressMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
