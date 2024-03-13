package main.jsoncreatorjsonproperty;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.quarkus.runtime.annotations.RegisterForReflection;

@JsonInclude(Include.NON_NULL)
@RegisterForReflection
public record ErrorResponseBodyWithType(String error, String message, String type) {
}
