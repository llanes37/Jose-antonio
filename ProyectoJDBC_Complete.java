/******************************************************************************************
 *                        📚 **GUÍA AVANZADA: SQL EN JAVA CON JDBC Y SQLITE**  
 ******************************************************************************************
 * AUTOR: José Antonio Martínez  
 * FECHA: 14/05/2025  
 *
 * INTRODUCCIÓN:
 * -------------
 * Esta aplicación demuestra en detalle cómo utilizar JDBC con SQLite:
 *   • Cargar el driver y conectar a la base de datos embebida.  
 *   • Crear y verificar tablas (usuarios, productos).  
 *   • Realizar operaciones CRUD completas (INSERT, SELECT, UPDATE, DELETE).  
 *   • Añadir datos extra y experimentar con nuevas tablas.  
 *   • Desconectar y liberar recursos correctamente.  
 *
 * OBJETIVOS:
 * ----------
 * 1. Comprender la diferencia entre Statement y PreparedStatement.  
 * 2. Manejar ResultSet para iterar resultados.  
 * 3. Proteger la aplicación ante SQL injection.  
 * 4. Organizar el código en secciones limpias y comentadas.  
 *
 * REQUISITOS:
 * ------------
 * • Añadir `sqlite-jdbc-3.36.0.3.jar` a `libs/` y al classpath en tu IDE.  
 * • No se necesita servidor, la BD se guarda en `miBaseDatos.db`.  
 *
 * USO:
 * ----
 * 1. Compila: `javac ProyectoJDBC_Complete.java`  
 * 2. Ejecuta: `java ProyectoJDBC_Complete`  
 * 3. Sigue el menú paso a paso para probar cada operación.  
 *
 ******************************************************************************************/

import java.sql.*;      // API JDBC básica
import java.util.Scanner;

public class ProyectoJDBC_Complete {

    /** Conexión global a la base de datos */
    private static Connection conexion = null;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            // ================================
            // 📋 MENÚ PRINCIPAL
            // ================================
            System.out.println("\n========== MENÚ JDBC SQLITE ==========");
            System.out.println(" 1. Conectar / Inicializar BD");
            System.out.println(" 2. Crear Tablas (usuarios, productos)");
            System.out.println(" 3. INSERT en 'usuarios'");
            System.out.println(" 4. SELECT * FROM 'usuarios'");
            System.out.println(" 5. UPDATE usuario por ID");
            System.out.println(" 6. DELETE usuario por ID");
            System.out.println(" 7. INSERT en 'productos'");
            System.out.println(" 8. SELECT * FROM 'productos'");
            System.out.println(" 9. UPDATE producto por ID");
            System.out.println("10. DELETE producto por ID");
            System.out.println("11. CREAR y INSERTAR nueva tabla 'categorias'");
            System.out.println("12. SELECT de 'categorias' (ejercicio extra)");
            System.out.println("13. Desconectar y Salir");
            System.out.print("Selecciona opción: ");
            opcion = readInt(sc);

            switch (opcion) {
                case 1: inicializarBD();           break;
                case 2: crearTablas();             break;
                case 3: insertarUsuario(sc);       break;
                case 4: listarUsuarios();          break;
                case 5: actualizarUsuario(sc);     break;
                case 6: eliminarUsuario(sc);       break;
                case 7: insertarProducto(sc);      break;
                case 8: listarProductos();         break;
                case 9: actualizarProducto(sc);    break;
                case 10: eliminarProducto(sc);     break;
                case 11: crearYInsertarCategorias(sc); break;
                case 12: listarCategorias();       break;
                case 13: desconectarBD();          break;
                default: System.out.println("❌ Opción inválida."); break;
            }
        } while (opcion != 13);

        sc.close();
        System.out.println("👋 Programa finalizado. ¡Hasta luego!");
    }

    // ----------------------------------------------------------------------------------
    // 📌 readInt: lee un entero de forma segura, repitiendo hasta que sea válido
    // ----------------------------------------------------------------------------------
    private static int readInt(Scanner sc) {
        while (!sc.hasNextInt()) {
            System.out.print("❌ Entrada inválida. Introduce un número: ");
            sc.next();
        }
        int val = sc.nextInt();
        sc.nextLine(); // consumimos salto de línea
        return val;
    }

    // ==================================================================================
    // SECCIÓN 1: Conectar / Inicializar Base de Datos
    // ==================================================================================
    private static void inicializarBD() {
        try {
            // 📦 Cargar driver SQLite
            Class.forName("org.sqlite.JDBC");
            // 🔌 Conectar (o crear) archivo miBaseDatos.db
            conexion = DriverManager.getConnection("jdbc:sqlite:joaquin.db");
            System.out.println("✅ Conexión establecida en 'miBaseDatos.db'.");
        } catch (ClassNotFoundException e) {
            System.out.println("❌ Driver no encontrado: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("❌ Error conectando BD: " + e.getMessage());
        }
    }

    // ==================================================================================
    // SECCIÓN 2: Crear Tablas
    // ==================================================================================
    private static void crearTablas() {
        if (!checkConexion()) return;
        try (Statement st = conexion.createStatement()) {
            // ----------------------------------------------------------------------------------
            // Crear tabla usuarios: id, nombre, edad
            // ----------------------------------------------------------------------------------
            String sqlU = """
                CREATE TABLE IF NOT EXISTS usuarios (
                  id     INTEGER PRIMARY KEY AUTOINCREMENT,
                  nombre TEXT NOT NULL,
                  edad   INTEGER NOT NULL
                );""";
            st.execute(sqlU);
            System.out.println("✅ Tabla 'usuarios' creada/verificada.");

            // ----------------------------------------------------------------------------------
            // Crear tabla productos: id_producto, nombre, precio
            // ----------------------------------------------------------------------------------
            String sqlP = """
                CREATE TABLE IF NOT EXISTS productos (
                  id_producto INTEGER PRIMARY KEY AUTOINCREMENT,
                  nombre      TEXT NOT NULL,
                  precio      REAL NOT NULL
                );""";
            st.execute(sqlP);
            System.out.println("✅ Tabla 'productos' creada/verificada.");

            // ----------------------------------------------------------------------------------
            // CREANDO TABLA EXTRA: categorias (ejercicio 11)
            // ----------------------------------------------------------------------------------
            String sqlC = """
                CREATE TABLE IF NOT EXISTS categorias (
                  id_categoria INTEGER PRIMARY KEY AUTOINCREMENT,
                  nombre       TEXT NOT NULL
                );""";
            st.execute(sqlC);
            System.out.println("✅ Tabla 'categorias' creada/verificada (extra).");

        } catch (SQLException e) {
            System.out.println("❌ Error creando tablas: " + e.getMessage());
        }
    }

    // Comprueba que la conexión no sea null
    private static boolean checkConexion() {
        if (conexion == null) {
            System.out.println("⚠️ Primero conecta la BD (opción 1).");
            return false;
        }
        return true;
    }

    // ==================================================================================
    // SECCIÓN 3: INSERT en 'usuarios'
    // ==================================================================================
    private static void insertarUsuario(Scanner sc) {
        if (!checkConexion()) return;
        System.out.print("Introduce nombre de usuario: ");
        String nombre = sc.nextLine();
        System.out.print("Introduce edad: ");
        int edad = readInt(sc);

        String sql = "INSERT INTO usuarios(nombre,edad) VALUES(?,?);";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            // Asignamos parámetros seguros
            ps.setString(1, nombre);
            ps.setInt(2, edad);
            ps.executeUpdate();
            System.out.println("✅ Usuario insertado correctamente.");
        } catch (SQLException e) {
            System.out.println("❌ Error insertando usuario: " + e.getMessage());
        }
    }

    // ==================================================================================
    // SECCIÓN 4: SELECT * FROM 'usuarios'
    // ==================================================================================
    private static void listarUsuarios() {
        if (!checkConexion()) return;
        String sql = "SELECT * FROM usuarios;";
        try (Statement st = conexion.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            System.out.println("ID | Nombre         | Edad");
            System.out.println("---+----------------+-----");
            while (rs.next()) {
                // Leemos columnas por nombre
                int id = rs.getInt("id");
                String nom = rs.getString("nombre");
                int ed = rs.getInt("edad");
                System.out.printf("%2d | %-14s | %3d%n", id, nom, ed);
            }
        } catch (SQLException e) {
            System.out.println("❌ Error leyendo usuarios: " + e.getMessage());
        }
    }

    // ==================================================================================
    // SECCIÓN 5: UPDATE usuario por ID
    // ==================================================================================
    private static void actualizarUsuario(Scanner sc) {
        if (!checkConexion()) return;
        System.out.print("ID de usuario a actualizar: ");
        int id = readInt(sc);
        System.out.print("Nuevo nombre: ");
        String nuevo = sc.nextLine();
        System.out.print("Nueva edad: ");
        int edad = readInt(sc);

        String sql = "UPDATE usuarios SET nombre=?,edad=? WHERE id=?;";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, nuevo);
            ps.setInt(2, edad);
            ps.setInt(3, id);
            int afect = ps.executeUpdate();
            System.out.println(afect>0
                ? "✅ Usuario actualizado."
                : "⚠️ No existe usuario con ese ID.");
        } catch (SQLException e) {
            System.out.println("❌ Error actualizando usuario: " + e.getMessage());
        }
    }

    // ==================================================================================
    // SECCIÓN 6: DELETE usuario por ID
    // ==================================================================================
    private static void eliminarUsuario(Scanner sc) {
        if (!checkConexion()) return;
        System.out.print("ID de usuario a eliminar: ");
        int id = readInt(sc);

        String sql = "DELETE FROM usuarios WHERE id=?;";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, id);
            int afect = ps.executeUpdate();
            System.out.println(afect>0
                ? "✅ Usuario eliminado."
                : "⚠️ No existe usuario con ese ID.");
        } catch (SQLException e) {
            System.out.println("❌ Error eliminando usuario: " + e.getMessage());
        }
    }

    // ==================================================================================
    // SECCIÓN 7: INSERT en 'productos'
    // ==================================================================================
    private static void insertarProducto(Scanner sc) {
        if (!checkConexion()) return;
        System.out.print("Nombre del producto: ");
        String nombre = sc.nextLine();
        System.out.print("Precio del producto: ");
        double precio = Double.parseDouble(sc.nextLine());

        String sql = "INSERT INTO productos(nombre,precio) VALUES(?,?);";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ps.setDouble(2, precio);
            ps.executeUpdate();
            System.out.println("✅ Producto insertado correctamente.");
        } catch (SQLException e) {
            System.out.println("❌ Error insertando producto: " + e.getMessage());
        }
    }

    // ==================================================================================
    // SECCIÓN 8: SELECT * FROM 'productos'
    // ==================================================================================
    private static void listarProductos() {
        if (!checkConexion()) return;
        String sql = "SELECT * FROM productos;";
        try (Statement st = conexion.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            System.out.println("ID | Nombre            | Precio");
            System.out.println("---+-------------------+--------");
            while (rs.next()) {
                int id = rs.getInt("id_producto");
                String nom = rs.getString("nombre");
                double pr = rs.getDouble("precio");
                System.out.printf("%2d | %-17s | %6.2f%n", id, nom, pr);
            }
        } catch (SQLException e) {
            System.out.println("❌ Error leyendo productos: " + e.getMessage());
        }
    }

    // ==================================================================================
    // SECCIÓN 9: UPDATE producto por ID
    // ==================================================================================
    private static void actualizarProducto(Scanner sc) {
        if (!checkConexion()) return;
        System.out.print("ID producto a actualizar: ");
        int id = readInt(sc);
        System.out.print("Nuevo nombre de producto: ");
        String nuevo = sc.nextLine();
        System.out.print("Nuevo precio: ");
        double precio = Double.parseDouble(sc.nextLine());

        String sql = "UPDATE productos SET nombre=?,precio=? WHERE id_producto=?;";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, nuevo);
            ps.setDouble(2, precio);
            ps.setInt(3, id);
            int afect = ps.executeUpdate();
            System.out.println(afect>0
                ? "✅ Producto actualizado."
                : "⚠️ No existe producto con ese ID.");
        } catch (SQLException e) {
            System.out.println("❌ Error actualizando producto: " + e.getMessage());
        }
    }

    // ==================================================================================
    // SECCIÓN 10: DELETE producto por ID
    // ==================================================================================
    private static void eliminarProducto(Scanner sc) {
        if (!checkConexion()) return;
        System.out.print("ID producto a eliminar: ");
        int id = readInt(sc);

        String sql = "DELETE FROM productos WHERE id_producto=?;";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, id);
            int afect = ps.executeUpdate();
            System.out.println(afect>0
                ? "✅ Producto eliminado."
                : "⚠️ No existe producto con ese ID.");
        } catch (SQLException e) {
            System.out.println("❌ Error eliminando producto: " + e.getMessage());
        }
    }

    // ==================================================================================
    // SECCIÓN 11: CREAR e INSERTAR en nueva tabla 'categorias'
    // ==================================================================================
    private static void crearYInsertarCategorias(Scanner sc) {
        if (!checkConexion()) return;
        try (Statement st = conexion.createStatement()) {
            // Crear tabla
            st.execute("""
                CREATE TABLE IF NOT EXISTS categorias (
                  id_categoria INTEGER PRIMARY KEY AUTOINCREMENT,
                  nombre       TEXT NOT NULL
                );""");
            System.out.println("✅ Tabla 'categorias' creada/verificada.");

            // Insertar categorías de ejemplo
            PreparedStatement ps = conexion.prepareStatement(
                "INSERT INTO categorias(nombre) VALUES(?);"
            );
            String[] ejemplos = {"Electrónica", "Ropa", "Alimentación"};
            for (String cat : ejemplos) {
                ps.setString(1, cat);
                ps.executeUpdate();
            }
            ps.close();
            System.out.println("✅ Categorías de ejemplo insertadas.");
        } catch (SQLException e) {
            System.out.println("❌ Error en categorías: " + e.getMessage());
        }
    }

    // ==================================================================================
    // SECCIÓN 12: SELECT * FROM 'categorias' (ejercicio extra)
    // ==================================================================================
    private static void listarCategorias() {
        if (!checkConexion()) return;
        String sql = "SELECT * FROM categorias;";
        try (Statement st = conexion.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            System.out.println("ID | Nombre de categoría");
            System.out.println("---+--------------------");
            while (rs.next()) {
                System.out.printf("%2d | %s%n",
                    rs.getInt("id_categoria"),
                    rs.getString("nombre")
                );
            }
        } catch (SQLException e) {
            System.out.println("❌ Error leyendo categorías: " + e.getMessage());
        }
    }

    // ==================================================================================
    // SECCIÓN 13: Desconexión y limpieza
    // ==================================================================================
    private static void desconectarBD() {
        if (conexion != null) {
            try {
                conexion.close();
                System.out.println("✅ Conexión cerrada.");
            } catch (SQLException e) {
                System.out.println("❌ Error cerrando BD: " + e.getMessage());
            }
            conexion = null;
        } else {
            System.out.println("⚠️ No había conexión activa.");
        }
    }
}
