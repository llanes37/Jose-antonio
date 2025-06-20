
# Configuración de Python REPL y Code Runner en Visual Studio Code

Este archivo explica cómo configurar el **Python REPL** y el **Code Runner** en Visual Studio Code para ejecutar código Python de forma interactiva y eficiente.

---

## 1. Configuración del Python REPL

El **Python REPL** (Read-Eval-Print Loop) es una consola interactiva que permite ejecutar fragmentos de código Python línea por línea. Sigue estos pasos para configurarlo:

### Paso 1: Instalar Python
1. Descarga e instala Python desde [python.org](https://www.python.org/downloads/).
2. Durante la instalación, marca la opción **"Add Python to PATH"** para que Python sea reconocido por tu sistema.

### Paso 2: Instalar Visual Studio Code
1. Descarga VS Code desde [code.visualstudio.com](https://code.visualstudio.com/).
2. Instálalo en tu ordenador.

### Paso 3: Instalar la extensión oficial de Python
1. Abre VS Code.
2. Ve a la pestaña de extensiones (`Ctrl + Shift + X`).
3. Busca **"Python"** (desarrollada por Microsoft) e instálala.

### Paso 4: Configurar el intérprete de Python
1. Presiona `Ctrl + Shift + P` para abrir la paleta de comandos.
2. Escribe **"Python: Select Interpreter"** y selecciona esta opción.
3. Elige el intérprete de Python instalado (por ejemplo: `Python 3.x.x`).

### Paso 5: Usar el Python REPL
1. Abre una terminal integrada en VS Code (`Ctrl + ñ` o desde `Terminal > Nuevo Terminal`).
2. Escribe el comando `python` y presiona **Enter**.
3. Ahora puedes escribir código directamente en el REPL, por ejemplo:
   ```python
   >>> print("Hola, mundo")
   Hola, mundo
   >>> 2 + 2
   4
   ```

---

## 2. Configuración de Code Runner para que funcione en el terminal

El **Code Runner** es una extensión que permite ejecutar archivos de Python con un clic. Por defecto, usa una salida que no es interactiva, pero puedes configurarlo para usar la terminal integrada.

### Paso 1: Instalar Code Runner
1. Abre VS Code.
2. Ve a la pestaña de extensiones (`Ctrl + Shift + X`).
3. Busca **"Code Runner"** (desarrollada por Jun Han) e instálala.

### Paso 2: Configurar Code Runner para usar la terminal
1. Abre las configuraciones de VS Code:
   - Ve a `Archivo > Preferencias > Configuración` o presiona `Ctrl + ,`.
2. Busca **"Code Runner: Run in Terminal"**.
3. Activa la opción **"Run in Terminal"** marcando la casilla.

   **Configuración manual (opcional):**
   Si prefieres editar el archivo de configuración directamente:
   1. Haz clic en el ícono del archivo JSON en la esquina superior derecha de las configuraciones.
   2. Agrega lo siguiente:
      ```json
      "code-runner.runInTerminal": true
      ```

### Paso 3: Ejecutar un archivo Python
1. Abre un archivo `.py` en VS Code.
2. Presiona `Ctrl + Alt + N` o haz clic en el botón **Run Code** (ícono de triángulo).
3. El archivo se ejecutará en la terminal integrada y aceptará entradas (`input()`).

---

## 3. Verificación de Configuración

### Verificar Python REPL
1. Abre la terminal (`Ctrl + ñ`).
2. Escribe `python` y verifica que aparece el prompt interactivo (`>>>`).

### Verificar Code Runner
1. Abre un archivo `.py` con código que use `input()`, por ejemplo:
   ```python
   nombre = input("¿Cómo te llamas? ")
   print(f"Hola, {nombre}")
   ```
2. Ejecuta el archivo con Code Runner (`Ctrl + Alt + N`).
3. Verifica que puedas ingresar datos en la terminal.

---

## 4. Resolución de Problemas Comunes

### Problema: El REPL no abre
- Verifica que Python esté instalado y en el **PATH**.
- Abre una terminal externa y escribe `python --version` para confirmar que Python funciona.

### Problema: Code Runner no permite `input()`
- Asegúrate de haber habilitado **"Run in Terminal"** en la configuración de Code Runner.

---

Con esta configuración, podrás usar Python REPL y Code Runner en VS Code para ejecutar código de manera interactiva y eficiente.
