package org.example.hexlet;

import io.javalin.Javalin;

import java.util.Collections;
import java.util.List;

import org.example.hexlet.model.Course;
import org.example.hexlet.dto.courses.CoursePage;
import org.example.hexlet.dto.courses.CoursesPage;

public class HelloWorld {
    public static void main(String[] args) {
        var app = Javalin.create(config -> {
            config.plugins.enableDevLogging();
        });
        app.get("/", ctx -> ctx.render("index.jte"));
        app.get("/users", ctx -> ctx.result("GET /users"));
        app.post("/users", ctx -> ctx.result("POST /users"));

        app.get("/hello", ctx -> {
            var name = ctx.queryParamAsClass("name", String.class).getOrDefault("World");
            ctx.result("Hello, " + name + "!");
        });

        app.get("/courses", ctx -> {
            var courses = List.of(
                    new Course("Java-web", "Web technologies"),
                    new Course("Java-oop", "Strong programming"),
                    new Course("Java-core", "Basic skills")
            );
            var header = "Programming courses";
            var page = new CoursesPage(courses, header);
            ctx.render("courses/index.jte", Collections.singletonMap("page", page));
        });

        app.get("/courses/{id}", ctx -> {
            var id = ctx.pathParam("id");
            var course = new Course("Name: " + id, "Course with id: " + id);
            var page = new CoursePage(course);
            ctx.render("courses/show.jte", Collections.singletonMap("page", page));
        });

        app.get("/users/{id}", ctx -> {
            ctx.result("User ID: " + ctx.pathParam("id"));
        });

        app.get("/courses/{courseId}/lessons/{id}", ctx -> {
            var output = "Course ID: " +
                    ctx.pathParam("courseId") +
                    "\n" +
                    "Lesson ID: " +
                    ctx.pathParam("id");
            ctx.result(output);
        });

        app.start(7070);
    }
}
