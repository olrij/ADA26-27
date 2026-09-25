package com.ada;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class FlujosBytes {

    public static void main(String[] args) {

        String ruta="./datos/notas.dat";
        //crearArchivo(ruta);
        //leerArchivo(ruta);

        ArrayList<Double> notas = obtenerNotasAlumno(ruta, 4);

        for(double d:notas){
            System.out.println(d);
        }
        
    }

    public static void crearArchivo(String rutaArchivo){

        //1. Crear flujo
       

        try {
            DataOutputStream dos=new DataOutputStream(new FileOutputStream(rutaArchivo));
            //2. Hacer operación

            dos.writeInt(1);

            dos.writeChar(' ');

            dos.writeDouble(1.12);

            dos.writeChar('\n');

            dos.writeInt(2);

            dos.writeChar(' ');

            dos.writeDouble(5.7);

            dos.writeChar('\n');

            dos.writeInt(3);

            dos.writeChar(' ');

            dos.writeDouble(5.7);

            dos.writeChar('\n');

            dos.writeInt(1);

            dos.writeChar(' ');

            dos.writeDouble(7);

            dos.writeChar('\n');


            //3. Cerrar  

            dos.flush();

            dos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        


    }

    public static void leerArchivo(String rutaArchivo){

        
        DataInputStream dis = null;
        try {
            dis=new DataInputStream(new FileInputStream(rutaArchivo));

            while(true){
            
                System.out.print(dis.readInt());
                System.out.print(dis.readChar());
                System.out.print(dis.readDouble());
                System.out.println(dis.readChar());


            }




        } catch (EOFException e) {
            // TODO Auto-generated catch block
            System.out.println("Hemos llegado al final de archivo");
            try {
                dis.close();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        


    }

        public static ArrayList<Double> obtenerNotasAlumno(String rutaArchivo,int idAlumno){

        DataInputStream dis=null;
        ArrayList<Double> listaNotas=new ArrayList<Double>();
        int id=0;
        double nota=0.0;

        try {
            dis=new DataInputStream(new FileInputStream(rutaArchivo));



            while(true){
            
                id=dis.readInt();
                dis.readChar();
                nota=dis.readDouble();
                dis.readChar();
                if(id==idAlumno){
                    listaNotas.add(nota);
                }


            }




        } catch (EOFException e) {
            // TODO Auto-generated catch block
            System.out.println("Hemos llegado al final de archivo");
            try {
                dis.close();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return listaNotas;


    }



}
