FROM adoptopenjdk/openjdk12-openj9:alpine
VOLUME /tmp
ADD ./build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]