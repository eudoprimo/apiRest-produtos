# 📊 Schema de Transações de Criptomoedas

> **Guia Visual Completo** — Instruções de preenchimento para o schema de transações de criptomoedas.
> Você pode copiar os exemplos e colar diretamente na sua planilha!

---

## 📋 Índice

1. [O que é este schema?](#1--o-que-é-este-schema)
2. [Tabela completa dos campos](#2--tabela-completa-dos-campos)
3. [Como preencher cada campo](#3--como-preencher-cada-campo)
4. [Exemplos práticos de transações](#4--exemplos-práticos-de-transações)
5. [Checklist de validação](#5--checklist-de-validação)
6. [Domínios e valores permitidos](#6--domínios-e-valores-permitidos)

---

## 1. 📌 O que é este schema?

Este schema define a estrutura de dados para registrar **transações de compra e venda de criptomoedas**. Ele descreve:

- **Quais campos** devem ser preenchidos
- **Qual tipo de dado** cada campo aceita (texto, número, data etc.)
- **Se o campo é obrigatório** ou opcional
- **Quais valores são permitidos** em cada campo

---

## 2. 📊 Tabela Completa dos Campos

> ✅ = Obrigatório | ⬜ = Opcional

| # | Nome do Campo | Descrição | Tipo | Tamanho | Obrigatório | Exemplo |
|---|---------------|-----------|------|---------|-------------|---------|
| 1 | `preco` | Valor monetário da transação | DECIMAL | 18,8 | ✅ | `0.00015000` |
| 2 | `quantidade_de_moedas` | Quantidade de moedas negociadas | DECIMAL | 18,8 | ✅ | `0.00100000` |
| 3 | `criacao_ordem` | Data e hora de criação da ordem | DATETIME | 19 | ✅ | `2024-01-15T10:30:00` |
| 4 | `confirmacao_ordem` | Data e hora de confirmação da ordem | DATETIME | 19 | ⬜ | `2024-01-15T10:30:05` |
| 5 | `tipo_transacao` | Débito ou crédito na conta | STRING | 6 | ✅ | `DEBITO` |
| 6 | `id_usuario` | Identificador único do usuário | STRING | 36 | ✅ | `usr-123e4567-e89b-12d3` |
| 7 | `tipo_ordem` | Compra ou venda de criptomoeda | STRING | 6 | ✅ | `COMPRA` |
| 8 | `tipo_ativo` | Símbolo da criptomoeda | STRING | 10 | ✅ | `BTC` |
| 9 | `id_ordem` | Identificador único da ordem | STRING | 36 | ✅ | `ord-550e8400-e29b-41d4` |
| 10 | `status_ordem` | Status atual da ordem | STRING | 15 | ✅ | `PENDENTE` |
| 11 | `status_safer` | Status no sistema antifraude Safer | STRING | 15 | ✅ | `APROVADO` |

---

## 3. 🔍 Como Preencher Cada Campo

### 💰 Campo 1: `preco`
```
Nome:        preco
Tipo:        DECIMAL
Tamanho:     18,8 (até 18 dígitos, sendo 8 decimais)
Obrigatório: SIM
Rastreável:  SIM (vinculado ao id_ordem)
```
> **📝 Como preencher:** Informe o valor monetário da transação em formato numérico.
> Use ponto (`.`) como separador decimal. Suporta até 8 casas decimais para acomodar valores de criptomoedas.

| ✅ Correto | ❌ Errado |
|-----------|----------|
| `150.00` | `R$ 150,00` |
| `0.00015000` | `150,00` |
| `45230.50` | `cento e cinquenta` |

---

### 🪙 Campo 2: `quantidade_de_moedas`
```
Nome:        quantidade_de_moedas
Tipo:        DECIMAL
Tamanho:     18,8 (até 8 casas decimais)
Obrigatório: SIM
Rastreável:  SIM (vinculado ao id_ordem)
```
> **📝 Como preencher:** Informe a quantidade de criptomoedas negociadas.
> BTC usa até 8 casas decimais (Satoshis).

| ✅ Correto | ❌ Errado |
|-----------|----------|
| `0.00100000` | `1 moeda` |
| `2.50000000` | `0,001` |
| `0.00000001` | `dois e meio` |

---

### 📅 Campo 3: `criacao_ordem`
```
Nome:        criacao_ordem
Tipo:        DATETIME
Tamanho:     19 caracteres
Obrigatório: SIM
Formato:     YYYY-MM-DDTHH:MM:SS (ISO 8601, UTC)
```
> **📝 Como preencher:** Data e hora em que a ordem foi criada no sistema.
> Use o formato ISO 8601 no fuso horário UTC.

| ✅ Correto | ❌ Errado |
|-----------|----------|
| `2024-01-15T10:30:00` | `15/01/2024 10:30` |
| `2024-06-30T23:59:59` | `2024-01-15` |
| `2024-03-01T08:00:00` | `jan 15 2024` |

---

### 📅 Campo 4: `confirmacao_ordem`
```
Nome:        confirmacao_ordem
Tipo:        DATETIME
Tamanho:     19 caracteres
Obrigatório: NÃO (preenchido após execução)
Formato:     YYYY-MM-DDTHH:MM:SS (ISO 8601, UTC)
```
> **📝 Como preencher:** Data e hora em que a ordem foi confirmada/executada.
> Deixe **em branco** se a ordem ainda não foi confirmada.

| ✅ Correto | ❌ Errado |
|-----------|----------|
| `2024-01-15T10:30:05` | `15/01/2024 10:30:05` |
| *(vazio)* se pendente | `em andamento` |

---

### 💳 Campo 5: `tipo_transacao`
```
Nome:        tipo_transacao
Tipo:        STRING
Tamanho:     6 caracteres
Obrigatório: SIM
Valores:     DEBITO | CREDITO
```
> **📝 Como preencher:** Indica se a transação representa uma saída (DEBITO) ou entrada (CREDITO) de recursos na conta do usuário.

| Valor | Quando usar |
|-------|------------|
| `DEBITO` | Compra de criptomoeda (saída de dinheiro) |
| `CREDITO` | Venda de criptomoeda (entrada de dinheiro) |

---

### 👤 Campo 6: `id_usuario`
```
Nome:        id_usuario
Tipo:        STRING (UUID)
Tamanho:     36 caracteres
Obrigatório: SIM
Formato:     UUID v4
```
> **📝 Como preencher:** Código único do usuário que realizou a transação. Este código é gerado automaticamente pelo sistema.

| ✅ Correto | ❌ Errado |
|-----------|----------|
| `usr-123e4567-e89b-12d3-a456-426614174000` | `João Silva` |
| `123e4567-e89b-12d3-a456-426614174000` | `12345` |

---

### 📈 Campo 7: `tipo_ordem`
```
Nome:        tipo_ordem
Tipo:        STRING
Tamanho:     6 caracteres
Obrigatório: SIM
Valores:     COMPRA | VENDA
```
> **📝 Como preencher:** Indica se é uma ordem de compra ou venda de criptomoeda.

| Valor | Quando usar |
|-------|------------|
| `COMPRA` | Usuário está comprando criptomoeda |
| `VENDA` | Usuário está vendendo criptomoeda |

---

### 🪙 Campo 8: `tipo_ativo`
```
Nome:        tipo_ativo
Tipo:        STRING
Tamanho:     10 caracteres
Obrigatório: SIM
Valores:     BTC | ETH | USDT | BNB | SOL | (outros)
```
> **📝 Como preencher:** Sigla oficial da criptomoeda sendo negociada.

| Sigla | Criptomoeda |
|-------|------------|
| `BTC` | Bitcoin |
| `ETH` | Ethereum |
| `USDT` | Tether (Dólar Digital) |
| `BNB` | BNB (Binance Coin) |
| `SOL` | Solana |

---

### 🔑 Campo 9: `id_ordem`
```
Nome:        id_ordem
Tipo:        STRING (UUID)
Tamanho:     36 caracteres
Obrigatório: SIM
Formato:     UUID v4
```
> **📝 Como preencher:** Identificador único da ordem de negociação. É a **chave principal** desta transação e gerado automaticamente pelo sistema.

| ✅ Correto | ❌ Errado |
|-----------|----------|
| `ord-550e8400-e29b-41d4-a716-446655440000` | `ordem1` |
| `550e8400-e29b-41d4-a716-446655440000` | `1`, `2`, `3` |

---

### 📊 Campo 10: `status_ordem`
```
Nome:        status_ordem
Tipo:        STRING
Tamanho:     15 caracteres
Obrigatório: SIM
Valores:     PENDENTE | EXECUTADA | CANCELADA | EXPIRADA
```
> **📝 Como preencher:** Status atual da ordem no ciclo de vida da transação.

```
Ciclo de vida:
PENDENTE → EXECUTADA  ✅ (ordem executada com sucesso)
PENDENTE → CANCELADA  ❌ (ordem cancelada pelo usuário)
PENDENTE → EXPIRADA   ⏰ (ordem expirou sem execução)
```

| Valor | Significado |
|-------|------------|
| `PENDENTE` | Ordem criada, aguardando execução |
| `EXECUTADA` | Ordem concluída com sucesso |
| `CANCELADA` | Ordem cancelada pelo usuário ou sistema |
| `EXPIRADA` | Ordem expirou sem ser executada |

---

### 🛡️ Campo 11: `status_safer`
```
Nome:        status_safer
Tipo:        STRING
Tamanho:     15 caracteres
Obrigatório: SIM
Valores:     APROVADO | REPROVADO | PENDENTE | REVISAO
```
> **📝 Como preencher:** Resultado da análise antifraude pelo sistema Safer.

| Valor | Significado |
|-------|------------|
| `APROVADO` | Transação aprovada pelo Safer |
| `REPROVADO` | Transação bloqueada por suspeita de fraude |
| `PENDENTE` | Em análise pelo Safer |
| `REVISAO` | Requer revisão manual da equipe |

---

## 4. 🎯 Exemplos Práticos de Transações

### Exemplo 1: Compra de Bitcoin ✅

| Campo | Valor |
|-------|-------|
| `preco` | `150000.00` |
| `quantidade_de_moedas` | `0.00100000` |
| `criacao_ordem` | `2024-01-15T10:30:00` |
| `confirmacao_ordem` | `2024-01-15T10:30:05` |
| `tipo_transacao` | `DEBITO` |
| `id_usuario` | `usr-123e4567-e89b-12d3-a456` |
| `tipo_ordem` | `COMPRA` |
| `tipo_ativo` | `BTC` |
| `id_ordem` | `ord-550e8400-e29b-41d4-a716` |
| `status_ordem` | `EXECUTADA` |
| `status_safer` | `APROVADO` |

---

### Exemplo 2: Venda de Ethereum ✅

| Campo | Valor |
|-------|-------|
| `preco` | `18500.75` |
| `quantidade_de_moedas` | `2.50000000` |
| `criacao_ordem` | `2024-02-20T14:45:00` |
| `confirmacao_ordem` | `2024-02-20T14:45:03` |
| `tipo_transacao` | `CREDITO` |
| `id_usuario` | `usr-987fcdeb-51d2-43a1-b789-012345678901` |
| `tipo_ordem` | `VENDA` |
| `tipo_ativo` | `ETH` |
| `id_ordem` | `ord-11223344-5566-7788-99aa` |
| `status_ordem` | `EXECUTADA` |
| `status_safer` | `APROVADO` |

---

### Exemplo 3: Ordem Pendente (ainda não confirmada) ⏳

| Campo | Valor |
|-------|-------|
| `preco` | `5000.00` |
| `quantidade_de_moedas` | `1.00000000` |
| `criacao_ordem` | `2024-03-10T09:00:00` |
| `confirmacao_ordem` | *(vazio)* |
| `tipo_transacao` | `DEBITO` |
| `id_usuario` | `usr-aabbccdd-eeff-0011-2233` |
| `tipo_ordem` | `COMPRA` |
| `tipo_ativo` | `ETH` |
| `id_ordem` | `ord-aabbccdd-1122-3344-5566` |
| `status_ordem` | `PENDENTE` |
| `status_safer` | `PENDENTE` |

---

## 5. ✔️ Checklist de Validação

Antes de finalizar o preenchimento, verifique:

### ✅ Campos Obrigatórios
- [ ] `preco` está preenchido com valor numérico positivo?
- [ ] `quantidade_de_moedas` está preenchido com valor numérico positivo?
- [ ] `criacao_ordem` está no formato `YYYY-MM-DDTHH:MM:SS`?
- [ ] `tipo_transacao` é `DEBITO` ou `CREDITO` (maiúsculas)?
- [ ] `id_usuario` está preenchido com o código do usuário?
- [ ] `tipo_ordem` é `COMPRA` ou `VENDA` (maiúsculas)?
- [ ] `tipo_ativo` está preenchido com a sigla da criptomoeda?
- [ ] `id_ordem` está preenchido com o código único da ordem?
- [ ] `status_ordem` usa um dos valores válidos (PENDENTE/EXECUTADA/CANCELADA/EXPIRADA)?
- [ ] `status_safer` usa um dos valores válidos (APROVADO/REPROVADO/PENDENTE/REVISAO)?

### ✅ Campos Opcionais
- [ ] `confirmacao_ordem` está no formato `YYYY-MM-DDTHH:MM:SS` (ou vazio se pendente)?

### ✅ Consistência dos Dados
- [ ] Se `status_ordem` é `EXECUTADA`, o campo `confirmacao_ordem` está preenchido?
- [ ] Se `tipo_ordem` é `COMPRA`, verifique se `tipo_transacao` reflete corretamente o fluxo financeiro da operação (geralmente `DEBITO`)?
- [ ] Se `tipo_ordem` é `VENDA`, verifique se `tipo_transacao` reflete corretamente o fluxo financeiro da operação (geralmente `CREDITO`)?
- [ ] O `tipo_ativo` informado é uma criptomoeda válida?

---

## 6. 📚 Domínios e Valores Permitidos

### Valores de `tipo_transacao`
| Valor | Descrição |
|-------|-----------|
| `DEBITO` | Saída de recursos (compra de criptomoeda) |
| `CREDITO` | Entrada de recursos (venda de criptomoeda) |

### Valores de `tipo_ordem`
| Valor | Descrição |
|-------|-----------|
| `COMPRA` | Aquisição de criptomoedas |
| `VENDA` | Alienação de criptomoedas |

### Valores de `tipo_ativo`
| Valor | Criptomoeda |
|-------|-------------|
| `BTC` | Bitcoin |
| `ETH` | Ethereum |
| `USDT` | Tether |
| `BNB` | BNB |
| `SOL` | Solana |
| `ADA` | Cardano |
| `DOT` | Polkadot |
| `MATIC` | Polygon |

### Valores de `status_ordem`
| Valor | Descrição |
|-------|-----------|
| `PENDENTE` | Aguardando execução |
| `EXECUTADA` | Concluída com sucesso |
| `CANCELADA` | Cancelada pelo usuário ou sistema |
| `EXPIRADA` | Prazo de execução encerrado |

### Valores de `status_safer`
| Valor | Descrição |
|-------|-----------|
| `APROVADO` | Aprovado pelo sistema antifraude |
| `REPROVADO` | Bloqueado por suspeita de fraude |
| `PENDENTE` | Em análise |
| `REVISAO` | Requer revisão manual |

---

## 📁 Arquivo CSV do Schema

O arquivo [`schema-transacoes-cripto.csv`](./schema-transacoes-cripto.csv) contém a estrutura completa do schema no seguinte formato:

| Coluna | Descrição |
|--------|-----------|
| `nome_campo` | Nome do campo no sistema |
| `descricao` | Descrição do campo |
| `tipo_dado` | Tipo de dado (DECIMAL, STRING, DATETIME) |
| `tamanho` | Tamanho máximo do campo |
| `obrigatorio` | Se o campo é obrigatório (SIM/NAO) |
| `rastreabilidade` | Campo de rastreabilidade vinculado |
| `dominio_valores` | Valores permitidos no campo |
| `exemplo` | Exemplo de preenchimento |
| `observacoes` | Notas adicionais sobre o campo |

> 💡 **Dica:** Para importar o CSV na planilha, use a opção **"Importar Dados"** e selecione o separador como **vírgula (,)**.

---

*Schema criado para o sistema de transações de criptomoedas — versão 1.0*
