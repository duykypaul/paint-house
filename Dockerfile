FROM openjdk:1.8
ADD target/spring-boot-jpa-postgresql.jar spring-boot-jpa-postgresql.jar
ENTRYPOINT ["java", "-jar","spring-boot-jpa-postgresql.jar"]
EXPOSE 8080