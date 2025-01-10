package com.board.springboard.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PageResponseDto {
    private List<ArticleDto> articleDtoList;
    private int pg=1;
    private int total;
    private int size;
    private int startNo;
    private int start, end;
    private boolean prev,next;
    private PageRequestDto pageRequestDto;

    @Builder
    public PageResponseDto(PageRequestDto pageRequestDto, List<ArticleDto> dtoList,int size, int total){
        this.pg = pageRequestDto.getPg() > 0 ? pageRequestDto.getPg() : 1; // pg가 1 이상이어야 함
        this.total = total;
        this.size = pageRequestDto.getSize();
        this.articleDtoList = dtoList;
        this.startNo = total - ((pg-1)*10);  //개시물 시작번호
        this.end =(int) (Math.ceil(this.pg/10.0))*10;
        this.start = this.end -9;
        int last = (int)(Math.ceil(total/(double)size));
        this.end = Math.min(this.end, last); // end는 last를 초과할 수 없음
        this.prev = this.start > 1;
        this.next = total > this.end *this.size;
        // Ensure `end` is at least 1 if `start` is 1
        if (this.start < 1) {
            this.start = 1;
        }
        if (this.end < 1) {
            this.end = 1;
        }

    }

}
