package br.com.desafio.geozip.adapter.out.persistence;

import br.com.desafio.geozip.application.port.out.SearchLogPersistencePort;
import br.com.desafio.geozip.domain.model.SearchLog;
import org.springframework.stereotype.Component;

@Component
public class SearchLogPersistenceAdapter implements SearchLogPersistencePort {

    private final JpaSearchLogRepository repository;

    public SearchLogPersistenceAdapter(JpaSearchLogRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(SearchLog log) {
        repository.save(new SearchLogEntity(
                log.getCep(),
                log.getResponseData(),
                log.getSearchTimestamp()
        ));
    }
}
