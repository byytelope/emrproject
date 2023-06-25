package utils;

import java.util.regex.Pattern;

public class MiscUtils {
    public static boolean isInteger(String strInt) {
        if (strInt == null) {
            return false;
        }
        try {
            Integer.parseInt(strInt);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static boolean isDouble(String strDouble) {
        if (strDouble == null) {
            return false;
        }
        try {
            Double.parseDouble(strDouble);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static boolean isDate(String date) {
        String datePattern = "^(0[1-9]|1\\d|2\\d|3[01])/(0[1-9]|1[0-2])/\\d{4}$";
        Pattern regex = Pattern.compile(datePattern);

        return regex.matcher(date).matches();
    }
}
