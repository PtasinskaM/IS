package com.ptasinska.controller;

import com.ptasinska.FileUtils;
import com.ptasinska.data.Laptop;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.File;
import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class AppController implements Initializable {

    @FXML
    public VBox view;
    @FXML
    public TableView<Laptop> table;
    private final FileChooser chooser = new FileChooser();
    private final Pattern diskPattern = Pattern.compile("\\b\\d{1,4}GB|\\d{1,2}TB\\b");
    @FXML
    public void importFromTxt() {
        chooser.setSelectedExtensionFilter(chooser.getExtensionFilters().get(1));
        importFromFile();
    }
    @FXML
    public void importFromXml() {
        chooser.setSelectedExtensionFilter(chooser.getExtensionFilters().get(2));
        importFromFile();
    }
    @FXML
    public void exportToTxt() {
        chooser.setSelectedExtensionFilter(chooser.getExtensionFilters().get(1));
        chooser.setInitialFileName("export.txt");
        exportToFile();
    }
    @FXML
    public void exportToXml() {
        chooser.setSelectedExtensionFilter(chooser.getExtensionFilters().get(2));
        chooser.setInitialFileName("export.xml");
        exportToFile();
    }

    public void importFromFile() {
        File file = chooser.showOpenDialog(new Stage());
        String filePath = "";
        String ext = "";
        if(file!=null){
            filePath = file.getAbsolutePath();
            ext = filePath.substring(filePath.length()-3);

            if(ext.equals("txt")){
                List<Laptop> laptops = FileUtils.readFromFile(filePath);
                table.getItems().setAll(laptops);
            }
            else if(ext.equals("xml")){
                List<Laptop> laptops = FileUtils.readFromXml(file);
                table.getItems().setAll(laptops);
            }
        }
    }


    public void exportToFile() {
        File file = chooser.showSaveDialog(new Stage());
        String filePath = "";
        String ext = "";
        if(file!=null){
            filePath = file.getAbsolutePath();
            ext = filePath.substring(filePath.length()-3);
            List<Laptop> laptops = table.getItems();
            boolean success = false;

            if(ext.equals("txt")) success = FileUtils.saveToFile(filePath, laptops);
            else if(ext.equals("xml")) success = FileUtils.saveToXml(file, laptops);

            if (success) {
                showDialog(Alert.AlertType.INFORMATION, "Zapisano", "Pomyślnie eksportowano do pliku");
            } else {
                showDialog(Alert.AlertType.ERROR, "Błąd", "Wystąpił błąd podczas eksportu do pliku");
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        chooser.setInitialFileName("export.txt");
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Wszystkie pliki", "*.txt","*.xml"),
                new FileChooser.ExtensionFilter("Pliki tekstowe (*.txt)", "*.txt"),
                new FileChooser.ExtensionFilter("Pliki xml (*.xml)", "*.xml")
        );

        table.setPlaceholder(new Label("Brak danych"));
        String[] headers = FileUtils.HEADERS;

        //create table columns
        for (int i = 0; i < headers.length; i++) {
            TableColumn column = new TableColumn<>();
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
                column.setPrefWidth(89);
            }

            int temp = i;
            column.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Laptop, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue call(TableColumn.CellDataFeatures<Laptop,String> cdf) {
                    switch (temp){
                        case 0: return new SimpleStringProperty(String.valueOf(cdf.getValue().getId()));
                        case 1: return new SimpleStringProperty(String.valueOf(cdf.getValue().getManufacturer()));
                        case 2: return new SimpleStringProperty(String.valueOf(cdf.getValue().getScreen().getSize()));
                        case 3: return new SimpleStringProperty(String.valueOf(cdf.getValue().getScreen().getResolution()));
                        case 4: return new SimpleStringProperty(String.valueOf(cdf.getValue().getScreen().getType()));
                        case 5: return new SimpleStringProperty(String.valueOf(cdf.getValue().getScreen().getTouch()));
                        case 6: return new SimpleStringProperty(String.valueOf(cdf.getValue().getProcessor().getName()));
                        case 7: return new SimpleStringProperty(String.valueOf(cdf.getValue().getProcessor().getPhysicalCores()));
                        case 8: return new SimpleStringProperty(String.valueOf(cdf.getValue().getProcessor().getClockSpeed()));
                        case 9: return new SimpleStringProperty(String.valueOf(cdf.getValue().getRamSize()));
                        case 10: return new SimpleStringProperty(String.valueOf(cdf.getValue().getDisc().getStorage()));
                        case 11: return new SimpleStringProperty(String.valueOf(cdf.getValue().getDisc().getType()));
                        case 12: return new SimpleStringProperty(String.valueOf(cdf.getValue().getGraphicCard().getName()));
                        case 13: return new SimpleStringProperty(String.valueOf(cdf.getValue().getGraphicCard().getMemory()));
                        case 14: return new SimpleStringProperty(String.valueOf(cdf.getValue().getOs()));
                        case 15: return new SimpleStringProperty(String.valueOf(cdf.getValue().getDriveType()));
                        default: return  new SimpleStringProperty();
                    }
                }
            });




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
                    else if(col==10 && !newValue.matches(diskPattern.pattern())){
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
                    else laptop.setValueAt(col, newValue);
                }
            });

            table.getColumns().add(column);
        }
        table.setEditable(true);
        //add row on double click
        table.setRowFactory(tableRow -> {
            TableRow<Laptop> row = new TableRow<>();
            row.setOnMouseClicked(e -> {
                if(e.getClickCount()==2 && (row.isEmpty())){
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
        int row = table.getItems().size();
        Laptop laptop = new Laptop(row+1);
        table.getSelectionModel().clearSelection();
        table.getItems().add(laptop);
        table.getSelectionModel().select(row, position.getTableColumn());
        table.scrollTo(laptop);
    }

    public void showDialog(Alert.AlertType type, String title, String text){
        Alert dialog = new Alert(type);
        dialog.setTitle(title);
        dialog.setContentText(text);
        dialog.show();
    }
}
