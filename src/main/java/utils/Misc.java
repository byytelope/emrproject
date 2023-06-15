package utils;

public class Misc {
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int integer = Integer.parseInt(strNum);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
