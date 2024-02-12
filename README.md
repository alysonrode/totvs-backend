# TOTVs-API

## Introdução

Este é um projeto simples criado a fim de um teste técnico, onde se baseia na criação de uma API completa de contas

## Tecnologias

Foram utilizados no projeto:

Docker
Java
SpringBoot
JPA
FlyWay
JUnit
Tomcat (Integrado com o springboot)

## Como utilizar
 
Com o docker e docker compose instalados na máquina, basta ir até o diretório onde está o projeto e rodar o comando ```docker-compose up -d```

São disponibilizados os seguintes endpoints da API:

# Auntenticação:

```POST: http://localhost:8080/auth``` - Esta URL deve ter um body de uma string informando seu email, e irá retornar um token de autenticação com a data de validade de 1 dia.

# Contas

Todas as chamadas dessa sessão devem conter dois headers adicionais:

```Authentication```: O valor dele é o token retornado da url de autenticação.
```email```: O valor é o e-mail utilizado para caadastro do token.

As datas utilizam o padrão ISO-8601, e devem ser enviadas neste formato.

```POST: http://localhost:8080/bill ``` Criar uma conta, o body da mesma deve seguir padrão:
```json 
{
    "dueDate": "2024-05-22T00:00:00.000-03:00",
    "payday": "2024-05-24T00:00:00.000-03:00",
    "value": "430",
    "description": "Description",
    "situation": 2
}
Situation - 0 = OPEN, 1 = PAIED, 2 = DELAYED
```

```PUT: http://localhost:8080/bill ``` Alterar uma conta, o body da mesma deve seguir padrão:
```json 
{
    "id": "233ee91f-891c-40f5-a296-7014ae19c643",
	"dueDate": "2024-05-24T003:00",
	"payday": "2024-05-25T00:00:00.000-03:00",
	"value": "4000",
	"description": "teste",
	"situation": 2
}
```
```GET: http://localhost:8080/bill ``` Buscar uma lista de contas a partir do filtro de descrição e data de venciomento, o body da mesma deve seguir padrão:
```json 
{
  "dueDate": "2024-05-25T00:00:00.000-03:00",
  "description": "teste"
}
```
```GET: http://localhost:8080/bill/find-by-id/{UUID} ``` Buscar uma conta a partir do id, o body da mesma deve seguir padrão:
```json 
{
  "dueDate": "2024-05-25T00:00:00.000-03:00",
  "description": "teste"
}
```
```POST: http://localhost:8080/bill/import-from-csv ``` Cadastrar uma lista de contas a de um arquivo CSV, o body requisição deve ser um multipart com um arquivo CSV com as seguintes colunas:
``` 
{
	dueDate
	payday
	value;
	description;
	situation;
}
```

```PATCH: http://localhost:8080/bill/update-situation/{uuid} ``` Atualizar a situação de uma conta, o body da requisição deve seguir o padrão:
``` 
{
	0, 1 ou 2 no body
}
```
```PATCH: http://localhost:8080/bill/get-total ``` Busca o valor total das contas que foram pagas no periodo informado, o body da requisião deve seguir o padrão:
```json
{
	"startDate": "2024-05-25T00:00:00.000-03:00",
	"endDate": "2024-05-30T00:00:00.000-03:00"
}
```

>  This is a challenge by [Coodesh](https://coodesh.com/)