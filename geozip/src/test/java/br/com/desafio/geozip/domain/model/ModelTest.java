package br.com.desafio.geozip.domain.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class ModelTest {

    @Test
    @DisplayName("Deve testar os getters e setters do Address")
    void testAddress() {
        Address address = new Address();
        address.setCep("12345678");
        address.setLogradouro("Rua A");
        address.setBairro("Bairro B");
        address.setCidade("Cidade C");
        address.setEstado("ST");

        assertEquals("12345678", address.getCep());
        assertEquals("Rua A", address.getLogradouro());
        assertEquals("Bairro B", address.getBairro());
        assertEquals("Cidade C", address.getCidade());
        assertEquals("ST", address.getEstado());

        Address address2 = new Address("12345678", "Rua A", "Bairro B", "Cidade C", "ST");
        assertEquals(address.getCep(), address2.getCep());
    }

    @Test
    @DisplayName("Deve testar os getters e setters do SearchLog")
    void testSearchLog() {
        LocalDateTime now = LocalDateTime.now();
        SearchLog log = new SearchLog();
        log.setCep("12345678");
        log.setData("Data");
        log.setSearchDate(now);

        assertEquals("12345678", log.getCep());
        assertEquals("Data", log.getData());
        assertEquals(now, log.getSearchDate());

        SearchLog log2 = new SearchLog("12345678", "Data", now);
        assertEquals(log.getCep(), log2.getCep());
    }
}
