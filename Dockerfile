FROM openjdk:8
ADD . /Mealwrap_Backend
WORKDIR /Mealwrap_Backend
ENTRYPOINT ["/bin/sh", "-c", "./mvnw test && ./mvnw spring-boot:run -Dspring-boot.run.profiles=prod"]