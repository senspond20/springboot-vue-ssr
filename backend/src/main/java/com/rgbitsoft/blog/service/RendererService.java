package com.rgbitsoft.blog.service;

import com.rgbitsoft.blog.renderder.ServerSideRenderer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RendererService {
    private final ServerSideRenderer renderer;

    public String renderPage(String route) {
        String html = "";
        try {
            html = renderer.render(route);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return html;
    }
}
