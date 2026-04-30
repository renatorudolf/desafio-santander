package br.com.desafio.geozip.application.usecase;

import br.com.desafio.geozip.application.port.in.GetAddressByCepUseCase;
import br.com.desafio.geozip.application.port.out.AddressExternalServicePort;
import br.com.desafio.geozip.application.port.out.AddressPersistencePort;
import br.com.desafio.geozip.application.port.out.SearchLogPersistencePort;
import br.com.desafio.geozip.domain.model.Address;
import br.com.desafio.geozip.domain.model.SearchLog;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class GetAddressByCepUseCaseImpl implements GetAddressByCepUseCase {

    private final AddressExternalServicePort addressExternalServicePort;
    private final AddressPersistencePort addressPersistencePort;
    private final SearchLogPersistencePort searchLogPersistencePort;

    public GetAddressByCepUseCaseImpl(
            AddressExternalServicePort addressExternalServicePort,
            AddressPersistencePort addressPersistencePort,
            SearchLogPersistencePort searchLogPersistencePort) {
        this.addressExternalServicePort = addressExternalServicePort;
        this.addressPersistencePort = addressPersistencePort;
        this.searchLogPersistencePort = searchLogPersistencePort;
    }

    @Override
    public Optional<Address> execute(String cep) {
        if (cep == null || !cep.matches("[0-9]{8}")) {
            return Optional.empty();
        }

        Optional<Address> cachedAddress = addressPersistencePort.findByCep(cep);
        if (cachedAddress.isPresent()) {
            logSearch(cep, cachedAddress.get());
            return cachedAddress;
        }

        return addressExternalServicePort.fetchAddress(cep)
                .map(address -> {
                    addressPersistencePort.save(address);
                    logSearch(cep, address);
                    return address;
                });
    }

    private void logSearch(String cep, Address address) {
        String data = String.format("Logradouro: %s, Bairro: %s, Cidade: %s, Estado: %s",
                address.getLogradouro(), address.getBairro(), address.getCidade(), address.getEstado());
        searchLogPersistencePort.save(new SearchLog(cep, data, LocalDateTime.now()));
    }
}
