-- #############################################################
-- ⚙️ BLOQUE 04: PROCEDIMIENTOS SIMULADOS — TEORÍA, CÓDIGO Y EJERCICIOS
-- #############################################################

/*
📘 TEORÍA DE PROCEDIMIENTOS ALMACENADOS Y SIMULADOS

1. ¿Qué es un procedimiento almacenado?
   - Conjunto de sentencias SQL agrupadas bajo un nombre.
   - Permite encapsular lógica, reutilizar código y simplificar llamadas.
   - Sintaxis MySQL:
     CREATE PROCEDURE Nombre([IN|OUT|INOUT] parámetro TIPO,…)
     BEGIN
       -- lógica imperativa (IF, LOOP, SQL…);
     END;

2. Simulación en SQLite / SQL puro:
   - No existen SP nativos; se usan consultas con CASE, IF y transacciones.
   - Útil para entender el flujo antes de usar SP reales.

3. Ventajas:
   - Mantenibilidad: cambia la lógica en un solo sitio.
   - Seguridad: defines permisos más fácilmente.
   - Rendimiento: se compilan en el servidor.

4. Cuándo usar:
   - Operaciones repetitivas (ej. cálculos, validaciones).
   - Lógica de negocio compleja (IF…ELSE, bucles).
   - Interfaces limpias para aplicaciones (CALL en lugar de DML directo).

*/

-- -------------------------------------------------------------
-- 1️⃣ Simulación con CASE (procedimiento implícito)
-- -------------------------------------------------------------
-- * Calcula el nivel salarial según el salario de cada empleado.
SELECT nombre,
       CASE
         WHEN salario < 1000 THEN 'Bajo'
         WHEN salario BETWEEN 1000 AND 2000 THEN 'Medio'
         ELSE 'Alto'
       END AS nivel_salario
FROM empleados;
-- ✅ Útil para entender lógica condicional como si fuera un procedimiento.

-- -------------------------------------------------------------
-- 2️⃣ Procedimiento Almacenado: ObtenerNivelSalario
-- -------------------------------------------------------------
DELIMITER $$
CREATE PROCEDURE ObtenerNivelSalario()
BEGIN
  -- Muestra nombre y nivel salarial de todos los empleados
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

-- -------------------------------------------------------------
-- 3️⃣ Procedimiento con Parámetro: BuscarEmpleado
-- -------------------------------------------------------------
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

-- -------------------------------------------------------------
-- 4️⃣ Procedimiento de Inserción: AgregarCliente
-- -------------------------------------------------------------
DELIMITER $$
CREATE PROCEDURE AgregarCliente(IN cliNombre VARCHAR(100), IN cliEmail VARCHAR(100))
BEGIN
  -- Inserta un cliente nuevo; email debe ser único
  INSERT INTO clientes (nombre, email)
  VALUES (cliNombre, cliEmail);
END $$
DELIMITER ;
-- ✅ Ejecuta con: CALL AgregarCliente('Prueba', 'prueba@example.com');

-- -------------------------------------------------------------
-- 5️⃣ Eliminación Condicional: EliminarEmpleadoPorSalario
-- -------------------------------------------------------------
DELIMITER $$
CREATE PROCEDURE EliminarEmpleadoPorSalario(IN minSalario DECIMAL(10,2))
BEGIN
  -- Elimina empleados con salario menor al parámetro
  DELETE FROM empleados
  WHERE salario < minSalario;
END $$
DELIMITER ;
-- ✅ Ejecuta con: CALL EliminarEmpleadoPorSalario(1000.00);

-- -------------------------------------------------------------
-- 6️⃣ Limpieza de Procedimientos (opcional)
-- -------------------------------------------------------------
-- DROP PROCEDURE IF EXISTS ObtenerNivelSalario;
-- DROP PROCEDURE IF EXISTS BuscarEmpleado;
-- DROP PROCEDURE IF EXISTS AgregarCliente;
-- DROP PROCEDURE IF EXISTS EliminarEmpleadoPorSalario;

-- #############################################################
-- 🧪 EJERCICIOS PROPUESTOS
-- #############################################################

-- 1️⃣ Simulación con CASE:
--    • Copia el primer bloque de SELECT y modifica los rangos:
--      - salario < 1000   → 'Junior'
--      - 1000–3000         → 'Senior'
--      - >3000             → 'Lead'
--    • Ejecuta y comprueba los resultados.

-- 2️⃣ SP básico sin parámetros:
--    • Crea y prueba un SP llamado ListarClientes:
--         - Devuelva id y nombre de todos los clientes.
--    • Invoca con: CALL ListarClientes();

-- 3️⃣ SP con parámetro:
--    • Crea un SP FiltrarClientes por dominio de email:
--         - Parámetro: dominio VARCHAR(100)
--         - WHERE email LIKE CONCAT('%', dominio)
--    • Prueba con: CALL FiltrarClientes('example.com');

-- 4️⃣ SP de inserción:
--    • Crea un SP AgregarDepartamento:
--         - Parámetro: nombre VARCHAR(100)
--         - INSERT INTO departamentos(nombre)
--    • Gestiona duplicados: ¿qué ocurre si el nombre ya existe?

-- 5️⃣ SP de borrado condicional:
--    • Crea un SP EliminarClientesAntiguos:
--         - Parámetro: fecha_limite DATE
--         - DELETE FROM clientes WHERE fecha_alta < fecha_limite
--    • Invoca con: CALL EliminarClientesAntiguos('2022-01-01');

-- 6️⃣ Ejercicio avanzado:
--    • Dentro de un procedimiento, usa IF…ELSE para:
--         - Si no hay registros que cumplan la condición, SIGNAL un error.
--         - Si existen, realiza la operación y retorna un mensaje de éxito.

-- #############################################################
-- ✅ FIN BLOQUE 04: PROCEDIMIENTOS SIMULADOS
-- #############################################################
