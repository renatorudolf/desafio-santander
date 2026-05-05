package br.com.desafio.geozip.integration;

import br.com.desafio.geozip.GeozipApplication;
import br.com.desafio.geozip.adapter.out.persistence.JpaAddressRepository;
import br.com.desafio.geozip.adapter.out.persistence.JpaSearchLogRepository;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import org.junit.jupiter.api.extension.RegisterExtension;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

@SpringBootTest(classes = GeozipApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class AddressIntegrationTest {

    @RegisterExtension
    static WireMockExtension wireMockServer = WireMockExtension.newInstance()
            .options(wireMockConfig().dynamicPort())
            .build();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JpaSearchLogRepository searchLogRepository;

    @Autowired
    private JpaAddressRepository addressRepository;

    @DynamicPropertySource
    static void wiremockProperties(DynamicPropertyRegistry registry) {
        registry.add("wiremock.url", wireMockServer::baseUrl);
    }

    @BeforeEach
    void setUp() {
        searchLogRepository.deleteAll();
        addressRepository.deleteAll();
    }

    @Test
    @DisplayName("Cenário de Sucesso: CEP 04831031")
    void shouldReturn200AndSaveLogForCep04831031() throws Exception {
        wireMockServer.stubFor(WireMock.get(urlEqualTo("/cep/04831031"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody("{\"cep\":\"04831031\",\"logradouro\":\"Rua Exemplo\",\"bairro\":\"Bairro Exemplo\",\"cidade\":\"São Paulo\",\"estado\":\"SP\"}")));

        mockMvc.perform(get("/api/cep").param("cep", "04831031"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cep").value("04831031"))
                .andExpect(jsonPath("$.logradouro").value("Rua Exemplo"))
                .andExpect(jsonPath("$.bairro").value("Bairro Exemplo"))
                .andExpect(jsonPath("$.cidade").value("São Paulo"))
                .andExpect(jsonPath("$.estado").value("SP"));

        assertEquals(1, searchLogRepository.count(), "Deve ter 1 log registrado no banco");
    }

    @Test
    @DisplayName("Cenário de Sucesso: CEP 04831045")
    void shouldReturn200AndSaveLogForCep04831045() throws Exception {
        wireMockServer.stubFor(WireMock.get(urlEqualTo("/cep/04831045"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody("{\"cep\":\"04831045\",\"logradouro\":\"Rua Manuel de Teffé\",\"bairro\":\"Cidade Dutra\",\"cidade\":\"São Paulo\",\"estado\":\"SP\"}")));

        mockMvc.perform(get("/api/cep").param("cep", "04831045"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cep").value("04831045"))
                .andExpect(jsonPath("$.logradouro").value("Rua Manuel de Teffé"))
                .andExpect(jsonPath("$.bairro").value("Cidade Dutra"))
                .andExpect(jsonPath("$.cidade").value("São Paulo"))
                .andExpect(jsonPath("$.estado").value("SP"));

        assertEquals(1, searchLogRepository.count(), "Deve ter 1 log registrado no banco");
    }

    @Test
    @DisplayName("Cenário de Erro: CEP Não Encontrado (404)")
    void shouldReturn404WhenCepDoesNotExist() throws Exception {
        wireMockServer.stubFor(WireMock.get(urlEqualTo("/cep/99999999"))
                .willReturn(aResponse()
                        .withStatus(404)));

        mockMvc.perform(get("/api/cep").param("cep", "99999999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("CEP não encontrado"));

        assertEquals(0, searchLogRepository.count(), "Não deve salvar log para consultas sem sucesso");
    }

    @Test
    @DisplayName("Cenário de Erro: CEP Inválido - Menos de 8 dígitos (400)")
    void shouldReturn400WhenCepIsTooShort() throws Exception {
        mockMvc.perform(get("/api/cep").param("cep", "12345"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("faltam dados"));

        assertEquals(0, searchLogRepository.count());
    }

    @Test
    @DisplayName("Cenário de Erro: CEP Inválido - Com Letras (400)")
    void shouldReturn400WhenCepContainsLetters() throws Exception {
        mockMvc.perform(get("/api/cep").param("cep", "048310A1"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("faltam dados"));

        assertEquals(0, searchLogRepository.count());
    }

    @Test
    @DisplayName("Cenário de Erro: CEP Ausente (400)")
    void shouldReturn400WhenCepIsMissing() throws Exception {
        mockMvc.perform(get("/api/cep"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("faltam dados"));

        assertEquals(0, searchLogRepository.count());
    }
}
