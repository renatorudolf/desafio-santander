package br.com.desafio.geozip.adapter.in.rest;

public class ErrorResponseDTO {
    private String message;

    public ErrorResponseDTO() {}

    public ErrorResponseDTO(String message) {
        this.message = message;
    }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
