package controller;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import java.net.ServerSocket;
import java.net.Socket;

import java.util.Vector;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import logica.Excepciones;
import logica.HiloServidor;



public class ServidorControlador {

    @FXML
    private TextArea txtMostrarChat;
    //Vector de usuarios creados, donde se almacena los hilos que se van creando
//  	public static Vector<HiloServidor> usuariosActivos= new Vector<>();
  	//Variable para enviar datos
//  	private DataOutputStream enviar;
    private int puerto=8000;

    public void initialize() {
        Thread hiloServidor = new Thread(this::startServer);
        hiloServidor.setDaemon(true);
        hiloServidor.start();
    }

    private void startServer() {
        try (ServerSocket serverSocket = new ServerSocket(puerto)) {
            mostrarMensaje("Servidor escuchando en el puerto "+puerto);

            while (true){
            	
                Socket clienteSocket = serverSocket.accept();
                mostrarMensaje("Cliente conectado: " + clienteSocket.getInetAddress());
                
                DataInputStream nombre= new DataInputStream(clienteSocket.getInputStream());
                
                System.out.println(" servidor: Disparo un hilo para el cliente");
                
                
                try {
                	 HiloServidor hilo= new HiloServidor(clienteSocket, nombre.readUTF(), this);
                	 hilo.start();
//                	 usuariosActivos.add(hilo);
                } catch (Excepciones e) {
                	
                    System.out.println("El nombre no está disponible. Por favor, elige otro nombre.");
//                	enviar=new DataOutputStream(clienteSocket.getOutputStream());
//            		enviar.writeUTF("Nombre repetido");
                    clienteSocket.close();
                    
                    mostrarMensaje("Cliente desconectado: " + clienteSocket.getInetAddress()+" Nombre repetido");
                }       
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void mostrarMensaje(String message) {
        txtMostrarChat.appendText(message + "\n");
    }
    
   
}