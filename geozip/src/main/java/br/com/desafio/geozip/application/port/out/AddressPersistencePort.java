package br.com.desafio.geozip.application.port.out;

import br.com.desafio.geozip.domain.model.Address;

import java.util.Optional;

public interface AddressPersistencePort {
    void save(Address address);
    Optional<Address> findByCep(String cep);
}
