# 📚 Proyecto Java · JDBC + SQLite con Patrón MVC

Este proyecto es una guía práctica para aprender cómo crear una aplicación Java que se conecte a una base de datos SQLite, utilizando el patrón MVC (**Modelo - Vista - Controlador**). Está completamente comentado y preparado para enseñanza paso a paso, con tareas integradas para el alumno.

---

## 🧠 Objetivos de Aprendizaje

- Comprender cómo funciona JDBC en Java
- Aprender a aplicar el patrón MVC
- Separar responsabilidades: Modelo, Vista y Controlador
- Practicar operaciones CRUD con base de datos
- Mejorar el diseño de software usando DAO (Data Access Object)

---

## 🧱 Estructura del Proyecto

```
ProyectoJDBC_MVC/
├── modelo/
│   └── Usuario.java           # Clase que representa un usuario (datos)
├── dao/
│   └── UsuarioDAO.java        # Acceso a la base de datos (CRUD)
├── vista/
│   └── MenuConsola.java       # Interfaz por consola
├── controlador/
│   └── MainApp.java           # Lógica principal del programa
```

---

## 🔄 Flujo del Programa

1. El usuario ejecuta `MainApp.java`
2. Se crea (si no existe) la base de datos `miBaseDatos.db`
3. Se muestra un menú por consola
4. Según la opción, se insertan o listan usuarios usando `UsuarioDAO`

---

## 🧩 Detalle de Clases

### `Usuario.java` (modelo)

- Representa la tabla `usuarios`
- Tiene: `id`, `nombre`, `edad`
- Se usa como estructura de datos entre DAO y Controlador

### `UsuarioDAO.java` (acceso a datos)

- Métodos:
  - `insertar(Usuario)`
  - `listarTodos()`
  - `actualizar(Usuario)`
  - `eliminarPorId(int)`
  - `contarUsuarios()`
- Se conecta directamente con la base de datos

### `MenuConsola.java` (vista)

- Muestra el menú en consola
- Recoge la opción del usuario
- NO accede a la base de datos

### `MainApp.java` (controlador)

- Controla el flujo del programa
- Muestra el menú
- Usa el DAO para insertar, listar, etc.
- Mantiene la conexión abierta mientras se ejecuta

---

## 🧪 Tareas para el Alumno

### 👨‍💻 Funcionales
- [ ] Añadir opción 3 al menú: actualizar usuario
- [ ] Añadir opción 4: eliminar usuario por ID
- [ ] Añadir opción 5: contar usuarios registrados
- [ ] Añadir validaciones al modelo (`edad > 0`, `nombre != ""`)

### 🎨 Visuales
- [ ] Mejorar el menú con colores o formato ASCII
- [ ] Capturar errores si el usuario introduce texto en lugar de números

### 💾 Extra
- [ ] Exportar los usuarios a un archivo `.txt`
- [ ] Crear otra entidad (Producto, Categoría...) siguiendo el mismo patrón

---

## 🚀 Requisitos para Ejecutar

- Java 17 o superior
- Archivo `sqlite-jdbc-3.36.0.3.jar` en el classpath
- IDE recomendado: Visual Studio Code o IntelliJ

---

## 🧠 Conclusión

Este proyecto es una base sólida para enseñar o aprender cómo crear aplicaciones Java conectadas a base de datos con una estructura profesional y mantenible.