package controller;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import logica.Funciones;

import java.util.ArrayList;
import java.util.List;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SalaChatControlador  {

	@FXML
    private Button botnConectar;
	@FXML
    private Button botnEnviar;

    @FXML
    private TextField txtEscribirMensajes;

    @FXML
    private TextArea txtMostrarChat;

    @FXML
    private TextArea txtMostrarActivos;
    @FXML
    private TextField txtNombre;
  
    public Funciones f=new Funciones();
    private String nombreUsuario;
 
    
    public SalaChatControlador() {
    	   
    }

    @FXML
    void Conectar(ActionEvent event) {
    	nombreUsuario= txtNombre.getText();
    	System.out.println("Me voy a conectar");
    	f.conectar();
    	
		f.ingreso(nombreUsuario);

    }
    
    public void EnviarMensaje(ActionEvent event) {
        String mensaje = txtEscribirMensajes.getText();
        f.enviarMensaje(mensaje);
       
        txtEscribirMensajes.clear();

    }
    

//	public void closeWindows() {
//		 try {   
//             FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/login.fxml"));
//              
//              Parent root = loader.load();
//     
//      
//              //Controlador de la vista
//              LoginControlador controlador = loader.getController();//poner la clase del controlador 
////              controlador.setNombreUsuario(nombreUsuario);
//              Scene scene = new Scene(root);
//              Stage stage = new Stage ();
//             
//              stage.setScene(scene);
//              stage.show();
//            
//              //Stage cerrarPantalla = (Stage) this.botonRegistrar.getScene().getWindow();
//              //cerrarPantalla.close();
//		  } catch (IOException ex) {
//	            System.out.println("Errorrr..");
//	        }
//		
//	}
//	
	
	
}