package br.com.desafio.geozip.adapter.out.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "search_logs")
public class SearchLogEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cep;
    private String responseData;
    private LocalDateTime searchTimestamp;

    public SearchLogEntity() {}

    public SearchLogEntity(String cep, String responseData, LocalDateTime searchTimestamp) {
        this.cep = cep;
        this.responseData = responseData;
        this.searchTimestamp = searchTimestamp;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCep() { return cep; }
    public void setCep(String cep) { this.cep = cep; }

    public String getResponseData() { return responseData; }
    public void setResponseData(String responseData) { this.responseData = responseData; }

    public LocalDateTime getSearchTimestamp() { return searchTimestamp; }
    public void setSearchTimestamp(LocalDateTime searchTimestamp) { this.searchTimestamp = searchTimestamp; }
}
