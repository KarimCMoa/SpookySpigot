package eu.poliks.pspigot.util;

public class DoubleUtil {

    public static boolean isDouble(String arg) {
        try {
            Double.parseDouble(arg);
        } catch(NumberFormatException e) {
            return false;
        }
        return true;
    }
}
