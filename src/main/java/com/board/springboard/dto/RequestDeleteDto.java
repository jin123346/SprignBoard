package com.board.springboard.dto;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestDeleteDto {
    String password;
    Long articleId;
}
