package main.dependencyinjection;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {

    @Inject
    private ClassWithSingletonScoped classWithSingletonScoped;

    @Inject
    private ClassWithApplicationScoped classWithApplicationScoped;

    @Inject
    private ClassWithRequestScoped classWithRequestScoped;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        // https://stackoverflow.com/questions/18378608/why-is-constructor-of-cdi-bean-class-called-more-than-once
        System.out.println(classWithSingletonScoped.greetUser("Harsh")); // Only Single instance created
        System.out.println(classWithApplicationScoped.greetUser("Harsh")); // Only Single instance created lazily, i.e. when a method is invoked upon an injected instance for the first time.
        System.out.println(classWithRequestScoped.greetUser("Harsh")); // Instance created for each request
        return "Hello from RESTEasy Reactive";
    }
}
