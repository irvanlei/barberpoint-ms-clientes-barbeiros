# 👥 BarberPoint - Microserviço de Clientes e Barbeiros

## Descrição

Microserviço responsável por gerenciar clientes, barbeiros e serviços em uma plataforma de agendamento de barbearias. Implementado com **Spring Boot 3.3**, **Azure SQL Database** e JPA, seguindo os princípios de **Clean Architecture** e **Vertical Slice**.

## Arquitetura

```
src/main/java/com/barberpoint/users/
├── domain/
│   └── entities/           # Entidades (Cliente, Barbeiro, Servico)
├── application/
│   ├── usecases/          # Casos de uso (CRUD)
│   └── dtos/              # Data Transfer Objects
├── infrastructure/
│   └── repository/        # JPA repositories
└── api/
    └── controllers/       # REST endpoints
```

### Clean Architecture
- **Domain Layer**: Modelos e regras de negócio
- **Application Layer**: Serviços de aplicação e casos de uso
- **Infrastructure Layer**: Implementação JPA com Azure SQL
- **API Layer**: REST controllers

## Tecnologias

- **Java 22**
- **Spring Boot 3.3.0**
- **Spring Data JPA**
- **Azure SQL Database**
- **Lombok**
- **MapStruct**
- **SpringDoc OpenAPI** (Swagger)
- **JUnit 5** + **Mockito**
- **H2 Database** (testes)

## Pré-requisitos

- Java 22 ou superior
- Maven 3.8+
- Azure SQL Database ou SQL Server local

## Como Rodar Localmente

### 1. Com Docker e SQL Server

```bash
# Clone o repositório
git clone https://github.com/seu-usuario/barberpoint-ms-clientes-barbeiros.git
cd barberpoint-ms-clientes-barbeiros

# Inicie o SQL Server
docker run -e "ACCEPT_EULA=Y" -e "SA_PASSWORD=YourPassword123!" \
  -p 1433:1433 --name sqlserver -d mcr.microsoft.com/mssql/server:2022-latest

# Aguarde 30 segundos e execute
mvn clean spring-boot:run
```

### 2. Com Banco de Dados Local

```bash
# Atualize application.properties com suas credenciais
# Compile e execute
git clone https://github.com/seu-usuario/barberpoint-ms-clientes-barbeiros.git
cd barberpoint-ms-clientes-barbeiros

mvn clean spring-boot:run
```

## Endpoints Disponíveis

### Swagger UI
- **URL**: `http://localhost:8082/api/swagger-ui.html`

### Clientes
- `POST /api/clientes` — Criar cliente
- `GET /api/clientes/{id}` — Obter cliente
- `GET /api/clientes` — Listar clientes
- `PUT /api/clientes/{id}` — Atualizar cliente
- `DELETE /api/clientes/{id}` — Deletar cliente

### Barbeiros
- `POST /api/barbeiros` — Criar barbeiro
- `GET /api/barbeiros/{id}` — Obter barbeiro
- `GET /api/barbeiros` — Listar barbeiros
- `PUT /api/barbeiros/{id}` — Atualizar barbeiro
- `DELETE /api/barbeiros/{id}` — Deletar barbeiro

### Serviços
- `POST /api/servicos` — Criar serviço
- `GET /api/servicos/{id}` — Obter serviço
- `GET /api/servicos` — Listar serviços
- `PUT /api/servicos/{id}` — Atualizar serviço
- `DELETE /api/servicos/{id}` — Deletar serviço

## Testes

```bash
# Executar testes
mvn clean test

# Com cobertura
mvn clean test jacoco:report
```

## Build e Deploy

### Build Docker

```bash
docker build -t seu-usuario/barberpoint-ms-clientes-barbeiros:v1.0 .
docker push seu-usuario/barberpoint-ms-clientes-barbeiros:v1.0
```

## Variáveis de Ambiente

```properties
# Banco de Dados
SPRING_DATASOURCE_URL=jdbc:sqlserver://localhost:1433;databaseName=barberpoint_users
SPRING_DATASOURCE_USERNAME=sa
SPRING_DATASOURCE_PASSWORD=YourPassword123!

# Server
SERVER_PORT=8082
```

## Estrutura de Dados (Azure SQL)

### Tabela: `clientes`
```sql
CREATE TABLE clientes (
  id BIGINT PRIMARY KEY IDENTITY,
  nome VARCHAR(100) NOT NULL,
  sobrenome VARCHAR(100),
  email VARCHAR(100) UNIQUE,
  telefone VARCHAR(20),
  data_criacao DATETIME DEFAULT GETDATE()
);
```

### Tabela: `barbeiros`
```sql
CREATE TABLE barbeiros (
  id BIGINT PRIMARY KEY IDENTITY,
  nome VARCHAR(100) NOT NULL,
  sobrenome VARCHAR(100),
  email VARCHAR(100) UNIQUE,
  telefone VARCHAR(20),
  data_criacao DATETIME DEFAULT GETDATE()
);
```

### Tabela: `servicos`
```sql
CREATE TABLE servicos (
  id BIGINT PRIMARY KEY IDENTITY,
  nome VARCHAR(100) NOT NULL,
  preco DECIMAL(10,2),
  duracao INT,
  data_criacao DATETIME DEFAULT GETDATE()
);
```

## CI/CD

- **GitHub Actions**: Automação de testes
- **Docker Hub**: Deploy de imagens
- **Azure SQL**: Database em nuvem

---

## Autores
- Irvanlei de Abreu
- João Yutaka
- Fellipe
- Nelson
- Allan

**Mantido por**: BarberPoint Team  
**Última atualização**: 27 de abril de 2026
