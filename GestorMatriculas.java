/******************************************************************************************
 * EXAMEN 4 – GESTOR DE MATRÍCULAS DE ALUMNOS EN CURSOS
 * ─────────────────────────────────────────────────────────────────────────────
 * OBJETIVO: Desarrollar una aplicación en Java para gestionar alumnos y cursos,
 * usando ArrayList, Map y buenas prácticas de programación modular.
 *
 * 🔹 Parte 1: Clases base
 * - Clase Alumno con: nombre, email, cursoMatriculado
 * - Clase Curso con: código, nombre, horas, modalidad (presencial o no)
 *
 * 🔹 Parte 2: Funcionalidades a implementar (modularizadas)
 * 1. Añadir un nuevo alumno
 * 2. Buscar alumno por email
 * 3. Modificar curso de un alumno dado su email
 * 4. Eliminar un alumno por email
 * 5. Mostrar todos los alumnos matriculados con sus datos
 * 6. Mostrar todos los cursos disponibles
 * 7. Copiar todos los alumnos matriculados en un curso a otra lista
 * 8. Salir
 *
 * ✅ Se valorará el uso de métodos separados, validaciones básicas,
 * uso adecuado de colecciones y buena estructura general.
 ******************************************************************************************/

import java.util.*;

// ========== CLASE CURSO ==========
class Curso {
    private String codigo;
    private String nombre;
    private int horas;
    private boolean presencial;

    public Curso(String codigo, String nombre, int horas, boolean presencial) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.horas = horas;
        this.presencial = presencial;
    }

    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public int getHoras() { return horas; }
    public boolean isPresencial() { return presencial; }

    @Override
    public String toString() {
        return codigo + " - " + nombre + " (" + horas + "h) - Presencial: " + (presencial ? "Sí" : "No");
    }
}

// ========== CLASE ALUMNO ==========
class Alumno {
    private String nombre;
    private String email;
    private Curso cursoMatriculado;

    // Lista general de alumnos en la academia
    static List<Alumno> alumnos = new ArrayList<>();

    public Alumno(String nombre, String email, Curso cursoMatriculado) {
        this.nombre = nombre;
        this.email = email;
        this.cursoMatriculado = cursoMatriculado;
    }

    public String getNombre() { return nombre; }
    public String getEmail() { return email; }
    public Curso getCursoMatriculado() { return cursoMatriculado; }
    public void setCursoMatriculado(Curso curso) { this.cursoMatriculado = curso; }

    @Override
    public String toString() {
        return nombre + " - " + email + " - Curso: " + cursoMatriculado.getNombre();
    }

    // MÉTODOS A IMPLEMENTAR POR LOS ALUMNOS:

    public static void añadirAlumno(Scanner sc, Map<String, Curso> cursosDisponibles) {
      
        // TODO: Pedir datos al usuario (nombre, email, código de curso)
        // TODO: Validar que el código de curso exista en el mapa
        // TODO: Crear alumno y añadir a la lista
        
    }

    public static void buscarAlumnoPorEmail(Scanner sc) {
        // TODO: Pedir email
        // TODO: Buscar en la lista de alumnos
        // TODO: Mostrar datos si existe
    }

    public static void modificarCurso(Scanner sc, Map<String, Curso> cursosDisponibles) {
        // TODO: Pedir email
        // TODO: Buscar alumno
        // TODO: Pedir nuevo código de curso y validar
        // TODO: Cambiar curso del alumno
    }

    public static void eliminarAlumno(Scanner sc) {
        // TODO: Pedir email
        // TODO: Buscar alumno y eliminarlo si existe
    }

    public static void mostrarAlumnos() {
        // TODO: Recorrer la lista y mostrar todos los alumnos
    }

    public static void copiarAlumnosDeCurso(Scanner sc) {
        // TODO: Pedir código de curso
        // TODO: Crear una nueva lista con alumnos de ese curso
        // TODO: Mostrar la nueva lista
    }
}

// ========== CLASE PRINCIPAL ==========
public class GestorMatriculas {

    static Map<String, Curso> cursos = new HashMap<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Inicializar algunos cursos por defecto
        cursos.put("JAVA", new Curso("JAVA", "Programación Java", 60, true));
        cursos.put("WEB", new Curso("WEB", "Desarrollo Web", 45, false));
        cursos.put("BD", new Curso("BD", "Bases de Datos", 50, true));

        int opcion;
        do {
            System.out.println("""
            ========== MENÚ GESTOR DE MATRÍCULAS ==========
            1. Añadir alumno
            2. Buscar alumno por email
            3. Modificar curso del alumno
            4. Eliminar alumno
            5. Mostrar todos los alumnos
            6. Mostrar todos los cursos
            7. Copiar alumnos de un curso
            0. Salir
            ================================================
            """);
            System.out.print("Elige opción: ");
            opcion = sc.nextInt(); sc.nextLine();

            switch (opcion) {
                case 1 -> Alumno.añadirAlumno(sc, cursos);
                case 2 -> Alumno.buscarAlumnoPorEmail(sc);
                case 3 -> Alumno.modificarCurso(sc, cursos);
                case 4 -> Alumno.eliminarAlumno(sc);
                case 5 -> Alumno.mostrarAlumnos();
                case 6 -> mostrarCursos();
                case 7 -> Alumno.copiarAlumnosDeCurso(sc);
                case 0 -> System.out.println("👋 Saliendo del gestor...");
                default -> System.out.println("❌ Opción no válida");
            }

        } while (opcion != 0);

        sc.close();
    }

    // Método auxiliar para mostrar todos los cursos
    public static void mostrarCursos() {
        System.out.println("📚 Cursos disponibles:");
        for (Curso c : cursos.values()) {
            System.out.println(c);
        }
    }
}
