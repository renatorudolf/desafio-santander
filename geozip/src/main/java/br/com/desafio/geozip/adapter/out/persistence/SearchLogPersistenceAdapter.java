package br.com.desafio.geozip.adapter.out.persistence;

import br.com.desafio.geozip.adapter.SearchLogMapper;
import br.com.desafio.geozip.application.port.out.SearchLogPersistencePort;
import br.com.desafio.geozip.domain.model.SearchLog;
import org.springframework.stereotype.Component;

@Component
public class SearchLogPersistenceAdapter implements SearchLogPersistencePort {

    private final JpaSearchLogRepository repository;
    private final SearchLogMapper searchLogMapper;

    public SearchLogPersistenceAdapter(JpaSearchLogRepository repository, SearchLogMapper searchLogMapper) {
        this.repository = repository;
        this.searchLogMapper = searchLogMapper;
    }

    @Override
    public void save(SearchLog log) {
        repository.save(searchLogMapper.toEntity(log));
    }
}
