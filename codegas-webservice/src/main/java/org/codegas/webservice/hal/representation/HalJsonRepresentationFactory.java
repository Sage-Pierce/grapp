package org.codegas.webservice.hal.representation;

import java.io.Serializable;
import java.util.Collection;

import org.codegas.webservice.hal.api.HalLink;
import org.codegas.webservice.hal.api.HalRepresentation;
import org.codegas.webservice.hal.api.HalRepresentationFactory;

import com.theoryinpractise.halbuilder.json.JsonRepresentationFactory;

public final class HalJsonRepresentationFactory extends JsonRepresentationFactory implements HalRepresentationFactory {

    public HalJsonRepresentationFactory() {
        // Curies
        //      withNamespace("base", UriBuilder.fromUri("").scheme("http").host("localhost").port(8008).segment("rest").segment("{rel}").toTemplate());
        //      withNamespace("docs", UriBuilder.fromUri("/").segment("docs").segment("{rel}").toTemplate());
        withFlag(JsonRepresentationFactory.PRETTY_PRINT);
        withFlag(JsonRepresentationFactory.COALESCE_ARRAYS);
    }

    public HalRepresentation createFor(Object resource) {
        return new HalJsonRepresentationImpl(newRepresentation()).withBean(wrapResource(resource));
    }

    public HalRepresentation createForLinks(Iterable<HalLink> links) {
        return new HalJsonRepresentationImpl(newRepresentation()).withLinks(links);
    }

    private Object wrapResource(Object resource) {
        if (resource == null || resource.getClass().isPrimitive() || CharSequence.class.isInstance(resource)) {
            return new PrimitiveWrapper(resource);
        } else if (Collection.class.isInstance(resource)) {
            return new CollectionWrapper(Collection.class.cast(resource));
        } else {
            return resource;
        }
    }

    protected static class PrimitiveWrapper implements Serializable {

        private final Object value;

        public PrimitiveWrapper(Object value) {
            this.value = value;
        }

        public Object getValue() {
            return value;
        }
    }

    protected static class CollectionWrapper implements Serializable {

        private final Collection values;

        private CollectionWrapper(Collection values) {
            this.values = values;
        }

        public Collection getValues() {
            return values;
        }
    }
}
