package br.com.desafio.geozip.application.usecase;

import br.com.desafio.geozip.application.port.in.AddressByCepUseCase;
import br.com.desafio.geozip.application.port.out.AddressExternalServicePort;
import br.com.desafio.geozip.application.port.out.SearchLogPersistencePort;
import br.com.desafio.geozip.domain.exception.CepNotFoundException;
import br.com.desafio.geozip.domain.exception.InvalidCepException;
import br.com.desafio.geozip.domain.model.Address;
import br.com.desafio.geozip.domain.model.SearchLog;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class GetAddressByCepUseCaseImpl implements AddressByCepUseCase {

    private final AddressExternalServicePort addressExternalServicePort;
    private final SearchLogPersistencePort searchLogPersistencePort;

    public GetAddressByCepUseCaseImpl(
            AddressExternalServicePort addressExternalServicePort,
            SearchLogPersistencePort searchLogPersistencePort) {
        this.addressExternalServicePort = addressExternalServicePort;
        this.searchLogPersistencePort = searchLogPersistencePort;
    }

    @Override
    public Optional<Address> execute(String cep) {
        if (cep == null || !cep.matches("[0-9]{8}")) {
            throw new InvalidCepException("faltam dados");
        }

        return addressExternalServicePort.fetchAddress(cep)
                .map(address -> {
                    logSearch(cep, address);
                    return address;
                })
                .or(() -> {
                    throw new CepNotFoundException("CEP não encontrado");
                });
    }

    private void logSearch(String cep, Address address) {
        String data = String.format("Logradouro: %s, Bairro: %s, Cidade: %s, Estado: %s",
                address.getLogradouro(), address.getBairro(), address.getCidade(), address.getEstado());
        searchLogPersistencePort.save(SearchLog.builder()
                .cep(cep)
                .data(data)
                .searchDate(LocalDateTime.now())
                .build());
    }
}
