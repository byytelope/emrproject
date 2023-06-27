package utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import models.BaseModel;

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

    public static void initializeTableColumns(List<String> columnNames, TableView<ObservableList<String>> tableView) {
        tableView.getColumns().clear();

        for (String columnName : columnNames) {
            TableColumn<ObservableList<String>, String> column = new TableColumn<>(columnName);
            final int columnIndex = columnNames.indexOf(columnName);

            column.setCellValueFactory(cellData -> {
                ObservableList<String> row = cellData.getValue();
                if (columnIndex >= 0 && columnIndex < row.size()) {
                    return new SimpleStringProperty(row.get(columnIndex));
                } else {
                    return new SimpleStringProperty("");
                }
            });

            tableView.getColumns().add(column);
        }
    }

    public static <T> void initializeTableRows(List<T> list, TableView<ObservableList<String>> table) {
        if (!list.isEmpty()) {
            List<ObservableList<String>> rows = new ArrayList<>();

            for (T li : list) {
                if (li instanceof BaseModel) {
                    BaseModel item = (BaseModel) li;
                    ObservableList<String> row = FXCollections
                            .observableArrayList(CsvHandler.parseCsvLine(item.toCsvString()));
                    row.remove(0);
                    rows.add(row);
                }
            }

            table.getItems().addAll(rows);
        }
    }
}
