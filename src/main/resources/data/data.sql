INSERT INTO oauth2.authority  VALUES(1,'ROLE_OAUTH_ADMIN');
INSERT INTO oauth2.authority VALUES(2,'ROLE_RESOURCE_ADMIN');
INSERT INTO oauth2.authority VALUES(3,'ROLE_PRODUCT_ADMIN');
INSERT INTO oauth2.credentials VALUES(1,b'1','oauth_admin','$2a$10$BurTWIy5NTF9GJJH4magz.9Bd4bBurWYG8tmXxeQh1vs7r/wnCFG2','0');
INSERT INTO oauth2.credentials_authorities VALUE (1,1);
INSERT INTO oauth2.credentials_authorities VALUE (2,2);
INSERT INTO oauth2.credentials_authorities VALUE (3,3);


INSERT INTO oauth2.oauth_client_details VALUES('client1','product_api', '$2a$10$BurTWIy5NTF9GJJH4magz.9Bd4bBurWYG8tmXxeQh1vs7r/wnCFG2', 'read,write', 'client_credentials,refresh_token,password', 'http://127.0.0.1', 'ROLE_PRODUCT_ADMIN', 10, 20, NULL, 'true');
