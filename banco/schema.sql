CREATE DATABASE IF NOT EXISTS `estoque_veiculos`
    DEFAULT CHARACTER SET utf8mb4
    DEFAULT COLLATE utf8mb4_0900_ai_ci;

USE `estoque_veiculos`;

CREATE TABLE IF NOT EXISTS `marca` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `nome` VARCHAR(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `modelo` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `nome` VARCHAR(255) DEFAULT NULL,
    `marca_id` BIGINT NOT NULL,
    PRIMARY KEY (`id`),
    KEY `FKllxq2dldvhxvb5q9csar7vdfy` (`marca_id`),
    CONSTRAINT `FKllxq2dldvhxvb5q9csar7vdfy`
        FOREIGN KEY (`marca_id`) REFERENCES `marca` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `veiculo` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `ano_fabricacao` INT NOT NULL,
    `cor` VARCHAR(255) NOT NULL,
    `preco` DECIMAL(12,2) NOT NULL,
    `quilometragem` INT NOT NULL,
    `status` ENUM('DISPONIVEL', 'RESERVADO', 'VENDIDO') NOT NULL,
    `modelo_id` BIGINT NOT NULL,
    PRIMARY KEY (`id`),
    KEY `FKg66qhh2d26iq69v6fhhifnfu6` (`modelo_id`),
    CONSTRAINT `FKg66qhh2d26iq69v6fhhifnfu6`
        FOREIGN KEY (`modelo_id`) REFERENCES `modelo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;