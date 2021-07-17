FROM adoptopenjdk/openjdk15:ubi
RUN mkdir /app
COPY ./service/service_center8005/target/service_center.jar /app
COPY ./wait-for-it.sh /app
WORKDIR /app
CMD ["java", "-jar", "service_center.jar"]
