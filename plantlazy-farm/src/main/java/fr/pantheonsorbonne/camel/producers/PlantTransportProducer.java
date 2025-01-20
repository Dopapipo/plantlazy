package fr.pantheonsorbonne.camel.producers;

import jakarta.enterprise.context.ApplicationScoped;
import org.apache.camel.builder.RouteBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class PlantTransportProducer extends RouteBuilder {
    @ConfigProperty(name = "plant.transport.endpoint")
    String transportEndpoint;
    @Override
    public void configure() throws Exception {
        from("direct:plantQueue")
                .log("Sending log message: ${body}")
                .to(transportEndpoint);
    }
}

