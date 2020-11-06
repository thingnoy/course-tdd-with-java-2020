FROM openjdk:8-jdk-alpine AS BUILD_STAGE
WORKDIR /src/app
COPY mvnw mvnw.cmd pom.xml ./
COPY .mvn /src/app/.mvn
RUN ./mvnw package || return 0
COPY . .
RUN ./mvnw clean test
RUN ./mvnw jacoco:report
RUN ./mvnw package

FROM openjdk:8-jre-alpine
WORKDIR /root
COPY --from=BUILD_STAGE /src/app/target/demo-spring-boot-maven-0.0.1-SNAPSHOT.jar /root/app.jar
EXPOSE 8080
CMD [ "java", "-jar", "/root/app.jar" ]