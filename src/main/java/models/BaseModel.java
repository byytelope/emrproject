package models;

import java.lang.reflect.Field;
import java.util.List;

public class BaseModel {
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName()).append("{");

        Field[] fields = getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);
            sb.append(fields[i].getName()).append("=");

            try {
                Object value = fields[i].get(this);
                if (value instanceof String) {
                    sb.append("\"").append(value).append("\"");
                } else {
                    sb.append(value);
                }
            } catch (IllegalAccessException e) {
                sb.append("N/A");
            }

            if (i < fields.length - 1) {
                sb.append(", ");
            }
        }

        sb.append("}");
        return sb.toString();
    }

    public String toCsvHeader() {
        StringBuilder sb = new StringBuilder();

        try {
            Field[] fields = this.getClass().getDeclaredFields();
            for (Field field : fields) {
                sb.append(field.getName()).append(",");
            }

            if (sb.length() > 0) {
                sb.setLength(sb.length() - 1);
                sb.append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sb.toString();
    }

    public String toCsvString() {
        StringBuilder sb = new StringBuilder();

        try {
            Field[] fields = this.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                Object value = field.get(this);
                String formattedValue = null;

                if (value != null) {
                    if (value instanceof List) {
                        @SuppressWarnings("unchecked")
                        List<String> listValue = (List<String>) value;
                        formattedValue = '"' + String.join(",", listValue) + '"';
                    } else {
                        formattedValue = '"' + value.toString() + '"';
                    }
                } else {
                    formattedValue = "";
                }

                sb.append(formattedValue).append(",");
            }

            if (sb.length() > 0) {
                sb.setLength(sb.length() - 1);
                sb.append("\n");
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }
}

// if (value instanceof List) {
// formattedValue = '"' + String.join(",", (List<String>) value);
// } else {
// }