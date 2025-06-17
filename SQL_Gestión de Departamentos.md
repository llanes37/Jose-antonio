## 🧾 Práctica Completa: Gestión de Empleados y Departamentos

### 🎯 Objetivo

En esta práctica trabajarás con las tablas `empleados` y `departamentos`. Repasarás:

- Cursores
- Procedimientos almacenados
- Control de condiciones
- Creación de tablas auxiliares
- Consultas avanzadas con lógica de negocio

---

### 🧱 Paso 1: Crear las tablas iniciales y añadir datos

```sql
-- Tabla de departamentos
CREATE TABLE departamentos (
    dept_no  TINYINT(2) NOT NULL,
    dnombre  VARCHAR(15), 
    loc      VARCHAR(15)
);

INSERT INTO departamentos VALUES 
(10,'CONTABILIDAD','SEVILLA'),
(20,'INVESTIGACIÓN','MADRID'),
(30,'VENTAS','BARCELONA'),
(40,'PRODUCCIÓN','BILBAO');

-- Tabla de empleados
CREATE TABLE empleados (
    emp_no    SMALLINT(4) UNSIGNED NOT NULL,
    apellido  VARCHAR(10),
    oficio    VARCHAR(10),
    dir       SMALLINT,
    fecha_alt DATE,
    salario   FLOAT(6,2),
    comision  FLOAT(6,2),
    dept_no   TINYINT(2) NOT NULL
);

INSERT INTO empleados VALUES 
(7369,'SÁNCHEZ','EMPLEADO',7902,'1990-12-17',1040,NULL,20),
(7499,'ARROYO','VENDEDOR',7698,'1990-02-20',1500,390,30),
(7521,'SALA','VENDEDOR',7698,'1991-02-22',1625,650,30),
(7566,'JIMÉNEZ','DIRECTOR',7839,'1991-04-02',2900,NULL,20),
(7654,'MARTÍN','VENDEDOR',7698,'1991-09-29',1600,1020,30),
(7698,'NEGRO','DIRECTOR',7839,'1991-05-01',3005,NULL,30),
(7782,'CEREZO','DIRECTOR',7839,'1991-06-09',2885,NULL,10),
(7788,'GIL','ANALISTA',7566,'1991-11-09',3000,NULL,20),
(7839,'REY','PRESIDENTE',NULL,'1991-11-17',4100,NULL,10),
(7844,'TOVAR','VENDEDOR',7698,'1991-09-08',1350,0,30),
(7876,'ALONSO','EMPLEADO',7788,'1991-09-23',1430,NULL,20),
(7900,'JIMENO','EMPLEADO',7698,'1991-12-03',1335,NULL,30),
(7902,'FERNÁNDEZ','ANALISTA',7566,'1991-12-03',3000,NULL,20),
(7934,'MUÑOZ','EMPLEADO',7782,'1992-01-23',1690,NULL,10);
```

---

## 🧭 Ejercicio 1: Mostrar apellidos de empleados usando un cursor

### 📄 Enunciado completo

Crea un procedimiento almacenado llamado `MostrarEmpleados` que utilice un cursor para recorrer la tabla `empleados` e imprimir por consola (con `SELECT`) el **apellido de cada uno** de ellos.

Este ejercicio tiene como objetivo que practiques:

- La creación y uso de cursores.
- La estructura de control con `LOOP`.
- El uso de `FETCH` y control de final de datos con `HANDLER`.

---

### 🔧 Código del procedimiento comentado paso a paso

```sql
-- ✅ Cambiamos el delimitador para permitir bloques BEGIN...END
DELIMITER //

CREATE PROCEDURE MostrarEmpleados()
BEGIN
    -- Declaramos variables necesarias
    DECLARE terminado INT DEFAULT 0;          -- Variable para saber si hemos llegado al final del cursor
    DECLARE nom VARCHAR(20);                  -- Variable donde se almacenará el apellido recuperado

    -- Declaramos el cursor que recorrerá los apellidos de los empleados
    DECLARE c CURSOR FOR 
        SELECT apellido FROM empleados;

    -- Handler para cuando ya no haya más filas
    DECLARE CONTINUE HANDLER FOR NOT FOUND 
        SET terminado = 1;

    -- Abrimos el cursor
    OPEN c;

    -- Iniciamos un bucle para recorrer todos los empleados
    bucle: LOOP
        -- Obtenemos el siguiente apellido
        FETCH c INTO nom;

        -- Si no hay más filas, salimos del bucle
        IF terminado = 1 THEN
            LEAVE bucle;
        END IF;

        -- Mostramos el apellido por consola
        SELECT nom AS Apellido;
    END LOOP;

    -- Cerramos el cursor
    CLOSE c;
END //

-- ✅ Restauramos el delimitador por defecto
DELIMITER ;
```

---

### 💡 Explicación paso a paso

- **Cursores**: se usan para recorrer resultados uno a uno cuando no es posible hacerlo directamente con un SELECT.
- **Handler**: sirve para controlar qué hacer cuando no hay más datos (aquí usamos `SET terminado = 1`).
- **LOOP + LEAVE**: estructura de repetición que sale cuando `terminado = 1`.
- **FETCH**: extrae una fila del cursor y la guarda en una variable.
- **SELECT dentro del bucle**: simula una impresión por pantalla para ver cada apellido.

---

### 🧪 Prueba sugerida

Antes de llamar al procedimiento, asegúrate de que la tabla `empleados` existe y tiene datos cargados. Puedes usar el script de `empleados-departamentos.sql`.

```sql
-- Ejecutar el procedimiento
CALL MostrarEmpleados();
```

📌 Resultado esperado: una lista de todos los apellidos de los empleados, cada uno en una línea diferente.

---

### 🎯 Tarea para ti

🔧 Modifica el procedimiento para que:

1. Además del **apellido**, también muestre el **salario** de cada empleado.
2. Añade un `ORDER BY` por **salario descendente** dentro del cursor.
3. Ejecuta el procedimiento modificado y verifica los resultados.

💡 Pista para la solución:

```sql
-- Cambia el cursor por:
DECLARE c CURSOR FOR 
    SELECT apellido, salario FROM empleados ORDER BY salario DESC;

-- Y declara dos variables:
DECLARE nom VARCHAR(20);
DECLARE sueldo FLOAT(6,2);
```

## 🦯 Ejercicio 2: Aumentar salario a empleados con sueldo bajo

### 📄 Enunciado completo

Crea un procedimiento almacenado llamado `AumentarSueldos` que aumente el salario de todos los empleados cuyo salario sea **menor a 1500** en un **10%**.

Este ejercicio te servira para repasar:

* Uso de `UPDATE` con condiciones.
* Calculo porcentual dentro de SQL.
* Creacion de procedimientos simples sin parametros.

---

### 🔧 Codigo del procedimiento con comentarios

```sql
-- ✅ Cambiamos el delimitador para permitir bloques BEGIN...END
DELIMITER //

CREATE PROCEDURE AumentarSueldos()
BEGIN
    -- Aumentamos el salario en un 10% solo a empleados con salario < 1500€
    UPDATE empleados
    SET salario = salario * 1.10
    WHERE salario < 1500;
END //

-- ✅ Restauramos el delimitador por defecto
DELIMITER ;
```

---

### 💡 Explicación

* `UPDATE empleados` aplica cambios en todos los registros de la tabla.
* La condición `WHERE salario < 1500` filtra solo los empleados con sueldos bajos.
* Se multiplica el salario por `1.10`, es decir, se incrementa un 10%.
* Este procedimiento es útil cuando queremos aplicar una subida general a un grupo concreto.

---

### 🧪 Prueba sugerida

```sql
-- 1. Ver empleados con salarios bajos antes del aumento
SELECT emp_no, apellido, salario FROM empleados WHERE salario < 1500;

-- 2. Ejecutar el procedimiento
CALL AumentarSueldos();

-- 3. Verificar los salarios actualizados
SELECT emp_no, apellido, salario FROM empleados WHERE salario < 1650;
```

📌 Deberías notar que los empleados con salario inferior a 1500 ahora tienen un nuevo salario, un 10% más alto.

---

### 🎯 Tarea para ti

Crea una versión mejorada del procedimiento llamada `AumentarSueldosPorcentaje` que:

1. Reciba un parámetro de entrada `pPorcentaje` (por ejemplo: 0.15 para un 15%).
2. Aplique ese porcentaje de aumento a los empleados con salario inferior a 1800.
3. Utilice esta fórmula dentro del `UPDATE`:

```sql
SET salario = salario * (1 + pPorcentaje)
```

💡 Esto te permitirá practicar cómo pasar parámetros y aplicar lógica flexible dentro de un procedimiento.


---

## 📋 Ejercicio 3: Crear tabla EMP\_SAL y rellenarla con los N empleados mejor pagados

### 📄 Enunciado completo

Crea una tabla llamada `emp_sal` y un procedimiento llamado `TopSalarios` que reciba un parámetro `n` e inserte en esa tabla los **n empleados con mayor salario** de la tabla original `empleados`.

Este ejercicio permite repasar:

* Creación de tablas nuevas con estructura concreta.
* Uso de `ORDER BY` y `LIMIT` en `INSERT INTO ... SELECT`.
* Procedimientos con parámetros de entrada.
* Actualización de contenido en cada ejecución.

---

### 🔧 Paso 1: Crear la tabla `emp_sal`

```sql
CREATE TABLE emp_sal (
    emp_no INT,
    apellido VARCHAR(15),
    salario FLOAT(6,2)
);
```

Esta tabla almacenará los resultados del procedimiento. Puedes ejecutarla una sola vez.

---

### 🔧 Paso 2: Crear el procedimiento `TopSalarios`

```sql
DELIMITER //

CREATE PROCEDURE TopSalarios(IN n INT)
BEGIN
    -- Limpiamos la tabla antes de insertar nuevos valores
    DELETE FROM emp_sal;

    -- Insertamos los N empleados con mayor salario
    INSERT INTO emp_sal (emp_no, apellido, salario)
    SELECT emp_no, apellido, salario
    FROM empleados
    ORDER BY salario DESC
    LIMIT n;
END //

DELIMITER ;
```

---

### 💡 Explicación paso a paso

* `DELETE FROM emp_sal`: vacía la tabla cada vez que se llama al procedimiento.
* `SELECT ... ORDER BY salario DESC LIMIT n`: selecciona los empleados mejor pagados.
* `n` es un parámetro de entrada: lo eliges al llamar al procedimiento.
* `INSERT INTO emp_sal (...) SELECT ...`: guarda los resultados en la tabla `emp_sal`.

---

### 🧪 Prueba sugerida

```sql
-- Ejecutar con los 5 empleados mejor pagados
CALL TopSalarios(5);

-- Ver el contenido de la tabla
SELECT * FROM emp_sal;
```

---

### 🎯 Tarea para ti

1. Añade una columna `fecha_consulta DATE` a la tabla `emp_sal`.
2. Modifica el procedimiento para que incluya `CURDATE()` en cada fila insertada.
3. Crea una versión alternativa llamada `TopSalariosPorDepto` que reciba un parámetro `pDept INT` y filtre por departamento.

💡 Esto te ayudará a trabajar con parámetros, filtros y trazabilidad de resultados.

```sql
CREATE TABLE emp_sal (
    emp_no INT,
    apellido VARCHAR(15),
    salario FLOAT(6,2)
);
```

```sql
DELIMITER //

CREATE PROCEDURE TopSalarios(IN n INT)
BEGIN
    DELETE FROM emp_sal;

    INSERT INTO emp_sal (emp_no, apellido, salario)
    SELECT emp_no, apellido, salario
    FROM empleados
    ORDER BY salario DESC
    LIMIT n;
END //

DELIMITER ;
```

---

## 📌 Ejercicio 4: Mostrar suma de salario por departamento

### 📄 Enunciado completo

Crea un procedimiento almacenado llamado `SumaSalariosPorDepartamento` que muestre la suma total de salarios agrupada por número de departamento (`dept_no`).

Este ejercicio te ayudará a practicar:

* Agrupaciones con `GROUP BY`.
* Sumar columnas numéricas (`SUM`).
* Procedimientos sin parámetros.

---

### 🔧 Código del procedimiento paso a paso

```sql
-- ✅ Cambiamos el delimitador para poder definir el procedimiento
DELIMITER //

CREATE PROCEDURE SumaSalariosPorDepartamento()
BEGIN
    -- Mostramos la suma total de salarios agrupada por departamento
    SELECT dept_no AS Departamento, SUM(salario) AS TotalSalarios
    FROM empleados
    GROUP BY dept_no;
END //

-- ✅ Restauramos el delimitador por defecto
DELIMITER ;
```

---

### 🧪 Prueba sugerida

```sql
-- Llamamos al procedimiento para ver el resultado
CALL SumaSalariosPorDepartamento();
```

---

### 💡 Explicación

* `GROUP BY dept_no`: agrupa los empleados por su departamento.
* `SUM(salario)`: suma todos los salarios de cada grupo.
* Se devuelve una tabla con dos columnas: número de departamento y suma total de salarios.

---

### 🎯 Tarea extra para ti

Modifica el procedimiento para que:

* Solo muestre departamentos con salario total superior a **5000€**.

Pista: Usa `HAVING SUM(salario) > 5000` al final del `SELECT`.

## 🔁 Ejercicio 5: Copiar empleados a otra tabla

### 📄 Enunciado completo

Crea un procedimiento llamado `CopiarEmpleados` que copie todos los registros de la tabla `empleados` a una nueva tabla llamada `empleados_copia`, manteniendo la misma estructura.

---

### 🔧 Paso 1: Crear la tabla `empleados_copia`

```sql
CREATE TABLE empleados_copia AS
SELECT * FROM empleados WHERE 1 = 0;
```

Esto crea una copia de la estructura, pero sin insertar datos.

---

### 🔧 Paso 2: Crear el procedimiento `CopiarEmpleados`

```sql
DELIMITER //

CREATE PROCEDURE CopiarEmpleados()
BEGIN
    DELETE FROM empleados_copia;

    INSERT INTO empleados_copia
    SELECT * FROM empleados;
END //

DELIMITER ;
```

---

### 📊 Prueba sugerida

```sql
CALL CopiarEmpleados();
SELECT * FROM empleados_copia;
```

---

## 🔢 Ejercicio 6: Contar empleados por letra inicial

### 📄 Enunciado completo

Crea un procedimiento llamado `ContarPorLetraInicial` que muestre cuántos empleados hay por cada **inicial del apellido** (A-Z).

---

### 🔧 Código del procedimiento

```sql
DELIMITER //

CREATE PROCEDURE ContarPorLetraInicial()
BEGIN
    SELECT LEFT(apellido, 1) AS LetraInicial, COUNT(*) AS Total
    FROM empleados
    GROUP BY LEFT(apellido, 1)
    ORDER BY LetraInicial;
END //

DELIMITER ;
```

---

### 📊 Prueba sugerida

```sql
CALL ContarPorLetraInicial();
```

---

## 📊 Ejercicio 7: Salario medio por departamento (con cursor)

### 📄 Enunciado completo

Crea un procedimiento `SalarioMedioDeptoCursor` que recorra los departamentos usando un cursor y calcule el salario medio de los empleados de cada uno.

---

### 🔧 Procedimiento con cursor

```sql
DELIMITER //

CREATE PROCEDURE SalarioMedioDeptoCursor()
BEGIN
    DECLARE fin INT DEFAULT 0;
    DECLARE dept INT;
    DECLARE sueldoMedio FLOAT;

    DECLARE c CURSOR FOR SELECT dept_no FROM departamentos;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET fin = 1;

    OPEN c;

    bucle: LOOP
        FETCH c INTO dept;
        IF fin = 1 THEN
            LEAVE bucle;
        END IF;

        SELECT dept AS Departamento,
               (SELECT ROUND(AVG(salario),2) FROM empleados WHERE dept_no = dept) AS SalarioMedio;
    END LOOP;

    CLOSE c;
END //

DELIMITER ;
```

---

### 📊 Prueba sugerida

```sql
CALL SalarioMedioDeptoCursor();
```

---

## ✅ Ejercicio 8: Marcar empleados como revisados

### 📄 Enunciado completo

1. Añade un campo `revisado BOOLEAN DEFAULT FALSE` a la tabla `empleados`.
2. Crea un procedimiento `MarcarRevisados` que marque como `TRUE` a todos los empleados cuyo salario sea **mayor al salario promedio global**.

---

### 🔧 Paso 1: Alterar tabla

```sql
ALTER TABLE empleados ADD COLUMN revisado BOOLEAN DEFAULT FALSE;
```

---

### 🔧 Paso 2: Crear procedimiento `MarcarRevisados`

```sql
DELIMITER //

CREATE PROCEDURE MarcarRevisados()
BEGIN
    DECLARE media FLOAT;

    -- Calcular salario medio de todos los empleados
    SELECT AVG(salario) INTO media FROM empleados;

    -- Marcar revisado a los que ganan más del promedio
    UPDATE empleados
    SET revisado = TRUE
    WHERE salario > media;
END //

DELIMITER ;
```

---

### 📊 Prueba sugerida

```sql
CALL MarcarRevisados();
SELECT apellido, salario, revisado FROM empleados;
```

---

## 💪 Ejercicio Final de Repaso: Operaciones con empleados y departamentos

### 🎯 Objetivo

Repasar todos los conceptos clave de procedimientos almacenados en MySQL: cursores, condiciones, bucles, parámetros, `UPDATE`, `SELECT`, `INSERT`, `GROUP BY`... usando exclusivamente las tablas `empleados` y `departamentos`.

---

### 🛍️ Ejercicio 1: Mostrar empleados de un departamento concreto

**Crea un procedimiento `VerEmpleadosDepto` que reciba un número de departamento como parámetro y muestre el apellido y salario de todos los empleados que pertenecen a él.**

💡 **Ayuda:** Usa `SELECT apellido, salario FROM empleados WHERE dept_no = pDepto`.

---

### 🛍️ Ejercicio 2: Subida de sueldo a un departamento

**Crea un procedimiento `SubidaDepto` que reciba un número de departamento y un porcentaje, y aumente el salario de los empleados de ese departamento.**

💡 **Ayuda:** Usa `UPDATE empleados SET salario = salario * (1 + pPorcentaje)` con `WHERE dept_no = pDepto`.

---

### 🛍️ Ejercicio 3: Mostrar los nombres de empleados con salario superior a la media

**Crea un procedimiento `EmpleadosSobreMedia` que muestre los apellidos y salarios de empleados cuyo sueldo es superior a la media de todos.**

💡 **Ayuda:**

* Calcular media con `SELECT AVG(salario)`.
* Luego hacer un `SELECT` con `WHERE salario > media`.

---

### 🛍️ Ejercicio 4: Contar empleados por inicial de apellido (cursor)

**Crea un procedimiento que cuente cuántos empleados hay por cada inicial de apellido (A-Z) y lo imprima usando un cursor.**

💡 **Ayuda:**

* `SELECT DISTINCT LEFT(apellido,1)` para obtener las letras.
* Usa un cursor para recorrer letras.
* Dentro del bucle, haz un `SELECT COUNT(*)` con `WHERE apellido LIKE 'A%'`, etc.

---

### 🛍️ Ejercicio 5: Revisar empleados con salario alto

**Crea un procedimiento que recorra a todos los empleados con un cursor y muestre solo aquellos cuyo salario sea mayor de 2500€.**

💡 **Ayuda:**

* Cursor con `SELECT apellido, salario FROM empleados`.
* Dentro del bucle, usar `IF salario > 2500 THEN SELECT ...`.

---

### 🛍️ Ejercicio 6: Mostrar departamentos con salario total alto

**Crea un procedimiento que muestre los departamentos donde la suma de salarios sea superior a 6000€.**

💡 **Ayuda:**

* `SELECT dept_no, SUM(salario) FROM empleados GROUP BY dept_no HAVING SUM(salario) > 6000`.

---

### ✅ Extra (nivel avanzado): Subir sueldo al que menos cobra de cada departamento

**Crea un procedimiento que recorra todos los departamentos y aumente en 5% el salario del empleado que menos cobra en cada uno.**

💡 **Ayuda:**

* Bucle sobre `SELECT dept_no FROM departamentos`.
* Para cada uno, hacer un `UPDATE empleados SET salario = salario * 1.05 WHERE salario = (SELECT MIN(salario) FROM empleados WHERE dept_no = ...)`.

---

📅 Ideal como ejercicio de repaso completo antes del examen.

