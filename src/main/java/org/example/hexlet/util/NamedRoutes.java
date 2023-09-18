package org.example.hexlet.util;

public class NamedRoutes {
    public static String rootPath() {
        return "/";
    }
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
        return userPath(String.valueOf(id));
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

    public static String editUserPath(Long id) {
        return userPath(id) + "/edit";
    }

    public static String editUserPath(String id) {
        return userPath(id) + "/edit";
    }

    public static String editCoursePath(Long id) {
        return coursePath(id) + "/edit";
    }

    public static String editCoursePath(String id) {
        return coursePath(id) + "/edit";
    }

    public static String sessionsPath() {
        return "/sessions";
    }

    public static String buildSessionPath() {
        return sessionsPath() + "/build";
    }

    public static String carsPath() {
        return "/cars";
    }

    public static String carPath(Long id) {
        return carPath(String.valueOf(id));
    }

    public static String carPath(String id) {
        return "/cars/" + id;
    }

    public static String newCarPath() {
        return "/cars/new";
    }
}
