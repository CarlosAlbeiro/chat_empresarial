package Archivo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;



/**
 * Description:
 * Esta clase tiene algunas funcionalidades relacionadas con la manipulación de archivos.
 * @author Ruben Garrido
 * @author Carlos Galeano
 */


public class Archivos {


	public static final String RUTA_ARCHIVO_HISTORIAL_USUARIOS = "src/Archivo/HistorialUsuarios.txt";
	public static final String RUTA_ARCHIVO_MENSAJES = "src/Archivo/Mensajes.txt";
	// Vamos a guardar historial de inicio sesion, usuarios, mensajes 
	
	/**
	 * Este método lee el contenido del archivo "HistorialUsuarios.txt" y lo devuelve como una cadena de texto.
	 * @return
	 */
	public String leerArchivousuarios() {
    StringBuilder contenido = new StringBuilder();
    try {
        FileReader fileReader = new FileReader(RUTA_ARCHIVO_HISTORIAL_USUARIOS);//busca el archivo
        BufferedReader bufferedReader = new BufferedReader(fileReader);// lee el contenido

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
	
	/**
	 * Este método agrega el contenido de la variable usuario al archivo "HistorialUsuarios.txt".
	 *  Primero, lee el contenido existente del archivo, luego añade el nuevo usuario y guarda el resultado en el archivo.
	 * @param usuario
	 */
	
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
	/**
	 * Este método lee el contenido del archivo "Mensajes.txt" y lo devuelve como una cadena de texto.
	 * @return
	 */
	
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
		
	
        /**
         * Este método agrega el contenido de la variable mensaje al archivo "Mensajes.txt". 
         * Al igual que en el método anterior, primero lee el contenido existente del archivo, añade el nuevo mensaje y guarda el resultado en el archivo.  	
         * @param mensaje
         */
		//Escribir el archvio de usuario
	public void guardarMensajes(String mensaje) {
			String lectura=leerArchivomensajes();
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