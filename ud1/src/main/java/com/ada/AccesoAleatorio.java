package com.ada;

import java.io.EOFException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class AccesoAleatorio {

    public static void main(String[] args) {

        String rutaFichero="./datos/notas.dat";


        //cambiarNota(2, 7.5,"./datos/notas.dat");
        //introducirNuevaNota(4, 0.7, rutaFichero);
        cambiarId(12,1,rutaFichero);



    }

    public static void cambiarNota(int numNota,double nuevaNota,String rutaFichero){

        try (RandomAccessFile raf = new RandomAccessFile(rutaFichero, "rw")) {
            raf.seek(16*(numNota-1)+6);
            System.out.println(nuevaNota);
            raf.writeDouble(nuevaNota);
        
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }

    public static void introducirNuevaNota(int idAlumno,double nota,String rutaFichero){

        try (RandomAccessFile raf = new RandomAccessFile(rutaFichero, "rw")) {

            //Vamos a la posición que me interesa
            raf.seek(raf.length());

            // Hago la operación que corresponda
            raf.writeInt(idAlumno);
            raf.writeChar(' ');
            raf.writeDouble(nota);
            raf.writeChar('\n');




        
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }

    public static void cambiarId(int idOld,int idNew,String rutaFichero){

        int id=0;

        try (RandomAccessFile raf = new RandomAccessFile(rutaFichero, "rw")) {

            
            while(true){
                id=raf.readInt();

                if(id==idOld){
                    raf.seek(raf.getFilePointer()-4);
                    raf.writeInt(idNew);
                }


                raf.readChar();
                raf.readDouble();
                raf.readChar();
            }




        
        } catch (EOFException e) {
            // TODO Auto-generated catch block
            System.out.println("Hemos llegado al final del archivo");
        }
        
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }


}
