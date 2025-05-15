package main.reflection.reflectiontotestdisabled;

// @RegisterForReflection
public enum Province {
    AB,
    BC,
    ON,
    UNKNOWN,

    @Deprecated
    AK,
    @Deprecated
    IA,
    @Deprecated
    NY;

    public static boolean isDeprecated(String province) {
        try {
            return Province.class.getField(province).isAnnotationPresent(Deprecated.class);
        } catch (NoSuchFieldException e) {
            return false;
        }
    }
}
