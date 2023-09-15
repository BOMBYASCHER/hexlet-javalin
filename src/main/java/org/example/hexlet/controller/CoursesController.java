package org.example.hexlet.controller;

import java.util.Collections;
import java.util.List;

import io.javalin.validation.ValidationException;
import org.example.hexlet.dto.courses.BuildCoursePage;
import org.example.hexlet.dto.courses.CoursePage;
import org.example.hexlet.dto.courses.CoursesPage;
import org.example.hexlet.model.Course;
import org.example.hexlet.repository.CourseRepository;
import org.example.hexlet.util.NamedRoutes;

import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;

public class CoursesController {
    public static void index(Context ctx) {
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
        page.setFlash(ctx.consumeSessionAttribute("flash"));
        ctx.render("courses/index.jte", Collections.singletonMap("page", page));
    }

    public static void show(Context ctx) {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var course = CourseRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse("Entity with id = " + id + " not found"));
        var page = new CoursePage(course);
        ctx.render("courses/show.jte", Collections.singletonMap("page", page));
    }

    public static void build(Context ctx) {
        var page = new BuildCoursePage();
        ctx.render("courses/build.jte", Collections.singletonMap("page", page));
    }

    public static void create(Context ctx) {
//        var nameValidation = ctx.formParamAsClass("name", String.class)
//                .check(value -> value.length() >= 2, "Name of course short than 2 characters");
//        var descriptionValidation = ctx.formParamAsClass("description", String.class)
//                .check(value -> value.length() > 10, "Description short than 10 characters");
//        var errors = JavalinValidation.collectErrors(nameValidation, descriptionValidation);
//        var name = nameValidation.get();
//        var description = descriptionValidation.get();
//        if (!errors.isEmpty()) {
//            var page = new BuildCoursePage(name, description, errors);
//            ctx.render("courses/build.jte", Collections.singletonMap("page", page));
//        } else {
//            var course = new Course(name, description);
//            CourseRepository.save(course);
//            ctx.sessionAttribute("flash", "Course has been created!");
//            ctx.redirect(NamedRoutes.coursesPath());
//        }

        var name = ctx.formParam("name");
        var description = ctx.formParam("description");
        try {
            ctx.formParamAsClass("name", String.class)
                    .check(value -> value.length() >= 2, "Name of course shorter than 2 characters")
                    .get();
            ctx.formParamAsClass("description", String.class)
                    .check(value -> value.length() > 10, "Description shorter than 10 characters")
                    .get();
            var course = new Course(name, description);
            CourseRepository.save(course);
            ctx.sessionAttribute("flash", "Course has been created!");
            ctx.redirect(NamedRoutes.coursesPath());
        } catch (ValidationException e) {
            var page = new BuildCoursePage(name, description, e.getErrors());
            ctx.render("courses/build.jte", Collections.singletonMap("page", page));
        }
    }

    public static void edit(Context ctx) {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var user = CourseRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse("Entity with id = " + id + " not found"));
        var page = new CoursePage(user);
        ctx.render("courses/edit.jte", Collections.singletonMap("page", page));
    }

    public static void update(Context ctx) {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var name = ctx.formParam("name");
        var description = ctx.formParam("description");
        var course = CourseRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse("Entity with id = " + id + " not found"));
        course.setName(name);
        course.setDescription(description);
        CourseRepository.save(course);
        ctx.redirect(NamedRoutes.coursesPath());
    }

    public static void destroy(Context ctx) {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        CourseRepository.delete(id);
        ctx.redirect(NamedRoutes.coursesPath());
    }
}