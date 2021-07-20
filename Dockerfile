FROM openjdk:11

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} e-psychological-counseling.jar

ENTRYPOINT ["java", "-jar", "/e-psychological-counseling.jar"]
