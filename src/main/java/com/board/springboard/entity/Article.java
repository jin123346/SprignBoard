package com.board.springboard.entity;

import com.board.springboard.dto.ArticleDto;
import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "article")
public class Article {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long articleId;
    private String title;
    private String content;
    private String nick;
    private String password;

    @Builder.Default
    private Long hit = 0L ;

    public void increaseHit(){
        hit++;
    }
    public ArticleDto toDto(){
        return ArticleDto.builder()
                .articleId(articleId)
                .hit(hit)
                .title(title)
                .content(content)
                .nick(nick)
                .password(password)
                .build();
    }
    public Article update(ArticleDto dto){
        this.title = dto.getTitle();
        this.content = dto.getContent();
        this.nick = dto.getNick();

        return this;
    }
}
