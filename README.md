# MentalCheck API - Sistema de Monitoramento de Bem-Estar Mental

Sistema desenvolvido como parte da Global Solution da FIAP para monitoramento de bem-estar mental em ambientes de trabalho híbrido.

## 🚀 Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.3.5**
- **Spring Data JPA** - Persistência com JPA/Hibernate
- **Spring Security** - Autenticação JWT
- **Bean Validation** - Validação de dados
- **Oracle Database** - Banco de dados relacional
- **Swagger/OpenAPI** - Documentação da API
- **Maven** - Gerenciamento de dependências

## 📋 Funcionalidades

- ✅ API REST com arquitetura em camadas (Controller → Service → Repository)
- ✅ Autenticação e autorização com JWT
- ✅ CRUD completo com paginação, ordenação e filtros
- ✅ Validações com Bean Validation (@NotNull, @Min, @Max, @Size)
- ✅ Integração com Stored Procedures Oracle
- ✅ Tratamento global de exceções
- ✅ DTOs para entrada e saída de dados
- ✅ Documentação automática com Swagger

## 🔧 Pré-requisitos

- Java 17 ou superior
- Maven 3.8+
- Banco de dados Oracle configurado e populado

## ⚙️ Configuração e Execução

### 1. Clone o repositório
```bash
git clone <url-do-repositorio>
cd mentalcheck-backend
```

### 2. Configure as variáveis de ambiente

Crie arquivo `.env` na raiz do projeto:
```env
DB_URL=jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL
DB_USERNAME=rm560384
DB_PASSWORD=<sua-senha>
JWT_SECRET=<sua-chave-secreta>
JWT_EXPIRATION=86400000
```

### 3. Compile e execute
```bash
mvn clean install
mvn spring-boot:run
```

### 4. Acesse a documentação
```
http://localhost:8080/api/swagger-ui.html
```

## 🔐 Autenticação

A API utiliza JWT (JSON Web Token). Para acessar endpoints protegidos:

1. **Obtenha o token:**
```bash
POST /api/auth/login
{
  "email": "carlos.silva@empresa.com",
  "senha": "senha123"
}
```

2. **Use o token:**
   - No Swagger: clique em "Authorize" e cole o token
   - Ou adicione header: `Authorization: Bearer {seu-token}`

## 📚 Endpoints Principais

### Autenticação
- `POST /auth/login` - Autenticar e obter token

### Usuários
- `GET /usuarios` - Listar usuários (paginado)
- `GET /usuarios/{id}` - Buscar por ID
- `POST /usuarios` - Criar usuário
- `POST /usuarios/procedure` ⭐ - Criar via stored procedure
- `PUT /usuarios/{id}` - Atualizar
- `DELETE /usuarios/{id}` - Deletar

### Check-ins
- `GET /checkins` - Listar (paginado)
- `GET /checkins/usuario/{id}` - Listar por usuário
- `POST /checkins/procedure` ⭐ - Criar via stored procedure
- `DELETE /checkins/{id}` - Deletar

### Dicas
- `GET /dicas` - Listar (paginado)
- `GET /dicas/categoria/{cat}` - Buscar por categoria
- `POST /dicas/procedure` ⭐ - Criar via stored procedure

⭐ = Endpoints que demonstram integração com Stored Procedures Oracle

## 🏗️ Arquitetura
```
src/main/java/com/fiap/mentalcheck/
├── controller/      # Controllers REST
├── service/         # Lógica de negócio
├── repository/      # Acesso a dados (JPA)
├── entity/          # Entidades JPA
├── dto/             # Data Transfer Objects
├── mapper/          # Conversão Entity ↔ DTO
├── security/        # JWT e configurações de segurança
└── exception/       # Tratamento global de exceções
```

## 🎯 Demonstração de Requisitos

### 1. API REST (40 pts)
✅ CRUD completo com métodos HTTP corretos
✅ Paginação e filtros implementados
✅ Respostas HTTP apropriadas (200, 201, 400, 404, 500)

### 2. Persistência com JPA (20 pts)
✅ Mapeamento completo de entidades
✅ Relacionamentos (@ManyToOne, @OneToMany)
✅ Repositories Spring Data JPA

### 3. Bean Validation (10 pts)
✅ Validações em DTOs (@NotNull, @Min, @Max, @Size)
✅ Tratamento de erros de validação

### 4. Stored Procedures (10 pts)
✅ Integração com procedures Oracle via JPA
✅ Endpoints `/procedure` demonstram chamadas diretas

### 5. Swagger (10 pts)
✅ Documentação automática completa
✅ Testável via interface web

### 6. Deploy em Nuvem (10 pts)
✅ Aplicação deployada no Azure
✅ Vídeo demonstrando funcionamento

## 👥 Equipe

- RM560384 - Alexis Rondo
- [Membro 2]
- [Membro 3]

## 📹 Vídeo Demonstração

[Link do vídeo no YouTube demonstrando a aplicação funcionando]