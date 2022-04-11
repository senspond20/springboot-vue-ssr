package com.rgbitsoft.blog.controller.api;

import com.rgbitsoft.blog.service.WordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api")
public class WordRestController {
    private final WordService wordService;

    @GetMapping("/get-word/{pageId}")
    public Map<String, String> getWord(@PathVariable("pageId") String pageId) {

        return wordService.getWord(pageId);
    }

}


