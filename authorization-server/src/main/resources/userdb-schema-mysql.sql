--drop table if exists oauth_client_details;
create table if not exists users (
	username varchar(50) NOT NULL,
	password varchar(150) NOT NULL,
	enabled boolean NOT NULL,	
	CONSTRAINT users_pk PRIMARY KEY (username)
);

CREATE TABLE IF NOT EXISTS `authorities` (
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL,
  KEY `authorities_fk` (`username`),
  CONSTRAINT `authorities_fk` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


INSERT INTO users (username, password ,enabled)  
SELECT 'enduser','{bcrypt}$2y$12$sIxZxjgCIZAvW0xXlHrBmejrhsPa6u0mYDFTmUHgQsUxsFKscEXk.',true
WHERE NOT EXISTS (SELECT username FROM users WHERE username='enduser')
;

INSERT INTO authorities (username, authority) 
SELECT 'enduser','USER'
WHERE NOT EXISTS (SELECT username FROM authorities WHERE username='enduser' AND authority='USER')
;