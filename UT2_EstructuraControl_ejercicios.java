import java.util.Scanner;

/**
 *  ! UT 2 - PRÁCTICA Nº 1 - PROGRAMACIÓN EN JAVA
 *  ? Este archivo contiene varios ejercicios resueltos en Java con explicaciones detalladas.
 *  ? Se incluyen estructuras de control, validaciones, funciones y conceptos básicos esenciales.
 *  * Cada ejercicio tiene un enunciado previo y está explicado paso a paso para facilitar el aprendizaje.
 *
 *  📌 Autor: Joaquín
 *  📆 Fecha: 2025
 */

public class UT2_EstructuraControl_ejercicios {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // *******************************
        // * 🗓️ EJERCICIO 1: VALIDAR FECHA
        // *******************************
        /**
         *  ! 📜 ENUNCIADO:
         *  Programa en Java que lea tres números enteros cualesquiera y nos diga si los tres forman una fecha correcta o no.
         *  - Un mes debe estar entre 1 y 12.
         *  - Un día debe estar entre 1 y 30.
         *  - El año debe ser distinto de 0.
         *  
         *  📌 Ejemplo:
         *  ✔ Entrada: (10, 5, 1500) -> Salida: "La fecha 10/5/1500 es correcta."
         *  ❌ Entrada: (-3, 12, 0) -> Salida: "La fecha -3/12/0 no es correcta."
         */

        System.out.println("\n🗓️ EJERCICIO 1: VALIDAR FECHA");
        System.out.print("Introduce el día: ");
        int dia = scanner.nextInt();
        System.out.print("Introduce el mes: ");
        int mes = scanner.nextInt();
        System.out.print("Introduce el año: ");
        int anio = scanner.nextInt();

        if (anio != 0 && mes >= 1 && mes <= 12 && dia >= 1 && dia <= 30) {
            System.out.println("✅ La fecha " + dia + "/" + mes + "/" + anio + " es correcta.");
        } else {
            System.out.println("❌ La fecha " + dia + "/" + mes + "/" + anio + " no es correcta.");
        }

        // **************************************
        // * 💰 EJERCICIO 2: SUELDOS DE EMPLEADOS
        // **************************************
        /**
         *  ! 📜 ENUNCIADO:
         *  Programa en Java que solicite un número entero al usuario, que representa el número de empleados de una fábrica.
         *  - Si el número ingresado es positivo, se pedirá el sueldo de cada empleado.
         *  - Se calculará el sueldo medio y la cantidad de empleados que ganan más de 1000€.
         *  - Si el número de empleados es negativo o 0, el programa finalizará mostrando un error.
         */

        System.out.println("\n💰 EJERCICIO 2: SUELDOS DE EMPLEADOS");
        System.out.print("Introduce el número de empleados: ");
        int empleados = scanner.nextInt();

        if (empleados > 0) {
            double totalSueldos = 0;
            int empleadosMasMil = 0;

            for (int i = 1; i <= empleados; i++) {
                System.out.print("Introduce el sueldo del empleado " + i + ": ");
                double sueldo = scanner.nextDouble();
                totalSueldos += sueldo;
                if (sueldo > 1000) {
                    empleadosMasMil++;
                }
            }

            System.out.println("💲 Sueldo medio: " + (totalSueldos / empleados));
            System.out.println("👥 Empleados que cobran más de 1000€: " + empleadosMasMil);
        } else {
            System.out.println("❌ Número de empleados no válido.");
        }

        // ****************************************
        // * 🔢 EJERCICIO 3: CONTAR NÚMEROS PRIMOS
        // ****************************************
        /**
         *  ! 📜 ENUNCIADO:
         *  Programa en Java que solicite un número positivo mayor que 1.
         *  - El programa debe contar cuántos números primos impares hay entre 1 y N.
         *  - Se implementará una función llamada `esPrimo()` que determinará si un número es primo.
         */

        System.out.println("\n🔢 EJERCICIO 3: CONTAR NÚMEROS PRIMOS IMPARES");
        int numero;
        do {
            System.out.print("Introduce un número mayor que 1: ");
            numero = scanner.nextInt();
        } while (numero <= 1);

        int contadorPrimos = 0;
        for (int i = 1; i <= numero; i++) {
            if (esPrimo(i) && i % 2 != 0) {
                contadorPrimos++;
            }
        }
        System.out.println("🔢 Cantidad de números primos impares entre 1 y " + numero + ": " + contadorPrimos);

        // ********************************************
        // * 🔡 EJERCICIO 4: VALIDAR CADENA
        // ********************************************
        /**
         *  ! 📜 ENUNCIADO:
         *  Programa en Java que verifique si una cadena de texto cumple ciertos requisitos.
         *  - La longitud de la cadena debe ser mayor a un número ingresado.
         *  - Si no cumple, debe contener al menos un carácter especial: `$ @ &`
         *  - Se implementa una función `validar()` para verificar estas condiciones.
         */

        System.out.println("\n🔡 EJERCICIO 4: VALIDAR CADENA");
        scanner.nextLine(); // Limpiar buffer
        System.out.print("Introduce una cadena de texto: ");
        String cadena = scanner.nextLine();
        System.out.print("Introduce un número mínimo de caracteres: ");
        int tamano = scanner.nextInt();

        if (validar(cadena, tamano)) {
            System.out.println("✅ La cadena es válida.");
        } else {
            System.out.println("❌ La cadena no es válida.");
        }

        scanner.close();
    }

    /**
     *  * 🔍 FUNCIONES AUXILIARES PARA VALIDACIONES
     */

    // * Método para verificar si un número es primo (sin usar for)
    public static boolean esPrimo(int num) {
        if (num < 2) return false;
        int divisor = 2;
        while (divisor < num) {
            if (num % divisor == 0) return false;
            divisor++;
        }
        return true;
    }

    // * Método para validar cadenas según el tamaño o caracteres especiales
    public static boolean validar(String texto, int tamano) {
        String caracteresEspeciales = "$@&";
        if (texto.length() > tamano) return true;
        for (char c : caracteresEspeciales.toCharArray()) {
            if (texto.contains(String.valueOf(c))) return true;
        }
        return false;
    }
}
