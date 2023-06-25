package models;

import java.lang.reflect.Field;
import java.util.List;

abstract public class BaseModel {
    abstract public String getFileName();

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName()).append("{");

        Class<?> currentClass = this.getClass().getSuperclass();
        for (int i = 0; i < 2; i++) {
            Field[] fields = currentClass.getDeclaredFields();
            for (int j = 0; j < fields.length; j++) {
                fields[j].setAccessible(true);
                sb.append(fields[j].getName()).append("=");

                try {
                    Object value = fields[j].get(this);
                    if (value instanceof String) {
                        sb.append("\"").append(value).append("\"");
                    } else {
                        sb.append(value);
                    }
                } catch (IllegalAccessException e) {
                    sb.append("N/A");
                }

                if (j < fields.length - 1) {
                    sb.append(", ");
                }
            }
            currentClass = this.getClass();
        }

        sb.append("}");
        return sb.toString();
    }

    public String toCsvHeader() {
        StringBuilder sb = new StringBuilder();

        Class<?> currentClass = this.getClass().getSuperclass();
        for (int i = 0; i < 2; i++) {
            Field[] fields = currentClass.getDeclaredFields();
            for (Field field : fields) {
                sb.append(field.getName()).append(",");
            }
            currentClass = this.getClass();
        }

        if (sb.length() > 0) {
            sb.setLength(sb.length() - 1);
            sb.append("\n");
        }

        return sb.toString();
    }

    public String toCsvString() {
        StringBuilder sb = new StringBuilder();

        Class<?> currentClass = this.getClass().getSuperclass();
        for (int i = 0; i < 2; i++) {
            Field[] fields = currentClass.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                try {
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
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            currentClass = this.getClass();
        }

        if (sb.length() > 0) {
            sb.setLength(sb.length() - 1);
            sb.append("\n");
        }

        return sb.toString();
    }
}
