FROM openkbs/jdk-mvn-py3

COPY . /usr/app

USER root

WORKDIR /usr/app

RUN mvn clean install

ENV PORT 8080

EXPOSE 8080

ENTRYPOINT ["mvn","spring-boot:run"]

