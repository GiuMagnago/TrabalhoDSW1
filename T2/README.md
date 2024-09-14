Roteiro de execução:

É preciso Java, JDK, Maven, MySQL, Spring-Boot. Com tudo isso instalado, basta abrir a pasta do projeto e executar o comando: mvn spring-boot:run

Após rodar o projeto, basta acessar o link: localhost:8080

OBS: no arquivo application.properties é necessário alterar os valores das variáveis "spring.email.username" e "spring.email.password" com um e-mail e senha de APP, respectivamente. Não deixei um valor definido pelo fato do repositório ser público.

Recomendação para teste de funcionalidade R8 (enviar um email para o profissional quando o status da sua candidatura mudar):

Recomendado realizar isso antes de fazer qualquer ação no sistema. Se já realizou alterações, rodar a aplicação de novo para testar.

1- Alterar no arquivo data.sql, o registro destacado, colocando um endereço de e-mail que você tenha acesso.
2- Fazer login no sistema como a empresa Nestle (email: nestle@gmail.com; senha: 123).
3- Página de Login -> Página de Empresa -> Minhas Vagas.
4- Aparecerá 1 vaga, clique em Candidaturas.
4- Aparecerá a candidatura do usuário "recebeEmail" (o email dele corresponde ao endereço que você colocou no arquivo "data.sql" no passo 1), a data limite de inscrição dessa vaga acabou, então agora a empresa pode analisar as candidaturas e editar seus Status. Clique em Editar.
5- Altere o Status da Canditatura.
	5.1 - Se alterar para "Não Selecionado" e clicar em Atualizar, você receberá um email falando que não passou na fase de análise.
	5.2 - Se alterar para "Entrevista", aparecerá um campo para colocar o link de entrevista, clicando em Atualizar, você receberá um email falando que foi selecionado para entrevista em uma data definida e com o link inserido.
 

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
	Profissional ID: 5 (Giuseppe)
	Vaga ID: 2 (Vaga 2 - Nestlé)
	Currículo: src/main/resources/uploads/curriculo_4.pdf
	Status: ABERTO

CheckList:

R1: CRUD de profissionais (requer login de administrador)
(X) Implementado ( ) Parcialmente implementado ( ) Não implementado
Divisão na implementação da funcionalidade: Giuseppe Chaves (100%)

R2: CRUD de empresas (requer login de administrador)
(X) Implementado ( ) Parcialmente implementado ( ) Não implementado
Divisão na implementação da funcionalidade: Giuseppe Chaves (100%)

R3: Cadastro de vagas de estágio/trabalho (requer login da empresa).
(X) Implementado ( ) Parcialmente implementado ( ) Não implementado
Divisão na implementação da funcionalidade: Gabriel Spolon (100%)

R4: Listagem de todas as vagas (em aberto) em uma única página (com funcionalidade de filtrar por cidade).
(X) Implementado ( ) Parcialmente implementado ( ) Não implementado
Divisão na implementação da funcionalidade: Antônio Cícero (50%) e Gabriel Spolon (50%)

R5: Candidatura a vaga de estágio/trabalho com permitindo upload de currículo (cada profissional só se inscreve uma vez em cada vaga).
(X) Implementado ( ) Parcialmente implementado ( ) Não implementado
Divisão na implementação da funcionalidade: Giuseppe Chaves (100%)

R6: Listagem de todas as vagas de uma empresa (requer login da empresa).
(X) Implementado (X) Parcialmente implementado ( ) Não implementado
Divisão na implementação da funcionalidade: Antônio Cícero (50%) e Gabriel Spolon (50%)

R7: Listagem de todas as candidaturas de um profissional (requer login do profissional).
(X) Implementado ( ) Parcialmente implementado ( ) Não implementado
Divisão na implementação da funcionalidade: Gabriel Spolon (100%)

R8: Fase de análise das candidaturas onde as empresas pode mudar o status da candidatura do profissional e deve ser enviado um e-mail para ele (requer login de empresa).
(X) Implementado ( ) Parcialmente implementado ( ) Não implementado
Divisão na implementação da funcionalidade: Giuseppe Chaves (100%)

R9: O sistema deve ser internacionalizado em pelo menos dois idiomas: português + outro de sua escolha.
(X) Implementado ( ) Parcialmente implementado ( ) Não implementado
Divisão na implementação da funcionalidade: Antônio Cícero (100%)

R10: O sistema deve validar (tamanho, formato, etc) todas as informações (campos nos formulários) cadastradas e/ou editadas.
(X) Implementado ( ) Parcialmente implementado ( ) Não implementado
Divisão na implementação da funcionalidade: Antônio Cícero (100%)
