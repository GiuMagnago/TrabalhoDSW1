drop database if exists Sistema;

create database Sistema;

use Sistema;

CREATE TABLE Usuario(
    id_usuario bigint not null auto_increment PRIMARY KEY, 
    email varchar(50) not null,
    senha varchar(50) not null
);

CREATE TABLE Empresa(
    id_empresa bigint not null auto_increment PRIMARY KEY, 
    id_usuario bigint not null, 
    cnpj varchar(14) not null, 
    nome varchar(256) not null,
    descricao varchar(256), 
    cidade varchar(40) not null, 
    FOREIGN KEY(id_usuario) REFERENCES Usuario(id_usuario) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Profissional(
    id_profissional bigint not null auto_increment PRIMARY KEY,  
    id_usuario bigint not null, 
    cpf varchar(11) not null, 
    nome varchar(256) not null, 
    telefone varchar(15) not null, 
    sexo varchar(15) not null, 
    datanasc date not null,
    FOREIGN KEY(id_usuario) REFERENCES Usuario(id_usuario) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Vaga(
    id_vaga bigint not null auto_increment PRIMARY KEY, 
    id_empresa bigint not null, 
    cnpj_empresa varchar(14) not null, 
    remuneracao double not null,
    descricao varchar(50) not null, 
    dataLimite date not null,
    FOREIGN KEY(id_empresa) REFERENCES Empresa(id_empresa) ON DELETE CASCADE ON UPDATE CASCADE
);

create table Candidatura(
    id_profissional bigint not null,
    id_vaga bigint not null, 
    statusCandidatura varchar(20),
    id_candidatura bigint not null auto_increment PRIMARY KEY,
    FOREIGN KEY (id_profissional) REFERENCES Profissional(id_profissional) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (id_vaga) REFERENCES Vaga(id_vaga) ON DELETE CASCADE ON UPDATE CASCADE
);
