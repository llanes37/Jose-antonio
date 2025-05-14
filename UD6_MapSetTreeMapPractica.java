/******************************************************************************************
 * 🔧 UD6 - PRÁCTICA GUIADA: MAP, SET y TREEMAP EN JAVA
 * ─────────────────────────────────────────────────────────────────────────────
 * 🎯 OBJETIVO: Comprender y practicar Map (HashMap), Set (HashSet)
 *              y TreeMap con ejemplos comentados para Better Comments.
 * 📖 INSTRUCCIONES: Ejecuta el menú y selecciona cada sección para ver teoría,
 *                   demostraciones y ejercicios.
 ******************************************************************************************/

import java.util.*;

public class UD6_MapSetTreeMapPractica {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("\n==== MENÚ PRINCIPAL ====");
            System.out.println("1 - Sección MAP");
            System.out.println("2 - Sección SET");
            System.out.println("3 - Sección TREEMAP");
            System.out.println("4 - EJERCICIOS FINALES");
            System.out.println("0 - Salir");
            System.out.print("Elige sección: ");
            opcion = sc.nextInt(); sc.nextLine();
            switch (opcion) {
                case 1 -> mapSection();
                case 2 -> setSection();
                case 3 -> treeMapSection();
                case 4 -> finalExercises();
                case 0 -> System.out.println("👋 Saliendo...");
                default -> System.out.println("❌ Opción no válida.");
            }
        } while (opcion != 0);
        sc.close();
    }

    // ? SECCIÓN MAP: HashMap - teoría y práctica
    public static void mapSection() {
        System.out.println("\n--- SECCIÓN MAP (HashMap) ---");
        // * Teoría:
        // * - Map<K,V> asocia claves únicas (K) a valores (V).
        // * - HashMap implementa Map usando tabla hash, O(1) promedio para get/put.
        // * - No mantiene orden de inserción.
        
        // * DEMOSTRACIÓN de operaciones básicas:
        Map<String,String> mapa = new HashMap<>();             // Crear HashMap vacío
        mapa.put("A", "Apple");                            // Añadir clave "A"->"Apple"
        mapa.put("B", "Banana");                           // Añadir clave "B"->"Banana"
        System.out.println("Contenido inicial: " + mapa);    // Mostrar todo
        String valorB = mapa.get("B");                       // get por clave
        System.out.println("get(\"B\"): " + valorB);
        mapa.remove("A");                                     // Eliminar clave "A"
        System.out.println("Después de remove(\"A\"): " + mapa);

        // TODO EJERCICIOS MAP:
        // TODO 1. Añade la clave "C" con valor "Cherry" y muéstralo.
        // TODO 2. Comprueba si existe la clave "D" y muestra mensaje.
        // TODO 3. Itera usando entrySet() e imprime cada par.
    }

    // ? SECCIÓN SET: HashSet - teoría y práctica
    public static void setSection() {
        System.out.println("\n--- SECCIÓN SET (HashSet) ---");
        // * Teoría:
        // * - Set<E> almacena elementos únicos.
        // * - HashSet usa tabla hash, no ordena.
        // * - Ideal para eliminar duplicados.

        // * DEMOSTRACIÓN de operaciones básicas:
        Set<Integer> set = new HashSet<>();                   // Crear HashSet vacío
        set.add(1);                                            // Añadir 1
        set.add(2);                                            // Añadir 2
        set.add(2);                                            // Intentar duplicado
        System.out.println("Contenido set: " + set);
        boolean has2 = set.contains(2);                        // contains
        System.out.println("contains(2): " + has2);
        set.remove(1);                                         // remove
        System.out.println("Después de remove(1): " + set);

        // TODO EJERCICIOS SET:
        // TODO 1. Añade 3,4,5 al set y muéstralo.
        // TODO 2. Crea set con duplicados [5,5,6,6,7] y demuestra unicidad.
        // TODO 3. Itera con for-each y suma los valores.
    }

    // ? SECCIÓN TREEMAP: TreeMap - teoría y práctica
    public static void treeMapSection() {
        System.out.println("\n--- SECCIÓN TREEMAP ---");
        // * Teoría:
        // * - TreeMap<K,V> implementa SortedMap y ordena claves.
        // * - firstKey(), lastKey(), headMap(), tailMap().

        // * DEMOSTRACIÓN de operaciones básicas:
        TreeMap<String,Integer> tm = new TreeMap<>();          // Crear TreeMap vacío
        tm.put("A", 100);
        tm.put("C", 300);
        tm.put("B", 200);
        System.out.println("TreeMap ordenado: " + tm);
        System.out.println("firstKey(): " + tm.firstKey());
        System.out.println("lastKey(): " + tm.lastKey());
        System.out.println("floorKey(\"B\"): " + tm.floorKey("B"));

        // TODO EJERCICIOS TREEMAP:
        // TODO 1. Añade "D"->400 y muestra.
        // TODO 2. Obtén headMap("C") y muéstralo.
        // TODO 3. Itera keySet() e imprime clave->valor.
    }

    // * EJERCICIOS FINALES: combinar Map, Set y TreeMap
    public static void finalExercises() {
        System.out.println("\n--- EJERCICIOS FINALES ---");
        // TODO a) De TreeMap de colores, crea Set con sus valores.
        // TODO b) De HashSet de números, convierte a List y ordénala.
        // TODO c) Implementa Comparator para invertir TreeMap.
        // TODO d) Crea Map letra inicial -> lista de colores.
        // TODO e) BONUS: Guarda/clona Map desde archivo.
    }
}
