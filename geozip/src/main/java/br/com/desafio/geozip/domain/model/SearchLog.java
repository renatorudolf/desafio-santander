package br.com.desafio.geozip.domain.model;

import java.time.LocalDateTime;

public class SearchLog {
    private String cep;
    private String responseData;
    private LocalDateTime searchTimestamp;

    public SearchLog() {}

    public SearchLog(String cep, String responseData, LocalDateTime searchTimestamp) {
        this.cep = cep;
        this.responseData = responseData;
        this.searchTimestamp = searchTimestamp;
    }

    public String getCep() { return cep; }
    public void setCep(String cep) { this.cep = cep; }

    public String getResponseData() { return responseData; }
    public void setResponseData(String responseData) { this.responseData = responseData; }

    public LocalDateTime getSearchTimestamp() { return searchTimestamp; }
    public void setSearchTimestamp(LocalDateTime searchTimestamp) { this.searchTimestamp = searchTimestamp; }
}
