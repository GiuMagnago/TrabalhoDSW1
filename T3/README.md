# Roteiro de execução:

## Ferramentas utilizadas
- Java 17

- JDK
- Maven
- MySQL
- Spring-Boot

Para executar o programa localmente é necessário rodar o comando nesse diretório
```sh
$ mvn spring-boot:run
```
> Certifique-se de ter um deamon do MySQL rodando em sua maquina

## Populando o banco de dados
- Nome do banco de dados: **SistemaVagas**
- Ele pode ser configurado em `application.properties`

#### Inserindo Usuários
```sql
-- Inserir usuário administrador
INSERT INTO Usuario (id, nome, email, senha, papel, enable)
VALUES (1, 'Administrador', 'admin@gmail.com', 'a', 'ROLE_ADMIN', TRUE);

-- Inserir empresa Google
INSERT INTO Usuario (id, nome, email, senha, papel, enable)
VALUES (2, 'google', 'google@gmail.com', 'a', 'ROLE_EMPRESA', TRUE);

-- Inserir empresa Nestlé
INSERT INTO Usuario (id, nome, email, senha, papel, enable)
VALUES (3, 'nestle', 'nestle@gmail.com', 'a', 'ROLE_EMPRESA', TRUE);

-- Inserir profissional João
INSERT INTO Usuario (id, nome, email, senha, papel, enable)
VALUES (4, 'joao', 'joao@gmail.com', 'a', 'ROLE_PROFISSIONAL', TRUE);
```
#### Usuários

| ID  | Nome | Email | Senha (BCrypt) | Papel |
|-----|------|-------|----------------|-------|
| 1 | Administrador | admin@gmail.com | admin | ROLE_ADMIN |
| 2 | Google | google@gmail.com | 123 | ROLE_EMPRESA |
| 3 | Nestlé | nestle@gmail.com | 123 | ROLE_EMPRESA |
| 4 | João | joao@gmail.com | 123 | ROLE_PROFISSIONAL |
| 5 | recebeEmail | altereIsso@gmail.com | 123 | ROLE_PROFISSIONAL |

#### Inserindo Vagas
```sql
-- Inserir vaga 1
INSERT INTO Vaga (cnpj_empresa, remuneracao, descricao, dataLimite, empresa_id)
VALUES ('11.111.111/1111.11', 3000, '...', '2025-08-30', 2);

-- Inserir Vaga 2
INSERT INTO Vaga (cnpj_empresa, remuneracao, descricao, dataLimite, empresa_id)
VALUES ('22.222.222/2222.22', 5000, '...', '2024-07-30', 3);

-- Inserir Vaga 3
INSERT INTO Vaga (cnpj_empresa, remuneracao, descricao, dataLimite, empresa_id)
VALUES ('11.111.111/1111.11', 7000, '...', '2025-12-30', 2);
```
#### Vagas
| CNPJ Empresa | Remuneração | Descrição | Data Limite | Empresa ID |
|--------------|-------------|-----------|-------------|------------|
| 11.111.111/1111.11 | 3000 | ... | 2025-08-30  | 2 |
| 22.222.222/2222.22 | 5000 | ... | 2024-07-30  | 3 |
| 11.111.111/1111.11 | 7000 | ... | 2025-12-30  | 2 |

#### Inserindo Candidaturas
```sql
-- Inserir candidatura 1
INSERT INTO Candidatura (profissional_id, vaga_id, curriculoPath, status_candidatura)
VALUES (4, 1, 'src/main/resources/uploads/curriculo_3.pdf', 'ABERTO');

-- Inserir Candidatura 2
INSERT INTO Candidatura (profissional_id, vaga_id, curriculoPath, status_candidatura)
VALUES (5, 2, 'src/main/resources/uploads/curriculo_4.pdf', 'ABERTO');
```

#### Candidaturas
| Profissional ID | Vaga ID | Currículo Path | Status Candidatura |
|-----------------|---------|----------------|--------------------|
| 4 | 1 | src/main/resources/uploads/curriculo_3.pdf | ABERTO |
| 5 | 2 | src/main/resources/uploads/curriculo_4.pdf | ABERTO |


## Usando a API
- Nesse passo iremos usar a ferramenta `curl` para fazer a chamada dos endpoints
da nossa aplicação

#### CRUD Profissionais

##### CREATE
###### Criação de uma Empresa
```sh
$ curl -X POST http://localhost:8080/api/empresas \
-H "Content-Type: application/json" \
-d '{
    "nome": "Nome da Empresa",
    "email": "email@empresa.com",
    "senha": "senha123",
    "cnpj":"12.345.678/9123-45",
    "descricao": "Descrição da Empresa",
    "cidade": "Cidade da Empresa"
}'
```
Retorno esperado:
```json
{
    "id":6,
    "nome":"Empresa Teste",
    "email":"empresa@teste.com",
    "senha":"senha123",
    "cnpj":"12.345.678/9123-45",
    "descricao":"Descrição da empresa",
    "cidade":"Cidade Teste"
}
```

##### READ
###### Lendo todas as empresas:
```sh
$ curl -X GET http://localhost:8080/api/empresas
```
Retorno esperado:
```json
[
    {
        "id":2,
        "nome":"google",
        "email":"google@gmail.com",
        "senha":"$2a$10$qUG3wH58/2TLQ16OOk.6puf/CMNj2B/s8CSah/5yb4SeSD4yeeK5O",
        "cnpj":"11.111.111/1111-11",
        "descricao":"aaaa",
        "cidade":"São Carlos"
    },
    {
        "id":3,
        "nome":"nestle",
        "email":"nestle@gmail.com",
        "senha":"$2a$10$KAcf439I5rvdF9dYIDPFKupXkpYhLl/LB/faGLQaKo171f.UM7WtG",
        "cnpj":"22.222.222/2222-22",
        "descricao":"aaaa",
        "cidade":"São Paulo"
    }
]
```
###### Lendo apenas uma empresa:
```sh
$ curl -X GET http://localhost:8080/api/empresas/{empresa_id}
```
Retorno esperado:
```json
{
    "id":2,
    "nome":"google",
    "email":"google@gmail.com",
    "senha":"$2a$10$qUG3wH58/2TLQ16OOk.6puf/CMNj2B/s8CSah/5yb4SeSD4yeeK5O",
    "cnpj":"11.111.111/1111-11",
    "descricao":"aaaa",
    "cidade":"São Carlos"
}
```

##### UPDATE
###### Atualizando as informaçoes de uma empresa
```sh
$ curl -X PUT http://localhost:8080/api/empresas/{empresa_id} \
-H "Content-Type: application/json" \
-d '{
    "nome": "Nome Atualizado da Empresa",
    "email": "novoemail@empresa.com",
    "senha": "novasenha123",
    "cnpj":"11.121.111/1111-11",
    "descricao": "Descrição Atualizada da Empresa",
    "cidade": "Nova Cidade"
}'
```
Retorno esperado:
```json
{
    "id":7,
    "nome":"Nome Atualizado da Empresa",
    "email":"novoemail@empresa.com",
    "senha":"novasenha123",
    "cnpj":"11.121.111/1111-11",
    "descricao":"Descrição Atualizada da Empresa",
    "cidade":"Nova Cidade"
}
```

##### DELETE
###### Remover uma empresa do nosso banco
```sh
$ curl -X DELETE http://localhost:8080/api/empresas/{empresa_id}
```

Retorno esperado:
```json
true
```

#### CRUD Profissionais

##### CREATE
###### Criação de um Profissional
```sh
$ curl -X POST "http://localhost:8080/api/profissionais" \
-H "Content-Type: application/json" \
-d '{
    "nome": "Profissional Exemplo",
    "email": "profissional@exemplo.com",
    "senha": "senha123",
    "cpf": "987.654.321-01",
    "telefone": "11912345678",
    "sexo": "Masculino",
    "dataNasc": "2000-01-01"
}'
```
Retorno esperado:
```json
{
    "id":11,
    "nome":"Profissional Exemplo",
    "email":"profissional@exemplo.com",
    "senha":"senha123",
    "cpf":"987.654.321-01",
    "telefone":"1191234-5678",
    "sexo":"Masculino",
    "dataNasc":"2000-01-01T00:00:00.000+00:00"
}
```

##### READ
###### Lendo todos os profissionais:
```sh
$ curl -X GET "http://localhost:8080/api/profissionais"
```
Retorno esperado:
```json
[
    {
        "id":4,
        "nome":"joao",
        "email":"joao@gmail.com",
        "senha":"$2a$10$hZ7OL7ks3ife3O3/w18xEu0qdQWbY7Yz7EtGot8B1F/uX5ZL9cemS",
        "cpf":"123.456.789-00",
        "telefone":"(16) 1234-5678",
        "sexo":"M",
        "dataNasc":"2001-01-01T02:00:00.000+00:00"
    },
    {
        "id":5,
        "nome":"recebeEmail",
        "email":"altereIsso@gmail.com",
        "senha":"$2a$10$0WIlMUofHrODaw1o6ZeUZucWAhsNIDKc8sTHzoEAzJmYbW48o2wD2",
        "cpf":"123.456.789-01",
        "telefone":"(16) 1234-5678",
        "sexo":"M",
        "dataNasc":"2001-01-01T02:00:00.000+00:00"
    }
]
```
###### Lendo apenas uma empresa:
```sh
$ curl -X GET "http://localhost:8080/api/profissionais/{profissional_id}"
```
Retorno esperado:
```json
{
    "id":4,
    "nome":"joao",
    "email":"joao@gmail.com",
    "senha":"$2a$10$hZ7OL7ks3ife3O3/w18xEu0qdQWbY7Yz7EtGot8B1F/uX5ZL9cemS",
    "cpf":"123.456.789-00",
    "telefone":"(16) 1234-5678",
    "sexo":"M",
    "dataNasc":"2001-01-01T02:00:00.000+00:00"
}
```

##### UPDATE
###### Atualizando as informaçoes de uma empresa
```sh
$ curl -X PUT "http://localhost:8080/api/profissionais/{profissional_id}" \
-H "Content-Type: application/json" \
-d '{
    "nome": "Update Profissional",
    "email": "update@profissional.com",
    "senha": "novasenha123",
    "cpf": "927.654.321-01",
    "telefone": "11987654321",
    "sexo": "Masculino",
    "dataNasc": "2000-01-01"
}'
```
Retorno esperado:
```json
{
    "id":13,
    "nome":"Update Profissional",
    "email":"update@profissional.com",
    "senha":"novasenha123",
    "cpf":"927.654.321-01",
    "telefone":"11987654321",
    "sexo":"Masculino",
    "dataNasc":"2000-01-01T00:00:00.000+00:00"
}
```

##### DELETE
###### Remover uma empresa do nosso banco
```sh
curl -X DELETE "http://localhost:8080/api/profissionais/{profissional_id}
```
Retorno esperado:
```json
true
```

#### CRUD Vagas
> Nesse caso fizemos apenas os READs

##### READ
###### Lendo todas as vagas
```sh
$ curl -X GET "http://localhost:8080/api/vagas"
```
Retorno esperado:
```json
[
    {
        "id":1,
        "cnpj_empresa":"11.111.111/1111.11",
        "remuneracao":3000.0,
        "descricao":"...",
        "dataLimite":"2025-08-30T03:00:00.000+00:00",
        "empresa": 
        {
            "id":2,
            "nome":"google",
            "email":"google@gmail.com",
            "senha":"$2a$10$bNQYu0w978aU1emPe3P47Oc2DRvU6JcqVc77kRiOvjusoEbqlhwNq",
            "cnpj":"11.111.111/1111-11",
            "descricao":"aaaa",
            "cidade":"São Carlos"
        }
    },
    {
        "id":2,
        "cnpj_empresa":"22.222.222/2222.22",
        "remuneracao":5000.0,
        "descricao":"...",
        "dataLimite":"2024-07-30T03:00:00.000+00:00",
        "empresa":
        {
            "id":3,
            "nome":"nestle",
            "email":"nestle@gmail.com",
            "senha":"$2a$10$toVqRwY9ajqtbke0uSNDh.YJC3zOZz60WVhVt0xKgYxE8hElBImbO",
            "cnpj":"22.222.222/2222-22",
            "descricao":"aaaa",
            "cidade":"São Paulo"
        }
    },
    {
        "id":3,
        "cnpj_empresa":"11.111.111/1111.11",
        "remuneracao":7000.0,
        "descricao":"...",
        "dataLimite":"2025-12-30T03:00:00.000+00:00",
        "empresa":
        {
            "id":2,
            "nome":"google",
            "email":"google@gmail.com",
            "senha":"$2a$10$bNQYu0w978aU1emPe3P47Oc2DRvU6JcqVc77kRiOvjusoEbqlhwNq",
            "cnpj":"11.111.111/1111-11",
            "descricao":"aaaa",
            "cidade":"São Carlos"
        }
    }
]
```
###### Lendo apenas uma vaga:
```sh
$ curl -X GET "http://localhost:8080/api/vagas/{vaga_id}"
```
Retorno esperado:
```json
{
    "id":1,
    "cnpj_empresa":"11.111.111/1111.11",
    "remuneracao":3000.0,
    "descricao":"...",
    "dataLimite":"2025-08-30T03:00:00.000+00:00",
    "empresa":
    {
        "id":2,
        "nome":"google",
        "email":"google@gmail.com",
        "senha":"$2a$10$bNQYu0w978aU1emPe3P47Oc2DRvU6JcqVc77kRiOvjusoEbqlhwNq",
        "cnpj":"11.111.111/1111-11",
        "descricao":"aaaa",
        "cidade":"São Carlos"
    }
}
```
###### Lendo apenas as vagas de uma empresa:
```sh
$ curl -X GET "http://localhost:8080/api/vagas/empresas/{empresa_id}"
```
Retorno esperado:
```json
[
    {
        "id":1,
        "cnpj_empresa":"11.111.111/1111.11",
        "remuneracao":3000.0,
        "descricao":"...",
        "dataLimite":"2025-08-30T03:00:00.000+00:00",
        "empresa":
        {
            "id":2,
            "nome":"google",
            "email":"google@gmail.com",
            "senha":"$2a$10$bNQYu0w978aU1emPe3P47Oc2DRvU6JcqVc77kRiOvjusoEbqlhwNq",
            "cnpj":"11.111.111/1111-11",
            "descricao":"aaaa",
            "cidade":"São Carlos"
        }
    },
    {
        "id":3,
        "cnpj_empresa":"11.111.111/1111.11",
        "remuneracao":7000.0,
        "descricao":"...",
        "dataLimite":"2025-12-30T03:00:00.000+00:00",
        "empresa":
        {
            "id":2,
            "nome":"google",
            "email":"google@gmail.com",
            "senha":"$2a$10$bNQYu0w978aU1emPe3P47Oc2DRvU6JcqVc77kRiOvjusoEbqlhwNq",
            "cnpj":"11.111.111/1111-11",
            "descricao":"aaaa",
            "cidade":"São Carlos"
        }
    }
]
```
## CheckList:

#### REST API - CRUD de Profissionais
- [x] Implementado 
- [ ] Parcialmente implementado 
- [ ] Não implementado

##### Divisão na implementação da funcionalidade:
- Giuseppe Chaves (33%)
- Gabriel Spolon (33%)
- Antônio Cícero (33%)

#### REST API -- CRUD de Empresas e READs de Vagas
- [x] Implementado 
- [ ] Parcialmente implementado 
- [ ] Não implementado
##### Divisão na implementação da funcionalidade:
- Giuseppe Chaves (33%)
- Gabriel Spolon (33%) 
- Antônio Cícero (33%)
