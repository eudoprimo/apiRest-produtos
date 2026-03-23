# Instruções: Adicionar campos de transação cripto ao template de schema

## 1. Recomendação objetiva

| Regra | O que fazer |
|---|---|
| **Não substituir cabeçalhos** | Nunca altere a linha de cabeçalho da aba Schema. |
| **Manter linhas obrigatórias** | Mantenha todas as linhas `bradesco.*` e `schema.*` exatamente como estão. |
| **Não misturar schemas diferentes na mesma aba** | O template já possui um schema de log/retorno preenchido. Não coloque campos de transação cripto misturados com esses campos. Veja seção 4. |
| **Inserir um subobjeto `transacao` dentro do `0 body`** | Em vez de colocar os 11 campos diretamente como filhos do `body`, crie primeiro um objeto `transacao` (level 1) e coloque os campos dentro dele (level 2). Isso evita conflito com os campos existentes e organiza o payload. |

---

## 2. Bloco TSV completo para colar no Excel

Copie o bloco abaixo e cole na aba **Schema** do seu template Excel (Ctrl+V).  
**Cole a partir da coluna `Nivel`** na linha imediatamente abaixo de `0 body` e acima de `1 codigoTransacao`.

> O formato está separado por TAB. Colunas não utilizadas ficam em branco — não apague as células, apenas deixe-as vazias.

```
Nivel	Nome	Descricao	Tipo	Lista	object.required	brad.business.label	any.enum	any.const
1	transacao	Subobjeto contendo os dados da transação de criptoativo	object	false	true	transacao		
2	preco	Valor da transação. Usar ponto como separador decimal (ex.: 100000.75).	number	false	true	preco		
2	quantidadeDeMoedas	Quantidade de moedas negociadas na ordem.	number	false	true	quantidadeDeMoedas		
2	criacaoOrdem	Data e hora de criação da ordem (formato ISO 8601, ex.: 2026-03-23T14:00:00Z).	string	false	true	criacaoOrdem		
2	confirmacaoOrdem	Data e hora de confirmação da ordem (formato ISO 8601).	string	false	true	confirmacaoOrdem		
2	tipoTransacao	Tipo da transação: debito ou credito.	string	false	true	tipoTransacao	debito|credito	
2	idUsuario	Identificador do usuário (ID interno/externo).	string	false	true	idUsuario		
2	tipoOrdem	Tipo de ordem: compra ou venda.	string	false	true	tipoOrdem	compra|venda	
2	tipoAtivo	Símbolo do ativo/moeda negociado.	string	false	true	tipoAtivo	BTC|ETH|USDT	
2	idOrdem	Identificador único da ordem.	string	false	true	idOrdem		
2	statusOrdem	Status da operação no sistema.	string	false	true	statusOrdem	criada|confirmada|cancelada|falha	
2	statusSafer	Status da operação no Safer.	string	false	true	statusSafer	aprovado|reprovado|pendente	
```

O arquivo [`template-transacao-cripto.tsv`](./template-transacao-cripto.tsv) neste diretório contém o mesmo bloco pronto para download/abertura direta.

---

## 3. Ponto exato de inserção no arquivo

```
... (linhas obrigatórias: bradesco.*, schema.*)
─────────────────────────────────────────────────────────────────────
0 │ body │ Dados de entrada e saída... │ object │ false │ true │ ...
─────────────────────────────────────────────────────────────────────
  ← INSIRA AQUI as linhas do bloco TSV acima (objeto transacao + campos level 2)
─────────────────────────────────────────────────────────────────────
1 │ codigoTransacao │ ...    ← linha existente, NÃO mova nem apague
```

**Passos no Excel:**
1. Localize a linha `0 body` (coluna `Nivel = 0`, coluna `Nome = body`).
2. Clique na linha **imediatamente abaixo** dela (que provavelmente contém `1 codigoTransacao`).
3. Selecione **12 linhas** inteiras (Shift + clique na 12ª linha abaixo).
4. Clique com botão direito → **Inserir linhas** (isso empurra o conteúdo existente para baixo).
5. Cole o bloco TSV acima nessas 12 linhas novas.

---

## 4. Como lidar com duplicidade de blocos (dois schemas no mesmo arquivo)

Se o arquivo Excel contém dois schemas distintos na mesma aba (ex.: schema de log/retorno HTTP **e** schema de transação cripto), **separe-os**:

| Situação | O que fazer |
|---|---|
| **Dois schemas em abas diferentes no mesmo arquivo** | Recomendado. Duplique a aba atual (clique com direito na aba → "Mover ou copiar" → marcar "Criar cópia"). Renomeie cada aba com o nome do schema (ex.: `schema-log`, `schema-transacao-cripto`). |
| **Dois schemas em arquivos separados** | Ideal para schemas muito diferentes. Faça "Salvar como" e crie `schema-transacao-cripto.xlsx`. Mantenha as linhas `bradesco.*` e `schema.*` em ambos os arquivos (são obrigatórias em cada schema). |
| **Nunca misture** | Não coloque campos de transação cripto nas mesmas linhas dos campos de log/retorno — isso gera um JSON schema inválido com campos de contratos diferentes no mesmo objeto. |

---

## 5. Checklist de validação e geração do JSON Schema via macros

Use este checklist após preencher o template:

- [ ] **Cabeçalhos intactos**: a linha de cabeçalho da aba Schema não foi alterada.
- [ ] **Linhas obrigatórias presentes**: as linhas `bradesco.*` e `schema.*` estão todas preenchidas.
- [ ] **`0 body`** com `Tipo = object` e `object.required = true`.
- [ ] **`1 transacao`** com `Tipo = object`, `object.required = true`, `Nivel = 1`.
- [ ] **11 campos de transação** com `Nivel = 2`, todos com `object.required = true`.
- [ ] **`any.enum`** preenchido para: `tipoTransacao`, `tipoOrdem`, `tipoAtivo`, `statusOrdem`, `statusSafer`.
- [ ] **`any.const`** em branco (ou com valor fixo caso o campo seja sempre o mesmo valor).
- [ ] Clique em **Validar Schema** (macro): nenhum erro de campo não cadastrado no catálogo.
  - Se aparecer erro de catálogo: acesse **Schema_Catálogo → Inserir/Atualizar catálogo** para cada campo sinalizado, e repita a validação.
- [ ] Clique em **Gerar JSON** / **Gerar JSON Schema** (macro): o arquivo JSON é gerado sem erros.
- [ ] (Opcional) Clique em **Checklist** para conferir conformidade do schema gerado.
- [ ] Confira que o JSON gerado bate com o schema de referência em [`schema/transacao-cripto.json`](./transacao-cripto.json).

---

## Referência: estrutura esperada do JSON gerado

```json
{
  "body": {
    "type": "object",
    "required": ["transacao"],
    "properties": {
      "transacao": {
        "type": "object",
        "required": ["preco", "quantidadeDeMoedas", "criacaoOrdem", "confirmacaoOrdem",
                     "tipoTransacao", "idUsuario", "tipoOrdem", "tipoAtivo",
                     "idOrdem", "statusOrdem", "statusSafer"],
        "properties": {
          "preco":              { "type": "number" },
          "quantidadeDeMoedas": { "type": "number" },
          "criacaoOrdem":       { "type": "string" },
          "confirmacaoOrdem":   { "type": "string" },
          "tipoTransacao":      { "type": "string", "enum": ["debito", "credito"] },
          "idUsuario":          { "type": "string" },
          "tipoOrdem":          { "type": "string", "enum": ["compra", "venda"] },
          "tipoAtivo":          { "type": "string", "enum": ["BTC", "ETH", "USDT"] },
          "idOrdem":            { "type": "string" },
          "statusOrdem":        { "type": "string", "enum": ["criada", "confirmada", "cancelada", "falha"] },
          "statusSafer":        { "type": "string", "enum": ["aprovado", "reprovado", "pendente"] }
        }
      }
    }
  }
}
```

O arquivo completo com `$schema`, `title` e todas as anotações está em [`schema/transacao-cripto.json`](./transacao-cripto.json).
