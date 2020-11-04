FROM maven:3.6-jdk-8

WORKDIR /build

COPY . .

RUN mvn test
