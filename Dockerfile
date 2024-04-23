FROM openjdk
EXPOSE 8080
ADD target/lie-detector-backend.jar lie-detector-backend.jar
ENTRYPOINT ["java-","-jar","lie-detector-backend.jar"]