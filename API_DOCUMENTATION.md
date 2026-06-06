# 📚 Documentação da API SGHSS - Sistema de Gestão Hospitalar

## Base URL

```
http://localhost:8080
```

## Visão geral

Este projeto é uma API RESTful em Spring Boot para gestão hospitalar. O sistema usa autenticação JWT e permite operações sobre pacientes, profissionais, consultas, prontuários, agenda e dados hospitalares.

> Observação: a segurança é aplicada por filtro JWT no backend. O caminho das rotas não contém prefixo `/api` no código atual.

---

## Módulo de Autenticação (/auth)

### 1. Registrar usuário

**Endpoint:** POST /auth/register

**Descrição:** Registra um novo usuário (paciente, profissional ou administrador).

**Proteção:** Pública

**Corpo (Body):**
```json
{
  "nome": "João Silva",
  "email": "joao@hospital.com",
  "senha": "senha_segura_123",
  "tipo": "PACIENTE"
}
```

### 2. Login

**Endpoint:** POST /auth/login

**Descrição:** Autentica o usuário e retorna token JWT.

**Proteção:** Pública

**Corpo (Body):**
```json
{
  "email": "joao@hospital.com",
  "senha": "senha_segura_123"
}
```

**Resposta:**
```json
"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
```

---

## Módulo de Pacientes (/pacientes)

### 1. Criar paciente

**Endpoint:** POST /pacientes

**Descrição:** Cria um novo registro de paciente.

**Proteção:** JWT

**Corpo (Body):**
```json
{
  "nome": "Maria Santos",
  "email": "maria.santos@email.com",
  "senha": "senha_123",
  "dataNascimento": "1985-06-20",
  "endereco": "Avenida Principal, 456",
  "numeroUtente": "UT789456"
}
```

### 2. Obter paciente por ID

**Endpoint:** GET /pacientes/{id}

**Descrição:** Retorna dados de um paciente específico.

**Proteção:** JWT

### 3. Listar todos os pacientes

**Endpoint:** GET /pacientes

**Descrição:** Retorna lista de todos os pacientes.

**Proteção:** JWT

### 4. Atualizar paciente

**Endpoint:** PUT /pacientes/{id}

**Descrição:** Atualiza os dados de um paciente.

**Proteção:** JWT

**Corpo (Body):**
```json
{
  "nome": "Maria Santos Silva",
  "dataNascimento": "1985-06-20",
  "endereco": "Avenida Principal, 789",
  "telefone": "912345678"
}
```

### 5. Deletar paciente

**Endpoint:** DELETE /pacientes/{id}

**Descrição:** Remove um paciente.

**Proteção:** JWT

---

## Módulo de Profissionais (/admin/profissionais)

### 1. Criar profissional

**Endpoint:** POST /admin/profissionais

**Descrição:** Cria um novo profissional.

**Proteção:** JWT

**Corpo (Body):**
```json
{
  "nome": "Dr. Pedro Carvalho",
  "email": "dr.pedro@hospital.com",
  "senha": "senha_segura",
  "especialidade": "Cardiologia",
  "crm": "CRM2024001"
}
```

### 2. Obter profissional por ID

**Endpoint:** GET /admin/profissionais/{id}

**Descrição:** Retorna dados de um profissional.

**Proteção:** JWT

### 3. Listar todos os profissionais

**Endpoint:** GET /admin/profissionais

**Descrição:** Retorna todos os profissionais.

**Proteção:** JWT

### 4. Listar por especialidade

**Endpoint:** GET /admin/profissionais/especialidade/{especialidade}

**Descrição:** Retorna profissionais filtrados por especialidade.

**Proteção:** JWT

### 5. Atualizar profissional

**Endpoint:** PUT /admin/profissionais/{id}

**Descrição:** Atualiza um profissional.

**Proteção:** JWT

### 6. Deletar profissional

**Endpoint:** DELETE /admin/profissionais/{id}

**Descrição:** Deleta um profissional.

**Proteção:** JWT

---

## Módulo de Consultas (/consultas)

### 1. Agendar consulta

**Endpoint:** POST /consultas

**Descrição:** Agenda uma nova consulta.

**Proteção:** JWT

**Corpo (Body):**
```json
{
  "pacienteId": 1,
  "profissionalId": 2,
  "dataHora": "2026-06-15T14:00:00",
  "especialidade": "Cardiologia"
}
```

### 2. Obter consulta por ID

**Endpoint:** GET /consultas/{id}

**Descrição:** Retorna uma consulta específica.

**Proteção:** JWT

### 3. Listar consultas de um paciente

**Endpoint:** GET /consultas/paciente/{pacienteId}

**Descrição:** Retorna consultas de um paciente.

**Proteção:** JWT

### 4. Listar consultas de um profissional

**Endpoint:** GET /consultas/profissional/{profissionalId}

**Descrição:** Retorna consultas de um profissional.

**Proteção:** JWT

### 5. Cancelar consulta

**Endpoint:** PUT /consultas/{id}/cancelar

**Descrição:** Cancela uma consulta.

**Proteção:** JWT

---

## Módulo de Prontuários (/prontuarios)

### 1. Criar prontuário

**Endpoint:** POST /prontuarios

**Descrição:** Cria um novo prontuário.

**Proteção:** JWT

### 2. Obter prontuário por ID

**Endpoint:** GET /prontuarios/{id}

**Descrição:** Retorna um prontuário específico.

**Proteção:** JWT

### 3. Listar prontuários por paciente

**Endpoint:** GET /prontuarios/paciente/{pacienteId}

**Descrição:** Retorna prontuários de um paciente.

**Proteção:** JWT

### 4. Listar prontuários por profissional

**Endpoint:** GET /prontuarios/profissional/{profissionalId}

**Descrição:** Retorna prontuários de um profissional.

**Proteção:** JWT

### 5. Listar prontuários por consulta

**Endpoint:** GET /prontuarios/consulta/{consultaId}

**Descrição:** Retorna prontuários ligados a uma consulta.

**Proteção:** JWT

### 6. Atualizar prontuário

**Endpoint:** PUT /prontuarios/{id}

**Descrição:** Atualiza um prontuário.

**Proteção:** JWT

### 7. Deletar prontuário

**Endpoint:** DELETE /prontuarios/{id}

**Descrição:** Remove um prontuário.

**Proteção:** JWT

---

## Módulo de Agenda (/agenda)

### 1. Criar grade de trabalho

**Endpoint:** POST /agenda/grade

**Descrição:** Cria grade de trabalho para um profissional.

**Proteção:** JWT

### 2. Listar grade por profissional

**Endpoint:** GET /agenda/grade/profissional/{profissionalId}

**Descrição:** Lista grade de trabalho de um profissional.

**Proteção:** JWT

### 3. Deletar grade

**Endpoint:** DELETE /agenda/grade/{gradeId}

**Descrição:** Remove uma grade de trabalho.

**Proteção:** JWT

### 4. Criar bloqueio

**Endpoint:** POST /agenda/bloqueio

**Descrição:** Cria bloqueio de agenda para um profissional.

**Proteção:** JWT

### 5. Listar bloqueios por profissional

**Endpoint:** GET /agenda/bloqueio/profissional/{profissionalId}

**Descrição:** Lista bloqueios de um profissional.

**Proteção:** JWT

### 6. Deletar bloqueio

**Endpoint:** DELETE /agenda/bloqueio/{bloqueioId}

**Descrição:** Remove um bloqueio.

**Proteção:** JWT

### 7. Calcular disponibilidade

**Endpoint:** GET /agenda/disponibilidade/{profissionalId}/{data}

**Descrição:** Retorna horários disponíveis para um profissional em uma data.

**Proteção:** JWT

**Formato de data:** `YYYY-MM-DD`

---

## Módulo de Hospital (/admin/hospital)

### 1. Criar quarto

**Endpoint:** POST /admin/hospital/quartos

**Descrição:** Cria um quarto hospitalar.

**Proteção:** JWT

### 2. Obter quarto

**Endpoint:** GET /admin/hospital/quartos/{id}

**Descrição:** Retorna dados de um quarto.

**Proteção:** JWT

### 3. Listar quartos

**Endpoint:** GET /admin/hospital/quartos

**Descrição:** Lista todos os quartos.

**Proteção:** JWT

### 4. Atualizar quarto

**Endpoint:** PUT /admin/hospital/quartos/{id}

**Descrição:** Atualiza um quarto.

**Proteção:** JWT

### 5. Deletar quarto

**Endpoint:** DELETE /admin/hospital/quartos/{id}

**Descrição:** Remove um quarto.

**Proteção:** JWT

### 6. Criar leito

**Endpoint:** POST /admin/hospital/leitos

**Descrição:** Cria um novo leito.

**Proteção:** JWT

### 7. Listar leitos por quarto

**Endpoint:** GET /admin/hospital/leitos/quarto/{quartoId}

**Descrição:** Lista leitos de um quarto.

**Proteção:** JWT

### 8. Listar leitos disponíveis

**Endpoint:** GET /admin/hospital/leitos/disponiveis

**Descrição:** Lista leitos disponíveis.

**Proteção:** JWT

### 9. Deletar leito

**Endpoint:** DELETE /admin/hospital/leitos/{id}

**Descrição:** Remove um leito.

**Proteção:** JWT

### 10. Admitir internação

**Endpoint:** POST /admin/hospital/internacoes/admitir

**Descrição:** Registra admissão de internação.

**Proteção:** JWT

### 11. Dar alta

**Endpoint:** PUT /admin/hospital/internacoes/{id}/alta

**Descrição:** Registra alta de internação.

**Proteção:** JWT

### 12. Listar internações por paciente

**Endpoint:** GET /admin/hospital/internacoes/paciente/{pacienteId}

**Descrição:** Lista internações de um paciente.

**Proteção:** JWT

---

### 3. Listar e Filtrar Profissionais

**Endpoint:** GET /api/profissionais

**Descrição:** Lista todos os profissionais do sistema.

**Proteção:** Autenticado | Roles: ADMINISTRADOR, PACIENTE

---

**Endpoint:** GET /api/profissionais/especialidade/:especialidade

**Descrição:** Lista profissionais filtrados por especialidade.

**Proteção:** Autenticado | Roles: ADMINISTRADOR, PACIENTE

**Parâmetros de Path:** `:especialidade` - ex: Cardiologia

---

### 4. Disponibilidade de um Profissional

**Endpoint:** GET /api/profissionais/:id/disponibilidade

**Descrição:** Retorna os horários livres para um profissional numa data específica.

**Proteção:** Pública

**Parâmetros de Path:** `:id` - ID do profissional

**Parâmetros de Query:** `?data=YYYY-MM-DD` (obrigatório)

**Exemplo:**
```
GET /api/profissionais/1/disponibilidade?data=2026-04-20
```

---

## Módulo de Horários (/api/horarios)

### 1. Definir Grade Horária Semanal

**Endpoint:** POST /api/horarios/grade-semanal

**Descrição:** Permite a um profissional definir (ou substituir) a sua agenda de trabalho semanal completa numa única chamada.

**Proteção:** Autenticado | Role: PROFISSIONAL

**Corpo (Body):**
```json
[
  { "diaDaSemana": 1, "horaInicio": "09:00", "horaFim": "18:00", "duracaoConsultaMinutos": 30 },
  { "diaDaSemana": 2, "horaInicio": "09:00", "horaFim": "12:00", "duracaoConsultaMinutos": 30 },
  { "diaDaSemana": 3, "horaInicio": "09:00", "horaFim": "18:00", "duracaoConsultaMinutos": 30 },
  { "diaDaSemana": 4, "horaInicio": "09:00", "horaFim": "18:00", "duracaoConsultaMinutos": 30 },
  { "diaDaSemana": 5, "horaInicio": "09:00", "horaFim": "17:00", "duracaoConsultaMinutos": 30 }
]
```

**Nota:** Dias da semana (1=Segunda, 2=Terça, 3=Quarta, 4=Quinta, 5=Sexta)

---

### 2. Criar Indisponibilidade (Bloqueio)

**Endpoint:** POST /api/horarios/indisponibilidades

**Descrição:** Permite a um profissional bloquear um ou mais períodos na sua agenda (almoço, reuniões, folgas).

**Proteção:** Autenticado | Role: PROFISSIONAL

**Corpo (Body):**
```json
[
  {
    "dataHoraInicio": "2026-04-20T12:00:00",
    "dataHoraFim": "2026-04-20T13:00:00",
    "motivo": "Almoço"
  },
  {
    "dataHoraInicio": "2026-04-25T09:00:00",
    "dataHoraFim": "2026-04-25T18:00:00",
    "motivo": "Folga"
  }
]
```

---

### 3. Ver Agenda Completa

**Endpoint:** GET /api/horarios/minha-agenda

**Descrição:** Retorna a agenda completa de um profissional para um período, mostrando o status de cada slot (Livre, Agendado, Bloqueado).

**Proteção:** Autenticado | Role: PROFISSIONAL

**Parâmetros de Query:** `?dataInicio=YYYY-MM-DD&dataFim=YYYY-MM-DD` (obrigatórios)

**Exemplo:**
```
GET /api/horarios/minha-agenda?dataInicio=2026-04-20&dataFim=2026-04-27
```

---

## Módulo de Consultas (/api/consultas)

### 1. Agendar Nova Consulta

**Endpoint:** POST /api/consultas

**Descrição:** Agenda uma nova consulta num horário livre (pelo paciente).

**Proteção:** Autenticado | Role: PACIENTE

**Corpo (Body):**
```json
{
  "profissionalId": 1,
  "dataHoraInicio": "2026-04-20T10:00:00",
  "dataHoraFim": "2026-04-20T10:30:00",
  "observacoes": "Consulta de rotina"
}
```

---

**Endpoint:** POST /api/consultas/agendar-pelo-profissional

**Descrição:** Agenda uma nova consulta em nome de um paciente (pelo profissional ou admin).

**Proteção:** Autenticado | Roles: PROFISSIONAL, ADMINISTRADOR

**Corpo (Body):**
```json
{
  "pacienteId": 1,
  "dataHoraInicio": "2026-04-20T14:00:00",
  "dataHoraFim": "2026-04-20T14:30:00",
  "observacoes": "Seguimento"
}
```

---

### 2. Cancelar Consulta

**Endpoint:** PATCH /api/consultas/:id/cancelar-paciente

**Descrição:** Cancela uma consulta agendada pelo paciente, libertando o horário.

**Proteção:** Autenticado | Role: PACIENTE

**Parâmetros de Path:** `:id` - ID da consulta

---

**Endpoint:** PATCH /api/consultas/:id/cancelar-profissional

**Descrição:** Cancela uma consulta agendada pelo profissional.

**Proteção:** Autenticado | Role: PROFISSIONAL

---

### 3. Listar Consultas

**Endpoint:** GET /api/consultas/paciente/:pacienteId

**Descrição:** Lista todas as consultas de um paciente específico.

**Proteção:** Autenticado | Roles: PACIENTE, PROFISSIONAL, ADMINISTRADOR

---

**Endpoint:** GET /api/consultas/profissional/:profissionalId

**Descrição:** Lista todas as consultas de um profissional específico.

**Proteção:** Autenticado | Roles: PROFISSIONAL, ADMINISTRADOR

---

## Módulo de Registos Clínicos (/api/registros)

### 1. Salvar Registo Clínico de uma Consulta

**Endpoint:** PUT /api/consultas/:id/registro-clinico

**Descrição:** Cria ou atualiza o registo clínico (diagnóstico, tratamento, observações) de uma consulta. Ao ser salvo, o status da consulta muda para REALIZADA.

**Proteção:** Autenticado | Role: PROFISSIONAL

**Parâmetros de Path:** `:id` - ID da consulta

**Corpo (Body):**
```json
{
  "diagnostico": "Hipertensão estadio 1",
  "tratamento": "Medicação 2x dia após refeições",
  "observacoes": "Paciente em bom estado geral. Recomendado seguimento em 3 meses.",
  "anexos": ["ressonancia.pdf", "eletrocardiograma.png"]
}
```

---

### 2. Ver Registos de um Paciente

**Endpoint:** GET /api/registros/paciente/:pacienteId

**Descrição:** Retorna todos os registos clínicos criados para um paciente específico.

**Proteção:** Autenticado | Roles: PACIENTE, PROFISSIONAL, ADMINISTRADOR

---

**Endpoint:** GET /api/registros/consulta/:consultaId

**Descrição:** Retorna o registo clínico de uma consulta específica.

**Proteção:** Autenticado | Roles: PACIENTE, PROFISSIONAL, ADMINISTRADOR

---

## Módulo de Administração Hospitalar

### 1. Gestão de Quartos (/api/quartos)

**Endpoint:** POST /api/quartos

**Descrição:** Cria um novo quarto no hospital.

**Proteção:** Autenticado | Role: ADMINISTRADOR

**Corpo (Body):**
```json
{
  "numero": "101",
  "tipo": "ENFERMARIA",
  "leitosDisponiveis": 4
}
```

**Tipos de Quarto:** ENFERMARIA, SUITE, UTI

---

**Endpoint:** GET /api/quartos

**Descrição:** Lista todos os quartos do hospital.

**Proteção:** Autenticado | Roles: ADMINISTRADOR, PROFISSIONAL

---

**Endpoint:** GET /api/quartos/:id

**Descrição:** Retorna detalhes de um quarto específico.

**Proteção:** Autenticado | Roles: ADMINISTRADOR, PROFISSIONAL

---

**Endpoint:** PATCH /api/quartos/:id

**Descrição:** Atualiza informações de um quarto.

**Proteção:** Autenticado | Role: ADMINISTRADOR

---

**Endpoint:** DELETE /api/quartos/:id

**Descrição:** Remove um quarto do sistema.

**Proteção:** Autenticado | Role: ADMINISTRADOR

---

### 2. Gestão de Leitos (/api/leitos)

**Endpoint:** POST /api/leitos

**Descrição:** Cria um novo leito num quarto específico.

**Proteção:** Autenticado | Role: ADMINISTRADOR

**Corpo (Body):**
```json
{
  "quartoId": 1,
  "numero": "101-A"
}
```

---

**Endpoint:** GET /api/leitos

**Descrição:** Lista todos os leitos do hospital.

**Proteção:** Autenticado | Roles: ADMINISTRADOR, PROFISSIONAL

---

**Endpoint:** GET /api/leitos/status

**Descrição:** Retorna um painel completo com o status de todos os leitos, incluindo detalhes de internações ativas.

**Proteção:** Autenticado | Roles: ADMINISTRADOR, PROFISSIONAL

---

**Endpoint:** GET /api/leitos/disponiveis

**Descrição:** Lista apenas os leitos disponíveis para internação.

**Proteção:** Autenticado | Roles: ADMINISTRADOR, PROFISSIONAL

---

**Endpoint:** PATCH /api/leitos/:id

**Descrição:** Atualiza o status de um leito.

**Proteção:** Autenticado | Role: ADMINISTRADOR

---

**Endpoint:** DELETE /api/leitos/:id

**Descrição:** Remove um leito do sistema.

**Proteção:** Autenticado | Role: ADMINISTRADOR

---

### 3. Gestão de Internações (/api/internacoes)

**Endpoint:** POST /api/internacoes

**Descrição:** Admite um paciente, criando uma nova internação e ocupando um leito.

**Proteção:** Autenticado | Roles: ADMINISTRADOR, PROFISSIONAL

**Corpo (Body):**
```json
{
  "pacienteId": 1,
  "leitoId": 5,
  "profissionalResponsavelId": 2,
  "motivo": "Cirurgia de apendicite",
  "observacoes": "Pós-operatório imediato"
}
```

---

**Endpoint:** GET /api/internacoes

**Descrição:** Lista todas as internações (ativas e finalizadas).

**Proteção:** Autenticado | Roles: ADMINISTRADOR, PROFISSIONAL

---

**Endpoint:** GET /api/internacoes/:id

**Descrição:** Retorna detalhes de uma internação específica.

**Proteção:** Autenticado | Roles: ADMINISTRADOR, PROFISSIONAL

---

**Endpoint:** POST /api/internacoes/:id/registros

**Descrição:** Adiciona uma nova anotação clínica a uma internação ativa.

**Proteção:** Autenticado | Role: PROFISSIONAL

**Corpo (Body):**
```json
{
  "observacoes": "Paciente em recuperação satisfatória. Vital signs estáveis."
}
```

---

**Endpoint:** PATCH /api/internacoes/:id/alta

**Descrição:** Finaliza uma internação, dando alta ao paciente e libertando o leito.

**Proteção:** Autenticado | Roles: ADMINISTRADOR, PROFISSIONAL

**Corpo (Body):**
```json
{
  "observacoesFinal": "Alta autorizada. Prescrições fornecidas. Retorno em 15 dias."
}
```

---

## Permissões por Role

| Ação | ADMIN | PROF | PAC |
|------|-------|------|-----|
| Registar utilizador | ✅ | ✅ | ✅ |
| Login | ✅ | ✅ | ✅ |
| Ver perfil próprio | ✅ | ✅ | ✅ |
| Editar perfil próprio | ✅ | ✅ | ✅ |
| Gerenciar profissionais | ✅ | ❌ | ❌ |
| Definir grade horária | ❌ | ✅ | ❌ |
| Criar bloqueios | ❌ | ✅ | ❌ |
| Ver disponibilidade | ✅ | ✅ | ✅ |
| Agendar consulta | ✅ | ✅ | ✅ |
| Cancelar consulta própria | ✅ | ✅ | ✅ |
| Criar registo clínico | ✅ | ✅ | ❌ |
| Gerenciar quartos/leitos | ✅ | ❌ | ❌ |
| Admitir paciente | ✅ | ✅ | ❌ |
| Dar alta a paciente | ✅ | ✅ | ❌ |

---

## Fluxo Rápido de Uso

### 1. Criar Conta e Autenticar
```
POST /api/usuarios/register
POST /api/usuarios/login
→ Copiar TOKEN_JWT
```

### 2. Configurar Profissional (como ADMIN)
```
POST /api/profissionais (criar profissional)
POST /api/horarios/grade-semanal (definir horários)
POST /api/horarios/indisponibilidades (criar bloqueios)
```

### 3. Agendar Consulta (como PACIENTE)
```
GET /api/profissionais/:id/disponibilidade?data=2026-04-20
POST /api/consultas (agendar numa hora disponível)
```

### 4. Registar Consulta (como PROFISSIONAL)
```
PUT /api/consultas/:id/registro-clinico (salvar diagnóstico)
```

### 5. Internação (como ADMIN/PROFISSIONAL)
```
GET /api/leitos/disponiveis
POST /api/internacoes (admitir paciente)
PATCH /api/internacoes/:id/alta (dar alta)
