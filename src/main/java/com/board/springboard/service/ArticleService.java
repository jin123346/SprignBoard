package com.board.springboard.service;

import com.board.springboard.dto.ArticleDto;
import com.board.springboard.dto.PageRequestDto;
import com.board.springboard.dto.PageResponseDto;
import com.board.springboard.entity.Article;
import com.board.springboard.repository.ArticleRepository;
import jakarta.persistence.Tuple;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class ArticleService {


    private final ArticleRepository articleRepository;

    public void insertArticle(ArticleDto articleDto){
        Article article =  Article.builder()
                .title(articleDto.getTitle())
                .content(articleDto.getContent())
                .nick(articleDto.getNick())
                .password(articleDto.getPassword())
                .hit(0L)
                .build();
        articleRepository.save(article);
    }

    public PageResponseDto selectArticleList(PageRequestDto pageRequestDto){
        Pageable pageable = pageRequestDto.toPageable();

        Page<Article> pages = articleRepository.findAll(pageable);
        List<Article> articles = pages.getContent();
        List<ArticleDto> articleDtoList = articles.stream().map(Article::toDto).toList();

        return  PageResponseDto.builder()
                .dtoList(articleDtoList)
                .total((int) pages.getTotalElements())
                .size(pageRequestDto.getSize())
                .pageRequestDto(pageRequestDto)
                .build();
    }

    public String deleteArticle(Long articleId,String password){

        Optional<Article> isArticle = articleRepository.findByArticleId(articleId);
        if(isArticle.isPresent()){
            Article article = isArticle.get();
            log.info("article : " + article);
            if(article.getPassword().equals(password)){
                articleRepository.delete(article);
                return "success";
            }
            return "not match password";
        }
        return "not found";
    }

    public ArticleDto selectArticle(Long articleId){
        Optional<Article> isArticle = articleRepository.findByArticleId(articleId);
        if(isArticle.isPresent()){
            return isArticle.get().toDto();
        }
        return null;
    }

    public Long selectArticleWithPassword(String password,Long articleId){
        Optional<Article> isArticle = articleRepository.findByArticleId(articleId);
        if(isArticle.isPresent()){
            Article article = isArticle.get();
            return article.getPassword().equals(password) ? article.getArticleId() : null ;
        }
        return null;
    }

    public String modifyArticle(ArticleDto articleDto){
        Optional<Article> opt = articleRepository.findByArticleId(articleDto.getArticleId());
        if(opt.isPresent()){
            Article article = opt.get();
            if(articleDto.getPassword().equals(article.getPassword())){
                article.update(articleDto);
                Article updateArticle = articleRepository.save(article);
                return "success";
            }else{
                return "password Fail";
            }

        }

        return "not found";

    }

}
