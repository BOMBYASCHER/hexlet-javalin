package org.example.hexlet.dto.users;

import lombok.Getter;

import org.example.hexlet.model.User;

import java.util.List;

@Getter
public class UsersPage {
    private List<User> users;

    public UsersPage(List<User> users) {
        this.users = users;
    }
}
