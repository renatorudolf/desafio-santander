# GeoZip - Santander Challenge

This repository contains the solution for the **Santander Challenge**, a resilient microservice for querying Brazilian ZIP codes (CEP). The project is developed with **Java 21** and **Spring Boot 3.4**, strictly following **Hexagonal Architecture (Ports and Adapters)** principles.

## 📂 Project Structure

- **[geozip/](./geozip/)**: The core Java application (Spring Boot).
- **[ia/](./ia/)**: AI-assisted development resources, including custom skills and prompts used.
- **[solucao/](./solucao/)**: Architectural design and solution diagrams (Excalidraw).

## 🚀 Technology Stack

- **Java 21** (LTS)
- **Spring Boot 3.4.0**
- **Spring Data JPA**
- **PostgreSQL** (Production)
- **H2 Database** (Testing)
- **Maven**
- **MapStruct**
- **JaCoCo** (Business Logic Test Coverage)
- **Wiremock** (External Service Mocking)

## 🏗️ Architectural Vision

The project leverages Hexagonal Architecture to ensure high decoupling and long-term maintainability:

- **Domain**: Pure business entities (`Address`, `SearchLog`) and domain exceptions.
- **Application**: Input/Output ports and Use Case implementations (`AddressByCepUseCaseImpl`).
- **Adapters (In)**: REST Controllers and entry points.
- **Adapters (Out)**: Persistence (JPA) and external integrations (Third-party Address APIs).
- **Infrastructure**: Cross-cutting concerns, Spring configurations, and Global Exception Handling.

## 🛠️ Getting Started

For detailed instructions on setup, execution, and testing, please refer to the application-specific documentation:

👉 **[Go to geozip/README_EN.md](./geozip/README_EN.md)**

---
*Developed as a high-standard technical showcase for Senior Software Engineering roles.*
