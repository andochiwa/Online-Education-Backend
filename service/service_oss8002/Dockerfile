FROM adoptopenjdk/openjdk15:ubi
COPY ./service/service_oss8002/target/service_oss.jar /app
COPY ./wait-for-it.sh /app
WORKDIR /app
CMD ["java", "-jar", "service_oss.jar"]
