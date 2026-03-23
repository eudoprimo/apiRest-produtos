# Instruções de Preenchimento de Schemas

## Sumário

1. [Instruções Gerais](#1-instruções-gerais)
2. [Padrões de Nomenclatura](#2-padrões-de-nomenclatura)
   - [Nomenclatura para Registros](#21-nomenclatura-para-registros)
   - [Nomenclatura para Códigos de Transação](#22-nomenclatura-para-códigos-de-transação)
3. [Campos de Identificação de Schema](#3-campos-de-identificação-de-schema)
4. [Exemplos Práticos — Fluxo de Tela de Login](#4-exemplos-práticos--fluxo-de-tela-de-login)
5. [Manifesto, Validação e Custos](#5-manifesto-validação-e-custos)

---

## 1. Instruções Gerais

Estas instruções orientam o correto preenchimento dos schemas utilizados na API. O cumprimento rigoroso destas diretrizes garante rastreabilidade, consistência e integridade das transações registradas.

- Todos os campos obrigatórios devem ser preenchidos antes do envio do schema.
- Valores nulos ou em branco em campos obrigatórios resultarão em erro de validação.
- Os campos devem respeitar o tipo de dado definido (string, número, booleano, data etc.).
- Datas devem seguir o formato **ISO 8601**: `YYYY-MM-DDTHH:mm:ssZ`.
- Campos de texto não devem ultrapassar o limite de caracteres especificado em cada campo.
- Utilize sempre **UTF-8** como codificação para todos os valores textuais.

---

## 2. Padrões de Nomenclatura

### 2.1 Nomenclatura para Registros

Os nomes de registros seguem um padrão estruturado para facilitar a identificação e rastreabilidade.

**Formato:**
```
[CONTEXTO]_[ENTIDADE]_[AÇÃO]
```

| Componente | Descrição                                              | Exemplo         |
|------------|--------------------------------------------------------|-----------------|
| CONTEXTO   | Módulo ou domínio ao qual o registro pertence          | `AUTH`, `PROD`  |
| ENTIDADE   | Nome da entidade ou recurso envolvido                  | `USUARIO`, `PRODUTO` |
| AÇÃO       | Operação realizada sobre a entidade                    | `CRIADO`, `ATUALIZADO`, `REMOVIDO` |

**Exemplos válidos:**
- `AUTH_USUARIO_CRIADO`
- `PROD_PRODUTO_ATUALIZADO`
- `AUTH_SESSAO_ENCERRADA`

**Regras:**
- Usar apenas letras maiúsculas e underscores (`_`).
- Não utilizar espaços, acentos ou caracteres especiais.
- Manter nomes concisos e autoexplicativos.

---

### 2.2 Nomenclatura para Códigos de Transação

Os códigos de transação identificam unicamente cada operação no sistema e seguem um formato padronizado para rastreabilidade.

**Formato:**
```
[SIGLA_SISTEMA]-[TIPO]-[SEQUENCIAL]
```

| Componente    | Descrição                                     | Exemplo       |
|---------------|-----------------------------------------------|---------------|
| SIGLA_SISTEMA | Sigla do sistema ou serviço                   | `API`, `GW`   |
| TIPO          | Tipo da transação (`REQ` para requisição, `RSP` para resposta, `EVT` para evento) | `REQ`, `RSP`, `EVT` |
| SEQUENCIAL    | Número sequencial único de 8 dígitos          | `00000001`    |

**Exemplos válidos:**
- `API-REQ-00000001`
- `API-RSP-00000001`
- `GW-EVT-00000042`

**Regras:**
- O sequencial deve ser único por tipo e sistema, reiniciado a cada ciclo operacional.
- O código deve ser gerado automaticamente pelo sistema e nunca inserido manualmente.

---

## 3. Campos de Identificação de Schema

Cada schema deve conter os seguintes campos de identificação:

| Campo             | Tipo     | Obrigatório | Descrição                                                                 |
|-------------------|----------|-------------|---------------------------------------------------------------------------|
| `schemaId`        | string   | Sim         | Identificador único do schema no formato UUID v4.                         |
| `schemaVersao`    | string   | Sim         | Versão do schema no formato semântico `MAJOR.MINOR.PATCH` (ex.: `1.0.0`).|
| `transacaoId`     | string   | Sim         | Código único da transação conforme padrão de nomenclatura.                |
| `dataHora`        | string   | Sim         | Data e hora de criação do registro no formato ISO 8601.                   |
| `sistemaOrigem`   | string   | Sim         | Sigla do sistema que originou o registro (ex.: `API-PRODUTOS`).           |
| `correlacaoId`    | string   | Não         | ID de correlação para rastreamento de fluxos encadeados.                  |
| `ambiente`        | string   | Sim         | Ambiente de execução: `DEV`, `HML` ou `PRD`.                              |
| `usuarioId`       | string   | Condicional | Identificador do usuário que originou a transação (obrigatório quando aplicável). |

**Exemplo de preenchimento:**

```json
{
  "schemaId": "a3f4b2c1-d5e6-7890-abcd-ef1234567890",
  "schemaVersao": "1.0.0",
  "transacaoId": "API-REQ-00000001",
  "dataHora": "2024-06-01T10:30:00Z",
  "sistemaOrigem": "API-PRODUTOS",
  "correlacaoId": "corr-abc123",
  "ambiente": "PRD",
  "usuarioId": "usr-00042"
}
```

---

## 4. Exemplos Práticos — Fluxo de Tela de Login

Esta seção apresenta exemplos de preenchimento de schemas para o fluxo de autenticação (tela de login).

### 4.1 Requisição de Login

**Evento:** Usuário submete credenciais na tela de login.

```json
{
  "schemaId": "b1c2d3e4-f5a6-7890-bcde-f12345678901",
  "schemaVersao": "1.0.0",
  "transacaoId": "API-REQ-00000001",
  "dataHora": "2024-06-01T08:00:00Z",
  "sistemaOrigem": "API-PRODUTOS",
  "correlacaoId": "login-flow-001",
  "ambiente": "PRD",
  "usuarioId": null,
  "payload": {
    "registro": "AUTH_USUARIO_LOGIN",
    "email": "usuario@exemplo.com",
    "senha": "********"
  }
}
```

> **Nota:** O campo `senha` nunca deve ser registrado em texto puro em logs ou schemas persistidos. Utilize `"********"` como valor mascarado.

---

### 4.2 Resposta de Login com Sucesso

**Evento:** Sistema autentica o usuário com sucesso e retorna token de acesso.

```json
{
  "schemaId": "c2d3e4f5-a6b7-8901-cdef-123456789012",
  "schemaVersao": "1.0.0",
  "transacaoId": "API-RSP-00000001",
  "dataHora": "2024-06-01T08:00:01Z",
  "sistemaOrigem": "API-PRODUTOS",
  "correlacaoId": "login-flow-001",
  "ambiente": "PRD",
  "usuarioId": "usr-00042",
  "payload": {
    "registro": "AUTH_USUARIO_AUTENTICADO",
    "status": "SUCESSO",
    "tokenAcesso": "<JWT_TOKEN>",
    "expiracaoToken": "2024-06-01T09:00:01Z"
  }
}
```

---

### 4.3 Resposta de Login com Falha

**Evento:** Sistema rejeita as credenciais do usuário.

```json
{
  "schemaId": "d3e4f5a6-b7c8-9012-defa-234567890123",
  "schemaVersao": "1.0.0",
  "transacaoId": "API-RSP-00000002",
  "dataHora": "2024-06-01T08:00:01Z",
  "sistemaOrigem": "API-PRODUTOS",
  "correlacaoId": "login-flow-002",
  "ambiente": "PRD",
  "usuarioId": null,
  "payload": {
    "registro": "AUTH_USUARIO_FALHA_LOGIN",
    "status": "FALHA",
    "codigoErro": "AUTH-001",
    "mensagem": "Credenciais inválidas. Verifique e-mail e senha."
  }
}
```

---

## 5. Manifesto, Validação e Custos

### 5.1 Manifesto do Schema

O manifesto descreve os metadados do schema e deve ser mantido atualizado a cada alteração estrutural.

| Campo              | Descrição                                                        |
|--------------------|------------------------------------------------------------------|
| `nome`             | Nome descritivo do schema.                                       |
| `versao`           | Versão atual no formato semântico (`MAJOR.MINOR.PATCH`).         |
| `descricao`        | Descrição funcional do schema e seu propósito.                   |
| `proprietario`     | Time ou pessoa responsável pelo schema.                          |
| `dataPublicacao`   | Data de publicação/última alteração no formato ISO 8601.         |
| `status`           | Status do schema: `ATIVO`, `DEPRECIADO` ou `INATIVO`.            |
| `dependencias`     | Lista de outros schemas ou serviços dos quais este depende.      |

**Exemplo de manifesto:**

```json
{
  "nome": "Schema de Autenticação de Usuário",
  "versao": "1.0.0",
  "descricao": "Schema responsável pelo registro e rastreamento do fluxo de autenticação de usuários.",
  "proprietario": "Time de Plataforma",
  "dataPublicacao": "2024-06-01T00:00:00Z",
  "status": "ATIVO",
  "dependencias": ["schema-usuario-v1", "schema-sessao-v1"]
}
```

---

### 5.2 Validação de Schemas

Antes de qualquer deploy ou integração, o schema deve passar pelos seguintes níveis de validação:

1. **Validação Estrutural** — Verificação da estrutura JSON/XML conforme o modelo definido (campos obrigatórios, tipos de dados, tamanhos).
2. **Validação Semântica** — Verificação de regras de negócio (ex.: `expiracaoToken` deve ser posterior a `dataHora`).
3. **Validação de Nomenclatura** — Verificação se os campos de `registro` e `transacaoId` seguem os padrões definidos na [Seção 2](#2-padrões-de-nomenclatura).
4. **Validação de Ambiente** — Confirmar que o campo `ambiente` está correto para o contexto de execução (nunca enviar `DEV` em produção).

**Ferramentas recomendadas:**
- JSON Schema Validator (para schemas JSON)
- Swagger/OpenAPI Validator (para contratos REST)
- Testes automatizados de contrato (ex.: Spring Cloud Contract, Pact)

---

### 5.3 Custos e Rastreabilidade

O correto preenchimento dos schemas impacta diretamente nos custos operacionais e na rastreabilidade do sistema:

| Aspecto                     | Impacto do Preenchimento Incorreto                                     |
|-----------------------------|------------------------------------------------------------------------|
| **Rastreabilidade**         | Dificuldade em correlacionar eventos e identificar falhas em produção. |
| **Auditoria**               | Logs incompletos podem gerar não conformidades em auditorias.          |
| **Custos de Suporte**       | Maior tempo de investigação de incidentes sem rastreamento adequado.   |
| **Reprocessamento**         | Schemas inválidos causam rejeições que exigem reprocessamento manual.  |
| **Conformidade Regulatória**| Dados ausentes podem violar requisitos de compliance (ex.: LGPD).      |

**Boas práticas para redução de custos:**
- Automatizar a geração dos campos de identificação (`schemaId`, `transacaoId`, `dataHora`).
- Implementar validação na borda (client-side) antes do envio.
- Manter logs de erros de validação para análise e melhoria contínua.
- Revisar e atualizar os schemas periodicamente conforme evolução do negócio.

---

## Referências

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [ISO 8601 — Date and Time Format](https://www.iso.org/iso-8601-date-and-time-format.html)
- [UUID v4 Specification (RFC 4122)](https://www.ietf.org/rfc/rfc4122.txt)
- [Swagger / OpenAPI Specification](https://swagger.io/specification/)
- [JSON Schema](https://json-schema.org/)
