FROM openjdk:8-jdk-alpine AS BUILD_STAGE
WORKDIR /src/app
COPY build.gradle settings.gradle gradlew ./
COPY gradle /src/app/gradle
RUN ./gradlew build || return 0
COPY . .
RUN ./gradlew clean test
RUN ./gradlew jacocoTestReport
RUN ./gradlew build

FROM openjdk:8-jre-alpine
WORKDIR /root
COPY --from=BUILD_STAGE /src/app/build/libs/demo-spring-boot-gradle-0.0.1-SNAPSHOT.jar /root/app.jar
EXPOSE 8080
CMD [ "java", "-jar", "/root/app.jar" ]