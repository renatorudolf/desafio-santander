package br.com.desafio.geozip.adapter.in.rest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DTOTest {

    @Test
    @DisplayName("Deve testar os getters e setters do AddressResponseDTO")
    void testAddressResponseDTO() {
        AddressResponseDTO dto = new AddressResponseDTO();
        dto.setCep("12345678");
        dto.setLogradouro("Rua A");
        dto.setBairro("Bairro B");
        dto.setCidade("Cidade C");
        dto.setEstado("ST");

        assertEquals("12345678", dto.getCep());
        assertEquals("Rua A", dto.getLogradouro());
        assertEquals("Bairro B", dto.getBairro());
        assertEquals("Cidade C", dto.getCidade());
        assertEquals("ST", dto.getEstado());

        AddressResponseDTO dto2 = new AddressResponseDTO("12345678", "Rua A", "Bairro B", "Cidade C", "ST");
        assertEquals(dto.getCep(), dto2.getCep());
    }

    @Test
    @DisplayName("Deve testar os getters e setters do CepRequestDTO")
    void testCepRequestDTO() {
        CepRequestDTO dto = new CepRequestDTO();
        dto.setCep("12345678");
        assertEquals("12345678", dto.getCep());

        CepRequestDTO dto2 = new CepRequestDTO("12345678");
        assertEquals(dto.getCep(), dto2.getCep());
    }

    @Test
    @DisplayName("Deve testar os getters e setters do ErrorResponseDTO")
    void testErrorResponseDTO() {
        ErrorResponseDTO dto = new ErrorResponseDTO();
        dto.setMessage("Error");
        assertEquals("Error", dto.getMessage());

        ErrorResponseDTO dto2 = new ErrorResponseDTO("Error");
        assertEquals(dto.getMessage(), dto2.getMessage());
    }
}
