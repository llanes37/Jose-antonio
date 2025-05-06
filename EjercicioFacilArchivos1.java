// PROYECTO COMPLETO DE ARCHIVOS EN JAVA (Nivel básico a intermedio)
// -------------------------------------------------------------------
// AUTOR: [Tu nombre o el del alumno]
// FECHA: [Fecha actual]
// DESCRIPCIÓN GENERAL:
// Este proyecto está diseñado para enseñar de forma progresiva cómo trabajar con archivos en Java.
// Se comienza con la escritura y lectura de archivos de texto, se incluyen prácticas guiadas con comentarios
// y al final se trabaja con archivos binarios. Cada bloque incluye explicaciones detalladas para aprender paso a paso.

import java.io.*; // Importamos todas las clases necesarias para trabajar con archivos
import java.util.Scanner; // Importamos Scanner para entrada de datos por teclado

public class EjercicioFacilArchivos1 {

    public static void main(String[] args) {

        // ---------------------------------------------------------------------------
        // EJERCICIO 1: ESCRIBIR TEXTO BÁSICO EN UN ARCHIVO .TXT
        // ---------------------------------------------------------------------------
        try {
            FileWriter fw = new FileWriter("archivo1.txt"); // Creamos un archivo de texto
            BufferedWriter bw = new BufferedWriter(fw);     // Lo envolvemos para escribir líneas más eficientemente

            bw.write("Hola, esto es un archivo de texto."); // Escribimos la primera línea
            bw.newLine(); // Insertamos un salto de línea
            bw.write("Escribiendo línea por línea con Java."); // Escribimos la segunda línea

            bw.close(); // Cerramos el archivo para guardar los cambios
            System.out.println("✅ Ejercicio 1: archivo1.txt creado y escrito."); // Confirmación en consola

            // 👉 EJERCICIO PROPUESTO: Añade más líneas que describan tu comida favorita

        } catch (IOException e) {
            System.out.println("❌ Error en Ejercicio 1."); // Mensaje si falla la escritura
        }

        // ---------------------------------------------------------------------------
        // EJERCICIO 2: LEER UN ARCHIVO .TXT LÍNEA A LÍNEA
        // ---------------------------------------------------------------------------
        try {
            FileReader fr = new FileReader("archivo1.txt"); // Abrimos archivo para lectura
            BufferedReader br = new BufferedReader(fr); // Usamos BufferedReader para leer por líneas

            String linea;
            System.out.println("📖 Ejercicio 2: leyendo archivo1.txt:");

            while ((linea = br.readLine()) != null) { // Mientras haya líneas para leer
                System.out.println("-> " + linea); // Mostramos cada línea por pantalla
            }

            br.close(); // Cerramos archivo

            // 👉 EJERCICIO PROPUESTO: Cuenta manualmente cuántas líneas se imprimen

        } catch (IOException e) {
            System.out.println("❌ Error en Ejercicio 2 al leer archivo.");
        }

        // ---------------------------------------------------------------------------
        // EJERCICIO 3: PEDIR DATOS AL USUARIO Y GUARDARLOS EN UN ARCHIVO
        // ---------------------------------------------------------------------------
        try {
            Scanner sc = new Scanner(System.in); // Iniciamos Scanner para leer datos desde consola
            System.out.print("Introduce tu nombre: ");
            String nombre = sc.nextLine(); // Leemos nombre como texto
            System.out.print("Introduce tu edad: ");
            int edad = sc.nextInt(); // Leemos edad como número

            PrintWriter pw = new PrintWriter(new FileWriter("datos_usuario.txt")); // Abrimos archivo para escritura
            pw.println("Nombre: " + nombre); // Escribimos el nombre
            pw.println("Edad: " + edad); // Escribimos la edad
            pw.close(); // Cerramos archivo

            System.out.println("✅ Ejercicio 3: datos guardados en datos_usuario.txt"); // Confirmación

            // 👉 EJERCICIO PROPUESTO: Añade una línea preguntando si eres repetidor (true/false)

        } catch (IOException e) {
            System.out.println("❌ Error en Ejercicio 3.");
        }

        // ---------------------------------------------------------------------------
        // EJERCICIO 4: CONTAR LÍNEAS Y DETECTAR PALABRAS CLAVE
        // ---------------------------------------------------------------------------
        try {
            BufferedReader br = new BufferedReader(new FileReader("archivo1.txt")); // Abrimos archivo
            String linea;
            int contador = 0; // Contador de líneas

            System.out.println("📊 Ejercicio 4: analizando archivo1.txt:");

            while ((linea = br.readLine()) != null) {
                contador++; // Aumentamos el contador por cada línea
                if (linea.contains("texto")) { // Si la línea contiene la palabra "texto"
                    System.out.println("🔍 Línea " + contador + " contiene la palabra 'texto': " + linea);
                }
            }

            System.out.println("📌 Total líneas: " + contador); // Mostramos total de líneas
            br.close(); // Cerramos archivo

            // 👉 EJERCICIO PROPUESTO: Cambia "texto" por otra palabra y repite el ejercicio

        } catch (IOException e) {
            System.out.println("❌ Error en Ejercicio 4.");
        }

        // ---------------------------------------------------------------------------
        // EJERCICIO 5: CREAR UN ARCHIVO BINARIO (.DAT) Y GUARDAR NÚMEROS
        // ---------------------------------------------------------------------------
        try {
            DataOutputStream dos = new DataOutputStream(new FileOutputStream("numeros.dat")); // Abrimos archivo binario

            dos.writeInt(10); // Guardamos un número
            dos.writeInt(25); // Guardamos otro número
            dos.writeInt(7);
            dos.writeInt(42);

            dos.close(); // Cerramos el archivo
            System.out.println("✅ Ejercicio 5: archivo binario 'numeros.dat' creado.");

            // 👉 EJERCICIO PROPUESTO: Añade tu edad como otro número binario

        } catch (IOException e) {
            System.out.println("❌ Error en Ejercicio 5.");
        }

        // ---------------------------------------------------------------------------
        // EJERCICIO 6: LEER ARCHIVO BINARIO Y MOSTRAR NÚMERO MAYOR
        // ---------------------------------------------------------------------------
        try {
            DataInputStream dis = new DataInputStream(new FileInputStream("numeros.dat")); // Abrimos archivo binario para lectura

            int mayor = Integer.MIN_VALUE; // Inicializamos variable para máximo
            int num;

            System.out.println("📥 Ejercicio 6: leyendo 'numeros.dat':");

            while (dis.available() > 0) { // Mientras queden bytes por leer
                num = dis.readInt(); // Leemos un número entero
                System.out.println("Número leído: " + num);
                if (num > mayor) mayor = num; // Actualizamos máximo si es necesario
            }

            System.out.println("🏆 Número mayor: " + mayor); // Mostramos el mayor leído
            dis.close(); // Cerramos archivo

            // 👉 EJERCICIO PROPUESTO: Haz lo mismo pero calcula también el número menor

        } catch (IOException e) {
            System.out.println("❌ Error en Ejercicio 6.");
        }

        // ---------------------------------------------------------------------------
        // EJERCICIO 7: CONTAR CUÁNTOS NÚMEROS PARES HAY EN EL ARCHIVO BINARIO
        // ---------------------------------------------------------------------------
        try {
            DataInputStream dis = new DataInputStream(new FileInputStream("numeros.dat"));
            int pares = 0;
            while (dis.available() > 0) {
                int n = dis.readInt();
                if (n % 2 == 0) pares++;
            }
            dis.close();
            System.out.println("🔢 Ejercicio 7: hay " + pares + " números pares en el archivo binario.");

            // 👉 EJERCICIO PROPUESTO: Intenta también contar los impares

        } catch (IOException e) {
            System.out.println("❌ Error en Ejercicio 7.");
        }

        // ---------------------------------------------------------------------------
        // EJERCICIO 8: ELIMINAR LÍNEAS DE TEXTO QUE CONTIENEN UNA PALABRA CLAVE
        // ---------------------------------------------------------------------------
        try {
            BufferedReader br = new BufferedReader(new FileReader("archivo1.txt"));
            PrintWriter pw = new PrintWriter("archivo_filtrado.txt");

            String linea;
            while ((linea = br.readLine()) != null) {
                if (!linea.contains("Java")) {
                    pw.println(linea);
                }
            }
            br.close();
            pw.close();
            System.out.println("🧹 Ejercicio 8: creado 'archivo_filtrado.txt' sin líneas que contienen 'Java'.");

            // 👉 EJERCICIO PROPUESTO: Cambia la palabra 'Java' por otra que tú elijas

        } catch (IOException e) {
            System.out.println("❌ Error en Ejercicio 8.");
        }
    }
}
