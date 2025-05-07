// ==============================================================
// 📚 PROYECTO COMPLETO DE ARCHIVOS EN JAVA CON MENÚ Y PRUEBA FINAL
// ==============================================================
// 🔧 AUTOR: [Tu nombre o el del alumno]
// 📅 FECHA: [Fecha actual]
// 🧠 DESCRIPCIÓN GENERAL:
// Este proyecto enseña paso a paso el manejo de archivos en Java.
// Desde ejercicios básicos de texto (.txt) hasta lectura/escritura
// de binarios (.dat), incluye un menú interactivo y un reto final.
// 💡 Ideal para estudiar o presentar en examen.

// ==============================================================
// 🎨 COMENTARIOS ESTILO BETTER COMMENTS:
// ✅ ÉXITO     ❌ ERROR     👉 PISTA / TAREA     📌 IMPORTANTE
// ==============================================================

import java.io.*;
import java.util.Scanner;

public class EjercicioFacilArchivos1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            // 📋 MENÚ PRINCIPAL
            System.out.println("\n========= MENÚ DE ARCHIVOS JAVA =========");
            System.out.println("1. Ejercicio 1 - Escribir texto básico");
            System.out.println("2. Ejercicio 2 - Leer archivo .txt línea a línea");
            System.out.println("3. Ejercicio 3 - Pedir datos al usuario");
            System.out.println("4. Ejercicio 4 - Buscar palabras y contar líneas");
            System.out.println("5. Ejercicio 5 - Guardar números en binario (.dat)");
            System.out.println("6. Ejercicio 6 - Leer binario y mostrar el mayor");
            System.out.println("7. Ejercicio 7 - Contar números pares en binario");
            System.out.println("8. Ejercicio 8 - Filtrar líneas por palabra clave");
            System.out.println("9. 🧪 PRUEBA FINAL - Leer alumnos y analizar notas");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1: ejercicio1();          break;
                case 2: ejercicio2();          break;
                case 3: ejercicio3();          break;
                case 4: ejercicio4();          break;
                case 5: ejercicio5();          break;
                case 6: ejercicio6();          break;
                case 7: ejercicio7();          break;
                case 8: ejercicio8();          break;
                case 9: ejercicioFinal();      break;
                case 0: System.out.println("👋 Gracias por usar el proyecto. ¡Hasta luego!"); break;
                default: System.out.println("❌ Opción no válida. Elige del 0 al 9.");    break;
            }
        } while (opcion != 0);
    }

    // ---------------------------------------------------------------------------
    // ✅ EJERCICIO 1: ESCRIBIR TEXTO BÁSICO EN UN ARCHIVO .TXT
    // ---------------------------------------------------------------------------
    public static void ejercicio1() {
        try {
            FileWriter fw = new FileWriter("archivo1.txt");      // 📂 Abrimos / creamos 'archivo1.txt'
            BufferedWriter bw = new BufferedWriter(fw);          // 🔧 Envolvemos para eficiencia

            bw.write("Hola, esto es un archivo de texto.");      // 📄 Escribimos primera línea
            bw.newLine();                                        // ⏎ Insertamos salto de línea
            bw.write("Escribiendo línea por línea con Java.");   // 📄 Escribimos segunda línea

            bw.close();                                          // ✅ Cerramos archivo
            System.out.println("✅ Ejercicio 1: archivo1.txt creado y escrito.");
            // 👉 EJERCICIO PROPUESTO: Añade más líneas que describan tu comida favorita
        } catch (IOException e) {
            System.out.println("❌ Error en Ejercicio 1: " + e.getMessage());
        }
    }

    // ---------------------------------------------------------------------------
    // ✅ EJERCICIO 2: LEER ARCHIVO .TXT LÍNEA A LÍNEA Y CONTAR
    // ---------------------------------------------------------------------------
    public static void ejercicio2() {
        try {
            FileReader fr = new FileReader("archivo1.txt");      // 📂 Abrimos 'archivo1.txt' en modo lectura
            BufferedReader br = new BufferedReader(fr);          // 🔧 Envolvemos para leer líneas completas

            String linea;                                        // 📜 Variable para almacenar cada línea
            int totalLineas = 0;                                 // 🔢 Contador de líneas

            System.out.println("📖 Ejercicio 2: leyendo archivo1.txt:");
            while ((linea = br.readLine()) != null) {            // 🔄 Leer hasta que no haya más líneas
                totalLineas++;                                   // ➕ Incrementamos contador
                System.out.println("-> " + linea);               // 📋 Mostramos la línea
            }

            br.close();                                          // ✅ Cerramos archivo
            System.out.println("📌 Total de líneas leídas: " + totalLineas);
            // 👉 EJERCICIO PROPUESTO: Cuenta manualmente cuántas líneas se imprimen
        } catch (IOException e) {
            System.out.println("❌ Error en Ejercicio 2 al leer archivo: " + e.getMessage());
        }
    }

    // ---------------------------------------------------------------------------
    // ✅ EJERCICIO 3: PEDIR DATOS AL USUARIO Y GUARDARLOS EN UN ARCHIVO
    // ---------------------------------------------------------------------------
    public static void ejercicio3() {
        try {
            Scanner sc = new Scanner(System.in);                 // 🔧 Scanner para entrada por teclado
            System.out.print("Introduce tu nombre: ");
            String nombre = sc.nextLine();                       // 📋 Leemos nombre completo
            System.out.print("Introduce tu edad: ");
            int edad = sc.nextInt();                             // 🔢 Leemos edad como entero

            PrintWriter pw = new PrintWriter(new FileWriter("datos_usuario.txt"));
            pw.println("Nombre: " + nombre);                     // 📄 Escribimos nombre
            pw.println("Edad: " + edad);                         // 📄 Escribimos edad
            pw.close();                                          // ✅ Cerramos archivo

            System.out.println("✅ Ejercicio 3: datos guardados en datos_usuario.txt");
            // 👉 EJERCICIO PROPUESTO: Añade una línea preguntando si eres repetidor (true/false)
        } catch (IOException e) {
            System.out.println("❌ Error en Ejercicio 3: " + e.getMessage());
        }
    }

    // ---------------------------------------------------------------------------
    // ✅ EJERCICIO 4: BUSCAR PALABRAS Y CONTAR LÍNEAS EN UN .TXT
    // ---------------------------------------------------------------------------
    public static void ejercicio4() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("archivo1.txt")); // 📂 Abrimos el archivo
            String linea;                                   // 📜 Para cada línea
            int contador = 0;                               // 🔢 Contador total líneas

            System.out.println("📊 Ejercicio 4: analizando archivo1.txt:");
            while ((linea = br.readLine()) != null) {       // 🔄 Recorremos líneas
                contador++;                                 // ➕ Incrementar
                if (linea.contains("texto")) {              // 🔍 Si contiene la palabra "texto"
                    System.out.println("🔍 Línea " + contador +
                                       " contiene 'texto': " + linea);
                }
            }

            System.out.println("📌 Total líneas: " + contador); // 📌 Mostrar total
            br.close();                                       // ✅ Cerrar archivo
            // 👉 EJERCICIO PROPUESTO: Cambia "texto" por otra palabra y repite
        } catch (IOException e) {
            System.out.println("❌ Error en Ejercicio 4: " + e.getMessage());
        }
    }

    // ---------------------------------------------------------------------------
    // ✅ EJERCICIO 5: GUARDAR NÚMEROS EN UN ARCHIVO BINARIO (.dat)
    // ---------------------------------------------------------------------------
    public static void ejercicio5() {
        try {
            DataOutputStream dos = new DataOutputStream(new FileOutputStream("numeros.dat"));
            dos.writeInt(10);                                // 💾 Escribimos entero 10
            dos.writeInt(25);                                // 💾 Escribimos entero 25
            dos.writeInt(7);
            dos.writeInt(42);
            dos.close();                                     // ✅ Cerramos flujo
            System.out.println("✅ Ejercicio 5: 'numeros.dat' creado.");
            // 👉 EJERCICIO PROPUESTO: Añade tu edad como otro número binario
        } catch (IOException e) {
            System.out.println("❌ Error en Ejercicio 5: " + e.getMessage());
        }
    }

    // ---------------------------------------------------------------------------
    // ✅ EJERCICIO 6: LEER BINARIO Y MOSTRAR EL NÚMERO MAYOR
    // ---------------------------------------------------------------------------
    public static void ejercicio6() {
        try {
            DataInputStream dis = new DataInputStream(new FileInputStream("numeros.dat"));
            int mayor = Integer.MIN_VALUE;                   // ⬇️ Inicializamos menor valor posible
            System.out.println("📥 Ejercicio 6: leyendo 'numeros.dat':");
            while (dis.available() > 0) {                    // 🔄 Mientras queden datos
                int num = dis.readInt();                     // 📥 Leemos un entero
                System.out.println("Número leído: " + num);
                if (num > mayor) mayor = num;                // 🔼 Actualizamos máximo
            }
            System.out.println("🏆 Número mayor: " + mayor);  // 🏆 Mostramos resultado
            dis.close();                                     // ✅ Cerramos flujo
        } catch (IOException e) {
            System.out.println("❌ Error en Ejercicio 6: " + e.getMessage());
        }
    }

    // ---------------------------------------------------------------------------
    // ✅ EJERCICIO 7: CONTAR NÚMEROS PARES EN EL ARCHIVO BINARIO
    // ---------------------------------------------------------------------------
    public static void ejercicio7() {
        try {
            DataInputStream dis = new DataInputStream(new FileInputStream("numeros.dat"));
            int pares = 0;                                   // 🔢 Contador de pares
            while (dis.available() > 0) {
                int n = dis.readInt();                       // 📥 Leemos siguiente entero
                if (n % 2 == 0) pares++;                    // 🔍 Si es par, incrementamos
            }
            dis.close();                                     // ✅ Cerramos flujo
            System.out.println("🔢 Ejercicio 7: hay " + pares +
                               " números pares en el archivo binario.");
            // 👉 EJERCICIO PROPUESTO: Cuenta también los impares
        } catch (IOException e) {
            System.out.println("❌ Error en Ejercicio 7: " + e.getMessage());
        }
    }

    // ---------------------------------------------------------------------------
    // ✅ EJERCICIO 8: FILTRAR LÍNEAS DE TEXTO POR PALABRA CLAVE
    // ---------------------------------------------------------------------------
    public static void ejercicio8() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("archivo1.txt"));
            PrintWriter pw = new PrintWriter("archivo_filtrado.txt");
            String linea;                                    // 📜 Línea actual
            while ((linea = br.readLine()) != null) {        // 🔄 Recorremos todas
                if (!linea.contains("Java")) {               // ❌ Si NO contiene "Java"
                    pw.println(linea);                       // 💾 Lo escribimos en el nuevo archivo
                }
            }
            br.close();                                      // ✅ Cerramos lectura
            pw.close();                                      // ✅ Cerramos escritura
            System.out.println("🧹 Ejercicio 8: 'archivo_filtrado.txt' creado sin líneas con 'Java'.");
            // 👉 EJERCICIO PROPUESTO: Cambia la palabra 'Java' por tu propia palabra clave
        } catch (IOException e) {
            System.out.println("❌ Error en Ejercicio 8: " + e.getMessage());
        }
    }

    // ---------------------------------------------------------------------------
    // 🧪 PRUEBA FINAL: LEER ALUMNOS Y ANALIZAR MEJOR Y PEOR NOTA
    // ---------------------------------------------------------------------------
    public static void ejercicioFinal() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("alumnos.txt"));
            String linea;                                    // 📜 Línea con datos de un alumno

            // Inicializamos seguimiento de mejor y peor nota
            String mejorAlumno = "";
            double mejorNota = Double.MIN_VALUE;
            boolean mejorRepetidor = false;

            String peorAlumno = "";
            double peorNota = Double.MAX_VALUE;
            boolean peorRepetidor = false;

            System.out.println("📘 Leyendo archivo 'alumnos.txt':");
            // 🔄 Leemos hasta el final
            while ((linea = br.readLine()) != null) {
                System.out.println("- " + linea);             // 📄 Mostramos línea cruda
                String[] partes = linea.split(";");          // ✂️ Separamos por ‘;’
                String nombre = partes[0];                   // 📋 Nombre completo
                double nota = Double.parseDouble(partes[1]); // 🔢 Nota numérica
                boolean repetidor = Boolean.parseBoolean(partes[2]); // 🔀 Estado repetidor

                // 📈 Actualizamos mejor alumno
                if (nota > mejorNota) {
                    mejorNota = nota;
                    mejorAlumno = nombre;
                    mejorRepetidor = repetidor;
                }
                // 📉 Actualizamos peor alumno
                if (nota < peorNota) {
                    peorNota = nota;
                    peorAlumno = nombre;
                    peorRepetidor = repetidor;
                }
            }
            br.close();                                      // ✅ Cerramos flujo

            // 🏅 Resultados finales
            System.out.println("\n🏅 Alumno con MEJOR nota:");
            System.out.println("👨‍🎓 Nombre: " + mejorAlumno);
            System.out.println("📈 Nota: " + mejorNota);
            System.out.println("🔁 Repetidor: " + mejorRepetidor);

            System.out.println("\n❗ Alumno con PEOR nota:");
            System.out.println("👨‍🎓 Nombre: " + peorAlumno);
            System.out.println("📉 Nota: " + peorNota);
            System.out.println("🔁 Repetidor: " + peorRepetidor);

        } catch (IOException e) {
            System.out.println("❌ Error al leer el archivo de alumnos: " + e.getMessage());
        }
    }
}
