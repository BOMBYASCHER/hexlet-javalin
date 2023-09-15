package org.example.hexlet.dto.users;

import lombok.Getter;
import lombok.AllArgsConstructor;

import org.example.hexlet.dto.BasePage;
import org.example.hexlet.model.User;

import java.util.List;

@Getter
@AllArgsConstructor
public class UsersPage extends BasePage {
    private List<User> users;
}
