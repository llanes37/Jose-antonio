/******************************************************************************************
 *                        📚 TEMA 02: FICHEROS Y SCANNER EN JAVA
 * ──────────────────────────────────────────────────────────────────────────────
 * INTRODUCCIÓN:
 * -------------
 * En esta práctica aprenderás a trabajar con ficheros de texto en Java usando:
 *   🔹 File → para representar un archivo o directorio
 *   🔹 FileWriter → para escribir texto en un archivo
 *   🔹 BufferedWriter → para mejorar el rendimiento de escritura
 *   🔹 FileReader y BufferedReader → para leer archivos línea a línea
 *   🔹 Scanner → para leer datos desde el teclado
 *
 * ¿Por qué es importante?
 * ------------------------
 * Los ficheros nos permiten almacenar datos de manera persistente sin necesidad
 * de usar bases de datos. Este tipo de almacenamiento es ideal para configuraciones,
 * logs, agendas, y archivos simples de datos.
 *
 * OBJETIVO:
 * ---------
 * - Crear un archivo de texto llamado `datos.txt`
 * - Escribir datos que introduzca el usuario (nombre y edad)
 * - Leer esos datos y mostrarlos por consola
 *
 * REQUISITOS:
 * -----------
 * ✅ Java JDK 17 o superior
 * ✅ IDE como Visual Studio Code, NetBeans o IntelliJ
 * ✅ Extensiones: Java Extension Pack + Better Comments
 *
 * COMPILACIÓN Y EJECUCIÓN:
 * -------------------------
 * javac FileScanner02.java
 * java FileScanner02
 *
 * AUTOR: Joaquín Rodríguez Llanes
 * FECHA: 07/04/2025
 ******************************************************************************************/

 import java.io.*;            // 📦 Librerías necesarias para leer/escribir archivos
 import java.util.Scanner;   // 📦 Librería para leer desde el teclado
 
 public class UT2_FileScanner02 {
 
     // 🟢 Se crea un objeto Scanner que usaremos en todo el programa
     static Scanner teclado = new Scanner(System.in);
 
     public static void main(String[] args) {
         int opcion; // 🟢 Variable que almacenará la opción del menú
 
         // 🔁 Bucle que mantiene el menú activo hasta que se elija salir
         do {
             System.out.println("\n===== MENÚ DE FICHEROS Y SCANNER =====");
             System.out.println("1. Escribir datos en un fichero");
             System.out.println("2. Leer datos desde el fichero");
             System.out.println("3. Salir");
             System.out.print("👉 Elige una opción: ");
             opcion = readInt(); // 🔸 Método para leer opción válida
 
             // 🔁 Ejecuta la opción seleccionada
             switch (opcion) {
                 case 1:
                     escribirEnFichero(); // ✏️ Llama al método que escribe datos
                     break;
                 case 2:
                     leerDesdeFichero(); // 📖 Llama al método que lee datos
                     break;
                 case 3:
                     System.out.println("👋 Saliendo...");
                     break;
                 default:
                     System.out.println("❌ Opción no válida.");
             }
         } while (opcion != 3); // 🔁 Repite hasta que se elija "Salir"
     }
 
     // ===================================================
     // 📌 MÉTODO: Leer un número entero con validación
     // ===================================================
     public static int readInt() {
         while (true) {
             try {
                 return Integer.parseInt(teclado.nextLine()); // 🔸 Convierte la entrada a entero
             } catch (Exception e) {
                 System.out.print("❌ Introduce un número válido: "); // ⚠️ Mensaje si no es un número
             }
         }
     }
 
     // ===================================================
     // 📌 MÉTODO: ESCRIBIR EN FICHERO
     // ===================================================
     /**
      * //! ESCRIBIR DATOS EN FICHERO
      * ? TEORÍA:
      * Usamos FileWriter en modo "append" para que los nuevos datos se añadan al final.
      * BufferedWriter mejora el rendimiento evitando muchas escrituras individuales.
      *
      * * TAREA PARA EL ALUMNO:
      * Escribe varios registros en el fichero "datos.txt"
      */
     public static void escribirEnFichero() {
         try {
             // 🛠️ Creamos FileWriter con true para no sobrescribir, sino añadir al final
             FileWriter fw = new FileWriter("datos.txt", true);
             BufferedWriter bw = new BufferedWriter(fw); // Mejora el rendimiento de escritura
 
             // 🟢 Le pedimos datos al usuario
             System.out.print("👉 Introduce tu nombre: ");
             String nombre = teclado.nextLine(); // 🔸 Lee una línea con el nombre
 
             System.out.print("👉 Introduce tu edad: ");
             int edad = readInt(); // 🔸 Usamos nuestro método para validar enteros
 
             // 📝 Escribimos los datos en el fichero separados por coma
             bw.write(nombre + "," + edad);
             bw.newLine(); // Salto de línea
             bw.close();   // 🛑 Cerramos el BufferedWriter
 
             System.out.println("✅ Datos guardados correctamente.");
         } catch (IOException e) {
             System.out.println("❌ Error al escribir en el fichero: " + e.getMessage());
         }
     }
 
     // ===================================================
     // 📌 MÉTODO: LEER DESDE FICHERO
     // ===================================================
     /**
      * //! LEER DATOS DESDE FICHERO
      * ? TEORÍA:
      * BufferedReader permite leer texto línea a línea.
      * Cada línea se separa usando split() para identificar nombre y edad.
      *
      * * TAREA PARA EL ALUMNO:
      * Filtrar solo personas mayores de edad o mostrar con otro formato.
      */
     public static void leerDesdeFichero() {
         try {
             File file = new File("datos.txt"); // 📄 Creamos objeto File del archivo
 
             // ⚠️ Verificamos si existe el archivo
             if (!file.exists()) {
                 System.out.println("⚠️ El fichero 'datos.txt' no existe.");
                 return;
             }
 
             // 📖 Creamos BufferedReader para leer el archivo línea a línea
             BufferedReader br = new BufferedReader(new FileReader(file));
             String linea;
 
             System.out.println("📄 Contenido del fichero:");
 
             // 🔁 Leemos cada línea hasta que no haya más
             while ((linea = br.readLine()) != null) {
                 String[] partes = linea.split(","); // ✂️ Separar por coma
                 if (partes.length == 2) {
                     String nombre = partes[0]; // 🔸 Primera parte: nombre
                     int edad = Integer.parseInt(partes[1]); // 🔸 Segunda parte: edad
                     // 📤 Mostramos el contenido
                     System.out.println("👤 Nombre: " + nombre + " | 🎂 Edad: " + edad);
                 }
             }
 
             br.close(); // 🛑 Cerramos el BufferedReader
         } catch (IOException e) {
             System.out.println("❌ Error al leer el fichero: " + e.getMessage());
         }
     }
 
     // ===================================================
     // 📌 EJERCICIO FINAL (para la clase)
     // ===================================================
     /*
      * 🧪 EJERCICIO PROPUESTO PARA EL ALUMNO:
      * --------------------------------------
      * 1. Modifica el programa para que cada registro también guarde el email del usuario.
      *    Ejemplo de línea: nombre,edad,email
      *
      * 2. Cambia el método de lectura para que también muestre ese campo por consola.
      *
      * 3. BONUS: Muestra solo los usuarios mayores de 30 años en un listado separado.
      */
 }
 