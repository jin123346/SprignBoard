package com.board.springboard.dto;

import com.board.springboard.entity.Article;
import lombok.*;

import java.time.LocalDateTime;

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
    private LocalDateTime regDate;

    public Article toEntity(){
        return Article.builder()
                .title(title)
                .content(content)
                .nick(nick)
                .password(password)
                .articleId(articleId)
                .hit(hit)
                .regDate(regDate)
                .build();
    }
}
