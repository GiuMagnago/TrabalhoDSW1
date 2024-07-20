drop database if exists Sistema;

create database Sistema;

use Sistema;

CREATE TABLE Usuario(
    id_usuario bigint not null auto_increment PRIMARY KEY, 
    email varchar(50) not null,
    senha varchar(50) not null
);

CREATE TABLE Empresa(
    id_empresa bigint not null auto_increment, 
    id_usuario bigint not null FOREIGN KEY REFERENCES Usuario(id_usuario) ON DELETE CASCADE ON UPDATE Usuario, 
    cnpj varchar(14) not null, 
    nome varchar(256) not null,
    descricao varchar(256), 
    cidade varchar(40) not null
);

CREATE TABLE Profissional(
    id_profissional bigint not null auto_increment PRIMARY KEY,  
    id_usuario bigint not null FOREIGN KEY REFERENCES Usuario(id_usuario) ON DELETE CASCADE ON UPDATE Usuario, 
    cpf varchar(11) not null, 
    nome varchar(256) not null, 
    telefone varchar(15) not null, 
    sexo varchar(15) not null, 
    datanasc date not null
);

CREATE TABLE Vaga(
    id_vaga bigint not null auto_increment PRIMARY KEY, 
    id_empresa bigint not null FOREIGN KEY REFERENCES Empresa(id_empresa) ON DELETE CASCADE ON UPDATE Empresa, 
    cnpj_empresa varchar(14), 
    descricao varchar(50), 
    datalimite date, 
);

create table Candidatura(
    id_profissional bigint not null FOREIGN KEY REFERENCES Profissional(id_profissional) ON DELETE CASCADE ON UPDATE Profissional,
    id_empresa bigint not null FOREIGN KEY REFERENCES Empresa(id_empresa) ON DELETE CASCADE ON UPDATE Empresa, 
    statusCandidatura varchar(20)
);
