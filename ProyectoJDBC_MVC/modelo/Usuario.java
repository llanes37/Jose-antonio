package modelo;

/**
 * ✅ MODELO: Clase Usuario
 * Representa un registro (fila) de la tabla 'usuarios' en la base de datos.
 * Esta clase encapsula los atributos básicos y permite manipular usuarios como objetos.
 * 
 * 🎓 TEORÍA:
 * En el patrón MVC, esta clase forma parte del **Modelo**:
 *   - Tiene solo datos (atributos)
 *   - No accede a la base de datos
 *   - No contiene lógica de menú
 * 
 * 🧱 Equivalencia con la tabla SQL:
 *   id       -> int id
 *   nombre   -> String nombre
 *   edad     -> int edad
 * 
 * 🎯 OBJETIVO DEL ALUMNO:
 * Comprender que esta clase solo guarda y transporta información de forma estructurada.
 * Se usa para enviar o recibir datos del DAO.
 */
public class Usuario {

    // 🧩 Atributos (campos)
    private int id;
    private String nombre;
    private int edad;

    /**
     * 🧱 Constructor principal
     * Recibe todos los datos del usuario. Se usa al insertar, actualizar o cargar desde la BD.
     * 
     * 💡 Normalmente al insertar se pone id = 0, y SQLite lo autogenera.
     */
    public Usuario(int id, String nombre, int edad) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
    }

    // 📤 GETTERS (obtener datos)
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public int getEdad() { return edad; }

    // 📥 SETTERS (modificar datos)
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setEdad(int edad) { this.edad = edad; }

    /**
     * 🔁 Método toString()
     * Sirve para mostrar el objeto de forma legible en consola.
     * Se usa cuando hacemos: System.out.println(usuario);
     */
    @Override
    public String toString() {
        return id + " | " + nombre + " | " + edad;
    }
}
    // 🔧 TODO: Añadir validaciones opcionales
    // 🛠️ EJERCICIO: Comprobar en el constructor que la
