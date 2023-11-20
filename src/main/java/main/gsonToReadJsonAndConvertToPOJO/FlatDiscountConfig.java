package main.gsonToReadJsonAndConvertToPOJO;

import lombok.ToString;

import java.util.Set;

@ToString
public class FlatDiscountConfig {
    public final boolean availableForAllCities;
    public final Set<CityProvince> availableCities;

    public FlatDiscountConfig(boolean availableForAllCities,
                              Set<CityProvince> availableCities) {
        this.availableCities = availableCities;
        this.availableForAllCities = availableForAllCities;
    }
}
