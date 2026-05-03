package br.com.desafio.geozip.adapter.out.wiremock;

import br.com.desafio.geozip.adapter.AddressMapper;
import br.com.desafio.geozip.domain.model.Address;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestOperations;
import org.springframework.http.HttpStatus;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class WiremockAddressAdapterTest {

    @Mock
    private RestOperations restTemplate;

    @Mock
    private AddressMapper addressMapper;

    @InjectMocks
    private WiremockAddressAdapter adapter;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(adapter, "wiremockUrl", "http://localhost:8081");
    }

    @Test
    @DisplayName("Deve buscar o endereço com sucesso")
    void shouldFetchAddressSuccessfully() {
        WiremockAddressResponse response = new WiremockAddressResponse("04831031", "Logradouro", "Bairro", "Cidade", "SP");
        Address address = Address.builder().cep("04831031").build();
        
        when(restTemplate.getForObject("http://localhost:8081/cep/04831031", WiremockAddressResponse.class))
                .thenReturn(response);
        when(addressMapper.toDomain(response)).thenReturn(address);

        Optional<Address> result = adapter.fetchAddress("04831031");

        assertTrue(result.isPresent());
        assertEquals("04831031", result.get().getCep());
    }

    @Test
    @DisplayName("Deve retornar vazio quando o endereço não for encontrado")
    void shouldReturnEmptyOnNotFound() {
        when(restTemplate.getForObject("http://localhost:8081/cep/00000000", WiremockAddressResponse.class))
                .thenThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND));

        Optional<Address> result = adapter.fetchAddress("00000000");

        assertFalse(result.isPresent());
    }

    @Test
    @DisplayName("Deve retornar vazio em caso de outras exceções")
    void shouldReturnEmptyOnOtherExceptions() {
        when(restTemplate.getForObject("http://localhost:8081/cep/04831031", WiremockAddressResponse.class))
                .thenThrow(new RuntimeException("Error"));

        Optional<Address> result = adapter.fetchAddress("04831031");

        assertFalse(result.isPresent());
    }

    @Test
    @DisplayName("Deve retornar vazio quando a resposta for nula")
    void shouldReturnEmptyWhenResponseIsNull() {
        when(restTemplate.getForObject("http://localhost:8081/cep/04831031", WiremockAddressResponse.class))
                .thenReturn(null);

        Optional<Address> result = adapter.fetchAddress("04831031");

        assertFalse(result.isPresent());
    }
}
