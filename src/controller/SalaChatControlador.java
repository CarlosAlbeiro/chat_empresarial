package controller;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import logica.Funciones;
import java.util.ArrayList;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 * 
 * @author Carlos Galeano
 * @author Ruben Garrido
 * 
 *  Description:
 *  Esta Se utiliza para controlar la lógica y la interacción de la interfaz gráfica de usuario de una sala de chat.
 *  La clase tiene variables globales y métodos para realizar operaciones de conexión y comunicación con un servidor de chat.
 *  La clase interna Entrada implementa la interfaz Runnable y se utiliza como hilo para recibir y mostrar las respuestas del servidor de chat.
 *  También maneja la recepción de una lista de usuarios activos y la muestra en el ListView llamado listActivos.
 */
 


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
    private TextField txtNombre;
    @FXML
    //variables globales
    private ListView<String> listActivos;
    public Funciones f=new Funciones();
    private String nombreUsuario;
	private Socket cliente;
	private ObjectInputStream listaEntrante;
 
    
    public SalaChatControlador() {
    	   
    }

    //Metodo conectar al servidor 
    @FXML
    void Conectar(ActionEvent event) {
    	nombreUsuario= txtNombre.getText();
    	
    	
    	//Validacion de campos De nombre 
    	if (nombreUsuario == null || nombreUsuario.trim().isEmpty()) {
            // El TextField está vacío
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Campo Vacío");
            alert.setHeaderText(null);
            alert.setContentText("El campo está vacío. Por favor, ingresa un nombre.");
            alert.showAndWait();
        } else {
        
        	System.out.println("Me voy a conectar");
        	cliente=f.conectar();
        	Thread hiloRespuestas= new Thread(new Entrada());
        	hiloRespuestas.setDaemon(true);
        	hiloRespuestas.start();
        	botnConectar.setDisable(true);
     	    txtNombre.setDisable(true);
        	
    		f.ingreso(nombreUsuario);
           
        }
    	
    	//Validacion en proceso de funcionalidad
//    	if(cliente.isConnected()) {
//    		
//    		
//		}else {
//			Alert alert = new Alert(Alert.AlertType. ERROR);
//            alert.setHeaderText(null);
//            alert.setTitle("Error");
//            alert.setContentText("Error nombre repetido");
//            alert.showAndWait();
//		}
    	
    	
    	//Actvia la teclado  "enter"
    	txtEscribirMensajes.setOnKeyPressed(new  EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				
				if (event.getCode() == KeyCode.ENTER) {
					
					
					String mensaje = txtEscribirMensajes.getText();
			        f.enviarMensaje(mensaje);
			        txtEscribirMensajes.clear();

                }
				
			}
		});
		

    }
    
//----------------------------------------metodo enviar mensaje------------------------------------------------------    
    
    public void EnviarMensaje(ActionEvent event) {
    	
    	
        String mensaje = txtEscribirMensajes.getText();
        
        
        if (mensaje == null || mensaje.trim().isEmpty()) {
            // El TextField está vacío
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Campo Vacío");
            alert.setHeaderText(null);
            alert.setContentText("El campo está vacío. Por favor, ingresa un mensaje.");
            alert.showAndWait();
        } else {
        
        	 
            f.enviarMensaje(mensaje);
            txtEscribirMensajes.clear();
          
            txtEscribirMensajes.clear();
        	
//            // El TextField contiene texto
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("Campo No Vacío");
//            alert.setHeaderText(null);
//            alert.setContentText("El campo contiene texto: " + mensaje);
//            alert.showAndWait();
        }
                
        
       

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
			
					listaEntrante=new ObjectInputStream(cliente.getInputStream());
					
					Object objetoRecibido =listaEntrante.readObject();
		
					if (objetoRecibido instanceof ArrayList<?>) {
					    ArrayList<String> listaRecibida = (ArrayList<String>) objetoRecibido;
					    // Convierte el ArrayList a ObservableList
					    ObservableList<String> observableList = FXCollections.observableArrayList(listaRecibida);
					    
						Platform.runLater(()->{
							// Agrega la lista al ListView
						    listActivos.setItems(observableList);
						});
					}
				}
			} catch (Exception e) {
				try {
					cliente.close();
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// TODO: handle exception
			}
    	}
    }
    
    
}