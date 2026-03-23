# Loja API

API REST de uma loja simples desenvolvida com Java e Spring Boot.

## Tecnologias
- Java 17
- Spring Boot 3.2
- Spring Data JPA
- Banco H2 (in-memory)

## Como rodar
```bash
mvn spring-boot:run
```
API disponível em: `http://localhost:8080`

## Endpoints
| Método | URL | Descrição |
|--------|-----|-----------|
| POST | /clientes | Criar cliente |
| GET | /clientes | Listar clientes |
| GET | /clientes/{id} | Buscar cliente |
| DELETE | /clientes/{id} | Deletar cliente |
| POST | /produtos | Criar produto |
| GET | /produtos | Listar produtos |
| GET | /produtos/{id} | Buscar produto |
| DELETE | /produtos/{id} | Deletar produto |
| POST | /pedidos | Criar pedido |
| GET | /pedidos | Listar pedidos |
| GET | /pedidos/{id} | Buscar pedido |
| DELETE | /pedidos/{id} | Deletar pedido |
