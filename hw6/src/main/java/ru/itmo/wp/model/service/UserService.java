package ru.itmo.wp.model.service;

import com.google.common.base.Strings;
import com.google.common.hash.Hashing;
import ru.itmo.wp.model.domain.Event;
import ru.itmo.wp.model.domain.EventType;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.model.repository.EventRepository;
import ru.itmo.wp.model.repository.UserRepository;
import ru.itmo.wp.model.repository.impl.EventRepositoryImpl;
import ru.itmo.wp.model.repository.impl.UserRepositoryImpl;

import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class UserService {
    private static final String PASSWORD_SALT = "1174f9d7bc21e00e9a5fd0a783a44c9a9f73413c";

    private final UserRepository userRepository = new UserRepositoryImpl();
    private final EventRepository eventRepository = new EventRepositoryImpl();

    public void validateRegistration(User user, String password, String passwordConfirmation) throws ValidationException {
        if (Strings.isNullOrEmpty(user.getLogin())) {
            throw new ValidationException("Login is required");
        }
        if (!user.getLogin().matches("[a-z]+")) {
            throw new ValidationException("Login can contain only lowercase Latin letters");
        }
        if (user.getLogin().length() > 8) {
            throw new ValidationException("Login can't be longer than 8 letters");
        }
        if (userRepository.findByLogin(user.getLogin()) != null) {
            throw new ValidationException("Login is already in use");
        }

        if (Strings.isNullOrEmpty(password)) {
            throw new ValidationException("Password is required");
        }
        if (password.length() < 4) {
            throw new ValidationException("Password can't be shorter than 4 characters");
        }
        if (password.length() > 64) {
            throw new ValidationException("Password can't be longer than 64 characters");
        }
        if (!password.equals(passwordConfirmation)) {
            throw new ValidationException("Invalid password confirmation");
        }

        if (!user.getEmail().contains("@")) {
            throw new ValidationException("Invalid email");
        }
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new ValidationException("User with this email is already registered");
        }
    }

    public void register(User user, String password) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        userRepository.save(user, getPasswordSha(password));
    }

    public void saveEvent(User user, EventType type) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        eventRepository.save(new Event(user.getId(), type));
    }

    private String getPasswordSha(String password) {
        return Hashing.sha256().hashBytes((PASSWORD_SALT + password).getBytes(StandardCharsets.UTF_8)).toString();
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public long findCount() {
        return userRepository.findCount();
    }

    public void validateEnter(String loginOrEmail, String password) throws ValidationException, InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        User user;
        if (loginOrEmail.contains("@")) {
            user = userRepository.findByEmailAndPasswordSha(loginOrEmail, getPasswordSha(password));
        } else {
            user = userRepository.findByLoginAndPasswordSha(loginOrEmail, getPasswordSha(password));
        }
        if (user == null) {
            throw new ValidationException("Invalid login or password");
        }
        saveEvent(user, EventType.ENTER);
    }

    public User findByLoginOrEmailAndPasswordSha(String loginOrEmail, String password) {
        if (loginOrEmail.contains("@")) {
            return userRepository.findByEmailAndPasswordSha(loginOrEmail, getPasswordSha(password));
        } else {
            return userRepository.findByLoginAndPasswordSha(loginOrEmail, getPasswordSha(password));
        }
    }
}
