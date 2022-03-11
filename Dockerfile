FROM openjdk:8
RUN apt-get update && apt-get --no-install-recommends install maven -y && apt-get clean -y && apt-get autoremove -y
ADD . /Mealwrap_Backend
WORKDIR /Mealwrap_Backend
ENTRYPOINT ["mvn", "spring-boot:run", "-Dspring-boot.run.profiles=test"]
#ENTRYPOINT ["/bin/bash", "-c" , "mvn test && mvn spring-boot:run -Dspring-boot.run.profiles=test"]