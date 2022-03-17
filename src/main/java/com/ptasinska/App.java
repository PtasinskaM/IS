package com.ptasinska;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/com/ptasinska/layout/app-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1366, 768);
        primaryStage.setTitle("Integracja systemów - Monika Ptasińska");

        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
