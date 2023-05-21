package logica;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import controller.ServidorControlador;

public class HiloServidor extends Thread {
	
	private DataInputStream recibir;
	private DataOutputStream enviar;
	private ServidorControlador servidor;
	private Socket cliente;
	private static Vector<HiloServidor> usuariosActivos= new Vector<>();
	private String nombre;
	
	public HiloServidor( Socket clientes, String cliente,ServidorControlador servi) {
		System.out.println("HiloServidor soy el hilo del cliente: "+cliente);
		this.servidor = servi;
		this.cliente = clientes;
		this.nombre = cliente;
		usuariosActivos.add(this);
		
		for (int i = 0; i < usuariosActivos.size(); i++) {
			try {
				usuariosActivos.get(i).mensaje(nombre+" conectado");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void run(){
		
		
		
		
		servidor.mostrarMensaje(nombre+" se conecto a la sala");
		String mensaje= " ";
		while (true) {
			try {
				 // Obtener los flujos de entrada y salida del socket
				InputStream entrada=cliente.getInputStream();
	
	            DataInputStream lectorEntrada = new DataInputStream(entrada);
				
	            
	            // Recibir datos del cliente
	            mensaje = lectorEntrada.readUTF();
	            servidor.mostrarMensaje("Mensaje recibido: "+mensaje);
	            System.out.println("Mensaje recibido del cliente: " + mensaje);

				servidor.mostrarMensaje(mensaje);
				for (int i = 0; i < usuariosActivos.size(); i++) {
					System.out.println("en el bucle para enviar");
					usuariosActivos.get(i).mensaje(mensaje);
					servidor.mostrarMensaje("Mensjae enviado");
				}
			} catch (Exception e) {
				break;
			}
			
		}
		
		usuariosActivos.removeElement(this);
		servidor.mostrarMensaje("Usuario desconectado");
		
	}
	
	private void mensaje(String msj) throws Exception{
		
		enviar=new DataOutputStream(cliente.getOutputStream());
		enviar.writeUTF(msj);
		
		for (int i = 0; i < usuariosActivos.size(); i++) {
			System.out.println(usuariosActivos.get(i).nombre);
		}	
	}
	
}