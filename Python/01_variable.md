
# Ejemplo Completo con Variables, Condicionales y Ejercicios en Python

Este archivo contiene ejemplos completos y ejercicios prácticos sobre cómo utilizar variables, condicionales y otros conceptos básicos de Python en un contexto de administración de sistemas.

---

## Sección 1: Variables en Python

Las variables en Python se utilizan para almacenar datos. En este caso, simulamos la gestión de un servidor, almacenando datos sobre su nombre, uso de CPU, memoria disponible y si está activo o no.

### Código de ejemplo:
```python
nombre_servidor = "Servidor_1"
cpu_uso = 85.5
memoria_disponible = 4096
es_activo = True

print(f"Nombre del servidor: {nombre_servidor}")
print(f"Uso de CPU: {cpu_uso}%")
print(f"Memoria disponible: {memoria_disponible} MB")
print(f"¿El servidor está activo? {es_activo}")
```

---

## Sección 2: Verificación de Tipos de Variables

Python permite trabajar con diferentes tipos de variables. Podemos usar la función `type()` para verificar el tipo de dato de cada variable.

### Código de ejemplo:
```python
print(f"El tipo de nombre_servidor es: {type(nombre_servidor)}")
print(f"El tipo de cpu_uso es: {type(cpu_uso)}")
print(f"El tipo de memoria_disponible es: {type(memoria_disponible)}")
print(f"El tipo de es_activo es: {type(es_activo)}")
```

---

## Sección 3: Cambio Dinámico de Tipo de Variables

Aquí mostramos cómo se puede cambiar el tipo de una variable en Python de manera dinámica.

### Código de ejemplo:
```python
cpu_uso = 85.5
print(f"Uso de CPU: {cpu_uso}% (tipo {type(cpu_uso)})")

cpu_uso = "85.5%"
print(f"Uso de CPU ahora es: {cpu_uso} (tipo {type(cpu_uso)})")
```

---

## Sección 4: Variables y Entrada del Usuario

En esta sección mostramos cómo las variables pueden tomar valores introducidos por el usuario.

### Código de ejemplo:
```python
nuevo_nombre_servidor = input("Introduce el nuevo nombre del servidor:")
print(f"El servidor ha sido renombrado a {nuevo_nombre_servidor}.")
```

---

## Sección 5: Conversión de Tipos a partir de la Entrada del Usuario

Convertimos entradas del usuario a otros tipos de datos como enteros para su procesamiento.

### Código de ejemplo:
```python
numero_procesos = int(input("Introduce el número de procesos que deseas ejecutar en paralelo:"))
print(f"Se configuraron {numero_procesos} procesos para ejecutar en paralelo.")
```

---

## Sección 6: Uso de Listas y Diccionarios

Demostramos el uso de listas y diccionarios en un contexto práctico de administración de sistemas.

### Código de ejemplo:
```python
usuarios_conectados = ["admin", "user1", "user2", "guest"]
usuarios_conectados.append("user3")
print(f"Usuarios conectados actualizados: {usuarios_conectados}")

servidor_info = {
    "nombre": "Servidor_1",
    "cpu_uso": 75.5,
    "memoria_disponible": 2048,
    "activo": True
}
print(f"Información del servidor: {servidor_info}")
```

---

## Sección Final: Autoevaluación

### Tareas:
1. Crea una variable que almacene el nombre de un servidor (cadena de texto).
2. Define una lista que almacene los nombres de dos servicios activos en ese servidor.
3. Define un diccionario que contenga los siguientes datos: nombre del servidor, cantidad de servicios, y si el servidor está activo o no (booleano).
4. Imprime todos los datos almacenados, incluyendo la lista de servicios.
5. Cambia el estado del servidor a 'no activo' y actualiza el diccionario.

### Código de ejemplo:
```python
nombre_servidor = "Servidor_Autoevaluacion"
servicios_activos = ["Servicio_Web", "Servicio_BD"]

servidor_info = {
    "nombre": nombre_servidor,
    "cantidad_servicios": len(servicios_activos),
    "activo": True
}
print(f"Nombre del servidor: {servidor_info['nombre']}")
print(f"Cantidad de servicios: {servidor_info['cantidad_servicios']}")
print(f"Servicios activos: {servicios_activos}")
print(f"¿El servidor está activo? {servidor_info['activo']}")

servidor_info["activo"] = False
print("El estado del servidor ha sido actualizado a: No activo.")
print(f"Estado actual del servidor: {servidor_info['activo']}")
```

---

Este archivo cubre conceptos clave en Python relacionados con variables, tipos de datos, entrada del usuario, y estructuras más avanzadas como listas y diccionarios.
