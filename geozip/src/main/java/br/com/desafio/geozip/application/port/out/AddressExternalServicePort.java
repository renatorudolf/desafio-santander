package br.com.desafio.geozip.application.port.out;

import br.com.desafio.geozip.domain.model.Address;

import java.util.Optional;

public interface AddressExternalServicePort {
    Optional<Address> fetchAddress(String cep);
}
