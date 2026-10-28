package com.ada;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GestorBDVentas {


    private String url="jdbc:mysql://192.168.129.49:3306/ventas";
    private String user="root";
    private String password="1234";
    private Connection con;

    public GestorBDVentas() throws SQLException{
        con = DriverManager.getConnection(url, user,password);
    }


    public boolean realizarOperacionDML(String sql) throws SQLException{


        Statement st = con.createStatement();

        return st.execute(sql);

    }

    public void consultarTodosComerciales() throws SQLException{
        Statement st = con.createStatement();

        ResultSet rs = st.executeQuery("SELECT * FROM comercial");

        while(rs.next()){
            int id=rs.getInt("id");
            String nombre=rs.getString(2);
            String apellido1=rs.getString(3);
            String apellido2=rs.getString("apellido2");
            float comision=rs.getFloat(5);

            System.out.println(id+" "+nombre+" "+apellido1+" "+apellido2+" "+comision);

        }
        
    }

        public void consultarComisionPorID(int idC) throws SQLException{
        PreparedStatement pst = con.prepareStatement("SELECT id,comision FROM comercial WHERE id=?;");


        pst.setInt(1,idC);

        ResultSet rs = pst.executeQuery();


        while(rs.next()){
            int id=rs.getInt("id");
            float comision=rs.getFloat(2);

            System.out.println(id+" "+comision);

        }
        
    }


    public void consultarPedidosPersonaConCantidadSuperiorAX(int idCl,double cantidadPed) throws SQLException{
        PreparedStatement pst = con.prepareStatement("SELECT * FROM pedido WHERE id_cliente=? and cantidad>?;");


        pst.setInt(1,idCl);
        pst.setDouble(2, cantidadPed);

        ResultSet rs = pst.executeQuery();


        while(rs.next()){
            int idP=rs.getInt(1);
            double cantidad=rs.getDouble(2);
            String fecha=rs.getString(3);
            int id_cliente=rs.getInt("id_cliente");
            int id_comercial=rs.getInt("id_comercial");

            System.out.println(idP+" "+cantidad+" "+fecha+" "+id_cliente+" "+id_comercial);

        }
        
    }

    public void cerrarConection() throws SQLException{
        con.close();
    }


}
