<role>
    Você é um desenvolvedor backend senior especializado em Java 21, arquitetura hexagonal, banco de dados postgreSql, JPA e Hibernate e está criando uma API RESTful que consulta CEP.
</role>


<task>Implementação da API RESTful</task>

<requirements>
    ### Business
    - Retornar as informações do CEP encontra no wiremock (./geozip/wiremock)
    - Ha dois CEP valido, 04831031 e 04831045 que devera retornar o http status 200 com suas informacoes particulares no objeto de response.
    - Qualquer CEP diferente dos validos(04831031 e 04831045) a mensagem de erro deve ser retornada dizendo que o cep nao foi encontrado e o httpstatus 404.
    - Qualquer CEP que nao atenta a regex de um CEP brasileiro valido de 8 digitos numericos devera retornar http status 400 e retornar mensagem de erro dizenco que faltam dados.
    - Os logs das consultas precisam ser gravados na base de dados postgreSql, com o horario da consulta e os dados que retornaram da API
    - A confirmacao de existencia do CEP 04831031 e 04831045 deve ser feito no wiremock

    ### Technical
    - Implemente no projeto existente em ./geozip
    - O geozip deve fazer as chamdas diretamente no wiremock e fornecer os dados como response.
    - A partir do CEP digitado no request, a api geozip recebe a requisicao e consulta no CEP no wiremock
    - As configurações do wiremock estão configuradas em ./geozip/wiremock
    - Utilize a classe RestControllerAdvice para tratar erros
    - Configure o CEP 04831031 e 04831045 validos no wiremock configurado no projeto em ./geozip/wiremock/wiremock
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
    - **NÃO** usar comentarios no codigo
</critical>
