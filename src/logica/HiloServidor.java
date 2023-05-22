package logica;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import Archivo.Archivos;
import controller.ServidorControlador;

public class HiloServidor extends Thread {
	
	private DataInputStream recibir;
	private DataOutputStream enviar;
	private ServidorControlador servidor;
	private Socket cliente;
	private static Vector<HiloServidor> usuariosActivos= new Vector<>();
	private String nombre;
	private ObjectOutputStream listaObjeto;
	private Archivos a =new Archivos();
	
	public HiloServidor( Socket clientes, String cliente,ServidorControlador servi) {
		System.out.println("HiloServidor soy el hilo del cliente: "+cliente);
		this.servidor = servi;
		this.cliente = clientes;
		this.nombre = cliente;
		usuariosActivos.add(this);
		
		for (int i = 0; i < usuariosActivos.size(); i++) {
			try {
				usuariosActivos.get(i).mensaje(nombre+" conectado");
				a.guardarUsuarios(nombre+" conectado");
				
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

	            DataInputStream lectorEntrada = new DataInputStream(cliente.getInputStream());
	            
	            mensaje = lectorEntrada.readUTF();
	            
	            servidor.mostrarMensaje("Mensaje recibido: "+mensaje);

				for (int i = 0; i < usuariosActivos.size(); i++) {
					usuariosActivos.get(i).mensaje(nombre+": "+mensaje);
					servidor.mostrarMensaje("Mensjae enviado a: "+usuariosActivos.get(i).nombre);
				}
				
			} catch (Exception e) {
				break;
			}
			
		}
		
		
		usuariosActivos.removeElement(this);
		for (int i = 0; i < usuariosActivos.size(); i++) {
			try {
				usuariosActivos.get(i).mensaje(nombre+" desconectado ");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		a.guardarUsuarios(nombre+" desconectado");
		servidor.mostrarMensaje(nombre+" desconectado");
		
		try {
			cliente.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	private void mensaje(String msj) throws Exception{
		enviar=new DataOutputStream(cliente.getOutputStream());
		enviar.writeUTF(msj);
	
		ArrayList<String> modelo= new ArrayList<>();
		
		for (int i = 0; i < usuariosActivos.size(); i++) {
			modelo.add(usuariosActivos.get(i).nombre);
		}	
		listaObjeto=new ObjectOutputStream(cliente.getOutputStream());
		listaObjeto.writeObject(modelo);
		
		
	}
	
}