# GeoZip - Desafio Santander

<!-- README-I18N:START -->

[English](./README.md) | **Português**

<!-- README-I18N:END -->

Este repositório contém a solução para o **Desafio Santander**, que consiste em um microserviço para consulta de CEPs brasileiros. O projeto foi desenvolvido com Java 21 e Spring Boot, seguindo os princípios da **Arquitetura Hexagonal (Ports and Adapters)**.

## 📂 Estrutura do Projeto

- **[geozip/](./geozip/)**: A aplicação Java principal (Spring Boot).
- **[ia/](./ia/)**: Recursos de desenvolvimento assistido por IA, incluindo skills personalizadas e prompts utilizados.
- **[solucao/](./solucao/)**: Desenho da arquitetura e diagramas da solução (Excalidraw).

## 🚀 Tecnologias

- **Java 21**
- **Spring Boot 3.4.0**
- **Spring Data JPA**
- **PostgreSQL** (Produção)
- **H2 Database** (Testes)
- **Maven**
- **MapStruct**
- **JaCoCo** (Cobertura de Testes - Lógica de Negócio)
- **Wiremock** (Mock de serviços externos)

## 🏗️ Arquitetura

O projeto segue o padrão de Arquitetura Hexagonal para garantir alto desacoplamento e facilidade de manutenção:

- **Domain**: Entidades de negócio (`Address`, `SearchLog`) e exceções de domínio.
- **Application**: Portas de entrada/saída e implementação de casos de uso (`AddressByCepUseCaseImpl`).
- **Adapters (In)**: Controladores REST.
- **Adapters (Out)**: Persistência (JPA) e integrações externas (Wiremock).
- **Infrastructure**: Configurações transversais, como Beans do Spring e tratamento global de exceções.

## 🛠️ Como Executar

Para instruções detalhadas sobre como configurar, executar e testar a aplicação, consulte a documentação na pasta da aplicação:

👉 **[Ir para geozip/README.md](./geozip/README.md)**

---
Desenvolvido como parte do Desafio Santander.
