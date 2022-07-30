FROM maven:3.8.3-jdk-11-slim AS build

WORKDIR /project

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src/ /project/src

RUN mvn package

FROM adoptopenjdk/openjdk11:jre-11.0.15_10-alpine

RUN mkdir /app

RUN addgroup -g 1001 -S kylcgroup

RUN adduser -S kylc -u 1001

COPY --from=build /project/target/spring-boot-jpa-postgresql.jar /app/spring-boot-jpa-postgresql.jar

WORKDIR /app

RUN chown -R kylc:kylcgroup /app

EXPOSE 8080

CMD java $JAVA_OPTS -jar spring-boot-jpa-postgresql.jar
