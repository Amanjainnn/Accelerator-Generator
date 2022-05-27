FROM openkbs/jdk-mvn-py3
USER root
RUN npm i -g n
RUN n stable
RUN npm i -g @angular/cli


COPY . /usr/app


WORKDIR /usr/app

RUN mvn clean install

ENV PORT 8080

EXPOSE 8080



ENTRYPOINT ["mvn", "spring-boot:run"]

