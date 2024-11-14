package ru.itmo.wp.model.service;

import ru.itmo.wp.model.domain.Article;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.model.repository.ArticleRepository;
import ru.itmo.wp.model.repository.impl.ArticleRepositoryImpl;
import com.google.common.base.Strings;

import java.util.List;

public class ArticleService {
    private final ArticleRepository articleRepository = new ArticleRepositoryImpl();

    public void validateArticle(Article article) throws ValidationException {
        if (Strings.isNullOrEmpty(article.getTitle().trim())) {
            throw new ValidationException("Title shouldn't be empty");
        }
        if (Strings.isNullOrEmpty(article.getText().trim())) {
            throw new ValidationException("Text shouldn't be empty");
        }
    }

    public void create(Article article) {
        articleRepository.save(article);
    }

    public List<Article> findArticlesDesc() {
        return articleRepository.findArticlesDesc();
    }
}
