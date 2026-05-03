package br.com.desafio.geozip.adapter;

import br.com.desafio.geozip.adapter.out.persistence.SearchLogEntity;
import br.com.desafio.geozip.domain.model.SearchLog;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SearchLogMapper {

    @Mapping(source = "data", target = "responseData")
    @Mapping(source = "searchDate", target = "searchTimestamp")
    @Mapping(target = "id", ignore = true)
    SearchLogEntity toEntity(SearchLog log);

    @Mapping(source = "responseData", target = "data")
    @Mapping(source = "searchTimestamp", target = "searchDate")
    SearchLog toDomain(SearchLogEntity entity);
}
