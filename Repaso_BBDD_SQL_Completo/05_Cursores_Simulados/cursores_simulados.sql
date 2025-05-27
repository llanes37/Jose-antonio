-- #############################################################
-- 🔁 BLOQUE 05: CURSORES SIMULADOS — TEORÍA, CÓDIGO Y EJERCICIOS
-- #############################################################

/*
📘 TEORÍA COMPLETA DE CURSORES EN SQL

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
1. ¿QUÉ ES UN CURSOR?
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
Un **cursor** es una estructura que permite **recorrer los resultados de una consulta fila a fila**, como si fuese un bucle sobre un conjunto de datos.

Se usa cuando necesitas aplicar **lógica individual** sobre cada registro:
- Validar datos uno a uno
- Aplicar actualizaciones condicionales
- Acumular cálculos en variables
- Generar auditorías, informes o estadísticas complejas

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
2. CURSORES SIMULADOS (SQL “PLANO” y SQLite)
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
En sistemas como SQLite (y también en MySQL, si no se usa procedural), simulamos el recorrido fila a fila con consultas como:

✅ SELECT con `ORDER BY`: simula cómo leería el cursor
✅ `LIMIT`, `OFFSET`: simulan el control de lectura paso a paso
✅ `CASE`: permite aplicar lógica fila a fila (como IF/ELSE)

📌 Se complementa en código externo:  
– Java: `while (rs.next()) { ... }`  
– Python: `for row in cursor: ...`

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
3. CURSORES REALES EN MYSQL (PROGRAMACIÓN PROCEDURAL)
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
MySQL sí permite cursores reales, dentro de procedimientos almacenados.

📐 Estructura básica:

```sql
DECLARE cursor_nombre CURSOR FOR
    SELECT ... FROM ...;

DECLARE CONTINUE HANDLER FOR NOT FOUND
    SET variable_fin = TRUE;

OPEN cursor_nombre;
REPEAT
    FETCH cursor_nombre INTO variable1, variable2;
    -- lógica por fila aquí
UNTIL variable_fin END REPEAT;
CLOSE cursor_nombre;


-- -------------------------------------------------------------
-- 1️⃣ SELECT ORDENADO COMO CURSOR SIMULADO
-- -------------------------------------------------------------
-- * Obtenemos empleados ordenados por salario descendente.
SELECT id, nombre, salario
FROM empleados
ORDER BY salario DESC;

-- 🔽 EN LA APLICACIÓN (MySQL Workbench):
-- 1. Selecciona solo esta consulta (líneas 2 a 4).
-- 2. Pulsa Ctrl + Enter para ejecutarla y ver los resultados en la tabla inferior.
-- 3. Observa cómo aparecen ordenados de mayor a menor salario.
-- 4. Explica que esto simula la lectura "fila a fila" que haría un cursor:
--    - La primera fila sería la que leería primero el cursor.
-- 5. Puedes repetirlo luego con ORDER BY ASC para mostrar lo contrario.


-- -------------------------------------------------------------
-- 2️⃣ TOP-N (LIMIT) COMO FETCH MÚLTIPLE
-- -------------------------------------------------------------
-- * Recuperar solo los 3 empleados mejor pagados.
SELECT id, nombre, salario
FROM empleados
ORDER BY salario DESC
LIMIT 3;
-- ✅ Simula FETCH FIRST 3 ROWS del cursor.

-- -------------------------------------------------------------
-- 3️⃣ PROCESAMIENTO CON CONDICIONES POR FILA
-- -------------------------------------------------------------
-- * Mostrar estado salarial con mensajes personalizados.
SELECT id,
       nombre,
       CASE
         WHEN salario < 1000 THEN CONCAT('❗ ', nombre, ' tiene salario bajo (', salario, ')')
         ELSE CONCAT('✅ ', nombre, ' - OK (', salario, ')')
       END AS estado_salario
FROM empleados
ORDER BY salario ASC;
-- ✅ Simula FETCH + lógica IF/ELSE interna en cada iteración.

-- -------------------------------------------------------------
-- 4️⃣ CURSOR REAL DENTRO DE PROCEDIMIENTO (MySQL)
-- -------------------------------------------------------------
DELIMITER $$
CREATE PROCEDURE ProcesarEmpleados()
BEGIN
  DECLARE fin INT DEFAULT FALSE;
  DECLARE emp_id INT;
  DECLARE emp_nom VARCHAR(100);
  DECLARE emp_sal DECIMAL(10,2);

  -- Cursor para recorrer todos los empleados
  DECLARE cursor_emp CURSOR FOR
    SELECT id, nombre, salario FROM empleados ORDER BY id;

  -- Handler para detectar fin de datos
  DECLARE CONTINUE HANDLER FOR NOT FOUND SET fin = TRUE;

  OPEN cursor_emp;
  bucle: LOOP
    FETCH cursor_emp INTO emp_id, emp_nom, emp_sal;
    IF fin THEN
      LEAVE bucle;
    END IF;
    -- Ejemplo de procesamiento: aumentar salario un 5%
    UPDATE empleados
    SET salario = emp_sal * 1.05
    WHERE id = emp_id;
  END LOOP;
  CLOSE cursor_emp;
END $$
DELIMITER ;
-- ✅ Ejecuta con: CALL ProcesarEmpleados();

-- -------------------------------------------------------------
-- 5️⃣ VERIFICACIÓN POST-PROCESO
-- -------------------------------------------------------------
-- * Comprobar que todos los salarios se incrementaron correctamente.
SELECT id, nombre, salario
FROM empleados
ORDER BY id;

-- #############################################################
-- 🧪 EJERCICIOS PROPUESTOS
-- #############################################################

-- 1️⃣ Ajuste inverso:
--    • Modifica el procedimiento ProcesarEmpleados para disminuir
--      en vez de aumentar un 5% los salarios de los empleados.
--    • Ejecuta y verifica con SELECT.

-- 2️⃣ Cursor para clientes:
--    • Crea un procedimiento ProcesarClientes que recorra
--      SELECT id, nombre, email FROM clientes.
--    • Dentro del bucle, actualiza un campo ficticio o registra en log.

-- 3️⃣ Simulación externa:
--    • Escribe un pseudocódigo Java:
--        while(rs.next()) {
--          // imprime id y nombre
--        }
--      para el SELECT ORDER BY salario.

-- 4️⃣ Cursor anidado:
--    • Dentro de un procedimiento, usa dos cursores:
--        – Uno sobre departamentos: SELECT id FROM departamentos;
--        – Dentro del bucle, otro SELECT COUNT(*) FROM empleados WHERE departamento_id = depto_id;
--      • Actualiza una tabla estadística con esos conteos.

-- 5️⃣ Tarea avanzada:
--    • Usa un handler para SIGNAL SQLSTATE y envía un error
--      si en algún FETCH el salario es NULO o negativo.
