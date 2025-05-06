import java.io.*; // Importamos todas las clases para trabajar con archivos

public class EjercicioFacilArchivos {

    public static void main(String[] args) {

        // ----------------------------
        // 1. ESCRIBIR UN ARCHIVO .TXT
        // ----------------------------

        try {
            // FileWriter permite escribir texto en un archivo
            FileWriter fw = new FileWriter("nombres.txt");

            // PrintWriter facilita escribir línea a línea
            PrintWriter pw = new PrintWriter(fw);

            // Escribimos nombres, cada uno en una línea
            pw.println("María");
            pw.println("Antonio");
            pw.println("Lucía");
            pw.println("Carlos");

            // Cerramos para guardar los cambios
            pw.close();

            System.out.println("Archivo 'nombres.txt' creado y escrito con éxito.\n");

        } catch (IOException e) {
            // Si hay error al escribir
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
