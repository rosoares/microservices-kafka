FROM maven:3.8.2-amazoncorretto-11
WORKDIR /orders
COPY . .
RUN mvn clean install
CMD mvn spring-boot:run