package br.com.desafio.geozip.adapter.out.persistence;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class EntityTest {

    @Test
    @DisplayName("Deve testar os getters, setters e builder do AddressEntity")
    void testAddressEntity() {
        AddressEntity entity = new AddressEntity();
        entity.setCep("12345678");
        entity.setLogradouro("Rua A");
        entity.setBairro("Bairro B");
        entity.setCidade("Cidade C");
        entity.setEstado("ST");

        assertEquals("12345678", entity.getCep());
        assertEquals("Rua A", entity.getLogradouro());
        assertEquals("Bairro B", entity.getBairro());
        assertEquals("Cidade C", entity.getCidade());
        assertEquals("ST", entity.getEstado());

        AddressEntity entity2 = new AddressEntity("12345678", "Rua A", "Bairro B", "Cidade C", "ST");
        assertEquals(entity.getCep(), entity2.getCep());
        
        AddressEntity entity3 = AddressEntity.builder()
                .cep("12345678")
                .build();
        assertEquals("12345678", entity3.getCep());
    }

    @Test
    @DisplayName("Deve testar os getters, setters e builder do SearchLogEntity")
    void testSearchLogEntity() {
        LocalDateTime now = LocalDateTime.now();
        SearchLogEntity entity = new SearchLogEntity();
        entity.setId(1L);
        entity.setCep("12345678");
        entity.setResponseData("Data");
        entity.setSearchTimestamp(now);

        assertEquals(1L, entity.getId());
        assertEquals("12345678", entity.getCep());
        assertEquals("Data", entity.getResponseData());
        assertEquals(now, entity.getSearchTimestamp());

        SearchLogEntity entity2 = new SearchLogEntity(1L, "12345678", "Data", now);
        assertEquals(entity.getId(), entity2.getId());

        SearchLogEntity entity3 = new SearchLogEntity("12345678", "Data", now);
        assertEquals("12345678", entity3.getCep());
        
        SearchLogEntity entity4 = SearchLogEntity.builder()
                .id(1L)
                .build();
        assertEquals(1L, entity4.getId());
    }
}
