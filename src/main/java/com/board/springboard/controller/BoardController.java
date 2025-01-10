package com.board.springboard.controller;

import com.board.springboard.dto.ArticleDto;
import com.board.springboard.dto.PageRequestDto;
import com.board.springboard.dto.PageResponseDto;
import com.board.springboard.dto.RequestDeleteDto;
import com.board.springboard.entity.Article;
import com.board.springboard.service.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Log4j2
@Controller
@RequiredArgsConstructor
public class BoardController {

    private final ArticleService articleService;

    @GetMapping(value = {"/board/list","/"})
    public String list(PageRequestDto pageRequestDto, Model model){
        log.info("list : " + pageRequestDto);

        PageResponseDto pageResponseDto = articleService.selectArticleList(pageRequestDto);
        log.info("pageResponseDto : " + pageResponseDto);
        model.addAttribute("pageResponseDto", pageResponseDto);
        return "list";
    }
    @GetMapping("/board/write")
    public String write(){



        return "write";
    }

    @PostMapping("/board/write")
    public String writeSubmit(ArticleDto articleDto){
        log.info("writeSubmit : " + articleDto);

        articleService.insertArticle(articleDto);
        return "redirect:/board/list";
    }

    @GetMapping("/board/view/{articleId}")
    public String view(@PathVariable("articleId") String articleId,Model model,@RequestParam(defaultValue = "1") long pg){
        ArticleDto articleDto = articleService.selectArticle(Long.valueOf(articleId));
        model.addAttribute("articleDto",articleDto);
        model.addAttribute("pg",pg);
        return "view";
    }

    @GetMapping("/board/modify/{articleId}")
    public String modify(@PathVariable("articleId") String articleId ,@RequestParam(defaultValue = "1") long pg,Model model){
        log.info("modify : " + articleId);

        ArticleDto articleDto = articleService.selectArticle(Long.valueOf(articleId));
        model.addAttribute("articleDto",articleDto);
        model.addAttribute("pg",pg);
        return "modify";
    }

    @PostMapping("/board/delete")
    public ResponseEntity delete(@RequestBody RequestDeleteDto requestDeleteDto){
        log.info("delete : " + requestDeleteDto);
        Map<String,String> result = new HashMap<>();

        String deleteResult = articleService.deleteArticle(requestDeleteDto.getArticleId(),requestDeleteDto.getPassword());
        result.put("result",deleteResult);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/board/modify")
    public ResponseEntity modifySubmit(@RequestBody ArticleDto articleDto){
        log.info("modifySubmit : " + articleDto);
        Map<String,String> result = new HashMap<>();

        String updateResult = articleService.modifyArticle(articleDto);

        result.put("result",updateResult);
        return ResponseEntity.ok().body(result);


    }

    @PostMapping("/board/validation")
    public ResponseEntity validation(@RequestBody ArticleDto articleDto){
        log.info("validation : " + articleDto);
        Map<String,String> result = new HashMap<>();
        Long validationResult = articleService.selectArticleWithPassword(articleDto.getPassword(),articleDto.getArticleId());
        if(validationResult != null){
            return ResponseEntity.ok().body(validationResult);

        }else{
            return ResponseEntity.ok().body("fail");
        }


    }
}
