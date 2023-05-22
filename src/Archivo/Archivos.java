package Archivo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import logica.Mensaje;





public class Archivos {


	public static final String RUTA_ARCHIVO_HISTORIAL_USUARIOS = "src/Archivo/HistorialUsuarios.txt";
	public static final String RUTA_ARCHIVO_MENSAJES = "src/Archivo/Mensajes.txt";
	// Vamos a guardar historial de inicio sesion, usuarios, mensajes 
	
	
	public String leerArchivousuarios() {
    StringBuilder contenido = new StringBuilder();
    try {
        FileReader fileReader = new FileReader(RUTA_ARCHIVO_HISTORIAL_USUARIOS);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String linea;
        while ((linea = bufferedReader.readLine()) != null) {
            // Concatenar la línea leída al contenido
            contenido.append(linea+"\n");
        }

        bufferedReader.close();
        fileReader.close();
    } catch (IOException e) {
        System.out.println("Error al leer el archivo");
        e.printStackTrace();
    }
    // Devolver el contenido como una cadena
    return contenido.toString();
}
	
	//Escribir el archvio de usuario
	public void guardarUsuarios(String usuario) {
		String lectura=leerArchivousuarios();
		try {
			FileWriter fileWriter = new FileWriter(RUTA_ARCHIVO_HISTORIAL_USUARIOS);
	        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

	        bufferedWriter.write(lectura+""+usuario);

	        bufferedWriter.close();
	        fileWriter.close();
		

		} catch (FileNotFoundException e) {
			
			System.out.println("File not found\n");
		} catch (IOException e) {
			
			System.out.println("Error initializing stream\n");
		}
		
	}
	
	
	public String leerArchivomensajes() {
	    StringBuilder contenido = new StringBuilder();
	    try {
	        FileReader fileReader = new FileReader(RUTA_ARCHIVO_MENSAJES);
	        BufferedReader bufferedReader = new BufferedReader(fileReader);

	        String linea;
	        while ((linea = bufferedReader.readLine()) != null) {
	            // Concatenar la línea leída al contenido
	            contenido.append(linea+"\n");
	        }

	        bufferedReader.close();
	        fileReader.close();
	    } catch (IOException e) {
	        System.out.println("Error al leer el archivo");
	        e.printStackTrace();
	    }
	    // Devolver el contenido como una cadena
	    return contenido.toString();
	}
		
		//Escribir el archvio de usuario
	public void guardarMensajes(String mensaje) {
			String lectura=leerArchivousuarios();
			try {
				FileWriter fileWriter = new FileWriter(RUTA_ARCHIVO_MENSAJES);
		        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

		        bufferedWriter.write(lectura+""+mensaje);

		        bufferedWriter.close();
		        fileWriter.close();
			

			} catch (FileNotFoundException e) {
				
				System.out.println("No existe el archvio\n");
			} catch (IOException e) {
				
				System.out.println("Error al inicializar\n");
			}
			
		}
		
	

}