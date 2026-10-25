# 📝 API de Tarefas

API REST desenvolvida com **Java e Spring Boot** para gerenciamento de tarefas. O projeto permite cadastrar, consultar, atualizar, excluir, filtrar e ordenar tarefas, utilizando MySQL para persistência dos dados.

O objetivo é praticar o desenvolvimento de APIs REST, organização em camadas, persistência de dados e documentação de endpoints.

## 🚀 Tecnologias utilizadas

- **Java 25**
- **Spring Boot 4**
- **Spring Web MVC** — criação dos endpoints REST
- **Spring Data JPA** — persistência e consultas ao banco
- **MySQL** — banco de dados relacional
- **Flyway** — controle de migrations do banco de dados
- **Bean Validation** — validação dos dados recebidos
- **Swagger / OpenAPI** — documentação e testes dos endpoints
- **Maven** — gerenciamento de dependências
- **Git e GitHub** — versionamento do código

## ✨ Funcionalidades

- Cadastrar novas tarefas.
- Listar todas as tarefas.
- Buscar uma tarefa pelo ID.
- Atualizar uma tarefa existente.
- Excluir uma tarefa.
- Filtrar tarefas por status de conclusão.
- Pesquisar tarefas pelo título, sem diferenciar maiúsculas e minúsculas.
- Ordenar tarefas por campo, em ordem crescente ou decrescente.
- Validar os dados recebidos pela API.
- Documentar e testar os endpoints pelo Swagger UI.

## 📋 Modelo de tarefa

Cada tarefa possui os seguintes campos:

| Campo | Descrição |
|---|---|
| `id` | Identificador da tarefa |
| `titulo` | Título da tarefa, obrigatório, com até 120 caracteres |
| `descricao` | Descrição da tarefa |
| `concluida` | Indica se a tarefa foi concluída |

## ⚙️ Como executar o projeto

### Pré-requisitos

- JDK compatível com Java 25.
- MySQL instalado e em execução.
- Git instalado.
- IDE de sua preferência, como IntelliJ IDEA.

### 1. Clonar o repositório

```bash
git clone https://github.com/ThorZucknit/TO-DO-API.git
```

### 2. Criar o banco de dados

No MySQL, crie o banco utilizado pela aplicação:

```sql
CREATE DATABASE db_tarefas;
```

Se o nome configurado na sua aplicação for diferente, ajuste o comando para corresponder à configuração local.

### 3. Configurar as variáveis de ambiente

Configure a URL, o usuário e a senha do MySQL de acordo com o `application.properties` e com a forma como o projeto carrega o arquivo `.env`.

Exemplo de variáveis:

```properties
DATABASE_URL=jdbc:mysql://localhost:3306/db_tarefas
DATABASE_USERNAME=seu_usuario
DATABASE_PASSWORD=sua_senha
```

**Importante:** não publique senhas reais, arquivos `.env` ou outras credenciais no GitHub.

### 4. Executar a aplicação

Abra o projeto na IDE e execute a classe principal `ApiTreinoApplication`.

Também é possível iniciar pelo terminal, na raiz do projeto:

```bash
./mvnw spring-boot:run
```

No Windows, use:

```powershell
.\mvnw.cmd spring-boot:run
```

## 📡 Endpoints da API

Considerando que a aplicação esteja executando em `http://localhost:8080` e que o controller utilize o prefixo `/list`:

| Método | Endpoint | Descrição |
|---|---|---|
| `GET` | `/list/all` | Lista todas as tarefas |
| `GET` | `/list/find/{id}` | Busca uma tarefa pelo ID |
| `POST` | `/list/cadastrar` | Cadastra uma tarefa |
| `PUT` | `/list/{id}` | Atualiza uma tarefa |
| `DELETE` | `/list/delete/{id}` | Exclui uma tarefa |
| `GET` | `/list/status?concluida=true` | Filtra pelo status |
| `GET` | `/list/findtitle?title=Correr` | Pesquisa pelo título |
| `GET` | `/list/order?campo=titulo&direcao=asc` | Ordena as tarefas |

### Exemplo de cadastro

Requisição:

```http
POST /api/tarefas
Content-Type: application/json
```

Corpo JSON:

```json
{
  "titulo": "Estudar Spring Boot",
  "descricao": "Praticar desenvolvimento de APIs REST",
  "concluida": false
}
```

A API retorna `201 Created` quando a tarefa é cadastrada com sucesso. Um título vazio é rejeitado pela validação.

## 📚 Documentação com Swagger

Com a aplicação em execução, acesse:

**Swagger UI:** http://localhost:8080/swagger-ui.html

**OpenAPI JSON:** http://localhost:8080/v3/api-docs

O Swagger permite visualizar os endpoints, consultar parâmetros, enviar requisições e verificar as respostas da API.

## 🗂️ Organização do projeto

O projeto utiliza uma estrutura em camadas:

```text
src/
└── main/
    ├── java/com/treino/api_treino/
    │   ├── controller/
    │   ├── entity/
    │   ├── repository/
    │   ├── service/
    │   └── ApiTreinoApplication.java
    └── resources/
        ├── application.properties
        └── db/migration/
```

- **Controller:** recebe as requisições HTTP e retorna as respostas.
- **Entity:** representa as entidades persistidas no banco de dados.
- **Repository:** realiza operações de persistência e consultas.
- **Service:** concentra a lógica de negócio.
- **Migration:** contém scripts SQL versionados pelo Flyway.

## 🎯 Objetivos de aprendizagem

Este projeto foi desenvolvido para praticar:

- Construção de APIs REST com Java e Spring Boot.
- Operações CRUD com Spring Data JPA.
- Integração com MySQL.
- Validação de dados.
- Filtros, pesquisas e ordenação.
- Versionamento de banco com Flyway.
- Documentação de APIs com Swagger.
- Versionamento de código com Git e GitHub.

## 👨‍💻 Autor

Desenvolvido como projeto de estudo em **Java Backend**, com foco na evolução prática em Spring Boot, bancos de dados relacionais e desenvolvimento de APIs REST.
