package com.ptasinska.controller;

import com.ptasinska.TxtFileUtils;
import com.ptasinska.data.Laptop;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class AppController implements Initializable {

    @FXML
    public VBox view;
    public TableView<Laptop> table;
    private final FileChooser chooser = new FileChooser();
    private final Pattern diskPattern = Pattern.compile("\\b\\d{1,4}GB|\\d{1,2}TB\\b");

    public void onImportButtonClick() {
        showDialog(Alert.AlertType.ERROR, "tytuł", "treść");
    }

    public void onExportButtonClick() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        chooser.setInitialFileName("export.txt");
        chooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("*.*","txt"));
        table.setPlaceholder(new Label("Brak danych"));
        String[] headers = TxtFileUtils.HEADERS;

        //create table columns
        for (int i = 0; i < headers.length; i++) {
            TableColumn column = new TableColumn();
            column.setText(headers[i]);
            if(i==0){
                column.setEditable(false);
                column.setPrefWidth(30);
                //compare strings as int
                column.setComparator(new Comparator() {
                    @Override
                    public int compare(Object o1, Object o2) {
                        return Integer.compare(
                                Integer.parseInt(String.valueOf(o1)),
                                Integer.parseInt(String.valueOf(o2))
                        );
                    }
                });
            }
            else{
                column.setEditable(true);
                column.setPrefWidth(80);
            }

            column.setCellFactory(TextFieldTableCell.forTableColumn());
            //cell edit
            column.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent>() {
                @Override
                public void handle(TableColumn.CellEditEvent event) {
                    Laptop laptop = (Laptop) event.getTableView().getSelectionModel().getSelectedItem();
                    int row = event.getTablePosition().getRow();
                    int col = event.getTablePosition().getColumn();
                    String newValue = (String) event.getNewValue();
                    boolean correct = true;
                    //validation
                    if(newValue.trim().isEmpty()){
                        correct=false;
                        showDialog(Alert.AlertType.WARNING, "Błąd", "Pole nie może być puste!");
                    }
                    else if(col==5 &&
                            !(newValue.trim().equalsIgnoreCase("nie") ||
                            newValue.trim().equalsIgnoreCase("tak") )){
                        correct=false;
                        showDialog(Alert.AlertType.WARNING, "Błąd", "Pole może zawierać jedynie wartości: [tak|nie]");
                    }
                    else if(col==10 && newValue.matches(diskPattern.pattern())){
                        correct=false;
                        showDialog(Alert.AlertType.WARNING, "Błąd", "Pole musi zawierać wartości wg. wzoru: [LICZBA][GB|TB]");
                    }

                    //revert changes
                    if(!correct){
                        laptop.setValueAt(col, event.getOldValue().toString());
                        //update table
                        event.getTableView().getItems().set(row, laptop);
                    }
                    //save changes
                    laptop.setValueAt(col, newValue);
                }
            });

            switch (i){
                case 0: column.setCellValueFactory(new PropertyValueFactory<>("id"));  break;
                case 1: column.setCellValueFactory(new PropertyValueFactory<>("manufacturer"));  break;
                case 2: column.setCellValueFactory(new PropertyValueFactory<>("diagonal"));  break;
                case 3: column.setCellValueFactory(new PropertyValueFactory<>("resolution"));  break;
                case 4: column.setCellValueFactory(new PropertyValueFactory<>("surface"));  break;
                case 5: column.setCellValueFactory(new PropertyValueFactory<>("touchscreen"));  break;
                case 6: column.setCellValueFactory(new PropertyValueFactory<>("cpu"));  break;
                case 7: column.setCellValueFactory(new PropertyValueFactory<>("cpuCores"));  break;
                case 8: column.setCellValueFactory(new PropertyValueFactory<>("clockspeed"));  break;
                case 9: column.setCellValueFactory(new PropertyValueFactory<>("ramSize"));  break;
                case 10: column.setCellValueFactory(new PropertyValueFactory<>("diskSize"));  break;
                case 11: column.setCellValueFactory(new PropertyValueFactory<>("diskType"));  break;
                case 12: column.setCellValueFactory(new PropertyValueFactory<>("gpu"));  break;
                case 13: column.setCellValueFactory(new PropertyValueFactory<>("gpuMemory"));  break;
                case 14: column.setCellValueFactory(new PropertyValueFactory<>("os"));  break;
                case 15: column.setCellValueFactory(new PropertyValueFactory<>("driveType"));  break;
            }

        }
        table.setEditable(true);
        //add row on double click
        table.setRowFactory(tableRow -> {
            TableRow<Laptop> row = new TableRow<>();
            row.setOnMouseClicked(e -> {
                if(e.getClickCount()==2 && row.isEmpty()){
                    addNewRow();
                }
            });
            return row;
        });

        //add shortcuts
        view.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if(keyEvent.getCode()== KeyCode.DELETE) removeRow();
                if(keyEvent.isControlDown() && keyEvent.getCode()==KeyCode.N) addNewRow();
            }
        });



    }

    private void removeRow() {
        table.getItems().remove(table.getSelectionModel().getSelectedItem());
        table.getSelectionModel().clearSelection();
        //update id
        for(int i=0;i<table.getItems().size();i++) table.getItems().get(i).setId(i+1);
    }

    private void addNewRow() {
        TablePosition position = table.getFocusModel().getFocusedCell();
        table.getSelectionModel().clearSelection();
        Laptop laptop = new Laptop(position.getRow()+1);
        table.getSelectionModel().select(position.getRow()+1, position.getTableColumn());
        table.scrollTo(laptop);
    }

    public void showDialog(Alert.AlertType type, String title, String text){
        Alert dialog = new Alert(type);
        dialog.setTitle(title);
        dialog.setContentText(text);
        dialog.show();
    }
}
