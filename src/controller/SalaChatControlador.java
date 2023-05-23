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
import javax.swing.JOptionPane;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 * 
 * @author Carlos Galeano
 * @author Ruben Garrido
 * 
 *  Description:
 *  Esta actua como cliente 
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
    
    
    /**
     * Me perimite desactivar el campo de texto para que no se utilice 
     */

    public void initialize() {
        // Desactivar el campo de texto al iniciar la interfaz
        txtEscribirMensajes.setDisable(true);
    }

    //Metodo conectar al servidor 
    @FXML
    /**
     * Evento que permite conectar con el servidor 
     * @param event
     */
    void Conectar(ActionEvent event) {
    	nombreUsuario= txtNombre.getText();
    	// Activar campo de text mensaje
    	txtEscribirMensajes.setDisable(false);
    	
    	//Validacion de campos De nombre 
    	if (nombreUsuario == null || nombreUsuario.trim().isEmpty()) {
            // El TextField está vacío
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Campo Vacío");
            alert.setHeaderText(null);
            alert.setContentText("El campo está vacío. Por favor, ingresa un nombre.");
            alert.showAndWait();
        } else {
        
        	///Sino esta vacio haga el proceso de conectar al servidor
        	System.out.println("Me voy a conectar");
        	cliente=f.conectar();
        	f.ingreso(nombreUsuario);
        	Thread hiloRespuestas= new Thread(new HiloCliente());
        	hiloRespuestas.setDaemon(true);
        	hiloRespuestas.start();
        	botnConectar.setDisable(true);
     	    txtNombre.setDisable(true);
        	
           
        }
    	    	
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
    /**
     * Evento para enviar un mensaje 
     * @param event
     */
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
        
        	 // mandar mensaje al servidor 
            f.enviarMensaje(mensaje);
            txtEscribirMensajes.clear();
          
            txtEscribirMensajes.clear();
        	
        }
                

    }
    

  //---------------------------------HILOCLIENTE------------------------------
    /**
     * Hilo cliente que se inicia despues de conectar el socket para escuchar lo que nos dice el servidor 
     * @author Ruben Garrido
     * @author Carlos Galeano
     *
     */
    public class HiloCliente implements Runnable {

    	/**
    	 * Esta clase representa un hilo que se ejecuta en paralelo con otros hilos y se encarga de la comunicación con el servidor 
    	 */
    	public void run() {
    		try {
				
				InputStream inStream =null;
				
				// Hace la comunicacion con servidor y este se encuentra todo el tiempo escuchando los mensajes y respuestas del server
				while (true) {
					inStream = cliente.getInputStream();
					DataInputStream respuestaServidor= new DataInputStream(inStream);
					String respuesta = respuestaServidor.readUTF();
					if (respuesta.equals("Nombre repetido")) {
						
						JOptionPane.showInternalMessageDialog(null, "Nombre repetido\nIntente con otro difernte");
						botnConectar.setDisable(false);
						txtNombre.setDisable(false);
					}else {
						
						txtMostrarChat.appendText(respuesta+"\n");
					}
			
					listaEntrante=new ObjectInputStream(cliente.getInputStream());
					
					Object objetoRecibido =listaEntrante.readObject();
		
					if (objetoRecibido instanceof ArrayList<?>) {
					    ArrayList<String> listaRecibida = (ArrayList<String>) objetoRecibido;
					    // Convierte el ArrayList a ObservableList
					    ObservableList<String> observableList = FXCollections.observableArrayList(listaRecibida);

					    //Se añadio este metodo para poder agregar los nombres a la lista
					    /**
					     * Hilo que permite modificar la interfaz de usuario 
					     */
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