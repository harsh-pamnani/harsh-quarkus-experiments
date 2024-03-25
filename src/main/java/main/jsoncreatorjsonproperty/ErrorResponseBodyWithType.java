package main.jsoncreatorjsonproperty;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.quarkus.runtime.annotations.RegisterForReflection;

import java.util.Optional;

@JsonInclude(Include.NON_ABSENT)
@RegisterForReflection
public record ErrorResponseBodyWithType(String error, String message, String type, Optional<Integer> discount) {
}
