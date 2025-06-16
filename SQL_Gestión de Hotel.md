# Práctica Completa SQL: Gestión de Hotel 🏨

## ✨ Objetivo de la práctica

Vamos a trabajar con la base de datos de un hotel. El objetivo es aprender a utilizar:

* Procedimientos almacenados (`CREATE PROCEDURE`)
* Triggers (disparadores)
* Comprobaciones de disponibilidad
* Inserciones condicionadas
* Lógica de negocio en SQL

Al finalizar esta práctica serás capaz de:

* Registrar nuevos clientes
* Hacer reservas comprobando disponibilidad
* Actualizar precios
* Modificar el estado de las habitaciones automáticamente
* Evitar errores lógicos con triggers preventivos

---

## ⚡ Paso 1: Crear la base de datos y las tablas

```sql
-- Crear la base de datos 'Hotel' y usarla
CREATE DATABASE IF NOT EXISTS Hotel;
USE Hotel;

-- Crear la tabla 'Clientes'
CREATE TABLE IF NOT EXISTS Clientes (
    ClienteID INT AUTO_INCREMENT PRIMARY KEY,         -- ID autoincremental
    Nombre VARCHAR(100) NOT NULL,                    -- Nombre obligatorio
    Apellido VARCHAR(100) NOT NULL,                  -- Apellido obligatorio
    DNI VARCHAR(20) UNIQUE NOT NULL,                 -- DNI único y obligatorio
    Telefono VARCHAR(15),                            -- Teléfono opcional
    Email VARCHAR(100)                               -- Email opcional
);

-- Crear la tabla 'Habitaciones'
CREATE TABLE IF NOT EXISTS Habitaciones (
    HabitacionID INT AUTO_INCREMENT PRIMARY KEY,      -- ID autoincremental
    Numero INT NOT NULL UNIQUE,                      -- Número de habitación único
    Tipo VARCHAR(50) NOT NULL,                       -- Tipo de habitación
    PrecioNoche DECIMAL(5,2) NOT NULL                -- Precio por noche
);

-- Crear la tabla 'Reservas'
CREATE TABLE IF NOT EXISTS Reservas (
    ReservaID INT AUTO_INCREMENT PRIMARY KEY,         -- ID de reserva
    ClienteID INT,                                    -- FK Cliente
    HabitacionID INT,                                 -- FK Habitación
    FechaEntrada DATE NOT NULL,
    FechaSalida DATE NOT NULL,
    FOREIGN KEY (ClienteID) REFERENCES Clientes(ClienteID),
    FOREIGN KEY (HabitacionID) REFERENCES Habitaciones(HabitacionID)
);
```

---

## 💰 Paso 2: Insertar datos iniciales

```sql
-- Insertar clientes de prueba
INSERT INTO Clientes (Nombre, Apellido, DNI, Telefono, Email) VALUES
('Juan', 'Pérez', '12345678A', '600123456', 'juan.perez@email.com'),
('Ana', 'García', '87654321B', '610234567', 'ana.garcia@email.com');

-- Insertar habitaciones de prueba
INSERT INTO Habitaciones (Numero, Tipo, PrecioNoche) VALUES
(101, 'Individual', 50.00),
(102, 'Doble', 75.00),
(103, 'Suite', 120.00);

-- Insertar reservas de prueba
INSERT INTO Reservas (ClienteID, HabitacionID, FechaEntrada, FechaSalida) VALUES
(1, 1, '2024-06-15', '2024-06-18'),
(2, 2, '2024-06-20', '2024-06-22');
```

## ✍️ Ejercicio 1: Registrar un nuevo cliente

### 📄 Enunciado completo

Crea un **procedimiento almacenado** llamado `RegistrarCliente` que permita insertar un nuevo cliente en la tabla `Clientes`.

Este procedimiento debe cumplir las siguientes condiciones:

- Recibe como entrada: nombre, apellido, DNI, teléfono y email del cliente.
- Antes de insertar, debe comprobar si ya existe un cliente con ese mismo DNI en la base de datos.
- Si el cliente **ya existe**, se debe devolver el mensaje: `'El cliente ya existe en la base de datos.'`
- Si el cliente **no existe**, se insertará con todos los datos proporcionados.

Este control permite evitar duplicados y garantizar la integridad del sistema.

---

### 🔧 Código del procedimiento comentado paso a paso

```sql
-- Cambiamos el delimitador para usar bloques BEGIN ... END
-- Cambiamos el delimitador para usar bloques BEGIN ... END
DELIMITER //

CREATE PROCEDURE RegistrarCliente(
    IN pNombre VARCHAR(100),     -- Nombre del cliente
    IN pApellido VARCHAR(100),   -- Apellido del cliente
    IN pDNI VARCHAR(20),         -- DNI único del cliente
    IN pTelefono VARCHAR(15),    -- Teléfono de contacto
    IN pEmail VARCHAR(100)       -- Correo electrónico del cliente
)
BEGIN
    DECLARE existe INT;

    -- 1. Si el nombre es 'José Antonio', rechazamos la inserción directamente
    IF pNombre = 'José Antonio' THEN
        SELECT 'No se permiten registros con el nombre José Antonio.' AS Mensaje;
    ELSE
        -- 2. Comprobamos si ya existe el DNI
        SELECT COUNT(*) INTO existe
        FROM Clientes
        WHERE DNI = pDNI;

        -- Si no existe, insertamos al cliente
        IF existe = 0 THEN
            INSERT INTO Clientes (Nombre, Apellido, DNI, Telefono, Email)
            VALUES (pNombre, pApellido, pDNI, pTelefono, pEmail);
        ELSE
            -- Si ya existe, mostramos un mensaje
            SELECT 'El cliente ya existe en la base de datos.' AS Mensaje;
        END IF;
    END IF;
END //

-- Restauramos el delimitador original
DELIMITER ;

```

---

### 💡 Explicación rápida

- Este procedimiento verifica si el DNI ya está en la base.
- Si no lo encuentra (`existe = 0`), inserta al nuevo cliente.
- Si ya existe, muestra un mensaje informativo.
- Los comentarios en el código te guían en cada línea.

---

## 🧾 Procedimiento Extra: RegistrarClienteSimple

### 📄 Enunciado completo

Crea un **procedimiento almacenado** llamado `RegistrarClienteSimple` que registre un nuevo cliente de forma simplificada.

Este procedimiento debe:

- Recibir **solo tres parámetros**: `Nombre`, `Apellido` y `DNI`.
- Insertar un nuevo cliente usando únicamente esos campos.
- Dejar los campos `Telefono` y `Email` con valor `NULL`.
- Verificar si el cliente **ya existe** en la tabla `Clientes` mediante su `DNI`.
- Si ya existe, mostrar el mensaje: `'El cliente ya está registrado.'`
- Si no existe, insertar el nuevo cliente y guardar los datos mínimos.

---

### 🔧 Código del procedimiento con comentarios paso a paso

```sql
-- Cambiamos el delimitador para poder definir el procedimiento correctamente
DELIMITER //

CREATE PROCEDURE RegistrarClienteSimple(
    IN pNombre VARCHAR(100),     -- Nombre del cliente
    IN pApellido VARCHAR(100),   -- Apellido del cliente
    IN pDNI VARCHAR(20)          -- DNI único del cliente
)
BEGIN
    -- Declaramos una variable para comprobar si el DNI ya está registrado
    DECLARE existe INT;

    -- Contamos cuántos clientes tienen ese mismo DNI
    SELECT COUNT(*) INTO existe
    FROM Clientes
    WHERE DNI = pDNI;

    -- Si el cliente NO existe, lo insertamos con Teléfono y Email como NULL
    IF existe = 0 THEN
        INSERT INTO Clientes (Nombre, Apellido, DNI, Telefono, Email)
        VALUES (pNombre, pApellido, pDNI, NULL, NULL);
        SELECT '✅ Cliente registrado correctamente.' AS Mensaje;

    -- Si ya existe, mostramos un mensaje
    ELSE
        SELECT '⚠️ El cliente ya está registrado.' AS Mensaje;
    END IF;
END //

-- Restauramos el delimitador original
DELIMITER ;
```

---

### 💡 Explicación técnica

- Se trabaja solo con los campos obligatorios: `Nombre`, `Apellido`, `DNI`.
- `Telefono` y `Email` se insertan como `NULL` usando `VALUES (..., NULL, NULL)`.
- Se controla la duplicidad mediante una consulta `SELECT COUNT(*)` filtrando por `DNI`.
- Se usa un `IF` para insertar solo si no existe previamente.
- Devuelve un mensaje diferente según el resultado de la operación.

---

### 🧪 Prueba sugerida

```sql
-- Prueba insertando un cliente nuevo
CALL RegistrarClienteSimple('Mario', 'Ruiz', '77889900T');

-- Prueba insertando el mismo DNI de nuevo
CALL RegistrarClienteSimple('Mario', 'Ruiz', '77889900T');

-- Consulta la tabla para ver el resultado
SELECT * FROM Clientes WHERE DNI = '77889900T';
```

---

### 🎯 Tarea para ti

Crea una nueva versión del procedimiento llamada `RegistrarClienteNullSeguro` que además:

- No permita insertar si el nombre o apellido vienen vacíos (`''`).
- En caso de que alguno esté vacío, devuelva el mensaje: `'❌ Nombre y apellido son obligatorios.'`


💡 Esto te permitirá practicar inserciones con campos opcionales y lógica de validación.

🧪 **Ejemplo para probarlo una vez creado**:

```sql
CALL RegistrarClienteSimple('Mario', 'Ruiz', '88877766P');
SELECT * FROM Clientes WHERE DNI = '88877766P';
```


## 🗒️ Ejercicio 2: Realizar una reserva

### 📄 Enunciado completo

Crea un **procedimiento almacenado** llamado `RealizarReserva` que permita insertar una nueva reserva en la tabla `Reservas`.

Este procedimiento debe:

- Recibir como parámetros el `ClienteID`, el `HabitacionID`, la `FechaEntrada` y la `FechaSalida`.
- Antes de insertar la reserva, debe comprobar si **la habitación ya está ocupada** durante ese rango de fechas.
- Si la habitación **está libre**, debe insertar la reserva correctamente.
- Si **ya hay una reserva que se solapa** con las fechas solicitadas, se debe mostrar el mensaje: `'La habitación no está disponible para las fechas seleccionadas.'`

El objetivo es evitar reservas dobles y garantizar que ninguna habitación esté ocupada por dos clientes a la vez.

---

### 🔧 Código del procedimiento comentado paso a paso

```sql
-- Cambiamos el delimitador para poder definir el procedimiento
DELIMITER //

CREATE PROCEDURE RealizarReserva(
    IN pClienteID INT,         -- ID del cliente que quiere reservar
    IN pHabitacionID INT,      -- ID de la habitación a reservar
    IN pFechaEntrada DATE,     -- Fecha de entrada
    IN pFechaSalida DATE       -- Fecha de salida
)
BEGIN
    -- Declaramos una variable para comprobar si la habitación está ocupada en ese rango
    DECLARE ocupada INT;

    -- Comprobamos si existen reservas que se solapen con las fechas pedidas
    SELECT COUNT(*) INTO ocupada
    FROM Reservas
    WHERE HabitacionID = pHabitacionID
    AND NOT (
        FechaSalida <= pFechaEntrada OR
        FechaEntrada >= pFechaSalida
    );

    -- Si no hay conflicto, realizamos la reserva
    IF ocupada = 0 THEN
        INSERT INTO Reservas (ClienteID, HabitacionID, FechaEntrada, FechaSalida)
        VALUES (pClienteID, pHabitacionID, pFechaEntrada, pFechaSalida);
    ELSE
        -- Si hay conflicto, devolvemos un mensaje
        SELECT 'La habitación no está disponible para las fechas seleccionadas.' AS Mensaje;
    END IF;
END //

-- Restauramos el delimitador original
DELIMITER ;
```

---

### 💡 Explicación técnica

- `NOT (FechaSalida <= entrada OR FechaEntrada >= salida)`:
  - Esto detecta si las fechas **se solapan** con alguna reserva existente.
  - Si no se solapan, la habitación está libre.
- Si no hay conflictos (`ocupada = 0`), se hace la reserva con un `INSERT`.
- Si hay conflicto (`ocupada > 0`), se muestra un mensaje al usuario.

---

### 🎯 Tarea para ti

Crea una **nueva versión** del procedimiento llamada `RealizarReservaConMensaje`, que haga lo siguiente:

- Inserte la reserva si la habitación está libre.
- Si la inserción es exitosa, devuelva el mensaje: `'✅ Reserva realizada con éxito'`.
- Si la habitación está ocupada, devuelva: `'❌ La habitación no está disponible en esas fechas'`.

💡 Esta variante te ayudará a practicar cómo mostrar mensajes distintos según el resultado del procedimiento.

🧪 **Sugerencia de prueba**:

```sql
-- Prueba con una habitación libre
CALL RealizarReserva(1, 3, '2024-06-25', '2024-06-27');

-- Prueba con una habitación ya ocupada
CALL RealizarReserva(2, 1, '2024-06-16', '2024-06-17');
```

✅ Verifica luego con:

```sql
SELECT * FROM Reservas ORDER BY FechaEntrada;
```


## 💸 Ejercicio 3: Actualizar precio de una habitación

### 📄 Enunciado completo

Crea un **procedimiento almacenado** llamado `ActualizarPrecioHabitacion` que permita **modificar el precio por noche** de una habitación concreta en la tabla `Habitaciones`.

Este procedimiento debe:

- Recibir como parámetros:
  - El `ID` de la habitación (`HabitacionID`).
  - El nuevo precio (`PrecioNoche`).
- Actualizar directamente el campo `PrecioNoche` de la habitación seleccionada.

Es útil cuando hay cambios de tarifas, promociones o ajustes en la política del hotel.

---

### 🔧 Código del procedimiento comentado paso a paso

```sql
-- Cambiamos el delimitador para definir el procedimiento correctamente
DELIMITER //

CREATE PROCEDURE ActualizarPrecioHabitacion(
    IN pHabitacionID INT,          -- ID de la habitación a actualizar
    IN pNuevoPrecio DECIMAL(5,2)   -- Nuevo precio por noche
)
BEGIN
    -- Actualizamos el campo PrecioNoche para la habitación indicada
    UPDATE Habitaciones
    SET PrecioNoche = pNuevoPrecio
    WHERE HabitacionID = pHabitacionID;
END //

-- Restauramos el delimitador original
DELIMITER ;
```

---

### 💡 Explicación técnica

- `UPDATE Habitaciones SET PrecioNoche = pNuevoPrecio`: cambia el valor del precio.
- `WHERE HabitacionID = pHabitacionID`: limita la actualización solo a la habitación indicada.
- Se asume que `HabitacionID` existe. Si no existe, no se actualiza nada (no lanza error).

---

### 🎯 Tarea para ti

Crea un procedimiento llamado `AumentarPrecioSuites10` que:

- Actualice **todas las habitaciones cuyo tipo sea `'Suite'`**.
- Aumente su `PrecioNoche` en un **10%** respecto al valor actual.
- No reciba parámetros. Debe aplicar el cambio globalmente.

🧠 Para ello, puedes usar la siguiente fórmula en `SET`:

```sql
SET PrecioNoche = PrecioNoche * 1.10
```

🧪 **Sugerencia de prueba**:

```sql
CALL ActualizarPrecioHabitacion(2, 85.00);
SELECT * FROM Habitaciones WHERE HabitacionID = 2;
```

Y luego prueba tu tarea con:

```sql
CALL AumentarPrecioSuites10();
SELECT * FROM Habitaciones WHERE Tipo = 'Suite';
```


## 🔠 Ejercicio 4: Triggers para actualizar estado de habitaciones

### 📄 Enunciado completo

Vamos a automatizar el control del **estado de las habitaciones** en el hotel. Para ello, necesitas crear una columna y dos **triggers**.

El objetivo es:

- Cuando se **realiza una reserva**, la habitación debe pasar a estado `'Ocupada'`.
- Cuando se **elimina una reserva**, la habitación debe volver a estar `'Disponible'`.

Esto garantiza que siempre sepamos en qué estado se encuentra cada habitación sin hacerlo manualmente.

---

### 🔧 Paso 1: Añadir la columna `Estado` a la tabla `Habitaciones`

```sql
ALTER TABLE Habitaciones
ADD Estado VARCHAR(10) DEFAULT 'Disponible';
```

📌 Esta columna indicará si la habitación está `Disponible`, `Ocupada`, `Limpieza`, etc.

---

### 🔧 Paso 2: Trigger que actualiza a 'Ocupada' al insertar una reserva

```sql
CREATE TRIGGER ActualizarEstadoReserva
AFTER INSERT ON Reservas
FOR EACH ROW
BEGIN
    -- Al insertar una nueva reserva, cambiamos el estado a 'Ocupada'
    UPDATE Habitaciones
    SET Estado = 'Ocupada'
    WHERE HabitacionID = NEW.HabitacionID;
END;
```

📌 `NEW.HabitacionID` se refiere a la habitación que se acaba de reservar.

---

### 🔧 Paso 3: Trigger que actualiza a 'Disponible' al eliminar una reserva

```sql
CREATE TRIGGER ActualizarEstadoCancelacion
AFTER DELETE ON Reservas
FOR EACH ROW
BEGIN
    -- Al cancelar una reserva, la habitación vuelve a estar disponible
    UPDATE Habitaciones
    SET Estado = 'Disponible'
    WHERE HabitacionID = OLD.HabitacionID;
END;
```

📌 `OLD.HabitacionID` hace referencia a la habitación que estaba reservada antes de la eliminación.

---

### 💡 Explicación técnica

- `AFTER INSERT`: se ejecuta justo después de añadir una nueva reserva.
- `AFTER DELETE`: se ejecuta justo después de eliminarla.
- `NEW` y `OLD`: permiten acceder a los datos nuevos y antiguos en cada acción.
- La lógica mantiene el estado de la habitación sincronizado con las reservas activas.

---

### 🎯 Tarea para ti

Crea un **nuevo trigger** llamado `ActualizarEstadoLimpieza` que:

- Se dispare **después de actualizar** una reserva (`AFTER UPDATE`).
- Compare si la nueva `FechaSalida` es **menor** que la anterior (`OLD.FechaSalida`).
- Si es así, actualice el estado de la habitación a `'Limpieza'`.

💡 Esto simula el caso en el que un cliente se va antes de lo previsto y el personal de limpieza debe actuar.

🧪 **Sugerencia de prueba para la tarea**:

```sql
-- Actualizamos una reserva para simular que el cliente se va antes
UPDATE Reservas
SET FechaSalida = '2024-06-16'
WHERE ReservaID = 1;
```

Y luego consulta el estado:

```sql
SELECT Estado FROM Habitaciones WHERE HabitacionID = 1;
```

✅ Resultado esperado: `'Limpieza'`


---

## ⚠️ Ejercicio 5: Trigger de disponibilidad preventiva

### 📄 Enunciado completo

Vamos a crear un **trigger de seguridad** que impida insertar una reserva si **la habitación ya está ocupada** durante las fechas seleccionadas.

Este trigger actúa como una **última línea de defensa**, incluso si el procedimiento que inserta la reserva no ha hecho bien la comprobación.

Debe:

- Activarse **antes de insertar** (`BEFORE INSERT`) una nueva fila en la tabla `Reservas`.
- Comprobar si las fechas de la nueva reserva se **solapan** con alguna ya existente para esa habitación.
- Si hay conflicto, **impedir la inserción** mostrando un mensaje de error personalizado.

---

### 🔧 Código del trigger con comentarios paso a paso

```sql
-- Trigger que se ejecuta antes de insertar una reserva
CREATE TRIGGER VerificarDisponibilidad
BEFORE INSERT ON Reservas
FOR EACH ROW
BEGIN
    DECLARE habitacion_ocupada INT;

    -- Contamos cuántas reservas se solapan con la nueva
    SELECT COUNT(*) INTO habitacion_ocupada
    FROM Reservas
    WHERE NEW.HabitacionID = HabitacionID
    AND (
        NEW.FechaEntrada BETWEEN FechaEntrada AND FechaSalida OR
        NEW.FechaSalida BETWEEN FechaEntrada AND FechaSalida OR
        FechaEntrada BETWEEN NEW.FechaEntrada AND NEW.FechaSalida
    );

    -- Si hay alguna, impedimos la inserción lanzando un error
    IF habitacion_ocupada > 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = '❌ La habitación no está disponible para las fechas seleccionadas.';
    END IF;
END;
```

---

### 💡 Explicación técnica

- `BEFORE INSERT`: se ejecuta justo **antes de insertar** una nueva reserva.
- `NEW.FechaEntrada` y `NEW.FechaSalida`: son los valores que el usuario intenta insertar.
- La condición `OR` busca **cualquier solapamiento de fechas**:
  - Si la entrada o salida están entre una reserva existente, o si se solapan por completo.
- `SIGNAL SQLSTATE '45000'`: lanza un error que detiene la operación.

---

### 🎯 Tarea para ti

Modifica este trigger o crea uno nuevo que además de impedir la reserva:

1. Inserte un **registro del intento fallido** en una tabla llamada `ErroresReservas`.
2. Esta tabla debe contener:
   - `ID` autoincremental
   - `FechaIntento`
   - `ClienteID`
   - `HabitacionID`
   - `FechaEntrada`
   - `FechaSalida`
   - `MensajeError`

---

### 🧪 Sugerencia de prueba

```sql
-- Intentamos insertar una reserva que se solapa con una existente
INSERT INTO Reservas (ClienteID, HabitacionID, FechaEntrada, FechaSalida)
VALUES (2, 1, '2024-06-16', '2024-06-18');
```

➡️ Resultado esperado: se lanza un error que impide el insert.

---

### 🛠️ Sugerencia para crear la tabla de errores

```sql
CREATE TABLE ErroresReservas (
    ErrorID INT AUTO_INCREMENT PRIMARY KEY,
    FechaIntento DATETIME,
    ClienteID INT,
    HabitacionID INT,
    FechaEntrada DATE,
    FechaSalida DATE,
    MensajeError VARCHAR(255)
);
```

Luego puedes practicar cómo usar `INSERT INTO ErroresReservas` dentro de un trigger combinado o procedimiento.

## 🧪 Práctica Final: Gestión de cancelaciones y facturación

### 🎯 Objetivo general

Esta práctica final integra todo lo aprendido: procedimientos, triggers, validaciones, inserciones y control de estado.

Simula el proceso real de:

1. Cancelar una reserva por parte del cliente.
2. Registrar la cancelación en una nueva tabla.
3. Calcular el importe de la estancia.
4. Actualizar el estado de la habitación a "Disponible".

---

### 🧱 Parte 1: Nueva tabla para registrar cancelaciones

Crea una tabla llamada `Cancelaciones` que registre toda cancelación que se haga.

```sql
CREATE TABLE Cancelaciones (
    CancelacionID INT AUTO_INCREMENT PRIMARY KEY,
    ReservaID INT,
    ClienteID INT,
    HabitacionID INT,
    FechaCancelacion DATE,
    ImporteTotal DECIMAL(8,2),
    FOREIGN KEY (ReservaID) REFERENCES Reservas(ReservaID)
);
```

---

### ⚙️ Parte 2: Crear un procedimiento `CancelarReserva`

Este procedimiento debe:

- Recibir como parámetros:
  - `pReservaID`
  - `pFechaCancelacion`
- Buscar los datos asociados a la reserva (cliente, habitación, fechas).
- Calcular el número de noches y el importe total.
- Insertar un registro en la tabla `Cancelaciones`.
- Eliminar la reserva de la tabla `Reservas`.

```sql
DELIMITER //

CREATE PROCEDURE CancelarReserva(
    IN pReservaID INT,
    IN pFechaCancelacion DATE
)
BEGIN
    DECLARE vClienteID INT;
    DECLARE vHabitacionID INT;
    DECLARE vFechaEntrada DATE;
    DECLARE vFechaSalida DATE;
    DECLARE vPrecio DECIMAL(5,2);
    DECLARE vDias INT;
    DECLARE vImporteTotal DECIMAL(8,2);

    -- Obtenemos los datos de la reserva
    SELECT ClienteID, HabitacionID, FechaEntrada, FechaSalida
    INTO vClienteID, vHabitacionID, vFechaEntrada, vFechaSalida
    FROM Reservas
    WHERE ReservaID = pReservaID;

    -- Obtenemos el precio por noche
    SELECT PrecioNoche INTO vPrecio
    FROM Habitaciones
    WHERE HabitacionID = vHabitacionID;

    -- Calculamos los días de estancia
    SET vDias = DATEDIFF(vFechaSalida, vFechaEntrada);

    -- Calculamos el importe total
    SET vImporteTotal = vDias * vPrecio;

    -- Insertamos en Cancelaciones
    INSERT INTO Cancelaciones (ReservaID, ClienteID, HabitacionID, FechaCancelacion, ImporteTotal)
    VALUES (pReservaID, vClienteID, vHabitacionID, pFechaCancelacion, vImporteTotal);

    -- Eliminamos la reserva
    DELETE FROM Reservas WHERE ReservaID = pReservaID;
END //

DELIMITER ;
```

---

### 🧲 Parte 3: Trigger para actualizar estado al cancelar

Crea un trigger que, **tras eliminar una reserva**, ponga el estado de la habitación a `'Disponible'`.

```sql
CREATE TRIGGER EstadoDisponibleTrasCancelacion
AFTER DELETE ON Reservas
FOR EACH ROW
BEGIN
    UPDATE Habitaciones
    SET Estado = 'Disponible'
    WHERE HabitacionID = OLD.HabitacionID;
END;
```

---

### 📌 ¿Qué conceptos has repasado aquí?

- `CREATE TABLE` con claves foráneas
- `CREATE PROCEDURE` con múltiples variables y lógica
- `SELECT INTO`, `DATEDIFF`, `SET`, `INSERT`, `DELETE`
- Cálculo automático de importes
- `CREATE TRIGGER` para mantener integridad del estado

---

### 🎯 Tareas para ti

1. Inserta una reserva con fechas de prueba para un cliente.
2. Ejecuta `CALL CancelarReserva(ID_reserva, CURDATE());`
3. Comprueba que:
   - La reserva ha sido eliminada.
   - La habitación vuelve a estar disponible.
   - La cancelación aparece registrada con el importe correcto.
4. Mejora el procedimiento para que devuelva el importe con un `SELECT`.

---

✉️ ¡Fin de la práctica! Si completaste todos los pasos, has repasado todos los conceptos clave del módulo.

