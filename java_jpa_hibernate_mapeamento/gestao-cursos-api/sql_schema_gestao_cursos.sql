-- Esquema do Banco de Dados para Sistema de Gestão de Cursos
-- Gerado automaticamente pelo Hibernate com base nas entidades JPA

-- Tabela Professor
CREATE TABLE professor (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nome_completo VARCHAR(255) NOT NULL,
    matricula VARCHAR(255) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL
);

-- Tabela Aluno
CREATE TABLE aluno (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nome_completo VARCHAR(255) NOT NULL,
    matricula VARCHAR(255) NOT NULL UNIQUE,
    nascimento DATE,
    email VARCHAR(255) NOT NULL
);

-- Tabela Curso
CREATE TABLE curso (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nome VARCHAR(255) NOT NULL,
    sigla VARCHAR(255) NOT NULL,
    professor_id INTEGER,
    FOREIGN KEY (professor_id) REFERENCES professor(id)
);

-- Tabela Telefone (para Professor e Aluno)
CREATE TABLE telefone (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    ddd VARCHAR(255) NOT NULL,
    numero VARCHAR(255) NOT NULL,
    professor_id INTEGER,
    aluno_id INTEGER,
    FOREIGN KEY (professor_id) REFERENCES professor(id),
    FOREIGN KEY (aluno_id) REFERENCES aluno(id)
);

-- Tabela Endereco (para Aluno)
CREATE TABLE endereco (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    logradouro VARCHAR(255) NOT NULL,
    endereco VARCHAR(255) NOT NULL,
    numero VARCHAR(255) NOT NULL,
    bairro VARCHAR(255) NOT NULL,
    cidade VARCHAR(255) NOT NULL,
    estado VARCHAR(255) NOT NULL,
    cep INTEGER NOT NULL,
    aluno_id INTEGER,
    FOREIGN KEY (aluno_id) REFERENCES aluno(id)
);

-- Tabela Material do Curso
CREATE TABLE material_curso (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    url VARCHAR(255) NOT NULL,
    curso_id INTEGER,
    FOREIGN KEY (curso_id) REFERENCES curso(id)
);

-- Tabela de relacionamento Curso-Aluno (Many-to-Many)
CREATE TABLE curso_aluno (
    curso_id INTEGER NOT NULL,
    aluno_id INTEGER NOT NULL,
    PRIMARY KEY (curso_id, aluno_id),
    FOREIGN KEY (curso_id) REFERENCES curso(id),
    FOREIGN KEY (aluno_id) REFERENCES aluno(id)
);

-- Inserção de dados de exemplo
INSERT INTO professor (nome_completo, matricula, email) VALUES 
('Dr. João Silva', 'PROF001', 'joao.silva@universidade.edu'),
('Dra. Ana Costa', 'PROF002', 'ana.costa@universidade.edu');

INSERT INTO aluno (nome_completo, matricula, nascimento, email) VALUES 
('Maria Santos', 'ALU001', '2000-05-15', 'maria.santos@email.com'),
('Pedro Oliveira', 'ALU002', '1999-10-20', 'pedro.oliveira@email.com');

INSERT INTO curso (nome, sigla, professor_id) VALUES 
('Programação Java', 'JAVA101', 1),
('Banco de Dados', 'BD101', 2);

INSERT INTO telefone (ddd, numero, professor_id) VALUES 
('11', '99999-9999', 1);

INSERT INTO telefone (ddd, numero, aluno_id) VALUES 
('11', '88888-8888', 1),
('11', '77777-7777', 2);

INSERT INTO endereco (logradouro, endereco, numero, bairro, cidade, estado, cep, aluno_id) VALUES 
('Rua', 'Rua das Flores', '123', 'Centro', 'São Paulo', 'SP', 01000000, 1),
('Avenida', 'Avenida Paulista', '456', 'Bela Vista', 'São Paulo', 'SP', 01310100, 2);

INSERT INTO material_curso (url, curso_id) VALUES 
('https://docs.oracle.com/javase/tutorial/', 1),
('https://www.postgresql.org/docs/', 2);

INSERT INTO curso_aluno (curso_id, aluno_id) VALUES 
(1, 1),
(1, 2),
(2, 1);