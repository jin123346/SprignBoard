package com.board.springboard.dto;

import com.board.springboard.entity.Article;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDto {
    private Long articleId;
    private Long hit;
    private String title;
    private String content;
    private String nick;
    private String password;

    public Article toEntity(){
        return Article.builder()
                .title(title)
                .content(content)
                .nick(nick)
                .password(password)
                .articleId(articleId)
                .hit(hit)
                .build();
    }
}
