# 🛒 Loja API — Spring Boot REST

## Como rodar

### Pré-requisitos
- Java 17+
- Maven 3.8+

### Executar
```bash
mvn spring-boot:run
```
A API sobe em: `http://localhost:8080`
Console H2: `http://localhost:8080/h2-console` (JDBC URL: `jdbc:h2:mem:lojadb`, user: `sa`, sem senha)

---

## 📦 Estrutura do Projeto

```
src/main/java/com/loja/api/
├── LojaApiApplication.java         ← Ponto de entrada
├── model/
│   ├── Produto.java                ← Entidade JPA
│   ├── Cliente.java
│   └── Pedido.java
├── repository/
│   ├── ProdutoRepository.java      ← Extende JpaRepository
│   ├── ClienteRepository.java
│   └── PedidoRepository.java
├── service/
│   ├── ProdutoService.java         ← Regras de negócio
│   ├── ClienteService.java
│   └── PedidoService.java
├── controller/
│   ├── ProdutoController.java      ← Endpoints REST
│   ├── ClienteController.java
│   └── PedidoController.java
└── exception/
    ├── RecursoNaoEncontradoException.java
    └── GlobalExceptionHandler.java
```

---

## 🔌 Endpoints — Clientes

| Método | URL | Descrição | Status |
|--------|-----|-----------|--------|
| POST   | `/clientes` | Criar cliente | 201 |
| GET    | `/clientes` | Listar todos | 200 |
| GET    | `/clientes/{id}` | Buscar por ID | 200 |
| PUT    | `/clientes/{id}` | Atualizar | 200 |
| DELETE | `/clientes/{id}` | Deletar | 204 |

### POST /clientes — Body
```json
{
  "nome": "Maria Silva",
  "clienteDesde": "2024-01-15"
}
```

---

## 🔌 Endpoints — Produtos

| Método | URL | Descrição | Status |
|--------|-----|-----------|--------|
| POST   | `/produtos` | Criar produto | 201 |
| GET    | `/produtos` | Listar todos | 200 |
| GET    | `/produtos/{id}` | Buscar por ID | 200 |
| PUT    | `/produtos/{id}` | Atualizar | 200 |
| DELETE | `/produtos/{id}` | Deletar | 204 |

### POST /produtos — Body
```json
{
  "nome": "Notebook Dell",
  "preco": 3499.90,
  "estoque": true
}
```

---

## 🔌 Endpoints — Pedidos

| Método | URL | Descrição | Status |
|--------|-----|-----------|--------|
| POST   | `/pedidos` | Criar pedido | 201 |
| GET    | `/pedidos` | Listar todos | 200 |
| GET    | `/pedidos/{id}` | Buscar por ID | 200 |
| PUT    | `/pedidos/{id}` | Atualizar | 200 |
| DELETE | `/pedidos/{id}` | Deletar | 204 |

### POST /pedidos — Body
```json
{
  "clienteId": 1,
  "produtoId": 1,
  "quantidade": 2
}
```
> ⚠️ O pedido valida se o clienteId e produtoId existem. Crie cliente e produto antes!

---

## 🧪 Sequência de Testes no Postman

### 1. Criar Cliente
- **POST** `http://localhost:8080/clientes`
- Headers: `Content-Type: application/json`
- Body:
```json
{ "nome": "João Souza", "clienteDesde": "2023-06-01" }
```

### 2. Criar Produto
- **POST** `http://localhost:8080/produtos`
- Body:
```json
{ "nome": "Mouse Gamer", "preco": 149.90, "estoque": true }
```

### 3. Criar Pedido
- **POST** `http://localhost:8080/pedidos`
- Body:
```json
{ "clienteId": 1, "produtoId": 1, "quantidade": 3 }
```

### 4. Listar todos os clientes
- **GET** `http://localhost:8080/clientes`

### 5. Listar todos os produtos
- **GET** `http://localhost:8080/produtos`

### 6. Listar todos os pedidos
- **GET** `http://localhost:8080/pedidos`

### 7. Buscar cliente por ID
- **GET** `http://localhost:8080/clientes/1`

### 8. Buscar produto por ID
- **GET** `http://localhost:8080/produtos/1`

### 9. Buscar pedido por ID
- **GET** `http://localhost:8080/pedidos/1`

### 10. Deletar
- **DELETE** `http://localhost:8080/clientes/1`
- Resposta: `204 No Content`

---

## ⚠️ Respostas de Erro

### 404 — Recurso não encontrado
```json
{ "erro": "Produto não encontrado com id: 99" }
```

### 400 — Validação falhou
```json
{
  "nome": "Nome é obrigatório",
  "preco": "Preço deve ser maior que zero"
}
```
