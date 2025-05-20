package controlador;

// 📚 Librerías necesarias
import java.sql.*;                        // JDBC: Para conectar y operar con SQLite
import java.util.List;                    // List: para manejar listas de usuarios
import java.util.Scanner;                // Scanner: entrada por teclado
import modelo.Usuario;                   // Modelo: clase Usuario (representa un registro)
import dao.UsuarioDAO;                   // DAO: clase que accede a la base de datos
import vista.MenuConsola;                // Vista: clase que muestra el menú por consola

/**
 * ✅ CONTROLADOR (MainApp)
 * Esta clase es el "cerebro" del programa.
 * Se encarga de:
 *  - Establecer la conexión con la base de datos
 *  - Mostrar el menú (vista)
 *  - Llamar a los métodos DAO para hacer el CRUD sobre usuarios
 *
 * 🎓 TEORÍA:
 * En el patrón MVC (Modelo-Vista-Controlador), esta clase representa el **Controlador**.
 * El controlador **coordina** la lógica entre los datos (modelo), la base de datos (DAO) y la vista (interfaz).
 *
 * 🔍 OBJETIVO DEL ALUMNO:
 * Entender que esta clase orquesta todo: no contiene datos, ni lógica SQL, ni menús directamente,
 * solo llama a las demás piezas para que colaboren.
 */
public class MainApp {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            // 🔌 Conectamos a la base de datos SQLite
            // El archivo 'miBaseDatos.db' se crea automáticamente si no existe
            Connection conexion = DriverManager.getConnection("jdbc:sqlite:miBaseDatos.db");

            // 🔄 Creamos una instancia del DAO (conexión compartida)
            UsuarioDAO dao = new UsuarioDAO(conexion);

            // 🧱 Creamos la tabla 'usuarios' si no existe ya
            Statement st = conexion.createStatement();
            st.execute("""
                CREATE TABLE IF NOT EXISTS usuarios (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,  -- Clave primaria autoincremental
                    nombre TEXT NOT NULL,                  -- Nombre del usuario (obligatorio)
                    edad INTEGER NOT NULL                  -- Edad del usuario (obligatorio)
                );
            """);

            // 🔁 Menú interactivo: el usuario puede elegir varias opciones
            int opcion;
            do {
                // 🖥️ Mostrar menú y capturar opción del usuario
                opcion = MenuConsola.mostrarMenuYLeerOpcion(sc);

                switch (opcion) {
                    case 1 -> {
                        // ➕ INSERTAR nuevo usuario
                        System.out.print("Nombre: ");
                        String nombre = sc.nextLine();  // Leer nombre del usuario

                        System.out.print("Edad: ");
                        int edad = Integer.parseInt(sc.nextLine());  // Leer edad

                        dao.insertar(new Usuario(0, nombre, edad));  // ID=0, se autogenera
                        System.out.println("✅ Usuario insertado.");
                    }

                    case 2 -> {
                        // 📋 LISTAR todos los usuarios
                        List<Usuario> usuarios = dao.listarTodos();
                        usuarios.forEach(System.out::println);  // Mostrar uno por uno
                    }

                    // 🚧 Aquí podrías añadir más opciones (actualizar, eliminar...)
                    
                    // 🔧 TODO: Añadir opción 3 - Actualizar usuario por ID
                    // 🔁 TAREA: Pide el ID, nuevo nombre y nueva edad al alumno
                    //          Luego llama a dao.actualizar(...) (tendrás que crearlo si no existe)

                    // 🔧 TODO: Añadir opción 4 - Eliminar usuario por ID
                    // 🔁 TAREA: Pregunta al alumno el ID del usuario a eliminar y llama a dao.eliminar(...)

                    // 🔧 TODO: Añadir opción 5 - Mostrar número total de usuarios
                    // 💡 IDEA: Usa SELECT COUNT(*) y muéstralo en pantalla
                }

            } while (opcion != 0);  // 0 = Salir del programa

            // ✅ Cerramos la conexión al final
            conexion.close();
            System.out.println("👋 Conexión cerrada. Fin del programa.");

        } catch (Exception e) {
            // ⚠️ Cualquier error lo mostramos
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
}
