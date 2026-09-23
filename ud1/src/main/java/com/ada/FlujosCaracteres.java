package com.ada;

import java.io.*;

public class FlujosCaracteres {

    public static void main(String[] args) {
        File archivo = new File("mensaje.txt");

        //escribirArchivo(archivo, "\nBien", false);

        //leerArchivoCaracteres(archivo);
        leerArchivoLineas(archivo);
    }



    public static void escribirArchivo(File archivo,String mensaje,boolean sobreescribir){
        
        // 1. Abrir flujo
        FileWriter fw;
        try {
            fw = new FileWriter(archivo, !sobreescribir);
            PrintWriter pw=new PrintWriter(fw);

            // 2. Realiza la operación
            pw.print(mensaje);

            // 3. Cerrar flujo
            pw.flush();
            pw.close();


        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

        public static void leerArchivoCaracteres(File archivo){
        
        // 1. Abrir flujo
        
        try (FileReader fr = new FileReader(archivo)) {

            // 2. Realiza la operación
            int c=-0;
            


            c=fr.read();
            while(c!= -1){
                System.out.print((char)c);
                c=fr.read();
            }
            System.out.println();

            // 3. Cerrar flujo: Se cierra automáticamente al usar try-with-resources
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    
    
 
        
    }

    public static void leerArchivoLineas(File archivo){
        
        // 1. Abrir flujo
        
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {

            // 2. Realiza la operación
            String linea="";



            linea=br.readLine();
            while(linea!= null){
                System.out.println(linea);
                linea=br.readLine();
            }


            // 3. Cerrar flujo: Se cierra automáticamente al usar try-with-resources
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
        

    
    
 
        


}


