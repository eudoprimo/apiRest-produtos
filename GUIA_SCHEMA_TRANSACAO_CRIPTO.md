# Guia Prático: Preenchimento do Schema de Transação Cripto

> **Idioma:** Português  
> **Contexto:** Preenchimento do template Excel (Schema) para transação de criptoativos, respeitando as regras e macros do template Bradesco.

---

## 1. Checklist Resumido (do início ao fim)

- [ ] **Passo 1 – Preparar o arquivo**
  - Duplique a aba (ou faça "Salvar como") do template para um arquivo/aba exclusivo do schema de transação cripto.
  - Nunca altere o cabeçalho da planilha.
- [ ] **Passo 2 – Manter linhas obrigatórias de identificação/configuração**
  - Certifique-se de que as seguintes linhas estejam presentes e preenchidas corretamente (são linhas de configuração, **não aparecem no LOG/payload**):
    - `bradesco.rastreabilidade`
    - `schema.contabil`
    - `schema.produto`
    - `schema.type`
    - `schema.sistemico`
    - `schema.classificacao`
- [ ] **Passo 3 – Executar a macro "Configurar Schema"**
  - Use para inicializar/validar as configurações do arquivo antes de preencher os campos do negócio.
- [ ] **Passo 4 – Inserir linhas para os campos do JSON**
  - Localize a linha `0 body` (coluna `any.title` = `body`).
  - Insira novas linhas **logo abaixo** dela (ver [Seção 2](#2-onde-inserir-campos-do-json-de-transação-cripto)).
  - **Ao inserir, sempre copie a linha anterior** para preservar fórmulas e validações do template.
- [ ] **Passo 5 – Preencher "Nome Negócio"**
  - Para campos analíticos: preencha a coluna `brad.business.label` com o label desejado (ver [Seção 5](#5-como-preencher-nome-negócio-para-analítico)).
- [ ] **Passo 6 – Executar a macro "Gerar Pré Schema"**
  - Gera a estrutura preliminar do schema para revisão antes da validação final.
- [ ] **Passo 7 – Executar a macro "Validar Schema"**
  - Se houver alerta de "campo não cadastrado no catálogo", siga os passos da [Seção 6](#6-o-que-fazer-quando-validar-schema-acusar-falta-no-catálogo).
- [ ] **Passo 8 – Executar a macro "Gerar JSON"**
  - Gera o JSON Schema final a partir das linhas preenchidas na planilha.

---

## 2. Onde Inserir Campos do JSON de Transação Cripto

### Estrutura recomendada

Os campos da transação cripto devem ser inseridos como um **sub-objeto dentro de `body`**, por exemplo `body.transacao`. Isso evita misturar campos de negócio (transação) com campos já existentes de log (como `codigoTransacao`, `mensagens`, `retorno`).

**Estrutura esperada no JSON final:**

```json
{
  "body": {
    "transacao": {
      "preco": 0,
      "quantidade_de_moedas": 0,
      "criacao_ordem": "2024-01-01T00:00:00Z",
      "confirmacao_ordem": "2024-01-01T00:00:01Z",
      "tipo_transacao": "debito",
      "id_usuario": "12345",
      "tipo_ordem": "compra",
      "tipo_ativo": "BTC",
      "id_ordem": "ORD-001",
      "status_ordem": "confirmada",
      "status_safer": "aprovado"
    },
    "codigoTransacao": "BCPF_SRV_STATUS_RFQ_CL",
    "mensagens": { "codigo": "", "texto": "" },
    "retorno": "SUCESSO"
  }
}
```

### Como posicionar na planilha

1. Encontre a linha com `level = 0` e `any.title = body`.
2. **Logo abaixo** dela, insira uma linha com `level = 1`, `any.title = transacao`, `any.type = object` (esse é o sub-objeto).
3. Abaixo do sub-objeto `transacao`, insira os campos com `level = 2`.

> ⚠️ **Importante:** Não use o caractere `.` (ponto) em nomes de campos dentro do `body`. O ponto é reservado para campos de configuração (ex.: `schema.produto`, `bradesco.auth.username`). Para campos de negócio dentro do body, use `_` (underline) ou camelCase.

---

## 3. Bloco TSV Pronto para Colar

Cole este bloco **logo abaixo da linha `0 body`** no Excel (clique na célula da coluna `level` na linha imediatamente abaixo do `0 body` e pressione Ctrl+V):

> As colunas do template são: `level | any.title | any.description | any.type | Lista | object.required | brad.business.label | any.enum | any.const`

```tsv
level	any.title	any.description	any.type	Lista	object.required	brad.business.label	any.enum	any.const
1	transacao	Dados da transação cripto	object	false	true	transacao		
2	preco	Valor da transação.	number	false	true	preco		
2	quantidade_de_moedas	Quantidade de moedas negociadas.	number	false	true	quantidadeDeMoedas		
2	criacao_ordem	Data/hora de criação da ordem (ISO 8601).	string	false	true	criacaoOrdem		
2	confirmacao_ordem	Data/hora de confirmação da ordem (ISO 8601).	string	false	true	confirmacaoOrdem		
2	tipo_transacao	Tipo da transação: debito ou credito.	string	false	true	tipoTransacao	debito|credito	
2	id_usuario	Identificador do usuário.	string	false	true	idUsuario		
2	tipo_ordem	Tipo de ordem: compra ou venda.	string	false	true	tipoOrdem	compra|venda	
2	tipo_ativo	Símbolo do ativo/moeda (ex.: BTC).	string	false	true	tipoAtivo		
2	id_ordem	Identificador único da ordem.	string	false	true	idOrdem		
2	status_ordem	Status da operação.	string	false	true	statusOrdem	criada|confirmada|cancelada|falha	
2	status_safer	Status da operação no Safer.	string	false	true	statusSafer	aprovado|reprovado|pendente	
```

### Ajustes opcionais

> 📝 **Nota sobre separadores na planilha vs. nesta documentação:** Na planilha Excel, use `|` (pipe simples) para separar valores do enum, exatamente como aparece no bloco TSV acima (ex.: `debito|credito`). Nas tabelas abaixo, o caractere aparece como `\|` apenas por exigência da formatação Markdown — na planilha, use sempre `|` sem barra invertida.

| Campo | Coluna `any.const` | Coluna `any.enum` (na planilha) | Quando usar |
|---|---|---|---|
| `tipo_ativo` | `BTC` | (vazio) | Se o ativo for **sempre** BTC |
| `tipo_ativo` | (vazio) | `BTC\|ETH\|USDT` | Se o ativo **variar** entre valores fixos |
| `tipo_ativo` | (vazio) | (vazio) | Se o ativo for **livre** (qualquer string) |

---

## 4. Como Lidar com Dois Schemas no Mesmo Arquivo

Se você precisar manter dois schemas distintos (ex.: schema de log existente + schema de transação cripto), há duas abordagens:

### Opção A – Separar por aba (recomendada)

1. Duplique a aba do template e renomeie (ex.: `Schema_TransacaoCripto`).
2. Em cada aba, mantenha as linhas obrigatórias de configuração (`bradesco.rastreabilidade`, `schema.*`).
3. Preencha os campos de negócio específicos de cada schema na aba correspondente.
4. Execute as macros **separadamente** em cada aba.

> ✅ **Vantagem:** schemas independentes, sem risco de conflito entre contratos diferentes.

### Opção B – Separar por arquivo

1. Faça "Salvar como" do template Excel para um novo arquivo dedicado ao schema de transação cripto.
2. Preencha as linhas de configuração e os campos de negócio normalmente.
3. Mantenha o arquivo original intacto para o schema de log.

> ✅ **Vantagem:** total isolamento entre schemas.

### ⚠️ O que NÃO fazer

- Não misture os campos de dois schemas diferentes dentro das mesmas linhas do `body`.
- Não apague as linhas padrão (`bradesco.*`, `schema.*`) de nenhum dos schemas.
- Não altere o cabeçalho da planilha em nenhuma das abas/arquivos.

---

## 5. Como Preencher Nome Negócio para Analítico

A coluna **`brad.business.label`** (Nome Negócio) define o label analítico do campo, ou seja, como ele aparecerá nos dashboards/relatórios.

### Regras práticas

| Situação | O que preencher em `brad.business.label` | Exemplo |
|---|---|---|
| Campo deve aparecer em análise/dashboard | Use o label em **camelCase** | `tipoTransacao` |
| Campo é apenas técnico (não analítico) | Deixe **vazio** | (em branco) |
| Campo de enum já padronizado | Use o mesmo label do enum | `statusOrdem` |
| Campos do tipo objeto (agrupador) | Pode usar o nome do objeto | `transacao` |

### Quando usar "Sim"

Alguns templates possuem uma coluna separada para indicar se o campo é analítico (`Sim` ou `Não`). Se o seu template tiver essa coluna:

- **"Sim"**: o campo deve aparecer nos relatórios analíticos → preencha também o `brad.business.label`.
- **"Não"** ou vazio: campo apenas técnico → deixe `brad.business.label` vazio.

> 💡 **Dica:** campos como `preco`, `quantidade_de_moedas`, `tipo_ativo`, `status_ordem`, `tipo_ordem` geralmente são analíticos. Campos de controle interno como `id_ordem` podem ser técnicos.

---

## 6. O que Fazer Quando "Validar Schema" Acusar Falta no Catálogo

Quando a macro **Validar Schema** exibir uma mensagem do tipo `"campo não cadastrado no catálogo"`, siga este fluxo:

1. **Identifique** quais campos estão faltando no catálogo (a macro geralmente lista os nomes).
2. **Acesse a aba/planilha do Catálogo** (geralmente chamada de `Catálogo` ou `Catalog` dentro do mesmo arquivo).
3. **Inclua os campos faltantes** no catálogo:
   - Use a opção/macro **"Incluir campo no catálogo"** (se disponível no template), ou
   - Insira manualmente uma nova linha no catálogo com: nome do campo, tipo, descrição e demais colunas obrigatórias.
   - **Ao inserir linhas no catálogo, copie a linha anterior** para manter fórmulas/validações.
4. **Repita** a execução de **Validar Schema** até não haver mais erros.
5. Somente após validação sem erros, execute **Gerar JSON**.

> ⚠️ Não pule a validação. Campos não cadastrados no catálogo podem causar falha na geração do JSON Schema ou inconsistências nos logs.

---

## 7. Guia Rápido das Macros

| Macro | Quando usar | O que faz |
|---|---|---|
| **Configurar Schema** | Antes de qualquer preenchimento | Inicializa e valida as configurações do arquivo do schema |
| **Gerar Pré Schema** | Após preencher os campos, antes de validar | Gera uma prévia da estrutura do schema para revisão |
| **Validar Schema** | Após preencher todos os campos | Valida se todos os campos estão no catálogo e se as regras estão corretas |
| **Incluir campo no catálogo** | Quando Validar Schema acusar campo faltante | Abre o fluxo para cadastrar novos campos no catálogo |
| **Gerar JSON** | Após validação sem erros | Gera o JSON Schema final a partir da planilha preenchida |

---

## 8. Regras Essenciais (Resumo)

1. **Nunca altere o cabeçalho** da planilha.
2. **Ao inserir linhas**, sempre copie a linha anterior para manter fórmulas e validações.
3. **Mantenha as linhas obrigatórias** de configuração:
   - `bradesco.rastreabilidade`, `schema.contabil`, `schema.produto`, `schema.type`, `schema.sistemico`, `schema.classificacao`
   - Essas linhas são **só configuração** e **não aparecem no LOG (payload)**.
4. **Não use `.` (ponto) em nomes de campos** dentro do `body` — use `_` ou camelCase.
5. Para campos de transação cripto, **crie um sub-objeto** dentro do `body` (ex.: `body.transacao`) em vez de inserir diretamente no nível 1 do `body`, para não misturar com campos existentes de log.
6. Campos com `.` no nome (ex.: `service.name`, `bradesco.auth.username`) são reservados para campos de rastreabilidade/configuração fora do `body`.

---

## 9. Referência Rápida das Colunas do Template

| Coluna | Descrição | Exemplo |
|---|---|---|
| `level` | Nível de hierarquia do campo (0 = raiz, 1 = filho de raiz, 2 = neto...) | `2` |
| `any.title` | Nome técnico do campo (sem `.` para campos de negócio) | `tipo_transacao` |
| `any.description` | Descrição do campo | `Tipo da transação: debito ou credito` |
| `any.type` | Tipo do dado | `string`, `number`, `object`, `boolean` |
| `Lista` | Se o campo é um array/lista | `true` ou `false` |
| `object.required` | Se o campo é obrigatório | `true` ou `false` |
| `brad.business.label` | Label analítico (Nome Negócio) | `tipoTransacao` |
| `any.enum` | Valores permitidos separados por `|` (pipe) na planilha | `debito\|credito` |
| `any.const` | Valor fixo/constante | `BTC` |
