package logica;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Vector;

import controller.ServidorControlador;

public class Hilocliente extends Thread {
	
	private Socket cliente;
	private DataInputStream recibir;
	private ServidorControlador servidor;

	private String nombre;
	
	public HiloCliente( Socket clientes,ServidorControlador servi) throws Exception {
		
		this.servidor = servi;
		this.cliente = clientes;
		this.nombre = nombres;
		
		usuariosActivos.add(this);
		
		for (int i = 0; i < usuariosActivos.size(); i++) {
			usuariosActivos.get(i).mensaje(nombre+" Se conecto a la sala");
		}
	}
	
	public void run(){
		String msj= " ";
		while (true) {
			try {
				recibir = new DataInputStream(cliente.getInputStream());
				msj=recibir.readUTF();
				for (int i = 0; i < usuariosActivos.size(); i++) {
					usuariosActivos.get(i).mensaje(msj);
					servidor.mostrarMensaje("Mensjae enviado");
				
				}
				
				
			} catch (Exception e) {
				break;
			}
			
		}
		
		usuariosActivos.removeElement(this);
		servidor.mostrarMensaje("Usuario desconectado");
		
		try {
			cliente.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	private void mensaje(String msj) throws Exception{
		
		enviar=new DataOutputStream(cliente.getOutputStream());
		enviar.writeUTF(msj);
		
		for (int i = 0; i < usuariosActivos.size(); i++) {
			System.out.println(usuariosActivos.get(i).nombre);
		}	
	}
	
}
