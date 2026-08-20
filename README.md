
#  Baozi Store API

> **Atividade Prática / Projeto Acadêmico**  
> **Instituição:** Centro Universitário Internacional UNINTER  
> **Curso:** Análise e Desenvolvimento de Sistemas  
> **Autor:** Magno Walério Alves Ferreira  
> **RU:** 4*****5  
> **Identificação Técnica (Pacote):** `br.com.amw.baozistore`  
> *(A sigla **AMW** no pacote base refere-se às iniciais do autor: **A**lves / **M**agno / **W**alério)*

---

##  Sobre o Projeto

O **Baozi Store API** é uma aplicação Back-End desenvolvida em Java com Spring Boot para o 
gerenciamento completo de um e-commerce (Clientes, Produtos e Pedidos).

O projeto foi construído aplicando arquitetura em camadas, persistência de dados relacional com 
PostgreSQL, validações de regras de negócio, tratamento de exceções HTTP e controle de versão.

---

##  Tecnologias Utilizadas

- **Linguagem:** Java 21
- **Framework Principal:** Spring Boot 4+
- **Mapeamento e Persistência:** Spring Data JPA / Hibernate
- **Banco de Dados:** PostgreSQL
- **Gerenciador de Dependências:** Maven
- **Testes de API:** Postman
- **Modelagem e Versionamento:** PlantUML, Git e GitHub

---

##  Arquitetura do Sistema

A aplicação segue a organização padrão de mercado em camadas bem definidas:

```text
src/main/java/br/com/amw/baozistore
│
├── controller      # Endpoints REST e recebimento de requisições HTTP
├── model           # Entidades JPA e mapeamento do banco de dados
├── repository      # Interfaces Spring Data JPA para comunicação com o banco
└── service         # Camada de regras de negócio e validações
```
---
## Camadas

- **Controller:** Responsável por receber as requisições HTTP e disponibilizar os endpoints da API.


- **Service:** 
Responsável pelas regras e operações relacionadas às entidades.


- **Repository:** Responsável pela comunicação com o banco de dados utilizando Spring Data JPA.


- **Model:** Contém as entidades utilizadas pela aplicação e seus respectivos atributos e relacionamentos.

---
## Entidades

### Cliente

    Representa os clientes cadastrados na loja.


|Campo	|  Tipo  |
| :---  | :---   |
|id	    |   Long |
|nome	|  String |
|clienteDesde |	LocalDate |

---
### Produto

Representa os produtos disponíveis na loja.

|Campo	| Tipo    |
| :--- |:--------|
|id	| Long |
|nome	| String |
|preco	| BigDecimal |
|estoque	| Boolean |

---
### Pedido

Representa os pedidos realizados pelos clientes.

|Campo	| Tipo           |
| :--- |:---------------|
|id	| Long           |
|cliente	| Cliente        |
|produto	| Produto        |
|quantidade	| Integer        |
|dataPedido | 	LocalDateTime |

O pedido possui relacionamento com as entidades Cliente e Produto.

---
## Endpoints
### Clientes
Criar cliente
POST /clientes

Exemplo de requisição:

```
{
"nome": "Magno Ferreira",
"clienteDesde": "2026-08-08"
}
```

### Listar clientes
GET /clientes
Consultar cliente por ID
GET /clientes/{id}


Exemplo:

### GET /clientes/1
Atualizar cliente
PUT /clientes/{id}

Exemplo:

```{
"nome": "João da Silva Atualizado",
"clienteDesde": "2026-08-08"
}
```
---
### Excluir cliente
DELETE /clientes/{id}

Exemplo:

DELETE /clientes/4

Após a exclusão, uma nova consulta pelo ID retorna 404 Not Found quando o cliente não está mais 
cadastrado.
---
## Produtos
### Criar produto
POST /produtos

Exemplo:

```{
"nome": "Camiseta Baozi",
"preco": 64.90,
"estoque": true
}
```
---
## Listar produtos
### GET /produtos
Consultar produto por ID
GET /produtos/{id} 


Exemplo:

GET /produtos/7
Atualizar produto
PUT /produtos/{id}

Exemplo:

```{
"nome": "Camiseta Baozi Atualizada",
"preco": 64.90,
"estoque": true
}
```
---
### Excluir produto
DELETE /produtos/{id}

Exemplo:

DELETE /produtos/8
Observação sobre exclusão de produtos

Um produto que esteja sendo referenciado por um pedido não pode ser excluído diretamente devido à 
restrição de chave estrangeira existente no banco de dados.

Essa proteção impede que um pedido fique associado a um produto inexistente.

---
## Pedidos
### Criar pedido
POST /pedidos

Exemplo:

```{
"cliente": {
"id": 1
},
"produto": {
"id": 3
},
"quantidade": 1,
"dataPedido": "2026-08-12T21:00:00"
}
```
---

## Listar pedidos
### GET /pedidos
Consultar pedido por ID
GET /pedidos/{id}

Exemplo:

GET /pedidos/10
Atualizar pedido
PUT /pedidos/{id}

Exemplo:

```{
"cliente": {
"id": 1
},
"produto": {
"id": 3
},
"quantidade": 2,
"dataPedido": "2026-08-13T20:15:02"
}
```
---
## Excluir pedido
### DELETE /pedidos/{id}

Exemplo:

DELETE /pedidos/10

Após a exclusão, uma nova consulta pelo ID retorna 404 Not Found quando o pedido não 
está mais cadastrado.
---

## Banco de dados

O projeto utiliza PostgreSQL como sistema gerenciador de banco de dados.

Banco utilizado durante o desenvolvimento:

baozi_store

Configuração utilizada pela aplicação:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/baozi_store
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
```

### Segurança das credenciais

As credenciais do banco de dados não são armazenadas diretamente no código-fonte.

A aplicação utiliza as seguintes variáveis de ambiente:

**DB_USERNAME
DB_PASSWORD**

Essa configuração evita que a senha do banco seja exposta diretamente no arquivo application.properties.

---
### Configuração das variáveis de ambiente

No IntelliJ IDEA, as variáveis podem ser configuradas em:

_Run
→ Edit Configurations...
→ Environment variables_

Adicionar:

```
DB_USERNAME=postgres
DB_PASSWORD=sua_senha_do_postgresql
```
A senha utilizada deve ser a senha configurada no PostgreSQL do ambiente local.

Não coloque a senha real no código-fonte ou no README.

## Configuração do projeto
### Pré-requisitos

Antes de executar o projeto, é necessário possuir:

* Java 21 instalado
* PostgreSQL instalado e em execução
* Banco de dados baozi_store criado
* IntelliJ IDEA ou outra IDE compatível com projetos Maven
* Postman, caso queira realizar os testes da API

## Configuração do banco

_Crie o banco de dados:_

 ```CREATE DATABASE baozi_store;```

Depois configure as variáveis:

**_DB_USERNAME
DB_PASSWORD_**

A aplicação utiliza:
```
spring.jpa.hibernate.ddl-auto=update
```
Portanto, as tabelas são gerenciadas pelo Hibernate conforme o mapeamento das entidades.

---
## Executando a aplicação

Após configurar o PostgreSQL e as variáveis de ambiente:

1. Abra o projeto na IDE.
2. Confirme que o Java 21 está configurado.
3. Configure DB_USERNAME e DB_PASSWORD.
4. Execute a classe principal da aplicação:
5. BaoziStoreApiApplication

A aplicação será iniciada no servidor local:
```
http://localhost:8080
```

---
## Testes com Postman

Durante o desenvolvimento, os endpoints foram testados utilizando o Postman.

Foram realizados testes envolvendo:

* Criação de clientes
* Listagem de clientes
* Consulta de cliente por ID
* Atualização de clientes
* Exclusão de clientes
* Criação de produtos
* Listagem de produtos
* Consulta de produto por ID
* Atualização de produtos
* Exclusão de produtos
* Criação de pedidos
* Listagem de pedidos
* Consulta de pedido por ID
* Atualização de pedidos
* Exclusão de pedidos

As operações foram verificadas por meio dos códigos de resposta HTTP retornados pela API.

---
## Diagrama de Caso de Uso

O projeto possui um diagrama de caso de uso desenvolvido em PlantUML.

Arquivo:
```
docs/uml/caso-de-uso.puml
```
O diagrama representa as principais operações disponíveis para o usuário da API:

* Gerenciar clientes
* Gerenciar produtos
* Gerenciar pedidos
* Versionamento

O projeto utiliza Git para controle de versão e GitHub para armazenamento remoto do código-fonte.

---
## Repositório:

https://github.com/MagnoDevbraul/baozi-store-api

spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
Segurança das credenciais)

O histórico de commits registra a evolução do desenvolvimento da aplicação, incluindo implementação 
das funcionalidades, correções, testes e documentação.
---

## Identificação técnica

A aplicação utiliza o pacote:

_`br.com.amw.baozistore`_

A sigla AMW, utilizada na identificação técnica do projeto, refere-se ao meu nome:
 Magno Walério Alves

Autor:
**Magno Walério Alves**

Projeto desenvolvido como atividade acadêmica do curso de:

**Análise e Desenvolvimento de Sistemas** — **UNINTER**

---
## Considerações finais

 _O projeto foi desenvolvido com o objetivo de aplicar conceitos de desenvolvimento de APIs REST 
utilizando Java e Spring Boot, integração com banco de dados PostgreSQL, organização em camadas, 
operações CRUD, relacionamentos entre entidades, testes utilizando Postman e controle de versão 
com Git e GitHub._


