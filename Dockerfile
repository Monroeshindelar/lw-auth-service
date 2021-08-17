FROM openjdk:11
LABEL authors="Monroe Shindelar (Monroeshindelar@gmail.com), Tanner Dryden (tdd7197@gmail.com)"
VOLUME /main-app
ADD build/libs/*.jar service.jar
EXPOSE 6091
ENTRYPOINT ["java", "-jar", "/service.jar"]

