FROM openjdk:17-alpine
MAINTAINER Elleined

# Docker MySQL Credentials
ENV MYSQL_HOST=mysql_server
ENV MYSQL_USER=root
ENV MYSQL_PASSWORD=root
ENV MYSQL_PORT=3306
ENV MYSQL_DATABASE=pos_db
ENV PORT=8065

ADD ./target/*.jar pos-api.jar
EXPOSE 8065
CMD ["java", "-jar", "pos-api.jar"]