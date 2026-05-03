package br.com.desafio.geozip.adapter.out.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "addresses")
public class AddressEntity {
    @Id
    private String cep;
    private String logradouro;
    private String bairro;
    private String cidade;
    private String estado;

    public AddressEntity() {}

    public AddressEntity(String cep, String logradouro, String bairro, String cidade, String estado) {
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

        public AddressEntity build() {
            return new AddressEntity(cep, logradouro, bairro, cidade, estado);
        }
    }
}
