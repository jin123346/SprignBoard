package com.board.springboard.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageResponseDto {
    private List<ArticleDto> articleDtoList;
    private int pg;
    private int total;
    private int size;
    private int startNo;
    private int start, end;
    private boolean prev,next;
    private PageRequestDto pageRequestDto;

    @Builder
    public PageResponseDto(PageRequestDto pageRequestDto, List<ArticleDto> dtoList,int size, int total){
        this.pg = pageRequestDto.getPg();
        this.total = total;
        this.size = pageRequestDto.getSize();
        this.articleDtoList = dtoList;
        this.startNo = total - ((pg-1)*size);  //개시물 시작번호
        this.end =(int) (Math.ceil(this.pg/10.0))*10;
        this.start = this.end -9;
        int last = (int)(Math.ceil(total/(double)size));
        this.end = end > last? last:end;
        this.prev = this.start>1;
        this.next = total > this.end *this.size;
        // Ensure `end` is at least 1 if `start` is 1
        if (this.start == 1 && this.end == 0) {
            this.end = 0;
        }


    }

}
