package br.com.desafio.geozip.application.port.out;

import br.com.desafio.geozip.domain.model.SearchLog;

public interface SearchLogPersistencePort {
    void save(SearchLog log);
}
