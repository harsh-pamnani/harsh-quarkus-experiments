package main.cdi.injectusingannotations.courierpay;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
@UnderlyingRepository
public class JooqDeliveryZonesRepository implements DeliveryZonesRepository {
    @Override
    public void save() {
        System.out.println("Saving from Jooq repo");
    }
}
