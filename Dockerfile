FROM openjdk:8-jdk-alpine
EXPOSE 8080
VOLUME /tmp
ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app
COPY ${DEPENDENCY}/BOOT-INF/classes/eq3.exp /
COPY ${DEPENDENCY}/BOOT-INF/classes/expect /usr/bin
ENTRYPOINT ["java","-cp","app:app/lib/*","de.larskolmetz.smarthomemanager.SmartHomeManagerApplicationKt"]