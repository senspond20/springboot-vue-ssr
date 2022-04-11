package com.rgbitsoft.blog.controller.web;

import com.rgbitsoft.blog.service.RendererService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class IndexController {
    private final RendererService renderService;

    @GetMapping("/")
    public String index(Model model){
        String html = renderService.renderPage("/");
        model.addAttribute("rendered", html);
        return "index";
    }
}
