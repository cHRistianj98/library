FROM openjdk:11
ADD target/library-0.0.1-SNAPSHOT.jar .
EXPOSE 8080
CMD java -jar library-0.0.1-SNAPSHOT.jar