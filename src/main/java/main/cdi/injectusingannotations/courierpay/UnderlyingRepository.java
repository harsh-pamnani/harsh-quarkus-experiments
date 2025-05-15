package main.cdi.injectusingannotations.courierpay;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import jakarta.inject.Qualifier;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Qualifier @Target({ElementType.TYPE, FIELD, PARAMETER, METHOD})
@Retention(RUNTIME)
public @interface UnderlyingRepository {}
