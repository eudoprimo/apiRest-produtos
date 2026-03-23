# Documentação de Schemas — Guia Completo de Preenchimento

> **Versão:** 1.0  
> **Idioma:** Português (PT-BR)

---

## Índice

1. [Introdução](#1-introdução)
2. [Instruções Gerais de Preenchimento](#2-instruções-gerais-de-preenchimento)
3. [Padrões e Nomenclaturas](#3-padrões-e-nomenclaturas)
   - 3.1 [Padrão de Registro](#31-padrão-de-registro)
   - 3.2 [Código de Transação](#32-código-de-transação)
   - 3.3 [Campos de Identificação](#33-campos-de-identificação)
4. [Campos Obrigatórios do Schema](#4-campos-obrigatórios-do-schema)
5. [Exemplos Práticos](#5-exemplos-práticos)
   - 5.1 [Fluxo Completo — Tela de Login](#51-fluxo-completo--tela-de-login)
6. [Ações e Confirmação](#6-ações-e-confirmação)
   - 6.1 [Código de Transação](#61-código-de-transação)
   - 6.2 [Nome da Transação](#62-nome-da-transação)
   - 6.3 [Cliente Logado](#63-cliente-logado)
   - 6.4 [Arquivos de Schema — Tabela de Inclusão](#64-arquivos-de-schema--tabela-de-inclusão)
7. [Manifesto, Validação e Custos](#7-manifesto-validação-e-custos)
8. [Macro: Validar Schema](#8-macro-validar-schema)
9. [Incluir Campos no Catálogo de Dados](#9-incluir-campos-no-catálogo-de-dados)
10. [Macro: Gerar JSON e Checklist](#10-macro-gerar-json-e-checklist)
11. [Macro: Importar JSON (Schema)](#11-macro-importar-json-schema)
12. [Rastreabilidade](#12-rastreabilidade)
13. [Referências e Links Úteis](#13-referências-e-links-úteis)

---

## 1. Introdução

O sistema de **schemas** tem como objetivo padronizar a estrutura e o formato dos dados trafegados entre sistemas, garantindo consistência, rastreabilidade e facilidade de manutenção.

Esta documentação serve como guia completo de referência para:

- Preenchimento correto de schemas
- Aplicação de padrões e nomenclaturas
- Uso das macros disponíveis (Validar, Gerar JSON, Importar JSON)
- Inclusão de campos no catálogo de dados
- Processo de checklist e validação

---

## 2. Instruções Gerais de Preenchimento

Ao preencher um schema, siga as orientações abaixo para garantir conformidade com os padrões estabelecidos:

1. **Leia a documentação completa** antes de iniciar o preenchimento.
2. **Consulte os links de referência** disponíveis para cada tipo de campo.
3. **Utilize as nomenclaturas padronizadas** descritas na [Seção 3](#3-padrões-e-nomenclaturas).
4. **Não deixe campos obrigatórios em branco** — consulte a [Seção 4](#4-campos-obrigatórios-do-schema) para a lista completa.
5. **Valide o schema** após o preenchimento utilizando a [Macro Validar Schema](#8-macro-validar-schema).
6. **Gere o JSON** e execute o checklist antes de submeter o schema para aprovação.
7. Em caso de dúvida, consulte o responsável pelo catálogo de dados ou abra um chamado de suporte.

> **Atenção:** Alterações em schemas já aprovados devem passar pelo processo de revisão e re-aprovação descrito na [Seção 9](#9-incluir-campos-no-catálogo-de-dados).

---

## 3. Padrões e Nomenclaturas

### 3.1 Padrão de Registro

O registro de um schema deve seguir o padrão abaixo:

| Elemento | Formato | Exemplo |
|----------|---------|---------|
| Nome do Schema | `SNAKE_CASE` em maiúsculas | `LOGIN_USUARIO` |
| Versão | `v{MAJOR}.{MINOR}` | `v1.0` |
| Domínio | Sigla do domínio em maiúsculas | `AUTH`, `PAGTO`, `CADASTRO` |
| Identificador único | `{DOMINIO}_{NOME_SCHEMA}_{VERSAO}` | `AUTH_LOGIN_USUARIO_v1.0` |

### 3.2 Código de Transação

O **Código de Transação** identifica unicamente cada operação/fluxo dentro do sistema.

**Formato:** `{SIGLA_SISTEMA}{TIPO_TRANSACAO}{SEQUENCIAL}`

| Componente | Descrição | Exemplo |
|------------|-----------|---------|
| `SIGLA_SISTEMA` | Sigla do sistema de origem (2–4 caracteres) | `APP`, `WEB`, `MOB` |
| `TIPO_TRANSACAO` | Tipo da operação | `LGN` (Login), `PGT` (Pagamento), `CAD` (Cadastro) |
| `SEQUENCIAL` | Número sequencial com 3 dígitos | `001`, `002` |

**Exemplos:**

```
APPLGN001   → Sistema APP, Transação de Login, Sequencial 001
WEBPGT002   → Sistema WEB, Transação de Pagamento, Sequencial 002
MOBCAD001   → Sistema MOB, Transação de Cadastro, Sequencial 001
```

### 3.3 Campos de Identificação

Todos os schemas devem conter os seguintes campos de identificação:

| Campo | Tipo | Obrigatório | Descrição |
|-------|------|-------------|-----------|
| `schema_id` | `string` | Sim | Identificador único do schema |
| `schema_version` | `string` | Sim | Versão do schema |
| `transaction_code` | `string` | Sim | Código de transação associado |
| `domain` | `string` | Sim | Domínio ao qual o schema pertence |
| `created_at` | `datetime` | Sim | Data/hora de criação |
| `updated_at` | `datetime` | Sim | Data/hora da última atualização |
| `status` | `enum` | Sim | `ATIVO`, `INATIVO`, `RASCUNHO` |

---

## 4. Campos Obrigatórios do Schema

A tabela a seguir lista os campos obrigatórios que **todos** os schemas devem conter:

| # | Campo | Tipo | Descrição |
|---|-------|------|-----------|
| 1 | `id` | `string` | Identificador único do registro |
| 2 | `nome` | `string` | Nome legível do schema |
| 3 | `descricao` | `string` | Descrição detalhada do propósito do schema |
| 4 | `versao` | `string` | Versão atual do schema |
| 5 | `dominio` | `string` | Domínio de negócio ao qual pertence |
| 6 | `codigo_transacao` | `string` | Código de transação associado |
| 7 | `status` | `enum` | Status atual: `ATIVO`, `INATIVO`, `RASCUNHO` |
| 8 | `data_criacao` | `datetime` | Data de criação do schema |
| 9 | `data_atualizacao` | `datetime` | Data da última atualização |
| 10 | `responsavel` | `string` | Nome ou matrícula do responsável |

---

## 5. Exemplos Práticos

### 5.1 Fluxo Completo — Tela de Login

A seguir, um exemplo completo de preenchimento de schema para o fluxo de **Tela de Login**:

#### Identificação do Schema

| Campo | Valor |
|-------|-------|
| ID | `AUTH_LOGIN_USUARIO_v1.0` |
| Nome | `Login de Usuário` |
| Descrição | `Schema responsável por validar os dados de login do usuário na plataforma` |
| Versão | `v1.0` |
| Domínio | `AUTH` |
| Código de Transação | `APPLGN001` |
| Status | `ATIVO` |
| Responsável | `equipe-autenticacao` |

#### Campos do Schema — Login

| Campo | Tipo | Obrigatório | Descrição | Exemplo |
|-------|------|-------------|-----------|---------|
| `usuario` | `string` | Sim | CPF, e-mail ou nome de usuário | `joao.silva@email.com` |
| `senha` | `string` | Sim | Senha do usuário (criptografada) | `*****` |
| `lembrar_acesso` | `boolean` | Não | Manter sessão ativa | `true` |
| `dispositivo` | `string` | Não | Identificador do dispositivo | `MOB-ANDROID-001` |
| `ip_origem` | `string` | Sim | Endereço IP de origem da requisição | `192.168.1.1` |
| `timestamp` | `datetime` | Sim | Data/hora da tentativa de login | `2024-01-15T10:30:00Z` |

#### Campos de Resposta — Login

| Campo | Tipo | Descrição | Exemplo |
|-------|------|-----------|---------|
| `sucesso` | `boolean` | Indica se o login foi bem-sucedido | `true` |
| `token` | `string` | Token de autenticação JWT | `eyJhbGci...` |
| `expiracao_token` | `datetime` | Data/hora de expiração do token | `2024-01-15T11:30:00Z` |
| `mensagem` | `string` | Mensagem de retorno | `Login realizado com sucesso` |
| `codigo_erro` | `string` | Código de erro (quando aplicável) | `ERR_SENHA_INVALIDA` |

#### Exemplo de JSON Gerado

```json
{
  "schema_id": "AUTH_LOGIN_USUARIO_v1.0",
  "schema_version": "v1.0",
  "transaction_code": "APPLGN001",
  "domain": "AUTH",
  "fields": {
    "request": {
      "usuario": { "type": "string", "required": true },
      "senha":   { "type": "string", "required": true },
      "lembrar_acesso": { "type": "boolean", "required": false },
      "dispositivo":    { "type": "string",  "required": false },
      "ip_origem":      { "type": "string",  "required": true },
      "timestamp":      { "type": "datetime","required": true }
    },
    "response": {
      "sucesso":         { "type": "boolean",  "required": true },
      "token":           { "type": "string",   "required": false },
      "expiracao_token": { "type": "datetime", "required": false },
      "mensagem":        { "type": "string",   "required": true },
      "codigo_erro":     { "type": "string",   "required": false }
    }
  }
}
```

---

## 6. Ações e Confirmação

A tela de **Ações e Confirmação** é utilizada para registrar e confirmar as transações associadas a um schema. Ela é composta pelos seguintes elementos:

### 6.1 Código de Transação

- Campo obrigatório que identifica unicamente a transação.
- Deve seguir o padrão descrito na [Seção 3.2](#32-código-de-transação).
- Não pode ser alterado após a aprovação do schema.

### 6.2 Nome da Transação

- Nome legível para identificação humana da transação.
- Deve ser claro, objetivo e em português.
- Exemplos: `Login de Usuário`, `Pagamento de Boleto`, `Cadastro de Cliente`.

### 6.3 Cliente Logado

- Indica se a transação requer que o cliente/usuário esteja autenticado.
- Valores possíveis: `SIM` / `NÃO`.
- Impacta diretamente nas políticas de segurança e rastreabilidade do schema.

### 6.4 Arquivos de Schema — Tabela de Inclusão

A tabela a seguir descreve os tipos de arquivos de schema e suas respectivas ações de inclusão:

| Tipo de Arquivo | Descrição | Ação |
|-----------------|-----------|------|
| **Wrapper** | Schema de envelope que encapsula outros schemas | Incluir via [Macro Importar JSON](#11-macro-importar-json-schema) |
| **Fluxo** | Schema que representa um fluxo de negócio completo | Incluir via [Macro Importar JSON](#11-macro-importar-json-schema) |
| **Arquivo JSON** | Schema definido diretamente em arquivo JSON | Importar arquivo `.json` |
| **Schema Simples** | Schema com campos básicos sem aninhamento | Preencher manualmente no formulário |

---

## 7. Manifesto, Validação e Custos

### Manifesto

O **Manifesto** é um documento gerado automaticamente que descreve a estrutura completa de um schema aprovado. Ele inclui:

- Lista de todos os campos com seus tipos e restrições
- Histórico de versões
- Dependências com outros schemas
- Informações de rastreabilidade

### Validação

O processo de validação garante que o schema está em conformidade com os padrões estabelecidos. As etapas são:

1. **Validação de estrutura** — verifica a sintaxe e a estrutura do JSON
2. **Validação de nomenclatura** — verifica se os campos seguem os padrões de nomenclatura
3. **Validação de tipos** — verifica se os tipos de dados são compatíveis
4. **Validação de obrigatoriedade** — verifica se todos os campos obrigatórios estão presentes
5. **Validação de unicidade** — verifica se o `schema_id` e o `transaction_code` são únicos

### Custos de Armazenamento

O armazenamento de schemas possui custos associados conforme a tabela abaixo:

| Tipo | Tamanho Máximo | Custo por Schema |
|------|---------------|-----------------|
| Schema Simples | até 10 KB | Sem custo adicional |
| Schema Médio | 10 KB – 100 KB | Conforme política interna |
| Schema Grande | acima de 100 KB | Aprovação especial necessária |

> **Nota:** Os custos são revisados periodicamente. Consulte o gestor de catálogo para informações atualizadas.

---

## 8. Macro: Validar Schema

A **Macro Validar Schema** é uma ferramenta que permite verificar se um schema está corretamente preenchido e em conformidade com os padrões.

### Funcionalidades

| Funcionalidade | Descrição |
|----------------|-----------|
| **Verificação de estrutura** | Valida a sintaxe e estrutura do schema |
| **Consulta de campos** | Verifica se todos os campos obrigatórios estão presentes |
| **Verificação de rastreabilidade** | Confirma que os campos de rastreabilidade estão corretamente preenchidos |
| **Validação de nomenclatura** | Verifica conformidade com os padrões de nomenclatura |
| **Relatório de inconsistências** | Gera relatório detalhado com os erros encontrados |

### Como Utilizar

1. Acesse a ferramenta de schemas.
2. Selecione o schema que deseja validar.
3. Clique em **"Validar Schema"** (ou execute a macro correspondente).
4. Aguarde o processamento.
5. Analise o relatório gerado:
   - ✅ **Verde** — campo válido
   - ⚠️ **Amarelo** — campo com alerta (não crítico)
   - ❌ **Vermelho** — campo com erro (deve ser corrigido)
6. Corrija os erros indicados e repita o processo até que todos os campos estejam válidos.

### Verificação de Rastreabilidade

A validação de rastreabilidade verifica se os seguintes campos estão presentes e corretamente preenchidos:

- `schema_id` — identificador único
- `transaction_code` — código de transação
- `created_at` — data de criação
- `updated_at` — data de atualização
- `responsavel` — responsável pelo schema

---

## 9. Incluir Campos no Catálogo de Dados

O **Catálogo de Dados** é o repositório centralizado de todos os campos utilizados nos schemas. Para incluir novos campos, siga o processo abaixo:

### Processo de Inclusão

```
Solicitação de Inclusão
        │
        ▼
Preenchimento do Formulário
        │
        ▼
Revisão Técnica (Equipe de Dados)
        │
        ▼
Aprovação pelo Gestor de Catálogo
        │
        ▼
Publicação no Catálogo
        │
        ▼
Disponível para uso em Schemas
```

### Informações Necessárias para Inclusão

| Campo | Obrigatório | Descrição |
|-------|-------------|-----------|
| Nome do Campo | Sim | Nome técnico no padrão `snake_case` |
| Tipo de Dado | Sim | `string`, `integer`, `boolean`, `datetime`, `array`, `object` |
| Descrição | Sim | Descrição clara do propósito do campo |
| Domínio | Sim | Domínio de negócio ao qual o campo pertence |
| Obrigatoriedade | Sim | Se o campo é obrigatório ou opcional |
| Valores Permitidos | Não | Lista de valores válidos (para enumerações) |
| Formato | Não | Formato esperado (ex: `YYYY-MM-DD` para datas) |
| Exemplo de Valor | Sim | Um exemplo de valor válido |
| Responsável | Sim | Responsável pela solicitação |

### Inclusão no Schema

Após a aprovação no catálogo, o campo estará disponível para inclusão nos schemas:

1. Acesse o schema desejado.
2. Clique em **"Incluir Campo"**.
3. Pesquise o campo pelo nome ou domínio.
4. Selecione o campo do catálogo.
5. Configure as propriedades específicas para este schema (obrigatoriedade, valor padrão, etc.).
6. Salve as alterações.
7. Execute a [Macro Validar Schema](#8-macro-validar-schema) para confirmar a inclusão.

> **Atenção:** Campos **não catalogados** não podem ser incluídos diretamente nos schemas. Solicite a inclusão no catálogo antes de utilizar o campo.

---

## 10. Macro: Gerar JSON e Checklist

A **Macro Gerar JSON** converte o schema preenchido em um arquivo JSON estruturado, pronto para importação e uso nos sistemas.

### Processo de Geração

1. Acesse o schema finalizado e validado.
2. Clique em **"Gerar JSON"**.
3. Aguarde a geração do arquivo.
4. O sistema iniciará automaticamente o **Checklist de Validação**.

### Checklist de Validação

O checklist verifica os seguintes itens antes de liberar o JSON gerado:

| # | Item | Critério de Aprovação |
|---|------|-----------------------|
| 1 | Campos obrigatórios | Todos os campos obrigatórios preenchidos |
| 2 | Nomenclatura | Todos os campos seguem o padrão `snake_case` |
| 3 | Tipos de dados | Todos os tipos estão corretamente definidos |
| 4 | Código de transação | Código único e no formato correto |
| 5 | Rastreabilidade | Campos de rastreabilidade presentes |
| 6 | Versão do schema | Versão seguindo o padrão `v{MAJOR}.{MINOR}` |
| 7 | Status | Schema com status `ATIVO` |
| 8 | Responsável | Campo responsável preenchido |
| 9 | Validação estrutural | Schema aprovado na Macro Validar Schema |
| 10 | Catálogo de dados | Todos os campos presentes no catálogo |

### Resultado do Checklist

- **Todos os itens aprovados** → JSON é gerado e disponibilizado para download/importação.
- **Itens com falha** → Lista de pendências é exibida; corrija antes de gerar novamente.

### Formato do JSON Gerado

O JSON gerado segue a estrutura padrão:

```json
{
  "schema_id": "...",
  "schema_version": "...",
  "transaction_code": "...",
  "domain": "...",
  "status": "ATIVO",
  "created_at": "...",
  "updated_at": "...",
  "responsavel": "...",
  "fields": { ... },
  "metadata": {
    "checklist_aprovado": true,
    "data_geracao": "...",
    "gerado_por": "..."
  }
}
```

---

## 11. Macro: Importar JSON (Schema)

A **Macro Importar JSON** permite criar ou atualizar schemas a partir de arquivos JSON previamente gerados ou elaborados externamente.

### Tipos de Importação

| Tipo | Descrição | Quando Usar |
|------|-----------|-------------|
| **Wrapper** | Importa um schema de envelope que referencia outros schemas | Quando o schema encapsula múltiplos schemas internos |
| **Fluxo** | Importa um schema que representa um fluxo de negócio completo | Quando o schema descreve uma sequência de operações |
| **Arquivo JSON** | Importa diretamente um arquivo `.json` | Para schemas elaborados externamente ou migrados |

### Como Importar

#### Importação de Wrapper

1. Selecione **"Importar JSON"** > **"Wrapper"**.
2. Informe os schemas internos que serão encapsulados.
3. Faça o upload do arquivo JSON do Wrapper.
4. Confirme o mapeamento dos campos.
5. Clique em **"Importar"**.

#### Importação de Fluxo

1. Selecione **"Importar JSON"** > **"Fluxo"**.
2. Informe a sequência de operações do fluxo.
3. Faça o upload do arquivo JSON do Fluxo.
4. Confirme a ordem das etapas.
5. Clique em **"Importar"**.

#### Importação de Arquivo JSON

1. Selecione **"Importar JSON"** > **"Arquivo JSON"**.
2. Faça o upload do arquivo `.json`.
3. O sistema validará automaticamente a estrutura do arquivo.
4. Confirme as informações exibidas na pré-visualização.
5. Clique em **"Importar"**.

### Validações Aplicadas na Importação

- **Estrutura do JSON** — verifica se o arquivo é um JSON válido
- **Campos obrigatórios** — verifica se todos os campos obrigatórios estão presentes
- **Unicidade** — verifica se o `schema_id` não está duplicado
- **Versão** — verifica se a versão segue o padrão definido
- **Catálogo** — verifica se os campos estão cadastrados no catálogo de dados

> **Atenção:** Se a importação falhar, um relatório de erros será exibido detalhando os problemas encontrados. Corrija o arquivo JSON e tente novamente.

---

## 12. Rastreabilidade

A **rastreabilidade** é um requisito fundamental para todos os schemas. Ela garante que seja possível:

- Identificar **quem** criou ou alterou o schema
- Identificar **quando** cada alteração foi realizada
- Identificar **qual sistema** utilizou o schema
- Rastrear o **fluxo completo** de uma transação

### Campos de Rastreabilidade

| Campo | Tipo | Descrição |
|-------|------|-----------|
| `schema_id` | `string` | Identificador único e imutável do schema |
| `transaction_code` | `string` | Código único da transação associada |
| `created_at` | `datetime` | Data/hora de criação (UTC) |
| `updated_at` | `datetime` | Data/hora da última modificação (UTC) |
| `created_by` | `string` | Usuário/sistema que criou o schema |
| `updated_by` | `string` | Usuário/sistema que realizou a última alteração |
| `version_history` | `array` | Histórico de versões com alterações e responsáveis |

### Boas Práticas de Rastreabilidade

1. **Nunca altere o `schema_id`** após a aprovação — crie uma nova versão se necessário.
2. **Sempre incremente a versão** ao realizar alterações, mesmo que pequenas.
3. **Documente as alterações** no campo `descricao_alteracao` ao criar uma nova versão.
4. **Mantenha o histórico de versões** atualizado.
5. **Associe sempre um `transaction_code`** único a cada schema.

---

## 13. Referências e Links Úteis

| Recurso | Descrição | Link |
|---------|-----------|------|
| Catálogo de Dados | Repositório de campos catalogados | *(link interno)* |
| Padrões de Nomenclatura | Guia completo de nomenclaturas | *(link interno)* |
| Ferramenta de Schemas | Interface principal de gestão de schemas | *(link interno)* |
| Portal de Suporte | Abertura de chamados e dúvidas | *(link interno)* |
| Documentação da API | Referência técnica da API de schemas | *(link interno)* |

---

> **Dúvidas ou sugestões?** Entre em contato com a equipe responsável pelo catálogo de dados ou abra um chamado no portal de suporte.

---

*Última atualização: 2024 — Equipe de Arquitetura e Dados*
