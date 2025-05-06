// PRACTICA SIMPLIFICADA PARA ALUMNOS
// ----------------------------------
// Este programa lee datos de un archivo de texto llamado "datos.txt"
// y determina qué alumno tiene la nota más alta y cuál la más baja.
// Está diseñado para alumnos que están empezando, usando solo FileReader y BufferedReader.
// NO USA FICHEROS BINARIOS. TODO ESTÁ EXPLICADO PASO A PASO.

import java.io.*;

public class Practica1B_Ejer2 {

    public static void main(String[] args) {

        // Variables para procesar los datos
        String nombreCompleto;     // nombre y apellidos en una sola línea
        double nota;               // nota del alumno
        boolean repetidor;         // si es repetidor o no

        // Variables para guardar los mejores y peores resultados
        String mejorAlumno = "";
        double mejorNota = Double.MIN_VALUE;
        boolean mejorRepetidor = false;

        String peorAlumno = "";
        double peorNota = Double.MAX_VALUE;
        boolean peorRepetidor = false;

        // Ruta del archivo de texto
        String archivo = "datos.txt";

        // Uso de try-with-resources para cerrar el archivo automáticamente
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {

            String linea;

            // Leer línea por línea
            while ((linea = br.readLine()) != null) {
                // Cada línea tiene este formato:
                // Nombre Apellido;7.8;false
                String[] partes = linea.split(";");

                // Extraemos los datos
                nombreCompleto = partes[0];                 // nombre y apellidos
                nota = Double.parseDouble(partes[1]);        // convertimos nota a double
                repetidor = Boolean.parseBoolean(partes[2]); // convertimos a booleano

                // Comprobamos si es la mejor nota
                if (nota > mejorNota) {
                    mejorNota = nota;
                    mejorAlumno = nombreCompleto;
                    mejorRepetidor = repetidor;
                }

                // Comprobamos si es la peor nota
                if (nota < peorNota) {
                    peorNota = nota;
                    peorAlumno = nombreCompleto;
                    peorRepetidor = repetidor;
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("El archivo no existe: " + archivo);
        } catch (IOException e) {
            System.out.println("Error al leer el archivo");
        }

        // Mostramos resultados por pantalla
        System.out.println("Alumno con mejor nota: " + mejorAlumno + " - Nota: " + mejorNota + " - Repetidor: " + mejorRepetidor);
        System.out.println("Alumno con peor nota: " + peorAlumno + " - Nota: " + peorNota + " - Repetidor: " + peorRepetidor);
    }
}

/*
EJEMPLO DE CONTENIDO DE datos.txt:
-----------------------------------
Juan Pérez;8.2;false
Ana López;6.5;true
Mario Ruiz;9.0;false
Lucía Torres;5.7;true

Cada línea tiene:
- Nombre y apellidos separados por espacio
- Punto y coma ;
- Nota como número decimal
- Punto y coma ;
- true o false indicando si repite
*/
