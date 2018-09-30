# spring-oauth2-Jdbc-Caching
Spring Oauth2 Authorization server implementation with beautiful UI to configure and enabled Redis cache to ease the performance

Please refer this for Resource server to test this authorization server. Basically Authorization server is responsible for issuing the token.

For Resource Server: https://github.com/jinagamvasubabu/oauth2-Resource-Server. 

Start Authorization server and fetch access token by calling http://localhost:8081/oauth/token by passing the required information depending up on the grant type.

Start Resource Server (sitting in another repo) and use the token, got from the previous step and call http://localhost:8083/product/1 to get the data back.

# Requirements:
* Maven 
* MySQL
* Redis
* Jdk 1.8


# References:
https://github.com/Akourtiim/oauth2-spring-boot-2.0.2



