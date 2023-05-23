package controller;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import logica.Excepciones;
import logica.HiloServidor;

/**
 * 
 * @author 31285
 *
 */

public class ServidorControlador {

    @FXML
    private TextArea txtMostrarChat;
     private int puerto=8000;

    public void initialize() {
    	// se crea y se inicia un nuevo hilo de ejecución utilizando una expresión llamada (this::startServer).
    	//El método startServer se ejecutará en este hilo de fondo.
    	
        Thread hiloServidor = new Thread(this::startServer);
        hiloServidor.setDaemon(true);
        hiloServidor.start();
    }
    
    /**
     * Este metodo es que tiene la logica del servidor 
     */

    private void startServer() {
        try (ServerSocket serverSocket = new ServerSocket(puerto)) {
            mostrarMensaje("Servidor escuchando en el puerto "+puerto);

            while (true){
            	
                Socket clienteSocket = serverSocket.accept();
                mostrarMensaje("Cliente conectado: " + clienteSocket.getInetAddress());
                
                DataInputStream nombre= new DataInputStream(clienteSocket.getInputStream());
                
                System.out.println(" servidor: Disparo un hilo para el cliente");
                
                
                try {
                	//lanza un hilo  para cada peticion
                	 HiloServidor hilo= new HiloServidor(clienteSocket, nombre.readUTF(), this);
                	 hilo.start();

                } catch (Excepciones e) {
                	// por si ya existe el nombre 
                    System.out.println("El nombre no está disponible. Por favor, elige otro nombre.");
                	DataOutputStream enviar = new DataOutputStream(clienteSocket.getOutputStream());
            		enviar.writeUTF("Nombre repetido");
            		clienteSocket.close();
            		mostrarMensaje("Cliente desconectado: "+ clienteSocket.getInetAddress()+"Nombre repetido");

                }       
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    //metodo para mostrar mensajes
    public void mostrarMensaje(String message) {
        txtMostrarChat.appendText(message + "\n");
    }
    
   
}