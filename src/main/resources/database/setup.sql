CREATE TABLE IF NOT EXISTS questao(
    id INTEGER PRIMARY KEY NOT NULL,
    texto TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS tarefa(
    id INTEGER PRIMARY KEY NOT NULL,
    nota_maxima FLOAT NOT NULL DEFAULT 0 
);

CREATE TABLE IF NOT EXISTS tarefa_questao(
    id_tarefa INTEGER NOT NULL,
    id_questao INTEGER NOT NULL,
    PRIMARY KEY (id_tarefa, id_questao),
    FOREIGN KEY (id_tarefa) REFERENCES tarefa(id),
    FOREIGN KEY (id_questao) REFERENCES questao(id)
);

CREATE TABLE IF NOT EXISTS aula(
);

CREATE TABLE IF NOT EXISTS unidade(
    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    numero INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS unidade_aula(
    unidade_id INTEGER NOT NULL,
    aula_id INTEGER NOT NULL,
    PRIMARY KEY (unidade_id, aula_id),
    FOREIGN KEY (unidade_id) REFERENCES unidade(id),
    FOREIGN KEY (aula_id) REFERENCES aula(id)
);

CREATE TABLE IF NOT EXISTS curso (
    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    titulo TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS curso_unidade(
    curso_id INTEGER NOT NULL,
    unidade_id INTEGER NOT NULL,
    PRIMARY KEY (curso_id, unidade_id),
    FOREIGN KEY (curso_id) REFERENCES curso(id),
    FOREIGN KEY (unidade_id) REFERENCES unidade(id)
);

CREATE TABLE IF NOT EXISTS unidade_tarefa(
    unidade_id INTEGER NOT NULL,
    tarefa_id INTEGET NOT NULL,
    PRIMARY KEY (unidade_id, tarefa_id),
    FOREIGN KEY (unidade_id) REFERENCES unidade(id),
    FOREIGN KEY (tarefa_id) REFERENCES tarefa(id)
);

CREATE TABLE IF NOT EXISTS usuario (
    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    name TEXT NOT NULL,
    fullName TEXT NOT NULL,
    email TEXT NOT NULL,
    apelido TEXT NOT NULL,
    linkFoto TEXT
);

CREATE TABLE IF NOT EXISTS aluno(
    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    FOREIGN KEY (id) REFERENCES usuario(id)
);

CREATE TABLE IF NOT EXISTS professor(
    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    FOREIGN KEY (id) REFERENCES usuario(id)
);

CREATE TABLE IF NOT EXISTS professor_curso(
    id_professor INTEGER NOT NULL,
    id_curso INTEGER NOT NULL,
    PRIMARY KEY (id_professor, id_curso),
    FOREIGN KEY (id_professor) REFERENCES professor(id),
    FOREIGN KEY (id_curso) REFERENCES curso(id)
);

CREATE TABLE IF NOT EXISTS aluno_curso(
    id_aluno INTEGER NOT NULL,
    id_curso INTEGER NOT NULL,
    PRIMARY KEY (id_aluno, id_curso)
    FOREIGN KEY (id_aluno) REFERENCES aluno(id),
    FOREIGN KEY (id_curso) REFERENCES curso(id)
);

CREATE TABLE IF NOT EXISTS aluno_curso_unidade(
    id_aluno_curso INTEGER NOT NULL,
    id_unidade INTEGER NOT NULL,
    nota FLOAT NOT NULL,
    PRIMARY KEY (id_aluno_curso, id_unidade),
    FOREIGN KEY (id_aluno_curso) REFERENCES aluno_curso(id),
    FOREIGN KEY (id_unidade) REFERENCES unidade(id),
);

CREATE TABLE IF NOT EXISTS aluno_curso_unidade_tarefa(
    id_aluno_curso_unidade INTEGER NOT NULL,
    id_tarefa INTEGER NOT NULL,
    nota_aluno FLOAT NOT NULL DEFAULT 0,
    PRIMARY KEY (id_aluno_curso_unidade, id_tarefa),
    FOREIGN KEY (id_aluno_curso_unidade) REFERENCES aluno_curso_unidade(id),
    FOREIGN KEY (id_tarefa) REFERENCES tarefa(id)
);



