/******************************************************************************************
 *                        📚 **TEORÍA Y CONCEPTOS: NÚMEROS ALEATORIOS Y BUCLES**
 * ──────────────────────────────────────────────────────────────────────────────
 * En esta práctica aprenderás a:
 * 
 * 1️⃣ Contar de 2 en 2 hasta un número dado utilizando bucles.
 * 2️⃣ Generar números aleatorios y contar cuántos cumplen una condición (por ejemplo, ser pares).
 * 3️⃣ Generar números aleatorios y contar cuántos son mayores a un umbral (por ejemplo, 50).
 * 4️⃣ Sumar todos los números impares entre 1 y N.
 * 5️⃣ Generar la tabla de multiplicar de un número.
 * 
 * 💡 *TAREA PARA EL ALUMNO:* En cada ejemplo, modifica el código según las indicaciones en los
 * comentarios para practicar y afianzar lo aprendido.
 *
 * 🚀 ¡Explora, experimenta y mejora el código!
 ******************************************************************************************/

 import java.util.Scanner;  // Para leer datos ingresados por el usuario
 import java.util.Random;   // Para generar números aleatorios
 
 public class UT2_EstructuraControl_NumerosAleatorios {
 
     public static void main(String[] args) {
         // ¡Descomenta cada método para probar los ejemplos!
         ejemploContarDe2En2();
         ejemploGenerarPares();
         ejemploGenerarAleatoriosContarMayor50();
         ejemploSumarImpares();
         ejemploTablaMultiplicar();
     }
 
     // =====================================================
     // ! 📌 SECCIÓN 1: Contar de 2 en 2 hasta un número dado
     // =====================================================
     /**
      * 📖 TEORÍA:
      * - Se utiliza un bucle "for" que comienza en 0 y se incrementa de 2 en 2 hasta alcanzar
      *   el número ingresado por el usuario.
      * 
      * 💡 TAREA PARA EL ALUMNO: Modifica el bucle para que cuente de 3 en 3.
      */
     public static void ejemploContarDe2En2() {
         Scanner sc = new Scanner(System.in);
         System.out.print("👉 Introduce un número: ");
         int N = sc.nextInt(); // Leemos el número N ingresado por el usuario
 
         // 🔵 Bucle for: Contamos de 2 en 2 hasta N
         for (int i = 0; i <= N; i += 2) { 
             System.out.println(i); // Imprime cada número generado
         }
     }
 
     // =====================================================
     // ! 📌 SECCIÓN 2: Generar 5 números aleatorios y contar cuántos son pares
     // =====================================================
     /**
      * 📖 TEORÍA:
      * - Se utiliza la clase Random para generar números aleatorios entre 1 y 100.
      * - Se recorre un bucle para generar 5 números y se verifica si cada uno es par.
      * 
      * 💡 TAREA PARA EL ALUMNO: Modifica el rango de números (por ejemplo, entre 1 y 200)
      * y cambia la cantidad de números generados (por ejemplo, 10 números).
      */
     public static void ejemploGenerarPares() {
         Random rand = new Random(); // Objeto para generar números aleatorios
         int contadorPares = 0;      // Contador para los números pares
 
         // 🔵 Bucle for: Generar 5 números aleatorios
         for (int i = 0; i < 5; i++) {
             int numero = rand.nextInt(100) + 1; // Número aleatorio entre 1 y 100
             System.out.println("Número generado: " + numero); // Imprime el número generado
             if (numero % 2 == 0) { // Verifica si el número es par
                 contadorPares++; // Incrementa el contador si es par
             }
         }
         System.out.println("✅ Cantidad de números pares generados: " + contadorPares);
     }
 
     // =====================================================
     // ! 📌 SECCIÓN 3: Generar 10 números aleatorios y contar cuántos son mayores a 50
     // =====================================================
     /**
      * 📖 TEORÍA:
      * - Se generan 10 números aleatorios entre 1 y 100.
      * - Se cuenta cuántos de esos números son mayores a 50 usando una condición en un bucle.
      * 
      * 💡 TAREA PARA EL ALUMNO: Experimenta cambiando el umbral (por ejemplo, 70 en lugar de 50)
      * o modifica la cantidad de números generados.
      */
     public static void ejemploGenerarAleatoriosContarMayor50() {
         Random rand = new Random(); // Objeto para generar números aleatorios
         int contadorMayor50 = 0;    // Contador para los números mayores a 50
 
         // 🔵 Bucle for: Generar 10 números aleatorios
         for (int i = 0; i < 10; i++) {
             int numero = rand.nextInt(100) + 1; // Número aleatorio entre 1 y 100
             System.out.println("Número generado: " + numero);
             if (numero > 50) { // Comprueba si el número es mayor a 50
                 contadorMayor50++; // Incrementa el contador
             }
         }
         System.out.println("✅ Cantidad de números mayores a 50: " + contadorMayor50);
     }
 
     // =====================================================
     // ! 📌 SECCIÓN 4: Sumar los números impares entre 1 y N
     // =====================================================
     /**
      * 📖 TEORÍA:
      * - Se solicita un número N y se recorre un bucle desde 1 hasta N.
      * - Se verifica si cada número es impar (usando el operador módulo).
      * - Se acumulan los números impares en una variable de suma.
      * 
      * 💡 TAREA PARA EL ALUMNO: Modifica el ejemplo para que, además de sumar,
      * se cuente la cantidad de números impares encontrados.
      */
     public static void ejemploSumarImpares() {
         Scanner sc = new Scanner(System.in);
         System.out.print("👉 Introduce un número: ");
         int N = sc.nextInt(); // Número N ingresado por el usuario
         int suma = 0;         // Inicializa la suma de números impares
 
         // 🔵 Bucle for: Recorre desde 1 hasta N, sumando los impares
         for (int i = 1; i <= N; i++) {
             if (i % 2 != 0) { // Comprueba si el número es impar
                 suma += i;    // Suma el número impar
             }
         }
         System.out.println("✅ La suma de los números impares hasta " + N + " es: " + suma);
     }
 
     // =====================================================
     // ! 📌 SECCIÓN 5: Generar una tabla de multiplicar
     // =====================================================
     /**
      * 📖 TEORÍA:
      * - Se solicita un número N y se genera su tabla de multiplicar usando un bucle for.
      * - Cada iteración multiplica N por el contador del bucle (del 1 al 10).
      * 
      * 💡 TAREA PARA EL ALUMNO: Modifica el ejemplo para que la tabla se imprima hasta 12
      * o permite al usuario elegir hasta qué número multiplicar.
      */
     public static void ejemploTablaMultiplicar() {
         Scanner sc = new Scanner(System.in);
         System.out.print("👉 Introduce un número: ");
         int N = sc.nextInt(); // Número N ingresado por el usuario
 
         // 🔵 Bucle for: Genera la tabla de multiplicar de N desde 1 hasta 10
         for (int i = 1; i <= 10; i++) {
             int resultado = N * i; // Calcula la multiplicación
             System.out.println(N + " x " + i + " = " + resultado); // Muestra el resultado
         }
     }
 }
 