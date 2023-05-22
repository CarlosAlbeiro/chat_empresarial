package controller;


import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Observer;
import java.util.Scanner;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import logica.HiloServidor;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServidorControlador {

    @FXML
    private TextArea txtMostrarChat;
    private List<String> guardarClientes = new ArrayList<>();
    private ExecutorService executorService = Executors.newCachedThreadPool();
    private Scanner scanner;
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
                
                DataInputStream entrada= new DataInputStream(clienteSocket.getInputStream());
                
                System.out.println(" servidor: Disparo un hilo para el cliente");
                HiloServidor hilo= new HiloServidor(clienteSocket, entrada.readUTF(), this);
                
                hilo.start();
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