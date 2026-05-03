package br.com.desafio.geozip.adapter;

import br.com.desafio.geozip.adapter.in.rest.AddressResponseDTO;
import br.com.desafio.geozip.adapter.out.persistence.AddressEntity;
import br.com.desafio.geozip.adapter.out.wiremock.WiremockAddressResponse;
import br.com.desafio.geozip.domain.model.Address;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AddressMapper {

    AddressResponseDTO toDTO(Address address);

    AddressEntity toEntity(Address address);

    Address toDomain(AddressEntity entity);

    Address toDomain(WiremockAddressResponse response);
}
