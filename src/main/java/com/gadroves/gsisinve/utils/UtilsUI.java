package com.gadroves.gsisinve.utils;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.stream.IntStream;

/**
 * Created by geykel on 09/05/2015.
 */
public class UtilsUI {
    public static void setUpColumnsValues(String[] properties, TableColumn[] cols) {
        IntStream.range(0, cols.length)
                .parallel()
                .forEach(i -> cols[i].setCellValueFactory( new PropertyValueFactory<>(properties[i])) );
    }

    public static <T> void textFieldFilter(TableView<T> table, ObservableList<T> data ,TextField text, CallablePredicate<T, String> pre) {
        FilteredList<T> filteredData = new FilteredList<>(data, p -> true);
        SortedList<T> sortedData = new SortedList<>(filteredData);
        text.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(t -> pre.evaluate(t, newValue));
        });
        sortedData.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sortedData);
    }
}
