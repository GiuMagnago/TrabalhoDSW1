Roteiro de execução:

É preciso Java, JDK, Maven, MySQL, Spring-Boot. Com tudo isso instalado, basta abrir a pasta do projeto e executar o comando: mvn spring-boot:run

Após rodar o projeto, basta utilizar os enpoints abaixo (recomendamos também objetos em JSON para serem utilizados):

Cria um novo profissional [Create - CRUD]
POST http://localhost:8080/api/profissionais
Body: raw/JSON (application/json)
JSON exemplo:
{
    "nome": "lucas",
    "email": "lucas@gmail.com",
    "senha": "123",
    "cpf": "333.333.333-33",
    "telefone": "(55) 5555-5555",
    "sexo": "M",
    "dataNasc": "2001-01-01T02:00:00.000+00:00"
}

Retorna a lista de profissionais [Read - CRUD]
GET http://localhost:8080/api/profissionais

Retorna o profissional de id = {id} [Read - CRUD]
GET http://localhost:8080/api/profissionais/{id}

Atualiza o profissional de id = {id} [Update - CRUD]
PUT http://localhost:8080/api/profissionais/{id}
Body: raw/JSON (application/json)
JSON exemplo:
{
    "id": 4,
    "nome": "joao neves",
    "email": "joao@gmail.com",
    "senha": "$2a$10$R7q3Gpmg8Dq4fs1CI2KM5ObzAmsqOWX2dQ.w0C9WxeIfvrTBJTO.C",
    "cpf": "111.111.111-11",
    "telefone": "(99) 9999-9999",
    "sexo": "M",
    "dataNasc": "2001-01-01T02:00:00.000+00:00"
}

Remove o profissional de id = {id} [Delete - CRUD]
DELETE http://localhost:8080/api/profissionais/{id}

REST API -- CRUD de empresas
Cria uma nova empresa [Create - CRUD]
POST http://localhost:8080/api/empresas
Body: raw/JSON (application/json)
JSON exemplo:
{
    "nome": "samsung",
    "email": "samsung@gmail.com",
    "senha": "123",
    "cnpj": "33.333.333/3333-33",
    "descricao": "aaaa",
    "cidade": "Belo Horizonte"
}

Retorna a lista de empresas [Read - CRUD]
GET http://localhost:8080/api/empresas

Retorna a empresa de id = {id} [Read - CRUD]
GET http://localhost:8080/api/empresas/{id}

Retorna a lista de todas as empresas da cidade de nome = {nome}
GET http://localhost:8080/api/empresas/cidades/{nome}

Atualiza a empresa de id = {id} [Update - CRUD]
PUT http://localhost:8080/api/empresas/{id}
Body: raw/JSON (application/json)
JSON exemplo:
{
    "nome": "samsung",
    "email": "samsung@gmail.com",
    "senha": "123",
    "cnpj": "33.333.333/3333-33",
    "descricao": "aaaa",
    "cidade": "Belo Horizonte"
}

Remove a empresa de id = {id} [Delete - CRUD]
DELETE http://localhost:8080/api/empresas/{id}

REST API -- Retorna a lista de vagas [Read - CRUD]
GET http://localhost:8080/api/vagas

REST API -- Retorna a vaga de id = {id} [Read - CRUD]
GET http://localhost:8080/api/vagas/{id}

REST API -- Retorna a lista de vagas (em aberto) da empresa de id = {id} [Read - CRUD]
GET http://localhost:8080/api/vagas/empresas/{id}

Dados Usados para Popular:

Nome do banco de dados: SistemaVagas

Foi utilizado o arquivo "data.sql" para popular o banco, o arquivo application.properties está configurado para executar o script SQL sempre que a aplicação rodar.

#### Usuários ####

1. Administrador:
	ID: 1
	Nome: Administrador
	Email: admin@gmail.com
	Senha: admin (criptografada com BCryptEnconder)
	Papel: ROLE_ADMIN (Administrador do sistema)

2. Google (Empresa):
	ID: 2
	Nome: Google
	Email: google@gmail.com
	Senha: 123 (criptografada com BCryptEnconder)
	Papel: ROLE_EMPRESA (Empresa)

3. Nestlé (Empresa):
	ID: 3
	Nome: Nestlé
	Email: nestle@gmail.com
	Senha: 123 (criptografada com BCryptEnconder)
	Papel: ROLE_EMPRESA (Empresa)
	Ativo: Sim (enable = TRUE)

4. João (Profissional):
	ID: 4
	Nome: João
	Email: joao@gmail.com
	Senha: 123 (criptografada com BCryptEnconder)
	Papel: ROLE_PROFISSIONAL (Profissional)

5. recebeEmail (Profissional):
	ID: 5
	Nome: recebeEmail
	Email: altereIsso@gmail.com
	Senha: 123 (criptografada com BCryptEnconder)
	Papel: ROLE_PROFISSIONAL (Profissional)

#### Vagas ####
1. Vaga 1 (Em aberto):
	CNPJ da Empresa: 11.111.111/1111.11 (Google)
	Remuneração: 3000
	Descrição: ...
	Data Limite: 30/08/2025
	Empresa ID: 2 (Google)

2. Vaga 2 (Fechada):
	CNPJ da Empresa: 22.222.222/2222.22 (Nestlé)
	Remuneração: 5000
	Descrição: ...
	Data Limite: 30/07/2024
	Empresa ID: 3 (Nestlé)


3. Vaga 3 (Em aberto):
	CNPJ da Empresa: 11.111.111/1111.11 (Google)
	Remuneração: 7000
	Descrição: ...
	Data Limite: 30/12/2025
	Empresa ID: 2 (Google)
	
#### Candidaturas ####
1. Candidatura 1 (Fase = Em aberto):
	Profissional ID: 4 (João)
	Vaga ID: 1 (Vaga 1 - Google)
	Currículo: src/main/resources/uploads/curriculo_3.pdf
	Status: ABERTO

2. Candidatura 2 (Fase = Análise):
	Profissional ID: 5 (recebeEmail)
	Vaga ID: 2 (Vaga 2 - Nestlé)
	Currículo: src/main/resources/uploads/curriculo_4.pdf
	Status: ABERTO

CheckList:

REST API -- CRUD de Profissionais
(X) Implementado ( ) Parcialmente implementado ( ) Não implementado
Divisão na implementação da funcionalidade: Giuseppe Chaves (100%)

REST API -- CRUD de Empresas e GETs de Vagas
(X) Implementado ( ) Parcialmente implementado ( ) Não implementado
Divisão na implementação da funcionalidade: Gabriel Spolon (50%) e Antônio Cícero (50%)




