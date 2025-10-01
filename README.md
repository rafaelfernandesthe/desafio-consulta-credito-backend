# Consulta Crédito Backend

## Pré-requisitos
- Docker
- Docker Compose

## Como executar
1. Suba os containers com o Docker Compose:
   ```bash
   docker-compose up -d
   ```
   > Obs.: O banco de dados já vai estar correto pois o arquivo `init.sql` é carregado no start do container mysql para criar tabelas e popular com alguns registros. 

2. Start da aplicação java
   ```bash
   mvn clean package
   java -jar target/desafio-consulta-credito-backend-0.0.1-SNAPSHOT.jar
   ```

## Autenticação
- não é necessária autenticaçao

## Documentação da API
Os endpoints estão disponíveis e podem ser explorados no Swagger:
http://localhost:8080/swagger-ui.html

## Postman
Uma collection do Postman com requisições configuradas está disponível no diretório raiz do projeto, é so importar e utilizar :)