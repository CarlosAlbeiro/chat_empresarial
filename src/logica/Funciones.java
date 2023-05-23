package logica;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;



public class Funciones {
	//Declaramos el socket
	public  Socket cliente;

	
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