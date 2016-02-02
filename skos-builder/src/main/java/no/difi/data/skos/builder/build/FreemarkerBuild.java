package no.difi.data.skos.builder.build;

import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.MultiTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import no.difi.data.skos.builder.Objects;
import no.difi.data.skos.builder.Workspace;
import no.difi.data.skos.builder.api.Build;
import no.difi.data.skos.model.Config;
import no.difi.data.skos.model.SkosObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

public class FreemarkerBuild implements Build {

    private static Logger logger = LoggerFactory.getLogger(FreemarkerBuild.class);

    @Override
    public void build(Config config, Workspace workspace, Objects objects) throws IOException {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_23);
        configuration.setDefaultEncoding("UTF-8");
        // During web page *development* TemplateExceptionHandler.HTML_DEBUG_HANDLER is better.
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        ClassTemplateLoader classTemplateLoader = new ClassTemplateLoader(getClass(), "/freemarker");
        configuration.setTemplateLoader(new MultiTemplateLoader(new TemplateLoader[]{classTemplateLoader}));

        for (String key : objects.keySet()) {
            try {
                SkosObject object = objects.get(key);

                Map<String, Object> model = new HashMap<>();
                model.put("config", config);
                model.put("key", key);
                model.put("object", object);
                model.put("objects", objects);

                Template temp = configuration.getTemplate(object.getClass().getSimpleName() + ".ftl");
                temp.process(model, Files.newBufferedWriter(workspace.getTargetPath(key + ".html")));
            } catch (TemplateException e) {
                logger.warn(e.getMessage(), e);
            }
        }
    }
}
