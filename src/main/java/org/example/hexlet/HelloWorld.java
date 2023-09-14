package org.example.hexlet;

import io.javalin.Javalin;

import java.util.Collections;
import java.util.List;

import io.javalin.validation.ValidationException;
import org.apache.commons.text.StringEscapeUtils;
import org.example.hexlet.controller.CoursesController;
import org.example.hexlet.controller.SessionsController;
import org.example.hexlet.controller.UsersController;
import org.example.hexlet.dto.MainPage;
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

        app.get(NamedRoutes.rootPath(), ctx -> {
            var visited = Boolean.valueOf(ctx.cookie("visited"));
            var page = new MainPage(visited, ctx.sessionAttribute("currentUser"));
            ctx.render("index.jte", Collections.singletonMap("page", page));
            ctx.cookie("visited", String.valueOf(true));
        });

        app.get("/hello", ctx -> {
            var name = ctx.queryParamAsClass("name", String.class).getOrDefault("World");
            ctx.result("Hello, " + name + "!");
        });

        app.get(NamedRoutes.myPath(), ctx -> {
            ctx.render("my.jte");
        });

        app.get(NamedRoutes.buildSessionPath(), SessionsController::build);
        app.post(NamedRoutes.sessionsPath(), SessionsController::create);
        app.delete(NamedRoutes.sessionsPath(), SessionsController::destroy);

        app.get(NamedRoutes.usersPath(), UsersController::index);
        app.get(NamedRoutes.newUserPath(), UsersController::build);
        app.post(NamedRoutes.usersPath(), UsersController::create);
        app.get(NamedRoutes.userPath("{id}"), UsersController::show);
        app.get(NamedRoutes.editUserPath("{id}"), UsersController::edit);
        app.patch(NamedRoutes.userPath("{id}"), UsersController::update);
        app.delete(NamedRoutes.usersPath(), UsersController::destroy);

        app.get(NamedRoutes.coursesPath(), CoursesController::index);
        app.get(NamedRoutes.newCoursePath(), CoursesController::build);
        app.post(NamedRoutes.coursesPath(), CoursesController::create);
        app.get(NamedRoutes.coursePath("{id}"), CoursesController::show);
        app.get(NamedRoutes.editCoursePath("{id}"), CoursesController::edit);
        app.patch(NamedRoutes.coursePath("{id}"), CoursesController::update);
        app.delete(NamedRoutes.coursesPath(), CoursesController::destroy);

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
