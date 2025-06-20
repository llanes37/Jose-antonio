# -------------------------------------------------------------------------------------------
# Programación Funcional en Python
# -------------------------------------------------------------------------------------------
# La programación funcional es un paradigma de programación que trata de construir el software 
# mediante funciones puras, sin estado y sin efectos secundarios. Python, aunque es un lenguaje 
# orientado a objetos, admite la programación funcional a través de funciones de orden superior, 
# funciones lambda, map, filter, reduce, entre otras características.
# -------------------------------------------------------------------------------------------


# -------------------------------------------------------------------------------------------
# * SECCIÓN 1: FUNCIONES PURAS
# ? Una función pura es aquella que siempre devuelve el mismo resultado para los mismos argumentos.
# ? No produce efectos secundarios (no cambia variables fuera de su entorno).
# -------------------------------------------------------------------------------------------

# Ejemplo de función pura:
def suma(a, b):
    return a + b

# Explicación:
# - `suma()` es una función pura porque para los mismos valores de `a` y `b`, siempre devolverá
#   el mismo resultado, y no modifica ninguna variable externa.
# -------------------------------------------------------------------------------------------


# -------------------------------------------------------------------------------------------
# * SECCIÓN 2: FUNCIONES ANÓNIMAS (LAMBDA)
# ? Las funciones lambda son funciones anónimas de una sola línea que se definen usando la palabra 
# ? clave `lambda`. Son útiles cuando necesitamos una función rápida que se utilice solo una vez.
# -------------------------------------------------------------------------------------------

# Ejemplo de función lambda para multiplicar dos números:
multiplicacion = lambda x, y: x * y

# Explicación:
# - Las lambdas son útiles para definir funciones de forma concisa. La función `multiplicacion` 
#   se puede usar de la misma manera que una función normal, pero su sintaxis es más compacta.
# -------------------------------------------------------------------------------------------


# -------------------------------------------------------------------------------------------
# * SECCIÓN 3: FUNCIONES DE ORDEN SUPERIOR
# ? Una función de orden superior es aquella que puede tomar otras funciones como argumentos o 
# ? devolver funciones como resultado.
# -------------------------------------------------------------------------------------------

# Ejemplo de función de orden superior:
def aplicar_funcion(func, valor):
    return func(valor)

# Definir una función para elevar al cuadrado:
def cuadrado(n):
    return n * n

# Usar la función de orden superior:
resultado = aplicar_funcion(cuadrado, 5)  # Resultado: 25

# Explicación:
# - `aplicar_funcion` toma una función (`func`) como argumento y la aplica al valor proporcionado.
# - Este es un principio clave de la programación funcional, donde las funciones se tratan como objetos.
# -------------------------------------------------------------------------------------------


# -------------------------------------------------------------------------------------------
# * SECCIÓN 4: USO DE `MAP`, `FILTER` Y `REDUCE`
# ? Estas funciones son fundamentales en la programación funcional, ya que permiten trabajar 
# ? con colecciones de datos de manera eficiente.
# -------------------------------------------------------------------------------------------

# * `MAP`: Aplica una función a todos los elementos de una lista y devuelve una nueva lista.
numeros = [1, 2, 3, 4, 5]
cuadrados = list(map(lambda x: x**2, numeros))  # Resultado: [1, 4, 9, 16, 25]

# * `FILTER`: Filtra los elementos de una lista basándose en una función de filtro.
pares = list(filter(lambda x: x % 2 == 0, numeros))  # Resultado: [2, 4]

# * `REDUCE`: Acumula los elementos de una lista aplicando una función (necesita importar functools).
from functools import reduce
suma_total = reduce(lambda x, y: x + y, numeros)  # Resultado: 15

# Explicación:
# - `map` aplica una transformación a cada elemento de la lista, `filter` selecciona elementos según
#   una condición, y `reduce` combina los elementos acumulándolos en un solo valor.
# -------------------------------------------------------------------------------------------


# -------------------------------------------------------------------------------------------
# * SECCIÓN 5: INMUTABILIDAD
# ? La programación funcional se basa en la inmutabilidad, lo que significa que una vez que se crea
# ? una estructura de datos, no puede ser modificada. Cualquier operación que altere los datos 
# ? devuelve una nueva estructura en lugar de modificar la existente.
# -------------------------------------------------------------------------------------------

# Ejemplo de inmutabilidad con listas:
lista_original = [1, 2, 3, 4]

# Crear una nueva lista con todos los elementos incrementados:
lista_nueva = list(map(lambda x: x + 1, lista_original))

# Explicación:
# - En lugar de modificar `lista_original`, creamos una nueva lista `lista_nueva`. Este enfoque 
#   evita efectos secundarios y mantiene el estado de las variables consistente.
# -------------------------------------------------------------------------------------------


# -------------------------------------------------------------------------------------------
# * SECCIÓN 6: COMPOSICIÓN DE FUNCIONES
# ? La composición de funciones permite combinar funciones más pequeñas para formar una función
# ? más compleja. En la programación funcional, es común combinar varias funciones.
# -------------------------------------------------------------------------------------------

# Ejemplo de composición de funciones:
def triple(n):
    return n * 3

def sumar_dos(n):
    return n + 2

# Componer funciones:
def triple_y_sumar_dos(n):
    return sumar_dos(triple(n))

# Usar la función compuesta:
resultado = triple_y_sumar_dos(4)  # Resultado: 14

# Explicación:
# - Componer funciones pequeñas y reutilizables facilita la creación de programas más complejos 
#   sin duplicar código.
# -------------------------------------------------------------------------------------------


# -------------------------------------------------------------------------------------------
# * SECCIÓN 7: RECURSIÓN
# ? En la programación funcional, la recursión es una técnica muy utilizada en lugar de los bucles.
# ? Es una función que se llama a sí misma para resolver problemas divididos en subproblemas.
# -------------------------------------------------------------------------------------------

# Ejemplo de función recursiva: Factorial de un número
def factorial(n):
    if n == 0:
        return 1
    else:
        return n * factorial(n - 1)

# Explicación:
# - La recursión se usa para dividir el problema en subproblemas más pequeños hasta que se alcanza 
#   el caso base (n == 0 en este caso).
# -------------------------------------------------------------------------------------------


# -------------------------------------------------------------------------------------------
# * AUTOEVALUACIÓN FINAL:
# 1. Define una función pura que sume dos números y una función anónima (lambda) para multiplicarlos.
# 2. Usa `map` para convertir una lista de números en su versión al cuadrado.
# 3. Crea una función de orden superior que acepte una función de transformación y una lista, 
#    y aplique la transformación a cada elemento.
# 4. Implementa una función recursiva que calcule la suma de los números de una lista.
# 5. Utiliza `filter` para seleccionar los números pares de una lista y `reduce` para sumar todos los elementos.
# -------------------------------------------------------------------------------------------
