package main.gsonToReadJsonAndConvertToPOJO;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class FlatDiscountConfigMain {
    public static void main(String[] args) throws FileNotFoundException {
        Gson gson = new Gson();
        FileReader fileReader = new FileReader("src/main/resources/flat_discount_config.json");
        FlatDiscountConfig flatDiscountConfig = gson.fromJson(fileReader, FlatDiscountConfig.class);
        System.out.println(flatDiscountConfig.availableForAllCities);
        System.out.println(flatDiscountConfig.availableCities);

    }
}
