-- #############################################
-- 🧩 BLOQUE 03: CONSULTAS Y LÓGICA EN SQL
-- #############################################

-- ! Este script contiene ejemplos de SELECT con lógica: filtros, comparaciones,
-- ! condicionales y agrupaciones. Ejecútalo paso a paso en MySQL Workbench.

-- -----------------------------------------------------
-- 1️⃣ SELECT BÁSICO: Todas las columnas
-- -----------------------------------------------------
-- * Obtenemos todas las filas y columnas de empleados.
SELECT *
FROM empleados;
-- * Útil para verificar la estructura y contenido de la tabla.


-- -----------------------------------------------------
-- 2️⃣ USO DE ALIAS: Renombrar tablas y columnas
-- -----------------------------------------------------
-- * Alias de tabla (e) y alias de columna (sueldo).
SELECT e.id        AS empleado_id,
       e.nombre    AS nombre_empleado,
       e.salario   AS sueldo
FROM empleados AS e;


-- -----------------------------------------------------
-- 3️⃣ FILTRADO CON WHERE
-- -----------------------------------------------------
-- ! Consultar empleados que ganan más de 1500.
SELECT nombre, salario
FROM empleados
WHERE salario > 1500;

-- ? Prueba a cambiar el valor para ver distintos resultados.


-- -----------------------------------------------------
-- 4️⃣ BUSQUEDAS CON LIKE
-- -----------------------------------------------------
-- * Buscar clientes con email de dominio "gmail.com".
SELECT nombre, email
FROM clientes
WHERE email LIKE '%@gmail.com';

-- TODO: Intenta buscar nombres que empiecen por 'A':
-- SELECT nombre FROM clientes WHERE nombre LIKE 'A%';


-- -----------------------------------------------------
-- 5️⃣ FILTRADO CON IN
-- -----------------------------------------------------
-- * Obtener empleados de departamentos 1 (Ventas) o 2 (IT).
SELECT nombre, departamento_id
FROM empleados
WHERE departamento_id IN (1, 2);

-- ? Modifica la lista de IDs para practicar con otros departamentos.


-- -----------------------------------------------------
-- 6️⃣ RANGO DE FECHAS CON BETWEEN
-- -----------------------------------------------------
-- * Empleados dados de alta entre 2021 y 2022.
SELECT nombre, fecha_alta
FROM empleados
WHERE fecha_alta BETWEEN '2021-01-01' AND '2022-12-31';


-- -----------------------------------------------------
-- 7️⃣ CONDICIONES CON CASE (IF / ELSE)
-- -----------------------------------------------------
-- ! Asignar un nivel salarial según el salario.
SELECT nombre,
       CASE
         WHEN salario < 1000 THEN 'Bajo'
         WHEN salario BETWEEN 1000 AND 2000 THEN 'Medio'
         ELSE 'Alto'
       END AS nivel_salarial
FROM empleados;

-- * Ideal para simulaciones de procedimientos con condicionales.


-- -----------------------------------------------------
-- 8️⃣ AGRUPACIÓN Y AGREGADOS (GROUP BY)
-- -----------------------------------------------------
-- * Contar empleados y promedio salarial por departamento.
SELECT departamento_id,
       COUNT(*)    AS total_empleados,
       AVG(salario) AS salario_medio
FROM empleados
GROUP BY departamento_id;

-- TODO: Añade HAVING para filtrar departamentos con más de 2 empleados.


-- -----------------------------------------------------
-- 9️⃣ JOIN ENTRE TABLAS
-- -----------------------------------------------------
-- * Unir empleados con nombres de sus departamentos.
SELECT e.nombre      AS empleado,
       d.nombre      AS departamento
FROM empleados AS e
LEFT JOIN departamentos AS d
  ON e.departamento_id = d.id;

-- ? Cambia LEFT JOIN por INNER JOIN y observa la diferencia.


-- #############################################
-- ✅ FIN BLOQUE 03: SELECT Y LÓGICA
-- #############################################
