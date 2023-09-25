FROM gradle:7.4.0-jdk17

WORKDIR /src/main/java/org.example.hexlet

COPY /src/main/java/org.example.hexlet .

RUN gradle installDist

CMD ./build/install/HexletJavalin/bin/HexletJavalin