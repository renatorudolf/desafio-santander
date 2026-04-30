package br.com.desafio.geozip.application.usecase;

import br.com.desafio.geozip.application.port.out.AddressExternalServicePort;
import br.com.desafio.geozip.application.port.out.AddressPersistencePort;
import br.com.desafio.geozip.application.port.out.SearchLogPersistencePort;
import br.com.desafio.geozip.domain.model.Address;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetAddressByCepUseCaseImplTest {

    @Mock
    private AddressExternalServicePort addressExternalServicePort;

    @Mock
    private AddressPersistencePort addressPersistencePort;

    @Mock
    private SearchLogPersistencePort searchLogPersistencePort;

    @InjectMocks
    private GetAddressByCepUseCaseImpl useCase;

    private Address sampleAddress;

    @BeforeEach
    void setUp() {
        sampleAddress = new Address("01001000", "Praça da Sé", "Sé", "São Paulo", "SP");
    }

    @Test
    void shouldReturnCachedAddressAndLogSearch() {
        when(addressPersistencePort.findByCep("01001000")).thenReturn(Optional.of(sampleAddress));

        Optional<Address> result = useCase.execute("01001000");

        assertTrue(result.isPresent());
        verify(searchLogPersistencePort).save(any());
        verifyNoInteractions(addressExternalServicePort);
    }

    @Test
    void shouldFetchSaveAndLogSearch() {
        when(addressPersistencePort.findByCep("01001000")).thenReturn(Optional.empty());
        when(addressExternalServicePort.fetchAddress("01001000")).thenReturn(Optional.of(sampleAddress));

        Optional<Address> result = useCase.execute("01001000");

        assertTrue(result.isPresent());
        verify(addressPersistencePort).save(sampleAddress);
        verify(searchLogPersistencePort).save(any());
    }

    @Test
    void shouldReturnEmptyWhenCepIsInvalid() {
        Optional<Address> result = useCase.execute("123");

        assertFalse(result.isPresent());
        verifyNoInteractions(searchLogPersistencePort);
    }
}
