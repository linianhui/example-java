# https://github.com/linianhui/docker/pkgs/container/maven

FROM ghcr.io/linianhui/maven:3.8-openjdk-8 AS builder

WORKDIR /build

COPY . .

RUN mvn package --projects networking


FROM ghcr.io/linianhui/openjdk:8u322

WORKDIR /app

COPY --from=builder /build/networking/target/networking-1.0-jar-with-dependencies.jar app.jar

ENV JAVA_OPTIONS='-XX:+UseG1GC -XX:+UseContainerSupport -XX:MaxRAMPercentage=75.00'

ENTRYPOINT ["/bin/bash", "-c", "java $JAVA_OPTIONS $JAVA_DEBUG_OPTIONS -jar app.jar"]

EXPOSE 12345 5005
