package Server;

import java.io.*;
import java.net.*;
import Logico.Empresa;

public class Servidor extends Thread {

    public static void main(String args[]) {
        ServerSocket sfd = null;
        try {
            System.out.println("Iniciando Conexion");
            sfd = new ServerSocket(7000);
            System.out.println("Aceptando conexiones por la ip: " + sfd.getInetAddress());
        } catch (IOException ioe) {
            System.out.println("Comunicación rechazada." + ioe);
            System.exit(1);
        }

        while (true) {
            try {
                Socket nsfd = sfd.accept();
                System.out.println("Conexion aceptada de: " + nsfd.getInetAddress());
                ObjectInputStream FlujoLectura = new ObjectInputStream(new BufferedInputStream(nsfd.getInputStream()));
                try {
                    Empresa empresa = (Empresa) FlujoLectura.readObject();
                    System.out.println("Empresa recibida: " + empresa.countIdComponente());
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } catch (IOException ioe) {
                System.out.println("Error: " + ioe);
            }
        }
    }
}
