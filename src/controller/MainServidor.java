package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainServidor extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
    	Parent root = FXMLLoader.load(getClass().getResource("../view/Servidor.fxml"));
        primaryStage.setTitle("Servidor");
        primaryStage.setScene(new Scene(root));
        
        primaryStage.setX(960);
        primaryStage.setY(40);
        
        primaryStage.show();
        
    }

    public static void main(String[] args) {
        launch(args);
    }
}