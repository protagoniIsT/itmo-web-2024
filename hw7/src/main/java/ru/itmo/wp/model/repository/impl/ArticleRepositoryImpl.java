package ru.itmo.wp.model.repository.impl;

import ru.itmo.wp.model.database.DatabaseUtils;
import ru.itmo.wp.model.domain.Article;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.RepositoryException;
import ru.itmo.wp.model.repository.ArticleRepository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticleRepositoryImpl extends BasicRepositoryImpl<Article> implements ArticleRepository {

    @Override
    public void save(Article article) {
        saveBasic(article,
                new Pair("userId", article.getUserId()),
                new Pair("title", article.getTitle()),
                new Pair("text", article.getText()));
    }

    @Override
    public List<Article> findArticlesDesc() {
        List<Article> res = new ArrayList<>();
        try (Connection connection = DATA_SOURCE.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM `Article` ORDER BY creationTime DESC")) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    Article article;
                    while ((article = toModel(statement.getMetaData(), resultSet)) != null) {
                        res.add(article);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RepositoryException("Can't find Article.", e);
        }
        return res;
    }

    @Override
    protected String getName() {
        return "Article";
    }

    @Override
    protected Article toModel(ResultSetMetaData metaData, ResultSet resultSet) throws SQLException {
        if (!resultSet.next()) {
            return null;
        }

        Article article = new Article();
        for (int i = 1; i <= metaData.getColumnCount(); i++) {
            switch (metaData.getColumnName(i)) {
                case "id":
                    article.setId(resultSet.getLong(i));
                    break;
                case "userId":
                    article.setUserId(resultSet.getLong(i));
                    break;
                case "title":
                    article.setTitle(resultSet.getString(i));
                    break;
                case "text":
                    article.setText(resultSet.getString(i));
                    break;
                case "creationTime":
                    article.setCreationTime(resultSet.getTimestamp(i));
                    break;
                default:
                    // No operations.
            }
        }

        return article;
    }
}
