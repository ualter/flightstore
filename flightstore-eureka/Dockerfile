# Docker file for the Read Service
#
# Version 1.0

#jdk image
FROM openjdk:8-jdk-alpine

# install

# label for the image
LABEL Description="Eureka Server" Version="1.0"

# the version of the archive
ARG VERSION=1.0

# mount the temp volume
VOLUME /tmp

# Add the service as app.jar
ADD target/flightstore-eureka-1.0.jar app.jar

# touch the archive for timestamp
RUN sh -c 'touch /app.jar'

# entrypoint to the image on run
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]