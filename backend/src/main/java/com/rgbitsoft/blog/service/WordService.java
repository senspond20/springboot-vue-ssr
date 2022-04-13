package com.rgbitsoft.blog.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class WordService {

    public Map<String, String> getWord(String pageId) {

        Map<String, String> map = new HashMap<String, String>();

        if (pageId.equals("blogPage")) {
            map.put("word", "home page data");
        } else {
            map.put("word", "blog page data");
        }
        return map;
    }
}
