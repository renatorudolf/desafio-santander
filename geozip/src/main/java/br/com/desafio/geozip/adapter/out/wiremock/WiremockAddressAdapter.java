package br.com.desafio.geozip.adapter.out.wiremock;

import br.com.desafio.geozip.application.port.out.AddressExternalServicePort;
import br.com.desafio.geozip.domain.model.Address;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
public class WiremockAddressAdapter implements AddressExternalServicePort {

    private final RestTemplate restTemplate;

    @Value("${wiremock.url}")
    private String wiremockUrl;

    public WiremockAddressAdapter(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Optional<Address> fetchAddress(String cep) {
        String url = wiremockUrl + "/cep/" + cep;
        try {
            WiremockAddressResponse response = restTemplate.getForObject(url, WiremockAddressResponse.class);
            if (response == null || response.getCep() == null) {
                return Optional.empty();
            }
            return Optional.of(mapToDomain(response));
        } catch (HttpClientErrorException.NotFound e) {
            return Optional.empty();
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    private Address mapToDomain(WiremockAddressResponse response) {
        return Address.builder()
                .cep(response.getCep())
                .logradouro(response.getLogradouro())
                .bairro(response.getBairro())
                .cidade(response.getCidade())
                .estado(response.getEstado())
                .build();
    }
}
