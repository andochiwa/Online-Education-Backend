FROM adoptopenjdk/openjdk15:ubi
RUN mkdir /app
COPY ./service/service_order8006/target/service_order.jar /app
COPY ./wait-for-it.sh /app
WORKDIR /app
CMD ["java", "-jar", "service_order.jar"]
