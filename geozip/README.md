# GeoZip API

A GeoZip API é um microserviço desenvolvido em Spring Boot para consulta de endereços baseada no CEP (Código de Endereçamento Postal) brasileiro. O projeto segue os princípios da **Arquitetura Hexagonal (Ports and Adapters)** para garantir desacoplamento e facilidade de manutenção.

## 🚀 Tecnologias

- **Java 21**
- **Spring Boot 3.4.0**
- **Spring Data JPA**
- **PostgreSQL** (Produção)
- **H2 Database** (Testes Integrados)
- **Maven**
- **JaCoCo** (Cobertura de Testes - Focado em lógica de negócio)
- **Wiremock** (Mock de serviços externos)

## 🏗️ Arquitetura

O projeto está estruturado seguindo a Arquitetura Hexagonal:

- **Domain**: Contém as entidades de negócio (`Address`, `SearchLog`) e exceções de domínio.
- **Application**: Contém as portas de entrada/saída e a implementação dos casos de uso (`GetAddressByCepUseCase`).
- **Adapters (In)**: Adaptadores de entrada, como controladores REST.
- **Adapters (Out)**: Adaptadores de saída, como persistência em banco de dados e integração com serviços externos via Wiremock.
- **Infrastructure**: Configurações transversais, como Beans do Spring e tratamento global de exceções.

## 🛠️ Como Executar

### Pré-requisitos

- Java 21
- Maven 3.9+
- PostgreSQL rodando localmente (ou via Docker)
- Wiremock para simular o serviço externo de CEP

### Configuração do Banco de Dados

Certifique-se de que o PostgreSQL está rodando e crie o banco de dados conforme configurado no `application.yaml`:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/santanderDB
    username: root
    password: root
```

### Executando a Aplicação

1. Clone o repositório.
2. Navegue até a pasta `geozip`.
3. Execute o comando:
   ```bash
   mvn spring-boot:run
   ```

### Executando os Testes

Para executar os testes unitários e de integração com relatório de cobertura JaCoCo:
```bash
mvn test
```

## 🔌 API Endpoints

### Consultar CEP

Retorna as informações de endereço para um CEP válido (8 dígitos numéricos).

- **URL:** `/api/cep`
- **Método:** `GET`
- **Parâmetros:** `cep=[string]`
- **Exemplo:** `GET http://localhost:8080/api/cep?cep=04831031`

**Resposta de Sucesso (200 OK):**
```json
{
  "cep": "04831031",
  "logradouro": "Rua Exemplo",
  "bairro": "Bairro Exemplo",
  "cidade": "São Paulo",
  "estado": "SP"
}
```

**Erros Comuns:**
- `400 Bad Request`: "faltam dados" (CEP inválido ou nulo).
- `404 Not Found`: "CEP não encontrado".

## 📝 Registro de Consultas

Sempre que uma consulta de CEP é realizada com sucesso, os dados são persistidos no banco de dados na tabela de logs (`search_log`), permitindo auditoria e histórico de pesquisas.

---
Desenvolvido como parte do Desafio Santander.
 de dados na tabela de logs (`search_log`), permitindo auditoria e histórico de pesquisas.

---
Desenvolvido como parte do Desafio Santander.
