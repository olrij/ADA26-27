package com.ada;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        GestorBDVentas gestor = new GestorBDVentas();

        //gestor.realizarOperacionDML("INSERT INTO comercial VALUES(101, 'Daniel', 'Sáez', 'Vega', 0.15);");
        //gestor.consultarTodosComerciales();
        gestor.consultarComisionPorID(3);

    }
}