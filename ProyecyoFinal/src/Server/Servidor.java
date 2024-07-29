package Server;
import java.io.*;
import java.net.*;
import Logico.Empresa;

public class Servidor {
	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		try {
			System.out.println("Iniciando Conexion");
			serverSocket = new ServerSocket(7000);
			System.out.println("Servidor escuchando en el puerto 7000");

			while (true) {
				try {
					Socket clientSocket = serverSocket.accept();
					System.out.println("Conexion aceptada de: " + clientSocket.getInetAddress());

					ObjectInputStream inputStream = new ObjectInputStream(clientSocket.getInputStream());

					try {
						Empresa empresa = (Empresa) inputStream.readObject();
						System.out.println("Empresa recibida: " + empresa.countIdComponente());                        
					} catch (ClassNotFoundException e) {
						System.out.println("Error al leer el objeto: " + e.getMessage());
					}

					inputStream.close();
					clientSocket.close();
				} catch (IOException ioe) {
					System.out.println("Error en la conexión del cliente: " + ioe.getMessage());
				}
			}
		} catch (IOException ioe) {
			System.out.println("Error al iniciar el servidor: " + ioe.getMessage());
		} finally {
			if (serverSocket != null && !serverSocket.isClosed()) {
				try {
					serverSocket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}