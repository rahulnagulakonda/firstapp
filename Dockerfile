FROM openjdk:11
EXPOSE 8081
ADD target/FirstApp.jar firstapp.jar
ENTRYPOINT ["java","-jar","firstapp.jar"]