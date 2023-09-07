package org.example.hexlet.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.example.hexlet.model.Course;

public class CourseRepository {
    private static List<Course> entities = new ArrayList<>();

    public static void save(Course course) {
        course.setId((long) entities.size() + 1);
        entities.add(course);
    }

    public static List<Course> search(String term) {
        var courses = entities.stream()
                .filter(entity -> entity.getName().contains(term))
                .toList();
        return courses;
    }

    public static Optional<Course> find(Long id) {
        var course = entities.stream()
                .filter(entity -> entity.getId() == id)
                .findAny()
                .orElse(null);
        return Optional.of(course);
    }

    public static List<Course> getEntities() {
        return entities;
    }
}
