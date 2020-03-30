FROM openjdk:8
ADD target/account-0.0.1-SNAPSHOT.jar account-0.0.1-SNAPSHOT.jar
CMD ["mkdir", "/root/config"]
COPY src/main/resources/application.properties /root/config/
COPY src/main/resources/data.sql /root/config/
COPY src/main/resources/sharedDTOs.jar /root/config/
ENTRYPOINT ["java","-jar","account-0.0.1-SNAPSHOT.jar","--spring.config.location=file:///root/config/"]
