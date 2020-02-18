--drop table if exists oauth_client_details;
create table if not exists oauth_client_details (
  client_id VARCHAR(255) PRIMARY KEY,
  resource_ids VARCHAR(255),
  client_secret VARCHAR(255),
  scope VARCHAR(255),
  authorized_grant_types VARCHAR(255),
  web_server_redirect_uri VARCHAR(255),
  authorities VARCHAR(255),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additional_information VARCHAR(4096),
  autoapprove VARCHAR(255)
);
 
--drop table if exists oauth_client_token;
create table if not exists oauth_client_token (
  token_id VARCHAR(255),
  token BYTEA,
  authentication_id VARCHAR(255) PRIMARY KEY,
  user_name VARCHAR(255),
  client_id VARCHAR(255)
);
 
--drop table if exists oauth_access_token;
create table if not exists oauth_access_token (
  token_id VARCHAR(255),
  token BYTEA,
  authentication_id VARCHAR(255) PRIMARY KEY,
  user_name VARCHAR(255),
  client_id VARCHAR(255),
  authentication BYTEA,
  refresh_token VARCHAR(255)
);
 
--drop table if exists oauth_refresh_token;
create table if not exists oauth_refresh_token (
  token_id VARCHAR(255),
  token BYTEA,
  authentication BYTEA
);
 
--drop table if exists oauth_code;
create table if not exists oauth_code (
  code VARCHAR(255), authentication BYTEA
);
 
--drop table if exists oauth_approvals;
create table if not exists oauth_approvals (
    userId VARCHAR(255),
    clientId VARCHAR(255),
    scope VARCHAR(255),
    status VARCHAR(10),
    expiresAt TIMESTAMP,
    lastModifiedAt TIMESTAMP
);
 
--drop table if exists ClientDetails;
create table if not exists ClientDetails (
  appId VARCHAR(255) PRIMARY KEY,
  resourceIds VARCHAR(255),
  appSecret VARCHAR(255),
  scope VARCHAR(255),
  grantTypes VARCHAR(255),
  redirectUrl VARCHAR(255),
  authorities VARCHAR(255),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additionalInformation VARCHAR(4096),
  autoApproveScopes VARCHAR(255)
);


INSERT INTO oauth_client_details
(client_id, resource_ids, client_secret, scope, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove)
SELECT 'db_client_client-credintials', '', '{bcrypt}$2y$12$sIxZxjgCIZAvW0xXlHrBmejrhsPa6u0mYDFTmUHgQsUxsFKscEXk.', 'any', 'client_credentials',  '', '', 10000, 10000, '{}', true
WHERE NOT EXISTS(SELECT client_id FROM oauth_client_details WHERE client_id = 'db_client_client-credintials');


INSERT INTO oauth_client_details
(client_id, resource_ids, client_secret, scope, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove)
SELECT 'db-client-password-credentials', '', '{bcrypt}$2y$12$sIxZxjgCIZAvW0xXlHrBmejrhsPa6u0mYDFTmUHgQsUxsFKscEXk.', 'any', 'password', '', '', 10000, 10000, '{}', true
WHERE NOT EXISTS (SELECT client_id FROM oauth_client_details WHERE client_id = 'db-client-password-credentials');

INSERT INTO oauth_client_details
(client_id, resource_ids, client_secret, scope, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove)
SELECT 'db_client_auth_code', 'test', '{bcrypt}$2y$12$sIxZxjgCIZAvW0xXlHrBmejrhsPa6u0mYDFTmUHgQsUxsFKscEXk.', 'any', 'authorization_code', '/testapp', '', 10000, 10000, '{}', false
WHERE NOT EXISTS (SELECT client_id FROM oauth_client_details WHERE client_id = 'db_client_auth_code');
