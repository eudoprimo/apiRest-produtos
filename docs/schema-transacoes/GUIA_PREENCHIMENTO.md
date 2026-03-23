# Guia de Preenchimento — Aba Schema (Transações Cripto)

Este guia mostra **exatamente onde e como preencher** cada campo do JSON de transação na aba **Schema** da planilha.

---

## 1. Estrutura da Aba Schema

A aba **Schema** possui **6 colunas** (cabeçalho na linha 1):

| Coluna | Nome da Coluna     | O que preencher                                                          |
|--------|--------------------|--------------------------------------------------------------------------|
| A      | **campo**          | Nome do campo exatamente como aparece no JSON (snake_case, minúsculas)   |
| B      | **descricao**      | Descrição curta e clara do que representa o campo                        |
| C      | **tipo**           | Tipo de dado: `number`, `string` ou `date`                               |
| D      | **obrigatorio**    | `Sim` se o campo é obrigatório; `Não` caso contrário                     |
| E      | **rastreavel**     | `Sim` se o campo é usado para auditoria/consulta futura                  |
| F      | **exemplo**        | Um valor de exemplo real para facilitar validação                        |

---

## 2. Mapa Visual — Linha por Linha

Copie a tabela abaixo para a aba **Schema** a partir da **linha 2** (linha 1 = cabeçalho):

```
     A                        B                                              C        D    E             F
┌────────────────────────┬──────────────────────────────────────────────┬────────┬─────┬─────┬────────────────────────┐
│ campo                  │ descricao                                    │ tipo   │ obr │ ras │ exemplo                │
├────────────────────────┼──────────────────────────────────────────────┼────────┼─────┼─────┼────────────────────────┤
│ preco                  │ Valor unitário do ativo na transação         │ number │ Sim │ Sim │ 100000.75              │
│ quantidade_de_moedas   │ Quantidade de moedas negociadas              │ number │ Sim │ Sim │ 2.5                    │
│ criacao_ordem          │ Data e hora de criação da ordem (ISO 8601)   │ date   │ Sim │ Sim │ 2026-03-23T14:00:00Z   │
│ confirmacao_ordem      │ Data e hora de confirmação da ordem          │ date   │ Sim │ Sim │ 2026-03-23T14:30:00Z   │
│ tipo_transacao         │ Tipo de movimentação financeira              │ string │ Sim │ Sim │ debito                 │
│ id_usuario             │ Identificador único do usuário               │ string │ Sim │ Sim │ user12345              │
│ tipo_ordem             │ Tipo de ordem executada (compra ou venda)    │ string │ Sim │ Sim │ compra                 │
│ tipo_ativo             │ Símbolo do ativo/criptomoeda negociada       │ string │ Sim │ Sim │ BTC                    │
│ id_ordem               │ Identificador único da ordem                 │ string │ Sim │ Sim │ ord56789               │
│ status_ordem           │ Status atual da ordem                        │ string │ Sim │ Sim │ concluida              │
│ status_safer           │ Status da verificação no sistema Safer       │ string │ Sim │ Sim │ aprovado               │
└────────────────────────┴──────────────────────────────────────────────┴────────┴─────┴─────┴────────────────────────┘
```

---

## 3. Tabela Pronta para Copiar e Colar

| campo                  | descricao                                          | tipo   | obrigatorio | rastreavel | exemplo               |
|------------------------|----------------------------------------------------|--------|-------------|------------|-----------------------|
| preco                  | Valor unitário do ativo na transação               | number | Sim         | Sim        | 100000.75             |
| quantidade_de_moedas   | Quantidade de moedas negociadas                    | number | Sim         | Sim        | 2.5                   |
| criacao_ordem          | Data e hora de criação da ordem (ISO 8601)         | date   | Sim         | Sim        | 2026-03-23T14:00:00Z  |
| confirmacao_ordem      | Data e hora de confirmação da ordem (ISO 8601)     | date   | Sim         | Sim        | 2026-03-23T14:30:00Z  |
| tipo_transacao         | Tipo de movimentação financeira (debito/credito)   | string | Sim         | Sim        | debito                |
| id_usuario             | Identificador único do usuário                     | string | Sim         | Sim        | user12345             |
| tipo_ordem             | Tipo de ordem executada (compra ou venda)          | string | Sim         | Sim        | compra                |
| tipo_ativo             | Símbolo do ativo/criptomoeda negociada             | string | Sim         | Sim        | BTC                   |
| id_ordem               | Identificador único da ordem                       | string | Sim         | Sim        | ord56789              |
| status_ordem           | Status atual da ordem (ex: concluida)              | string | Sim         | Sim        | concluida             |
| status_safer           | Status da verificação no sistema Safer (ex: aprovado) | string | Sim      | Sim        | aprovado              |

---

## 4. Regras de Preenchimento (baseadas nas instruções)

### ✅ Coluna A — `campo`
- Use **snake_case** (palavras separadas por `_`)
- Tudo em **minúsculas**
- Sem espaços, sem acentos, sem caracteres especiais
- Exemplos corretos: `preco`, `id_usuario`, `status_safer`

### ✅ Coluna B — `descricao`
- Frase curta e objetiva
- Pode conter acentos e letras maiúsculas normalmente
- Descreva **o que representa** o campo, não como ele é usado

### ✅ Coluna C — `tipo`
| Valor    | Quando usar                                        |
|----------|----------------------------------------------------|
| `number` | Valores numéricos (inteiros ou decimais): preço, quantidade |
| `string` | Textos, códigos, identificadores, status           |
| `date`   | Datas e horários (formato ISO 8601: `YYYY-MM-DDTHH:MM:SSZ`) |

### ✅ Coluna D — `obrigatorio`
- `Sim` — campo obrigatório (não pode ser nulo/vazio)
- `Não` — campo opcional

### ✅ Coluna E — `rastreavel`
- `Sim` — campo utilizado para auditoria, rastreamento ou consulta futura
- `Não` — campo não precisa de rastreamento

### ✅ Coluna F — `exemplo`
- Coloque um valor real e representativo
- Para `date`: use formato ISO 8601 (`2026-03-23T14:00:00Z`)
- Para `number`: use ponto como separador decimal (`100000.75`)
- Para `string`: use um valor típico do domínio (`debito`, `BTC`, `concluida`)

---

## 5. Como Gerar o Schema Após Preencher

1. **Preencha** todas as linhas da aba **Schema** conforme a tabela acima (a partir da linha 2).
2. **Salve** o arquivo.
3. Na plataforma, localize o botão **"Validar Schema"** — clique para verificar se há erros.
4. Se a validação passar, clique em **"Gerar JSON Schema"** (ou "Exportar Schema").
5. O JSON Schema gerado estará pronto para importar no sistema ou usar como contrato de API.

---

## 6. Checklist Antes de Gerar

- [ ] Coluna `campo`: todos os nomes em snake_case e minúsculas
- [ ] Coluna `tipo`: somente valores `number`, `string` ou `date`
- [ ] Coluna `obrigatorio`: preenchida com `Sim` ou `Não` (sem variações)
- [ ] Coluna `rastreavel`: preenchida com `Sim` ou `Não`
- [ ] Coluna `exemplo`: exemplos coerentes com o tipo do campo
- [ ] Nenhuma linha em branco entre os campos
- [ ] Cabeçalho na linha 1, dados a partir da linha 2

---

## 7. Arquivo CSV Pronto para Importar

O arquivo [`schema_transacoes.csv`](./schema_transacoes.csv) nesta pasta já contém todos os campos preenchidos corretamente. Você pode:

- **Abrir diretamente** no Excel/Google Sheets para ver a tabela formatada
- **Importar** na plataforma se ela aceitar CSV como entrada de schema
- **Copiar** o conteúdo para a aba Schema da sua planilha existente

---

*Gerado com base no JSON de transações e nas instruções fornecidas.*
