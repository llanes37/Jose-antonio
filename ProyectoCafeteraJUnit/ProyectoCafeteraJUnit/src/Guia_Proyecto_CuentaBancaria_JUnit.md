# 🏦 Proyecto Java + JUnit 5: Cuenta Bancaria (Nivel Básico-Intermedio)

Este proyecto guía paso a paso cómo crear una **clase `CuentaBancaria`** y una clase de pruebas `CuentaBancariaTest` usando **JUnit 5** en **NetBeans**.

Es ideal para **aprender a diseñar clases con lógica de negocio simple** y validarlas mediante **pruebas automatizadas**.

---

## 🎯 Objetivo del Proyecto

- Practicar Programación Orientada a Objetos (POO)
- Validar funcionalidad con tests unitarios
- Familiarizarse con NetBeans y JUnit 5

---

## 🧠 ¿Qué vamos a construir?

### Clase: `CuentaBancaria`

Una clase que represente una cuenta bancaria básica, con métodos como:
- `depositar(double cantidad)`
- `retirar(double cantidad)`
- `consultarSaldo()`
- `bloquearCuenta()`
- `estaBloqueada()`

### Clase de Test: `CuentaBancariaTest`

Una clase que contenga **pruebas unitarias** para cada uno de esos métodos, incluyendo casos normales y casos de error.

---

## ✅ PASOS A SEGUIR EN NETBEANS

### 1. Crear Proyecto Java
- Archivo > Nuevo Proyecto > Java con Ant > Proyecto Java
- Nombre: `CuentaBancariaJUnit`

### 2. Crear archivo `CuentaBancaria.java`
- Click derecho sobre `src` > Nuevo > Clase Java > `CuentaBancaria`

### 3. Escribir esta estructura básica:

```java
public class CuentaBancaria {
    private double saldo;
    private boolean bloqueada;

    public CuentaBancaria() {
        this.saldo = 0;
        this.bloqueada = false;
    }

    public double consultarSaldo() {
        return saldo;
    }

    public void depositar(double cantidad) {
        if (bloqueada || cantidad <= 0) return;
        saldo += cantidad;
    }

    public void retirar(double cantidad) {
        if (bloqueada || cantidad <= 0 || cantidad > saldo) return;
        saldo -= cantidad;
    }

    public void bloquearCuenta() {
        bloqueada = true;
    }

    public boolean estaBloqueada() {
        return bloqueada;
    }
}
```

---

### 4. Crear archivo `CuentaBancariaTest.java`
- Click derecho sobre `src` > Nuevo > Clase Java > `CuentaBancariaTest`

### 5. Agregar imports de JUnit 5:

```java
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
```

---

## 🧪 Tests a crear

```java
public class CuentaBancariaTest {
    private CuentaBancaria cuenta;

    @BeforeEach
    public void setUp() {
        cuenta = new CuentaBancaria();
    }

    @Test
    public void testDepositoValido() {
        cuenta.depositar(100);
        assertEquals(100, cuenta.consultarSaldo());
    }

    @Test
    public void testRetiroValido() {
        cuenta.depositar(200);
        cuenta.retirar(150);
        assertEquals(50, cuenta.consultarSaldo());
    }

    @Test
    public void testRetiroInvalidoPorSaldoInsuficiente() {
        cuenta.depositar(50);
        cuenta.retirar(100);
        assertEquals(50, cuenta.consultarSaldo());
    }

    @Test
    public void testBloquearCuentaImpideDeposito() {
        cuenta.bloquearCuenta();
        cuenta.depositar(100);
        assertEquals(0, cuenta.consultarSaldo());
    }

    @Test
    public void testBloquearCuentaImpideRetiro() {
        cuenta.depositar(100);
        cuenta.bloquearCuenta();
        cuenta.retirar(50);
        assertEquals(100, cuenta.consultarSaldo());
    }

    @Test
    public void testBloqueo() {
        assertFalse(cuenta.estaBloqueada());
        cuenta.bloquearCuenta();
        assertTrue(cuenta.estaBloqueada());
    }

    @AfterEach
    public void tearDown() {
        cuenta = null;
    }
}
```

---

## ⚙️ Añadir JUnit 5 a NetBeans

1. Clic derecho sobre el proyecto > Propiedades
2. Ir a **Bibliotecas > Compilación**
3. Clic en **Agregar Biblioteca > JUnit 5**

---

## ✅ Qué aprendes con este ejercicio

- Cómo validar métodos con condiciones (`if`, comparaciones)
- Cómo controlar estados (como `bloqueada`)
- Cómo usar `assertEquals`, `assertTrue`, `assertFalse`
- Cómo preparar objetos antes de cada test (`@BeforeEach`)

---

## 🧑‍💻 TAREAS EXTRAS PARA PRACTICAR

1. ✏️ **Agregar método `desbloquearCuenta()`**
   - Permite volver a usar la cuenta
   - Añadir test que lo verifique

2. ✏️ **Agregar método `transferir(CuentaBancaria destino, double cantidad)`**
   - Solo si la cuenta no está bloqueada y hay saldo suficiente
   - Escribir tests para:
     - Transferencia válida
     - Transferencia con cuenta bloqueada
     - Transferencia sin saldo suficiente

3. ✏️ **Evitar valores negativos**
   - Mejorar los métodos `depositar()` y `retirar()` para lanzar excepción si `cantidad <= 0`
   - Añadir test con `assertThrows`

4. ✏️ **Crear un método `cerrarCuenta()`**
   - Debe vaciar el saldo y bloquear la cuenta
   - Verificar con test que tras cerrarla no se pueda operar más

---

## 🎓 ¿Y después?

Puedes crear tu propio proyecto desde cero cambiando el dominio:
- 📚 Biblioteca (libros prestados)
- 🎟️ Cine (compra de entradas)
- 🛒 Carrito de la compra (agregar/eliminar productos)

Sigue la misma estructura:
- Crear clase principal con métodos
- Crear clase `Test` con pruebas para cada uno

---

© Proyecto educativo JUnit 5 - Prueba de lógica, condiciones y estados en Java
