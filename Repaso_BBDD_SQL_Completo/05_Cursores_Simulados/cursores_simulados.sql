-- #############################################################
-- 🔁 BLOQUE 05: CURSORES SIMULADOS — TEORÍA, CÓDIGO Y EJERCICIOS
-- #############################################################

/*
📘 TEORÍA DE CURSORES

1. ¿Qué es un cursor?
   - Mecanismo que permite procesar fila a fila los resultados de una consulta.
   - Se usa cuando necesitas lógica por registro (actualizaciones, validaciones…).

2. Simulación en SQLite / SQL puro:
   - No hay cursores nativos: usamos SELECT con ORDER BY, LIMIT y CASE.
   - El procesamiento fila a fila se realiza externamente (Java: while(rs.next()), Python: for row in cursor).

3. Cursores reales en MySQL:
   - Definidos dentro de procedimientos con DECLARE CURSOR, OPEN, FETCH y handlers.
   - Permiten lógica imperativa y bucles controlados (LOOP).

4. ¿Cuándo usar cursores?
   - Cálculos complejos por registro.
   - Transformaciones paso a paso.
   - Integridad de datos en procesos ETL pequeños.
*/

-- -------------------------------------------------------------
-- 1️⃣ SELECT ORDENADO COMO CURSOR SIMULADO
-- -------------------------------------------------------------
-- * Obtenemos empleados ordenados por salario descendente.
SELECT id, nombre, salario
FROM empleados
ORDER BY salario DESC;
-- ✅ Simula OPEN + FETCH inicial de un cursor: el primer registro es el de mayor salario.

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
