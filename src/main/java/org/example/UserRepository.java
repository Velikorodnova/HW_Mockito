package org.example;

import java.util.*;

public class UserRepository {

    private List<User> users = new ArrayList<>();

    public Collection<User> getAllUser() {
        return Collections.unmodifiableCollection(users);
    }

    public Optional<User> addUserByLogin(String login) {
        for (User user : users) {
            if (user.getLogin().equals(login)) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    public Optional<User> addUserByLoginAndPassword(String login, String password) {
        for (User user : users) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    public void addUser(User user) {
        this.users.add(user);
    }
}
