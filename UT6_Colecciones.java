/******************************************************************************************
 *                        📚 **TEORÍA Y CONCEPTOS: COLECCIONES EN JAVA**
 * ──────────────────────────────────────────────────────────────────────────────
 * En esta práctica aprenderás a:
 * 
 * ✅ **Comprender el concepto de colecciones en Java y su importancia.**
 * ✅ **Distinguir entre listas, conjuntos y mapas.**
 * ✅ **Utilizar las clases `ArrayList`, `LinkedList`, `HashSet`, `TreeSet`, `HashMap` y `TreeMap`.**
 * ✅ **Manejar iteradores para recorrer y modificar colecciones.**
 * ✅ **Aplicar la programación genérica para trabajar con colecciones de distintos tipos.**
 * ✅ **Clonar colecciones y objetos en Java.**
 * 
 * 🚀 **¡Explora, experimenta y mejora el código!**
 ******************************************************************************************/

 import java.util.*;

 /*
  * 🧠 **TEORÍA GLOBAL: COLECCIONES EN JAVA**
  * ──────────────────────────────────────────────────────────────
  * 
  * 🔵 **¿Qué es una Colección?**
  *  - Una colección es un conjunto de elementos que se almacenan y gestionan de manera dinámica.
  *  - Java proporciona el **Java Collections Framework (JCF)** para facilitar el trabajo con colecciones.
  *  - Las principales interfaces son `List`, `Set` y `Map`, cada una con diferentes propósitos.
  * 
  * 🔹 **Interfaces principales y sus características:**
  * 
  * 1️⃣ **List (Lista ordenada, permite duplicados)**
  *     - Implementaciones: `ArrayList`, `LinkedList`
  * 
  * 2️⃣ **Set (Conjunto, sin duplicados)**
  *     - Implementaciones: `HashSet`, `TreeSet`, `LinkedHashSet`
  * 
  * 3️⃣ **Map (Estructura clave-valor)**
  *     - Implementaciones: `HashMap`, `TreeMap`, `LinkedHashMap`
  */
 
 public class UT6_Colecciones {
     public static void main(String[] args) {
         System.out.println("🔹 Listas en Java:");
         manejarListas();
 
         System.out.println("\n🔹 Conjuntos en Java:");
         manejarConjuntos();
 
         System.out.println("\n🔹 Mapas en Java:");
         manejarMapas();
 
         System.out.println("\n🔹 Iteradores:");
         manejarIteradores();
 
         System.out.println("\n🔹 Clonación de Objetos:");
         clonarColecciones();
     }
 
     // * 📖 TEORÍA: LISTAS (ARRAYLIST Y LINKEDLIST)
     // ──────────────────────────────────────────────────────────────
     // ? Una lista (List) es una colección ordenada que permite elementos duplicados.
     // ? Se accede a los elementos por índice y permite modificaciones dinámicas.
     public static void manejarListas() {
         // Creación de una lista de cadenas
         List<String> lista = new ArrayList<>();
         lista.add("Java");
         lista.add("Python");
         lista.add("JavaScript");
 
         // 📌 Ejemplo de acceso y modificación
         System.out.println("Lista original: " + lista);
         lista.add(1, "C++"); // Insertar en una posición específica
         System.out.println("Lista después de insertar C++: " + lista);
         lista.remove("JavaScript"); // Eliminar por valor
         System.out.println("Lista después de eliminar JavaScript: " + lista);
 
         // ! ✅ EJERCICIOS:
         // * 1️⃣ Escribe una función que reciba una lista y elimine todos los elementos duplicados.
         // * 2️⃣ Implementa una función que busque un elemento en la lista y retorne su índice.
     }
 
     // * 📖 TEORÍA: CONJUNTOS (HASHSET Y TREESET)
     // ──────────────────────────────────────────────────────────────
     // ? Un conjunto (Set) almacena elementos únicos, sin duplicados.
     // ? `HashSet` no garantiza orden, mientras que `TreeSet` ordena los elementos.
     public static void manejarConjuntos() {
         // Creación de un conjunto HashSet
         Set<Integer> numeros = new HashSet<>();
         numeros.add(5);
         numeros.add(10);
         numeros.add(5); // No se añade porque es un duplicado
 
         System.out.println("HashSet (sin orden y sin duplicados): " + numeros);
 
         // TreeSet ordena automáticamente los valores
         Set<Integer> numerosOrdenados = new TreeSet<>(numeros);
         System.out.println("TreeSet (ordenado): " + numerosOrdenados);
 
         // ! ✅ EJERCICIOS:
         // * 1️⃣ Escribe una función que agregue números a un HashSet y verifique si un número ya está presente.
         // * 2️⃣ Implementa una función que convierta una lista con duplicados en un conjunto único.
     }
 
     // * 📖 TEORÍA: MAPAS (HASHMAP Y TREEMAP)
     // ──────────────────────────────────────────────────────────────
     // ? Un mapa (Map) almacena pares clave-valor.
     // ? `HashMap` no mantiene el orden, mientras que `TreeMap` ordena por clave.
     public static void manejarMapas() {
         // Creación de un HashMap
         Map<Integer, String> mapa = new HashMap<>();
         mapa.put(1, "Rojo");
         mapa.put(2, "Verde");
         mapa.put(3, "Azul");
 
         System.out.println("HashMap (sin orden): " + mapa);
         System.out.println("Valor asociado a la clave 2: " + mapa.get(2));
 
         // TreeMap mantiene el orden de las claves
         Map<Integer, String> mapaOrdenado = new TreeMap<>(mapa);
         System.out.println("TreeMap (ordenado por clave): " + mapaOrdenado);
 
         // ! ✅ EJERCICIOS:
         // * 1️⃣ Escribe una función que almacene nombres y edades en un `HashMap` y permita buscar por nombre.
         // * 2️⃣ Implementa un `TreeMap` que ordene productos por precio.
     }
 
     // * 📖 TEORÍA: ITERADORES EN JAVA
     // ──────────────────────────────────────────────────────────────
     // ? Un iterador permite recorrer y modificar una colección de forma segura.
     public static void manejarIteradores() {
         List<String> lista = new ArrayList<>(Arrays.asList("Java", "C++", "Python"));
 
         Iterator<String> iter = lista.iterator();
         while (iter.hasNext()) {
             String elemento = iter.next();
             System.out.println("Elemento: " + elemento);
             if (elemento.equals("C++")) {
                 iter.remove(); // Eliminamos C++
             }
         }
         System.out.println("Lista después de eliminar C++ con iterador: " + lista);
 
         // ! ✅ EJERCICIOS:
         // * 1️⃣ Escribe una función que use un iterador para eliminar todos los números pares de una lista de enteros.
         // * 2️⃣ Implementa una función que recorra un `Set` con un iterador y cuente cuántos elementos tiene.
     }
 
     // * 📖 TEORÍA: CLONACIÓN DE COLECCIONES Y OBJETOS
     // ──────────────────────────────────────────────────────────────
     // ? La clonación permite copiar una colección sin modificar la original.
     public static void clonarColecciones() {
         List<String> original = new ArrayList<>(Arrays.asList("Uno", "Dos", "Tres"));
         List<String> copia = new ArrayList<>(original);
 
         System.out.println("Lista original: " + original);
         System.out.println("Lista clonada: " + copia);
 
         // ! ✅ EJERCICIOS:
         // * 1️⃣ Escribe una función que clone una lista sin alterar la original.
         // * 2️⃣ Implementa una función que clone un `Map` y modifique la copia sin afectar el original.
     }
 }
 