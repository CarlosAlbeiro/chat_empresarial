package controller;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
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
	private Socket cliente;
 
    
    public SalaChatControlador() {
    	   
    }

    @FXML
    void Conectar(ActionEvent event) {
    	nombreUsuario= txtNombre.getText();
    	System.out.println("Me voy a conectar");
    	cliente=f.conectar();
    	Thread hiloRespuestas= new Thread(new Entrada());
    	hiloRespuestas.start();
    	
		f.ingreso(nombreUsuario);

    }
    
    public void EnviarMensaje(ActionEvent event) {
        String mensaje = txtEscribirMensajes.getText();
        f.enviarMensaje(mensaje);
//        txtMostrarChat.appendText(mensaje);
       
        txtEscribirMensajes.clear();

    }
    
   
    
    public class Entrada implements Runnable {
    	public void run() {
    		try {
				String mensaje;
				InputStream inStream =null;
				while (true) {
					inStream = cliente.getInputStream();
					DataInputStream respuestaServidor= new DataInputStream(inStream);
					String respuesta = respuestaServidor.readUTF();
					txtMostrarChat.appendText(respuesta+"\n");
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
    	}
    }
    
    
}