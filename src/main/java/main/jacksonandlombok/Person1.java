package main.jacksonandlombok;

import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@EqualsAndHashCode
@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class Person1 {
    public final String name;

    @Builder.Default
    public final Optional<Integer> age = Optional.empty();
}
