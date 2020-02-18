--drop table if exists oauth_client_details;
create table if not exists users (
	username varchar(50) NOT NULL,
	password varchar(150) NOT NULL,
	enabled boolean NOT NULL,	
	CONSTRAINT users_pk PRIMARY KEY (username)
);

create table if not exists authorities (
	username varchar(50) NOT NULL,
	authority varchar(50) NOT NULL,
	CONSTRAINT authorities_fk FOREIGN KEY (username) REFERENCES public.users(username)
);
CREATE INDEX if not exists authorities_username_idx ON authorities (username,authority);

INSERT INTO users (username,password,enabled)  
SELECT 'enduser','{bcrypt}$2y$12$sIxZxjgCIZAvW0xXlHrBmejrhsPa6u0mYDFTmUHgQsUxsFKscEXk.',true
WHERE NOT EXISTS (SELECT username FROM users WHERE username='enduser')
;

INSERT INTO public.authorities (username, authority) 
SELECT 'enduser','USER'
WHERE NOT EXISTS (SELECT username FROM authorities WHERE username='enduser' AND authority='USER')
;