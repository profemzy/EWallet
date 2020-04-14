FROM  openjdk:8-alpine
VOLUME /tmp
EXPOSE 9000
COPY target/EWallet-0.0.1-SNAPSHOT.jar app.jar
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JxAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]
