# Guía de SQLAlchemy en Python

SQLAlchemy es una biblioteca de Python que proporciona una abstracción de alto nivel para interactuar con bases de datos relacionales. Permite trabajar con bases de datos de manera eficiente mediante el uso de un ORM (Object Relational Mapper).

## Instalación

Para utilizar SQLAlchemy en Python, primero debes instalarlo con el siguiente comando:

```sh
pip install sqlalchemy
```

## Creación del Motor de Base de Datos

En SQLAlchemy, se necesita un **motor de base de datos** para establecer la conexión con la base de datos:

```python
from sqlalchemy import create_engine

engine = create_engine('sqlite:///academia.db')  # Conexión a SQLite
```

## Declaración de la Base

Para definir las tablas, se utiliza una **clase base declarativa**:

```python
from sqlalchemy.orm import declarative_base

Base = declarative_base()
```

## Definición de un Modelo de Datos

Un modelo de datos representa una tabla en la base de datos. Se define como una clase en Python:

```python
from sqlalchemy import Column, Integer, String, DateTime
from datetime import datetime

class Usuarios(Base):
    __tablename__ = 'usuarios'

    id = Column(Integer, primary_key=True, autoincrement=True)
    usename = Column(String(50), nullable=False, unique=True)
    nombre = Column(String(50))
    apellido1 = Column(String(50))
    apellido2 = Column(String(50))
    creado = Column(DateTime, default=datetime.now())

    def __init__(self, usename, nombre, apellido1, apellido2):
        self.usename = usename
        self.nombre = nombre
        self.apellido1 = apellido1
        self.apellido2 = apellido2

    def __str__(self):
        return f'Añadido el usuario: {self.usename}'
```

## Creación de la Base de Datos

Para crear las tablas en la base de datos:

```python
Base.metadata.create_all(engine)
```

## Creación de una Sesión

Una sesión permite interactuar con la base de datos:

```python
from sqlalchemy.orm import sessionmaker

Session = sessionmaker(bind=engine)
session = Session()
```

## Operaciones CRUD en SQLAlchemy

### 1. Insertar Datos

```python
usuario1 = Usuarios('jvazara', 'Jose', 'Vázquez', 'Arantegui')
session.add(usuario1)
session.commit()
```

### 2. Consultar Datos

```python
usuarios = session.query(Usuarios).all()
for usuario in usuarios:
    print(usuario.usename, usuario.nombre, usuario.apellido1, usuario.apellido2, usuario.creado)
```

### 3. Filtrar Datos

```python
usuarios = session.query(Usuarios).filter(Usuarios.nombre == 'Jose').all()
```

### 4. Actualizar Datos

```python
usuario = session.query(Usuarios).filter(Usuarios.usename == 'jvazara').first()
usuario.nombre = 'José Ignacio'
session.commit()
```

### 5. Eliminar Datos

```python
usuario = session.query(Usuarios).filter(Usuarios.usename == 'ssanubi').first()
session.delete(usuario)
session.commit()
```

## Conclusión

SQLAlchemy proporciona una forma flexible y potente para interactuar con bases de datos relacionales en Python. Su ORM permite trabajar con objetos en lugar de escribir consultas SQL manualmente, facilitando la gestión de bases de datos.

