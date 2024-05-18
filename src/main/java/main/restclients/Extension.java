package main.restclients;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

// We need to add this, otherwise Jackson will look for all properties, and it will even if a single property is missing from POJO.
@JsonIgnoreProperties(ignoreUnknown = true)
public class Extension {
    public String id;
    public String name;
    public String shortName;
    public List<String> keywords;
}