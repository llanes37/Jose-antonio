/******************************************************************************************
 * 📘 PROYECTO DE ESTUDIO COMPLETO: CAFETERA Y TESTS UNITARIOS EN JAVA (JUnit 5)
 * ----------------------------------------------------------------------------------------
 * ✅ OBJETIVO: Comprender y dominar el uso de pruebas unitarias con JUnit 5 para el examen.
 * ✅ INCLUYE:
 *   1) Teoría explicada paso a paso sobre JUnit 5
 *   2) Clase Cafetera con comentarios detallados
 *   3) Clase de test unitarios completa, explicando el propósito de cada test
 *   4) Buenas prácticas y recordatorios clave para aprobar con soltura
 ******************************************************************************************/

/******************************************************************************************
 * 🧠 SECCIÓN 1: CLASE DE PRODUCCIÓN - Cafetera
 * ----------------------------------------------------------------------------------------
 * Esta clase representa una cafetera simple con funciones típicas:
 * - Llenar, vaciar, servir taza, agregar café
 * - Verificar si queda café o no
 * - Lanza excepción si se desborda al añadir café
 ******************************************************************************************/

public class Cafetera {
    // 🔒 Atributo inmutable: capacidad máxima de la cafetera
    private final int capacidadMaxima;

    // 📦 Atributo modificable: cantidad actual de café disponible
    private int cantidadActual;

    /**
     * 🛠️ Constructor con parámetro:
     * Crea una cafetera llena con la capacidad especificada.
     * Ejemplo: new Cafetera(500) -> capacidadMáxima = 500, cantidadActual = 500
     */
    public Cafetera(int capacidad) {
        this.capacidadMaxima = capacidad;
        this.cantidadActual = capacidad;
    }

    /**
     * 🛠️ Constructor por defecto:
     * Crea una cafetera vacía con capacidad máxima de 1000 ml
     */
    public Cafetera() {
        this.capacidadMaxima = 1000;
        this.cantidadActual = 0;
    }

    // 📤 Getter: devuelve la capacidad máxima
    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }

    // 📥 Getter: devuelve la cantidad actual de café
    public int getCantidadActual() {
        return cantidadActual;
    }

    /**
     * 🔄 Método que llena la cafetera a su capacidad máxima
     */
    public void llenarCafetera() {
        this.cantidadActual = this.capacidadMaxima;
    }

    /**
     * ☕ Método que sirve una taza de café
     * Si la cantidad solicitada es mayor que la disponible, la vacía por completo
     * @param cant Cantidad de café que se desea servir
     */
    public void servirTaza(int cant) {
        if (cant > this.cantidadActual) {
            this.cantidadActual = 0; // No hay suficiente: se sirve lo que hay
        } else {
            this.cantidadActual -= cant; // Se descuenta normalmente
        }
    }

    /**
     * 🚫 Método que vacía por completo la cafetera
     */
    public void vaciarCafetera() {
        this.cantidadActual = 0;
    }

    /**
     * ✅ Método que comprueba si queda café
     * @return true si cantidadActual > 0
     */
    public boolean quedaCafe() {
        return this.cantidadActual > 0;
    }

    /**
     * ➕ Método que agrega café a la cafetera
     * ⚠️ Si se supera la capacidad máxima, lanza excepción
     * @param cant Cantidad a añadir
     */
    public void agregarCafe(int cant) {
        this.cantidadActual += cant;
        if (this.cantidadActual > this.capacidadMaxima) {
            throw new IllegalArgumentException("Cafetera desbordada");
        }
    }
}


