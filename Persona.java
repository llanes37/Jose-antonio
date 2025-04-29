/*
 * ALUMNO: José Antonio - Proyecto Depurador Completo
 * ASIGNATURA: Programación
 * PROFESOR: [Nombre del Profesor]
 * FECHA: 30 de abril de 2025
 *
 * DESCRIPCIÓN GENERAL:
 * Este proyecto consiste en crear un programa sencillo en Java que represente una persona, utilizar NetBeans para depurarlo,
 * poner puntos de ruptura (normales y condicionales), inspeccionar variables y modificar sus valores durante la ejecución.
 * Posteriormente, realizar capturas de pantalla que demuestren todo el proceso y explicarlo en un documento Word para su entrega.
 *
 * ---------------------
 * INSTRUCCIONES BÁSICAS DE DEPURACIÓN EN NETBEANS:
 * ---------------------
 * 1. Abrir NetBeans.
 * 2. Crear un nuevo proyecto Java: File > New Project > Java Application.
 * 3. Copiar este archivo dentro del proyecto.
 * 4. Para poner un breakpoint normal:
 *    - Haz clic en la parte izquierda del número de línea.
 * 5. Para poner un breakpoint condicional:
 *    - Clic derecho sobre el punto rojo del breakpoint > Properties > Condition.
 *    - Escribir una condición, por ejemplo: edad > 30.
 * 6. Iniciar la depuración:
 *    - Botón "Debug Project" (el del insecto verde) o pulsar F5.
 * 7. Inspeccionar variables:
 *    - Cuando el programa se detenga, abrir la ventana Variables.
 *    - Ver los valores actuales y modificarlos haciendo doble clic en ellos.
 * 8. Capturas recomendadas:
 *    - Poner breakpoint normal.
 *    - Poner breakpoint condicional.
 *    - Mostrar variables.
 *    - Cambiar una variable durante la depuración.
 *    - Ejecutar Step Into (F7), Step Over (F8).
 *
 * NOTA FINAL:
 * Redactar un Word explicando cada captura, por ejemplo:
 * - "En esta captura vemos cómo he puesto un breakpoint normal en la línea 40..."
 * - "Aquí se muestra cómo he cambiado la edad de 29 a 30 durante la depuración..."
 */

 public class Persona {
    // Atributos de la clase Persona
    private String nombre;
    private int edad;
    private double altura;
    private String ciudad;

    // Constructor que inicializa todos los atributos
    public Persona(String nombre, int edad, double altura, String ciudad) {
        this.nombre = nombre;
        this.edad = edad;
        this.altura = altura;
        this.ciudad = ciudad;
    }

    // Método para saludar
    public void saludar() {
        System.out.println("Hola, me llamo " + nombre + ", tengo " + edad + " años, mido " + altura + " metros y vivo en " + ciudad + ".");
        // Breakpoint normal recomendado aquí
    }

    // Método para cumplir años
    public void cumplirAnios() {
        edad++;
        System.out.println("¡He cumplido años! Ahora tengo " + edad + " años.");
        // Breakpoint condicional recomendado aquí: edad > 30
    }

    // Método para cambiar el nombre
    public void cambiarNombre(String nuevoNombre) {
        nombre = nuevoNombre;
        System.out.println("Ahora me llamo " + nombre + ".");
        // Inspección de cambio de variable nombre
    }

    // Método para actualizar la altura
    public void actualizarAltura(double nuevaAltura) {
        altura = nuevaAltura;
        System.out.println("Ahora mi altura es " + altura + " metros.");
        // Inspección de cambio de variable altura
    }

    // Método para cambiar de ciudad
    public void cambiarCiudad(String nuevaCiudad) {
        ciudad = nuevaCiudad;
        System.out.println("Ahora vivo en " + ciudad + ".");
        // Breakpoint para observar cambio de ciudad
    }

    // Método para mostrar toda la información actual
    public void mostrarInfo() {
        System.out.println("--- Información de la persona ---");
        System.out.println("Nombre: " + nombre);
        System.out.println("Edad: " + edad);
        System.out.println("Altura: " + altura + " metros");
        System.out.println("Ciudad: " + ciudad);
        // Breakpoint útil para ver todos los atributos juntos
    }

    // Método principal para ejecutar el programa
    public static void main(String[] args) {
        Persona alumno = new Persona("José Antonio", 29, 1.75, "Madrid");

        alumno.saludar();
        alumno.cumplirAnios();
        alumno.saludar();

        alumno.cambiarNombre("Antonio José");
        alumno.actualizarAltura(1.78);
        alumno.cambiarCiudad("Barcelona");
        alumno.mostrarInfo();
        alumno.saludar();
    }
}
