package logica;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import Archivo.Archivos;



public class ControlLogica {
	//Este es el contolador para los metodos de la logica
	public static Archivos archivo = new Archivos();
	public static ArrayList<Usuario> usuarios= new ArrayList<Usuario>();
	public static ArrayList<Mensaje> mensaje= new ArrayList<Mensaje>();
	//Un hilo para escribir mensajes y otro para cargar mensajes 
	public static boolean crearUsuario(String nombre,String apellido, String cedula, String programa, String clave) {
		
		usuarios=archivo.cargarUsuarios();
		
		Usuario usuario= new Usuario(nombre);
		System.out.println("usuarios: "+usuario.toString());

		usuarios.add(usuario);
		System.out.println("Arreglo usuarios: "+ usuarios);

		
		usuarios=archivo.guardarUsuarios(usuarios);
		System.out.println("Usuarios cargados: "+usuarios);
		
		consultarUsuarios();
		
		if (usuarios.size()>=0) {
			return true;
		}else {
			return false;
		}
	}
	
	private static void consultarUsuarios() {
		usuarios=archivo.cargarUsuarios();
		for (Usuario usuarios : usuarios) {
			System.out.println("Cedula: "+usuarios.getCedula());
			System.out.println("Nombre: "+usuarios.getNombre());
			System.out.println("Programa: "+usuarios.getPrograma());
			System.out.println("Clave: "+usuarios.getClave());
			System.out.println("--------------------------");
		}
	}
	

//	public static boolean enviarMensaje(ArrayList<Mensaje> mensaje) {
//		
//		mensaje=archivo.cargarMensajes();
//		
//		Usuario usuario= new Usuario(nombre, apellido,cedula, programa, clave);
//		System.out.println("usuarios: "+usuario.toString());
//
//		usuarios.add(usuario);
//		System.out.println("Arreglo usuarios: "+ usuarios);
//
//		
//		usuarios=archivo.guardarUsuarios(usuarios);
//		System.out.println("Usuarios cargados: "+usuarios);
//		
//		consultarUsuarios();
//		
//		if (usuarios.size()>=0) {
//			return true;
//		}else {
//			return false;
//		}
//	}
	

}
