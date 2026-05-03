package br.com.desafio.geozip.adapter.out.persistence;

import br.com.desafio.geozip.application.port.out.AddressPersistencePort;
import br.com.desafio.geozip.domain.model.Address;
import br.com.desafio.geozip.adapter.AddressMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AddressPersistenceAdapter implements AddressPersistencePort {

    private final JpaAddressRepository repository;
    private final AddressMapper addressMapper;

    public AddressPersistenceAdapter(JpaAddressRepository repository, AddressMapper addressMapper) {
        this.repository = repository;
        this.addressMapper = addressMapper;
    }

    @Override
    public void save(Address address) {
        repository.save(addressMapper.toEntity(address));
    }

    @Override
    public Optional<Address> findByCep(String cep) {
        return repository.findById(cep).map(addressMapper::toDomain);
    }
}
