package main.gsonToReadJsonAndConvertToPOJO;

import lombok.ToString;

@ToString
public class CityProvince {
    public final String city;
    public final String province;

    public CityProvince(String city, String province) {
        this.city = city;
        this.province = province;
    }
}
