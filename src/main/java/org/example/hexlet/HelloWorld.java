package org.example.hexlet;

import io.javalin.Javalin;

import java.util.Collections;
import java.util.List;

import org.apache.commons.text.StringEscapeUtils;
import org.example.hexlet.dto.users.UsersPage;
import org.example.hexlet.model.Course;
import org.example.hexlet.dto.courses.CoursePage;
import org.example.hexlet.dto.courses.CoursesPage;
import org.example.hexlet.model.User;
import org.example.hexlet.repository.CourseRepository;
import org.example.hexlet.repository.UserRepository;

public class HelloWorld {
    private static UserRepository userRepository = new UserRepository();
    private static CourseRepository courseRepository = new CourseRepository();

    public static void main(String[] args) {
        var app = Javalin.create(config -> {
            config.plugins.enableDevLogging();
        });
        courseRepository.save(new Course("Java-web", "Web technologies"));
        courseRepository.save(new Course("Java-oop", "Strong programming"));
        courseRepository.save(new Course("Java-core", "Basic skills"));
        courseRepository.save(new Course("Java-advance", "Advanced programming skills"));
        app.get("/", ctx -> ctx.render("layout/page.jte"));
        app.get("/hello", ctx -> {
            var name = ctx.queryParamAsClass("name", String.class).getOrDefault("World");
            ctx.result("Hello, " + name + "!");
        });

        app.get("/my", ctx -> {
            ctx.render("my.jte");
        });

        app.get("/users", ctx -> {
            var page = new UsersPage(userRepository.getEntities());
            ctx.render("users/index.jte", Collections.singletonMap("page", page));
        });

        app.get("/courses", ctx -> {
            var term = ctx.queryParam("term");
            List<Course> courses;
            var entities = courseRepository.getEntities();
            if (term != null) {
                courses = entities.stream()
                        .filter(course -> course.getName().equalsIgnoreCase(term) ||
                                course.getDescription().toLowerCase().contains(term.toLowerCase()))
                        .toList();
            } else {
                courses = entities;
            }
            var header = "Programming courses";
            var page = new CoursesPage(courses, header, term);
            ctx.render("courses/index.jte", Collections.singletonMap("page", page));
        });

        app.get("/courses/new", ctx -> {
            ctx.render("courses/new.jte");
        });

        app.post("/courses", ctx -> {
            var name = ctx.formParam("name");
            var description = ctx.formParam("description");

            var course = new Course(name, description);
            courseRepository.save(course);
            ctx.redirect("courses");
        });

        app.get("/courses/{id}", ctx -> {
            var id = ctx.pathParam("id");
            var escapedId = StringEscapeUtils.escapeHtml4(id);
            var course = new Course("Name: " + escapedId, "Course with id: " + escapedId);
            var page = new CoursePage(course);
            ctx.render("courses/show.jte", Collections.singletonMap("page", page));
        });

        app.get("/users/new", ctx -> {
            ctx.render("users/new.jte");
        });

        app.post("/users", ctx -> {
            var name = ctx.formParam("name").trim();
            var email = ctx.formParam("email").trim().toLowerCase();
            var password = ctx.formParam("password");
            var passwordConfirmation = ctx.formParam("passwordConfirmation");

            var user = new User(name, email, password);
            userRepository.save(user);
            ctx.redirect("/users");
        });

        app.get("/users/{id}", ctx -> {
            var id = ctx.pathParam("id");
            var escapedId = StringEscapeUtils.escapeHtml4(id);
            ctx.contentType("text/html");
            ctx.result("User ID: " + escapedId);
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
