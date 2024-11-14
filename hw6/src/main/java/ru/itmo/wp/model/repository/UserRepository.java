package ru.itmo.wp.model.repository;

import ru.itmo.wp.model.domain.User;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface UserRepository {
    User findByLogin(String login);

    User findByEmail(String email);

    User findByLoginAndPasswordSha(String login, String passwordSha);

    User findByEmailAndPasswordSha(String email, String passwordSha);

    List<User> findAll();

    long findCount();

    void save(User user, String passwordSha) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException;
}
