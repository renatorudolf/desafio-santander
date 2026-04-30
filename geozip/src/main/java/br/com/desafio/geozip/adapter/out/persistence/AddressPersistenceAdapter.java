package br.com.desafio.geozip.adapter.out.persistence;

import br.com.desafio.geozip.application.port.out.AddressPersistencePort;
import br.com.desafio.geozip.domain.model.Address;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AddressPersistenceAdapter implements AddressPersistencePort {

    private final JpaAddressRepository repository;

    public AddressPersistenceAdapter(JpaAddressRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(Address address) {
        repository.save(mapToEntity(address));
    }

    @Override
    public Optional<Address> findByCep(String cep) {
        return repository.findById(cep).map(this::mapToDomain);
    }

    private AddressEntity mapToEntity(Address address) {
        return AddressEntity.builder()
                .cep(address.getCep())
                .logradouro(address.getLogradouro())
                .bairro(address.getBairro())
                .cidade(address.getCidade())
                .estado(address.getEstado())
                .build();
    }

    private Address mapToDomain(AddressEntity entity) {
        return Address.builder()
                .cep(entity.getCep())
                .logradouro(entity.getLogradouro())
                .bairro(entity.getBairro())
                .cidade(entity.getCidade())
                .estado(entity.getEstado())
                .build();
    }
}
