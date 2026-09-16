package com.ada;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        File f1=new File("C:\\Users\\User\\Desktop\\test.txt");
        File f2=new File("f.txt");

        File f3=new File("/home/u/Documentos/ADA/datos","numeros.txt");
        File f4=new File("/home/u/Documentos/ADA/datos/numeros.txt");
        File f5=new File("./datos/numeros.txt"); //*****/

        File d=new File("/home/u/Documentos/ADA");


        System.out.println(f3.exists());
        System.out.println(f4.exists());

        System.out.println(File.separator);


        File[] files = d.listFiles();

        for (File file : files) {
            System.out.println(file.getName());
        }
    


    }
}