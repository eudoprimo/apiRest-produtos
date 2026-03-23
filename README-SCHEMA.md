# 📊 Guia de Preenchimento do Schema de Transações de Criptomoedas

> **Para quem é este guia?** Para iniciantes que precisam preencher o schema de transações de criptomoedas na plataforma, seguindo as instruções e padrões de nomenclatura corretos.

---

## 📋 Índice

1. [O que é o Schema](#o-que-é-o-schema)
2. [Para que serve](#para-que-serve)
3. [Visão Geral](#visão-geral-do-processo)
4. [Tabela Completa Preenchida](#tabela-completa-preenchida)
5. [Explicação Campo por Campo](#explicação-campo-por-campo)
6. [Exemplos Práticos](#exemplos-práticos)
7. [Checklist de Validação](#checklist-de-validação)

---

## 🧩 O que é o Schema

O **schema** é uma estrutura que descreve e documenta os dados que serão registrados em um sistema. Pense nele como o "mapa" ou "modelo" que diz ao sistema:

- Quais informações existem
- Que tipo de informação é cada campo (número, texto, data…)
- Se o campo é obrigatório ou opcional
- Quais valores são permitidos
- Se o dado precisa ser rastreado (auditado)

---

## 🎯 Para que serve

Este schema serve para registrar **transações de criptomoedas** como compras e vendas de Bitcoin (BTC). Cada campo representa uma informação importante da operação, como:

- Quanto foi negociado (`preco`, `quantidade_de_moedas`)
- Quando aconteceu (`criacao_ordem`, `confirmacao_ordem`)
- Quem fez a operação (`id_usuario`)
- O que foi feito (`tipo_transacao`, `tipo_ordem`)
- Qual ativo (`tipo_ativo`)
- Como está a operação (`status_ordem`, `status_safer`)

---

## 🔄 Visão Geral do Processo

```
JSON com dados  →  Preencher Schema  →  Validar Schema  →  Gerar JSON  →  Importar
```

1. Você recebe os dados no formato JSON
2. Mapeia cada campo do JSON para uma linha no schema
3. Preenche todas as colunas obrigatórias
4. Valida o schema (botão "Validar Schema")
5. Gera o JSON de configuração (botão "Gerar JSON")
6. Importa o JSON gerado no sistema

---

## 📊 Tabela Completa Preenchida

Abaixo está a tabela completa com todos os 11 campos do JSON já preenchidos. **Você pode copiar e colar esta tabela na sua planilha!**

| Nome do Campo | Nome Técnico | Descrição | Tipo | Rastreabilidade | Obrigatoriedade | Lista de Valores | Formato | Exemplo |
|---|---|---|---|---|---|---|---|---|
| Preço | `preco` | Valor monetário da transação em reais (BRL) | `number` | ✅ Sim | 🔴 Obrigatório | — | `0.00` | `15000.50` |
| Quantidade de Moedas | `quantidade_de_moedas` | Quantidade de moedas negociadas na transação | `number` | ✅ Sim | 🔴 Obrigatório | — | `0.00000000` | `0.05000000` |
| Data de Criação da Ordem | `criacao_ordem` | Data e hora em que a ordem foi criada | `datetime` | ✅ Sim | 🔴 Obrigatório | — | `yyyy-MM-dd'T'HH:mm:ss` | `2024-01-15T10:30:00` |
| Data de Confirmação da Ordem | `confirmacao_ordem` | Data e hora em que a ordem foi confirmada | `datetime` | ✅ Sim | 🟡 Opcional | — | `yyyy-MM-dd'T'HH:mm:ss` | `2024-01-15T10:31:05` |
| Tipo de Transação | `tipo_transacao` | Se a transação é débito ou crédito | `string` | ✅ Sim | 🔴 Obrigatório | `debito`, `credito` | — | `debito` |
| Identificador do Usuário | `id_usuario` | Código único do usuário | `string` | ✅ Sim | 🔴 Obrigatório | — | — | `USR-00123` |
| Tipo de Ordem | `tipo_ordem` | Se a ordem é de compra ou venda | `string` | ✅ Sim | 🔴 Obrigatório | `compra`, `venda` | — | `compra` |
| Tipo de Ativo | `tipo_ativo` | Sigla da criptomoeda negociada | `string` | ✅ Sim | 🔴 Obrigatório | `BTC`, `ETH`, `USDT`, `BNB` | — | `BTC` |
| Identificador da Ordem | `id_ordem` | Código único da ordem | `string` | ✅ Sim | 🔴 Obrigatório | — | — | `ORD-20240115-001` |
| Status da Ordem | `status_ordem` | Status atual da ordem no sistema | `string` | ✅ Sim | 🔴 Obrigatório | `pendente`, `processando`, `concluida`, `cancelada`, `erro` | — | `concluida` |
| Status Safer | `status_safer` | Status no sistema de análise de risco | `string` | ✅ Sim | 🔴 Obrigatório | `aprovado`, `reprovado`, `pendente_analise`, `em_analise` | — | `aprovado` |

---

## 📖 Explicação Campo por Campo

### 💰 1. Preço (`preco`)

| Atributo | Valor |
|---|---|
| **Nome no Schema** | `preco` |
| **Por que esse nome** | Nome em português minúsculo, sem acentos, seguindo o padrão snake_case |
| **Descrição** | Valor monetário da transação de criptomoeda em reais (BRL) |
| **Tipo de dado** | `number` |
| **Rastreabilidade** | ✅ Sim — valores monetários devem ser auditados |
| **Obrigatoriedade** | 🔴 Obrigatório — toda transação tem um preço |
| **Valor mínimo** | `0.01` |
| **Exemplo** | `15000.50` |

---

### 🪙 2. Quantidade de Moedas (`quantidade_de_moedas`)

| Atributo | Valor |
|---|---|
| **Nome no Schema** | `quantidade_de_moedas` |
| **Por que esse nome** | Descreve claramente o que representa, em snake_case |
| **Descrição** | Quantidade de moedas negociadas na transação |
| **Tipo de dado** | `number` |
| **Rastreabilidade** | ✅ Sim — quantidade negociada deve ser auditada |
| **Obrigatoriedade** | 🔴 Obrigatório — toda transação tem uma quantidade |
| **Valor mínimo** | `0.00000001` (menor unidade de BTC = 1 satoshi) |
| **Exemplo** | `0.05000000` |

---

### 📅 3. Data de Criação da Ordem (`criacao_ordem`)

| Atributo | Valor |
|---|---|
| **Nome no Schema** | `criacao_ordem` |
| **Por que esse nome** | Indica o momento de criação, em snake_case |
| **Descrição** | Data e hora em que a ordem foi criada no sistema |
| **Tipo de dado** | `datetime` |
| **Rastreabilidade** | ✅ Sim — datas de criação são essenciais para auditoria |
| **Obrigatoriedade** | 🔴 Obrigatório — toda ordem tem uma data de criação |
| **Formato** | `yyyy-MM-dd'T'HH:mm:ss` |
| **Exemplo** | `2024-01-15T10:30:00` |

---

### 📅 4. Data de Confirmação da Ordem (`confirmacao_ordem`)

| Atributo | Valor |
|---|---|
| **Nome no Schema** | `confirmacao_ordem` |
| **Por que esse nome** | Indica o momento de confirmação, em snake_case |
| **Descrição** | Data e hora em que a ordem foi confirmada pelo sistema |
| **Tipo de dado** | `datetime` |
| **Rastreabilidade** | ✅ Sim — data de confirmação é auditável |
| **Obrigatoriedade** | 🟡 Opcional — ordem pode ainda não ter sido confirmada |
| **Formato** | `yyyy-MM-dd'T'HH:mm:ss` |
| **Exemplo** | `2024-01-15T10:31:05` |

> ⚠️ **Atenção:** Este é o único campo opcional. A ordem pode ser criada mas ainda não confirmada.

---

### 💳 5. Tipo de Transação (`tipo_transacao`)

| Atributo | Valor |
|---|---|
| **Nome no Schema** | `tipo_transacao` |
| **Por que esse nome** | Classifica o tipo da transação financeira |
| **Descrição** | Indica se a transação é um débito ou crédito na carteira |
| **Tipo de dado** | `string` |
| **Rastreabilidade** | ✅ Sim — tipo de operação deve ser rastreado |
| **Obrigatoriedade** | 🔴 Obrigatório |
| **Lista de valores** | `debito` \| `credito` |
| **Exemplo** | `debito` |

> 📝 **Dica:** Em uma **compra**, o tipo_transacao é `debito` (sai dinheiro). Em uma **venda**, é `credito` (entra dinheiro).

---

### 👤 6. Identificador do Usuário (`id_usuario`)

| Atributo | Valor |
|---|---|
| **Nome no Schema** | `id_usuario` |
| **Por que esse nome** | Identificador único do usuário, em snake_case |
| **Descrição** | Código único que identifica o usuário da transação |
| **Tipo de dado** | `string` |
| **Rastreabilidade** | ✅ Sim — obrigatório para auditoria e compliance |
| **Obrigatoriedade** | 🔴 Obrigatório — toda transação precisa ter um dono |
| **Exemplo** | `USR-00123` |

---

### 🛒 7. Tipo de Ordem (`tipo_ordem`)

| Atributo | Valor |
|---|---|
| **Nome no Schema** | `tipo_ordem` |
| **Por que esse nome** | Classifica o tipo da ordem no livro de ordens |
| **Descrição** | Indica se a ordem é de compra ou venda de criptomoeda |
| **Tipo de dado** | `string` |
| **Rastreabilidade** | ✅ Sim — tipo de ordem deve ser rastreado |
| **Obrigatoriedade** | 🔴 Obrigatório |
| **Lista de valores** | `compra` \| `venda` |
| **Exemplo** | `compra` |

---

### 🪙 8. Tipo de Ativo (`tipo_ativo`)

| Atributo | Valor |
|---|---|
| **Nome no Schema** | `tipo_ativo` |
| **Por que esse nome** | Identifica qual ativo (criptomoeda) foi negociado |
| **Descrição** | Sigla da criptomoeda negociada na transação |
| **Tipo de dado** | `string` |
| **Rastreabilidade** | ✅ Sim — qual ativo foi negociado é essencial |
| **Obrigatoriedade** | 🔴 Obrigatório |
| **Lista de valores** | `BTC`, `ETH`, `USDT`, `BNB` (e outros conforme suportado) |
| **Exemplo** | `BTC` |

---

### 🔑 9. Identificador da Ordem (`id_ordem`)

| Atributo | Valor |
|---|---|
| **Nome no Schema** | `id_ordem` |
| **Por que esse nome** | Identificador único da ordem no sistema |
| **Descrição** | Código único que identifica a ordem de transação |
| **Tipo de dado** | `string` |
| **Rastreabilidade** | ✅ Sim — ID de ordem é a chave de rastreamento |
| **Obrigatoriedade** | 🔴 Obrigatório |
| **Exemplo** | `ORD-20240115-001` |

---

### 📊 10. Status da Ordem (`status_ordem`)

| Atributo | Valor |
|---|---|
| **Nome no Schema** | `status_ordem` |
| **Por que esse nome** | Indica o estado atual da ordem no fluxo |
| **Descrição** | Status atual da ordem no sistema de transações |
| **Tipo de dado** | `string` |
| **Rastreabilidade** | ✅ Sim — status deve ser auditado para rastrear o fluxo |
| **Obrigatoriedade** | 🔴 Obrigatório |
| **Lista de valores** | `pendente` \| `processando` \| `concluida` \| `cancelada` \| `erro` |
| **Exemplo** | `concluida` |

---

### 🛡️ 11. Status Safer (`status_safer`)

| Atributo | Valor |
|---|---|
| **Nome no Schema** | `status_safer` |
| **Por que esse nome** | Status específico do sistema anti-fraude "Safer" |
| **Descrição** | Status da operação no sistema de análise de risco Safer |
| **Tipo de dado** | `string` |
| **Rastreabilidade** | ✅ Sim — análise de risco deve ser sempre auditada |
| **Obrigatoriedade** | 🔴 Obrigatório |
| **Lista de valores** | `aprovado` \| `reprovado` \| `pendente_analise` \| `em_analise` |
| **Exemplo** | `aprovado` |

---

## 🎯 Seções Visuais Destacadas

### ✅ Campos Obrigatórios (10 de 11)

Todos os campos são obrigatórios, **exceto** `confirmacao_ordem`:

- ✅ `preco`
- ✅ `quantidade_de_moedas`
- ✅ `criacao_ordem`
- 🟡 `confirmacao_ordem` ← **único opcional**
- ✅ `tipo_transacao`
- ✅ `id_usuario`
- ✅ `tipo_ordem`
- ✅ `tipo_ativo`
- ✅ `id_ordem`
- ✅ `status_ordem`
- ✅ `status_safer`

---

### 🔍 Campos Rastreáveis (todos os 11)

Todos os campos possuem rastreabilidade ativada, pois transações financeiras de criptomoedas exigem auditoria completa:

| Campo | Motivo da Rastreabilidade |
|---|---|
| `preco` | Valor monetário — auditoria financeira obrigatória |
| `quantidade_de_moedas` | Quantidade negociada — controle de volume |
| `criacao_ordem` | Timestamp de criação — linha do tempo da operação |
| `confirmacao_ordem` | Timestamp de confirmação — confirmação da operação |
| `tipo_transacao` | Débito/crédito — compliance financeiro |
| `id_usuario` | Identifica o responsável — LGPD e compliance |
| `tipo_ordem` | Compra/venda — registro do tipo de operação |
| `tipo_ativo` | Qual cripto — rastreamento por ativo |
| `id_ordem` | Chave única — rastreamento fim-a-fim |
| `status_ordem` | Fluxo da ordem — histórico de estados |
| `status_safer` | Análise de risco — compliance anti-fraude |

---

### 📝 Campos com Lista de Valores

Estes campos aceitam **apenas valores pré-definidos**. Não use outros valores!

| Campo | Valores Permitidos |
|---|---|
| `tipo_transacao` | `debito`, `credito` |
| `tipo_ordem` | `compra`, `venda` |
| `tipo_ativo` | `BTC`, `ETH`, `USDT`, `BNB` |
| `status_ordem` | `pendente`, `processando`, `concluida`, `cancelada`, `erro` |
| `status_safer` | `aprovado`, `reprovado`, `pendente_analise`, `em_analise` |

---

### 📅 Campos de Data/Hora

| Campo | Formato | Exemplo |
|---|---|---|
| `criacao_ordem` | `yyyy-MM-dd'T'HH:mm:ss` | `2024-01-15T10:30:00` |
| `confirmacao_ordem` | `yyyy-MM-dd'T'HH:mm:ss` | `2024-01-15T10:31:05` |

> 💡 **Como ler o formato:**
> - `yyyy` = ano com 4 dígitos (ex: `2024`)
> - `MM` = mês com 2 dígitos (ex: `01` para janeiro)
> - `dd` = dia com 2 dígitos (ex: `15`)
> - `T` = separador entre data e hora (sempre letra T maiúscula)
> - `HH` = hora com 2 dígitos no formato 24h (ex: `10`)
> - `mm` = minutos com 2 dígitos (ex: `30`)
> - `ss` = segundos com 2 dígitos (ex: `00`)

---

## 🧪 Exemplos Práticos

### Exemplo 1: Transação de COMPRA de Bitcoin

**Situação:** O usuário USR-00123 compra 0.05 BTC por R$ 15.000,50

```json
{
    "id_ordem": "ORD-20240115-001",
    "id_usuario": "USR-00123",
    "tipo_ordem": "compra",
    "tipo_ativo": "BTC",
    "tipo_transacao": "debito",
    "preco": 15000.50,
    "quantidade_de_moedas": 0.05000000,
    "criacao_ordem": "2024-01-15T10:30:00",
    "confirmacao_ordem": "2024-01-15T10:31:05",
    "status_ordem": "concluida",
    "status_safer": "aprovado"
}
```

**Como isso fica no schema (linha da planilha):**

| Campo | Valor |
|---|---|
| `id_ordem` | `ORD-20240115-001` |
| `id_usuario` | `USR-00123` |
| `tipo_ordem` | `compra` |
| `tipo_ativo` | `BTC` |
| `tipo_transacao` | `debito` ← comprou, então **saiu** dinheiro da carteira |
| `preco` | `15000.50` |
| `quantidade_de_moedas` | `0.05000000` |
| `criacao_ordem` | `2024-01-15T10:30:00` |
| `confirmacao_ordem` | `2024-01-15T10:31:05` |
| `status_ordem` | `concluida` |
| `status_safer` | `aprovado` |

---

### Exemplo 2: Transação de VENDA de Bitcoin

**Situação:** O usuário USR-00456 vende 0.02 BTC por R$ 6.200,00

```json
{
    "id_ordem": "ORD-20240115-002",
    "id_usuario": "USR-00456",
    "tipo_ordem": "venda",
    "tipo_ativo": "BTC",
    "tipo_transacao": "credito",
    "preco": 6200.00,
    "quantidade_de_moedas": 0.02000000,
    "criacao_ordem": "2024-01-15T14:00:00",
    "confirmacao_ordem": "2024-01-15T14:00:45",
    "status_ordem": "concluida",
    "status_safer": "aprovado"
}
```

**Como isso fica no schema (linha da planilha):**

| Campo | Valor |
|---|---|
| `id_ordem` | `ORD-20240115-002` |
| `id_usuario` | `USR-00456` |
| `tipo_ordem` | `venda` |
| `tipo_ativo` | `BTC` |
| `tipo_transacao` | `credito` ← vendeu, então **entrou** dinheiro na carteira |
| `preco` | `6200.00` |
| `quantidade_de_moedas` | `0.02000000` |
| `criacao_ordem` | `2024-01-15T14:00:00` |
| `confirmacao_ordem` | `2024-01-15T14:00:45` |
| `status_ordem` | `concluida` |
| `status_safer` | `aprovado` |

---

### Exemplo 3: Ordem em Análise (ainda não confirmada)

**Situação:** Ordem criada mas aguardando análise de risco

```json
{
    "id_ordem": "ORD-20240115-003",
    "id_usuario": "USR-00789",
    "tipo_ordem": "compra",
    "tipo_ativo": "BTC",
    "tipo_transacao": "debito",
    "preco": 50000.00,
    "quantidade_de_moedas": 0.15000000,
    "criacao_ordem": "2024-01-15T16:00:00",
    "confirmacao_ordem": null,
    "status_ordem": "pendente",
    "status_safer": "em_analise"
}
```

> ⚠️ Note que `confirmacao_ordem` é `null` porque a ordem ainda não foi confirmada — este é o único campo que pode ficar vazio!

---

### 🔄 Como os dados fluem do JSON para o Schema

```
JSON Original                    Schema (Planilha)
─────────────                    ─────────────────
"preco"                   →      Coluna: Nome Técnico = preco
                                 Coluna: Tipo = number
                                 Coluna: Rastreabilidade = sim
                                 Coluna: Obrigatório = sim

"tipo_transacao"          →      Coluna: Nome Técnico = tipo_transacao
                                 Coluna: Tipo = string
                                 Coluna: Lista de Valores = debito,credito
                                 Coluna: Rastreabilidade = sim
```

---

## ✔️ Checklist de Validação

Use esta lista para verificar se seu schema foi preenchido corretamente:

### Nomenclatura
- [ ] Todos os nomes técnicos estão em minúsculas
- [ ] Palavras separadas por underline (`_`) — snake_case
- [ ] Sem acentos nos nomes técnicos (ex: `criacao` e não `criação`)
- [ ] Sem espaços nos nomes técnicos

### Tipos de Dados
- [ ] Valores monetários (`preco`) estão como `number`
- [ ] Quantidades (`quantidade_de_moedas`) estão como `number`
- [ ] Datas (`criacao_ordem`, `confirmacao_ordem`) estão como `datetime`
- [ ] Identificadores e status estão como `string`

### Rastreabilidade
- [ ] Todos os 11 campos têm rastreabilidade marcada como `sim`

### Obrigatoriedade
- [ ] 10 campos estão marcados como `obrigatório`
- [ ] Apenas `confirmacao_ordem` está marcado como `opcional`

### Lista de Valores
- [ ] `tipo_transacao` tem a lista: `debito,credito`
- [ ] `tipo_ordem` tem a lista: `compra,venda`
- [ ] `tipo_ativo` tem a lista com as criptomoedas suportadas
- [ ] `status_ordem` tem a lista completa de status
- [ ] `status_safer` tem a lista completa de status

### Campos Numéricos
- [ ] `preco` tem valor mínimo definido (`0.01`)
- [ ] `quantidade_de_moedas` tem valor mínimo definido (`0.00000001`)

### Revisão Final
- [ ] Todos os 11 campos do JSON estão no schema
- [ ] Cada campo tem descrição clara em português
- [ ] Exemplos de valores estão preenchidos
- [ ] Schema foi validado na plataforma (botão "Validar Schema")
- [ ] JSON foi gerado com sucesso (botão "Gerar JSON")

---

## 📚 Referências

### Campos do JSON Mapeados

| Campo JSON | Nome Técnico Schema | Tipo |
|---|---|---|
| `preco` | `preco` | number |
| `quantidade_de_moedas` | `quantidade_de_moedas` | number |
| `criacao_ordem` | `criacao_ordem` | datetime |
| `confirmacao_ordem` | `confirmacao_ordem` | datetime |
| `tipo_transacao` | `tipo_transacao` | string |
| `id_usuario` | `id_usuario` | string |
| `tipo_ordem` | `tipo_ordem` | string |
| `tipo_ativo` | `tipo_ativo` | string |
| `id_ordem` | `id_ordem` | string |
| `status_ordem` | `status_ordem` | string |
| `status_safer` | `status_safer` | string |

### Regras de Nomenclatura Aplicadas

1. **snake_case**: palavras separadas por underline (`_`)
2. **Minúsculas**: todos os nomes técnicos em letras minúsculas
3. **Sem acentos**: `criacao` em vez de `criação`, `confirmacao` em vez de `confirmação`
4. **Descritivos**: nomes que descrevem claramente o conteúdo do campo

### Arquivo do Schema

O arquivo `schema-transacoes-cripto.csv` contém o schema completo pronto para importar ou copiar para a planilha.

---

> 💬 **Dúvidas?** Revise cada campo neste guia e compare com o checklist de validação. Todos os campos foram preenchidos seguindo as instruções e padrões da plataforma.
