# -------------------------------------------------------------------------------------------
# EXAMEN: GESTIÓN DE OPERACIONES MILITARES
# TEMA: CONDICIONALES, BUCLES Y FUNCIONES
# DURACIÓN: 45 MINUTOS
# NOTA MÁXIMA: 10 PUNTOS
# -------------------------------------------------------------------------------------------
# INSTRUCCIONES:
# 1. Completa cada sección según lo indicado.
# 2. Solo puedes usar tus apuntes y documentación personal. No se permite el uso de IA.
# 3. Verifica que tu código se ejecute correctamente antes de pasar a la siguiente sección.
# -------------------------------------------------------------------------------------------

# -------------------------------------------------------------------------------------------
# SECCIÓN 1: CONDICIONALES (3 PUNTOS)
# -------------------------------------------------------------------------------------------

# AYUDA: OPERADORES COMPARATIVOS Y LÓGICOS
# - `>`  : Mayor que
# - `<`  : Menor que
# - `>=` : Mayor o igual que
# - `<=` : Menor o igual que
# - `==` : Igual a
# - `!=` : Distinto de
# - `or` : Se usa cuando una de las condiciones debe ser verdadera.
#         Ejemplo: `x < 50 or x > 80` (se cumple si `x` es menor a 50 o mayor a 80).
# - `and`: Se usa cuando ambas condiciones deben ser verdaderas.
#         Ejemplo: `x > 50 and x < 80` (se cumple si `x` está entre 50 y 80).

# ! PREGUNTA 1: (1 punto)
# * Evalúa el estado de un pelotón según su nivel de preparación:
# - Solicita al usuario un número entre 0 y 100.
# - Si la preparación es mayor o igual a 80, muestra "Pelotón listo".
# - Si está entre 50 y 79, muestra "Pelotón en entrenamiento".
# - Si es menor a 50, muestra "Pelotón no apto".

# Solución:
preparacion = int(input("Introduce el nivel de preparación del pelotón (0-100): "))

# Verifica si la preparación es mayor o igual a 80.
if preparacion >= 80:
    print("Pelotón listo")
# Verifica si la preparación está entre 50 y 79.
elif 50 <= preparacion < 80:
    print("Pelotón en entrenamiento")
# Si la preparación es menor a 50.
else:
    print("Pelotón no apto")

# ! PREGUNTA 2: (1 punto)
# * Evalúa si un vehículo militar necesita combustible:
# - Solicita al usuario el nivel de combustible (entre 0 y 100).
# - Si es menor a 30, muestra "Reabastecimiento urgente".
# - Si es 30 o mayor, muestra "Suficiente combustible".

# Solución:
combustible = int(input("Introduce el nivel de combustible del vehículo (0-100): "))

# Verifica si el nivel de combustible es menor a 30.
if combustible < 30:
    print("Reabastecimiento urgente")
# Si el combustible es 30 o mayor.
else:
    print("Suficiente combustible")

# ! PREGUNTA 3: (1 punto)
# * Determina la prioridad de una misión según el tiempo restante:
# - Solicita al usuario el tiempo de inicio (en minutos).
# - Si es menor a 15, muestra "Misión urgente".
# - Si está entre 15 y 60, muestra "Misión prioritaria".
# - Si es mayor a 60, muestra "Tiempo suficiente".

# Solución:
tiempo_restante = int(input("Introduce el tiempo restante para la misión (en minutos): "))

# Verifica si el tiempo es menor a 15.
if tiempo_restante < 15:
    print("Misión urgente")
# Verifica si el tiempo está entre 15 y 60.
elif 15 <= tiempo_restante <= 60:
    print("Misión prioritaria")
# Si el tiempo es mayor a 60.
else:
    print("Tiempo suficiente")

# -------------------------------------------------------------------------------------------
# SECCIÓN 2: BUCLES (2 PUNTOS)
# -------------------------------------------------------------------------------------------

# ! PREGUNTA 1: (1 punto)
# * Crea un programa que solicite al usuario ingresar los nombres de 3 soldados asignados a una misión:
# - Guarda los nombres en una lista y muestra los nombres ingresados.

# Solución:
soldados = []  # Lista para almacenar los nombres de los soldados.

# Solicita 3 nombres al usuario.
for i in range(3):
    nombre = input(f"Introduce el nombre del soldado {i+1}: ")
    soldados.append(nombre)  # Agrega cada nombre a la lista.

# Muestra la lista de soldados ingresados.
print("Soldados asignados:", soldados)

# ! PREGUNTA 2: (1 punto)
# * Recorre una lista de equipos ["Dron", "Radar", "GPS", "Radio"]:
# - Pregunta si cada equipo está operativo (sí/no).
# - Si está operativo, agrégalo a una nueva lista llamada `equipos_operativos`.
# - Muestra la lista de equipos operativos al final.

# Solución:
equipos = ["Dron", "Radar", "GPS", "Radio"]  # Lista de equipos.
equipos_operativos = []  # Lista para almacenar equipos operativos.

# Recorre cada equipo y pregunta si está operativo.
for equipo in equipos:
    estado = input(f"¿El equipo {equipo} está operativo? (sí/no): ").strip().lower()
    if estado == "sí":
        equipos_operativos.append(equipo)  # Agrega a la lista si está operativo.

# Muestra los equipos operativos.
print("Equipos operativos:", equipos_operativos)

# -------------------------------------------------------------------------------------------
# SECCIÓN 3: FUNCIONES (5 PUNTOS)
# -------------------------------------------------------------------------------------------

# ! PREGUNTA 1: (2 puntos)
# * Define una función llamada `sumar_valores` que reciba tres parámetros:
#   - `a`: El primer valor numérico.
#   - `b`: El segundo valor numérico.
#   - `c`: El tercer valor numérico.
# 
# La función debe:
# - Sumar los tres valores.
# - Si el resultado es mayor o igual a 150, devolver "Suma alta".
# - Si está entre 100 y 149, devolver "Suma moderada".
# - Si es menor a 100, devolver "Suma baja".

# Solución:
def sumar_valores(a, b, c):
    """
    Suma tres valores y evalúa el resultado.
    :param a: Primer número.
    :param b: Segundo número.
    :param c: Tercer número.
    :return: Mensaje indicando el nivel de la suma.
    """
    suma = a + b + c  # Calcula la suma de los tres valores.
    
    # Evalúa el resultado de la suma.
    if suma >= 150:
        return "Suma alta"
    elif 100 <= suma < 150:
        return "Suma moderada"
    else:
        return "Suma baja"

# Llamada a la función con valores de ejemplo.
print(sumar_valores(50, 40, 60))

# ! PREGUNTA 2: (3 puntos)
# * Define una función llamada `filtrar_pelotones` que reciba una lista de niveles de preparación:
# - Evalúa cada nivel (0-100) usando:
#   - Si es mayor o igual a 80, agrégalo a una lista llamada `pelotones_listos`.
#   - Devuelve la lista de pelotones listos.

# Solución:
def filtrar_pelotones(preparaciones):
    """
    Filtra los pelotones que están listos según su preparación.
    :param preparaciones: Lista de niveles de preparación (0-100).
    :return: Lista de pelotones listos.
    """
    pelotones_listos = []  # Lista para almacenar pelotones listos.

    # Recorre cada nivel de preparación.
    for nivel in preparaciones:
        if nivel >= 80:  # Verifica si el nivel es suficiente.
            pelotones_listos.append(nivel)  # Agrega a la lista de listos.

    return pelotones_listos  # Devuelve la lista de pelotones listos.

# Llamada a la función con valores de ejemplo.
print(filtrar_pelotones([90, 60, 85, 40]))
