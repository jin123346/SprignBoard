package com.board.springboard.dto;

import lombok.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class PageRequestDto {
    @Builder.Default
    private int no =1;  //글번호

    @Builder.Default
    private int pg =1;  //페이지 번호

    @Builder.Default
    private int size=10; //한페이지에 검색할 갯수

    private String type;
    private String keyword;

    public Pageable toPageable(){
        return Pageable.ofSize(size).withPage(pg-1);
    }
}
