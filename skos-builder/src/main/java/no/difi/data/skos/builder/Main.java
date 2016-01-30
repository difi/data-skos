package no.difi.data.skos.builder;

import no.difi.data.skos.builder.api.Build;
import no.difi.data.skos.model.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Paths;

public class Main {

    private static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String... args) throws IOException {
        Workspace workspace = new Workspace(Paths.get("los"));
        workspace.cleanTarget();

        Config config = workspace.getConfig();
        logger.info("Project \"{}\".", config.getName());

        Objects objects = workspace.getObjects();
        logger.info("Found {} objects.", objects.size());

        objects.populate();

        for (String build : config.getBuild()) {
            try {
                logger.info("Running {}", build);
                Build b = (Build) Class.forName(build).newInstance();
                b.build(config, workspace, objects);
            } catch (Exception e) {
                logger.warn(e.getMessage(), e);
            }
        }
    }
}
