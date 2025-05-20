package dao;

import modelo.Usuario;
import java.sql.*;
import java.util.*;

/**
 * ✅ DAO: Data Access Object
 * Esta clase contiene todas las operaciones relacionadas con la tabla 'usuarios'.
 * Utiliza JDBC para ejecutar las consultas SQL.
 * 
 * 🎓 TEORÍA:
 * El patrón DAO permite:
 *   • Encapsular toda la lógica de acceso a datos
 *   • Separar la lógica SQL del resto del programa
 *   • Reutilizar fácilmente los métodos desde el controlador
 */
public class UsuarioDAO {

    // 🔗 Conexión que recibe desde el controlador
    private Connection conexion;

    public UsuarioDAO(Connection conexion) {
        this.conexion = conexion;
    }

    /**
     * ➕ INSERTAR usuario
     * Inserta un nuevo usuario en la base de datos.
     */
    public void insertar(Usuario u) throws SQLException {
        String sql = "INSERT INTO usuarios(nombre,edad) VALUES (?,?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, u.getNombre());
            ps.setInt(2, u.getEdad());
            ps.executeUpdate();
        }
    }

    /**
     * 📋 LISTAR todos los usuarios
     * Devuelve una lista con todos los usuarios almacenados en la tabla.
     */
    public List<Usuario> listarTodos() throws SQLException {
        List<Usuario> lista = new ArrayList<>();
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM usuarios");
        while (rs.next()) {
            Usuario u = new Usuario(
                rs.getInt("id"),
                rs.getString("nombre"),
                rs.getInt("edad")
            );
            lista.add(u);
        }
        return lista;
    }

    // 🔧 TODO: Añadir método actualizar(Usuario u)

    /**
     * 🔄 ACTUALIZAR usuario
     * Recibe un objeto Usuario con ID, y actualiza su nombre y edad.
     * 
     * 🧠 IMPORTANTE: el ID debe existir previamente en la tabla.
     */
    public void actualizar(Usuario u) throws SQLException {
        String sql = "UPDATE usuarios SET nombre=?, edad=? WHERE id=?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, u.getNombre());
            ps.setInt(2, u.getEdad());
            ps.setInt(3, u.getId());
            int filas = ps.executeUpdate();

            if (filas == 0) {
                System.out.println("⚠️ No se encontró ningún usuario con ese ID.");
            }
        }
    }

    // 🔧 TODO: Añadir método eliminarPorId(int id)

    /**
     * ❌ ELIMINAR usuario por ID
     * Borra el usuario con el ID proporcionado.
     */
    public void eliminarPorId(int id) throws SQLException {
        String sql = "DELETE FROM usuarios WHERE id=?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, id);
            int filas = ps.executeUpdate();

            if (filas == 0) {
                System.out.println("⚠️ No se encontró ningún usuario con ese ID.");
            }
        }
    }

    // 🔧 TODO: Añadir método contarUsuarios()

    /**
     * 🔢 CONTAR usuarios registrados
     * Devuelve cuántos usuarios hay actualmente en la tabla.
     */
    public int contarUsuarios() throws SQLException {
        String sql = "SELECT COUNT(*) FROM usuarios";
        try (Statement st = conexion.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getInt(1);  // primera columna del resultado
            } else {
                return 0;
            }
        }
    }
}
