package logica;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;


/**
 * Encapsulador de funciones para enviar peticiones al servidor 
 * @author Ruben Garrido
 * @author Carlos Galeano
 */
public class Funciones {
	//Declaramos el socket
	public  Socket cliente;

	/**
	 * 
	 * @return devuelve el socket conectado
	 */
	public Socket conectar() {
		System.out.println("LLEGUE A LA FUNCION");
		try {
			System.out.println("Voy a conectar el socket");
			cliente = new Socket("localHost", 8000);
			return cliente;
		} catch (IOException e) {
			System.out.println("Entro al try");
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Funcion para enviar una peticion de ingreso al chat
	 * @param nombreUsuario
	 */
	public void ingreso(String nombreUsuario) {
		OutputStream outStream = null;
		try {
			outStream = cliente.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		DataOutputStream peticionSalida= new DataOutputStream(outStream);
        try {
			peticionSalida.writeUTF(nombreUsuario);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Funcion para enviar mensajes 
	 * @param mensaje
	 */
	public void enviarMensaje(String mensaje) {
		OutputStream mensajeSaliente = null;
		try {
			mensajeSaliente = cliente.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		DataOutputStream peticionSalida= new DataOutputStream(mensajeSaliente);
        try {
			peticionSalida.writeUTF(mensaje);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}