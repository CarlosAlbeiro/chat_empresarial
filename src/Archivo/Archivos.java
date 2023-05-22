package Archivo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import logica.Mensaje;




public class Archivos {


	public static final String RUTA_ARCHIVO_USUARIOS = "src/Archivo/Usuarios.txt";
	public static final String RUTA_ARCHIVO_HISTORIAL_INGRESO = "src/Archivo/HistorialIngreso.txt";
	public static final String RUTA_ARCHIVO_MENSAJES = "src/Archivo/Meensajes.txt";
	// Vamos a guardar historial de inicio sesion, usuarios, mensajes 
	
	//Escribir el archvio de usuario
	public void guardarUsuarios(String usuario) {
		try {
//			FileOutputStream f = new FileOutputStream(new File(RUTA_ARCHIVO_USUARIOS));
//			ObjectOutputStream o = new ObjectOutputStream(f);
//
//			// Write objects to file
//			o.writeObject(usuario);
//
//			o.close();
//			f.close();
		
			FileWriter fileWriter = new FileWriter(RUTA_ARCHIVO_USUARIOS);
	        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

	        bufferedWriter.write(usuario+"\n");

	        bufferedWriter.close();
	        fileWriter.close();
		

		} catch (FileNotFoundException e) {
			
			System.out.println("File not found\n");
		} catch (IOException e) {
			
			System.out.println("Error initializing stream\n");
		}
		
	}
	
	
//	public ArrayList cargarHistorial() {
//
//		boolean result = false;
//		ArrayList listaUsuarios = new ArrayList<>();
//
//		try {
//			FileInputStream fi = new FileInputStream(new File(RUTA_ARCHIVO_USUARIOS));
//			ObjectInputStream oi = new ObjectInputStream(fi);
//
//			// Read objects
//			listaUsuarios = (ArrayList) oi.readObject();
//
//			oi.close();
//			fi.close();
//
//		} catch (FileNotFoundException e) {
//			System.out.println("File not found");
//		} catch (IOException e) {
//			System.out.println("Error initializing stream");
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		return listaUsuarios;
//
//	}
//
//	//Escribir el archvio de usuario
//	public ArrayList guardarHistorial(ArrayList<Usuario> listaUsuarios) {
//		ArrayList<Usuario> respuesta;
//		try {
//			FileOutputStream f = new FileOutputStream(new File(RUTA_ARCHIVO_USUARIOS));
//			ObjectOutputStream o = new ObjectOutputStream(f);
//
//			// Write objects to file
//			o.writeObject(listaUsuarios);
//
//			o.close();
//			f.close();
//			respuesta = listaUsuarios;
//
//		} catch (FileNotFoundException e) {
//			respuesta = null;
//			System.out.println("File not found\n");
//		} catch (IOException e) {
//			respuesta = null;
//			System.out.println("Error initializing stream\n");
//		}
//		return respuesta;
//	}
//	
//	
//	public ArrayList<Mensaje> cargarMensajes() {
//
//		boolean result = false;
//		ArrayList<Mensaje> listaMensaje = new ArrayList<>();
//
//		try {
//			FileInputStream fi = new FileInputStream(new File(RUTA_ARCHIVO_MENSAJES));
//			ObjectInputStream oi = new ObjectInputStream(fi);
//
//			// Read objects
//			listaMensaje = (ArrayList<Mensaje>) oi.readObject();
//
//			oi.close();
//			fi.close();
//
//		} catch (FileNotFoundException e) {
//			System.out.println("File not found");
//		} catch (IOException e) {
//			System.out.println("Error initializing stream");
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		return listaMensaje;
//
//	}
//
//	//Escribir el archvio de usuario
//	public ArrayList<Mensaje> guardarMensajes(ArrayList<Mensaje> listaMensajes) {
//		ArrayList<Mensaje> respuesta;
//		try {
//			FileOutputStream f = new FileOutputStream(new File(RUTA_ARCHIVO_USUARIOS));
//			ObjectOutputStream o = new ObjectOutputStream(f);
//
//			// Write objects to file
//			o.writeObject(listaMensajes);
//
//			o.close();
//			f.close();
//			respuesta = listaMensajes;
//
//		} catch (FileNotFoundException e) {
//			respuesta = null;
//			System.out.println("File not found\n");
//		} catch (IOException e) {
//			respuesta = null;
//			System.out.println("Error initializing stream\n");
//		}
//		return respuesta;
//	}
//	
//	
//	

}
