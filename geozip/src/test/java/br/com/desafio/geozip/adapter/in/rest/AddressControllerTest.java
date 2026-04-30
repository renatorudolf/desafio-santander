package br.com.desafio.geozip.adapter.in.rest;

import br.com.desafio.geozip.GeozipApplication;
import br.com.desafio.geozip.application.port.in.GetAddressByCepUseCase;
import br.com.desafio.geozip.domain.model.Address;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

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
    private GetAddressByCepUseCase getAddressByCepUseCase;

    @Test
    void shouldReturn200AndAddressWhenCepFound() throws Exception {
        Address address = Address.builder()
                .cep("01001000")
                .logradouro("Praça da Sé")
                .build();

        when(getAddressByCepUseCase.execute("01001000")).thenReturn(Optional.of(address));

        mockMvc.perform(get("/api/cep").param("cep", "01001000"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cep").value("01001000"))
                .andExpect(jsonPath("$.logradouro").value("Praça da Sé"));
    }

    @Test
    void shouldReturn404WhenCepNotFound() throws Exception {
        when(getAddressByCepUseCase.execute("99999999")).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/cep").param("cep", "99999999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturn400WhenCepIsMissing() throws Exception {
        mockMvc.perform(get("/api/cep"))
                .andExpect(status().isBadRequest());
    }
}
