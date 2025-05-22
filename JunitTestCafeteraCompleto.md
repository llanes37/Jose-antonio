# 🧪 Guía para Crear un Proyecto JUnit 5 en NetBeans (con Cafetera)

Este documento explica cómo preparar un proyecto en **NetBeans** para ejecutar **tests unitarios con JUnit 5** usando tu clase `Cafetera` y su clase de test `CafeteraTest`.

---

## ✅ PASO 1: Crear el Proyecto Java

1. Abre NetBeans
2. Ve a **Archivo > Nuevo Proyecto**
3. Elige:
   - Categoría: `Java con Ant` o `Java con Maven`
   - Proyecto: `Proyecto Java`
4. Ponle un nombre, por ejemplo: `ProyectoCafeteraJUnit`
5. Finaliza el asistente

---

## 📁 PASO 2: Crear las Clases Java

1. En el árbol del proyecto, haz clic derecho sobre `Source Packages > default package`
2. Selecciona `Nuevo > Clase Java`
3. Crea las siguientes clases:

### ✅ Clase 1: `Cafetera.java`

- Nombre: `Cafetera`
- Copia y pega aquí el contenido de tu clase original `Cafetera`

### ✅ Clase 2: `CafeteraTest.java`

- Nombre: `CafeteraTest`
- Copia y pega el contenido de la clase de test `CafeteraTest` (con `@Test`, `assertEquals`, etc.)

> 🔁 Ambas clases deben estar en el **mismo paquete** (por defecto) o en uno que tú definas.

---

## ⚙️ PASO 3: Añadir JUnit 5 al Proyecto

### Si usas Ant (proyecto básico):
1. Haz clic derecho sobre el proyecto > **Propiedades**
2. Ve a **Bibliotecas > Compilación**
3. Pulsa **Agregar Biblioteca** > selecciona **JUnit 5**
4. Asegúrate de que aparece en la lista de bibliotecas

### Si usas Maven:
1. Abre `pom.xml`
2. Añade estas dependencias:

```xml
<dependencies>
  <dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter</artifactId>
    <version>5.10.0</version>
    <scope>test</scope>
  </dependency>
</dependencies>
```

3. Guarda el archivo y espera que se descargue la librería

---

## ▶️ PASO 4: Ejecutar los Tests

1. Haz clic derecho sobre `CafeteraTest.java`
2. Selecciona `Test File` o presiona **Alt + F6**
3. Verás los resultados de los tests en la consola inferior

✅ Si todos los métodos pasan, aparecerán en verde con mensaje `BUILD SUCCESSFUL`.

---

## 📌 Notas Importantes

- Los archivos `Cafetera.java` y `CafeteraTest.java` **deben estar separados**
- Cada método de test debe estar marcado con `@Test`
- Usa `assertEquals`, `assertTrue`, `assertThrows` según corresponda

---

## 🧠 BONUS: Estructura Recomendada

```
ProyectoCafeteraJUnit/
├── src/
│   └── default package/
│       ├── Cafetera.java
│       └── CafeteraTest.java
└── test/
        (no necesitas modificarlo si usas Ant)
```

---

Con esto puedes usar tu clase `Cafetera` como está y solo copiar y pegar el contenido. ¡Listo para el examen!


