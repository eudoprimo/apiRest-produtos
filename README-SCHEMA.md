# 📊 Guia Visual: Como Preencher o Schema de Transações de Criptomoedas

> **Para iniciantes** — Siga este guia passo a passo para preencher cada campo do JSON na planilha de Schema. Você pode copiar e colar os exemplos diretamente!

---

## 📋 Sumário

1. [O que é o Schema e para que serve?](#1-o-que-é-o-schema-e-para-que-serve)
2. [Checklist Visual — Campos do JSON na Planilha](#2-checklist-visual--campos-do-json-na-planilha)
3. [Instruções Campo a Campo](#3-instruções-campo-a-campo)
4. [Exemplo de JSON Pronto para Copiar](#4-exemplo-de-json-pronto-para-copiar)
5. [Modelo de Planilha (CSV) para Copiar e Colar](#5-modelo-de-planilha-csv-para-copiar-e-colar)
6. [Passo a Passo: Como Gerar o Schema](#6-passo-a-passo-como-gerar-o-schema)
7. [Checklist de Validação Final](#7-checklist-de-validação-final)

---

## 1. O que é o Schema e para que serve?

O **Schema** é como um "formulário de cadastro" para os campos que existem no seu sistema.
Cada linha da planilha (aba **Schema**) representa **um campo** do seu JSON.

Ao preencher o Schema corretamente, você consegue:
- ✅ Validar automaticamente os dados enviados
- ✅ Gerar documentação do sistema
- ✅ Importar o schema para outros sistemas via JSON
- ✅ Rastrear cada campo para auditoria

---

## 2. Checklist Visual — Campos do JSON na Planilha

A tabela abaixo mostra **como cada campo do seu JSON deve ser preenchido** nas colunas da planilha (aba Schema).

> 📌 **Como ler a tabela:** Cada linha é um campo do JSON. As colunas são as colunas da planilha que você precisa preencher.

```
╔══════════════════════════╦══════════════════════════════════════════╦══════════╦═════════════╦════════════╦════════════════════════╗
║  Nome do Campo           ║  Descrição                               ║  Tipo    ║  Obrigatório║  Rastreável║  Exemplo de Valor      ║
╠══════════════════════════╬══════════════════════════════════════════╬══════════╬═════════════╬════════════╬════════════════════════╣
║ [✓] preco                ║ Valor da transação em reais              ║ number   ║    Sim      ║    Sim     ║ 100000.75              ║
║ [✓] quantidade_de_moedas ║ Quantidade de moedas negociadas          ║ number   ║    Sim      ║    Sim     ║ 2.5                    ║
║ [✓] criacao_ordem        ║ Data e hora de criação da ordem          ║ datetime ║    Sim      ║    Sim     ║ 2026-03-23T14:00:00Z   ║
║ [✓] confirmacao_ordem    ║ Data e hora de confirmação da ordem      ║ datetime ║    Sim      ║    Sim     ║ 2026-03-23T14:30:00Z   ║
║ [✓] tipo_transacao       ║ Tipo da transação: debito ou credito     ║ string   ║    Sim      ║    Sim     ║ debito                 ║
║ [✓] id_usuario           ║ Identificador único do usuário           ║ string   ║    Sim      ║    Sim     ║ user12345              ║
║ [✓] tipo_ordem           ║ Tipo de ordem: compra ou venda           ║ string   ║    Sim      ║    Sim     ║ compra                 ║
║ [✓] tipo_ativo           ║ Tipo da moeda negociada (ex: BTC, ETH)   ║ string   ║    Sim      ║    Sim     ║ BTC                    ║
║ [✓] id_ordem             ║ Identificador único da ordem             ║ string   ║    Sim      ║    Sim     ║ ord56789               ║
║ [✓] status_ordem         ║ Status atual da operação                 ║ string   ║    Sim      ║    Sim     ║ concluida              ║
║ [✓] status_safer         ║ Status da operação no sistema Safer      ║ string   ║    Sim      ║    Sim     ║ aprovado               ║
╚══════════════════════════╩══════════════════════════════════════════╩══════════╩═════════════╩════════════╩════════════════════════╝
```

---

## 3. Instruções Campo a Campo

Abaixo, explicamos **cada campo detalhadamente** com regras e exemplos práticos:

---

### 💰 Campo 1: `preco`

| Coluna da Planilha | O que preencher |
|---|---|
| **Nome do Campo** | `preco` |
| **Descrição** | Valor da transação em reais |
| **Tipo** | `number` |
| **Obrigatório** | `Sim` |
| **Rastreável** | `Sim` |
| **Exemplo de Valor** | `100000.75` |

> ⚠️ **Regras:**
> - Use **ponto** (`.`) como separador decimal, nunca vírgula
> - Não inclua símbolo de moeda (`R$`, `$`)
> - Valor deve ser **positivo**
> - Exemplo: `100000.75` ✅ | `100.000,75` ❌

---

### 🪙 Campo 2: `quantidade_de_moedas`

| Coluna da Planilha | O que preencher |
|---|---|
| **Nome do Campo** | `quantidade_de_moedas` |
| **Descrição** | Quantidade de moedas negociadas |
| **Tipo** | `number` |
| **Obrigatório** | `Sim` |
| **Rastreável** | `Sim` |
| **Exemplo de Valor** | `2.5` |

> ⚠️ **Regras:**
> - Use **ponto** (`.`) como separador decimal
> - Pode ter até 8 casas decimais (padrão Bitcoin)
> - Exemplo: `0.00025000` ✅ | `0,00025` ❌

---

### 📅 Campo 3: `criacao_ordem`

| Coluna da Planilha | O que preencher |
|---|---|
| **Nome do Campo** | `criacao_ordem` |
| **Descrição** | Data e hora de criação da ordem |
| **Tipo** | `datetime` |
| **Obrigatório** | `Sim` |
| **Rastreável** | `Sim` |
| **Exemplo de Valor** | `2026-03-23T14:00:00Z` |

> ⚠️ **Regras:**
> - Use o formato **ISO 8601**: `YYYY-MM-DDTHH:MM:SSZ`
> - O `T` separa a data da hora
> - O `Z` indica fuso horário UTC
> - Exemplo: `2026-03-23T14:00:00Z` ✅ | `23/03/2026 14:00` ❌

---

### 📅 Campo 4: `confirmacao_ordem`

| Coluna da Planilha | O que preencher |
|---|---|
| **Nome do Campo** | `confirmacao_ordem` |
| **Descrição** | Data e hora de confirmação da ordem |
| **Tipo** | `datetime` |
| **Obrigatório** | `Sim` |
| **Rastreável** | `Sim` |
| **Exemplo de Valor** | `2026-03-23T14:30:00Z` |

> ⚠️ **Regras:**
> - Mesmo formato que `criacao_ordem`
> - Deve ser **igual ou posterior** à data de criação
> - Exemplo: `2026-03-23T14:30:00Z` ✅

---

### 🔄 Campo 5: `tipo_transacao`

| Coluna da Planilha | O que preencher |
|---|---|
| **Nome do Campo** | `tipo_transacao` |
| **Descrição** | Tipo da transação: débito ou crédito |
| **Tipo** | `string` |
| **Obrigatório** | `Sim` |
| **Rastreável** | `Sim` |
| **Exemplo de Valor** | `debito` |
| **Valores Permitidos** | `debito` ou `credito` |

> ⚠️ **Regras:**
> - Apenas dois valores aceitos: `debito` ou `credito`
> - Tudo em **letras minúsculas**, sem acento
> - Exemplo: `debito` ✅ | `Débito` ❌ | `DEBITO` ❌

---

### 👤 Campo 6: `id_usuario`

| Coluna da Planilha | O que preencher |
|---|---|
| **Nome do Campo** | `id_usuario` |
| **Descrição** | Identificador único do usuário |
| **Tipo** | `string` |
| **Obrigatório** | `Sim` |
| **Rastreável** | `Sim` |
| **Exemplo de Valor** | `user12345` |

> ⚠️ **Regras:**
> - Deve ser **único** para cada usuário
> - Não pode estar vazio
> - Pode conter letras e números
> - Exemplo: `user12345` ✅ | `` (vazio) ❌

---

### 📦 Campo 7: `tipo_ordem`

| Coluna da Planilha | O que preencher |
|---|---|
| **Nome do Campo** | `tipo_ordem` |
| **Descrição** | Tipo de ordem: compra ou venda |
| **Tipo** | `string` |
| **Obrigatório** | `Sim` |
| **Rastreável** | `Sim` |
| **Exemplo de Valor** | `compra` |
| **Valores Permitidos** | `compra` ou `venda` |

> ⚠️ **Regras:**
> - Apenas dois valores aceitos: `compra` ou `venda`
> - Tudo em **letras minúsculas**
> - Exemplo: `compra` ✅ | `Compra` ❌ | `BUY` ❌

---

### 💎 Campo 8: `tipo_ativo`

| Coluna da Planilha | O que preencher |
|---|---|
| **Nome do Campo** | `tipo_ativo` |
| **Descrição** | Tipo da moeda negociada |
| **Tipo** | `string` |
| **Obrigatório** | `Sim` |
| **Rastreável** | `Sim` |
| **Exemplo de Valor** | `BTC` |
| **Valores Permitidos** | `BTC`, `ETH`, `USDT`, etc. |

> ⚠️ **Regras:**
> - Use a sigla oficial da moeda em **letras maiúsculas**
> - Exemplo: `BTC` ✅ | `bitcoin` ❌ | `btc` ❌

---

### 🔑 Campo 9: `id_ordem`

| Coluna da Planilha | O que preencher |
|---|---|
| **Nome do Campo** | `id_ordem` |
| **Descrição** | Identificador único da ordem |
| **Tipo** | `string` |
| **Obrigatório** | `Sim` |
| **Rastreável** | `Sim` |
| **Exemplo de Valor** | `ord56789` |

> ⚠️ **Regras:**
> - Deve ser **único** para cada ordem
> - Pode conter letras e números
> - Não pode estar vazio
> - Exemplo: `ord56789` ✅ | `` (vazio) ❌

---

### 🚦 Campo 10: `status_ordem`

| Coluna da Planilha | O que preencher |
|---|---|
| **Nome do Campo** | `status_ordem` |
| **Descrição** | Status atual da operação |
| **Tipo** | `string` |
| **Obrigatório** | `Sim` |
| **Rastreável** | `Sim` |
| **Exemplo de Valor** | `concluida` |
| **Valores Sugeridos** | `pendente`, `processando`, `concluida`, `cancelada` |

> ⚠️ **Regras:**
> - Letras **minúsculas**, sem acento
> - Exemplo: `concluida` ✅ | `Concluída` ❌

---

### 🛡️ Campo 11: `status_safer`

| Coluna da Planilha | O que preencher |
|---|---|
| **Nome do Campo** | `status_safer` |
| **Descrição** | Status da operação no sistema Safer |
| **Tipo** | `string` |
| **Obrigatório** | `Sim` |
| **Rastreável** | `Sim` |
| **Exemplo de Valor** | `aprovado` |
| **Valores Sugeridos** | `aprovado`, `reprovado`, `em_analise`, `pendente` |

> ⚠️ **Regras:**
> - Letras **minúsculas**, sem acento
> - Exemplo: `aprovado` ✅ | `Aprovado` ❌

---

## 4. Exemplo de JSON Pronto para Copiar

Abaixo está o JSON **completo e preenchido** com valores de exemplo. Você pode usar como referência:

```json
{
  "preco": 100000.75,
  "quantidade_de_moedas": 2.5,
  "criacao_ordem": "2026-03-23T14:00:00Z",
  "confirmacao_ordem": "2026-03-23T14:30:00Z",
  "tipo_transacao": "debito",
  "id_usuario": "user12345",
  "tipo_ordem": "compra",
  "tipo_ativo": "BTC",
  "id_ordem": "ord56789",
  "status_ordem": "concluida",
  "status_safer": "aprovado"
}
```

---

## 5. Modelo de Planilha (CSV) para Copiar e Colar

Copie o conteúdo abaixo e cole diretamente na sua planilha (aba **Schema**):

```csv
Nome do Campo,Descrição,Tipo,Obrigatório,Rastreável,Valores Permitidos,Exemplo de Valor
preco,Valor da transação em reais,number,Sim,Sim,Número positivo com ponto decimal,100000.75
quantidade_de_moedas,Quantidade de moedas negociadas,number,Sim,Sim,Número positivo com ponto decimal,2.5
criacao_ordem,Data e hora de criação da ordem,datetime,Sim,Sim,Formato ISO 8601 (YYYY-MM-DDTHH:MM:SSZ),2026-03-23T14:00:00Z
confirmacao_ordem,Data e hora de confirmação da ordem,datetime,Sim,Sim,Formato ISO 8601 (YYYY-MM-DDTHH:MM:SSZ),2026-03-23T14:30:00Z
tipo_transacao,Tipo da transação: débito ou crédito,string,Sim,Sim,debito | credito,debito
id_usuario,Identificador único do usuário,string,Sim,Sim,Texto alfanumérico único,user12345
tipo_ordem,Tipo de ordem: compra ou venda,string,Sim,Sim,compra | venda,compra
tipo_ativo,Tipo da moeda negociada,string,Sim,Sim,Sigla da moeda em maiúsculas (BTC|ETH|USDT),BTC
id_ordem,Identificador único da ordem,string,Sim,Sim,Texto alfanumérico único,ord56789
status_ordem,Status atual da operação,string,Sim,Sim,pendente | processando | concluida | cancelada,concluida
status_safer,Status da operação no sistema Safer,string,Sim,Sim,aprovado | reprovado | em_analise | pendente,aprovado
```

---

## 6. Passo a Passo: Como Gerar o Schema

Siga estes passos em ordem para gerar o schema corretamente:

### ▶ Passo 1 — Abrir a Planilha
- Abra a planilha e vá até a aba chamada **"Schema"**
- Você verá as colunas: `Nome do Campo`, `Descrição`, `Tipo`, `Obrigatório`, `Rastreável`, `Exemplo de Valor`

### ▶ Passo 2 — Preencher os Campos
- Para cada campo do JSON, preencha **uma linha** na planilha
- Use a tabela da seção **2 (Checklist Visual)** ou o CSV da seção **5** para guiar o preenchimento
- Copie e cole a linha correspondente para cada campo

```
EXEMPLO DE COMO FICA NA PLANILHA:

  Linha 1: [ preco ] [ Valor da transação ] [ number ] [ Sim ] [ Sim ] [ 100000.75 ]
  Linha 2: [ quantidade_de_moedas ] [ Qtd. negociada ] [ number ] [ Sim ] [ Sim ] [ 2.5 ]
  Linha 3: [ criacao_ordem ] [ Data de criação ] [ datetime ] [ Sim ] [ Sim ] [ 2026-03-23T14:00:00Z ]
  ... (continue para todos os 11 campos)
```

### ▶ Passo 3 — Validar o Schema
- Após preencher todos os 11 campos, clique no botão **"Validar Schema"** (ou execute a macro "Validar Schema")
- Se aparecer ✅ verde: está correto, pode prosseguir
- Se aparecer ❌ vermelho: verifique o campo indicado e corrija conforme as regras

### ▶ Passo 4 — Gerar o JSON do Schema
- Clique no botão **"Gerar JSON"** (ou execute a macro "Gerar Json e Checklist")
- O sistema irá gerar automaticamente o arquivo JSON do schema
- Verifique se os 11 campos aparecem no JSON gerado

### ▶ Passo 5 — Importar o Schema
- Com o JSON gerado, clique em **"Importar JSON"** (ou execute a macro "Importar JSON")
- Escolha a opção adequada:
  - **Wrapper** — para schemas que envolvem outros schemas
  - **Fluxo** — para schemas de fluxo de dados
  - **Arquivo** — para importar de um arquivo JSON salvo
- Confirme a importação e aguarde a mensagem de sucesso

### ▶ Passo 6 — Confirmar no Catálogo de Dados
- Após importar, verifique se os campos aparecem no **catálogo de dados**
- Se necessário, passe pelo processo de aprovação para incluir os campos no catálogo

---

## 7. Checklist de Validação Final

Use este checklist para confirmar que tudo está correto antes de finalizar:

```
CHECKLIST — SCHEMA DE TRANSAÇÕES DE CRIPTOMOEDAS
=================================================

PREENCHIMENTO DA PLANILHA:
  [ ] 01. preco             — Tipo: number, Obrigatório: Sim, Rastreável: Sim
  [ ] 02. quantidade_de_moedas — Tipo: number, Obrigatório: Sim, Rastreável: Sim
  [ ] 03. criacao_ordem     — Tipo: datetime, Obrigatório: Sim, Rastreável: Sim
  [ ] 04. confirmacao_ordem — Tipo: datetime, Obrigatório: Sim, Rastreável: Sim
  [ ] 05. tipo_transacao    — Tipo: string, Valores: debito|credito
  [ ] 06. id_usuario        — Tipo: string, Obrigatório: Sim, Rastreável: Sim
  [ ] 07. tipo_ordem        — Tipo: string, Valores: compra|venda
  [ ] 08. tipo_ativo        — Tipo: string, Valores: BTC|ETH|USDT|...
  [ ] 09. id_ordem          — Tipo: string, Obrigatório: Sim, Rastreável: Sim
  [ ] 10. status_ordem      — Tipo: string, Valores: pendente|processando|concluida|cancelada
  [ ] 11. status_safer      — Tipo: string, Valores: aprovado|reprovado|em_analise|pendente

VALIDAÇÕES:
  [ ] Todos os campos usam snake_case (letras minúsculas com underline)
  [ ] Números usam ponto (.) como separador decimal, nunca vírgula
  [ ] Datas estão no formato ISO 8601 (YYYY-MM-DDTHH:MM:SSZ)
  [ ] Nenhum campo obrigatório está vazio
  [ ] A macro "Validar Schema" retornou sem erros (✅)

GERAÇÃO E IMPORTAÇÃO:
  [ ] JSON gerado pela macro "Gerar JSON" contém os 11 campos
  [ ] Schema importado com sucesso via macro "Importar JSON"
  [ ] Campos aparecem corretamente no catálogo de dados
```

---

## 📌 Resumo Rápido — Regras Essenciais

| Regra | ✅ Correto | ❌ Errado |
|---|---|---|
| Nomes de campos | `tipo_transacao` | `tipoTransacao`, `TipoTransacao` |
| Números decimais | `100000.75` | `100.000,75` |
| Datas | `2026-03-23T14:00:00Z` | `23/03/2026 14:00` |
| Texto em listas | `debito` (minúsculo) | `Débito`, `DEBITO` |
| Siglas de moeda | `BTC` (maiúsculo) | `btc`, `Bitcoin` |
| Campos vazios | Sempre preencher | Nunca deixar vazio |

---

> 💡 **Dica:** Se tiver dúvida em algum campo, consulte a seção **3 (Instruções Campo a Campo)** acima.
> Se o sistema mostrar erro na validação, verifique o nome do campo e o tipo de dado primeiro.

---

*Guia criado para o schema de transações de criptomoedas — versão 1.0*
