FROM maven:3.8.2-adoptopenjdk-11

WORKDIR /smartapi
COPY . .
RUN mvn clean install -DskipTests

CMD mvn spring-boot:run

#RUN docker compose command: docker-compose up --build -d