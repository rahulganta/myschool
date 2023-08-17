#FROM openjdk:11-slim #this takes 384MB
FROM adoptopenjdk/openjdk11:alpine-jre

MAINTAINER Rahul Ganta

EXPOSE 9191

WORKDIR /

COPY ["docker/entrypoint.sh", "/"]

ENTRYPOINT ["/entrypoint.sh"]

#ARG JAR_FILE
#ENV JAVA_OPTS="-XX:PermSize=1024m -XX:MaxPermSize=512m -XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap"
#ADD /maven/${JAR_FILE} /myschool/myschool-web-service.jar
ADD target/adminservice-0.0.1-SNAPSHOT-exec.jar /myschool/myschool-web-service.jar
RUN chmod a+x /entrypoint.sh