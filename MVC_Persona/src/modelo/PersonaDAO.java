/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author daw
 */
public class PersonaDAO {

    public boolean insertar(Persona persona) {
        boolean devolver = false;
        Connection con = DBConexion.conectar();
        if (persona != null) {
            try {
                PreparedStatement pst = con.prepareStatement("insert into personas values (?,?,?)");
                pst.setString(1, persona.getNombre());
                pst.setInt(2, persona.getEdad());
                pst.setInt(3, persona.getIdPoblacion());
                int res = pst.executeUpdate();
                if (res > 0) {
                    devolver = true;
                }

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            } finally {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PersonaDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
        return devolver;

    }

    public Persona buscar(String nombre) {
        Connection con = DBConexion.conectar();
        Statement st;
        Persona persona = null;
        try {
            st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from personas where nombre = '" + nombre + "' ");
            if (rs.next()) {
                persona = new Persona();
                persona.setEdad(rs.getInt("edad"));
                persona.setNombre(rs.getString("nombre"));
                persona.setIdPoblacion(rs.getInt("idPoblacion"));

            }
        } catch (SQLException ex) {
            Logger.getLogger(PersonaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(PersonaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return persona;
    }

    public int eliminar(String nombre) {
        Connection conexion = DBConexion.conectar();
        String sql = "DELETE FROM personas WHERE LOWER(nombre) = LOWER(?);";
        PreparedStatement pst = null;
        int res = 0;
        try {
            pst = conexion.prepareStatement(sql);
            pst.setString(1, nombre);
            res = pst.executeUpdate();
            System.out.println("resultado al eliminar");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }finally{
            try {
                conexion.close();
            } catch (SQLException ex) {
                Logger.getLogger(PersonaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return res;
    }
}
