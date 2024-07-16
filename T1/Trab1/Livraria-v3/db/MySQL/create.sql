drop database if exists Sistema;

create database Sistema;

use Sistema;

create table Empresa(id bigint not null auto_increment, email varchar(256) not null,senha varchar(256) not null, cnpj varchar(18) not null, nome varchar(256) not null,descricao varchar(256), cidade varchar(40) not null ,primary key (id));

create table Profissional(id bigint not null auto_increment, email varchar(256) not null, senha varchar(256) not null, CPF varchar(20) not null, nome varchar(256) not null, telefone varchar(15) not null, sexo char, datanasc varchar(10) not null,primary key (id));

insert into Empresa(cnpj, nome, email, senha, cidade) values  ('55.789.390/0008-99', 'Google', 'google@gmail.com', 'google123', 'Sao Paulo');

insert into Empresa(cnpj, nome, email, senha, cidade) values ('71.150.470/0001-40', 'Apple', 'apple12@gmail.com', 'stevejobs123', 'Araraquara');

insert into Empresa(cnpj, nome, email, senha, cidade) values ('32.106.536/0001-82', 'Nestle', 'chocolate@gmail.com', 'garoto', 'Campinas');

insert into Profissional(email, senha, nome, sexo, cpf, telefone, datanasc) values ('pedrogames@gmail.com', 'minecraft', 'Pedro Souza', 'M', '222.222.222-11', '99999-9999', '01/01/2000');

insert into Profissional(email, senha, nome, sexo, cpf, telefone, datanasc) values  ('giuseppeER@gmail.com', 'malenia', 'Giuseppe Chaves', 'M', '444.444.444-22', '11111-1111', '02/02/2002');

insert into Profissional(email, senha, nome, sexo, cpf, telefone, datanasc) values ('marialuiza123@gmail.com', 'malu4052', 'Maria Luiza Silva', 'F', '111.111.111-33', '22222-2222', '03/03/2003');
