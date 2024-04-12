package main.reflectiontotestdisabled;

import io.quarkus.runtime.annotations.QuarkusMain;

/*
    IMPORTANT
    Uncomment @QuarkusMain from below and comment it everywhere else in the project because there can be only one main in Quarkus

    1. Generate the native-image using "native" profile (just run clean install with native profile)
    2. That will generate the native image in target folder
    3. Run the native image using "./target/reflection-1.0.0-SNAPSHOT-runner main.reflection.Exp2"
    4. Since Province is not registered for reflection, it will return `false` even for deprecated provinces (i.e. NY)
    5. Just add @RegisterForReflection in Province and run the native image again to see the results. It should be `true` now.
 */
//@QuarkusMain
public class ProvinceMain {
    public static void main(String[] args) {
        System.out.println(Province.isDeprecated("ON"));

        // Expected: true, but Actual: false
        System.out.println(Province.isDeprecated("NY"));
    }
}
