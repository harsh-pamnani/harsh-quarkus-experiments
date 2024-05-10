package main.cdi.injectusingannotations.courierpay;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DeliveryZonesService {

    private DeliveryZonesRepository deliveryZonesRepository;

    public DeliveryZonesService(@HybridRepository DeliveryZonesRepository deliveryZonesRepository) {
        this.deliveryZonesRepository = deliveryZonesRepository;
    }

    public void save() {
        deliveryZonesRepository.save();
    }
}
