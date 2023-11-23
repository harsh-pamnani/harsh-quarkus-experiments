package main.jacksonandlombok;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class Color2 {

    private int red;
    private int green;
    private int blue;

    public String getHexString() {
        return String.format("#%02X%02X%02X", red, green, blue);
    }
}