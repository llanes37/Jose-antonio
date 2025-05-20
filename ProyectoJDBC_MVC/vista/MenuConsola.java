package vista;

import java.util.Scanner;

/**
 * ✅ VISTA (MenuConsola)
 * Esta clase se encarga exclusivamente de mostrar el menú al usuario
 * y devolver la opción que el usuario ha elegido.
 * 
 * 🎓 TEORÍA:
 * En el patrón MVC, la Vista se encarga de:
 *   - Mostrar datos por pantalla (salida)
 *   - Pedir datos al usuario (entrada)
 *   - No contiene lógica ni llamadas a la base de datos
 *
 * 🎯 OBJETIVO DEL ALUMNO:
 * Entender que esta clase solo imprime por consola y recoge una opción,
 * sin tomar decisiones ni gestionar datos.
 */
public class MenuConsola {

    /**
     * 📋 MENÚ PRINCIPAL
     * Muestra las opciones disponibles y devuelve la opción introducida por el usuario.
     * 
     * @param sc Scanner que viene del main para no crear uno nuevo (buena práctica)
     * @return opción elegida como int
     */
    public static int mostrarMenuYLeerOpcion(Scanner sc) {
        System.out.println("\n====== MENÚ USUARIOS ======");
        System.out.println("1. ➕ Insertar usuario");
        System.out.println("2. 📋 Listar usuarios");

        // 🔧 TODO: Añadir opción 3 - Actualizar usuario por ID
        // 🧪 TAREA: Muestra "3. ✏️ Actualizar usuario"

        // 🔧 TODO: Añadir opción 4 - Eliminar usuario por ID
        // 🧪 TAREA: Muestra "4. ❌ Eliminar usuario"

        // 🔧 TODO: Añadir opción 5 - Contar usuarios
        // 🧪 TAREA: Muestra "5. 🔢 Mostrar número total de usuarios"

        System.out.println("0. 🚪 Salir");
        System.out.print("Selecciona una opción: ");

        // 🎯 Convertimos entrada a int (¡puede lanzar excepción si el texto no es un número!)
        return Integer.parseInt(sc.nextLine());
    }
}
