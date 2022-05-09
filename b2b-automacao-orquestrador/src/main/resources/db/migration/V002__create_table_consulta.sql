CREATE TABLE `consulta` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `descricao` varchar(255) DEFAULT NULL,
  `sistema` varchar(5) DEFAULT NULL,
  `data_hora_inicio` datetime DEFAULT NULL,
  `data_hora_fim` datetime DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `mensagem_status` varchar(255) DEFAULT NULL,
  `dados_saida` varchar(255) DEFAULT NULL,
  `status_processamento` varchar(255) DEFAULT NULL,
  `usuario` varchar(255) DEFAULT NULL,
  `porta_automacao` varchar(255) DEFAULT NULL,
  `dados_checklist_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKppw0qprqwlhj22ojjl8jnxm6u` (`dados_checklist_id`),
  CONSTRAINT `FKppw0qprqwlhj22ojjl8jnxm6u` FOREIGN KEY (`dados_checklist_id`) REFERENCES `dados_checklist` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci