FROM eclipse-temurin:20-jdk

WORKDIR /app

COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY gradlew .

RUN ./gradlew --no-daemon dependencies

COPY src src

RUN ./gradlew --no-daemon build

ENV JAVA_OPTS "-Xmx512M -Xms512M"
EXPOSE 7070

CMD java -jar build/libs/HexletJavalin-1.0-SNAPSHOT-all.jar