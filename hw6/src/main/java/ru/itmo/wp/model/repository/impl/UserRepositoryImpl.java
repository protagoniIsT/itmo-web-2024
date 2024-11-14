package ru.itmo.wp.model.repository.impl;

import ru.itmo.wp.model.database.DatabaseUtils;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.RepositoryException;
import ru.itmo.wp.model.repository.UserRepository;

import javax.sql.DataSource;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("SqlNoDataSourceInspection")
public class UserRepositoryImpl extends BasicRepositoryImpl<User> implements UserRepository {
    private final DataSource DATA_SOURCE = DatabaseUtils.getDataSource();

    @Override
    public User findByLogin(String login) {
        return findBy(new Pair("login", login));
    }

    @Override
    public User findByEmail(String email) {
        return findBy(new Pair("email", email));
    }

    @Override
    public User findByLoginAndPasswordSha(String login, String passwordSha) {
        return findBy(new Pair("login", login),
                      new Pair("passwordSha", passwordSha));
    }

    @Override
    public User findByEmailAndPasswordSha(String email, String passwordSha) {
        return findBy(new Pair("email", email),
                      new Pair("passwordSha", passwordSha));
    }

    @Override
    public List<User> findAll() {
        return findAllBasic();
    }

    protected User toModel(ResultSetMetaData metaData, ResultSet resultSet) throws SQLException {
        if (!resultSet.next()) {
            return null;
        }

        User user = new User();
        for (int i = 1; i <= metaData.getColumnCount(); i++) {
            switch (metaData.getColumnName(i)) {
                case "id":
                    user.setId(resultSet.getLong(i));
                    break;
                case "login":
                    user.setLogin(resultSet.getString(i));
                    break;
                case "creationTime":
                    user.setCreationTime(resultSet.getTimestamp(i));
                    break;
                case "email":
                    user.setEmail(resultSet.getString(i));
                    break;
                default:
                    // No operations.
            }
        }

        return user;
    }

    public long findCount() {
        try (Connection connection = DATA_SOURCE.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("SELECT COUNT(*) FROM User")) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getLong(1);
                    } else {
                        throw new RepositoryException("Unable to retrieve row count.");
                    }
                }
            }
        } catch (SQLException e) {
            throw new RepositoryException("Database error occurred while retrieving row count.", e);
        }
    }


    @Override
    public void save(User user, String passwordSha) {
        saveBasic(user,
                  new Pair("login", user.getLogin()),
                  new Pair("email", user.getEmail()),
                  new Pair("passwordSha", passwordSha));
    }

    public String getName() {
        return "User";
    }
}
