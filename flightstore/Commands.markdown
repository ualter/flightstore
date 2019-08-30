#Annotations
---
##Maven
```bash
# Start SpringBoot
/flightstore-aiplane $ mvn spring-boot:run -Dspring.profiles.active=windows -Djasypt.encryptor.password=**** -Dspring.flyway.user=**** -Dspring.flyway.password=**** -Dspring.flyway.placeholders.userpass=**** -V

# Debug SpringBoot
/flightstore-aiplane $ mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,addre ss=5005 -Dspring.profiles.active=windows -Djasypt.encryptor.password=**** -Dspring.flyway.user=**** -Dspring.flyway.password=**** -Dspring.flyway.placeholders.userpass=****"

# Package running Test WITH Integration Tests
/flightstore-aiplane $ mvn clean package -Dspring.profiles.active=windows -Dintegration-tests=true -Djasypt.encryptor.password=**** 

# Package running Test WITHOUT Integration Tests
/flightstore-aiplane $ mvn clean package -Dspring.profiles.active=windows

#Dockerfile must be at the same folder
/flightstore-aiplane $ mvn install dockerfile:build 

#Others
/flightstore-parent $ mvn clean package -Dmaven.test.skip=true
```
---
##Java
```bash
# Start with Windows Profile (application-windows.properties)
/flightstore-aiplane $ java -jar  -Dspring.profiles.active=windows -Djasypt.encryptor.password=**** target/flightstore-airplane-1.0.jar

# Start with Environment Variables
/flightstore-aiplane $ java -jar  -DMYSQL_IP=localhost -DMYSQL_PORT=4406 -Djasypt.encryptor.password=**** -Dspring.flyway.placeholders.userpass=**** -Dspring.flyway.user=root -Dspring.flyway.password=**** target/flightstore-airplane-1.0.jar

```
---
##Docker
```bash
# Create Docker Image with DockerFile SpringBoot
#Dockerfile must be at the same folder
$ mvn install dockerfile:build  

# Launch Docker Container
$ docker run -e MYSQL_IP=10.253.163.97 -e MYSQL_PORT=4406 -e jasypt.encryptor.password=**** -e spring.flyway.placeholders.userpass=**** -e spring.flyway.user=root -e spring.flyway.password=**** -p 9181:9180 ualter/flightstore-airplane

docker run -e MYSQL_IP=localhost -e MYSQL_PORT=4406 -e jasypt.encryptor.password=**** -e spring.flyway.placeholders.userpass=**** -e spring.flyway.user=**** -e spring.flyway.password=**** -p 9181:9180 ualter/flightstore-airplane

# Debug Docker Container
$ docker run -e MYSQL_IP=192.168.99.101 -e MYSQL_PORT=4406 -e jasypt.encryptor.password=**** -e spring.flyway.placeholders.userpass=**** -e spring.flyway.user=**** -e spring.flyway.password=**** -e "JAVA_OPTS=-agentlib:jdwp=transport=dt_socket,address=5005,server=y,suspend=n" -p 9181:9180 ualter/flightstore-airplane

# Interacting Bash with Alpine Docker Image
$ docker exec -it 6a7826770c7e sh

# Update (Ubuntu) Docker Image
$ docker exec 6a7826770c7e apk update

# Install CURL on the Alpine (Ubuntu) Docker Image
$ docker exec 6a7826770c7e apk add curl

# Docker Compose
$ docker-compose up -d
$ docker-compose logs
$ docker-compose logs -f --tail=10
$ docker-compose exec app ps
$ docker-compose exec db ls
$ docker-compose up -d --scale app=3

# Volumes
$ docker volume ls
$ docker volume prune

# List all Docker Container IDs in Sequence (remove breaklines)
$ docker ps -a | awk 'NR==2,NR==3 {print $1}' | sed ':a;N;$!ba;s/\n/ /g'
# Stop all Docker Container IDs in Sequence (remove breaklines)
$ docker ps -a | awk 'NR==2,NR==3 {print $1}' | sed ':a;N;$!ba;s/\n/ /g' | xargs docker stop
# Remove all Docker Container IDs in Sequence (remove breaklines)
$ docker ps -a | awk 'NR==2,NR==3 {print $1}' | sed ':a;N;$!ba;s/\n/ /g' | xargs docker rm
```
---
##REST Commands
```bash
$ curl -s GET   http://localhost:9180/flightstore-airplane/api/v1/airplanes | jq .

$ curl -s GET   http://localhost:9180/flightstore-airplane/api/v1/manufacturers | jq .
```
---
##URLs
- Micros
 - http://localhost:9180/flightstore-airplane/api/v1/airplanes
 - http://localhost:9180/flightstore-airplane/api/v1/manufacturers
- API Docs (Swagger Format)
 - http://localhost:9180/flightstore-airplane/swagger-ui.html#/

