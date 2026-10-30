package com.ada;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GestorBDVentas {


    private String url="jdbc:mysql://localhost:3306/ventas";
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

    public void verMetadatosBD(String nombreBD) throws SQLException{
        DatabaseMetaData metadatos = con.getMetaData();

        System.out.println(metadatos.getDatabaseProductName());
        System.out.println(metadatos.getDriverName());


        System.out.println("-----------------------------");

        ResultSet rs = metadatos.getTables(nombreBD, null, null, new String[] {"TABLE"});

        while(rs.next()){
            String tabla=rs.getString("TABLE_NAME");
            System.out.println("Tabla: "+tabla);

            // Recorremos las columnas

            ResultSet rs2 = metadatos.getColumns(nombreBD, null, tabla, null);
        

            while(rs2.next()){
                System.out.println(rs2.getString("COLUMN_NAME")+" "+rs2.getString("TYPE_NAME"));

            }
            System.out.println("-----------------------------");

        }

    }



    /* 
    delimiter //
    create procedure verClientesCiudad(in ciu varchar(30))
    begin
        SELECT id,nombre,ciudad FROM cliente WHERE ciudad like ciu;
    end //
    */

    public void verClientesCiudad(String ciudad) throws SQLException{

            CallableStatement cs=con.prepareCall("CALL verClientesCiudad(?)");

            cs.setString(1,ciudad);

            ResultSet rs = cs.executeQuery();

            while(rs.next()){
                int id=rs.getInt("id");
                String nombre=rs.getString("nombre");
                String ciudadCl=rs.getString("ciudad");
                System.out.println(id+" "+nombre+" "+ciudadCl);

        }




    }

    public void insertarInfoCliente(){

        try {

            con.setAutoCommit(false);

            Statement st=con.createStatement();

            st.execute("INSERT INTO cliente VALUES(90, 'Aarón', 'bLA', 'Gómez', 'Sevilla', 90);");

            st.execute("INSERT INTO pedido VALUES(90, 150.5, '2017-10-05', 90, 2);");
            st.execute("INSERT INTO pedido VALUES(91, 150.5, '2017-10-05', 90, 2);");

            con.commit();

            System.out.println("Información insertado correctamente");





        } catch (SQLException e) {
            // TODO Auto-generated catch block
            
            try {
                con.rollback();
                System.out.println("Se ha producido un error al insertar la información. Se restaura el estado anterior");
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }


        }

        try {
            con.setAutoCommit(true);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }



    public void cerrarConection() throws SQLException{
        con.close();
    }


}
