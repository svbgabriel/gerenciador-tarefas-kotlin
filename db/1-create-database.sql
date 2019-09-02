CREATE DATABASE IF NOT EXISTS gerenciador_tarefas;

USE gerenciador_tarefas;

CREATE TABLE tar_tarefas
(
	tar_id INT PRIMARY KEY AUTO_INCREMENT,
    tar_titulo VARCHAR(50) NOT NULL,
    tar_descricao VARCHAR(100) DEFAULT NULL,
    tar_data_expiracao DATE NOT NULL,
    tar_concluida BIT DEFAULT FALSE
);

CREATE TABLE usr_usuarios
(
	usr_id INT PRIMARY KEY AUTO_INCREMENT,
    usr_email VARCHAR(100) NOT NULL,
    usr_senha VARCHAR(100) NOT NULL
);

ALTER TABLE tar_tarefas ADD COLUMN usr_id INT NOT NULL;

ALTER TABLE tar_tarefas ADD CONSTRAINT fk_tar_tarefas_usr_usuarios FOREIGN KEY (usr_id) REFERENCES usr_usuarios(usr_id);
