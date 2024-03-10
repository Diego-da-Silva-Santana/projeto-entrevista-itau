# Desafio Itau

## Sobre o projeto

O projeto é uma aplicação WEB desenvolvida para participar do processo seletivo para vaga Analista Júnior.

Essa é uma aplicação de CRUD básico em que consiste em manipular dados no banco através da entidade DadosCadastro.

## FUNCIONALIDADES :

- Encontra uma pessoa registrada no sistema por ID.
- Encontra uma lista de pessoas registradas no sistema.
- Registra uma pessoa no sistema.
- Atualiza dados de uma pessoa do sistema.
- Deleta uma pessoa do sistema.



## Modelo conceitual






## Tecnologias utilizadas

- Java 11
- Spring Boot
- Maven
- JPA / Hibernate
- H2
- JUnit 5
- Mockito 3



## Estrutura

![padrão camadas (1)](https://github.com/Diego-da-Silva-Santana/desafio-entrevista-itau/assets/129296254/227e37e2-6445-4a7f-b294-112f18251017)

## Rotas
[POST]
<b>/cadastros</b>

Exemplo:<br>
Headers<br>
Content-Type = application/json<br><br>
Body
``` json    
{
  "nome": "Diego da Santana",
	"cpf": "389.910.268-18",
	"idade": 36,
	"pais": "Brasil"
}
```
Retorno<br>
Status: 201 Created
``` json
{
	"id": 4,
	"nome": "Diego da Santana",
	"cpf": "389.910.268-18",
	"idade": 36,
	"pais": "Brasil"
}
```


[GET]
<b>/cadastros/{id}</b> <br>

Exemplo:<br>
/cadastros/1 <br>
Retorno <br>
Status: 200 OK
``` json
{
	"id": 1,
	"nome": "Diego da Silva Santana",
	"cpf": "417.701.438-90",
	"idade": 36,
	"pais": "Brasil"
}
```


[GET]
<b>/cadastros</b> <br>

Exemplo:<br>
/cadastros <br>
Retorno<br>
Status: 200 OK
``` json
[
	{
		"id": 1,
		"nome": "Diego da Silva Santana",
		"cpf": "417.701.438-90",
		"idade": 36,
		"pais": "Brasil"
	},
	{
		"id": 2,
		"nome": "Pedro da Silva Santana",
		"cpf": "208.941.580-07",
		"idade": 33,
		"pais": "Argentina"
	},
	{
		"id": 3,
		"nome": "Joao da Silva Santana",
		"cpf": "556.448.560-20",
		"idade": 39,
		"pais": "Chile"
	},
	{
		"id": 4,
		"nome": "Diego da Santana",
		"cpf": "389.910.268-18",
		"idade": 36,
		"pais": "Brasil"
	}
]
}
```

[GET]
<b>/carros</b> <br>

Exemplo:<br>
/carros <br>
Retorno<br>
Status: 200 OK
``` json
[
	{
		"id": 1,
		"chassi": "9BWZZZ377VT004251",
		"nome": "Lamborghini Urus",
		"fabricante": "Automobili Lamborghini S.P.A.",
		"ano": 2023,
		"cor": "Branco",
		"status": "ALUGADO",
		"placa": "OPA0148"
	},
	{
		"id": 2,
		"chassi": "1PPZZZ377VT004285",
		"nome": "Monza",
		"fabricante": "Monza S.P.A.",
		"ano": 2022,
		"cor": "Preto",
		"status": "RESERVADO",
		"placa": "SSS0894"
	},
	{
		"id": 3,
		"chassi": "I96ZZZ377VT004285",
		"nome": "Corsa",
		"fabricante": "Chevrollet S.P.A.",
		"ano": 2015,
		"cor": "Preto",
		"status": "RESERVADO",
		"placa": "SSS0894"
	},
	{
		"id": 4,
		"chassi": "0JRZZZ377VT004211",
		"nome": "New Civic",
		"fabricante": "Honda S.P.A.",
		"ano": 1995,
		"cor": "Preto",
		"status": "RESERVADO",
		"placa": "SSS0894"
	},
	{
		"id": 5,
		"chassi": "0JRZZZ377VT004222",
		"nome": "New City",
		"fabricante": "Chevrollet S.P.A.",
		"ano": 2014,
		"cor": "Preto",
		"status": "RESERVADO",
		"placa": "SSS0894"
	},
	{
		"id": 6,
		"chassi": "0JRZZZ377VT004299",
		"nome": "New Civic",
		"fabricante": "Honda S.P.A.",
		"ano": 2000,
		"cor": "Preto",
		"status": "RESERVADO",
		"placa": "SSS0894"
	},
	{
		"id": 7,
		"chassi": "0JRZZZ377VT004295",
		"nome": "New Civic",
		"fabricante": "Automobili Lamborghini S.P.A.",
		"ano": 2014,
		"cor": "Preto",
		"status": "RESERVADO",
		"placa": "SSS0894"
	},
	{
		"id": 8,
		"chassi": "0JRZZZ377VT004278",
		"nome": "New Civic",
		"fabricante": "Honda S.P.A.",
		"ano": 1998,
		"cor": "Preto",
		"status": "RESERVADO",
		"placa": "SSS0894"
	},
	{
		"id": 9,
		"chassi": "0JRZZZ377VT004296",
		"nome": "New Civic",
		"fabricante": "Monza S.P.A.",
		"ano": 2014,
		"cor": "Preto",
		"status": "RESERVADO",
		"placa": "SSS0894"
	},
	{
		"id": 10,
		"chassi": "0JRZZZ377VT00436",
		"nome": "New Civic",
		"fabricante": "Chevrollet S.P.A.",
		"ano": 1960,
		"cor": "Preto",
		"status": "RESERVADO",
		"placa": "SSS0894"
	},
	{
		"id": 11,
		"chassi": "1PPZZZ7T004285",
		"nome": "Monza",
		"fabricante": " Monza S.P.A.",
		"ano": 2022,
		"cor": "Preto",
		"status": "RESERVADO",
		"placa": "SS088"
	}
]
```

[DELETE]
<b>/cadastros</b>

Exemplo: /carros/1<br>

Retorno<br>
Status: 204 No Content

## Códigos de erros

- <b>404 Not Found</b>: O carro com ID: 1 não foi encontrado na base de dados.
- <b>404 Not Found</b>: "O CPF: 389.918.268-18 já está cadastrado na base de dados"



## Autor
Diego da Silva Santana

https://www.linkedin.com/in/diego-da-silva-santana-252a41b3/
