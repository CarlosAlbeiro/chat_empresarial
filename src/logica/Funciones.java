package logica;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;



public class Funciones {
	//Declaramos el socket
	public  Socket cliente;

	
	public void conectar() {
		System.out.println("LLEGUE A LA FUNCION");
		try {
			System.out.println("Voy a conectar el socket");
			cliente = new Socket("localhost", 8000);
		} catch (IOException e) {
			System.out.println("Entro al try");
			e.printStackTrace();
		}
	}

	
	public void ingreso(String nombreUsuario) {
		
		//Inicializamos la variable de salida
		OutputStream outStream = null;
		InputStream inStream =null;
		
		try {
			//Asignamos a que corresponde, a un salida o entrada
			outStream = cliente.getOutputStream();
			inStream = cliente.getInputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Iniciamos el metodo de salida o envio de datos al servidor
		DataOutputStream peticionSalida= new DataOutputStream(outStream);
		DataInputStream peticionRespuesta= new DataInputStream(inStream);
     
		 // Enviar datos al servidor
        try {
			peticionSalida.writeUTF(nombreUsuario);
			String respuesta = peticionRespuesta.readUTF();
			System.out.println("Respuesta servidor: "+respuesta);
//			String primerosDosCaracteres = respuesta.substring(0, 2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      
		
	}


	public void enviarMensaje(String mensaje) {
		
		//Inicializamos la variable de salida
		OutputStream mensajeSaliente = null;
		InputStream respuestaServidor =null;
		try {
			//Asignamos a que corresponde, a un salida o entrada
			mensajeSaliente = cliente.getOutputStream();
			respuestaServidor = cliente.getInputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Iniciamos el metodo de salida o envio de datos al servidor
		DataOutputStream peticionSalida= new DataOutputStream(mensajeSaliente);
		DataInputStream peticionRespuesta= new DataInputStream(respuestaServidor);
     
		 // Enviar datos al servidor
        try {
			peticionSalida.writeUTF(mensaje);
			
			String respuesta = peticionRespuesta.readUTF();
			System.out.println("Respuesta servidor: "+respuesta);
//			String primerosDosCaracteres = respuesta.substring(0, 2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
