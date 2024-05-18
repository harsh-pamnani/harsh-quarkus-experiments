package main.restclients.dummyjsonrestclient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

// We need to add this, otherwise Jackson will look for all properties, and it will even if a single property is missing from POJO.
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {
    public String id;
    public String title;
    public String description;
    public List<String> images;
}