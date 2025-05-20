package modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PoblacionDAO {
    public ArrayList<Poblacion> getPoblaciones(){
         ArrayList<Poblacion> poblaciones = null;
         Connection con = DBConexion.conectar();
         Statement st = null;
         ResultSet rs = null;
        try {
            st = con.createStatement();
            rs = st.executeQuery("select * from poblaciones order by id");
            poblaciones = new ArrayList<>();
            while(rs.next()){
                Poblacion p = new Poblacion();
                p.setId(rs.getInt("id"));
                p.setNombre(rs.getString("nombre"));
                poblaciones.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }finally{
             try {
                 con.close();
             } catch (SQLException ex) {
                 Logger.getLogger(PoblacionDAO.class.getName()).log(Level.SEVERE, null, ex);
             }
        }
         return poblaciones;
         
    }
}
