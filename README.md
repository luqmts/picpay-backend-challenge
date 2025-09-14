[JAVA_BADGE]:https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white
[SPRING_BADGE]: https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white
<h1 align="center" style="font-weight: bold;">PicPay Simplificado</h1>

![java][JAVA_BADGE]
![spring][SPRING_BADGE]

>  Não participei de processo seletivo algum do PicPay, esse projeto foi desenvolvido apenas para fins de aprendizado.

- [🇧🇷 Descrição](#description-pt-br)
- [Requisitos](#requisites-pt-br)
- [Instalação e utilização](#install-and-use-pt-br)
- [Endpoints disponíveis](#available-endpoints-pt-br)
    - [POST /api/user](#post-user)
    - [PUT /api/user/{id}](#put-user)
    - [GET /api/user/](#get-user)
    - [GET /api/user/{id}](#get-user-by-id)
    - [DELETE /api/user/{id}](#delet-user)
    - [POST /api/transaction/transfer](#post-transaction)
    - [GET /api/transaction/transfer](#get-transactions)

<h1 id="description-pt-br">🇧🇷 Descrição</h1>

Esse projeto foi desenvolvido como solução do desafio técnico de back-end proposto pelo PicPay em seu repositório oficial, 
podendo ser encontrado a proposta e mais informações [aqui](https://github.com/PicPay/picpay-desafio-backend).

A aplicação possui duas entidades, _User_ e _Transaction_, a primeira apresentando os Usuários que podem ser gerenciados
e o segundo sendo responsável por representar uma Transação entre dois Usuários.

<h1 id="requisites-pt-br">Requisitos</h1>

- [Java 21](https://www.oracle.com/br/java/technologies/downloads/)
- [Apache Kafka 4.0.0](https://kafka.apache.org/downloads)

<h1 id="install-and-use-pt-br">Instalação e utilização</h1>

1. Realize o clone do repositório na sua máquina com `git clone your-project-url-in-github`;
2. Verifique se as dependências no arquivo [pom](./pom.xml) foram adicionados corretamente no projeto, caso não,
   execute o comando `mvn clean install`;
3. Inicie o Kafka na porta 9092;
4. Caso o tópico _notify_mail_topic_ não tenha sido criado, crie ele;
5. Execute o comando `mvn spring-boot:run` para iniciar a aplicação.

<h1 id="available-endpoints-pt-br">Endpoints disponíveis</h1>

Abaixo há todas as rotas de API disponíveis atualmente, o corpo da requisição, caso necessário e o que será retornado.

| Rota                                                | Descrição                             | 
|-----------------------------------------------------|---------------------------------------|
| [POST /api/user](#post-user)                        | Cadastra novo usuário                 | 
| [PUT /api/user/{id}](#put-user)                     | Atualiza usuário a partir de um id    | 
| [GET /api/user/](#get-user)                         | Obtém todos usuários cadastrados      | 
| [GET /api/user/{id}](#get-user-by-id)               | Obtém usuário a partir de um id       | 
| [DELETE /api/user/{id}](#delet-user)                | Deleta um usuário a partir de um id   | 
| [POST /api/transaction/transfer](#post-transaction) | Registra uma nova transferência       | 
| [GET /api/transaction/transfer](#get-transactions)  | Obtém todas transferências realizadas |


## CRUD de Usuários
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

## Transações

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
### GET /api/transaction/transfer
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