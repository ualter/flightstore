### Commands

##### Maven
```bash
# Start SpringBoot
/flightstore-aiplane $ mvn spring-boot:run -Dspring.profiles.active=windows -V

# Debug SpringBoot
/flightstore-aiplane $ mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,addre ss=5005 -Dspring.profiles.active=windows"

# Package running Test WITH Integration Tests
/flightstore-aiplane $ mvn clean package -Dspring.profiles.active=windows -Dintegration-tests=true

# Package running Test WITHOUT Integration Tests
/flightstore-aiplane $ mvn clean test -Dspring.profiles.active=windows

#Dockerfile must be at the same folder
/flightstore-aiplane $ mvn install dockerfile:build 

```

##### Java
```bash
# Start with Windows Profile (application-windows.properties)
/flightstore-aiplane $ java -jar  -Dspring.profiles.active=windows target/flightstore-airplane-1.0.jar

# Start with Environment Variables
/flightstore-aiplane $ java -jar -DMYSQL_IP=192.168.99.101 -DMYSQL_PORT=4406 target/flightstore-airplane-1.0.jar
```

##### Docker
```bash
# Create Docker Image with DockerFile SpringBoot
#Dockerfile must be at the same folder
$ mvn install dockerfile:build  

# Launch Docker Container
$ docker run -e MYSQL_IP=192.168.99.101 -e MYSQL_PORT=4406 -p 9181:9180 ualter/flightstore-airplane

# Debug Docker Container
$ docker run -e MYSQL_IP=192.168.99.101 -e MYSQL_PORT=4406 -e "JAVA_OPTS=-agentlib:jdwp=transport=dt_socket,address=5005,server=y,suspend=n" -p 9181:9180 ualter/flightstore-airplane

# Interacting Bash with Alpine Docker Image
$ docker exec -it 6a7826770c7e sh

# Update (Ubuntu) Docker Image
$ docker exec 6a7826770c7e apk update

# Install CURL on the Alpine (Ubuntu) Docker Image
$ docker exec 6a7826770c7e apk add curl

# More...
$ docker-compose up -d
$ docker-compose logs
$ docker-compose exec app ps
$ docker-compose exec db ls
$ docker-compose up -d --scale app=3
````


