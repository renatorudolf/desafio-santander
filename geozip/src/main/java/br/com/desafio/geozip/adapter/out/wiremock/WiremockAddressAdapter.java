package br.com.desafio.geozip.adapter.out.wiremock;

import br.com.desafio.geozip.adapter.AddressMapper;
import br.com.desafio.geozip.application.port.out.AddressExternalServicePort;
import br.com.desafio.geozip.domain.model.Address;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestOperations;

import java.util.Optional;

@Component
public class WiremockAddressAdapter implements AddressExternalServicePort {

    private final RestOperations restTemplate;
    private final AddressMapper addressMapper;

    @Value("${wiremock.url}")
    private String wiremockUrl;

    public WiremockAddressAdapter(RestOperations restTemplate, AddressMapper addressMapper) {
        this.restTemplate = restTemplate;
        this.addressMapper = addressMapper;
    }

    @Override
    public Optional<Address> fetchAddress(String cep) {
        String url = wiremockUrl + "/cep/" + cep;
        try {
            WiremockAddressResponse response = restTemplate.getForObject(url, WiremockAddressResponse.class);
            if (response == null || response.getCep() == null) {
                return Optional.empty();
            }
            return Optional.of(addressMapper.toDomain(response));
        } catch (HttpClientErrorException.NotFound e) {
            return Optional.empty();
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
