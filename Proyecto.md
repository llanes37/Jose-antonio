# 📘 Trabajo Práctico JavaDoc – Guía paso a paso

**Alumno:** José Antonio Martínez
**Profesor:** \[Nombre del profesor]
**Asignatura:** Programación – Optimización y Documentación
**Fecha:** \[Fecha de entrega]

---

## 🎯 Objetivo del trabajo

Crear un proyecto Java sin clase `main`, con al menos **dos clases relacionadas mediante herencia o composición**, documentarlas con JavaDoc, generar la documentación en HTML y entregar el proyecto completo comprimido con dicha documentación.

---

## 1. 🖥️ Crear el proyecto en NetBeans

1. Abrimos NetBeans y vamos a **File → New Project**.
2. Seleccionamos **Java → Java Application** → `Next`.
3. Ponemos como nombre del proyecto: `DispositivosDoc`.
4. MUY IMPORTANTE: **desmarcar** “Create Main Class”.
5. Clic en `Finish`.

---

## 2. 🧱 Crear el paquete y las clases

1. Clic derecho sobre *Source Packages* → **New → Java Package**.
   ➤ Nombre del paquete: `com.jose.dispositivos`

2. Dentro del paquete, creamos **3 clases**:

   * `Dispositivo` (abstracta)
   * `Smartphone` (hereda de Dispositivo)
   * `Tablet` (hereda de Dispositivo)

---

## 3. 📄 Clase `Dispositivo.java`

```java
package com.jose.dispositivos;

/**
 * Esta clase representa un dispositivo electrónico genérico.
 * Se utiliza como clase base para otros dispositivos más concretos.
 * Aquí están las propiedades comunes que comparten todos los dispositivos.
 */
public abstract class Dispositivo {
    protected String marca;
    protected String modelo;

    /**
     * Constructor para la clase Dispositivo.
     * Aquí asignamos marca y modelo al crear el objeto.
     * @param marca Marca del dispositivo
     * @param modelo Modelo del dispositivo
     */
    public Dispositivo(String marca, String modelo) {
        this.marca = marca;
        this.modelo = modelo;
    }

    /**
     * Método abstracto que todos los dispositivos deben implementar para encenderse.
     */
    public abstract void encender();

    /**
     * Devuelve una cadena con la marca y modelo del dispositivo.
     * @return marca y modelo como texto
     */
    public String obtenerInfo() {
        return marca + " " + modelo;
    }
}
```

💬 *Comentario personal:* Esta clase me ayuda a tener una base común para mis dispositivos. Así evito repetir el mismo código en las otras clases.

---

## 4. 📱 Clase `Smartphone.java`

```java
package com.jose.dispositivos;

/**
 * Clase que representa un smartphone (teléfono móvil inteligente).
 * Hereda de Dispositivo y añade el sistema operativo como diferencia.
 */
public class Smartphone extends Dispositivo {
    private String sistemaOperativo;

    /**
     * Constructor del smartphone.
     * Aquí añadimos además el sistema operativo que usa el móvil.
     * @param marca Marca del smartphone
     * @param modelo Modelo del smartphone
     * @param sistemaOperativo Android, iOS, etc.
     */
    public Smartphone(String marca, String modelo, String sistemaOperativo) {
        super(marca, modelo);
        this.sistemaOperativo = sistemaOperativo;
    }

    @Override
    public void encender() {
        System.out.println("Encendiendo smartphone " + modelo + " con " + sistemaOperativo);
    }

    /**
     * Devuelve el sistema operativo del smartphone.
     * @return sistema operativo como texto
     */
    public String getSistemaOperativo() {
        return sistemaOperativo;
    }
}
```

💬 *Comentario personal:* Elegí añadir el sistema operativo porque es una diferencia clara entre teléfonos. Así se ve que esta clase amplía la información base de Dispositivo.

---

## 5. 📱 Clase `Tablet.java`

```java
package com.jose.dispositivos;

/**
 * Clase que representa una tablet.
 * Se parece al smartphone pero con una propiedad nueva: stylus.
 */
public class Tablet extends Dispositivo {
    private boolean tieneStylus;

    /**
     * Constructor para la tablet.
     * Aquí indicamos si la tablet incluye stylus o no.
     * @param marca Marca de la tablet
     * @param modelo Modelo de la tablet
     * @param tieneStylus true si tiene stylus, false si no
     */
    public Tablet(String marca, String modelo, boolean tieneStylus) {
        super(marca, modelo);
        this.tieneStylus = tieneStylus;
    }

    @Override
    public void encender() {
        if (tieneStylus) {
            System.out.println("Encendiendo tablet " + modelo + " con stylus.");
        } else {
            System.out.println("Encendiendo tablet " + modelo);
        }
    }

    /**
     * Indica si la tablet tiene stylus.
     * @return true si tiene stylus
     */
    public boolean isTieneStylus() {
        return tieneStylus;
    }
}
```

💬 *Comentario personal:* Añadí esta clase para mostrar otro tipo de dispositivo y para practicar con booleanos. El `if` me permite personalizar el mensaje.

---

## 6. 📘 Cómo generar JavaDoc en NetBeans

1. Clic derecho sobre el proyecto → **Generate Javadoc**.
2. Esperar que diga **BUILD SUCCESSFUL** en la consola.
3. Abrir la carpeta `dist/javadoc/index.html`.
4. Navegar por clases y ver los métodos con sus explicaciones.

📌 *Consejo:* No uses frases genéricas como “Clase para…” en todas. Hazlas más naturales. Ej: “Esta clase representa…” o “Aquí se guarda…”

---

## 7. 📦 ¿Qué debo entregar?

1. Comprimir el proyecto completo **incluyendo**:

   * Código fuente (`src/...`)
   * Documentación generada (`dist/javadoc/`)
2. Crear un `.zip` con nombre:
   `javadoc_jose_antonio.zip`
3. Subirlo en el Aula Virtual antes del **14 de mayo a las 23:00h**

---

## ✅ Revisión final (Checklist)

* [x] Las clases tienen JavaDoc arriba con autor y versión.
* [x] Todos los métodos y constructores están documentados.
* [x] El archivo `index.html` se abre bien y se ve todo.
* [x] El `.zip` contiene todo y está limpio.
* [x] Entregado a tiempo por el Aula Virtual.

---

## 💬 Conclusión final del alumno

Este proyecto me ayudó a repasar herencia y a aprender cómo se hace documentación profesional. Usar JavaDoc me pareció fácil una vez entendí qué escribir en cada parte. Ahora sé cómo generar documentación que podría usar otro programador para entender mi código sin tener que leerlo entero.

---

**Fin del documento.**
