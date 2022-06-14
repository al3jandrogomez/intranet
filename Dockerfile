FROM openjdk:8
EXPOSE 8090
ADD target/intranet.jar intranet.jar 
ENTRYPOINT ["java","-jar","/intranet.jar"]