package br.com.desafio.geozip.application.usecase;

import br.com.desafio.geozip.application.port.out.AddressExternalServicePort;
import br.com.desafio.geozip.application.port.out.SearchLogPersistencePort;
import br.com.desafio.geozip.domain.exception.CepNotFoundException;
import br.com.desafio.geozip.domain.exception.InvalidCepException;
import br.com.desafio.geozip.domain.model.Address;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AddressByCepUseCaseImplTest {

    @Mock
    private AddressExternalServicePort addressExternalServicePort;

    @Mock
    private SearchLogPersistencePort searchLogPersistencePort;

    @InjectMocks
    private AddressByCepUseCaseImpl useCase;

    private Address sampleAddress;

    @BeforeEach
    void setUp() {
        sampleAddress = new Address("04831031", "Avenida Interlagos", "Jardim Interlagos", "São Paulo", "SP");
    }

    @Test
    @DisplayName("Deve buscar e registrar a pesquisa para um CEP válido")
    void shouldFetchAndLogSearchForValidCep() {
        String validCep = "04831031";
        when(addressExternalServicePort.fetchAddress(validCep)).thenReturn(Optional.of(sampleAddress));

        Optional<Address> result = useCase.execute(validCep);

        assertTrue(result.isPresent());
        assertEquals(sampleAddress, result.get());
        verify(searchLogPersistencePort).save(any());
        verify(addressExternalServicePort).fetchAddress(validCep);
    }

    @Test
    @DisplayName("Deve lançar CepNotFoundException quando o serviço externo retornar vazio")
    void shouldThrowCepNotFoundExceptionWhenExternalServiceReturnsEmpty() {
        String cep = "12345678";
        when(addressExternalServicePort.fetchAddress(cep)).thenReturn(Optional.empty());

        assertThrows(CepNotFoundException.class, () -> useCase.execute(cep));
        verify(addressExternalServicePort).fetchAddress(cep);
        verifyNoInteractions(searchLogPersistencePort);
    }

    @Test
    @DisplayName("Deve lançar InvalidCepException quando o formato do CEP for inválido")
    void shouldThrowInvalidCepExceptionWhenCepIsInvalidFormat() {
        assertThrows(InvalidCepException.class, () -> useCase.execute("123"));
        verifyNoInteractions(addressExternalServicePort);
        verifyNoInteractions(searchLogPersistencePort);
    }

    @Test
    @DisplayName("Deve lançar InvalidCepException quando o CEP for nulo")
    void shouldThrowInvalidCepExceptionWhenCepIsNull() {
        assertThrows(InvalidCepException.class, () -> useCase.execute(null));
    }
}
