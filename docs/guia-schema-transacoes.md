# Guia Visual: Preenchimento do Schema de Transações de Criptomoedas

> **Versão**: 1.0 | **Idioma**: Português | **Arquivo de schema**: `src/main/resources/schemas/transacao-schema.json`

---

## 📋 Tabela de Campos — O Que Preencher

| # | Nome do Campo | Descrição | Tipo | Obrigatório | Rastreável | Valores Aceitos | Exemplo |
|---|---------------|-----------|------|:-----------:|:----------:|-----------------|---------|
| 1 | `preco` | Valor unitário da criptomoeda na transação (BRL ou USD) | `number` | ✅ Sim | ✅ Sim | Número positivo com até 8 decimais | `100000.75` |
| 2 | `quantidade_de_moedas` | Quantidade de criptomoedas negociadas | `number` | ✅ Sim | ✅ Sim | Número positivo com até 8 decimais | `2.5` |
| 3 | `criacao_ordem` | Data e hora de criação da ordem | `date-time` | ✅ Sim | ✅ Sim | Formato ISO 8601 UTC: `YYYY-MM-DDTHH:MM:SSZ` | `"2026-03-23T14:00:00Z"` |
| 4 | `confirmacao_ordem` | Data e hora de confirmação/liquidação da ordem | `date-time` | ✅ Sim | ✅ Sim | Formato ISO 8601 UTC: `YYYY-MM-DDTHH:MM:SSZ` | `"2026-03-23T14:30:00Z"` |
| 5 | `tipo_transacao` | Tipo financeiro: saída ou entrada de valor | `string` | ✅ Sim | ✅ Sim | `"debito"` ou `"credito"` | `"debito"` |
| 6 | `id_usuario` | Identificador único do usuário | `string` | ✅ Sim | ✅ Sim | Texto não vazio | `"user12345"` |
| 7 | `tipo_ordem` | Tipo da operação executada | `string` | ✅ Sim | ✅ Sim | `"compra"` ou `"venda"` | `"compra"` |
| 8 | `tipo_ativo` | Símbolo/ticker da criptomoeda | `string` | ✅ Sim | ✅ Sim | 2 a 10 caracteres: `BTC`, `ETH`, `USDT`... | `"BTC"` |
| 9 | `id_ordem` | Identificador único da ordem no sistema | `string` | ✅ Sim | ✅ Sim | Texto não vazio (único por ordem) | `"ord56789"` |
| 10 | `status_ordem` | Status de execução da ordem na exchange | `string` | ✅ Sim | ✅ Sim | Ver tabela de valores abaixo | `"concluida"` |
| 11 | `status_safer` | Status de avaliação de risco/compliance (Safer) | `string` | ✅ Sim | ✅ Sim | Ver tabela de valores abaixo | `"aprovado"` |

---

## 🔢 Detalhamento dos Campos Numéricos

### Campo `preco`
```
┌─────────────────────────────────────────────────┐
│  CAMPO: preco                                   │
│  Tipo:  number (decimal)                        │
│  Min:   > 0 (maior que zero)                    │
│  Casas decimais: até 8                          │
│                                                 │
│  ✅ Correto:   100000.75                        │
│  ✅ Correto:   0.00001234  (moedas baratas)     │
│  ❌ Errado:    -100.00     (negativo)           │
│  ❌ Errado:    0           (zero)               │
└─────────────────────────────────────────────────┘
```

### Campo `quantidade_de_moedas`
```
┌─────────────────────────────────────────────────┐
│  CAMPO: quantidade_de_moedas                    │
│  Tipo:  number (decimal)                        │
│  Min:   > 0 (maior que zero)                    │
│  Casas decimais: até 8                          │
│                                                 │
│  ✅ Correto:   2.5                              │
│  ✅ Correto:   0.00100000  (satoshis/fração)    │
│  ❌ Errado:    -1.0        (negativo)           │
│  ❌ Errado:    0           (zero)               │
└─────────────────────────────────────────────────┘
```

---

## 📅 Detalhamento dos Campos de Data

### Formato ISO 8601 (UTC)

```
 2026-03-23 T 14:30:00 Z
 ────────── ─ ──────── ─
     │      │    │     └── 'Z' = UTC (fuso horário zero)
     │      │    └──────── HH:MM:SS (hora, minuto, segundo)
     │      └───────────── Separador data/hora
     └──────────────────── YYYY-MM-DD (ano-mês-dia)
```

**Exemplos válidos:**
- `"2026-03-23T14:00:00Z"` — criação da ordem
- `"2026-03-23T14:30:00Z"` — confirmação (deve ser >= criação)
- `"2026-01-01T00:00:00Z"` — início do dia

> ⚠️ **Atenção**: `confirmacao_ordem` deve sempre ser **posterior ou igual** a `criacao_ordem`.

---

## 🔤 Valores Aceitos — Campos Enumerados

### `tipo_transacao`
| Valor | Descrição | Quando Usar |
|-------|-----------|-------------|
| `"debito"` | Saída de valor da conta | Usuário **compra** criptomoeda (pagou BRL) |
| `"credito"` | Entrada de valor na conta | Usuário **vende** criptomoeda (recebeu BRL) |

### `tipo_ordem`
| Valor | Descrição |
|-------|-----------|
| `"compra"` | Ordem de compra de criptomoeda |
| `"venda"` | Ordem de venda de criptomoeda |

### `status_ordem`
| Valor | Descrição |
|-------|-----------|
| `"pendente"` | Ordem criada, aguardando processamento |
| `"em_processamento"` | Ordem sendo processada na exchange |
| `"concluida"` | Ordem executada com sucesso |
| `"cancelada"` | Ordem cancelada pelo usuário ou sistema |
| `"falha"` | Ordem falhou por erro técnico ou saldo insuficiente |

### `status_safer`
| Valor | Descrição |
|-------|-----------|
| `"pendente"` | Aguardando análise de risco |
| `"aprovado"` | Aprovada pelo sistema Safer |
| `"reprovado"` | Reprovada — transação bloqueada |
| `"em_analise"` | Em análise manual pelo time de compliance |

---

## 📝 Exemplo Completo de JSON Pronto para Uso

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

> 💡 **Dica**: Este exemplo está salvo também em `src/main/resources/schemas/transacao-exemplo.json` para uso direto.

---

## 📊 Exemplo na Planilha — Como Preencher Cada Linha

| Nome do Campo | Tipo | Obrigatório | Rastreável | Valor Exemplo | Observação |
|---|---|:---:|:---:|---|---|
| `preco` | number | Sim | Sim | `100000.75` | Positivo, até 8 decimais |
| `quantidade_de_moedas` | number | Sim | Sim | `2.5` | Positivo, até 8 decimais |
| `criacao_ordem` | date-time | Sim | Sim | `2026-03-23T14:00:00Z` | ISO 8601 UTC |
| `confirmacao_ordem` | date-time | Sim | Sim | `2026-03-23T14:30:00Z` | ISO 8601 UTC, >= criacao_ordem |
| `tipo_transacao` | string (enum) | Sim | Sim | `debito` | debito \| credito |
| `id_usuario` | string | Sim | Sim | `user12345` | ID único do usuário |
| `tipo_ordem` | string (enum) | Sim | Sim | `compra` | compra \| venda |
| `tipo_ativo` | string | Sim | Sim | `BTC` | Ticker: BTC, ETH, USDT... |
| `id_ordem` | string | Sim | Sim | `ord56789` | ID único da ordem |
| `status_ordem` | string (enum) | Sim | Sim | `concluida` | Ver tabela de valores |
| `status_safer` | string (enum) | Sim | Sim | `aprovado` | Ver tabela de valores |

---

## ✅ Checklist Final — Antes de Gerar o Schema

Antes de clicar em **"Gerar Schema"** ou **"Exportar JSON"**, confira:

### Campos Obrigatórios
- [ ] `preco` está preenchido com número **positivo**
- [ ] `quantidade_de_moedas` está preenchido com número **positivo**
- [ ] `criacao_ordem` está no formato `YYYY-MM-DDTHH:MM:SSZ`
- [ ] `confirmacao_ordem` está no formato `YYYY-MM-DDTHH:MM:SSZ`
- [ ] `confirmacao_ordem` é **posterior ou igual** a `criacao_ordem`
- [ ] `tipo_transacao` é `"debito"` ou `"credito"` (minúsculo, sem acento)
- [ ] `id_usuario` não está vazio
- [ ] `tipo_ordem` é `"compra"` ou `"venda"` (minúsculo)
- [ ] `tipo_ativo` tem entre 2 e 10 caracteres (ex: `BTC`, `ETH`)
- [ ] `id_ordem` não está vazio e é **único**
- [ ] `status_ordem` é um dos 5 valores permitidos
- [ ] `status_safer` é um dos 4 valores permitidos

### Validação Geral
- [ ] Todos os 11 campos estão presentes no JSON
- [ ] Nenhum valor está `null` ou vazio
- [ ] Strings enum usam letras **minúsculas** e **sem acentos**
- [ ] Datas usam o sufixo `Z` (UTC)
- [ ] Nenhum campo extra foi adicionado (schema não permite `additionalProperties`)

### Verificação de Negócio
- [ ] Se `tipo_ordem = "compra"` → `tipo_transacao` deve ser `"debito"`
- [ ] Se `tipo_ordem = "venda"` → `tipo_transacao` deve ser `"credito"`
- [ ] `tipo_ativo` corresponde à criptomoeda negociada
- [ ] `id_ordem` é único para esta transação
- [ ] `status_safer` reflete o resultado real da análise de risco

---

## 🗂️ Arquivos de Referência

| Arquivo | Descrição |
|---------|-----------|
| `src/main/resources/schemas/transacao-schema.json` | JSON Schema completo com validações |
| `src/main/resources/schemas/transacao-exemplo.json` | Exemplo de payload pronto para uso |
| `src/main/java/com/produtos/apirest/models/Transacao.java` | Entidade Java do modelo de transação |
| `docs/guia-schema-transacoes.md` | Este guia visual (você está aqui) |

---

## ❓ Dúvidas Frequentes

**P: Posso usar vírgula como separador decimal?**
R: Não. Use **ponto** como separador: `100000.75`, não `100000,75`.

**P: O campo `tipo_ativo` aceita letras minúsculas?**
R: Sim, mas por convenção use letras **maiúsculas** (ex: `BTC`, não `btc`).

**P: O que acontece se `status_safer = "reprovado"`?**
R: A transação é bloqueada pelo sistema de compliance. Neste caso, `status_ordem` geralmente fica como `"cancelada"`.

**P: Posso omitir algum campo?**
R: Não. Todos os 11 campos são **obrigatórios**. O schema retornará erro de validação se algum estiver ausente.

**P: Como valido o JSON antes de enviar?**
R: Use o arquivo `transacao-schema.json` com uma ferramenta como [jsonschemavalidator.net](https://www.jsonschemavalidator.net/) ou a macro de validação da planilha.
