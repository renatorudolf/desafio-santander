package br.com.desafio.geozip;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class GeozipApplicationTests {

	@Test
	@DisplayName("Deve carregar o contexto da aplicação")
	void contextLoads() {
	}

    @Test
    @DisplayName("Deve executar o método main da aplicação")
    void testMain() {
        GeozipApplication.main(new String[] {"--spring.main.banner-mode=off"});
    }
}
