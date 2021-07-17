FROM adoptopenjdk/openjdk15:ubi
RUN mkdir /app
COPY ./service/service_statistic8007/target/service_statictis.jar /app
COPY ./wait-for-it.sh /app
WORKDIR /app
CMD ["java", "-jar", "service_statictis.jar"]
