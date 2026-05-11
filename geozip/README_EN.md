# GeoZip API - Resilient Address Service

GeoZip API is a high-performance microservice developed with **Spring Boot 3.4** and **Java 21**, designed to query Brazilian addresses based on ZIP codes (CEP). This project serves as a showcase of **Senior Software Engineering** practices, focusing on maintainability, scalability, and testability.

## 🏗️ Architectural Vision: Hexagonal (Ports & Adapters)

The project strictly follows the **Hexagonal Architecture** pattern to ensure business logic is completely decoupled from infrastructure and external dependencies:

- **Domain**: Pure business logic containing entities (`Address`, `SearchLog`) and domain-specific exceptions. No framework dependencies.
- **Application**: Orchestrates use cases (`GetAddressByCepUseCase`) and defines Input/Output ports (Interfaces).
- **Adapters (In)**: Primary adapters, such as REST Controllers, handling external requests.
- **Adapters (Out)**: Secondary adapters for persistence (PostgreSQL) and external integrations (Third-party Address APIs).
- **Infrastructure**: Cross-cutting concerns, Spring configurations, and global exception handling.

## 🚀 Technology Stack

- **Java 21**: Leveraging the long-term support (LTS) release for improved performance, security, and modern runtime capabilities.
- **Spring Boot 3.4.0**: Latest version for improved performance and native support.
- **Spring Data JPA & PostgreSQL**: Robust data persistence and auditing.
- **MapStruct & Lombok**: Reducing boilerplate code while maintaining type safety.
- **JaCoCo**: Comprehensive test coverage reporting (focused on business logic).
- **Wiremock**: Used for mocking external address services in integration tests to ensure deterministic results.

## 🧪 Quality & Testing Strategy

High-quality code is a priority. The project implements a multi-layered testing strategy:
- **Unit Tests**: Verifying business rules in isolation.
- **Integration Tests**: Ensuring seamless communication between adapters and the database/external services.
- **Coverage**: Automated reporting via JaCoCo to maintain high standards of reliability.

## 🛠️ Getting Started

### Prerequisites

- Java 21
- Maven 3.9+
- Docker & Docker Compose (Recommended)

### Database Configuration

Ensure PostgreSQL is running. Default configuration (can be overridden via environment variables):
```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/santanderDB
    username: root
    password: root
```

### Running the Application

1. Clone the repository.
2. Navigate to the `geozip` directory.
3. Start the infrastructure (Postgres & Wiremock):
   ```bash
   cd wiremock && docker-compose up -d && cd ..
   ```
4. Run the app:
   ```bash
   mvn spring-boot:run
   ```

### Running Tests & Reports

```bash
mvn test
```
The JaCoCo report will be generated at `target/site/jacoco/index.html`.

## 🔌 API Documentation

### Query ZIP Code

- **Endpoint:** `/api/cep`
- **Method:** `GET`
- **Query Param:** `cep=[8-digit string]`
- **Example:** `GET http://localhost:8080/api/cep?cep=04831031`

**Success Response (200 OK):**
```json
{
  "cep": "04831031",
  "logradouro": "Rua Exemplo",
  "bairro": "Bairro Exemplo",
  "cidade": "São Paulo",
  "estado": "SP"
}
```

## 📝 Audit & Logging
Every successful query is automatically persisted in the `search_log` table for auditing, performance monitoring, and search history analysis.

---
*Developed as a high-standard technical showcase for Senior Java Engineering roles.*
