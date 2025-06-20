# Corrección del Examen: Gestión de Recursos y Red

Este documento contiene las explicaciones y correcciones detalladas para cada una de las preguntas del examen sobre condicionales, bucles y funciones. Cada sección incluye una descripción del objetivo, el enfoque de resolución y el código correcto.

---

## Sección 1: Condicionales (3 Puntos)

### **Pregunta 1**: Evaluación de Uso de Recursos

#### **Objetivo:**
Evaluar si el uso de CPU, memoria y disco se encuentra en niveles críticos, moderados o seguros.

#### **Resolución:**
- Se solicitan tres valores al usuario.
- Se evalúan las condiciones:
  - Si todos los valores son mayores al 90%, se muestra "Sobrecarga crítica".
  - Si uno o dos valores están en riesgo, se identifica cuáles son.
  - Si ninguno está en riesgo, se muestra "Servidor en buen estado".

#### **Código Correcto:**
```python
cpu = float(input("Introduce el uso de CPU (%): "))
memoria = float(input("Introduce el uso de Memoria (%): "))
disco = float(input("Introduce el uso de Disco (%): "))

if cpu > 90 and memoria > 90 and disco > 90:
    print("Sobrecarga crítica")
elif cpu > 90 or memoria > 90 or disco > 90:
    if cpu > 90:
        print("CPU en riesgo")
    if memoria > 90:
        print("Memoria en riesgo")
    if disco > 90:
        print("Disco en riesgo")
else:
    print("Servidor en buen estado")
```

---

### **Pregunta 2**: Estado del Servidor

#### **Objetivo:**
Determinar el estado del servidor y el riesgo por uso elevado de CPU si está conectado.

#### **Resolución:**
- Solicitar al usuario si el servidor está conectado ("sí" o "no").
- Evaluar si el uso de CPU supera el 85% cuando está conectado.
- Mostrar "Servidor desconectado" si no lo está.

#### **Código Correcto:**
```python
conexion = input("¿El servidor está conectado? (sí/no): ")
cpu = float(input("Introduce el uso de CPU (%): "))

if conexion == "sí":
    if cpu > 85:
        print("Riesgo por alta CPU")
    else:
        print("Servidor conectado y en buen estado")
else:
    print("Servidor desconectado")
```

---

### **Pregunta 3**: Tiempo de Respuesta del Servidor

#### **Objetivo:**
Clasificar el tiempo de respuesta de un servidor como rápido, moderado o lento.

#### **Resolución:**
- Solicitar al usuario el tiempo de respuesta en milisegundos.
- Evaluar:
  - Menor a 100 ms: "Respuesta rápida".
  - Entre 100 y 500 ms: "Respuesta moderada".
  - Mayor a 500 ms: "Respuesta lenta".

#### **Código Correcto:**
```python
tiempo_respuesta = int(input("Introduce el tiempo de respuesta (ms): "))

if tiempo_respuesta < 100:
    print("Respuesta rápida")
elif 100 <= tiempo_respuesta <= 500:
    print("Respuesta moderada")
else:
    print("Respuesta lenta")
```

---

## Sección 2: Bucles (2 Puntos)

### **Pregunta 1**: Captura de Direcciones IP

#### **Objetivo:**
Solicitar al usuario ingresar 5 direcciones IP y mostrarlas.

#### **Resolución:**
- Usar un bucle `for` para capturar las 5 direcciones IP.
- Almacenar las direcciones en una lista y mostrarlas al final.

#### **Código Correcto:**
```python
ips = []

for i in range(5):
    ip = input(f"Introduce la dirección IP {i+1}: ")
    ips.append(ip)

print("Las direcciones IP ingresadas son:", ips)
```

---

### **Pregunta 2**: Servicios Activos

#### **Objetivo:**
Recorrer una lista de servicios y verificar cuáles están activos.

#### **Resolución:**
- Iterar sobre una lista de servicios.
- Solicitar al usuario si cada servicio está activo.
- Agregar los servicios activos a una nueva lista y mostrarlos al final.

#### **Código Correcto:**
```python
servicios = ["HTTP", "DNS", "SSH", "FTP"]
servicios_activos = []

for servicio in servicios:
    activo = input(f"¿El servicio {servicio} está activo? (sí/no): ")
    if activo == "sí":
        servicios_activos.append(servicio)

print("Servicios activos:", servicios_activos)
```

---

## Sección 3: Funciones (5 Puntos)

### **Pregunta 1**: Promedio de una Lista

#### **Objetivo:**
Definir una función para calcular el promedio de una lista de números.

#### **Resolución:**
- Usar la función `sum()` para sumar los valores de la lista.
- Dividir la suma entre la cantidad de elementos con `len()`.

#### **Código Correcto:**
```python
def calcular_promedio(numeros):
    return sum(numeros) / len(numeros)

numeros = [float(input(f"Introduce el número {i+1}: ")) for i in range(5)]
print("El promedio es:", calcular_promedio(numeros))
```

---

### **Pregunta 2**: Evaluar Riesgos

#### **Objetivo:**
Evaluar los riesgos de CPU, memoria y disco según tres niveles.

#### **Resolución:**
- Recibir tres porcentajes como parámetros.
- Usar condiciones para determinar el nivel de riesgo.

#### **Código Correcto:**
```python
def evaluar_riesgos(cpu, memoria, disco):
    if cpu < 70 and memoria < 70 and disco < 70:
        return "Sin riesgos"
    elif 70 <= cpu <= 90 or 70 <= memoria <= 90 or 70 <= disco <= 90:
        return "Riesgo moderado"
    else:
        return "Riesgo crítico"

cpu = float(input("Introduce el uso de CPU (%): "))
memoria = float(input("Introduce el uso de Memoria (%): "))
disco = float(input("Introduce el uso de Disco (%): "))
print("Estado de riesgos:", evaluar_riesgos(cpu, memoria, disco))
```

---

### **Pregunta 3**: Monitorear IPs

#### **Objetivo:**
Recibir una lista de IPs y devolver cuáles están activas.

#### **Resolución:**
- Usar una función para iterar sobre las IPs.
- Preguntar si cada IP está activa y devolver las activas.

#### **Código Correcto:**
```python
def monitorear_ips(ips):
    activas = []
    for ip in ips:
        estado = input(f"¿La IP {ip} está activa? (sí/no): ")
        if estado == "sí":
            activas.append(ip)
    return activas

ips = [input(f"Introduce la dirección IP {i+1}: ") for i in range(3)]
print("IPs activas:", monitorear_ips(ips))
