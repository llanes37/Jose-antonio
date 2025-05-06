import java.io.*; // Importamos todas las clases para trabajar con archivos
import java.util.Scanner;

public class EjercicioFacilArchivos {

    public static void main(String[] args) {

        // ----------------------------
        // 1. ESCRIBIR UN ARCHIVO .TXT
        // ----------------------------

      
        try {
            Scanner sc = new Scanner(System.in); // Creamos un lector desde teclado

            System.out.print("Introduce tu edad: ");
            int edad = sc.nextInt(); // Leemos la edad como número

            FileWriter fw = new FileWriter("nombres.txt");
            BufferedWriter bw = new BufferedWriter(fw);

            // Escribimos según la edad introducida
            if (edad >= 18) {
                bw.write("El usuario es mayor de edad.");
            } else {
                bw.write("El usuario es menor de edad.");
            }
           
            bw.close(); // Cerramos archivo

            System.out.println("✅ Archivo 'nombres.txt' creado con el resultado de la edad.");

        } catch (IOException e) {
            System.out.println("❌ Error al escribir en el archivo.");
        }
    

        // ----------------------------
        // 2. LEER EL ARCHIVO .TXT
        // ----------------------------

        try {
            // FileReader lee texto desde un archivo
            FileReader fr = new FileReader("nombres.txt");

            // BufferedReader permite leer línea a línea
            BufferedReader br = new BufferedReader(fr);

            String linea;

            System.out.println("📖 Nombres leídos del archivo:");

            // Leemos hasta que no haya más líneas (null)
            while ((linea = br.readLine()) != null) {
                System.out.println("- " + linea);
            }

            // Cerramos el archivo
            br.close();

        } catch (IOException e) {
            // Si hay error al leer
            System.out.println("❌ Error al leer el archivo.");
        }
    }
}
