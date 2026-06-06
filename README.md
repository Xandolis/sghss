# 🏥 SGHSS - Sistema de Gestão Hospitalar

[![Java Version](https://img.shields.io/badge/Java-17-blue.svg)](https://www.oracle.com/java/technologies/javase/jdk17-archive.html)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.5-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

Sistema completo de gestão hospitalar desenvolvido em Spring Boot com APIs RESTful para gerenciar pacientes, profissionais, consultas, internações, prontuários e agendas.

--- 

## 📋 Índice

- [Características](#caracteristicas)
- [Requisitos](#requisitos)
- [Instalação](#instalacao)
- [Configuração](#configuracao)
- [Como Executar](#como-executar)
- [Estrutura da API](#estrutura-da-api)
- [Autenticação](#autenticacao)
- [Exemplos de Uso](#exemplos-de-uso)
- [Modelos de Dados](#modelos-de-dados)
- [Tratamento de Erros](#tratamento-de-erros)

---

<a id="caracteristicas"></a>
## ✨ Características

- ✅ **Autenticação JWT** - Sistema seguro com tokens JWT
- ✅ **Gestão de Pacientes** - Registro e perfil de pacientes
- ✅ **Gestão de Profissionais** - Cadastro de médicos e profissionais
- ✅ **Agendamento de Consultas** - Sistema completo de agendas
- ✅ **Gestão de Internações** - Controle de internações hospitalares
- ✅ **Prontuários Eletrônicos** - Registro digital de pacientes
- ✅ **Gestão de Leitos** - Administração de leitos e quartos
- ✅ **Grades de Trabalho** - Agendas de profissionais
- ✅ **Bloqueios de Agendamento** - Controle de disponibilidade
- ✅ **Segurança** - Spring Security com controle de roles

---

<a id="requisitos"></a>
## 📦 Requisitos

### Mínimo
- **Java 17** ou superior
- **MySQL 8.0** ou superior
- **Maven 3.8.0** ou superior

### Recomendado
- **IntelliJ IDEA** ou **VS Code**
- **Postman** ou **Insomnia** (para testes de API)
- **Docker** (opcional, para executar MySQL)

---

<a id="instalacao"></a>
## 🚀 Instalação

### 1. Clone o Repositório

```bash
git clone https://github.com/seu-usuario/sghss.git
cd sghss
```

### 2. Instale as Dependências

```bash
./mvnw clean install
```

**Windows:**
```cmd
mvnw.cmd clean install
```

### 3. Configure o Banco de Dados

#### Opção A: MySQL Local

```bash
# Crie o banco de dados
mysql -u root -p

CREATE DATABASE sghss;
CREATE USER 'sghss_user'@'localhost' IDENTIFIED BY 'senha_segura';
GRANT ALL PRIVILEGES ON sghss.* TO 'sghss_user'@'localhost';
FLUSH PRIVILEGES;
```

#### Opção B: Docker

```bash
docker run --name mysql-sghss \
  -e MYSQL_ROOT_PASSWORD=root_password \
  -e MYSQL_DATABASE=sghss \
  -e MYSQL_USER=sghss_user \
  -e MYSQL_PASSWORD=senha_segura \
  -p 3306:3306 \
  -d mysql:8.0
```

---

<a id="configuracao"></a>
## ⚙️ Configuração

### 1. Atualize o arquivo `application.properties`

Localize em: `src/main/resources/application.properties`

```properties
# Banco de Dados
spring.datasource.url=jdbc:mysql://localhost:3306/sghss
spring.datasource.username=sghss_user
spring.datasource.password=senha_segura

# JPA/Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

# Configuração da Aplicação
server.port=8080
spring.application.name=sghss
```

### 2. Variáveis de Ambiente (Opcional)

Crie um arquivo `.env` na raiz do projeto:

```env
DB_URL=jdbc:mysql://localhost:3306/sghss
DB_USERNAME=sghss_user
DB_PASSWORD=senha_segura
JWT_SECRET=sua_chave_secreta_muito_longa_e_segura_aqui
JWT_EXPIRATION=86400000
```

---

<a id="como-executar"></a>
## 🏃 Como Executar

### Opção 1: Usando Maven

```bash
./mvnw spring-boot:run
```

**Windows:**
```cmd
mvnw.cmd spring-boot:run
```

### Opção 2: Build JAR e Executar

```bash
# Build
./mvnw clean package

# Executar
java -jar target/sghss-0.0.1-SNAPSHOT.jar
```

### Opção 3: Usando IDE

1. Abra a pasta do projeto em sua IDE
2. Localize `SghssApplication.java`
3. Clique com botão direito → "Run" ou pressione `Shift + F10` (IntelliJ)

### Verifique se está rodando

A aplicação estará disponível em: `http://localhost:8080`

```bash
curl http://localhost:8080/actuator/health
```

---

<a id="estrutura-da-api"></a>
## 📡 Estrutura da API

### Base URL
```
http://localhost:8080
```

### Módulos Principais

| Módulo | Endpoint | Descrição |
|--------|----------|-----------|
| **Autenticação** | `/auth` | Login e registro de usuários |
| **Pacientes** | `/pacientes` | Gestão de pacientes |
| **Profissionais** | `/admin/profissionais` | Gestão de profissionais |
| **Consultas** | `/consultas` | Agendamento e gestão de consultas |
| **Prontuários** | `/prontuarios` | Registros de prontuários |
| **Agenda** | `/agenda` | Grades de trabalho e bloqueios |
| **Hospital** | `/admin/hospital` | Gestão de quartos, leitos e internações |

---

<a id="autenticacao"></a>
## 🔐 Autenticação

### Sistema JWT

O SGHSS utiliza **JSON Web Tokens (JWT)** para autenticação e autorização.

#### 1. Registre um Usuário

```bash
curl -X POST http://localhost:8080/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "João Silva",
    "email": "joao@hospital.com",
    "senha": "senha_segura_123",
    "tipo": "PACIENTE"
  }'
```

#### 2. Faça Login

```bash
curl -X POST http://localhost:8080/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "joao@hospital.com",
    "senha": "senha_segura_123"
  }'
```

**Resposta:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJqb2FvQGhvc3BpdGFsLmNvbSIsImlhdCI6MTYyNDY5NDgwMH0...",
  "expiresIn": 86400
}
```

#### 3. Use o Token em Requisições

Adicione o header `Authorization` com o token:

```bash
curl -X GET http://localhost:8080/pacientes/me \
  -H "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
```

### Tipos de Usuário (Roles)

- `PACIENTE` - Acesso a dados próprios
- `PROFISSIONAL` - Acesso a consultas e agenda
- `ADMINISTRADOR` - Acesso completo ao sistema

---

## 💡 Exemplos de Uso

### 1. Autenticação

#### Registrar Novo Paciente

```bash
curl -X POST http://localhost:8080/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "Maria Santos",
    "email": "maria@hospital.com",
    "senha": "senha_123",
    "tipo": "PACIENTE"
  }'
```

#### Fazer Login

```bash
curl -X POST http://localhost:8080/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "maria@hospital.com",
    "senha": "senha_123"
  }'
```

**Salve o token para usar nos próximos exemplos:**
```bash
TOKEN="seu_token_aqui"
```

### 2. Gestão de Pacientes

#### Criar Novo Paciente

```bash
curl -X POST http://localhost:8080/pacientes \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer $TOKEN" \
  -d '{
    "nome": "Carlos Silva",
    "email": "carlos@hospital.com",
    "dataNascimento": "1990-05-15",
    "endereco": "Rua Principal, 123",
    "numeroUtente": "UT123456"
  }'
```

#### Obter Perfil do Paciente Autenticado

```bash
curl -X GET http://localhost:8080/pacientes/me \
  -H "Authorization: Bearer $TOKEN"
```

#### Listar Todos os Pacientes

```bash
curl -X GET http://localhost:8080/pacientes \
  -H "Authorization: Bearer $TOKEN"
```

#### Atualizar Dados do Paciente

```bash
curl -X PUT http://localhost:8080/pacientes/1 \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer $TOKEN" \
  -d '{
    "nome": "Carlos Silva Atualizado",
    "telefone": "912345678"
  }'
```

#### Deletar Paciente

```bash
curl -X DELETE http://localhost:8080/pacientes/1 \
  -H "Authorization: Bearer $TOKEN"
```

### 3. Gestão de Profissionais

#### Registrar Novo Profissional

```bash
curl -X POST http://localhost:8080/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "Dr. Pedro Oliveira",
    "email": "pedro@hospital.com",
    "senha": "senha_medico_123",
    "tipo": "PROFISSIONAL"
  }'
```

#### Criar Profissional com Especialidade

```bash
curl -X POST http://localhost:8080/profissionais \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer $TOKEN" \
  -d '{
    "nome": "Dr. Ana Silva",
    "email": "ana@hospital.com",
    "especialidade": "Cardiologia",
    "numeroRegistro": "CRM123456"
  }'
```

#### Listar Profissionais

```bash
curl -X GET http://localhost:8080/profissionais \
  -H "Authorization: Bearer $TOKEN"
```

### 4. Agendamento de Consultas

#### Agendar Consulta

```bash
curl -X POST http://localhost:8080/consultas \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer $TOKEN" \
  -d '{
    "pacienteId": 1,
    "profissionalId": 1,
    "dataConsulta": "2026-05-20",
    "horaConsulta": "14:30",
    "motivo": "Consulta de rotina"
  }'
```

#### Listar Consultas do Paciente

```bash
curl -X GET http://localhost:8080/consultas/paciente/1 \
  -H "Authorization: Bearer $TOKEN"
```

#### Atualizar Consulta

```bash
curl -X PUT http://localhost:8080/consultas/1 \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer $TOKEN" \
  -d '{
    "dataConsulta": "2026-05-21",
    "horaConsulta": "15:00",
    "motivo": "Consulta de acompanhamento"
  }'
```

#### Cancelar Consulta

```bash
curl -X DELETE http://localhost:8080/consultas/1 \
  -H "Authorization: Bearer $TOKEN"
```

### 5. Gestão de Agendas

#### Criar Grade de Trabalho

```bash
curl -X POST http://localhost:8080/agenda/grade \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer $TOKEN" \
  -d '{
    "profissionalId": 1,
    "dataInicio": "2026-05-20",
    "dataFim": "2026-05-20",
    "horaInicio": "08:00",
    "horaFim": "17:00"
  }'
```

#### Criar Bloqueio de Agendamento

```bash
curl -X POST http://localhost:8080/agenda/bloqueio \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer $TOKEN" \
  -d '{
    "profissionalId": 1,
    "dataInicio": "2026-06-01",
    "dataFim": "2026-06-07",
    "motivo": "Férias"
  }'
```

#### Verificar Disponibilidade

```bash
curl -X GET "http://localhost:8080/agenda/disponibilidade/1/2026-05-20" \
  -H "Authorization: Bearer $TOKEN"
```

### 6. Gestão de Internações

#### Registrar Internação

```bash
curl -X POST http://localhost:8080/internacoes \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer $TOKEN" \
  -d '{
    "pacienteId": 1,
    "leitoId": 1,
    "dataInternacao": "2026-05-15",
    "motivo": "Cirurgia programada"
  }'
```

#### Listar Internações

```bash
curl -X GET http://localhost:8080/internacoes \
  -H "Authorization: Bearer $TOKEN"
```

#### Alta Hospitalar

```bash
curl -X PUT http://localhost:8080/internacoes/1/alta \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer $TOKEN" \
  -d '{
    "dataAlta": "2026-05-20",
    "observacoes": "Recuperação satisfatória"
  }'
```

### 7. Gestão de Prontuários

#### Criar Prontuário

```bash
curl -X POST http://localhost:8080/prontuarios \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer $TOKEN" \
  -d '{
    "pacienteId": 1,
    "consultaId": 1,
    "diagnostico": "Hipertensão arterial",
    "prescricao": "Medicação: Losartana 50mg",
    "observacoes": "Acompanhamento necessário"
  }'
```

#### Obter Prontuário do Paciente

```bash
curl -X GET http://localhost:8080/prontuarios/paciente/1 \
  -H "Authorization: Bearer $TOKEN"
```

---

## 📊 Modelos de Dados

### Usuário (User)

```json
{
  "id": 1,
  "nome": "João Silva",
  "email": "joao@hospital.com",
  "tipo": "PACIENTE",
  "dataCriacao": "2026-04-15T10:30:00",
  "ativo": true
}
```

### Paciente

```json
{
  "id": 1,
  "nome": "Maria Santos",
  "email": "maria@hospital.com",
  "dataNascimento": "1990-05-15",
  "endereco": "Rua Principal, 456",
  "numeroUtente": "UT789456",
  "telefone": "912345678",
  "genero": "FEMININO",
  "dataRegistro": "2026-04-15T10:30:00"
}
```

### Profissional

```json
{
  "id": 1,
  "nome": "Dr. Pedro Oliveira",
  "email": "pedro@hospital.com",
  "especialidade": "Cardiologia",
  "numeroRegistro": "CRM123456",
  "telefone": "987654321",
  "dataRegistro": "2026-04-15T10:30:00"
}
```

### Consulta

```json
{
  "id": 1,
  "paciente": { "id": 1, "nome": "Maria Santos" },
  "profissional": { "id": 1, "nome": "Dr. Pedro Oliveira" },
  "dataConsulta": "2026-05-20",
  "horaConsulta": "14:30",
  "motivo": "Consulta de rotina",
  "status": "AGENDADA",
  "dataCriacao": "2026-04-15T10:30:00"
}
```

### Internação

```json
{
  "id": 1,
  "paciente": { "id": 1, "nome": "Maria Santos" },
  "leito": { "id": 1, "numero": "101" },
  "dataInternacao": "2026-05-15",
  "dataAlta": null,
  "motivo": "Cirurgia programada",
  "status": "ATIVA"
}
```

---

## ⚠️ Tratamento de Erros

### Códigos de Status HTTP

| Código | Significado |
|--------|-------------|
| `200` | OK - Requisição bem-sucedida |
| `201` | Created - Recurso criado com sucesso |
| `400` | Bad Request - Erro na requisição |
| `401` | Unauthorized - Não autenticado |
| `403` | Forbidden - Não autorizado |
| `404` | Not Found - Recurso não encontrado |
| `500` | Internal Server Error - Erro do servidor |

### Formato de Erro

```json
{
  "timestamp": "2026-04-15T10:30:00",
  "status": 400,
  "error": "Bad Request",
  "message": "Email já está em uso",
  "path": "/auth/register"
}
```

### Principais Erros

#### Erro de Autenticação

```json
{
  "status": 401,
  "message": "Email ou senha inválidos"
}
```

#### Erro de Autorização

```json
{
  "status": 403,
  "message": "Acesso negado. Você não tem permissão para acessar este recurso."
}
```

#### Recurso Não Encontrado

```json
{
  "status": 404,
  "message": "Paciente com ID 999 não encontrado"
}
```

---

## 🧪 Testes

### Executar Testes

```bash
./mvnw test
```

**Windows:**
```cmd
mvnw.cmd test
```

### Teste com Coverage

```bash
./mvnw test jacoco:report
```

---

## 🐛 Troubleshooting

### Erro: "Connection refused"

**Problema:** Não consegue conectar ao banco de dados

**Solução:**
1. Verifique se MySQL está rodando
2. Verifique as credenciais em `application.properties`
3. Verifique se o banco de dados `sghss` existe

```bash
mysql -u root -p -e "SHOW DATABASES;"
```

### Erro: "Token inválido"

**Problema:** Token JWT expirado ou inválido

**Solução:**
1. Faça login novamente para obter um novo token
2. Verifique se o token está sendo enviado corretamente no header Authorization

### Erro: "Port 8080 already in use"

**Problema:** Outra aplicação está usando a porta 8080

**Solução:**
Mude a porta em `application.properties`:

```properties
server.port=8081
```

### Erro: "Java version not compatible"

**Problema:** Você tem Java 11 mas precisa de Java 17

**Solução:**
```bash
# Verifique sua versão
java -version

# Baixe Java 17
# https://www.oracle.com/java/technologies/javase/jdk17-archive.html
```

---

## 📚 Documentação Adicional

- [API_DOCUMENTATION.md](API_DOCUMENTATION.md) - Documentação completa dos endpoints
- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Spring Security](https://spring.io/projects/spring-security)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)

---

## 🤝 Contribuindo

Contribuições são bem-vindas! Por favor:

1. Fork o projeto
2. Crie uma branch para sua feature (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request

---

## 📞 Suporte

Para dúvidas ou problemas:

1. Verifique a [Documentação da API](API_DOCUMENTATION.md)
2. Abra uma issue no GitHub

---

## 🎯 Roadmap Futuro

- [ ] Integração com sistemas de pagamento
- [ ] Dashboard de analytics
- [ ] Aplicativo mobile
- [ ] Integração com WhatsApp
- [ ] Sistema de notificações por email
- [ ] Relatórios avançados
- [ ] Docker compose para setup completo

---

**Desenvolvido com ❤️ em Spring Boot**
