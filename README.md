[JAVA_BADGE]:https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white
[SPRING_BADGE]: https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white
[KAFKA_BADGE]: https://img.shields.io/badge/Apache_Kafka-231F20?style=for-the-badge&logo=apache-kafka&logoColor=white
<h1 align="center" style="font-weight: bold;">PicPay Simplificado</h1>

![java][JAVA_BADGE]
![spring][SPRING_BADGE]
![kafka][KAFKA_BADGE]

>  Não participei de processo seletivo algum do PicPay, esse projeto foi desenvolvido apenas para fins de aprendizado.

- [🇧🇷 Descrição](#description-pt-br)
- [Requisitos](#requisites-pt-br)
- [Instalação e utilização](#install-and-usage-pt-br)
- [Endpoints disponíveis](#available-endpoints-pt-br)
  - [Corpo das requisições e respostas dos endpoints](#request-response-body)
- [🇺🇸 Description](#description-en-us)
- [Requisites](#requisites-en-us)
- [Installation and usage](#install-and-usage-en-us)
- [Available Endpoints](#available-endpoints-en-us)
  - [Request and Response Body endpoint](#request-response-body)

<h1 id="description-pt-br">🇧🇷 Descrição</h1>

Esse projeto foi desenvolvido como solução do desafio técnico de back-end proposto pelo PicPay em seu repositório oficial, 
podendo ser encontrado a proposta e mais informações [aqui](https://github.com/PicPay/picpay-desafio-backend).

A aplicação possui duas entidades, _User_ e _Transaction_, a primeira apresentando os Usuários que podem ser gerenciados
e o segundo sendo responsável por representar uma Transação entre dois Usuários.

As notificações enviadas através da aplicação são gerenciadas por um microsserviço, durante o desenvolvimento foi utilizado
o que está disponível [nesse repositório](https://github.com/luqmts/notification), caso queira utilizar um próprio, a aplicação cria mensagens no tópico _notify_mail_topic_.

<h1 id="requisites-pt-br">Requisitos</h1>

- [Java 21](https://www.oracle.com/br/java/technologies/downloads/)
- [Apache Kafka 4.0.0](https://kafka.apache.org/downloads)

<h1 id="install-and-usage-pt-br">Instalação e utilização</h1>

1. Realize o clone do repositório na sua máquina com `git clone your-project-url-in-github`;
2. Verifique se as dependências no arquivo [pom](./pom.xml) foram adicionados corretamente no projeto, caso não,
   execute o comando `mvn clean install`;
3. Inicie o Kafka na porta 9092;
4. Caso o tópico _notify_mail_topic_ não tenha sido criado, crie ele;
5. Execute o comando `mvn spring-boot:run` para iniciar a aplicação.

<h1 id="available-endpoints-pt-br">Endpoints disponíveis</h1>

Abaixo há todas as rotas de API disponíveis atualmente e uma descrição, ao clicar em alguma, é possível acessar o corpo da
resposta e caso exista, o corpo da requisição.


| Rota                                                | Descrição                             | 
|-----------------------------------------------------|---------------------------------------|
| [POST /api/user](#post-user)                        | Cadastra novo usuário                 | 
| [PUT /api/user/{id}](#put-user)                     | Atualiza usuário a partir de um id    | 
| [GET /api/user/](#get-user)                         | Obtém todos usuários cadastrados      | 
| [GET /api/user/{id}](#get-user-by-id)               | Obtém usuário a partir de um id       | 
| [DELETE /api/user/{id}](#delet-user)                | Deleta um usuário a partir de um id   | 
| [POST /api/transaction/transfer](#post-transaction) | Registra uma nova transferência       | 
| [GET /api/transaction/transfer](#get-transactions)  | Obtém todas transferências realizadas |

---

<h1 id="description-en-us">🇺🇸 Description</h1>

This project was developed as a solution for back-end technical challenge from PicPay in their official repository, where it
can be found the proposal and more info about it [here](https://github.com/PicPay/picpay-desafio-backend).

Application have two Entities, _User_ and _Transaction_, first one present Users that can be managed and second one is
responsible to represent a Transaction between two Users

Notifications sent on this application are managed by a microservice, on development was used the one available on 
[this repository](https://github.com/luqmts/notification), if you want to use another one, application create messages on topic _notify_mail_topic_.

<h1 id="requisites-en-us">Requisites</h1>

- [Java 21](https://www.oracle.com/br/java/technologies/downloads/)
- [Apache Kafka 4.0.0](https://kafka.apache.org/downloads)

<h1 id="install-and-usage-en-us">Installation and usage</h1>

1. Clone repository on your desktop with `git clone your-project-url-in-github`;
2. Verify if project's dependencies on [pom](./pom.xml) file were correctly added, if not, run the command `mvn clean install`;
3. Start Kafka on port 9092;
4. If topic _notify_mail_topic_ is not created, create it;
5. Run command `mvn spring-boot:run` to start application.

<h1 id="available-endpoints-en-us">Available endpoints</h1>

Bellow there are every API route actually available and a description, clicking on it, you can access the response body and
if it needs, the request body.

| Rota                                                | Descrição                    | 
|-----------------------------------------------------|------------------------------|
| [POST /api/user](#post-user)                        | Submit a new user            | 
| [PUT /api/user/{id}](#put-user)                     | Update a user by id          | 
| [GET /api/user/](#get-user)                         | Get all submitted users      | 
| [GET /api/user/{id}](#get-user-by-id)               | Get a user by id             | 
| [DELETE /api/user/{id}](#delet-user)                | Delete a user by id          | 
| [POST /api/transaction/transfer](#post-transaction) | Register a new transfer      | 
| [GET /api/transaction/transfer](#get-transactions)  | Get all registered transfers |

--- 
<h1 id="request-response-body">Corpo das requisições e respostas dos endpoints / Request and Response Body endpoint</h1>

## CRUD de Usuários / User Crud

<h3 id="post-user">POST /api/user</h3>

#### REQUEST

```json
{
    "fullName": "user sample",
    "userType": "Common",
    "document": "136.867.400-04",
    "mail": "samplemail@mail.com",
    "password": "12345678",
    "confirmPassword": "12345678",
    "balance": 150.0
}
```

#### RESPONSE

```json
{
    "id": 1,
    "fullName": "user sample",
    "userType": "Common",
    "document": "136.867.400-04",
    "mail": "samplemail@mail.com",
    "balance": 3000.0
}
```

<h3 id="put-user">PUT /api/user/{id}</h3>

#### REQUEST

```json
{
    "fullName": "user sample modified",
    "userType": "Merchant",
    "document": "90.428.379/0001-28",
    "mail": "samplemailmodified@mail.com",
    "password": "87654321",
    "confirmPassword": "87654321",
    "balance": 150.0
}
```

#### RESPONSE

```json
{
    "id": 1,
    "fullName": "user sample modified",
    "userType": "Merchant",
    "document": "90.428.379/0001-28",
    "mail": "samplemailmodified@mail.com",
    "balance": 150.0
}
```

<h3 id="get-user">GET /api/user</h3>

#### RESPONSE

```json
[
    {
      "id": 1,
      "fullName": "user sample modified",
      "userType": "Merchant",
      "document": "90.428.379/0001-28",
      "mail": "samplemailmodified@mail.com",
      "balance": 150.0
    }
]
```

<h3 id="get-user-by-id">GET /api/user/{id}</h3>

#### RESPONSE

```json
{
    "id": 1,
    "fullName": "user sample modified",
    "userType": "Merchant",
    "document": "90.428.379/0001-28",
    "mail": "samplemailmodified@mail.com",
    "balance": 150.0
}
```

<h3 id="delete-user">DELETE /api/user{id}</h3>

#### RESPONSE

```json
{
    "message": "Successfully deleted User with id: 1"
}
```

## Transações / Transactions

<h3 id="post-transaction">POST /api/transaction/transfer</h3>

#### REQUEST

```json
{
    "payerId": 1,
    "payeeId": 2,
    "amount": 5
}
```

#### RESPONSE

```json
{
    "id": 1,
    "transactionType": "UserToMerchant",
    "payer": {
        "id": 1,
        "fullName": "user sample",
        "userType": "Common",
        "document": "136.867.400-04",
        "mail": "samplemail@mail.com",
        "balance": 2995.00
    },
    "payee": {
        "id": 2,
        "fullName": "user sample 2",
        "userType": "Merchant",
        "document": "90.428.379/0001-28",
        "mail": "samplemail2@mail.com",
        "balance": 3005.00
    },
    "amount": 5,
    "created": "2025-09-14T12:41:03.072521606",
    "modified": "2025-09-14T12:41:03.07254559"
}
```

<h3 id="get-transactions">GET /api/transaction/transfer</h3>

#### RESPONSE

```json
[
    {
        "id": 1,
        "transactionType": "UserToMerchant",
        "payer": {
            "id": 1,
            "fullName": "user sample",
            "userType": "Common",
            "document": "136.867.400-04",
            "mail": "samplemail@mail.com",
            "balance": 2995.00
        },
        "payee": {
            "id": 2,
            "fullName": "user sample 2",
            "userType": "Merchant",
            "document": "90.428.379/0001-28",
            "mail": "samplemail2@mail.com",
            "balance": 3005.00
        },
        "amount": 5,
        "created": "2025-09-14T12:41:03.072521606",
        "modified": "2025-09-14T12:41:03.07254559"
    }
]
```