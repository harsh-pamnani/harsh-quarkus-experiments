package main.cdi.injectusingannotations.courierpay;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
@HybridRepository
public class HybridDeliveryZonesRepository implements DeliveryZonesRepository {
    private final DeliveryZonesRepository deliveryZonesRepository;
    private final Cache deliveryZonesCache;

    public HybridDeliveryZonesRepository(@UnderlyingRepository DeliveryZonesRepository deliveryZonesRepository,
                                         Cache deliveryZonesCache) {
        this.deliveryZonesRepository = deliveryZonesRepository;
        this.deliveryZonesCache = deliveryZonesCache;
    }

    @Override
    public void save() {
        System.out.println("Saving from Hybrid repo");
        deliveryZonesRepository.save();
        deliveryZonesCache.save();
    }
}
