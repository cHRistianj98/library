FROM jdk-11.0.11_9-alpine
ADD library-0.0.1-SNAPSHOT.jar .
EXPOSE 8080
CMD java -jar library-0.0.1-SNAPSHOT.jar