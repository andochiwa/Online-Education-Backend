FROM adoptopenjdk/openjdk15:ubi
RUN mkdir /app
COPY ./service/service_acl8008/target/service_acl.jar /app
COPY ./wait-for-it.sh /app
WORKDIR /app
CMD ["java", "-jar", "service_acl.jar"]
