FROM openkbs/jdk-mvn-py3
COPY . /usr/app
WORKDIR ./usr/app
USER root
RUN mvn clean install
ENV PORT 8080
EXPOSE 8080
ENTRYPOINT ["mvn","spring-boot:run"]
