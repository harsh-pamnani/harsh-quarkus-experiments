package main.reflection;

import io.quarkus.runtime.annotations.QuarkusMain;

import java.lang.reflect.InvocationTargetException;

/*
    IMPORTANT
    Uncomment @QuarkusMain from below and comment it everywhere else in the project because there can be only one main in Quarkus

    1. Generate the native-image using "native" profile (just run clean install with native profile)
    2. That will generate the native image in target folder
    3. Run the native image using "./target/reflection-1.0.0-SNAPSHOT-runner main.reflection.Exp2"
    4. Since Exp2 is not registered for reflection, it will throw an exception
    5. Just uncomment @RegisterForReflection from Exp2 and run the native image again. It should work now.
    6. Notice that Exp1 doesn't need to be registered for reflection because it is hard-coded in Class.forName. So, it will automatically registered for reflection
 */
//@QuarkusMain
public class ReflectionMain {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        var clazz1 = Class.forName("main.reflection.Exp1");
        var method1 = clazz1.getDeclaredMethod("greetings");
        var result1 = method1.invoke(null);
        System.out.println(result1);

        var clazz2 = Class.forName(args[0]);
        var method2 = clazz2.getDeclaredMethod("greetings");
        var result2 = method2.invoke(null);
        System.out.println(result2);
    }
}
