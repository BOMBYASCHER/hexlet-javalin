package org.example.hexlet;

import io.javalin.Javalin;

import java.util.Collections;
import java.util.List;

import io.javalin.validation.ValidationException;
import org.apache.commons.text.StringEscapeUtils;
import org.example.hexlet.dto.courses.BuildCoursePage;
import org.example.hexlet.dto.users.BuildUserPage;
import org.example.hexlet.dto.users.UsersPage;
import org.example.hexlet.model.Course;
import org.example.hexlet.dto.courses.CoursePage;
import org.example.hexlet.dto.courses.CoursesPage;
import org.example.hexlet.model.User;
import org.example.hexlet.repository.CourseRepository;
import org.example.hexlet.repository.UserRepository;
import org.example.hexlet.util.NamedRoutes;

public class HelloWorld {
    public static void main(String[] args) {
        var app = Javalin.create(config -> {
            config.plugins.enableDevLogging();
        });
        CourseRepository.save(new Course("Java-web", "Web technologies"));
        CourseRepository.save(new Course("Java-oop", "Strong programming"));
        CourseRepository.save(new Course("Java-core", "Basic skills"));
        CourseRepository.save(new Course("Java-advance", "Advanced programming skills"));
        app.get("/", ctx -> ctx.render("layout/page.jte"));
        app.get("/hello", ctx -> {
            var name = ctx.queryParamAsClass("name", String.class).getOrDefault("World");
            ctx.result("Hello, " + name + "!");
        });

        app.get(NamedRoutes.myPath(), ctx -> {
            ctx.render("my.jte");
        });

        app.get(NamedRoutes.usersPath(),ctx -> {
            var page = new UsersPage(UserRepository.getEntities());
            ctx.render("users/index.jte", Collections.singletonMap("page", page));
        });

        app.get(NamedRoutes.coursesPath(), ctx -> {
            var term = ctx.queryParam("term");
            List<Course> courses;
            var entities = CourseRepository.getEntities();
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

        app.get(NamedRoutes.newCoursePath(), ctx -> {
            var page = new BuildCoursePage();
            ctx.render("courses/new.jte", Collections.singletonMap("page", page));
        });

        app.post(NamedRoutes.coursesPath(), ctx -> {
            var name = ctx.formParam("name");
            var description = ctx.formParam("description");
            try {
                ctx.formParamAsClass("name", String.class)
                        .check(value -> value.length() >= 2, "Name of course short than 2 characters")
                        .get();
                ctx.formParamAsClass("description", String.class)
                        .check(value -> value.length() > 10, "Description short than 10 characters")
                        .get();
                var course = new Course(name, description);
                CourseRepository.save(course);
                ctx.redirect(NamedRoutes.coursesPath());
            } catch (ValidationException e) {
                var page = new BuildCoursePage(name, description, e.getErrors());
                ctx.render("courses/new.jte", Collections.singletonMap("page", page));
            }
        });

        app.get(NamedRoutes.coursePath("{id}"), ctx -> {
            var id = ctx.pathParam("id");
            var escapedId = StringEscapeUtils.escapeHtml4(id);
            var course = new Course("Name: " + escapedId, "Course with id: " + escapedId);
            var page = new CoursePage(course);
            ctx.render("courses/show.jte", Collections.singletonMap("page", page));
        });

        app.get(NamedRoutes.newUserPath(), ctx -> {
            var page = new BuildUserPage();
            ctx.render("users/new.jte", Collections.singletonMap("page", page));
        });

        app.post(NamedRoutes.usersPath(), ctx -> {
            var name = ctx.formParam("name").trim();
            var email = ctx.formParam("email").trim().toLowerCase();
            try {
                var passwordConfirmation = ctx.formParam("passwordConfirmation");
                var password = ctx.formParamAsClass("password", String.class)
                        .check(value -> value.equals(passwordConfirmation), "Password mismatch")
                        .check(value -> value.length() > 6, "Password short than 6 characters")
                        .get();
                var user = new User(name, email, password);
                UserRepository.save(user);
                ctx.redirect(NamedRoutes.usersPath());
            } catch (ValidationException e) {
                var page = new BuildUserPage(name, email, e.getErrors());
                ctx.render("users/new.jte", Collections.singletonMap("page", page));
            }
        });

        app.get(NamedRoutes.userPath("{id}"), ctx -> {
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
