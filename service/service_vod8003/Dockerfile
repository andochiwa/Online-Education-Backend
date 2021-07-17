FROM adoptopenjdk/openjdk15:ubi
RUN mkdir /app
COPY ./service/service_vod8003/target/service_vod.jar /app
COPY ./wait-for-it.sh /app
WORKDIR /app
CMD ["java", "-jar", "service_vod.jar"]
