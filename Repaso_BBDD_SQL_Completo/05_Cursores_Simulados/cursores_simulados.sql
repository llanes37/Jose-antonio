-- #############################################
-- 🔁 BLOQUE 05: CURSORES SIMULADOS
-- #############################################

-- ! En MySQL y otros motores se usan cursores dentro de procedimientos para procesar fila a fila.
-- ! En SQLite simulamos cursores con consultas ordenadas y procesamiento externo (Java, Python).
-- ! A continuación ejemplos y un procedimiento de MySQL con un cursor real.

-- -----------------------------------------------------
-- 1️⃣ SELECT ORDENADO COMO CURSOR SIMULADO
-- -----------------------------------------------------
-- * Obtenemos empleados ordenados por salario desc.
SELECT id, nombre, salario
FROM empleados
ORDER BY salario DESC;
-- * Simula recorrer un cursor: el primer registro es el de mayor salario.

-- -----------------------------------------------------
-- 2️⃣ FILTRADO DENTRO DE "CURSOR"
-- -----------------------------------------------------
-- * Obtener solo los 3 primeros empleados (TOP-N).
SELECT id, nombre, salario
FROM empleados
ORDER BY salario DESC
LIMIT 3;
-- * Simula FETCH FIRST 3 ROWS del cursor.

-- -----------------------------------------------------
-- 3️⃣ PROCESAMIENTO CON CONDICIONES
-- -----------------------------------------------------
-- * Mostrar un mensaje de alerta para salarios bajos.
SELECT id, nombre,
  CASE
    WHEN salario < 1000 THEN CONCAT('❗ ', nombre, ' tiene salario bajo (', salario, ')')
    ELSE CONCAT('✅ ', nombre, ' - OK (', salario, ')')
  END AS estado_salario
FROM empleados
ORDER BY salario ASC;
-- * Simula lógica interna: por cada fila, evaluamos condición.

-- -----------------------------------------------------
-- 4️⃣ CURSOR REAL EN PROCEDIMIENTO (MySQL)
-- -----------------------------------------------------
DELIMITER $$
CREATE PROCEDURE ProcesarEmpleados()
BEGIN
  DECLARE fin INT DEFAULT FALSE;
  DECLARE emp_id INT;
  DECLARE emp_nom VARCHAR(100);
  DECLARE emp_sal DECIMAL(10,2);

  -- Cursor para recorrer empleados
  DECLARE cursor_emp CURSOR FOR
    SELECT id, nombre, salario FROM empleados ORDER BY id;

  -- Handler para fin de datos
  DECLARE CONTINUE HANDLER FOR NOT FOUND SET fin = TRUE;

  OPEN cursor_emp;
  bucle: LOOP
    FETCH cursor_emp INTO emp_id, emp_nom, emp_sal;
    IF fin THEN
      LEAVE bucle;
    END IF;
    -- Ejemplo de procesamiento: aumentar salario temporalmente
    UPDATE empleados
      SET salario = emp_sal * 1.05
    WHERE id = emp_id;
  END LOOP;
  CLOSE cursor_emp;
END $$
DELIMITER ;
-- ✅ Ejecuta con: CALL ProcesarEmpleados();

-- -----------------------------------------------------
-- 5️⃣ VERIFICACIÓN POST-PROCESO
-- -----------------------------------------------------
-- * Verifica que todos los salarios se hayan incrementado un 5%.
SELECT id, nombre, salario
FROM empleados
ORDER BY id;
-- * Simula el resultado final tras procesar todas las filas.
