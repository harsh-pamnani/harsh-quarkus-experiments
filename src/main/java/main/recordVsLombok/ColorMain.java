package main.recordVsLombok;

public class ColorMain {
    public static void main(String[] args) {
        // Comparison @Data and @Value annotations
        Color1 c1 = new Color1(255, 0, 0);
        System.out.println(c1.getHexString());
        c1.setRed(155); // Setter will work because it is @Data and it is mutable
        System.out.println(c1.getHexString());

        Color2 c2 = new Color2(255, 0, 0);
        System.out.println(c2.getHexString());
        //        c2.setRed(155); -- Won't work because @Value is immutable
        System.out.println(c2.getRed());

        // Comparison @Value along with NONE access VS. record (i.e. granular control on fields' getter)
        ColorValueWithAccessLevelNone cvo = new ColorValueWithAccessLevelNone(255, 0, 0);
        //        cvo.getRed(); -- Will fail because @Getter is set with access level NONE.

        ColorRecord cr = new ColorRecord(255, 0, 0);
        System.out.println(cr.red()); // Getter will work because record generates getter by default
        //        cr.setRed(155); // Setter won't work because record are immutable by default
    }
}
