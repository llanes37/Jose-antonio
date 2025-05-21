# 🧪 Práctica Completa y Teórica de Comandos GIT – Revisión para el Examen

Este documento está diseñado como **simulación de examen**, pero también como **guía teórica y práctica completa** de todos los comandos importantes de Git. Está mejorado respecto a los errores comunes que se detectaron en exámenes anteriores.

Incluye:

* ✔️ Comandos completos
* ✔️ Explicaciones claras
* ✔️ Teoría de uso
* ✔️ Ejemplos visuales

---

## 🟢 1. Crear un Repositorio Local

Un repositorio local es donde Git guardará toda la evolución de nuestros archivos. Es el primer paso de cualquier proyecto versionado.

```bash
mkdir examen_git
cd examen_git
git init
```

🔍 **¿Qué hace cada comando?**

* `mkdir` crea la carpeta
* `cd` entra en la carpeta
* `git init` convierte esa carpeta en un repositorio Git local

✅ Ahora estás en un repositorio controlado por Git (hay una carpeta oculta `.git`).

---

## 👤 2. Configurar Git con tu nombre y correo

Esto es obligatorio. Git usará estos datos para firmar los commits.

```bash
git config --global user.name "Tu Nombre"
git config --global user.email "tucorreo@ejemplo.com"
```

🔍 **Opcional: comprobar configuración**

```bash
git config --list
```

✅ Esto se guarda en tu perfil de usuario del sistema, no solo para un repositorio.

---

## 🧱 3. Renombrar la rama principal de 'master' a 'main'

**MUY IMPORTANTE** en el examen. Si `git init` genera `master`, renómbrala:

```bash
git branch -m main
```

🔍 Esto cambia el nombre de la rama actual a `main`, como exige GitHub por defecto.

✅ Usa `git branch` para confirmar que estás en `main`.

---

## 📁 4. Crear archivos y añadirlos al staging area (git add)

```bash
echo "Hola mundo" > archivo1.txt
git status         # Verifica que Git ve el archivo

git add archivo1.txt
```

🧠 `git add` es **obligatorio** antes de hacer un `commit`. Pone el archivo en "espera" para ser guardado.

---

## 📝 5. Hacer un commit

```bash
git commit -m "Primer commit"
```

🧠 El commit **guarda una versión** de todos los archivos que estaban en staging. Es una unidad de trabajo completa.

✅ Revisa con `git log --oneline`

---

## 🔄 6. Modificar un archivo y restaurar cambios

```bash
echo "nueva línea" >> archivo1.txt
git restore archivo1.txt
```

🧠 `restore` borra los cambios hechos **desde el último commit**.

---

## 🔁 7. Ver diferencias

### a) Cambios **NO añadidos** (working directory):

```bash
git diff
```

### b) Cambios **ya añadidos** (staged):

```bash
git diff --staged
```

✅ ¡Este comando es obligatorio si te preguntan por diferencias preparadas para commit!

---

## 📋 8. Ver historial de commits

```bash
git log --oneline
```

🧠 Muestra todos los commits con su hash y descripción.

---

## 🌿 9. Crear y cambiar de rama (bien hecho)

```bash
git checkout -b nueva-rama
```

✅ Esto crea y te mueve a la rama nueva. Muy usado en flujos de trabajo profesional.

---

## 🔀 10. Fusionar ramas

```bash
git checkout main
git merge nueva-rama
```

✅ Esto trae todos los cambios de la otra rama a `main`. Si hay conflicto, Git lo mostrará.

---

## 🧹 11. Borrar una rama

```bash
git branch -d nueva-rama
```

🧠 Solo puede borrarse si ya ha sido fusionada. Usa `git branch --merged` para confirmarlo.

---

## ☁️ 12. Conectar y subir a repositorio remoto

```bash
git remote add origin https://github.com/tuusuario/tu-repo.git
git push -u origin main
```

🔍 `origin` es el nombre del servidor remoto, `main` es tu rama local.

✅ Con esto puedes trabajar desde GitHub y Git local.

---

## ❌ 13. Eliminar repositorio Git

```bash
rm -rf .git
```

⚠️ Cuidado: borra **toda la información de versiones**, pero no los archivos.

---

## ✅ Recomendaciones Finales

* Usa `git status` a cada paso.
* Haz commits **descriptivos y frecuentes**.
* Renombra `master` a `main` desde el principio.
* Practica diferencias entre `git diff` y `git diff --staged`.
* Aprende a leer bien `git log`, y usa ramas como práctica real.

---

Este documento está preparado para ayudarte a **no fallar los errores más comunes del examen**, incluyendo los comentarios vistos en tu corrección. Puedes añadir capturas de tus resultados para tener un feedback completo.
