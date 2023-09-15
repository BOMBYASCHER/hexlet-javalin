package org.example.hexlet.dto.courses;

import java.util.List;

import org.example.hexlet.dto.BasePage;
import org.example.hexlet.model.Course;

import lombok.Getter;
import lombok.AllArgsConstructor;

@Getter
@AllArgsConstructor
public class CoursesPage extends BasePage {
    private List<Course> courses;
    private String header;
    private String term;
}