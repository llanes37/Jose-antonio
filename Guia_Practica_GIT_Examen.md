# 🔪 Práctica Guiada de Comandos GIT – Simulación de Examen

Este documento te guiará paso a paso para practicar **todos los comandos esenciales de Git** tal y como se piden en los exámenes. Usa **Git Bash** (o terminal con Git) y sigue cada paso. Comprueba el resultado esperado tras ejecutar cada comando.

---

## 🟢 1. Crear un Repositorio Local

Un repositorio local es donde se va a controlar la evolución de tus archivos desde tu propio equipo.

```bash
mkdir examen_git
cd examen_git
git init
```

🔍 **Resultado esperado:**

```
Initialized empty Git repository in ...
```

✅ Ahora estás dentro de un repositorio vacío y controlado por Git.

---

## 👤 2. Configurar Git con tu nombre y correo

Esto es obligatorio la primera vez. Sirve para identificar quién hace cada commit.

```bash
git config --global user.name "Tu Nombre"
git config --global user.email "tucorreo@ejemplo.com"
```

🔍 **Comprobación (opcional):**

```bash
git config --list
```

✅ Debes ver tu nombre y correo en la lista. Se guardan en tu sistema para todos los repositorios.

---

## 📁 3. Crear archivos y añadirlos al staging area

Crea archivos y prepáralos para ser incluidos en el próximo commit. El “staging area” es como una caja temporal de preparación.

```bash
echo "Hola mundo" > archivo1.txt
git status         # muestra que hay archivos sin seguimiento
git add archivo1.txt
```

🔍 **Resultado de `git status`:**

```
new file:   archivo1.txt
```

✅ El archivo ahora está preparado para ser registrado por Git.

---

## 📝 4. Hacer un commit

Un commit guarda una versión del proyecto. Piensa en él como una "foto" del estado de los archivos en ese momento. Siempre debe llevar un mensaje descriptivo.

```bash
git commit -m "Primer commit"
```

🔍 **Resultado esperado:**

```
[main (root-commit) abc1234] Primer commit
```

🧠 **¿Qué hace este comando?**

* Guarda lo que está en el *staging area* como un nuevo punto de control en la historia del repositorio.
* El `-m` sirve para añadir un mensaje explicando qué hemos hecho.

---

## 🔄 5. Modificar y restaurar un archivo

Después de hacer un commit, puedes cambiar un archivo. Si quieres **deshacer el cambio** y volver al último commit:

```bash
echo "Nueva línea" >> archivo1.txt
git restore archivo1.txt
```

✅ El archivo vuelve a como estaba después del último commit.

---

## 🔁 6. Ver diferencias con `git diff`

Este comando te permite ver qué líneas han cambiado en los archivos **modificados pero no añadidos** al staging area.

```bash
echo "otro cambio" >> archivo1.txt
git diff
```

🔍 **Verás las diferencias en rojo/verde.**

---

## 📋 7. Listar commits

Para consultar el historial de cambios:

```bash
git log --oneline
```

🔍 **Resultado esperado:**

```
abc1234 Primer commit
```

🧠 Puedes usar otras variantes como:

```bash
git log        # vista detallada
git log -p     # con diferencias
git log --pretty=oneline
```

---

## 🌿 8. Crear y cambiar de rama

Las ramas sirven para trabajar en versiones paralelas del código, sin afectar la rama principal.

```bash
git branch nueva-rama
git checkout nueva-rama
```

🔍 **Verifica con:**

```bash
git branch
```

✅ Debería aparecer:

```
* nueva-rama
  main
```

---

## 🔀 9. Fusionar ramas

Sirve para combinar los cambios de otra rama (por ejemplo, `nueva-rama`) con la rama actual (por ejemplo, `main`):

```bash
git checkout main
git merge nueva-rama
```

✅ Git fusiona automáticamente si no hay conflictos.

---

## 🧹 10. Borrar una rama

Después de fusionar una rama, podemos eliminarla si ya no se necesita:

```bash
git branch -d nueva-rama
```

---

## ☁️ 11. Conectar con un repositorio remoto

Un repositorio remoto (como GitHub) te permite guardar tu trabajo en la nube y compartirlo.

### 1. Añadir conexión remota:

```bash
git remote add origin https://github.com/tuusuario/tu-repo.git
```

### 2. Subir tus commits al repositorio remoto:

```bash
git push -u origin main
```

🔐 Git te pedirá tu usuario y token de GitHub.

---

## ❌ 12. Eliminar el repositorio (opcional)

Si quieres **dejar de usar Git en esa carpeta**, elimina la carpeta oculta `.git`:

```bash
rm -rf .git
```

⚠️ Esto no borra tus archivos, solo deja de ser un repositorio Git.

---

## ✅ Consejos Finales

* Usa `git status` frecuentemente para no perderte.
* Escribe mensajes de commit claros y explicativos.
* No tengas miedo de equivocarte: Git te permite volver atrás.
* Practica todo esto en un entorno local antes del examen.
