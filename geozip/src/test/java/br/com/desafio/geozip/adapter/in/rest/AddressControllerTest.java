package br.com.desafio.geozip.adapter.in.rest;

import br.com.desafio.geozip.GeozipApplication;
import br.com.desafio.geozip.adapter.AddressMapper;
import br.com.desafio.geozip.application.port.in.AddressByCepUseCase;
import br.com.desafio.geozip.domain.exception.CepNotFoundException;
import br.com.desafio.geozip.domain.exception.InvalidCepException;
import br.com.desafio.geozip.domain.model.Address;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AddressController.class)
@ContextConfiguration(classes = GeozipApplication.class)
class AddressControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AddressByCepUseCase getAddressByCepUseCase;

    @MockBean
    private AddressMapper addressMapper;

    @Test
    @DisplayName("Deve retornar 200 e o endereço quando o CEP for encontrado")
    void shouldReturn200AndAddressWhenCepFound() throws Exception {
        Address address = Address.builder()
                .cep("04831031")
                .logradouro("Avenida Interlagos")
                .build();
        AddressResponseDTO responseDTO = new AddressResponseDTO();
        responseDTO.setCep("04831031");
        responseDTO.setLogradouro("Avenida Interlagos");

        when(getAddressByCepUseCase.execute("04831031")).thenReturn(Optional.of(address));
        when(addressMapper.toDTO(address)).thenReturn(responseDTO);

        mockMvc.perform(get("/api/cep").param("cep", "04831031"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cep").value("04831031"))
                .andExpect(jsonPath("$.logradouro").value("Avenida Interlagos"));
    }

    @Test
    @DisplayName("Deve retornar 200 e o endereço quando o CEP 04831045 for encontrado")
    void shouldReturn200AndAddressWhenCep04831045Found() throws Exception {
        Address address = Address.builder()
                .cep("04831045")
                .logradouro("Rua Manuel de Teffé")
                .build();
        AddressResponseDTO responseDTO = new AddressResponseDTO();
        responseDTO.setCep("04831045");
        responseDTO.setLogradouro("Rua Manuel de Teffé");

        when(getAddressByCepUseCase.execute("04831045")).thenReturn(Optional.of(address));
        when(addressMapper.toDTO(address)).thenReturn(responseDTO);

        mockMvc.perform(get("/api/cep").param("cep", "04831045"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cep").value("04831045"))
                .andExpect(jsonPath("$.logradouro").value("Rua Manuel de Teffé"));
    }

    @Test
    @DisplayName("Deve retornar 404 quando o CEP não for encontrado")
    void shouldReturn404WhenCepNotFound() throws Exception {
        when(getAddressByCepUseCase.execute("12345678")).thenThrow(new CepNotFoundException("CEP não encontrado"));

        mockMvc.perform(get("/api/cep").param("cep", "12345678"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("CEP não encontrado"));
    }

    @Test
    @DisplayName("Deve retornar 400 quando o CEP for inválido")
    void shouldReturn400WhenCepIsInvalid() throws Exception {
        when(getAddressByCepUseCase.execute("123")).thenThrow(new InvalidCepException("faltam dados"));

        mockMvc.perform(get("/api/cep").param("cep", "123"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("faltam dados"));
    }

    @Test
    @DisplayName("Deve retornar 400 quando o CEP não for informado")
    void shouldReturn400WhenCepIsMissing() throws Exception {
        when(getAddressByCepUseCase.execute(null)).thenThrow(new InvalidCepException("faltam dados"));

        mockMvc.perform(get("/api/cep"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("faltam dados"));
    }
}
