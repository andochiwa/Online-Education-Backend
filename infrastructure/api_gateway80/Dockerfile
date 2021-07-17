FROM adoptopenjdk/openjdk15:ubi
COPY ./infrastructure/api_gateway80/target/api_gateway.jar /app
COPY ./wait-for-it.sh /app
WORKDIR /app
CMD ["java", "-jar", "api_gateway.jar"]
