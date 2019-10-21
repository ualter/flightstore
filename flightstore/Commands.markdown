FlightStore
---

Maven
```bash

# Alternative to Set Jasypt Symmetric password Decryption
export JASYPT_ENCRYPTOR_PASSWORD=***

### Send JASYPT arguments if not working the default mode  (Workaround)
$ mvn spring-boot:run -Dspring-boot.run.arguments=jasypt.encryptor.password=***
$ mvn spring-boot:run -Dspring-boot.run.arguments=spring.profiles.active=windows,jasypt.encryptor.password=***

# Start SpringBoot
## Default Profile (Mac)
$ mvn spring-boot:run -Djasypt.encryptor.password=**** -V
## Windows Profile
$ mvn spring-boot:run -Dspring.profiles.active=windows -Djasypt.encryptor.password=**** -V

# Debug SpringBoot
$ mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=5005 -Dspring.profiles.active=mac -Djasypt.encryptor.password=***"

# Package running Test WITH Integration Tests
$ mvn clean package -Dspring.profiles.active=mac,test  -Dintegration-tests=true -Djasypt.encryptor.password=***

# Package running Test WITHOUT Integration Tests
$ mvn clean package -Dspring.profiles.active=mac,test -Djasypt.encryptor.password=***

#Dockerfile must be at the same folder
$ mvn install -Dspring.profiles.active=mac,test  -Djasypt.encryptor.password=***  dockerfile:build
$ mvn install -Dmaven.test.skip=true dockerfile:build

#Others
$ mvn clean package -Dmaven.test.skip=true
```
---

Java
```bash
# Start with Windows Profile (application-windows.properties)
$ java -jar  -Dspring.profiles.active=windows -Djasypt.encryptor.password=**** target/flightstore-airplane-1.0.jar

# Start with Environment Variables
$ java -jar  -DMYSQL_IP=localhost -DMYSQL_PORT=4406 -Djasypt.encryptor.password=**** -Dspring.flyway.placeholders.userpass=**** -Dspring.flyway.user=root -Dspring.flyway.password=**** target/flightstore-airplane-1.0.jar
# With No Flyway Variables (Content at YAML encrypted)
$ java -jar  -DMYSQL_IP=localhost -DMYSQL_PORT=4406 -Djasypt.encryptor.password=**** target/flightstore-airplane-1.0.jar

```
---

Docker
```bash
# Create Docker Image with DockerFile SpringBoot
# Dockerfile must be at the same folder
$ mvn install -Dmaven.test.skip=true dockerfile:build  

# Launch Docker Container
$ docker run -e MYSQL_IP=10.253.163.97 -e MYSQL_PORT=4406 -e jasypt.encryptor.password=**** -e spring.flyway.placeholders.userpass=**** -e spring.flyway.user=root -e spring.flyway.password=**** -p 9181:9180 ualter/flightstore-airplane
# or
$ docker run -e MYSQL_IP=10.253.163.97 -e MYSQL_PORT=4406 -e jasypt.encryptor.password=**** -p 9181:9180 ualter/flightstore-airplane -d

# Debug Docker Container
$ docker run -e MYSQL_IP=192.168.99.101 -e MYSQL_PORT=4406 -e jasypt.encryptor.password=**** -e "JAVA_OPTS=-agentlib:jdwp=transport=dt_socket,address=5005,server=y,suspend=n" -p 9181:9180 ualter/flightstore-airplane

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

REST Commands
```bash
$ curl -vsw "\n\n" GET   http://localhost:9180/flightstore-airplane/api/v1/airplanes
$ curl -vsw "\n\n" GET   http://localhost:9180/flightstore-airplane/api/v1/airplanes/1
$ curl -s GET http://localhost:9180/flightstore-airplane/api/v1/airplanes | jq .

$ curl -s GET   http://localhost:9180/flightstore-airplane/api/v1/manufacturers | jq .
$ curl -s GET http://localhost:9180/flightstore-airplane/api/v1/manufacturers/1 | jq .
$ curl -vsw "\n\n" GET http://localhost:9180/flightstore-airplane/api/v1/manufacturers/2345
```
---

##URLs
- Micros
 - http://localhost:9180/flightstore-airplane/api/v1/airplanes
 - http://localhost:9180/flightstore-airplane/api/v1/manufacturers
- API Docs (Swagger Format)
 - http://localhost:9180/flightstore-airplane/swagger-ui.html#/

Jasypt
```bash
# Encrypt Command Line
# Before Download Dependency
mvn dependency:get  -Dartifact=org.jasypt:jasypt:1.9.3

# Then...
java -cp ~/.m2/repository/org/jasypt/jasypt/1.9.3/jasypt-1.9.3.jar org.jasypt.intf.cli.JasyptPBEStringEncryptionCLI input="Topsecret@123" password=dev-env-secret algorithm=PBEWITHMD5ANDDES
```
---

Flyway
```bash
# Reparing Broken Versions
$ mvn flyway:repair -Dflyway.user=*** -Dflyway.password=*** -Dflyway.url=jdbc:mysql://localhost:4406/fs-airplane
```
---

Redis - Cache
```bash
$ docker exec -it redis-flightstore redis-cli
127.0.0.1:6379> keys *
127.0.0.1:6379> get "manufacturers::1"
```
---

QuickStart (Boot up)
```bash
#1 Start MySQL Docker
$ /flightstore/dockers/databases/./start.sh

#2 Start Redis Docker
$ /flightstore/dockers/cache/./start.sh

#3 Clean & Package Microservice
$ /flightstore/flightstore-airplane/mvn clean package -Dspring.profiles.active=mac,test  -Dintegration-tests=true -Djasypt.encryptor.password=***

#4 Build the Docker Image
$ mvn install -Dmaven.test.skip=true dockerfile:build   

#5 Start Container (Run Docker)
   # 5.1 Command 
   $ docker run -d --name flightstore-airplane -e MYSQL_IP=10.253.163.97 -e MYSQL_PORT=4406 -e jasypt.encryptor.password=*** -p 9181:9180 ualter/flightstore-airplane
     ## Check logs
     $ docker logs flightstore-airplane -f
   # 5.2 (Or use...) Docker Compose (need the .env file with MYSQL_IP, MYSQL_PORT and Jasypt Encryption Master Key)  
   $ docker-compose up -d
     ## Check logs
     $ docker-compose logs -f
     
```

Minishift / Openshift / OKD

Opcion Deploy the Local Docker Image into the OpenShift Registry:
https://docs.okd.io/latest/minishift/openshift/openshift-docker-registry.html

HERE: https://docs.okd.io/latest/dev_guide/application_lifecycle/new_app.html

```bash
# Before perform the Config Local Registry
https://github.com/ualter/flightstore/blob/master/flightstore/dockers/registry/commands-registry.md
# Enter Minishift VM
1. $ minishift ssh
# Config the Minishift DNS to reach the Host Registry
2. $ sudo vi /etc/hosts
3. $ <YOU-IP or docker-machine IP>	my-registry
4. $ ping my-registry (must work)
# Share the Windows Host Folder with Minishift VM
5. minishift hostfolder add --interactive
   ORminishi
5. minishift hostfolder add -t sshfs --source c:\Users --target /mnt/sda1/myshare myshare
5. minishift hostfolder mount myshare
5. minishift hostfolder list
5. minishift ssh "ls -al /mnt/sda1/myshare"
5. minishift ssh
5. sudo cp /mnt/sda1/myshare/docker-registry-certs/domain.crt  my-registry\:5000/ca.crt


# 6. Login as Developer
$ oc login

# 7. Create the Secret to Login to the My-Registry (My Docker Local Registry)
$ oc create secret docker-registry my-registry-secret --docker-server=my-registry:5000 --docker-username=ualter --docker-password=1234 --docker-email=ualter.junior@gmail.com

# 7. Create the Application
$ oc new-app my-registry:5000/ualter-flightstore-airplane 

```