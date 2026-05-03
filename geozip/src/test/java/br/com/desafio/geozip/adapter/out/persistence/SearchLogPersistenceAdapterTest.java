package br.com.desafio.geozip.adapter.out.persistence;

import br.com.desafio.geozip.adapter.SearchLogMapper;
import br.com.desafio.geozip.domain.model.SearchLog;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SearchLogPersistenceAdapterTest {

    @Mock
    private JpaSearchLogRepository repository;

    @Mock
    private SearchLogMapper searchLogMapper;

    @InjectMocks
    private SearchLogPersistenceAdapter adapter;

    @Test
    @DisplayName("Deve salvar um log de busca")
    void shouldSaveSearchLog() {
        SearchLog log = SearchLog.builder()
                .cep("04831031")
                .data("Some data")
                .searchDate(LocalDateTime.now())
                .build();
        SearchLogEntity entity = SearchLogEntity.builder().build();

        when(searchLogMapper.toEntity(log)).thenReturn(entity);

        adapter.save(log);

        verify(repository).save(entity);
    }
}
