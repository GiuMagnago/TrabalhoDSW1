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



-- Alterar esse
-- Alterar esse
-- Alterar esse
-- Alterar esse
-- Alterar esse
INSERT INTO Usuario (id, nome, email, senha, papel, enable)
VALUES (5, 'recebeEmail', 'altereIsso@gmail.com', 'a', 'ROLE_PROFISSIONAL', TRUE);
-- Alterar esse
-- Alterar esse
-- Alterar esse
-- Alterar esse
-- Alterar esse





-- Inserir empresa Google
INSERT INTO Empresa (id, cnpj, descricao, cidade)
VALUES (2, '11.111.111/1111-11', 'aaaa', 'São Carlos');

-- Inserir empresa Nestlé
INSERT INTO Empresa (id, cnpj, descricao, cidade)
VALUES (3, '22.222.222/2222-22', 'aaaa', 'São Paulo');

-- Inserir profissional João
INSERT INTO Profissional (id, cpf, telefone, sexo, dataNasc)
VALUES (4, '123.456.789-00', '(16) 1234-5678', 'M', '2001-01-01');

-- Inserir profissional Giuseppe
INSERT INTO Profissional (id, cpf, telefone, sexo, dataNasc)
VALUES (5, '123.456.789-01', '(16) 1234-5678', 'M', '2001-01-01');


-- Inserir vaga 1
INSERT INTO Vaga (cnpj_empresa, remuneracao, descricao, dataLimite, empresa_id)
VALUES ('11.111.111/1111-11', 3000, '...', '2025-08-30', 2);

-- Inserir Vaga 2 (comentado no Java, mas incluído aqui para referência)
INSERT INTO Vaga (cnpj_empresa, remuneracao, descricao, dataLimite, empresa_id)
VALUES ('22.222.222/2222-22', 5000, '...', '2024-07-30', 3);

-- Inserir Vaga 3
INSERT INTO Vaga (cnpj_empresa, remuneracao, descricao, dataLimite, empresa_id)
VALUES ('11.111.111/1111-11', 7000, '...', '2025-12-30', 2);

-- Inserir candidatura 1 (comentado no Java, mas incluído aqui para referência)
INSERT INTO Candidatura (profissional_id, vaga_id, curriculoPath, status_candidatura)
VALUES (4, 1, 'src/main/resources/uploads/curriculo_3.pdf', 'ABERTO');

-- Inserir Candidatura 2
INSERT INTO Candidatura (profissional_id, vaga_id, curriculoPath, status_candidatura)
VALUES (5, 2, 'src/main/resources/uploads/curriculo_4.pdf', 'ABERTO');
