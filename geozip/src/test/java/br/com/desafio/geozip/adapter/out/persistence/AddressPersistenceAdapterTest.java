package br.com.desafio.geozip.adapter.out.persistence;

import br.com.desafio.geozip.adapter.AddressMapper;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AddressPersistenceAdapterTest {

    @Mock
    private JpaAddressRepository repository;

    @Mock
    private AddressMapper addressMapper;

    @InjectMocks
    private AddressPersistenceAdapter adapter;

    private Address address;
    private AddressEntity entity;

    @BeforeEach
    void setUp() {
        address = Address.builder()
                .cep("04831031")
                .logradouro("Avenida Interlagos")
                .bairro("Jardim Interlagos")
                .cidade("São Paulo")
                .estado("SP")
                .build();

        entity = AddressEntity.builder()
                .cep("04831031")
                .logradouro("Avenida Interlagos")
                .bairro("Jardim Interlagos")
                .cidade("São Paulo")
                .estado("SP")
                .build();
    }

    @Test
    @DisplayName("Deve salvar um endereço")
    void shouldSaveAddress() {
        when(addressMapper.toEntity(address)).thenReturn(entity);
        
        adapter.save(address);
        
        verify(repository).save(entity);
    }

    @Test
    @DisplayName("Deve buscar um endereço pelo CEP")
    void shouldFindByCep() {
        when(repository.findById("04831031")).thenReturn(Optional.of(entity));
        when(addressMapper.toDomain(entity)).thenReturn(address);

        Optional<Address> result = adapter.findByCep("04831031");

        assertTrue(result.isPresent());
        assertEquals(address.getCep(), result.get().getCep());
    }

    @Test
    @DisplayName("Deve retornar vazio quando o CEP não for encontrado")
    void shouldReturnEmptyWhenNotFound() {
        when(repository.findById("00000000")).thenReturn(Optional.empty());

        Optional<Address> result = adapter.findByCep("00000000");

        assertFalse(result.isPresent());
    }
}
