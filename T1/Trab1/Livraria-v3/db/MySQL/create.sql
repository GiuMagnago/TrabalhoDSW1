drop database if exists Sistema;

create database Sistema;

use Sistema;

create table Empresa(id bigint not null auto_increment, email varchar(256) not null,senha varchar(256) not null, cnpj varchar(18) not null, nome varchar(256) not null,descricao varchar(256), cidade varchar(40) not null ,primary key (id));

create table Profissional(id bigint not null auto_increment, email varchar(256) not null, senha varchar(256) not null, CPF varchar(20) not null, nome varchar(256) not null, telefone varchar(15) not null, sexo varchar(15), datanasc date not null,primary key (id));

create table Vaga(id bigint not null auto_increment, empresa_id bigint not null, profissional_id bigint, primary key (id), foreign key (empresa_id) references Empresa, foreign key (profissional_id) references Profissional);

create table Usuario(id bigint not null auto_increment, nome varchar(256) not null, login varchar(20) not null unique, senha varchar(64) not null, papel varchar(10), primary key (id));

insert into Usuario(nome, login, senha, papel) values ('Administrador', 'admin', 'admin', 'ADMIN');

insert into Usuario(nome, login, senha, papel) values ('Usuario', 'user', 'user', 'USER');

insert into Empresa(email, senha, cnpj, nome, descricao, cidade) values  ('google@gmail.com', 'google123', '55.789.390/0008-99', 'Google', '...', 'Sao Paulo');

insert into Empresa(email, senha, cnpj, nome, descricao, cidade) values ('apple12@gmail.com', 'stevejobs123', '71.150.470/0001-40', 'Apple', '...', 'Araraquara');

insert into Empresa(email, senha, cnpj, nome, descricao, cidade) values ('chocolate@gmail.com', 'garoto', '32.106.536/0001-82', 'Nestle', '...', 'Campinas');

insert into Profissional(email, senha, cpf, nome, telefone, sexo, datanasc) values ('pedrogames@gmail.com', 'minecraft', 'Pedro Souza', 'Masculino', '222.222.222-11', '99999-9999', '2000/01/01');

insert into Profissional(email, senha, cpf, nome, telefone, sexo, datanasc) values  ('giuseppeER@gmail.com', 'malenia', 'Giuseppe Chaves', 'Masculino', '444.444.444-22', '11111-1111', '2002/02/02');

insert into Profissional(email, senha, cpf, nome, telefone, sexo, datanasc) values ('marialuiza123@gmail.com', 'malu4052', 'Maria Luiza Silva', 'Feminino', '111.111.111-33', '22222-2222', '2003/03/03');
