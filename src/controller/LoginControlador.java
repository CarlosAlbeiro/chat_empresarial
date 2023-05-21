package controller;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import logica.Funciones;



public class LoginControlador  {
	

		@FXML
		private Button botonIngresar;
		//variables
		@FXML
		private TextField cajaUsuario;
		
	    public String nombreUsuario;
	    

	    @FXML
	    void IngresarAlChat(ActionEvent event) {
	    	
	    	nombreUsuario = cajaUsuario.getText();
	    	SalaChatControlador sala= new SalaChatControlador();
	    	
	  
	    	
//	    	if () {
	    	
	    		FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/SalaChat.fxml"));
	            
	            Parent root;
	            try {
	            	root = loader.load();
				
		            //Controlador de la vista
		            SalaChatControlador controlador = loader.getController();
		            Scene scene = new Scene(root);
		            Stage stage = new Stage ();
		            stage.initModality(Modality.APPLICATION_MODAL);
		            stage.setScene(scene);
		            stage.show();
		            
//		            stage.setOnCloseRequest(e -> controlador.closeWindows());
		            
		            Stage cerrarPantalla = (Stage) this.botonIngresar.getScene().getWindow();
		            cerrarPantalla.close();

	            } catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
	            }
//			} else {

//			}
	    	
	    	
	    	//SalaChatControlador nombre = new SalaChatControlador(nombreUsuario);
	    
			
	    }


}