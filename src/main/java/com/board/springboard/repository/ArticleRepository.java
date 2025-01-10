package com.board.springboard.repository;

import com.board.springboard.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    Optional<Article> findByArticleId(Long articleId);
}
