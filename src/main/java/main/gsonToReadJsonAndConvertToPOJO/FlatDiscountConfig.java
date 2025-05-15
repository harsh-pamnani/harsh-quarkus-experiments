package main.gsonToReadJsonAndConvertToPOJO;

import java.util.Set;
import lombok.ToString;

@ToString
public class FlatDiscountConfig {
    public final boolean availableForAllCities;
    public final Set<CityProvince> availableCities;

    public FlatDiscountConfig(boolean availableForAllCities, Set<CityProvince> availableCities) {
        this.availableCities = availableCities;
        this.availableForAllCities = availableForAllCities;
    }
}
