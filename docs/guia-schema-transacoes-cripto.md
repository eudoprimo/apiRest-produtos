# Guia: Preenchimento do Schema de Transação Cripto no Template Excel

## Visão Geral

Este guia consolida as instruções do template Excel para o preenchimento da aba **Schema** com o schema de transação cripto, respeitando as regras de configuração obrigatórias e sem comprometer as fórmulas/validações existentes.

---

## Regras Gerais (obrigatórias)

1. **Não altere os cabeçalhos** da aba Schema (linha de títulos das colunas).
2. **Ao inserir novas linhas**, copie a linha imediatamente anterior para preservar fórmulas e validações do template — só então sobrescreva os valores das células.
3. **Mantenha sempre** as linhas obrigatórias de configuração listadas abaixo. Elas devem existir no schema, mas **não aparecem no LOG final** gerado pela macro:
   - `bradesco.rastreabilidade`
   - `schema.contabil`
   - `schema.produto`
   - `schema.type`
   - `schema.sistemico`
   - `schema.classificacao`
4. **Não remova** a linha `0 body` (objeto raiz dos dados de negócio).
5. Os campos de negócio do schema de transação cripto entram **como filhos do `body`**, portanto com **level = 1**.

---

## Passo a Passo

### 1. Localize a linha `0 body`

Na aba **Schema**, encontre a linha onde:
- coluna **level** = `0`
- coluna **any.title** = `body`

### 2. Limpe (ou substitua) o conteúdo de exemplo do `body`

Se o template já contiver linhas de exemplo abaixo do `0 body` (ex.: `codigoTransacao`, `mensagens`, `retorno`, `resource`, etc.), **selecione e apague essas linhas de exemplo** antes de inserir os campos do schema cripto.

> **Não apague** a própria linha `0 body` nem as linhas de configuração obrigatórias (`bradesco.rastreabilidade`, `schema.*`).

### 3. Insira as novas linhas abaixo do `0 body`

Para cada campo do schema cripto:

1. Clique na primeira linha vazia **logo abaixo do `0 body`**.
2. **Copie a linha anterior** (Ctrl+C na linha acima) e cole na nova linha (Ctrl+V) para herdar fórmulas e validações.
3. Sobrescreva apenas o conteúdo das células com os valores do campo desejado.

Repita o processo para todos os 11 campos listados na seção **Bloco TSV** abaixo.

### 4. Valide o schema

Clique no botão/macro **Validar Schema**. Se aparecer aviso de "campo não cadastrado no catálogo", utilize o fluxo **Schema_Catálogo → Inserir/Atualizar catálogo** antes de prosseguir.

### 5. Gere o JSON

Clique no botão/macro **Gerar JSON** (ou **Gerar JSON Schema**) para exportar o schema.

### 6. (Opcional) Execute o Checklist

Rode o **Checklist** de validação para garantir conformidade completa.

---

## Linhas Obrigatórias de Configuração

As linhas abaixo **devem estar presentes** no schema, mas **não aparecem no LOG final**:

| level | any.title              | Descrição resumida                                  |
|-------|------------------------|-----------------------------------------------------|
| 0     | bradesco.rastreabilidade | Bloco de rastreabilidade (object)                 |
| 1     | schema.contabil        | Informa se o schema é contábil                      |
| 1     | schema.produto         | Produto ou serviço associado                        |
| 1     | schema.type            | Tipo de log (schema)                                |
| 1     | schema.sistemico       | Sistêmico — imprime relatório                       |
| 1     | schema.classificacao   | Classificação dos logs                              |

> Essas linhas já existem no template; **não as remova e não as duplique**.

---

## Bloco TSV — Copiar e Colar no Excel

Copie o bloco abaixo e cole a partir da **primeira célula da coluna `level`** na linha vazia logo abaixo do `0 body`.

> O formato é separado por TAB (TSV), compatível com colagem direta no Excel.

```tsv
level	any.title	any.description	any.type	Lista	object.required	brad.business.label	any.enum	any.const
1	preco	Valor da transação. Usar ponto como separador decimal (ex.: 100000.75).	number	false	true	preco		
1	quantidade_de_moedas	Quantidade de moedas negociadas na ordem.	number	false	true	quantidadeDeMoedas		
1	criacao_ordem	Data e hora de criação da ordem (formato ISO 8601).	string	false	true	criacaoOrdem		
1	confirmacao_ordem	Data e hora de confirmação da ordem (formato ISO 8601).	string	false	true	confirmacaoOrdem		
1	tipo_transacao	Tipo da transação: debito ou credito.	string	false	true	tipoTransacao	debito|credito	
1	id_usuario	Identificador do usuário (ID interno/externo).	string	false	true	idUsuario		
1	tipo_ordem	Tipo de ordem: compra ou venda.	string	false	true	tipoOrdem	compra|venda	
1	tipo_ativo	Símbolo do ativo negociado (ex.: BTC, ETH).	string	false	true	tipoAtivo		
1	id_ordem	Identificador único da ordem.	string	false	true	idOrdem		
1	status_ordem	Status da operação no sistema.	string	false	true	statusOrdem	criada|confirmada|cancelada|falha	
1	status_safer	Status da operação no Safer.	string	false	true	statusSafer	aprovado|reprovado|pendente	
```

### Ajustes pontuais após colar

| Campo | Situação | Ação |
|---|---|---|
| `tipo_ativo` | Valor **sempre fixo** (ex.: sempre BTC) | Preencha a coluna `any.const` com `BTC` |
| `tipo_ativo` | Valor **variável** (BTC, ETH, USDT…) | Preencha a coluna `any.enum` com o valor `BTC|ETH|USDT` (separado por pipe) e deixe `any.const` vazio |
| `criacao_ordem` / `confirmacao_ordem` | Template suporta `date-time` | Substitua `string` por `date-time` na coluna `any.type` |
| `status_ordem` | Status reais diferem da lista | Atualize a coluna `any.enum` com os valores reais do sistema |

---

## Mapa de Colunas (referência rápida)

| Coluna do template | O que preencher |
|---|---|
| `level` | `1` para todos os campos filhos do `body` |
| `any.title` | Nome do campo no JSON (ex.: `preco`) |
| `any.description` | Descrição legível do campo |
| `any.type` | Tipo do dado: `string`, `number`, `object`, etc. |
| `Lista` | `true` se for array; `false` nos demais casos |
| `object.required` | `true` se o campo for obrigatório |
| `brad.business.label` | Nome amigável (camelCase) para uso de negócio |
| `any.enum` | Valores permitidos separados por pipe (`|`) — ex.: `debito|credito` (quando houver restrição) |
| `any.const` | Valor fixo e único (quando o campo for sempre igual) |

---

## Resumo Visual (antes × depois)

```
... (linhas de configuração obrigatórias: bradesco.rastreabilidade, schema.*)
─────────────────────────────────────────────────────────────
0 | body | Dados de entrada e saída do log | object | false | true
─────────────────────────────────────────────────────────────
  ↓ INSIRA AQUI as 11 linhas do schema cripto (level = 1)
─────────────────────────────────────────────────────────────
1 | preco                | Valor da transação                         | number | false | true | preco
1 | quantidade_de_moedas | Quantidade de moedas negociadas            | number | false | true | quantidadeDeMoedas
1 | criacao_ordem        | Data/hora de criação (ISO 8601)            | string | false | true | criacaoOrdem
1 | confirmacao_ordem    | Data/hora de confirmação (ISO 8601)        | string | false | true | confirmacaoOrdem
1 | tipo_transacao       | Tipo: debito ou credito                    | string | false | true | tipoTransacao  | debito|credito
1 | id_usuario           | Identificador do usuário                   | string | false | true | idUsuario
1 | tipo_ordem           | Tipo: compra ou venda                      | string | false | true | tipoOrdem      | compra|venda
1 | tipo_ativo           | Símbolo do ativo/moeda                     | string | false | true | tipoAtivo
1 | id_ordem             | Identificador único da ordem               | string | false | true | idOrdem
1 | status_ordem         | Status da operação                         | string | false | true | statusOrdem    | criada|confirmada|cancelada|falha
1 | status_safer         | Status no Safer                            | string | false | true | statusSafer    | aprovado|reprovado|pendente
```
