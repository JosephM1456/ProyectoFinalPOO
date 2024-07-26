package Logico;

import java.io.*;

public class ArchivoEmpresa {
    private static ArchivoEmpresa instance = null;

    private ArchivoEmpresa() {
        super();
    }

    public static ArchivoEmpresa getInstance() {
        if(instance == null) {
            instance = new ArchivoEmpresa();
        }
        return instance;
    }

    public void guardarEmpresa(Empresa empresa) throws IOException {
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("empresa.txt"))) {
            output.writeObject(empresa);
        }
    }

    public void cargarEmpresa(String archivo) throws IOException, ClassNotFoundException {
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(archivo))) {
            Empresa empresa = (Empresa) input.readObject();
            Empresa.setInstance(empresa);
        }
    }
}