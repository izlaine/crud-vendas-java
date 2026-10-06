# Sistema de Vendas — Programação Web 2

**Integrante:** Izlaine Carla Wilhelm Andrade  
**Disciplina:** Programação Web 2  
**Entrega:** Repositório no GitHub

Módulo Base do Sistema de Vendas (CRUD), desenvolvido conforme a atividade prática de Programação Web 2.

## Tecnologias
- Java 17
- Jakarta Faces (JSF)
- PrimeFaces 15
- CDI / Weld
- Hibernate ORM / JPA
- PostgreSQL
- Maven
- Git/GitHub

## Funcionalidades implementadas

### Marca
- Cadastro, edição, listagem e exclusão.
- Nome obrigatório e único.
- Campo descrição e situação ativo/inativo.
- Bloqueio de exclusão quando houver produtos vinculados.

### Categoria
- Cadastro, edição, listagem e exclusão.
- Relacionamento recursivo `categoriaPai`.
- Categorias raiz e subcategorias em múltiplos níveis.
- Indentação visual na seleção.
- Exibição do caminho hierárquico.
- Bloqueio de ciclos na hierarquia.
- Bloqueio de exclusão quando houver filhos ou produtos vinculados.

### Produto
- Cadastro, edição, listagem e exclusão.
- Associação obrigatória com Marca e Categoria.
- Preço maior que zero.
- Estoque maior ou igual a zero.
- Upload de JPG, PNG e WEBP até 5 MB.
- Validação de extensão e MIME type.
- Nome do arquivo armazenado com UUID para evitar colisões.
- Miniatura na tabela e pré-visualização no formulário de cadastro/edição.

### PrimeFaces / usabilidade
- `p:dataTable` com paginação, filtros e ordenação.
- `p:fileUpload` para imagens.
- `p:graphicImage` para miniaturas.
- `p:growl` para feedback.
- `p:confirmDialog` para confirmação de exclusões.
- Menus com conversores Faces para Marca e Categoria.

## Banco de dados

1. Crie o banco `sistema_vendas` no PostgreSQL.
2. Conecte-se ao banco.
3. Execute `sql/01_schema.sql`.
4. Confira usuário, senha e URL em `src/main/resources/META-INF/persistence.xml`.

Configuração usada no projeto: `localhost:5432/sistema_vendas`, usuário `postgres`. A senha está definida no `persistence.xml` e deve ser alterada caso o seu PostgreSQL utilize outra senha.

O script SQL cria as tabelas `tb_marca`, `tb_categoria` e `tb_produto`, incluindo a FK recursiva de categoria e as restrições de preço/estoque. Também insere dados iniciais para testes.

## Execução

Com Java 17+ e Maven instalados:

```bash
mvn clean package
```

O WAR será gerado em:

```text
target/sistema-vendas.war
```

Implante o WAR em um servidor compatível com Jakarta Faces/CDI/Jakarta EE configurado para o projeto.

## Upload de imagens

O formulário do produto usa `enctype="multipart/form-data"`. As imagens são gravadas no diretório de recursos de upload com nome UUID e o caminho é salvo em `caminhoImagem`. O diretório `src/main/webapp/resources/uploads` contém `.gitkeep` e os arquivos enviados em execução são ignorados pelo Git.

## Estrutura de camadas

```text
br.edu.vendas
├── controller
├── converter
├── model
├── repository
├── service
└── util
```

## Checklist para a entrega

- [x] Três entidades JPA: Marca, Categoria e Produto.
- [x] Auto-relacionamento recursivo de Categoria.
- [x] Bean Validation.
- [x] CRUD de Marca.
- [x] CRUD de Categoria com hierarquia.
- [x] CRUD de Produto com imagem e pré-visualização.
- [x] Paginação, filtros e ordenação.
- [x] `p:growl`.
- [x] `p:confirmDialog`.
- [x] Scripts DDL/DML na pasta `/sql`.
- [x] `.gitignore`.
- [ ] Executar e testar no ambiente local.
- [ ] Tirar capturas de tela reais da aplicação e adicioná-las ao README.
- [ ] Criar histórico de commits atômicos e progressivos no GitHub.
- [ ] Submeter o link do repositório no AVA.

## Sugestão de commits para o GitHub

Faça vários commits, por exemplo:

1. `chore: configurar projeto e persistencia`
2. `feat: implementar entidade e CRUD de marca`
3. `feat: implementar categorias com hierarquia`
4. `feat: implementar CRUD de produtos`
5. `feat: adicionar upload e exibicao de imagens`
6. `feat: melhorar validacoes e confirmacoes`
7. `docs: atualizar README e scripts SQL`

Não use um único commit para todo o projeto, pois o enunciado informa que isso pode ser penalizado.

## Capturas de tela

As capturas devem ser feitas após executar o sistema no seu computador, pois precisam demonstrar o funcionamento real. Recomenda-se registrar:

1. Lista/cadastro de Marcas.
2. Lista/cadastro de Categorias mostrando uma subcategoria.
3. Cadastro de Produto com Marca e Categoria.
4. Upload e miniatura da imagem.
5. Filtros/paginação.
6. Diálogo de confirmação de exclusão.
