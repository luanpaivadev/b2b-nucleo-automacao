CREATE TABLE `dados_checklist` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `uf` varchar(2) DEFAULT NULL,
  `ddd` varchar(3) DEFAULT NULL,
  `terminal` varchar(255) DEFAULT NULL,
  `circuito` varchar(255) DEFAULT NULL,
  `localidade` varchar(255) DEFAULT NULL,
  `cliente` varchar(255) DEFAULT NULL,
  `url_callback` varchar(255) NOT NULL,
  `status_callback` BIT(1),
  `request_id` VARCHAR(255) DEFAULT NULL,
  `lote_id` VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci