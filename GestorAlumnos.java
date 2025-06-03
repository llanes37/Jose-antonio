/******************************************************************************************
 * 🎓 PROYECTO FINAL JAVA · GESTIÓN DE ALUMNOS (1º DAW)
 * ----------------------------------------------------------------------------------------
 * ✅ OBJETIVO GENERAL:
 * Este proyecto está diseñado como una práctica global para repasar y consolidar
 * **todos los conocimientos de Java** vistos en 1º DAW. Los alumnos pondrán en práctica
 * la lógica, la organización del código, y las estructuras fundamentales de programación.
 *
 * 🌟 CONTENIDOS INTEGRADOS:
 * --------------------------------------------------------
 *   • 🔢 Variables y Tipos de Datos
 *   • 🎯 Condicionales (if / else / switch)
 *   • 🔁 Bucles (for / while / do while)
 *   • 🧮 Arrays y ArrayList
 *   • 🧩 Funciones y Métodos
 *   • 🏗️ Programación Orientada a Objetos (POO)
 *   • 🧬 Herencia y Clases Abstractas
 *   • ⚠️ Excepciones (try / catch / finally)
 *   • 📦 Colecciones (List, Set, Map)
 *   • 💾 Entrada y Salida de Ficheros
 *   • 🧭 Menús interactivos en consola
 *
 * 💬 COMENTARIOS EN FORMATO *Better Comments*:
 * --------------------------------------------------------
 *   ! Importante: conceptos clave o advertencias
 *   ? Dudas típicas o preguntas frecuentes
 *   TODO: Mejores prácticas o partes ampliables
 *   ✅ Confirmaciones y resultados esperados
 *   🔁 Estructuras repetitivas o cíclicas
 *   🔧 Ayudas o funciones auxiliares
 *   ➕ Nuevas funcionalidades o ampliaciones
 *   ⚠️ Gestión de errores y validaciones

 * 🧠 ESTRUCTURA DEL PROYECTO:
 * --------------------------------------------------------
 *   🔹 Clase abstracta `Alumno`:
 *       - Atributos comunes (nombre, edad)
 *       - Método abstracto `mostrarInformacion()`
 *
 *   🔹 Subclases:
 *       - `AlumnoPresencial` y `AlumnoOnline`
 *       - Cada una implementa `mostrarInformacion()` de forma distinta
 *
 *   🔹 Clase `GestorAlumnos`:
 *       - Contiene el `main()`
 *       - Presenta un menú de opciones (añadir, mostrar, buscar, guardar...)
 *       - Usa un `ArrayList<Alumno>` como base de datos temporal
 *
 * 🛠️ ¿CÓMO MODIFICAR EL MENÚ?
 * --------------------------------------------------------
 *   1️⃣ Añade un nuevo número al `switch` en `main()` (por ejemplo: case 11 -> opcionNueva();)
 *   2️⃣ Crea un nuevo método `public static void opcionNueva() { ... }`
 *   3️⃣ Añade esa opción también al `mostrarMenu()`
 *   4️⃣ Usa las prácticas anteriores como referencia (pedir datos, recorrer listas, etc.)

 * 🧪 PRÁCTICAS OPCIONALES (¡Muy recomendadas!):
 * --------------------------------------------------------
 *   ✅ Buscar por edad o tipo de alumno
 *   ✅ Ordenar por nombre descendente
 *   ✅ Mostrar estadísticas (media de edad, % online...)
 *   ✅ Usar `Map` para asociar alumnos con asignaturas
 *   ✅ Exportar e importar desde CSV o JSON
 *   ✅ Dividir el proyecto en paquetes (`model`, `controller`, `utils`)
 *
 * 👨‍💻 CONSEJO FINAL:
 * --------------------------------------------------------
 * Lee los comentarios en cada método. Están pensados para que aprendas a programar
 * como un profesional. **Prueba, modifica, rompe el código... y aprende arreglándolo.**
 *
 * 🌈 ¡Vamos a programar!
 ******************************************************************************************/


// 📦 Importamos librerías necesarias para colecciones y ficheros
import java.util.*;       // Scanner, ArrayList, Iterator
import java.io.*;         // Manejo de ficheros

// ============================================================
// 🔷 CLASE ABSTRACTA BASE: Alumno
// ============================================================
abstract class Alumno {
  // ! Atributos comunes para todos los tipos de alumno
  protected String nombre;
  protected int edad;

  // 🔨 Constructor que obliga a asignar nombre y edad
  public Alumno(String nombre, int edad) {
    this.nombre = nombre;
    this.edad = edad;
  }

  // ✅ Getters públicos para obtener datos del alumno
  public String getNombre() {
    return nombre;
  }

  public int getEdad() {
    return edad;
  }

  // 🔁 Método abstracto que deben implementar las clases hijas
  public abstract void mostrarInformacion();
}

// ============================================================
// 🟠 CLASE DERIVADA: AlumnoPresencial
// ============================================================
class AlumnoPresencial extends Alumno {
  public AlumnoPresencial(String nombre, int edad) {
    super(nombre, edad);  // 🧬 Heredamos constructor de la clase Alumno
  }

  @Override
  public void mostrarInformacion() {
    System.out.println("👤 [Presencial] " + nombre + " | Edad: " + edad);
  }
}

// ============================================================
// 🟠 CLASE DERIVADA: AlumnoOnline
// ============================================================
class AlumnoOnline extends Alumno {
  public AlumnoOnline(String nombre, int edad) {
    super(nombre, edad);
  }

  @Override
  public void mostrarInformacion() {
    System.out.println("👤 [Online] " + nombre + " | Edad: " + edad);
  }
}

// ============================================================
// 🌟 CLASE PRINCIPAL: GestorAlumnos (controla el menú y lógica)
// ============================================================
public class GestorAlumnos {
  static Scanner sc = new Scanner(System.in);                       // ! Objeto para leer entradas del usuario
  static ArrayList<Alumno> listaAlumnos = new ArrayList<>();       // ! Lista que guarda alumnos (polimórfica)



  public static void main(String[] args) {
    int opcion;
    do {
      mostrarMenu();                                               // 📋 Mostrar menú cada vez
      opcion = pedirEntero("Selecciona una opción:");
      try {
        // 🔁 Estructura de control switch para menú
        switch (opcion) {
          case 1 -> opcionAgregar();        // ➕ Añadir alumno
          case 2 -> opcionMostrar();        // 📋 Mostrar lista
          case 3 -> opcionBuscar();         // 🔍 Buscar por nombre
          case 4 -> opcionEliminar();       // ❌ Eliminar alumno
          case 5 -> opcionGuardar();        // 💾 Guardar en fichero
          case 6 -> opcionCargar();         // 📂 Cargar desde fichero
          case 7 -> opcionOrdenar();        // 🔢 Ordenar por nombre o edad
          case 8 -> opcionFiltrar();        // 🔎 Filtrar por criterios (edad, tipo...)
          case 9 -> opcionEstadisticas();   // 📊 Mostrar estadísticas (media de edad, cantidad, etc.)
          case 10 -> opcionModificar();     // ✏️ Modificar datos de un alumno
          case 0 -> System.out.println("👋 ¡Hasta pronto!");
          default -> System.out.println("❌ Opción no válida");
        }
      } catch (Exception e) {
        System.out.println("⚠️ Error inesperado: " + e.getMessage());  // ! Captura errores inesperados
      }
    } while (opcion != 0);

    sc.close();  // ✅ Cerramos Scanner al finalizar
  }


  // ========================================================
  // 📋 MENÚ PRINCIPAL
  // ========================================================
// ========================================================
// 📋 MENÚ PRINCIPAL - Opciones del programa
// ========================================================
public static void mostrarMenu() {
  System.out.println("\n===== GESTOR DE ALUMNOS 1º DAW =====");
  System.out.println("1. Agregar alumno");              // ➕ Insertar nuevo alumno
  System.out.println("2. Mostrar todos");              // 📋 Ver listado completo
  System.out.println("3. Buscar por nombre");          // 🔍 Buscar alumno por nombre
  System.out.println("4. Eliminar alumno");            // ❌ Eliminar alumno existente
  System.out.println("5. Guardar en fichero");         // 💾 Exportar a archivo .txt
  System.out.println("6. Cargar desde fichero");       // 📂 Importar desde archivo
  System.out.println("7. Ordenar alumnos");            // 🔢 Ordenar por nombre o edad
  System.out.println("8. Filtrar alumnos");            // 🔎 Mostrar solo los que cumplen una condición
  System.out.println("9. Estadísticas");               // 📊 Ver datos agregados (media, total...)
  System.out.println("10. Modificar datos alumno");    // ✏️ Editar información de un alumno
  System.out.println("0. Salir");                      // 🚪 Finalizar programa
  System.out.println("===================================");
}


// ========================================================
// ✅ OPCIÓN 1: AGREGAR ALUMNO
// ========================================================
/*
 📌 Esta función permite al usuario añadir un alumno a la lista. 
 Refuerza los siguientes conceptos:
  - Entrada por teclado con Scanner
  - Declaración y uso de variables
  - Estructuras condicionales (if-else)
  - Uso de clases hijas (AlumnoPresencial / AlumnoOnline)
  - Uso de colecciones dinámicas (ArrayList)
*/
public static void opcionAgregar() {
  System.out.print("✏️ Nombre: ");
  String nombre = sc.nextLine(); // ? Captura una cadena introducida por el usuario

  int edad = pedirEntero("🎂 Edad:"); // 🔧 Pedimos número con validación externa

  System.out.print("🎓 Tipo (P=Presencial / O=Online): ");
  String tipo = sc.nextLine(); // ? Tipo de alumno para decidir qué clase instanciar

  Alumno nuevo;

  // 🎯 Creamos un objeto Alumno específico según el tipo
  if (tipo.equalsIgnoreCase("P")) {
    nuevo = new AlumnoPresencial(nombre, edad);     // 👨‍🏫 Instanciamos clase hija
  } else {
    nuevo = new AlumnoOnline(nombre, edad);         // 💻 Otra clase hija (hereda de Alumno)
  }

  listaAlumnos.add(nuevo); // ➕ Añadimos el nuevo objeto a la lista de alumnos

  System.out.println("✅ Alumno agregado correctamente."); // ✔️ Confirmación por consola

  // TODO: Validar si ya existe un alumno con ese nombre (para evitar duplicados)

  /*
  🧪 PRACTICA EXTRA: Crear un alumno fuera de la función
  -----------------------------------------------------
  🔁 En lugar de pedir datos por consola, crea tú un alumno así:

  Alumno otro = new AlumnoPresencial("Lucía", 19);
  listaAlumnos.add(otro);

  ✅ Así refuerzas:
    - Cómo crear objetos desde cualquier parte
    - Cómo usar métodos y clases personalizados
  */
}


// ========================================================
// ✅ OPCIÓN 2: MOSTRAR TODOS LOS ALUMNOS
// ========================================================
/*
 * 🧠 En esta opción se recorre la lista de alumnos usando un bucle `for-each`.
 * Practicamos:
 * - Uso de `ArrayList.isEmpty()` para comprobar si hay datos.
 * - Recorrido de colecciones con `for-each`.
 * - Polimorfismo: cada objeto Alumno puede tener su propio `mostrarInformacion()`.
 * - Estructura limpia y visual para mostrar contenido.
 */

public static void opcionMostrar() {
  if (listaAlumnos.isEmpty()) {
    System.out.println("📭 No hay alumnos registrados.");             // ❗ Mensaje si la lista está vacía
  } else {
    System.out.println("📋 Lista de alumnos:");
    for (Alumno a : listaAlumnos) {                                   // 🔁 Bucle for-each para recorrer lista
      a.mostrarInformacion();                                         // 🧾 Polimorfismo: llama al método de cada subclase
      System.out.println("-----------------------------");            // ✨ Separador visual
    }
  }
}

/*
  🧪 PRÁCTICA EXTRA: Mostrar solo alumnos con edad mayor o igual a 18
  -------------------------------------------------------------------
  ✍️ Si quieres filtrar la lista por algún criterio (por ejemplo, solo mayores de edad), puedes probar esto:

  for (Alumno a : listaAlumnos) {
    if (a.getEdad() >= 18) {
      a.mostrarInformacion(); // 🧾 Solo se imprimen alumnos mayores de edad
    }
  }

  ✅ Refuerza:
  - Condicionales dentro de bucles
  - Lectura selectiva de colecciones
  - Comprensión de atributos de objetos
*/


// ========================================================
// ✅ OPCIÓN 3: BUSCAR ALUMNO POR NOMBRE
// ========================================================
/*
 * 🧠 Esta función permite al usuario buscar un alumno por su nombre.
 * Refuerza:
 * - Lectura de entrada desde consola
 * - Comparación de cadenas con `.equalsIgnoreCase()` o usando `.toLowerCase()`
 * - Búsqueda en colecciones con bucle `for-each`
 * - Uso de booleanos para determinar si se encontró el resultado
 * - Salida anticipada con `break` para optimizar rendimiento
 */

public static void opcionBuscar() {
  System.out.print("🔍 Nombre a buscar: ");
  String buscado = sc.nextLine().toLowerCase();  // 🔠 Convertimos a minúsculas para comparar sin importar mayúsculas

  boolean encontrado = false;                    // 🎯 Indicador para saber si lo hemos encontrado
  for (Alumno a : listaAlumnos) {
    if (a.getNombre().toLowerCase().equals(buscado)) {
      System.out.println("✅ Alumno encontrado:");
      a.mostrarInformacion();                    // 🧾 Mostramos los datos si coincide el nombre
      encontrado = true;
      break;                                     // ⛔ Paramos la búsqueda cuando lo encontramos
    }
  }

  if (!encontrado) {
    System.out.println("❌ Alumno no encontrado."); // 📭 Aviso si no hay coincidencias
  }
}

/*
  🧪 PRÁCTICA EXTRA: Buscar por parte del nombre (búsqueda parcial)
  ------------------------------------------------------------------
  🔎 En lugar de buscar por nombre exacto, puedes buscar si el nombre contiene una parte del texto:

  System.out.print("🔍 Parte del nombre a buscar: ");
  String parcial = sc.nextLine().toLowerCase();

  for (Alumno a : listaAlumnos) {
    if (a.getNombre().toLowerCase().contains(parcial)) {
      a.mostrarInformacion(); // ✅ Muestra todos los que contengan el texto parcial
    }
  }

  ✅ Con esto practicamos:
    - Uso de `.contains()` para búsquedas más flexibles
    - Recorridos completos sin `break`
    - Comparaciones más realistas como las que se hacen en formularios o buscadores
*/


 // ========================================================
// ✅ OPCIÓN 4: ELIMINAR ALUMNO POR NOMBRE
// ========================================================
/*
 * 🧠 Esta opción permite eliminar un alumno de la lista buscando por su nombre exacto.
 * Se refuerzan los siguientes conceptos:
 * - Uso del método `equals()` o `toLowerCase()` para comparar cadenas
 * - Iteración con `Iterator` (necesario para eliminar elementos en ArrayList mientras se recorre)
 * - Uso de booleanos para confirmar si se ha encontrado el alumno
 * - Control de flujo con `break` para optimizar el bucle
 */

public static void opcionEliminar() {
  System.out.print("🗑 Nombre a eliminar: ");
  String elim = sc.nextLine().toLowerCase();                       // 🔠 Entrada insensible a mayúsculas

  Iterator<Alumno> it = listaAlumnos.iterator();                  // 🔄 Usamos Iterator para eliminar sin error
  boolean eliminado = false;

  while (it.hasNext()) {
    Alumno a = it.next();                                         // 👁️ Recorremos cada alumno
    if (a.getNombre().toLowerCase().equals(elim)) {               // ✅ Comprobamos si coincide el nombre
      it.remove();                                                // 🗑️ Eliminamos el alumno de forma segura
      eliminado = true;
      break;                                                      // 🚪 Salimos del bucle tras eliminar
    }
  }

  // 🔔 Mostramos el resultado
  if (eliminado) {
    System.out.println("✅ Alumno eliminado correctamente.");
  } else {
    System.out.println("⚠️ No se encontró ningún alumno con ese nombre.");
  }
}

/*
  🧪 PRÁCTICA EXTRA: Eliminar múltiples alumnos con el mismo nombre
  ------------------------------------------------------------------
  🔁 Puedes eliminar **todos** los alumnos que tengan el mismo nombre, eliminando el `break`:

  while (it.hasNext()) {
    Alumno a = it.next();
    if (a.getNombre().toLowerCase().equals(elim)) {
      it.remove(); // ❌ Elimina todos los que coincidan
      eliminado = true;
    }
  }

  ✅ Esto refuerza:
    - Eliminación múltiple en colecciones
    - Uso de estructuras de control sin interrupción
*/


// ========================================================
// ✅ OPCIÓN 5: GUARDAR EN FICHERO
// ========================================================
/*
 * 🧠 Esta función guarda todos los alumnos actuales en un fichero de texto plano.
 * Se refuerzan conceptos clave de:
 * - Escritura en ficheros con BufferedWriter
 * - Recorrido de listas con bucles
 * - Serialización simple de objetos como texto (se podría extender a JSON o CSV)
 * 
 * 📂 El archivo generado se llamará "alumnos.txt" y contendrá una línea por alumno.
 * Ejemplo:
 *   Juan,19
 *   Lucía,20
 */

public static void opcionGuardar() throws IOException {
  // 📁 Abrimos (o creamos) un fichero llamado "alumnos.txt" para escritura
  BufferedWriter bw = new BufferedWriter(new FileWriter("alumnos.txt"));

  for (Alumno a : listaAlumnos) {
    // 📝 Guardamos cada alumno en formato "nombre,edad"
    bw.write(a.getNombre() + "," + a.getEdad());
    bw.newLine();  // ↩️ Salto de línea para cada alumno
  }

  bw.close();  // ✅ Cerramos el archivo para liberar recursos

  System.out.println("💾 Alumnos guardados correctamente en 'alumnos.txt'.");
}

/*
  🧪 PRÁCTICA EXTRA: Guardar también el tipo de alumno
  -----------------------------------------------------
  ✨ Si quisieras guardar también si el alumno es presencial u online,
  podrías añadir `a instanceof AlumnoOnline` y guardar un campo adicional:

  for (Alumno a : listaAlumnos) {
    String tipo = (a instanceof AlumnoOnline) ? "O" : "P";
    bw.write(a.getNombre() + "," + a.getEdad() + "," + tipo);
    bw.newLine();
  }

  ✅ Así refuerzas:
    - Uso de `instanceof` para diferenciar tipos en herencia
    - Escritura de más información en ficheros
*/


 // ========================================================
// ✅ OPCIÓN 6: CARGAR ALUMNOS DESDE FICHERO
// ========================================================
/*
 * 🧠 Esta opción lee un fichero de texto ("alumnos.txt") que contiene alumnos guardados previamente,
 * y los carga en la lista `listaAlumnos` como objetos de tipo `AlumnoPresencial`.
 * 
 * Se trabajan los siguientes conceptos:
 * - Lectura desde ficheros con BufferedReader
 * - Procesamiento de cadenas con `split()`
 * - Creación dinámica de objetos a partir de datos leídos
 * - Limpieza previa de estructuras para evitar duplicados
 */

public static void opcionCargar() throws IOException {
  // 📂 Abrimos el archivo para lectura
  BufferedReader br = new BufferedReader(new FileReader("alumnos.txt"));

  listaAlumnos.clear();  // 🧹 Limpiamos la lista antes de cargar (evita duplicados)

  String linea;
  while ((linea = br.readLine()) != null) {
    // ✂️ Dividimos cada línea por coma: "nombre,edad"
    String[] datos = linea.split(",");

    // 👤 Creamos un nuevo objeto alumno con los datos
    String nombre = datos[0];
    int edad = Integer.parseInt(datos[1]);
    Alumno a = new AlumnoPresencial(nombre, edad);

    listaAlumnos.add(a);  // ➕ Añadimos a la lista principal
  }

  br.close();  // ✅ Cerramos el archivo tras su lectura

  System.out.println("📂 Datos cargados correctamente desde 'alumnos.txt'.");
}

/*
  🧪 PRÁCTICA EXTRA: Cargar también el tipo de alumno
  -----------------------------------------------------
  Si en el archivo también se guardara el tipo de alumno (ej. "Juan,19,P"),
  podrías hacer algo como esto:

  String[] datos = linea.split(",");
  String nombre = datos[0];
  int edad = Integer.parseInt(datos[1]);
  String tipo = datos[2];

  Alumno a = tipo.equals("O") ? new AlumnoOnline(nombre, edad) 
                               : new AlumnoPresencial(nombre, edad);
  listaAlumnos.add(a);

  ✅ Así refuerzas:
    - Lógica condicional con ternarios
    - Reconstrucción de objetos de distintos tipos desde datos persistidos
*/
// ========================================================
// ✅ OPCIÓN 7: ORDENAR ALUMNOS
// ========================================================
/*
 * 🧠 En esta opción el alumno verá cómo ordenar una lista usando `Collections.sort()` y `Comparator`.
 *
 * Se refuerzan los conceptos de:
 * - Uso de lambdas o clases anónimas para ordenar listas
 * - Cómo modificar el orden de los objetos en una colección
 * - Recorrido posterior para ver el resultado de la ordenación
 * 
 * En este ejemplo, ofrecemos dos ordenaciones:
 *   1️⃣ Por nombre alfabéticamente (A-Z)
 *   2️⃣ Por edad de menor a mayor
 */

public static void opcionOrdenar() {
  if (listaAlumnos.isEmpty()) {
    System.out.println("⚠️ No hay alumnos que ordenar.");
    return;
  }

  System.out.println("🧩 ¿Cómo quieres ordenar la lista?");
  System.out.println("1. Por nombre (A-Z)");
  System.out.println("2. Por edad (menor a mayor)");
  int modo = pedirEntero("Elige opción:");

  switch (modo) {
    case 1 -> {
      // 🔤 Ordenar por nombre (alfabéticamente)
      listaAlumnos.sort(Comparator.comparing(Alumno::getNombre));
      System.out.println("✅ Lista ordenada por nombre:");
    }
    case 2 -> {
      // 🔢 Ordenar por edad
      listaAlumnos.sort(Comparator.comparingInt(Alumno::getEdad));
      System.out.println("✅ Lista ordenada por edad:");
    }
    default -> {
      System.out.println("❌ Opción no válida.");
      return;
    }
  }

  // 🎯 Mostrar el resultado ordenado
  for (Alumno a : listaAlumnos) {
    a.mostrarInformacion(); // 🧾 Polimorfismo: muestra info del alumno
  }
}

/*
 * 🧪 PRÁCTICA EXTRA: Ordenar en orden inverso
 * --------------------------------------------
 * Crea una opción 3 para ordenar por nombre de Z a A usando `.reversed()`:
 * 
 * listaAlumnos.sort(Comparator.comparing(Alumno::getNombre).reversed());
 * 
 * ✅ Así practicas:
 * - Ordenaciones inversas
 * - Encadenamiento de métodos con lambdas
 * - Pensamiento lógico para adaptar el código
 */

// ========================================================
// ✅ OPCIÓN 8: FILTRAR ALUMNOS
// ========================================================
/*
 * 🎯 Esta función permite mostrar una parte concreta de la lista de alumnos
 * en base a un criterio (edad mínima o tipo de alumno).
 * 
 * Refuerza:
 * - Condicionales (`if`)
 * - Uso de `instanceof` para distinguir subclases
 * - Recorrido con `for-each`
 * - Entrada por teclado con validación
 */

public static void opcionFiltrar() {
  // 🧠 Preguntamos al usuario qué tipo de filtro desea aplicar
  System.out.println("🔎 ¿Qué filtro quieres aplicar?");
  System.out.println("1. Mostrar solo alumnos mayores de edad (18+)");
  System.out.println("2. Mostrar solo alumnos presenciales");
  System.out.println("3. Mostrar solo alumnos online");

  int opcion = pedirEntero("Selecciona una opción:");

  // ❗ Validamos si la lista está vacía antes de aplicar filtros
  if (listaAlumnos.isEmpty()) {
    System.out.println("⚠️ No hay alumnos registrados.");
    return;
  }

  System.out.println("📋 Resultados del filtro:");
  
  // 🔄 Recorremos la lista de alumnos uno a uno
  for (Alumno a : listaAlumnos) {
    switch (opcion) {
      case 1 -> {
        // ✅ Mostramos solo si la edad es igual o mayor a 18
        if (a.getEdad() >= 18) {
          a.mostrarInformacion();
        }
      }
      case 2 -> {
        // ✅ Mostramos solo si el alumno es de tipo presencial
        if (a instanceof AlumnoPresencial) {
          a.mostrarInformacion();
        }
      }
      case 3 -> {
        // ✅ Mostramos solo si el alumno es de tipo online
        if (a instanceof AlumnoOnline) {
          a.mostrarInformacion();
        }
      }
      default -> {
        System.out.println("❌ Opción no válida. Inténtalo de nuevo.");
        return;
      }
    }
  }

  /*
   * 🧪 PRÁCTICA EXTRA: Mostrar alumnos menores de edad
   * ---------------------------------------------------
   * Agrega una opción 4 al menú anterior y muestra aquellos alumnos cuya edad < 18
   * 
   * if (a.getEdad() < 18) {
   *   a.mostrarInformacion();
   * }
   * 
   * ✅ Así refuerzas:
   * - Condiciones adicionales
   * - Práctica de comparación de enteros
   */
}


// ========================================================
// ✅ OPCIÓN 9: ESTADÍSTICAS
// ========================================================
/*
 * 📊 Esta función muestra estadísticas básicas del grupo de alumnos:
 * - Número total de alumnos
 * - Edad media del grupo
 * - Porcentaje de alumnos online
 *
 * Refuerza:
 * - Recorrido con bucles `for-each`
 * - Acumuladores (`suma`, `contador`)
 * - Cálculo de porcentajes
 * - Uso de `instanceof` para diferenciar tipos
 */

public static void opcionEstadisticas() {
  // ❗ Comprobamos si la lista está vacía antes de calcular
  if (listaAlumnos.isEmpty()) {
    System.out.println("⚠️ No hay alumnos registrados.");
    return;
  }

  // 📦 Inicializamos variables para cálculo
  int total = listaAlumnos.size();     // 🔢 Total de alumnos
  int sumaEdades = 0;                  // ➕ Suma acumulada de edades
  int totalOnline = 0;                 // 💻 Contador de alumnos online

  // 🔁 Recorremos la lista para obtener datos
  for (Alumno a : listaAlumnos) {
    sumaEdades += a.getEdad();         // ➕ Acumulamos la edad de cada alumno
    if (a instanceof AlumnoOnline) {   // 💡 Contamos cuántos son online
      totalOnline++;
    }
  }

  // 📐 Calculamos estadísticas
  double media = (double) sumaEdades / total;                     // 🎂 Edad media
  double porcentajeOnline = (totalOnline * 100.0) / total;        // 📊 Porcentaje

  // 📢 Mostramos resultados
  System.out.println("\n📊 ESTADÍSTICAS DEL GRUPO:");
  System.out.println("👥 Total de alumnos: " + total);
  System.out.printf("🎂 Edad media: %.2f años\n", media);
  System.out.printf("💻 Porcentaje de alumnos online: %.2f%%\n", porcentajeOnline);

  /*
   * 🧪 PRÁCTICA EXTRA: Mostrar también la edad máxima y mínima
   * -----------------------------------------------------------
   * Puedes extender esta función para encontrar la mayor y menor edad.
   * 
   * int maxEdad = Integer.MIN_VALUE;
   * int minEdad = Integer.MAX_VALUE;
   * for (Alumno a : listaAlumnos) {
   *   if (a.getEdad() > maxEdad) maxEdad = a.getEdad();
   *   if (a.getEdad() < minEdad) minEdad = a.getEdad();
   * }
   * System.out.println("🔼 Edad máxima: " + maxEdad);
   * System.out.println("🔽 Edad mínima: " + minEdad);
   *
   * ✅ Refuerza:
   * - Comparaciones condicionales
   * - Uso de constantes Integer.MIN/MAX_VALUE
   * - Estadísticas adicionales realistas
   */
}


// ========================================================
// ✅ OPCIÓN 10: MODIFICAR ALUMNO
// ========================================================
/*
 * ✏️ Esta opción permite al usuario modificar los datos de un alumno.
 * Primero se busca el alumno por su nombre, y si se encuentra, se pueden
 * editar sus atributos: nombre, edad y tipo (presencial u online).
 *
 * Se practican:
 * - Búsqueda en ArrayList
 * - Entrada de datos desde consola
 * - Reemplazo de objetos en una posición concreta
 * - Validación básica de entrada
 */

public static void opcionModificar() {
  System.out.print("🔍 Nombre del alumno a modificar: ");
  String buscado = sc.nextLine().toLowerCase();               // 🔠 Buscamos por nombre (insensible a mayúsculas)

  // 🔁 Recorremos la lista con índice para poder modificar en la misma posición
  for (int i = 0; i < listaAlumnos.size(); i++) {
    Alumno a = listaAlumnos.get(i);                           // 📌 Obtenemos alumno actual

    if (a.getNombre().toLowerCase().equals(buscado)) {
      System.out.println("✅ Alumno encontrado:");
      a.mostrarInformacion();                                 // 👁️ Mostramos los datos actuales

      // ✍️ Pedimos nuevos datos al usuario
      System.out.print("🆕 Nuevo nombre: ");
      String nuevoNombre = sc.nextLine();

      int nuevaEdad = pedirEntero("🆕 Nueva edad:");
      System.out.print("🆕 Nuevo tipo (P=Presencial / O=Online): ");
      String nuevoTipo = sc.nextLine();

      // 🏗️ Creamos un nuevo objeto del tipo correspondiente
      Alumno actualizado;
      if (nuevoTipo.equalsIgnoreCase("O")) {
        actualizado = new AlumnoOnline(nuevoNombre, nuevaEdad);    // 💻 Online
      } else {
        actualizado = new AlumnoPresencial(nuevoNombre, nuevaEdad); // 🏫 Presencial
      }

      listaAlumnos.set(i, actualizado);                     // 🔁 Reemplazamos el objeto antiguo por el nuevo
      System.out.println("✅ Alumno modificado correctamente.");
      return;
    }
  }

  // ❌ Si no se encontró el alumno
  System.out.println("⚠️ No se encontró ningún alumno con ese nombre.");
}

/*
 * 🧪 PRÁCTICA EXTRA: Validar que el nuevo nombre no exista ya
 * -----------------------------------------------------------
 * Puedes evitar duplicados al modificar usando este patrón:
 * 
 * for (Alumno otro : listaAlumnos) {
 *   if (otro.getNombre().equalsIgnoreCase(nuevoNombre)) {
 *     System.out.println("❌ Ya existe un alumno con ese nombre.");
 *     return;
 *   }
 * }
 * 
 * ✅ Así refuerzas:
 * - Validación previa
 * - Comparación de objetos dentro de estructuras
 */





  // ========================================================
  // 🔧 MÉTODO AUXILIAR PARA VALIDAR NÚMEROS
  // ========================================================
  public static int pedirEntero(String mensaje) {
    while (true) {
      try {
        System.out.print(mensaje + " ");
        return Integer.parseInt(sc.nextLine());                     // 🔢 Convertir String a entero
      } catch (NumberFormatException e) {
        System.out.println("⚠️ Debes introducir un número válido.");
      }
    }
  }
}


