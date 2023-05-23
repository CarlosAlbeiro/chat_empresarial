package logica;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;

import Archivo.Archivos;
import controller.ServidorControlador;

public class HiloServidor extends Thread {
	
	//Variable para enviar datos
	private DataOutputStream enviar;
	//Variable tipo objeto para enviar la lista de usuarios activos 
	private ObjectOutputStream listaObjeto;
	//Varible tipo servidor para que el hilo tenga las propiedades del servidor
	private ServidorControlador servidor;
	//Socket del cliente al que va a atender el servidor
	private Socket cliente;
	//Vector de usuarios creados, donde se almacena los hilos que se van creando
	private static Vector<HiloServidor> usuariosActivos= new Vector<>();
	//Variable para almacenar el nombre del usuario
	private String nombre;
	//Instancia para poder leer y escribir los archivos
	private Archivos a =new Archivos();
	//Instacia para saber la fecha actual
	private Date fecha= new Date();
	private int peticion=0;
	// Obtener la hora actual
	private LocalTime horaActual = LocalTime.now();
    
    // Obtener las partes de la hora (hora, minutos, segundos)
    private int hora = horaActual.getHour();
    private int minutos = horaActual.getMinute();
    private int segundos = horaActual.getSecond();
    
    // Obtener la fecha actual
    private LocalDate fechaActual = LocalDate.now();
    
    // Obtener las partes de la fecha (día, mes, año)
    private int dia = fechaActual.getDayOfMonth();
    private int mes = fechaActual.getMonthValue();
    private int anio = fechaActual.getYear();
    
    private String hora1 = dia+"/"+mes+"/"+anio+"/"+"-"+hora+":"+minutos+":"+segundos;
    
	
	//Hilo encargado de las peticiones del cliente
	public HiloServidor( Socket clientes, String cliente,ServidorControlador servi) throws Exception {
		
		if (verificarNombre(cliente)) {
			throw new Excepciones("El nombre no está disponible");
		}else {
			System.out.println("HiloServidor soy el hilo del cliente: "+cliente);
			a.guardarUsuarios(cliente+" se conecto hora: "+fecha+"\n");
			String hitorial=a.leerArchivomensajes();
			
			this.servidor = servi;
			this.cliente = clientes;
			this.nombre = cliente;
			usuariosActivos.add(this);
			
			usuariosActivos.get(usuariosActivos.size()-1).mensaje(hitorial);
			
//			envioHitorial();
			for (int i = 0; i < usuariosActivos.size(); i++) {
				try {
					usuariosActivos.get(i).mensaje(nombre+" se conecto");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
	
//	private void envioHitorial() {
//		int numeroUsuarios=(usuariosActivos.size())-1;
//		String historial=a.leerArchivomensajes();
//		
//		try {
//			usuariosActivos.get(numeroUsuarios).Hitorial(historial);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		
//	}

	//Iniciamos el hilo
	public void run(){
		
		servidor.mostrarMensaje(nombre+" se conecto a la sala");
		String mensaje= " ";
		while (true) {
			try {
				 // Obtener los flujos de entrada y salida del socket

	            DataInputStream lectorEntrada = new DataInputStream(cliente.getInputStream());
	            
	            mensaje = lectorEntrada.readUTF();
	            System.out.println("Peticion Nº: "+peticion+"---"+mensaje);
	            
	            servidor.mostrarMensaje("Mensaje recibido: "+mensaje);         
	            a.guardarMensajes(nombre+": "+mensaje+" -- "+hora1);
	            
				for (int i = 0; i < usuariosActivos.size(); i++) {
					usuariosActivos.get(i).mensaje(nombre+": "+mensaje+" -- "+hora1);
					servidor.mostrarMensaje("Mensjae enviado a: "+usuariosActivos.get(i).nombre);
				}
				peticion++;
			} catch (Exception e) {
				break;
			}
			
		}
		
		
		usuariosActivos.removeElement(this);
		for (int i = 0; i < usuariosActivos.size(); i++) {
			try {
				usuariosActivos.get(i).mensaje(nombre+" se desconecto ");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		a.guardarUsuarios(nombre+" se desconecto hora: "+fecha);
		servidor.mostrarMensaje(nombre+" se desconectado\n");
		try {
			cliente.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	private void Hitorial(String msj) throws Exception{
		enviar=new DataOutputStream(cliente.getOutputStream());
		enviar.writeUTF(msj);
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
	
	private static boolean verificarNombre(String Nombre) {

		Boolean disponible=false;
		for (int i = 0; i < usuariosActivos.size(); i++) {
			if(usuariosActivos.get(i).nombre.equals(Nombre)) {
				disponible=true;
				break;
			}else {
				disponible=false;
			}
		}
		return disponible;
	}
	
	
	
}