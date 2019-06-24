CREATE TABLE manufacturer (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  name varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE airplane (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  model varchar(255) DEFAULT NULL,
  range_km int(11) DEFAULT NULL,
  seats int(11) DEFAULT NULL,
  manufacturer_id bigint(20) DEFAULT NULL,
  PRIMARY KEY (id),
  INDEX(manufacturer_id),
  FOREIGN KEY (manufacturer_id) 
          REFERENCES manufacturer(id)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


INSERT INTO `manufacturer` (`id`,`name`) VALUES (1,'Boeing');
INSERT INTO `manufacturer` (`id`,`name`) VALUES (2,'Airbus');
INSERT INTO `manufacturer` (`id`,`name`) VALUES (3,'Bombardier');
INSERT INTO `manufacturer` (`id`,`name`) VALUES (4,'Embraer');
INSERT INTO `manufacturer` (`id`,`name`) VALUES (5,'Cessna'); 