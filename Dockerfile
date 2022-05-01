FROM openjdk:17-alpine

RUN mkdir /app

COPY ./build/libs/*.jar /app/flight-tracker-scraper.jar

ENTRYPOINT ["java","-jar","/app/flight-tracker-scraper.jar","--spring.config.location=classpath:/application.properties"]