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

# TODO: Escribe tu código aquí.

# ! PREGUNTA 2: (1 punto)
# * Evalúa si un vehículo militar necesita combustible:
# - Solicita al usuario el nivel de combustible (entre 0 y 100).
# - Si es menor a 30, muestra "Reabastecimiento urgente".
# - Si es 30 o mayor, muestra "Suficiente combustible".

# TODO: Escribe tu código aquí.

# ! PREGUNTA 3: (1 punto)
# * Determina la prioridad de una misión según el tiempo restante:
# - Solicita al usuario el tiempo de inicio (en minutos).
# - Si es menor a 15, muestra "Misión urgente".
# - Si está entre 15 y 60, muestra "Misión prioritaria".
# - Si es mayor a 60, muestra "Tiempo suficiente".

# TODO: Escribe tu código aquí.

# -------------------------------------------------------------------------------------------
# SECCIÓN 2: BUCLES (2 PUNTOS)
# -------------------------------------------------------------------------------------------

# ! PREGUNTA 1: (1 punto)
# * Crea un programa que solicite al usuario ingresar los nombres de 3 soldados asignados a una misión:
# - Guarda los nombres en una lista y muestra los nombres ingresados.

# TODO: Escribe tu código aquí.

# ! PREGUNTA 2: (1 punto)
# * Recorre una lista de equipos ["Dron", "Radar", "GPS", "Radio"]:
# - Pregunta si cada equipo está operativo (sí/no).
# - Si está operativo, agrégalo a una nueva lista llamada `equipos_operativos`.
# - Muestra la lista de equipos operativos al final.

# TODO: Escribe tu código aquí.

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
# 
# TODO: Escribe la función y realiza una llamada con valores de ejemplo.

# ! PREGUNTA 2: (3 puntos)
# * Define una función llamada `filtrar_pelotones` que reciba una lista de niveles de preparación:
# - Evalúa cada nivel (0-100) usando:
#   - Si es mayor o igual a 80, agrégalo a una lista llamada `pelotones_listos`.
#   - Devuelve la lista de pelotones listos.

# AYUDA:
# 1. Crea una lista vacía llamada `pelotones_listos`. Aquí guardarás los niveles de preparación
#    que cumplan con la condición de estar "listos".
# 2. Usa un bucle para recorrer cada número de la lista de niveles de preparación.
#    Esto te permitirá evaluar cada nivel individualmente.
# 3. Para cada nivel, verifica si es mayor o igual a 80.
#    - Si el nivel cumple esta condición, agrégalo a la lista `pelotones_listos`.
# 4. Una vez que termines de recorrer todos los niveles,
#    devuelve la lista `pelotones_listos`.
# 5. Ejemplo:
#    - Si tienes la lista de niveles [90, 60, 85, 40], los valores mayores o iguales a 80
#      son [90, 85], que serían los pelotones listos.

# TODO: Escribe tu código aquí.
