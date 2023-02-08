package org.example;

import org.example.exeption.LoginNotFound;
import org.example.exeption.PasswordNotFound;

import java.util.ArrayList;
import java.util.List;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createNewUser(String login, String password) {
        User user = new User(login, password);

        if (login == null || login.isBlank()) {
            throw new LoginNotFound("Не введен логин");
        }
        if (password == null || password.isBlank()) {
            throw new PasswordNotFound("Не введен пароль");
        }
        boolean userExist = this.userRepository
                .getAllUser()
                .stream()
                .anyMatch(u -> u.equals(user));
        if (userExist) {
            throw new IllegalArgumentException("Пользователь уже существует");
        }
        this.userRepository.addUser(user);
    }

    public List<String> getAllLogin() {
        List<String> login = new ArrayList<>();
        for (User user : userRepository.getAllUser()) {
            String userLogin = user.getLogin();
            login.add(userLogin);
        }
        return login;
    }

    public boolean userByLoginAndPassword(String login, String password) {
        for (User user : userRepository.getAllUser()) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
}
