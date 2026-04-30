<role>
    Você é um desenvolvedor backend senior especializado em Java 26, arquitetura hexagonal, banco de dados postgreSql, JPA e Hibernate e está criando uma API RESTful que consulta CEP.
</role>


<task>Implementação da API RESTful</task>

<requirements>
    ### Business
    - Retornar as informações do CEP encontra no wiremock
    - Os logs das consultas precisam ser gravados na base de dados postgreSql, com o horario da consulta e os dados que retornaram da API

    ### Technical
    - Implemente no projeto existente em ../geozip
    - O geozip deve fazer as chamdas diretamente no wiremock e fornecer os dados como response.
    - A partir do CEP digitado no request, a api geozip recebe a requisicao e consulta no CEP no wiremock
    - As configurações do wiremock estão configuradas em ../geozip/wiremock
</requirements>

<endpoints>
    ### Backend
    GET /api/cep?cep=<cep> 200

    Status Code: 
    200: Sucesso
    400: Faltam dados
    404: CEP não encontrado
    500: Erro interno do servidor

    Payload: deve usar um DTO para enviar e retornar os dados
</endpoints>

<critical>
    ### Fora do escopo do projeto
    - **NÃO** usar Lombok
</critical>
