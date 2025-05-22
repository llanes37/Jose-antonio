# 🧪 Proyecto Completo JUnit 5 - Cafetera (para NetBeans o Visual Studio Code)

Este proyecto es una guía completa para trabajar con **pruebas unitarias en Java usando JUnit 5**, centrado en un ejemplo didáctico: la clase `Cafetera` y sus tests.

---

## 🎯 Objetivo del Proyecto

- Entender cómo se estructuran y ejecutan pruebas unitarias con **JUnit 5**
- Aprender a escribir tests con anotaciones como `@Test`, `@BeforeEach`, `@AfterEach`, `@AfterAll`
- Detectar errores comunes, validar métodos y practicar programación orientada a objetos

---

## 📦 Contenido del Proyecto

```
ProyectoCafeteraJUnit/
├── src/
│   ├── Cafetera.java          // Clase lógica completa con comentarios
│   └── CafeteraTest.java      // Clase de pruebas con JUnit 5 explicadas
├── README_ProyectoCafetera.md // Esta guía teórica + práctica
```

---

## 🧠 Teoría de JUnit 5 explicada

### ¿Qué es una prueba unitaria?

Una prueba unitaria valida **una unidad mínima de código (un método)** para asegurar que se comporta como se espera.

---

### Ventajas de las pruebas unitarias

- ✅ Permiten detectar errores rápidamente
- 🔁 Se pueden repetir automáticamente
- 💡 Sirven como documentación viva del código

---

### Anotaciones principales en JUnit 5

| Anotación      | Qué hace                                                    |
|----------------|-------------------------------------------------------------|
| `@Test`        | Marca un método como prueba unitaria                        |
| `@BeforeEach`  | Se ejecuta antes de cada test                               |
| `@AfterEach`   | Se ejecuta después de cada test                             |
| `@AfterAll`    | Se ejecuta una vez al final de todas las pruebas            |
| `@Disabled`    | Desactiva temporalmente un test                             |

---

### Assertions más comunes

| Método                     | Descripción                                      |
|----------------------------|--------------------------------------------------|
| `assertEquals(a, b)`       | Comprueba que `a == b`                           |
| `assertTrue(condición)`    | Verifica que la condición sea verdadera          |
| `assertFalse(condición)`   | Verifica que la condición sea falsa              |
| `assertThrows(Exception.class, ...)` | Comprueba que se lanza una excepción    |

---

## 🧪 Clase `CafeteraTest.java`

Esta clase contiene **pruebas automáticas para los métodos de Cafetera**:

- Test de constructores (por defecto y con capacidad)
- Test de `llenarCafetera()`
- Test de `servirTaza()` con y sin suficiente café
- Test de `vaciarCafetera()`
- Test de `quedaCafe()`
- Test de `agregarCafe()` con y sin desbordamiento (usa `assertThrows`)

---

## ▶️ Cómo usar este proyecto en NetBeans

1. Crea un nuevo proyecto Java
2. Añade los archivos `Cafetera.java` y `CafeteraTest.java` dentro de `src/`
3. Haz clic derecho sobre el proyecto > **Propiedades**
4. Ve a **Bibliotecas > Compilación**
5. Añade la biblioteca **JUnit 5**
6. Haz clic derecho sobre `CafeteraTest.java` > **Test File**

---

## ▶️ Cómo usarlo en Visual Studio Code

1. Instala las extensiones:
   - Java Extension Pack
   - Java Test Runner
2. Coloca ambos archivos `.java` en tu carpeta de proyecto
3. Asegúrate de tener los `.jar` de JUnit en la carpeta `lib/` o usa Maven
4. Haz clic en el icono ▶ junto a cada `@Test` para ejecutar

---

## 🧑‍🏫 Ideal para clases

Este proyecto está preparado para:

- Explicar teoría en el mismo archivo
- Copiar y pegar directamente en el IDE
- Probar métodos reales en tiempo real

---

## 📌 Recomendaciones Finales

- Mantén los tests simples y bien comentados
- No mezcles lógica de test con lógica de negocio
- Usa nombres descriptivos para los métodos de prueba
- Ejecuta los tests constantemente mientras desarrollas

---

© 2025 · Proyecto educativo con JUnit 5 - Compatible con NetBeans y VS Code.
