package org.example.hexlet.util;

public class NamedRoutes {
    public static String myPath() {
        return "/my";
    }
    public static String usersPath() {
        return "/users";
    }

    public static String newUserPath() {
        return "/users/new";
    }

    public static String coursesPath() {
        return "/courses";
    }

    public static String newCoursePath() {
        return "/courses/new";
    }

    public static String userPath(Long id) {
        return coursePath(String.valueOf(id));
    }

    public static String userPath(String id) {
        return "/users/" + id;
    }

    public static String coursePath(Long id) {
        return coursePath(String.valueOf(id));
    }

    public static String coursePath(String id) {
        return "/courses/" + id;
    }
}
