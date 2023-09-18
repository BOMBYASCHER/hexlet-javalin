package org.example.hexlet.dto.cars;

import java.util.List;
import java.util.Map;

import io.javalin.validation.ValidationError;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BuildCarPage {
    public String make;
    public String model;
    public Map<String, List<ValidationError<Object>>> errors;
}
