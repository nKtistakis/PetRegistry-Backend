
# A Dockerized REST-API Server implemented with Spring together with a MySQL Database

## Installation Guide

# A REST-API Server implemented with Spring

## Installation Guide

### Step 1
#### Create a MySQL Database with *Docker*

- #### Run the following command in the command line, for the MySQL database docker container to be created:

   - `docker run --name mysqldb -v mysqldbvol:/var/lib/mysql -p 3306:3306 -e MYSQL_USER=user0 -e MYSQL_PASSWORD=changeit -e MYSQL_DATABASE=dsDb0 -e MYSQL_ROOT_PASSWORD=pass123 -d mysql/mysql-server:latest`

### Step 2
#### 1. Make sure that you have *Java Version 17* and *Maven* installed in your machine and in your path
#### 2. Open a command line inside the project directory and run the following commands:

 - `mvn package` (This will create a .war file inside the target folder with the name (rest-api.war)
 - `java -jar target/rest-api.war`


## Congratulations!! The server is up and running at: -> _http://localhost:8080_
