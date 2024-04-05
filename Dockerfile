FROM openjdk:23-ea-16-oraclelinux8
COPY target/vietnam_travel_system-0.0.1-SNAPSHOT.jar vietnam_travel_system-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","vietnam_travel_system-0.0.1-SNAPSHOT.jar"]
