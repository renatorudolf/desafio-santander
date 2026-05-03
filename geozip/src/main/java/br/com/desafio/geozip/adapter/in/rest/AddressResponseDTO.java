package br.com.desafio.geozip.adapter.in.rest;

public class AddressResponseDTO {
    private String cep;
    private String logradouro;
    private String bairro;
    private String cidade;
    private String estado;

    public AddressResponseDTO() {}

    public AddressResponseDTO(String cep, String logradouro, String bairro, String cidade, String estado) {
        this.cep = cep;
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
    }

    public String getCep() { return cep; }
    public void setCep(String cep) { this.cep = cep; }
    public String getLogradouro() { return logradouro; }
    public void setLogradouro(String logradouro) { this.logradouro = logradouro; }
    public String getBairro() { return bairro; }
    public void setBairro(String bairro) { this.bairro = bairro; }
    public String getCidade() { return cidade; }
    public void setCidade(String cidade) { this.cidade = cidade; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String cep;
        private String logradouro;
        private String bairro;
        private String cidade;
        private String estado;

        public Builder cep(String cep) { this.cep = cep; return this; }
        public Builder logradouro(String logradouro) { this.logradouro = logradouro; return this; }
        public Builder bairro(String bairro) { this.bairro = bairro; return this; }
        public Builder cidade(String cidade) { this.cidade = cidade; return this; }
        public Builder estado(String estado) { this.estado = estado; return this; }

        public AddressResponseDTO build() {
            return new AddressResponseDTO(cep, logradouro, bairro, cidade, estado);
        }
    }
}
