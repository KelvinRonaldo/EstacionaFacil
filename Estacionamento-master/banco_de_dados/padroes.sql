DESC tbl_movimentacao;
DESC tbl_preco;
DESC tbl_fabricante;
DESC tbl_veiculo;
DESC tbl_mensalista;
DESC tbl_mensalista_endereco;
DESC tbl_endereco;
DESC tbl_mensalista_telefone;

SELECT * FROM tbl_movimentacao;
SELECT * FROM tbl_preco;
SELECT * FROM tbl_fabricante;
SELECT * FROM tbl_mensalista;
SELECT * FROM tbl_mensalista_endereco;
SELECT * FROM tbl_endereco;
SELECT * FROM tbl_telefone;
SELECT * FROM tbl_mensalista_telefone;
SELECT * FROM tbl_veiculo;
SELECT * FROM tbl_cidade;
SELECT * FROM tbl_estado;

ALTER TABLE tbl_preco CHANGE COLUMN preco_mensal valor_mensal DECIMAL(5, 2) NOT NULL AFTER valor_demais_horas;

CREATE TABLE tbl_movimentacao(
cod_movimento INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
placa VARCHAR(8) NOT null,
modelo_carro VARCHAR(15) NOT NULL,
data_hora_entrada DATETIME NOT NULL,
data_hora_saida DATETIME,
tipo VARCHAR(10),
tempo_permanencia INT NOT NULL DEFAULT 0,
valor_pago DECIMAL(5, 2) NOT NULL DEFAULT 0
);

CREATE TABLE tbl_preco(
cod_preco INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
valor_primeira_hora DECIMAL(5, 2) NOT NULL,
valor_demais_horas DECIMAL(5, 2) NOT NULL,
tempo_tolerancia INT NOT NULL,
data_fim DATETIME
);

CREATE TABLE tbl_veiculo (
cod_veiculo INT NOT NULL auto_increment primary KEY,
placa varchar(7) NOT NULL, 
modelo_carro VARCHAR(30) NOT NULL,
cod_fabricante INT NOT NULL,
CONSTRAINT fk_veiculo_fabricante FOREIGN KEY (cod_fabricante)
REFERENCES tbl_fabricante (cod_fabricante)
);

CREATE TABLE tbl_fabricante(
	cod_fabricante INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR (30) NOT NULL
);

CREATE TABLE tbl_mensalista(
cod_mensalista INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
nome VARCHAR(100) NOT NULL,
email VARCHAR(150) NOT NULL,
cpf VARCHAR(15) NOT NULL,
cod_telefone INT NOT NULL, 
cod_endereco INT NOT NULL
);

CREATE TABLE tbl_mensalista_telefone(
cod_mensalista INT NOT NULL,
cod_telefone INT NOT NULL,
CONSTRAINT fk_mensalista_telefone
FOREIGN KEY (cod_telefone)
REFERENCES tbl_telefone(cod_telefone),
CONSTRAINT fk_telefone_mensalista
FOREIGN KEY (cod_mensalista)
REFERENCES tbl_mensalista(cod_mensalista)
);

CREATE TABLE tbl_telefone(
cod_telefone INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
telefone VARCHAR(20)
);

CREATE TABLE tbl_mensalista_endereco(
cod_mensalista INT NOT NULL,
cod_endereco INT NOT NULL,
CONSTRAINT fk_mensalista_endereco
FOREIGN KEY (cod_endereco)
REFERENCES tbl_endereco(cod_endereco),
CONSTRAINT fk_endereco_mensalista
FOREIGN KEY (cod_mensalista)
REFERENCES tbl_mensalista(cod_mensalista)
);

CREATE TABLE tbl_endereco(
cod_endereco INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
logradouro VARCHAR(55) NOT NULL,
numero VARCHAR(10) NOT NULL,
bairro VARCHAR(60) NOT NULL,
cod_cidade INT NOT NULL,
CONSTRAINT fk_endereco_cidade
FOREIGN KEY (cod_cidade)
REFERENCES tbl_cidade(cod_cidade)
);

CREATE TABLE tbl_cidade(
cod_cidade INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
cidade VARCHAR(100) NOT NULL,
cod_estado INT NOT NULL,
CONSTRAINT fk_cidade_estado
FOREIGN KEY (cod_estado)
REFERENCES tbl_estado(cod_estado)
);
CREATE TABLE tbl_estado(
cod_estado INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
estado VARCHAR(100) NOT NULL
);
DELETE FROM tbl_movimentacao WHERE cod_movimento = 1;





INSERT INTO tbl_preco (valor_primeira_hora, valor_demais_horas, preco_mensal, tempo_tolerancia) VALUES (5, 3, 150, 5);

INSERT INTO tbl_movimentacao (placa, modelo_carro, data_hora_entrada, tipo)
VALUES ('JGN-7852', 'Crossfox', '2019-05-30 15:49:26', 'Avulso');

-- endereco
INSERT INTO tbl_estado value (1, 'SÃO PAULO');

INSERT INTO tbl_cidade values (1, 'JANDIRA', 1);

INSERT INTO tbl_endereco values (1, 'Rua Antonio gomes dos santos', '03', 'Parque dos logos', '06542589','Jandira', 'São Paulo');

-- tirei os codigos de telefone e endereço pois uma tabela ja está fazendo isso
INSERT INTO tbl_mensalista values (1, 'David Silva Souza', 'david@terra.com', '435-423-668/03');

-- tabela relacionamento
INSERT INTO tbl_mensalista_endereco values (1, 1, 1);

INSERT INTO tbl_fabricante values (1, 'HYUNDAI');
INSERT INTO tbl_fabricante values (2, 'MAZDA');
INSERT INTO tbl_fabricante values (3, 'MUSTANG');

INSERT INTO tbl_veiculo values (1, 'ABC1234', 'FUSCA', 1, 1);
INSERT INTO tbl_veiculo values (2, 'AAD1245', 'GOL', 2);



ALTER TABLE tbl_mensalista_telefone ADD COLUMN cod_mensalista_telefone INT NOT NULL PRIMARY KEY AUTO_INCREMENT FIRST;
ALTER TABLE tbl_mensalista_endereco ADD COLUMN cod_mensalista_endereco INT NOT NULL PRIMARY KEY AUTO_INCREMENT FIRST;
