# Guia de Preenchimento do Schema de Transações Cripto

> **Objetivo:** orientar o preenchimento correto da aba **Schema** na planilha de mapeamento, usando as colunas visíveis nas imagens de referência (7–10), e explicar como executar as macros **Validar Schema** e **Gerar JSON**.

---

## 1. Mapa das Colunas da Planilha

| Coluna | Nome interno | O que você preenche |
|--------|-------------|---------------------|
| A | `level` | Nível hierárquico do campo no JSON (`0` = raiz, `1` = filho de `body`, `2` = neto, …) |
| B | `any.title` | **Nome do campo** exatamente como aparece no JSON (ex.: `preco`, `id_ordem`) |
| C | `any.description` | Descrição legível do campo (explique o que ele representa) |
| D | `any.type` | Tipo de dado: `string`, `number`, `integer`, `boolean`, `object`, `array` |
| E | `Lista` | `true` se o campo for um array/lista; `false` na maioria dos campos simples |
| F | `object.required` | `true` se o campo é obrigatório; `false` se é opcional |
| G | `brad.business.label` | Apelido de negócio (camelCase). Ex.: `tipoOrdem`, `idUsuario` |
| H | `any.enum` | Valores permitidos separados por `|` quando o campo só aceita opções fixas (ex.: `compra|venda`) |
| I | `any.const` | Valor fixo quando o campo sempre tem o mesmo valor (ex.: `BTC`). Deixe vazio se variar. |

---

## 2. Regra de Hierarquia (level)

```
level 0 → body  (objeto raiz)
  level 1 → preco
  level 1 → quantidade_de_moedas
  level 1 → criacao_ordem
  level 1 → ...
```

Todos os 11 campos de transação entram como **`level = 1`** porque são filhos diretos do `body`.

---

## 3. Template Pronto para Copiar/Colar (TSV)

> **Como usar:** abra o arquivo `schema-transacoes-template.tsv` (está na mesma pasta deste guia) e cole seu conteúdo diretamente na aba **Schema** da planilha, a partir da coluna **A**, linha logo abaixo dos cabeçalhos.
>
> Alternativamente, copie a tabela abaixo (com Tab entre colunas):

```
level	any.title	any.description	any.type	Lista	object.required	brad.business.label	any.enum	any.const
0	body	Objeto raiz do evento de transação.	object	false	true	body		
1	preco	Valor da transação. Usar ponto como separador decimal (ex.: 100000.75).	number	false	true	preco		
1	quantidade_de_moedas	Quantidade de moedas negociadas na ordem.	number	false	true	quantidadeDeMoedas		
1	criacao_ordem	Data e hora de criação da ordem no formato ISO 8601 (ex.: 2026-03-23T14:00:00Z).	string	false	true	criacaoOrdem		
1	confirmacao_ordem	Data e hora de confirmação da ordem no formato ISO 8601 (ex.: 2026-03-23T14:01:10Z).	string	false	true	confirmacaoOrdem		
1	tipo_transacao	Tipo da transação financeira.	string	false	true	tipoTransacao	debito|credito	
1	id_usuario	Identificador do usuário no sistema (ID interno ou externo).	string	false	true	idUsuario		
1	tipo_ordem	Tipo de ordem enviada ao mercado.	string	false	true	tipoOrdem	compra|venda	
1	tipo_ativo	Símbolo do ativo negociado (ex.: BTC, ETH, USDT).	string	false	true	tipoAtivo		
1	id_ordem	Identificador único da ordem no sistema.	string	false	true	idOrdem		
1	status_ordem	Status atual da ordem no sistema.	string	false	true	statusOrdem	criada|confirmada|cancelada|falha	
1	status_safer	Status da operação no Safer (sistema antifraude).	string	false	true	statusSafer	aprovado|reprovado|pendente	
```

---

## 4. Detalhamento Coluna a Coluna para Cada Campo

> **Nota sobre `|` nas tabelas abaixo:** o símbolo `\|` que aparece nas células das tabelas Markdown é apenas escaping do Markdown e **não deve ser digitado na planilha**. Na planilha (e no arquivo TSV), use o pipe simples `|` para separar os valores do `any.enum`.

### 4.1 `preco`
| Coluna | Valor |
|--------|-------|
| `level` | `1` |
| `any.title` | `preco` |
| `any.description` | Valor da transação. Usar ponto como separador decimal (ex.: 100000.75). |
| `any.type` | `number` |
| `Lista` | `false` |
| `object.required` | `true` |
| `brad.business.label` | `preco` |
| `any.enum` | *(vazio)* |
| `any.const` | *(vazio)* |

### 4.2 `quantidade_de_moedas`
| Coluna | Valor |
|--------|-------|
| `level` | `1` |
| `any.title` | `quantidade_de_moedas` |
| `any.description` | Quantidade de moedas negociadas na ordem. |
| `any.type` | `number` |
| `Lista` | `false` |
| `object.required` | `true` |
| `brad.business.label` | `quantidadeDeMoedas` |
| `any.enum` | *(vazio)* |
| `any.const` | *(vazio)* |

### 4.3 `criacao_ordem`
| Coluna | Valor |
|--------|-------|
| `level` | `1` |
| `any.title` | `criacao_ordem` |
| `any.description` | Data e hora de criação da ordem no formato ISO 8601 (ex.: 2026-03-23T14:00:00Z). |
| `any.type` | `string` |
| `Lista` | `false` |
| `object.required` | `true` |
| `brad.business.label` | `criacaoOrdem` |
| `any.enum` | *(vazio)* |
| `any.const` | *(vazio)* |

### 4.4 `confirmacao_ordem`
| Coluna | Valor |
|--------|-------|
| `level` | `1` |
| `any.title` | `confirmacao_ordem` |
| `any.description` | Data e hora de confirmação da ordem no formato ISO 8601 (ex.: 2026-03-23T14:01:10Z). |
| `any.type` | `string` |
| `Lista` | `false` |
| `object.required` | `true` |
| `brad.business.label` | `confirmacaoOrdem` |
| `any.enum` | *(vazio)* |
| `any.const` | *(vazio)* |

### 4.5 `tipo_transacao`
| Coluna | Valor |
|--------|-------|
| `level` | `1` |
| `any.title` | `tipo_transacao` |
| `any.description` | Tipo da transação financeira. |
| `any.type` | `string` |
| `Lista` | `false` |
| `object.required` | `true` |
| `brad.business.label` | `tipoTransacao` |
| `any.enum` | `debito\|credito` |
| `any.const` | *(vazio)* |

### 4.6 `id_usuario`
| Coluna | Valor |
|--------|-------|
| `level` | `1` |
| `any.title` | `id_usuario` |
| `any.description` | Identificador do usuário no sistema (ID interno ou externo). |
| `any.type` | `string` |
| `Lista` | `false` |
| `object.required` | `true` |
| `brad.business.label` | `idUsuario` |
| `any.enum` | *(vazio)* |
| `any.const` | *(vazio)* |

### 4.7 `tipo_ordem`
| Coluna | Valor |
|--------|-------|
| `level` | `1` |
| `any.title` | `tipo_ordem` |
| `any.description` | Tipo de ordem enviada ao mercado. |
| `any.type` | `string` |
| `Lista` | `false` |
| `object.required` | `true` |
| `brad.business.label` | `tipoOrdem` |
| `any.enum` | `compra\|venda` |
| `any.const` | *(vazio)* |

### 4.8 `tipo_ativo`
| Coluna | Valor |
|--------|-------|
| `level` | `1` |
| `any.title` | `tipo_ativo` |
| `any.description` | Símbolo do ativo negociado (ex.: BTC, ETH, USDT). |
| `any.type` | `string` |
| `Lista` | `false` |
| `object.required` | `true` |
| `brad.business.label` | `tipoAtivo` |
| `any.enum` | `BTC\|ETH\|USDT` *(ajuste conforme ativos suportados)* |
| `any.const` | Se **sempre BTC**, coloque `BTC`; caso contrário, deixe vazio e use `any.enum`. |

### 4.9 `id_ordem`
| Coluna | Valor |
|--------|-------|
| `level` | `1` |
| `any.title` | `id_ordem` |
| `any.description` | Identificador único da ordem no sistema. |
| `any.type` | `string` |
| `Lista` | `false` |
| `object.required` | `true` |
| `brad.business.label` | `idOrdem` |
| `any.enum` | *(vazio)* |
| `any.const` | *(vazio)* |

### 4.10 `status_ordem`
| Coluna | Valor |
|--------|-------|
| `level` | `1` |
| `any.title` | `status_ordem` |
| `any.description` | Status atual da ordem no sistema. |
| `any.type` | `string` |
| `Lista` | `false` |
| `object.required` | `true` |
| `brad.business.label` | `statusOrdem` |
| `any.enum` | `criada\|confirmada\|cancelada\|falha` *(ajuste conforme os status do seu sistema)* |
| `any.const` | *(vazio)* |

### 4.11 `status_safer`
| Coluna | Valor |
|--------|-------|
| `level` | `1` |
| `any.title` | `status_safer` |
| `any.description` | Status da operação no Safer (sistema antifraude). |
| `any.type` | `string` |
| `Lista` | `false` |
| `object.required` | `true` |
| `brad.business.label` | `statusSafer` |
| `any.enum` | `aprovado\|reprovado\|pendente` |
| `any.const` | *(vazio)* |

---

## 5. Regras Rápidas de Preenchimento

| Situação | O que preencher |
|----------|----------------|
| Campo aceita qualquer valor do tipo | Deixe `any.enum` e `any.const` vazios |
| Campo só aceita valores de uma lista | Preencha `any.enum` com os valores separados por `\|` |
| Campo sempre tem o mesmo valor | Preencha `any.const` com esse valor fixo (deixe `any.enum` vazio) |
| Campo é uma lista/array | Coloque `Lista = true` e `any.type = array` |
| Campo é obrigatório | `object.required = true` |
| Campo é opcional | `object.required = false` |

---

## 6. Como Gerar e Validar o Schema (Macros)

### Passo 1 – Preencha a aba Schema
Cole as linhas do template (seção 3) na aba **Schema** da planilha, respeitando as colunas descritas na seção 1.

### Passo 2 – Execute "Validar Schema"
1. Acesse o menu de macros da planilha.
2. Clique em **Validar Schema**.
3. Corrija todos os erros apontados:
   - **Campo não encontrado no catálogo:** acesse a aba **Schema_Catálogo** → use o botão **Inserir/Atualizar no Catálogo** para registrar o campo, depois volte e revalide.
   - **Tipo inválido:** verifique se o valor em `any.type` é um dos tipos aceitos.
   - **Enum inválido:** confirme que os valores em `any.enum` estão separados por `|` (pipe) e sem espaços.

### Passo 3 – Execute "Gerar JSON" / "Gerar JSON Schema"
1. Após a validação sem erros, clique em **Gerar JSON** (ou **Gerar JSON Schema**).
2. O schema será exportado no formato JSON para o campo/aba de saída da planilha.

### Passo 4 (Opcional) – Execute o Checklist
1. Clique em **Checklist** para executar a validação completa de conformidade.
2. Revise e resolva todos os itens marcados como pendentes.

---

## 7. Exemplo de JSON Schema Gerado

Após seguir os passos acima, o schema JSON resultante terá a seguinte estrutura:

```json
{
  "$schema": "http://json-schema.org/draft-07/schema#",
  "type": "object",
  "title": "body",
  "required": [
    "preco", "quantidade_de_moedas", "criacao_ordem", "confirmacao_ordem",
    "tipo_transacao", "id_usuario", "tipo_ordem", "tipo_ativo",
    "id_ordem", "status_ordem", "status_safer"
  ],
  "properties": {
    "preco": {
      "type": "number",
      "description": "Valor da transação. Usar ponto como separador decimal (ex.: 100000.75)."
    },
    "quantidade_de_moedas": {
      "type": "number",
      "description": "Quantidade de moedas negociadas na ordem."
    },
    "criacao_ordem": {
      "type": "string",
      "description": "Data e hora de criação da ordem no formato ISO 8601."
    },
    "confirmacao_ordem": {
      "type": "string",
      "description": "Data e hora de confirmação da ordem no formato ISO 8601."
    },
    "tipo_transacao": {
      "type": "string",
      "description": "Tipo da transação financeira.",
      "enum": ["debito", "credito"]
    },
    "id_usuario": {
      "type": "string",
      "description": "Identificador do usuário no sistema."
    },
    "tipo_ordem": {
      "type": "string",
      "description": "Tipo de ordem enviada ao mercado.",
      "enum": ["compra", "venda"]
    },
    "tipo_ativo": {
      "type": "string",
      "description": "Símbolo do ativo negociado (ex.: BTC, ETH, USDT)."
    },
    "id_ordem": {
      "type": "string",
      "description": "Identificador único da ordem no sistema."
    },
    "status_ordem": {
      "type": "string",
      "description": "Status atual da ordem no sistema.",
      "enum": ["criada", "confirmada", "cancelada", "falha"]
    },
    "status_safer": {
      "type": "string",
      "description": "Status da operação no Safer.",
      "enum": ["aprovado", "reprovado", "pendente"]
    }
  }
}
```

---

## 8. Checklist de Conferência Antes de Gerar

- [ ] Todos os 11 campos estão com `level = 1`
- [ ] Nenhum campo tem `any.title` com espaços (usar underscore: `tipo_ordem`)
- [ ] Campos numéricos (`preco`, `quantidade_de_moedas`) estão com `any.type = number`
- [ ] Campos de data/hora estão com `any.type = string` e `any.description` menciona ISO 8601
- [ ] Todos os campos estão com `object.required = true`
- [ ] Campos com lista de valores têm `any.enum` preenchido com `|` separando os valores
- [ ] `any.const` só está preenchido nos campos que **sempre** têm o mesmo valor
- [ ] `brad.business.label` está em camelCase para todos os campos
- [ ] O campo `body` (level 0) está presente com `any.type = object`
- [ ] A macro **Validar Schema** foi executada sem erros
