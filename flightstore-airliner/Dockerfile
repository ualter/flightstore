FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app
<<<<<<< HEAD
ENTRYPOINT ["java","-cp","app:app/lib/*","ujr.flightstore.airliner.AirlinerApp"]
=======
ENTRYPOINT ["java","-cp","app:app/lib/*","ujr.flightstore.flightstore-airliner.flightstore-airlinerApp"]
>>>>>>> 4e4777108aae31d345285aadaf12406b75de469c
