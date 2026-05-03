package br.com.desafio.geozip.infrastructure.exception;

import br.com.desafio.geozip.adapter.in.rest.ErrorResponseDTO;
import br.com.desafio.geozip.domain.exception.CepNotFoundException;
import br.com.desafio.geozip.domain.exception.InvalidCepException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.*;

class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

    @Test
    @DisplayName("Deve tratar InvalidCepException e retornar 400")
    void testHandleInvalidCepException() {
        ResponseEntity<ErrorResponseDTO> response = handler.handleInvalidCepException(new InvalidCepException("faltam dados"));
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("faltam dados", response.getBody().getMessage());
    }

    @Test
    @DisplayName("Deve tratar CepNotFoundException e retornar 404")
    void testHandleCepNotFoundException() {
        ResponseEntity<ErrorResponseDTO> response = handler.handleCepNotFoundException(new CepNotFoundException("CEP não encontrado"));
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("CEP não encontrado", response.getBody().getMessage());
    }

    @Test
    @DisplayName("Deve tratar exceções genéricas e retornar 500")
    void testHandleGeneralException() {
        ResponseEntity<ErrorResponseDTO> response = handler.handleGeneralException(new RuntimeException("Error"));
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
}
