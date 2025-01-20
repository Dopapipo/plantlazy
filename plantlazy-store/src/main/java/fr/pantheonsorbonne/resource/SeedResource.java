package fr.pantheonsorbonne.resource;

import fr.pantheonsorbonne.dto.DailySeedOfferDTO;
import fr.pantheonsorbonne.dto.PurchaseRequestDTO;
import fr.pantheonsorbonne.entity.SeedEntity;
import fr.pantheonsorbonne.entity.enums.PlantType;
import fr.pantheonsorbonne.services.SeedService;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Path("/store/seeds")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SeedResource {

    @Inject
    SeedService seedService;

    @GET
    public List<DailySeedOfferDTO> getAvailableSeeds() {
        List<SeedEntity> seeds = seedService.getAvailableSeeds();
        return seeds.stream()
                .map(seed -> new DailySeedOfferDTO(seed.getType(), seed.getQuantity(), seed.getPrice()))
                .collect(Collectors.toList());
    }

    @GET
    @Path("/pricing-and-stock")
    public Response getSeedPricingAndStock() {
        List<PurchaseRequestDTO> pricingAndStock = seedService.getSeedPricingAndStock();
        return Response.ok(pricingAndStock).build();
    }


    @POST
    @Path("/daily-offer")
    public Response updateDailySeedOffer() {
        seedService.updateDailySeedOffer();
        return Response.ok("Daily seed offer updated").build();
    }

    @POST
    @Path("/sell")
    public Response sellSeed(@QueryParam("quantity") int quantity) {
        try {
            List<SeedEntity> availableSeeds = seedService.getAvailableSeeds().stream()
                    .sorted(Comparator.comparingDouble(SeedEntity::getPrice)) // Trier par prix croissant
                    .toList();

            int remainingQuantity = quantity;
            double totalAmount = 0;
            List<PurchaseRequestDTO> purchasedSeeds = new ArrayList<>();

            for (SeedEntity seed : availableSeeds) {
                if (remainingQuantity <= 0) {
                    break;
                }

                int soldQuantity = Math.min(seed.getQuantity(), remainingQuantity);
                totalAmount += soldQuantity * seed.getPrice();
                remainingQuantity -= soldQuantity;

                // Ajouter au DTO des achats
                if (soldQuantity > 0) {
                    purchasedSeeds.add(new PurchaseRequestDTO(seed.getType(), soldQuantity, soldQuantity * seed.getPrice()));
                }

                // Mettre à jour la quantité en stock
                seedService.sellSeed(seed.getType(), soldQuantity);
            }

            // Construire la réponse finale avec les détails de l'achat
            return Response.ok(purchasedSeeds).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("An unexpected error occurred").build();
        }
    }
}
