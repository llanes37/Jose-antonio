-- #############################################
-- ⚙️ BLOQUE 04: PROCEDIMIENTOS SIMULADOS
-- #############################################

-- ! Este script muestra tanto la simulación de procedimientos usando consultas
-- ! como la creación de procedimientos almacenados en MySQL.
-- * Ejecuta cada bloque por separado en MySQL Workbench.

-- -----------------------------------------------------
-- 1️⃣ Simulación con CASE (procedimiento implícito)
-- -----------------------------------------------------
-- * Calcula el nivel salarial según el salario de cada empleado.
SELECT nombre,
       CASE
         WHEN salario < 1000 THEN 'Bajo'
         WHEN salario BETWEEN 1000 AND 2000 THEN 'Medio'
         ELSE 'Alto'
       END AS nivel_salario
FROM empleados;
-- ✅ Útil para entender lógica condicional como si fuera un procedimiento.

-- -----------------------------------------------------
-- 2️⃣ Procedimiento Almacenado: ObtenerNivelSalario
-- -----------------------------------------------------
DELIMITER $$
CREATE PROCEDURE ObtenerNivelSalario()
BEGIN
  -- Muestra nombre y nivel salarial
  SELECT nombre,
         CASE
           WHEN salario < 1000 THEN 'Bajo'
           WHEN salario BETWEEN 1000 AND 2000 THEN 'Medio'
           ELSE 'Alto'
         END AS nivel_salario
  FROM empleados;
END $$
DELIMITER ;
-- ✅ Ejecuta con: CALL ObtenerNivelSalario();

-- -----------------------------------------------------
-- 3️⃣ Procedimiento con Parámetro: BuscarEmpleado
-- -----------------------------------------------------
DELIMITER $$
CREATE PROCEDURE BuscarEmpleado(IN empNombre VARCHAR(100))
BEGIN
  -- Busca empleados cuyo nombre contenga el parámetro
  SELECT id, nombre, salario
  FROM empleados
  WHERE nombre LIKE CONCAT('%', empNombre, '%');
END $$
DELIMITER ;
-- ✅ Ejecuta con: CALL BuscarEmpleado('Ana');

-- -----------------------------------------------------
-- 4️⃣ Procedimiento de Inserción: AgregarCliente
-- -----------------------------------------------------
DELIMITER $$
CREATE PROCEDURE AgregarCliente(IN cliNombre VARCHAR(100), IN cliEmail VARCHAR(100))
BEGIN
  -- Inserta un cliente nuevo; email debe ser único
  INSERT INTO clientes (nombre, email)
  VALUES (cliNombre, cliEmail);
END $$
DELIMITER ;
-- ✅ Ejecuta con: CALL AgregarCliente('Prueba', 'prueba@example.com');

-- -----------------------------------------------------
-- 5️⃣ Eliminación Condicional: EliminarEmpleadoPorSalario
-- -----------------------------------------------------
DELIMITER $$
CREATE PROCEDURE EliminarEmpleadoPorSalario(IN minSalario DECIMAL(10,2))
BEGIN
  -- Elimina empleados con salario menor al parámetro
  DELETE FROM empleados
  WHERE salario < minSalario;
END $$
DELIMITER ;
-- ✅ Ejecuta con: CALL EliminarEmpleadoPorSalario(1000.00);

-- -----------------------------------------------------
-- 6️⃣ Limpieza de Procedimientos (opcional)
-- -----------------------------------------------------
-- DROP PROCEDURE IF EXISTS ObtenerNivelSalario;
-- DROP PROCEDURE IF EXISTS BuscarEmpleado;
-- DROP PROCEDURE IF EXISTS AgregarCliente;
-- DROP PROCEDURE IF EXISTS EliminarEmpleadoPorSalario;
