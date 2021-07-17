FROM adoptopenjdk/openjdk15:ubi
COPY ./service/service_cms8004/target/service_cms.jar /app
COPY ./wait-for-it.sh /app
WORKDIR /app
CMD ["java", "-jar", "service_cms.jar"]
