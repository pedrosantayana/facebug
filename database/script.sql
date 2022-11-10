drop SCHEMA if EXISTS facebug;


CREATE SCHEMA IF NOT EXISTS facebug;

-- -----------------------------------------------------
-- Table Usuario
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Usuario (
  Username VARCHAR(20) NOT NULL UNIQUE,
  Nome VARCHAR(45) NOT NULL,
  Senha BYTEA NOT NULL,
  Email VARCHAR(100) NOT NULL,
  PRIMARY KEY (Username)
);



-- -----------------------------------------------------
-- Table Classificacao
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Classificacao (
  UUID CHAR(36) UNIQUE NOT NULL PRIMARY KEY,
  Nome VARCHAR(45) NOT NULL
);



-- -----------------------------------------------------
-- Table Postagem
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Postagem (
  UUID CHAR(36) NOT NULL UNIQUE,
  Usuario VARCHAR(20) NOT NULL,
  Classificacao CHAR(36) NOT NULL,
  Titulo VARCHAR(45) NOT NULL,
  Data timestamp NOT NULL,
  Conteudo VARCHAR(300) NOT NULL,
  Midia VARCHAR(45) NULL,
  PRIMARY KEY (UUID, Usuario, Classificacao),
  CONSTRAINT fk_Postagem_Usuario
    FOREIGN KEY (Usuario)
    REFERENCES Usuario (Username)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_Postagem_Classificacao1
    FOREIGN KEY (Classificacao)
    REFERENCES Classificacao (UUID)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);



-- -----------------------------------------------------
-- Table Curtida
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Curtida (
  Postagem CHAR(36) NOT NULL,
  Usuario VARCHAR(20) NOT NULL,
  CONSTRAINT fk_Curtida_Postagem1
    FOREIGN KEY (Postagem)
    REFERENCES Postagem (UUID)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_Curtida_Usuario1
    FOREIGN KEY (Usuario)
    REFERENCES Usuario (Username)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);



-- -----------------------------------------------------
-- Table Seguidores
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Seguidores (
  Usuario VARCHAR(20) NOT NULL,
  Seguidor VARCHAR(20) NOT NULL,
  CONSTRAINT fk_Seguidores_Usuario1
    FOREIGN KEY (Usuario)
    REFERENCES Usuario (Username)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_Seguidores_Usuario2
    FOREIGN KEY (Seguidor)
    REFERENCES Usuario (Username)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)