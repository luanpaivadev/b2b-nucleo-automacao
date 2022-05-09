CREATE TABLE usuario (
    id BIGINT NOT NULL AUTO_INCREMENT,
    usuario VARCHAR(20) NOT NULL,
    sistema VARCHAR(5) NOT NULL,
    uf VARCHAR(2) NOT NULL,
    em_uso BIT(1),
    disponivel BIT(1),
	ultimo_uso DATETIME,
    PRIMARY KEY (id)
);