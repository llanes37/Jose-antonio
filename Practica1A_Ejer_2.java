/*
 * Ejercicio 2: se lee el fichero "numeros.bin" y calculamos el valor mayor y menor de los contenidos en el fichero
 */

// No olvides cambiar el nombre del paquete si lo usas en NetBeans
// package tema8;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Autor del código original
 */
public class Practica1A_Ejer_2 {

    public static void main(String[] args) {

        // Creamos el fichero automáticamente antes de leerlo (si no existe)
        crearFicheroBinario(); // ← Añadido

        String fichero = "numeros.bin";
        // Se define el nombre del fichero que se va a leer

        // try-with-resources: abre el archivo y lo cierra automáticamente al terminar
        try (BufferedInputStream bin = new BufferedInputStream(new FileInputStream(fichero))) {

            int num, mayor, menor;
            // Se definen tres variables:
            // num    → para guardar cada número leído
            // mayor  → para guardar el mayor número encontrado
            // menor  → para guardar el menor número encontrado

            System.out.println("Leyendo ...");
            // Muestra por consola que comienza la lectura

            num = bin.read();
            // Lee el primer número del fichero (como byte, 0-255) y lo guarda en num

            mayor = menor = num;
            // Inicializa tanto 'mayor' como 'menor' con el primer número leído

            System.out.println(mayor + " " + menor);
            // Muestra por pantalla los valores iniciales

            while (num != -1) {
                // Mientras no se llegue al final del fichero (read() devuelve -1 al final)

                if (num > mayor) {
                    mayor = num;
                }
                // Si el número leído es mayor que el actual 'mayor', se actualiza

                if (num < menor) {
                    menor = num;
                }
                // Si el número leído es menor que el actual 'menor', se actualiza

                System.out.print(num + " ");
                // Muestra el número leído en la misma línea, separado por espacios

                num = bin.read();
                // Lee el siguiente número del fichero
            }

            System.out.println("");
            // Salto de línea para separar resultados

            System.out.printf("Menor número %d mayor numero %d", menor, mayor);
            // Muestra el resultado final con el número menor y el mayor encontrados

        } catch (FileNotFoundException ex) {
            // Si no se encuentra el archivo, se muestra este mensaje
            System.out.println("Error al abrir el fichero");
        } catch (IOException ex) {
            // Si ocurre un error al leer el archivo
            System.out.println("Error al leer del fichero");
        }

    }

    // Método auxiliar para crear el fichero binario si no existe
    public static void crearFicheroBinario() {
        String fichero = "numeros.bin";
        int[] datos = {15, 8, 23, 42, 4, 30, 17}; // Valores que vamos a guardar

        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(fichero))) {
            for (int dato : datos) {
                bos.write(dato); // Escribimos cada número como byte
            }
            System.out.println("Archivo 'numeros.bin' creado con datos de prueba.");
        } catch (IOException e) {
            System.out.println("Error al crear el archivo binario: " + e.getMessage());
        }
    }
}
