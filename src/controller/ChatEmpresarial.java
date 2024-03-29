package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Esta clase arranca la ventana o interfaz de usuario.
 * @author Ruben Garrido
 * @author Carlos Galeano
 *
 */
public class ChatEmpresarial extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/view/SalaChat.fxml"));
        primaryStage.setTitle("SALA");
        primaryStage.setScene(new Scene(root));
        
       
        primaryStage.show();
        
    }

    public static void main(String[] args) {
        launch(args);
    }
}
