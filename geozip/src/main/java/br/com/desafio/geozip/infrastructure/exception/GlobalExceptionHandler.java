package br.com.desafio.geozip.infrastructure.exception;

import br.com.desafio.geozip.adapter.in.rest.ErrorResponseDTO;
import br.com.desafio.geozip.domain.exception.CepNotFoundException;
import br.com.desafio.geozip.domain.exception.InvalidCepException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidCepException.class)
    public ResponseEntity<ErrorResponseDTO> handleInvalidCepException(InvalidCepException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseDTO(e.getMessage()));
    }

    @ExceptionHandler(CepNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleCepNotFoundException(CepNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDTO(e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleGeneralException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
