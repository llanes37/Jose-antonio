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

/******************************************************************************************
 * 🧪 SECCIÓN 2: CLASE DE PRUEBAS UNITARIAS - CafeteraTest
 * ----------------------------------------------------------------------------------------
 * Esta clase prueba TODOS los métodos de la clase Cafetera de forma independiente.
 * Utiliza las principales anotaciones de JUnit 5:
 * - @Test → indica que es un caso de prueba
 * - @BeforeEach → se ejecuta antes de cada test (preparación)
 * - @AfterEach → se ejecuta después de cada test (limpieza)
 * - @AfterAll → muestra mensaje tras finalizar todos los tests
 ******************************************************************************************/

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class CafeteraTest {
    private Cafetera cafetera; // Objeto que se probará en cada test

    /**
     * 🔧 Método que se ejecuta antes de cada test para asegurar entorno limpio
     */
    @BeforeEach
    public void setUp() {
        cafetera = new Cafetera(500); // Inicia cada prueba con una cafetera llena de 500ml
    }

    /**
     * ✅ Verifica que el constructor parametrizado funciona correctamente
     */
    @Test
    public void testConstructorParametrizado() {
        assertEquals(500, cafetera.getCapacidadMaxima());
        assertEquals(500, cafetera.getCantidadActual());
    }

    /**
     * ✅ Verifica el constructor por defecto: capacidad 1000 y vacía
     */
    @Test
    public void testConstructorPorDefecto() {
        Cafetera caf = new Cafetera();
        assertEquals(1000, caf.getCapacidadMaxima());
        assertEquals(0, caf.getCantidadActual());
    }

    /**
     * 🔁 Verifica que llenar la cafetera tras vaciarla la deja llena
     */
    @Test
    public void testLlenarCafetera() {
        cafetera.vaciarCafetera();
        cafetera.llenarCafetera();
        assertEquals(500, cafetera.getCantidadActual());
    }

    /**
     * ☕ Prueba servir una taza con suficiente café
     */
    @Test
    public void testServirTazaConSuficiente() {
        cafetera.servirTaza(150);
        assertEquals(350, cafetera.getCantidadActual());
    }

    /**
     * ☕ Prueba servir una taza con más cantidad que la disponible
     */
    @Test
    public void testServirTazaInsuficiente() {
        cafetera.servirTaza(600);
        assertEquals(0, cafetera.getCantidadActual());
    }

    /**
     * 🚫 Prueba vaciar completamente la cafetera
     */
    @Test
    public void testVaciarCafetera() {
        cafetera.vaciarCafetera();
        assertEquals(0, cafetera.getCantidadActual());
    }

    /**
     * 🔍 Comprueba el método quedaCafe() con y sin contenido
     */
    @Test
    public void testQuedaCafe() {
        assertTrue(cafetera.quedaCafe());
        cafetera.vaciarCafetera();
        assertFalse(cafetera.quedaCafe());
    }

    /**
     * ➕ Verifica que se puede agregar café correctamente si no desborda
     */
    @Test
    public void testAgregarCafeCorrecto() {
        cafetera.vaciarCafetera();
        cafetera.agregarCafe(300);
        assertEquals(300, cafetera.getCantidadActual());
    }

    /**
     * ⚠️ Verifica que agregar demasiado café lanza excepción
     */
    @Test
    public void testAgregarCafeDesbordado() {
        assertThrows(IllegalArgumentException.class, () -> cafetera.agregarCafe(1000));
    }

    /**
     * 🧹 Método de limpieza opcional tras cada test
     */
    @AfterEach
    public void tearDown() {
        cafetera = null; // Liberamos la referencia para evitar errores posteriores
    }

    /**
     * 📢 Mensaje final para confirmar la ejecución total de pruebas
     */
    @AfterAll
    public static void finDePruebas() {
        System.out.println("✅ TODOS LOS TESTS DE CAFETERA FINALIZADOS CORRECTAMENTE ✅");
    }
}

/******************************************************************************************
 * 📚 RESUMEN FINAL PARA REPASO RÁPIDO (ANTES DEL EXAMEN)
 * ----------------------------------------------------------------------------------------
 * ➤ Toda clase a testear debe tener una clase separada de tests.
 * ➤ Cada test debe:
 *     - Ser independiente
 *     - Usar una aserción clara (assertEquals, assertTrue, assertThrows...)
 * ➤ Anotaciones más importantes de JUnit 5:
 *     @Test, @BeforeEach, @AfterEach, @BeforeAll, @AfterAll
 * ➤ assertThrows se usa para comprobar excepciones (desbordamiento, errores...)
 * ➤ Usa IDE como NetBeans o VS Code para ver resultados en verde/rojo fácilmente
 ******************************************************************************************/
