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