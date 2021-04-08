# https://hub.docker.com/_/maven/
FROM maven:3.8-jdk-11

WORKDIR /build

COPY . .

RUN mvn test
