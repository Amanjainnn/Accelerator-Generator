FROM openkbs/jdk-mvn-py3
USER root


COPY . /usr/app

WORKDIR /usr/app

RUN mvn clean install

RUN npm i -g n
RUN n stable
RUN npm i -g @angular/cli


ENV PORT 8080

EXPOSE 8080


