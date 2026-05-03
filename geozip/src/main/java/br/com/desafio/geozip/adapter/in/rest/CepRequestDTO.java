package br.com.desafio.geozip.adapter.in.rest;

public class CepRequestDTO {
    private String cep;

    public CepRequestDTO() {}

    public CepRequestDTO(String cep) {
        this.cep = cep;
    }

    public String getCep() { return cep; }
    public void setCep(String cep) { this.cep = cep; }
}
