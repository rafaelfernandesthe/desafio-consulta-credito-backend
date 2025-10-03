# Consulta Crédito Backend

## Pré-requisitos
- Docker
- Docker Compose

## Como executar o Backend
1. Clone do projeto
   ```bash
   git clone git@github.com:rafaelfernandesthe/desafio-consulta-credito-backend.git
   ou
   git clone https://github.com/rafaelfernandesthe/desafio-consulta-credito-backend.git
   ```
2. Na pasta raiz, inicie os containers do backend com o Docker Compose:
   ```bash
   docker-compose up --build -d
   ``` 
> Obs.: O banco de dados *docker* já vai estar correto pois o arquivo `init.sql` é carregado no start do container postgres para criar tabelas e popular com alguns registros.

## Documentação da API
Os endpoints podem ser explorados no Swagger:
http://localhost:8080/swagger-ui.html

## Como executar o Frontend
veja em https://github.com/rafaelfernandesthe/desafio-consulta-credito-frontend

### Postman
Uma collection do Postman com requisições configuradas está disponível no diretório raiz do projeto, é so importar e utilizar :)

## Configurações e Docker Compose
- **postgres**: PostgreSQL 15 na porta 5433 do host (evita conflito caso já exista um PostgreSQL local rodando em 5432).
Obs.: Monta o script init.sql para criação inicial da tabela e dados.
- **zookeeper**: Serviço necessário para coordenação do Kafka, rodando na porta 2181.
- **kafka**: Inicializa o broker do Apache Kafka (porta interna 9092, exposta no host em 29092).
Configurado para se comunicar com o Zookeeper e permitir acesso do backend via kafka:29092.
- **kafdrop**: Interface web de administração do Kafka, acessível em http://localhost:9000.
Permite visualizar tópicos, mensagens e consumidores em tempo real.
- **backend**: Realiza o Build e sobe a aplicação Spring Boot na porta 8080.

Obs.: Caso precise apontar para seu banco localhost ao inves do banco docker, mude de-para:
- de: jdbc:postgresql://host.docker.internal:**5433**/consultacredito
- para: jdbc:postgresql://host.docker.internal:**5432**/consultacredito
