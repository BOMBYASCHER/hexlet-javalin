package org.example.hexlet.dto.courses;

import java.util.List;
import java.util.Map;

import io.javalin.validation.ValidationError;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BuildCoursePage {
    public String name;
    public String description;
    public Map<String, List<ValidationError<Object>>> errors;
}
