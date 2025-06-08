# 🚀 Desafio: CRUD de Clientes

Este projeto consiste em uma API REST construída com **Spring Boot**, utilizando o padrão de camadas, tratamento de exceções, validações e banco de dados em memória H2.

---

### 📌 Especificações do Projeto

A API implementa **cinco operações básicas**:

- ✅ Busca paginada de clientes  
- ✅ Busca de cliente por ID  
- ✅ Inserção de novo cliente  
- ✅ Atualização de cliente existente  
- ✅ Deleção de cliente

---

### 🧾 Entidade `Client`

O cliente possui os seguintes campos:

- `id` (Long)
- `name` (String)
- `cpf` (String)
- `income` (Double)
- `birthDate` (LocalDate)
- `children` (Integer)

---

### ⚙️ Tecnologias Utilizadas

- Java 21
- Spring Boot
- Spring Data JPA
- H2 Database
- Bean Validation
- Maven

---

### 🛑 Tratamento de Exceções

- `404 Not Found`: recurso com ID não encontrado (GET por ID, PUT e DELETE)
- `422 Unprocessable Entity`: erro de validação com mensagens específicas por campo inválido

---

### 🔁 Endpoints

#### 🔎 GET /clients?page=0&size=6&sort=name
Busca paginada de clientes.

#### 🔎 GET /clients/{id}
Busca cliente por ID.

#### ➕ POST /clients
Insere novo cliente.  
**Exemplo:**
```json
{
  "name": "Maria Silva",
  "cpf": "12345678901",
  "income": 6500.0,
  "birthDate": "1994-07-20",
  "children": 2
}
```
#### 📝 PUT /clients/{id}
Atualiza um cliente existente.

**Corpo da requisição (exemplo):**
```json
{
  "name": "José da Silva",
  "cpf": "98765432101",
  "income": 700.0,
  "birthDate": "1995-02-22",
  "children": 3
}
```

#### ❌ DELETE /clients/{id}
Remove um cliente existente pelo ID.

