package eu.poliks.pspigot.util;

public class FloatUtil {

    public static boolean isFloat(String arg) {
        try {
            Float.parseFloat(arg);
        } catch(NumberFormatException e) {
            return false;
        }
        return true;
    }
}
