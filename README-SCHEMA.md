# 📋 Guia Completo de Preenchimento do Schema de Transações

> **Para quem é este guia?**
> Este guia foi feito para usuários iniciantes que precisam preencher a planilha de Schema com os campos do JSON de transações de criptomoedas. Siga o passo a passo com calma — cada item é explicado com exemplos visuais e dicas práticas.

---

## 📖 Índice

1. [O que é o Schema e para que serve?](#1-o-que-é-o-schema-e-para-que-serve)
2. [Entendendo as colunas da planilha](#2-entendendo-as-colunas-da-planilha)
3. [Tabela de Preenchimento — Copie e Cole](#3-tabela-de-preenchimento--copie-e-cole)
4. [Instrução detalhada campo a campo](#4-instrução-detalhada-campo-a-campo)
5. [Exemplo de mockup visual preenchido](#5-exemplo-de-mockup-visual-preenchido)
6. [Exemplo de JSON Schema gerado](#6-exemplo-de-json-schema-gerado)
7. [Dicas para conferência final](#7-dicas-para-conferência-final)
8. [✅ Checklist: Confira, Salve, Valide](#8--checklist-confira-salve-valide)

---

## 1. O que é o Schema e para que serve?

O **Schema** é como um "mapa" que descreve cada campo do seu JSON. Ele informa ao sistema:

- **Qual é o nome** do campo (ex: `preco`)
- **Que tipo de dado** ele guarda (número, texto, data...)
- **O que ele representa** (ex: valor da transação)
- **Se é obrigatório** ou opcional
- **Se pode ser rastreado** para auditoria

> 💡 **Analogia:** Pense no Schema como o cabeçalho de uma tabela. Se você tem uma planilha de vendas, o Schema seria como definir que a coluna A se chama "Preço", é do tipo número e é obrigatória.

---

## 2. Entendendo as colunas da planilha

Ao abrir a aba **Schema** na planilha, você verá as seguintes colunas. Preencha cada uma conforme explicado:

| Coluna             | O que colocar                                                             |
|--------------------|---------------------------------------------------------------------------|
| **Nome do Campo**  | O nome exato do campo no JSON (use letras minúsculas e underline `_`)     |
| **Descrição**      | Explique em poucas palavras o que o campo representa                      |
| **Tipo**           | O tipo do dado: `string`, `number`, `date`, `boolean`                     |
| **Obrigatório**    | `Sim` se o campo sempre deve estar presente, `Não` se pode ficar em branco|
| **Rastreável**     | `Sim` se o campo é importante para auditoria ou busca futura              |
| **Exemplo**        | Um valor de exemplo real para o campo                                      |

---

## 3. Tabela de Preenchimento — Copie e Cole

> 📌 **Como usar:** Copie a tabela abaixo e cole diretamente na aba **Schema** da sua planilha. Cada linha representa um campo do JSON.

```
Nome do Campo         | Descrição                              | Tipo    | Obrigatório | Rastreável | Exemplo
preco                 | Valor da transação em reais            | number  | Sim         | Sim        | 100000.75
quantidade_de_moedas  | Quantidade de moedas negociadas        | number  | Sim         | Sim        | 2.50
criacao_ordem         | Data e hora de criação da ordem        | date    | Sim         | Sim        | 2026-03-23T14:00:00Z
confirmacao_ordem     | Data e hora de confirmação da ordem    | date    | Sim         | Sim        | 2026-03-23T14:30:00Z
tipo_transacao        | Tipo da transação: debito ou credito   | string  | Sim         | Sim        | debito
id_usuario            | Identificador único do usuário         | string  | Sim         | Sim        | user12345
tipo_ordem            | Tipo de ordem: compra ou venda         | string  | Sim         | Sim        | compra
tipo_ativo            | Tipo de criptomoeda (ex: BTC)          | string  | Sim         | Sim        | BTC
id_ordem              | Identificador único da ordem           | string  | Sim         | Sim        | ord56789
status_ordem          | Status atual da operação               | string  | Sim         | Sim        | concluida
status_safer          | Status da operação no sistema Safer    | string  | Sim         | Sim        | aprovado
```

---

## 4. Instrução detalhada campo a campo

Abaixo está a explicação linha a linha de cada campo que você precisa preencher. Leia antes de começar a preencher na planilha.

---

### 🔹 Campo 1: `preco`

```
┌─────────────────────────────────────────────────────────────────┐
│  Nome do Campo  │  preco                                        │
│  Descrição      │  Valor da transação em reais                  │
│  Tipo           │  number                                       │
│  Obrigatório    │  Sim                                          │
│  Rastreável     │  Sim                                          │
│  Exemplo        │  100000.75                                    │
└─────────────────────────────────────────────────────────────────┘
```

**O que é:** O valor em dinheiro (reais ou moeda configurada) da transação de criptomoedas.

**Como preencher:**
- **Nome:** escreva `preco` (tudo minúsculo, sem acento)
- **Tipo:** `number` — porque é um valor numérico com decimais
- **Exemplo:** `100000.75` (use ponto `.` para decimais, não vírgula)

**⚠️ Atenção:** Não coloque `R$` nem vírgula. Apenas o número, ex: `250000.50`

---

### 🔹 Campo 2: `quantidade_de_moedas`

```
┌─────────────────────────────────────────────────────────────────┐
│  Nome do Campo  │  quantidade_de_moedas                         │
│  Descrição      │  Quantidade de moedas negociadas              │
│  Tipo           │  number                                       │
│  Obrigatório    │  Sim                                          │
│  Rastreável     │  Sim                                          │
│  Exemplo        │  2.50                                         │
└─────────────────────────────────────────────────────────────────┘
```

**O que é:** A quantidade de criptomoedas que foram compradas ou vendidas na transação.

**Como preencher:**
- **Nome:** escreva `quantidade_de_moedas` (com underscore `_` entre as palavras)
- **Tipo:** `number` — pode ter decimais (ex: 0.00123456 de BTC)
- **Exemplo:** `2.50`

**💡 Dica:** Criptomoedas como BTC aceitam muitas casas decimais (até 8). Exemplo: `0.00500000`

---

### 🔹 Campo 3: `criacao_ordem`

```
┌─────────────────────────────────────────────────────────────────┐
│  Nome do Campo  │  criacao_ordem                                │
│  Descrição      │  Data e hora de criação da ordem              │
│  Tipo           │  date                                         │
│  Obrigatório    │  Sim                                          │
│  Rastreável     │  Sim                                          │
│  Exemplo        │  2026-03-23T14:00:00Z                         │
└─────────────────────────────────────────────────────────────────┘
```

**O que é:** O momento exato (data e hora) em que a ordem de compra ou venda foi criada.

**Como preencher:**
- **Nome:** escreva `criacao_ordem` (sem acento em "criação")
- **Tipo:** `date`
- **Exemplo:** `2026-03-23T14:00:00Z` (formato ISO 8601 — padrão internacional de data/hora)

**💡 Explicação do formato:** `2026-03-23T14:00:00Z`
- `2026-03-23` = Ano-Mês-Dia
- `T` = separador entre data e hora
- `14:00:00` = Hora:Minuto:Segundo
- `Z` = fuso horário UTC (padrão)

---

### 🔹 Campo 4: `confirmacao_ordem`

```
┌─────────────────────────────────────────────────────────────────┐
│  Nome do Campo  │  confirmacao_ordem                            │
│  Descrição      │  Data e hora de confirmação da ordem          │
│  Tipo           │  date                                         │
│  Obrigatório    │  Sim                                          │
│  Rastreável     │  Sim                                          │
│  Exemplo        │  2026-03-23T14:30:00Z                         │
└─────────────────────────────────────────────────────────────────┘
```

**O que é:** O momento em que a ordem foi confirmada (processada com sucesso) pelo sistema.

**Como preencher:**
- **Nome:** escreva `confirmacao_ordem` (sem acento em "confirmação")
- **Tipo:** `date`
- **Exemplo:** `2026-03-23T14:30:00Z`

**⚠️ Atenção:** Este campo deve ter uma data/hora **depois** do campo `criacao_ordem`, pois a confirmação acontece após a criação.

---

### 🔹 Campo 5: `tipo_transacao`

```
┌─────────────────────────────────────────────────────────────────┐
│  Nome do Campo  │  tipo_transacao                               │
│  Descrição      │  Tipo da transação: debito ou credito         │
│  Tipo           │  string                                       │
│  Obrigatório    │  Sim                                          │
│  Rastreável     │  Sim                                          │
│  Exemplo        │  debito                                       │
└─────────────────────────────────────────────────────────────────┘
```

**O que é:** Indica se o dinheiro saiu da conta (`debito`) ou entrou na conta (`credito`).

**Como preencher:**
- **Nome:** escreva `tipo_transacao` (sem acento em "transação")
- **Tipo:** `string` — porque é um texto com valor fixo
- **Valores aceitos:** `debito` ou `credito` (sempre minúsculo, sem acento)

**💡 Exemplos práticos:**
- Você **comprou** BTC → o dinheiro **saiu** da sua conta → `debito`
- Você **vendeu** BTC → o dinheiro **entrou** na sua conta → `credito`

---

### 🔹 Campo 6: `id_usuario`

```
┌─────────────────────────────────────────────────────────────────┐
│  Nome do Campo  │  id_usuario                                   │
│  Descrição      │  Identificador único do usuário               │
│  Tipo           │  string                                       │
│  Obrigatório    │  Sim                                          │
│  Rastreável     │  Sim                                          │
│  Exemplo        │  user12345                                    │
└─────────────────────────────────────────────────────────────────┘
```

**O que é:** O código que identifica o usuário que realizou a transação no sistema.

**Como preencher:**
- **Nome:** escreva `id_usuario` (sem acento em "usuário")
- **Tipo:** `string` — porque é um código alfanumérico
- **Exemplo:** `user12345` ou `USR-98765` ou um UUID como `a1b2c3d4-e5f6-...`

**💡 Dica:** O `id_usuario` é importante para rastrear quem fez cada transação. Sempre marque como Rastreável = `Sim`.

---

### 🔹 Campo 7: `tipo_ordem`

```
┌─────────────────────────────────────────────────────────────────┐
│  Nome do Campo  │  tipo_ordem                                   │
│  Descrição      │  Tipo de ordem: compra ou venda               │
│  Tipo           │  string                                       │
│  Obrigatório    │  Sim                                          │
│  Rastreável     │  Sim                                          │
│  Exemplo        │  compra                                       │
└─────────────────────────────────────────────────────────────────┘
```

**O que é:** Indica se a ordem é de **compra** (aquisição de criptomoeda) ou **venda** (venda de criptomoeda).

**Como preencher:**
- **Nome:** escreva `tipo_ordem`
- **Tipo:** `string`
- **Valores aceitos:** `compra` ou `venda` (sempre minúsculo)

---

### 🔹 Campo 8: `tipo_ativo`

```
┌─────────────────────────────────────────────────────────────────┐
│  Nome do Campo  │  tipo_ativo                                   │
│  Descrição      │  Tipo de criptomoeda negociada                │
│  Tipo           │  string                                       │
│  Obrigatório    │  Sim                                          │
│  Rastreável     │  Sim                                          │
│  Exemplo        │  BTC                                          │
└─────────────────────────────────────────────────────────────────┘
```

**O que é:** O código da criptomoeda negociada na transação (Bitcoin, Ethereum, etc.).

**Como preencher:**
- **Nome:** escreva `tipo_ativo`
- **Tipo:** `string`
- **Exemplos de valores:** `BTC` (Bitcoin), `ETH` (Ethereum), `USDT` (Tether), `BRL` (Real)

**💡 Dica:** Use sempre as siglas em letras maiúsculas: `BTC`, `ETH`, `USDT`.

---

### 🔹 Campo 9: `id_ordem`

```
┌─────────────────────────────────────────────────────────────────┐
│  Nome do Campo  │  id_ordem                                     │
│  Descrição      │  Identificador único da ordem                 │
│  Tipo           │  string                                       │
│  Obrigatório    │  Sim                                          │
│  Rastreável     │  Sim                                          │
│  Exemplo        │  ord56789                                     │
└─────────────────────────────────────────────────────────────────┘
```

**O que é:** Um código único que identifica esta ordem específica no sistema.

**Como preencher:**
- **Nome:** escreva `id_ordem`
- **Tipo:** `string`
- **Exemplo:** `ord56789`, `ORD-2026-001`, ou UUID como `550e8400-e29b-41d4-a716-446655440000`

**⚠️ Atenção:** Cada ordem deve ter um `id_ordem` diferente. Nunca repita o mesmo código.

---

### 🔹 Campo 10: `status_ordem`

```
┌─────────────────────────────────────────────────────────────────┐
│  Nome do Campo  │  status_ordem                                 │
│  Descrição      │  Status atual da operação                     │
│  Tipo           │  string                                       │
│  Obrigatório    │  Sim                                          │
│  Rastreável     │  Sim                                          │
│  Exemplo        │  concluida                                    │
└─────────────────────────────────────────────────────────────────┘
```

**O que é:** Indica em que estado a ordem está no momento.

**Como preencher:**
- **Nome:** escreva `status_ordem`
- **Tipo:** `string`
- **Valores possíveis:**

| Valor        | Significado                                      |
|--------------|--------------------------------------------------|
| `pendente`   | A ordem foi criada mas ainda não foi processada  |
| `processando`| A ordem está sendo executada agora               |
| `concluida`  | A ordem foi executada com sucesso                |
| `cancelada`  | A ordem foi cancelada antes de ser executada     |
| `rejeitada`  | A ordem foi recusada pelo sistema                |

---

### 🔹 Campo 11: `status_safer`

```
┌─────────────────────────────────────────────────────────────────┐
│  Nome do Campo  │  status_safer                                 │
│  Descrição      │  Status da operação no sistema Safer          │
│  Tipo           │  string                                       │
│  Obrigatório    │  Sim                                          │
│  Rastreável     │  Sim                                          │
│  Exemplo        │  aprovado                                     │
└─────────────────────────────────────────────────────────────────┘
```

**O que é:** O status que o sistema de segurança/compliance "Safer" atribuiu a esta transação.

**Como preencher:**
- **Nome:** escreva `status_safer`
- **Tipo:** `string`
- **Valores possíveis:**

| Valor          | Significado                                          |
|----------------|------------------------------------------------------|
| `aprovado`     | A transação passou na verificação de segurança       |
| `em_analise`   | A transação está sendo analisada pelo Safer          |
| `rejeitado`    | A transação foi bloqueada pelo sistema de segurança  |
| `pendente`     | Aguardando análise do Safer                          |

---

## 5. Exemplo de mockup visual preenchido

Abaixo está uma simulação visual de como a sua planilha **Schema** deve ficar após o preenchimento completo:

```
╔══════════════════════╦════════════════════════════════════════╦═════════╦═════════════╦════════════╦══════════════════════════╗
║ Nome do Campo        ║ Descrição                              ║ Tipo    ║ Obrigatório ║ Rastreável ║ Exemplo                  ║
╠══════════════════════╬════════════════════════════════════════╬═════════╬═════════════╬════════════╬══════════════════════════╣
║ preco                ║ Valor da transação em reais            ║ number  ║ Sim         ║ Sim        ║ 100000.75                ║
╠══════════════════════╬════════════════════════════════════════╬═════════╬═════════════╬════════════╬══════════════════════════╣
║ quantidade_de_moedas ║ Quantidade de moedas negociadas        ║ number  ║ Sim         ║ Sim        ║ 2.50                     ║
╠══════════════════════╬════════════════════════════════════════╬═════════╬═════════════╬════════════╬══════════════════════════╣
║ criacao_ordem        ║ Data e hora de criação da ordem        ║ date    ║ Sim         ║ Sim        ║ 2026-03-23T14:00:00Z     ║
╠══════════════════════╬════════════════════════════════════════╬═════════╬═════════════╬════════════╬══════════════════════════╣
║ confirmacao_ordem    ║ Data e hora de confirmação da ordem    ║ date    ║ Sim         ║ Sim        ║ 2026-03-23T14:30:00Z     ║
╠══════════════════════╬════════════════════════════════════════╬═════════╬═════════════╬════════════╬══════════════════════════╣
║ tipo_transacao       ║ Tipo da transação: debito ou credito   ║ string  ║ Sim         ║ Sim        ║ debito                   ║
╠══════════════════════╬════════════════════════════════════════╬═════════╬═════════════╬════════════╬══════════════════════════╣
║ id_usuario           ║ Identificador único do usuário         ║ string  ║ Sim         ║ Sim        ║ user12345                ║
╠══════════════════════╬════════════════════════════════════════╬═════════╬═════════════╬════════════╬══════════════════════════╣
║ tipo_ordem           ║ Tipo de ordem: compra ou venda         ║ string  ║ Sim         ║ Sim        ║ compra                   ║
╠══════════════════════╬════════════════════════════════════════╬═════════╬═════════════╬════════════╬══════════════════════════╣
║ tipo_ativo           ║ Tipo de criptomoeda negociada          ║ string  ║ Sim         ║ Sim        ║ BTC                      ║
╠══════════════════════╬════════════════════════════════════════╬═════════╬═════════════╬════════════╬══════════════════════════╣
║ id_ordem             ║ Identificador único da ordem           ║ string  ║ Sim         ║ Sim        ║ ord56789                 ║
╠══════════════════════╬════════════════════════════════════════╬═════════╬═════════════╬════════════╬══════════════════════════╣
║ status_ordem         ║ Status atual da operação               ║ string  ║ Sim         ║ Sim        ║ concluida                ║
╠══════════════════════╬════════════════════════════════════════╬═════════╬═════════════╬════════════╬══════════════════════════╣
║ status_safer         ║ Status da operação no sistema Safer    ║ string  ║ Sim         ║ Sim        ║ aprovado                 ║
╚══════════════════════╩════════════════════════════════════════╩═════════╩═════════════╩════════════╩══════════════════════════╝
```

---

## 6. Exemplo de JSON Schema gerado

Após preencher a planilha e clicar em **"Gerar Schema"**, o sistema deve gerar um JSON Schema parecido com este:

```json
{
  "$schema": "http://json-schema.org/draft-07/schema#",
  "title": "Schema de Transação de Criptomoedas",
  "description": "Schema para validação de transações de compra e venda de criptomoedas",
  "type": "object",
  "required": [
    "preco",
    "quantidade_de_moedas",
    "criacao_ordem",
    "confirmacao_ordem",
    "tipo_transacao",
    "id_usuario",
    "tipo_ordem",
    "tipo_ativo",
    "id_ordem",
    "status_ordem",
    "status_safer"
  ],
  "properties": {
    "preco": {
      "type": "number",
      "description": "Valor da transação em reais",
      "example": 100000.75
    },
    "quantidade_de_moedas": {
      "type": "number",
      "description": "Quantidade de moedas negociadas",
      "example": 2.50
    },
    "criacao_ordem": {
      "type": "string",
      "format": "date-time",
      "description": "Data e hora de criação da ordem",
      "example": "2026-03-23T14:00:00Z"
    },
    "confirmacao_ordem": {
      "type": "string",
      "format": "date-time",
      "description": "Data e hora de confirmação da ordem",
      "example": "2026-03-23T14:30:00Z"
    },
    "tipo_transacao": {
      "type": "string",
      "enum": ["debito", "credito"],
      "description": "Tipo da transação: debito ou credito",
      "example": "debito"
    },
    "id_usuario": {
      "type": "string",
      "description": "Identificador único do usuário",
      "example": "user12345"
    },
    "tipo_ordem": {
      "type": "string",
      "enum": ["compra", "venda"],
      "description": "Tipo de ordem: compra ou venda",
      "example": "compra"
    },
    "tipo_ativo": {
      "type": "string",
      "description": "Tipo de criptomoeda negociada",
      "example": "BTC"
    },
    "id_ordem": {
      "type": "string",
      "description": "Identificador único da ordem",
      "example": "ord56789"
    },
    "status_ordem": {
      "type": "string",
      "enum": ["pendente", "processando", "concluida", "cancelada", "rejeitada"],
      "description": "Status atual da operação",
      "example": "concluida"
    },
    "status_safer": {
      "type": "string",
      "enum": ["aprovado", "em_analise", "rejeitado", "pendente"],
      "description": "Status da operação no sistema Safer",
      "example": "aprovado"
    }
  }
}
```

---

## 7. Dicas para conferência final

Antes de exportar ou gerar o schema, confira os seguintes pontos:

### ✍️ Dicas de nomenclatura
- ✅ Use **letras minúsculas** em todos os nomes de campo: `preco`, `id_ordem`
- ✅ Use **underline** (`_`) para separar palavras: `criacao_ordem`, `tipo_ativo`
- ❌ **Não use** espaços: ~~`id ordem`~~ — use `id_ordem`
- ❌ **Não use** acentos nos nomes: ~~`criação_ordem`~~ — use `criacao_ordem`
- ❌ **Não use** letras maiúsculas: ~~`PrecoTransacao`~~ — use `preco`

### 📅 Dicas para campos de data
- Use o formato **ISO 8601**: `2026-03-23T14:00:00Z`
- Sempre preencha com data e hora (não apenas a data)
- O `Z` no final significa fuso horário UTC (padrão internacional)

### 🔢 Dicas para campos numéricos
- Use **ponto** (`.`) para decimais, não vírgula: `100000.75` ✅ e não `100000,75` ❌
- Não coloque símbolo de moeda (`R$`, `$`, `BTC`)
- Números podem ter muitas casas decimais: `0.00012345`

### 🔤 Dicas para campos de texto (string)
- Use sempre **letras minúsculas** nos valores fixos: `debito`, `credito`, `compra`, `venda`
- Para IDs, mantenha o formato original do sistema
- Para siglas de moedas, use **maiúsculas**: `BTC`, `ETH`, `USDT`

### 🔍 Dicas de rastreabilidade
- Marque como **Rastreável = Sim** todos os campos usados em buscas ou relatórios
- Especialmente: `id_ordem`, `id_usuario`, `status_ordem`, `criacao_ordem`

---

## 8. ✅ Checklist: Confira, Salve, Valide

Use este checklist antes de finalizar. Marque cada item conforme você for conferindo:

### 📝 CONFERIR — Antes de salvar

- [ ] Todos os **11 campos** estão preenchidos na planilha
- [ ] Nenhum campo tem **espaço** ou **acento** no nome
- [ ] Todos os campos usam **underscore** (`_`) para separar palavras
- [ ] Os campos `preco` e `quantidade_de_moedas` estão com tipo `number`
- [ ] Os campos `criacao_ordem` e `confirmacao_ordem` estão com tipo `date`
- [ ] Os demais campos estão com tipo `string`
- [ ] Todos os campos têm **Obrigatório = Sim**
- [ ] Todos os campos têm **Rastreável = Sim**
- [ ] Os campos de **exemplo** estão com valores reais e válidos
- [ ] `tipo_transacao` tem exemplo `debito` ou `credito`
- [ ] `tipo_ordem` tem exemplo `compra` ou `venda`
- [ ] `tipo_ativo` tem exemplo `BTC` (ou outra sigla válida em MAIÚSCULAS)

### 💾 SALVAR — Após conferir

- [ ] Salve o arquivo com o nome `schema-transacoes` (ou conforme padrão do projeto)
- [ ] Salve nos formatos necessários: `.xlsx` (planilha) e/ou `.json`
- [ ] Faça backup do arquivo antes de exportar

### ✔️ VALIDAR — No sistema

- [ ] Clique no botão **"Validar Schema"** (ou equivalente na plataforma)
- [ ] Verifique se **nenhum erro** aparece na validação
- [ ] Se houver erros, corrija conforme as mensagens indicadas
- [ ] Clique em **"Gerar JSON Schema"** para exportar
- [ ] Abra o JSON gerado e confira se todos os campos aparecem corretamente
- [ ] Importe o schema no sistema de destino
- [ ] Teste com um registro real para confirmar que a validação funciona

---

## 📊 Resumo rápido — Referência dos 11 campos

| # | Campo                | Tipo   | Valores possíveis                              |
|---|----------------------|--------|------------------------------------------------|
| 1 | preco                | number | Qualquer valor numérico: `100000.75`           |
| 2 | quantidade_de_moedas | number | Qualquer valor numérico: `2.50`                |
| 3 | criacao_ordem        | date   | Formato ISO: `2026-03-23T14:00:00Z`            |
| 4 | confirmacao_ordem    | date   | Formato ISO: `2026-03-23T14:30:00Z`            |
| 5 | tipo_transacao       | string | `debito` ou `credito`                          |
| 6 | id_usuario           | string | ID único: `user12345`                          |
| 7 | tipo_ordem           | string | `compra` ou `venda`                            |
| 8 | tipo_ativo           | string | Sigla da moeda: `BTC`, `ETH`, `USDT`          |
| 9 | id_ordem             | string | ID único da ordem: `ord56789`                  |
|10 | status_ordem         | string | `pendente`, `processando`, `concluida`, `cancelada`, `rejeitada` |
|11 | status_safer         | string | `aprovado`, `em_analise`, `rejeitado`, `pendente` |

---

> 📞 **Precisa de ajuda?** Se encontrar algum erro ao gerar o schema ou ao validar no sistema, verifique primeiro se todos os campos da lista acima estão preenchidos corretamente. Em caso de dúvida, consulte as instruções originais ou entre em contato com o suporte técnico.
