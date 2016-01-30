package no.difi.data.skos.yaml;

import org.yaml.snakeyaml.introspector.Property;
import org.yaml.snakeyaml.nodes.NodeTuple;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.representer.Representer;

import java.util.List;
import java.util.Map;

public class SkosRepresenter extends Representer {
    @Override
    protected NodeTuple representJavaBeanProperty(Object javaBean, Property property, Object propertyValue, Tag customTag) {
        if (propertyValue == null)
            return null;
        if (propertyValue instanceof List && ((List) propertyValue).isEmpty())
            return null;
        if (propertyValue instanceof Map && ((Map) propertyValue).isEmpty())
            return null;

        return super.representJavaBeanProperty(javaBean, property, propertyValue, customTag);
    }
}