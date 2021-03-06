CREATE TABLE airliner (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  name varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE airliner_airplanes (
  airliner_id bigint(20) NOT NULL,
  airplane_id bigint(20) NOT NULL,
  PRIMARY KEY (airliner_id, airplane_id),
  INDEX(airliner_id, airplane_id),
  FOREIGN KEY (airliner_id) 
          REFERENCES airliner(id)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE USER IF NOT EXISTS 'flightuser'@'%' IDENTIFIED BY '${userpass}';
GRANT INSERT, SELECT, DELETE, UPDATE ON * . * TO 'flightuser'@'%';
FLUSH PRIVILEGES;

-- INSERT INTO `manufacturer` (`id`,`name`) VALUES (1,'Boeing');
