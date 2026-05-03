package br.com.desafio.geozip.domain.model;

import java.time.LocalDateTime;

public class SearchLog {
    private String cep;
    private String data;
    private LocalDateTime searchDate;

    public SearchLog() {}

    public SearchLog(String cep, String data, LocalDateTime searchDate) {
        this.cep = cep;
        this.data = data;
        this.searchDate = searchDate;
    }

    public String getCep() { return cep; }
    public void setCep(String cep) { this.cep = cep; }
    public String getData() { return data; }
    public void setData(String data) { this.data = data; }
    public LocalDateTime getSearchDate() { return searchDate; }
    public void setSearchDate(LocalDateTime searchDate) { this.searchDate = searchDate; }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String cep;
        private String data;
        private LocalDateTime searchDate;

        public Builder cep(String cep) { this.cep = cep; return this; }
        public Builder data(String data) { this.data = data; return this; }
        public Builder searchDate(LocalDateTime searchDate) { this.searchDate = searchDate; return this; }

        public SearchLog build() {
            return new SearchLog(cep, data, searchDate);
        }
    }
}
