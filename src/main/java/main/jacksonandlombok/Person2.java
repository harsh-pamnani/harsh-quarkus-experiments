package main.jacksonandlombok;

import java.util.Optional;
import lombok.Builder;

@Builder(toBuilder = true)
public record Person2(String name, Optional<Integer> age) {}
