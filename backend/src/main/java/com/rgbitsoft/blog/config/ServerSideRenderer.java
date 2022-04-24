package com.rgbitsoft.blog.config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Path;

import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.SimpleScriptContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;

@Component
public class ServerSideRenderer {
    private Logger logger = LoggerFactory.getLogger(ServerSideRenderer.class);
    private static final String VUE_RENDERER = System.getProperty("user.dir") + "/frontend-public/node_modules/vue-server-renderer/";


    private ScriptEngine getEngine() {
        return new ScriptEngineManager().getEngineByName("graal.js");
    }

    private ScriptContext getContext() {
        return new SimpleScriptContext();
    }

    /**
     * @param route
     * @return
     * @throws ScriptException
     * @throws IOException
     */
    public String render(String route) throws ScriptException, IOException {

        ScriptEngine engine = getEngine();
        ScriptContext context = getContext();
        Bindings engineScope = engineSetting(engine, context);
        logger.info("Server Side Rendering - url : {} " , route);
        logger.info("enginScope  : {}", engineScope);
        engineScope.put("rendered", null);             // Global variable declaration
        engineScope.put("route", route);                     // Global variable declaration
        engine.eval(read("static/public/js/server.js"), context);

        return context.getAttribute("rendered").toString();  // Get rendered variable to String type
    }

    /**
     * @param engine
     * @param context
     * @return
     * @throws ScriptException
     * @throws IOException
     */
    private Bindings engineSetting(ScriptEngine engine, ScriptContext context) throws ScriptException, IOException {
        context.setBindings(engine.createBindings(), ScriptContext.ENGINE_SCOPE);
        Bindings engineScope = context.getBindings(ScriptContext.ENGINE_SCOPE);

        engine.setContext(context);

        engine.eval("var process = { env: { VUE_ENV: 'server', NODE_ENV: 'production' }}; this.global = { process: process };",                context);
        loadFiles(engine, context);// vue-server-Loading renderer
        return engineScope;
    }

    /**
     * @param engine
     * @param context
     * @throws IOException
     * @throws ScriptException
     */
    private void loadFiles(ScriptEngine engine, ScriptContext context) throws IOException, ScriptException {
        Path path = Path.of(VUE_RENDERER);
        Path file = path.resolve("basic.js");

        Resource resource = new UrlResource(file.toUri());
        if (resource.exists() || resource.isReadable()) {
            InputStream in = resource.getInputStream();
            String all = all(new BufferedReader(new InputStreamReader(in)));
            engine.eval(all, context);
        }
    }

    private String all(BufferedReader reader) throws IOException {
        StringBuilder builder = new StringBuilder();
        String string;

        string = reader.readLine();
        while (string != null){
            builder.append(string + System.getProperty("line.separator"));
            string = reader.readLine();
        }
        return builder.toString();
    }

    private Reader read(String path) {
        InputStream in = getClass().getClassLoader().getResourceAsStream(path);
        return new InputStreamReader(in);
    }
}
