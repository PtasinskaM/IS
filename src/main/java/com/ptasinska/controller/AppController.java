package com.ptasinska.controller;

import com.ptasinska.data.Laptop;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.net.URL;
import java.util.ResourceBundle;

public class AppController implements Initializable {

    @FXML
    public VBox view;
    public TableView<Laptop> table;
    private final FileChooser chooser = new FileChooser();

    public void onImportButtonClick() {

    }

    public void onExportButtonClick() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
