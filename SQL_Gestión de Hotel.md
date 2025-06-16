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
DELIMITER //

CREATE PROCEDURE RegistrarCliente(
    IN pNombre VARCHAR(100),     -- Nombre del cliente
    IN pApellido VARCHAR(100),   -- Apellido del cliente
    IN pDNI VARCHAR(20),         -- DNI único del cliente
    IN pTelefono VARCHAR(15),    -- Teléfono de contacto
    IN pEmail VARCHAR(100)       -- Correo electrónico del cliente
)
BEGIN
    -- Declaramos una variable para saber si ya existe un cliente con ese DNI
    DECLARE existe INT;

    -- Contamos los registros con ese mismo DNI
    SELECT COUNT(*) INTO existe
    FROM Clientes
    WHERE DNI = pDNI;

    -- Si no existe ningún cliente con ese DNI, insertamos el nuevo
    IF existe = 0 THEN
        INSERT INTO Clientes (Nombre, Apellido, DNI, Telefono, Email)
        VALUES (pNombre, pApellido, pDNI, pTelefono, pEmail);
    ELSE
        -- Si ya existe, mostramos un mensaje al usuario
        SELECT 'El cliente ya existe en la base de datos.' AS Mensaje;
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

### 🎯 Tarea para ti

Crea un nuevo procedimiento llamado `RegistrarClienteSimple` que:

- Reciba solo 3 parámetros: `Nombre`, `Apellido` y `DNI`.
- Inserte un nuevo cliente usando esos campos.
- Deje `Telefono` y `Email` como `NULL`.
- También compruebe si el cliente ya existe como hicimos antes.

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

### 1. Agregar columna `Estado`:

```sql
ALTER TABLE Habitaciones ADD Estado VARCHAR(10) DEFAULT 'Disponible';
```

### 2. Trigger tras insertar una reserva:

```sql
CREATE TRIGGER ActualizarEstadoReserva
AFTER INSERT ON Reservas
FOR EACH ROW
BEGIN
    UPDATE Habitaciones SET Estado = 'Ocupada'
    WHERE HabitacionID = NEW.HabitacionID;
END;
```

### 3. Trigger tras eliminar una reserva:

```sql
CREATE TRIGGER ActualizarEstadoCancelacion
AFTER DELETE ON Reservas
FOR EACH ROW
BEGIN
    UPDATE Habitaciones SET Estado = 'Disponible'
    WHERE HabitacionID = OLD.HabitacionID;
END;
```

### 🔹 Tarea para ti

Crea un trigger que ponga la habitación como "Limpieza" si se actualiza la fecha de salida a una más temprana.

---

## ⚠️ Ejercicio 5: Trigger de disponibilidad preventiva

```sql
CREATE TRIGGER VerificarDisponibilidad
BEFORE INSERT ON Reservas
FOR EACH ROW
BEGIN
    DECLARE habitacion_ocupada INT;
    SELECT COUNT(*) INTO habitacion_ocupada
    FROM Reservas
    WHERE NEW.HabitacionID = HabitacionID
    AND (NEW.FechaEntrada BETWEEN FechaEntrada AND FechaSalida
    OR NEW.FechaSalida BETWEEN FechaEntrada AND FechaSalida
    OR FechaEntrada BETWEEN NEW.FechaEntrada AND NEW.FechaSalida);

    IF habitacion_ocupada > 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'La habitación no está disponible para las fechas seleccionadas.';
    END IF;
END;
```

### 💡 Explicación

* Este trigger impide que se inserte una reserva si hay conflicto de fechas.

### 🔹 Tarea para ti

Modifica este trigger para registrar en una tabla `ErroresReservas` los intentos fallidos de reserva.

---

## 📆 Ejercicio 6: Reservar todas las disponibles para un cliente VIP

```sql
CREATE PROCEDURE ReservarDisponiblesParaArtista(
    IN pFechaEntrada DATE,
    IN pFechaSalida DATE
)
BEGIN
    DECLARE done INT DEFAULT 0;
    DECLARE habID INT;

    DECLARE cursorHab CURSOR FOR
    SELECT HabitacionID FROM Habitaciones WHERE HabitacionID NOT IN (
        SELECT HabitacionID FROM Reservas
        WHERE NOT (FechaSalida <= pFechaEntrada OR FechaEntrada >= pFechaSalida)
    );

    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = 1;

    OPEN cursorHab;
    loop_hab: LOOP
        FETCH cursorHab INTO habID;
        IF done THEN
            LEAVE loop_hab;
        END IF;
        CALL RealizarReserva(1, habID, pFechaEntrada, pFechaSalida);
    END LOOP;
    CLOSE cursorHab;
END;
```

### 💡 Explicación

* Se usa un cursor para recorrer todas las habitaciones disponibles.
* Se llama al procedimiento de reserva por cada habitación libre.

### 🔹 Tarea para ti

Haz que este procedimiento devuelva un mensaje con el total de habitaciones reservadas para el artista.

---

✉️ Fin de la práctica. Puedes probar todos los procedimientos en MySQL Workbench. Recuerda comentar cada prueba que hagas y modificar los valores para ver diferentes resultados.
