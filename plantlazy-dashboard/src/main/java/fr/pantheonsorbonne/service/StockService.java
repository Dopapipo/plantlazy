package fr.pantheonsorbonne.service;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import fr.pantheonsorbonne.dto.InitMoneyDTO;

@RegisterRestClient(configKey = "stock-service")
@Path("/api")
public interface StockService {

    @POST
    @Path("/stock-init")
    @Consumes(MediaType.APPLICATION_JSON)
    void initializeMoney(InitMoneyDTO money);
}