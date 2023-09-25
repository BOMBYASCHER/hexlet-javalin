FROM gradle:7.4.0-jdk17

WORKDIR /org.example.hexlet

COPY /org.example.hexlet .

RUN gradle installDist

CMD ./build/install/HexletJavalin/bin/HexletJavalin