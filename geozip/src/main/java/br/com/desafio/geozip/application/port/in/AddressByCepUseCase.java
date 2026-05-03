package br.com.desafio.geozip.application.port.in;

import br.com.desafio.geozip.domain.model.Address;

import java.util.Optional;

public interface AddressByCepUseCase {
    Optional<Address> execute(String cep);
}
