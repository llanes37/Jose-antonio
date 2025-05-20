package modelo;

/**
 * ✅ MODELO: Clase Usuario
 * Representa un registro (fila) de la tabla 'usuarios' en la base de datos.
 * Esta clase encapsula los atributos básicos y permite manipular usuarios como
 * objetos.
 * 
 * 🎓 TEORÍA:
 * En el patrón MVC, esta clase forma parte del **Modelo**:
 * - Tiene solo datos (atributos)
 * - No accede a la base de datos
 * - No contiene lógica de menú
 * 
 * 🧱 Equivalencia con la tabla SQL:
 * id -> int id
 * nombre -> String nombre
 * edad -> int edad
 * 
 * 🎯 OBJETIVO DEL ALUMNO:
 * Comprender que esta clase solo guarda y transporta información de forma
 * estructurada.
 * Se usa para enviar o recibir datos del DAO.
 */
public class Usuario {

    // 🧩 Atributos (campos)
    private int id;
    private String nombre;
    private int edad;

    /**
     * 🧱 Constructor principal
     * Recibe todos los datos del usuario. Se usa al insertar, actualizar o cargar
     * desde la BD.
     * 
     * 💡 Normalmente al insertar se pone id = 0, y SQLite lo autogenera.
     */
    public Usuario(int id, String nombre, int edad) {
        if (nombre == null || nombre.trim().isEmpty()) {
            System.out.println("⚠️ Nombre vacío recibido. Se asigna 'Desconocido'.");
            this.nombre = "Desconocido";
        } else {
            this.nombre = nombre;
        }
        this.id = id;

       // 🔍 Validamos la edad
    if (edad < 0) {
        System.out.println("⚠️ Edad negativa recibida. Se asigna 0 por defecto.");
        this.edad = 0;
    } else {
        this.edad = edad;
    }
    }

    // 📤 GETTERS (obtener datos)
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    // 📥 SETTERS (modificar datos)
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

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
/**
 * 🧱 Constructor principal
 * Recibe todos los datos del usuario. Se usa al insertar, actualizar o cargar
 * desde la BD.
 * 
 * 💡 Normalmente al insertar se pone id = 0, y SQLite lo autogenera.
 * 
 * 🔧 TODO: Añadir validaciones opcionales
 * ──────────────────────────────────────────────
 * 🛠️ EJERCICIOS PARA EL ALUMNO:
 * 
 * 1️⃣ Validar que el nombre no esté vacío o en blanco
 * if (nombre == null || nombre.trim().isEmpty()) {
 * this.nombre = "Desconocido"; // o lanzar excepción
 * }
 * 
 * 2️⃣ Validar que la edad sea positiva
 * if (edad < 0) {
 * this.edad = 0; // o lanzar excepción
 * }
 * 
 * 3️⃣ BONUS: Si quieres impedir datos inválidos totalmente
 * Usa: throw new IllegalArgumentException("Edad no válida");
 * 
 * 4️⃣ Crear segundo constructor SOLO con nombre y edad (sin ID)
 * Se puede usar antes de insertar, y el ID se autogenerará luego.
 * 
 * 5️⃣ Añadir campo opcional 'email' y validarlo con .contains("@")
 * 
 * 🎯 OBJETIVO DIDÁCTICO:
 * Aprender que el modelo puede proteger sus datos
 * y evitar errores antes de llegar a la base de datos.
 */
