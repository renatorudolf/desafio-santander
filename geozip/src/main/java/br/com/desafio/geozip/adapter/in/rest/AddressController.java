package br.com.desafio.geozip.adapter.in.rest;

import br.com.desafio.geozip.application.port.in.GetAddressByCepUseCase;
import br.com.desafio.geozip.domain.model.Address;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cep")
public class AddressController {

    private final GetAddressByCepUseCase getAddressByCepUseCase;

    public AddressController(GetAddressByCepUseCase getAddressByCepUseCase) {
        this.getAddressByCepUseCase = getAddressByCepUseCase;
    }

    @GetMapping
    public ResponseEntity<AddressResponseDTO> getAddress(CepRequestDTO request) {
        try {
            if (request == null || request.getCep() == null || request.getCep().isBlank()) {
                return ResponseEntity.badRequest().build();
            }

            return getAddressByCepUseCase.execute(request.getCep())
                    .map(this::mapToDTO)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    private AddressResponseDTO mapToDTO(Address address) {
        return AddressResponseDTO.builder()
                .cep(address.getCep())
                .logradouro(address.getLogradouro())
                .bairro(address.getBairro())
                .cidade(address.getCidade())
                .estado(address.getEstado())
                .build();
    }
}
