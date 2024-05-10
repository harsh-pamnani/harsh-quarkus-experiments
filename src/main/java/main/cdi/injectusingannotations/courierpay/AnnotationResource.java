package main.cdi.injectusingannotations.courierpay;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/annotation")
public class AnnotationResource {

    @Inject
    DeliveryZonesService deliveryZonesService;

    @GET
    public String get() {
        deliveryZonesService.save();
        return "Hello from annotation resource";
    }
}
