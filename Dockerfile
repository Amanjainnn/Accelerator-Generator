FROM openkbs/jdk-mvn-py3

COPY . /usr/app

USER root

WORKDIR /usr/app

RUN mvn install

ENV PORT 8080

EXPOSE 8080

RUN npm i -g @angular/cli

RUN npm i -g n
RUN n stable

ENTRYPOINT ["spring-boot:run"]

