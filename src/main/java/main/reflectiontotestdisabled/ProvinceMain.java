package main.reflectiontotestdisabled;

import io.quarkus.runtime.annotations.QuarkusMain;

//@QuarkusMain
public class ProvinceMain {
    public static void main(String[] args) {
        System.out.println(Province.isDeprecated("ON"));

        // Since Province is not registered for reflection, it will return `false` for deprecated provinces (i.e. NY)
        // Just uncomment @RegisterForReflection from Province and run the native image again to see the results. It should be `true` now.
        System.out.println(Province.isDeprecated("NY"));
    }
}
