package main.jacksonAndLombok;

import lombok.Builder;

import java.util.Optional;

@Builder(toBuilder = true)
public record Person2(String name, Optional<Integer> age) {
}
