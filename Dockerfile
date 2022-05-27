FROM openkbs/jdk-mvn-py3
COPY . /usr/app/
WORKDIR /usr/app
RUN mvn clean install
ENV PORT 8080
ENTRYPOINT ["mvn","spring-boot:run"]
EXPOSE 8080
