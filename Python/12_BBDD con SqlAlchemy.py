from sqlalchemy import create_engine, Column, Integer, String, DateTime
from sqlalchemy.orm import declarative_base, sessionmaker
from datetime import datetime

Base=declarative_base() # se crea una clase base para las tablas de la base de datos
engine = create_engine('sqlite:///academia.db') # se crea un motor para la base de datos
class Usuarios(Base):
    __tablename__='usuarios'
    id = Column(Integer, primary_key=True, autoincrement=True)
    usename = Column(String(50), nullable=False, unique=True)
    nombre = Column(String(50))
    apellido1 = Column(String(50))
    apellido2 = Column(String(50))
    creado = Column(DateTime, default=datetime.now())

    def __init__(self, usename, nombre, apellido1, apellido2): # se define el metodo 
        self.usename=usename
        self.nombre=nombre
        self.apellido1=apellido1
        self.apellido2=apellido2
    def __str__(self): # se define el metodo __str__ para que al imprimir el objeto se muestre el mensaje
        return f'Añadido el usuario: {self.usename}'
    
Session = sessionmaker(engine) # se crea una clase para interactuar con la base de datos
session = Session() # se crea una sesion para interactuar con la base de datos

Base.metadata.drop_all(engine) # si existe, elimina la tabla de la base de datos
Base.metadata.create_all(engine) # crea la tabla en la base de datos

##################### AÑADIR UN REGISTRO A LA TABLA USUARIOS #############################
usuario1 = Usuarios('jvazara', 'Jose', 'Vázquez', 'Arantegui')
usuario2 = Usuarios('ssanubi', 'Santiago', 'Santolaria', 'Ubieto')
session.add(usuario1)
session.add(usuario2)
session.commit()
print(usuario1)
print(usuario2)
##################### CONSULTAR LOS REGISTROS DE UNA TABLA ################################
print('Todos los usuarios:')
usuarios = session.query(Usuarios).all() # se obtienen todos los registros de la tabla
for usuario in usuarios:
    print(usuario.usename, usuario.nombre, usuario.apellido1, usuario.apellido2, usuario.creado)
print('Usuarios con nombre Jose:')
usuarios = session.query(Usuarios).filter(Usuarios.nombre=='Jose') # solo el de nombre Jose
for usuario in usuarios:
    print(usuario.usename, usuario.nombre, usuario.apellido1, usuario.apellido2, usuario.creado) 

##################### ACTUALIZAR UN REGISTRO DE LA TABLA USUARIOS ##########################
usuario = session.query(Usuarios).filter(Usuarios.usename=='jvazara').first() # se obtiene el primer registro con usename=jvazara
usuario.nombre = 'José Ignacio' # se actualiza el nombre
session.commit() # se confirma la actualización
print('Usuario actualizado:')
print(usuario.usename, usuario.nombre, usuario.apellido1, usuario.apellido2, usuario.creado)

##################### ELIMINAR UN REGISTRO DE LA TABLA USUARIOS ##########################
usuario = session.query(Usuarios).filter(Usuarios.usename=='ssanubi').first() # se obtiene el primer registro con usename=ssanubi
session.delete(usuario) # se elimina el registro
session.commit() # se confirma la eliminación
print('Usuario eliminado:')
print(usuario.usename, usuario.nombre, usuario.apellido1, usuario.apellido2, usuario.creado)
