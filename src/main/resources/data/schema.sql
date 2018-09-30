
create database if not exists oauth2;

drop table if exists oauth2.oauth_client_details;
drop table if exists oauth2.oauth_access_token;
drop table if exists oauth2.oauth_refresh_token;
drop table if exists oauth2.authority;
drop table if exists oauth2.credentials;
drop table if exists oauth2.credentials_authorities;
drop table if exists oauth2.oauth_approvals;


CREATE TABLE oauth2.oauth_client_details (
  client_id varchar(255) NOT NULL,
  resource_ids varchar(255) DEFAULT NULL,
  client_secret varchar(255) DEFAULT NULL,
  scope varchar(255) DEFAULT NULL,
  authorized_grant_types varchar(255) DEFAULT NULL,
  web_server_redirect_uri varchar(255) DEFAULT NULL,
  authorities varchar(255) DEFAULT NULL,
  access_token_validity integer(11) DEFAULT NULL,
  refresh_token_validity integer(11) DEFAULT NULL,
  additional_information varchar(255) DEFAULT NULL,
  autoapprove varchar(255) DEFAULT NULL
);

create table oauth2.oauth_access_token (
  token_id VARCHAR(255),
  token LONGBLOB,
  authentication_id VARCHAR(255),
  user_name VARCHAR(255),
  client_id VARCHAR(255),
  authentication LONGBLOB,
  refresh_token VARCHAR(255)
);

create table oauth2.oauth_refresh_token(
  token_id VARCHAR(255),
  token LONGBLOB,
  authentication LONGBLOB
);

CREATE TABLE oauth2.authority (
  id  integer,
  authority varchar(255),
  primary key (id)
);


CREATE TABLE oauth2.credentials (
  id  integer,
  enabled boolean not null,
  name varchar(255) not null,
  password varchar(255) not null,
  version integer,
  primary key (id)
);

CREATE TABLE oauth2.credentials_authorities (
  credentials_id bigint not null,
  authorities_id bigint not null
);

create table oauth2.oauth_approvals (
    userId VARCHAR(255),
    clientId VARCHAR(255),
    scope VARCHAR(255),
    status VARCHAR(10),
    expiresAt DATETIME,
    lastModifiedAt DATETIME
);


