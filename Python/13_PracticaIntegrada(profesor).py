import os
import platform
import sqlite3
import time
from datetime import datetime

# Crear la base de datos y la tabla
DB_NAME = "monitoreo.db"
def crear_base_datos():
    conexion = sqlite3.connect(DB_NAME)
    cursor = conexion.cursor()
    cursor.execute('''
        CREATE TABLE IF NOT EXISTS registros (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            fecha_hora TEXT,
            cpu_uso TEXT,
            memoria_total TEXT,
            memoria_disponible TEXT,
            procesador TEXT,
            sistema_operativo TEXT
        )
    ''')
    conexion.commit()
    conexion.close()

# Obtener información del sistema
def obtener_info_sistema():
    nombre_sistema = platform.system()
    version_sistema = platform.version()
    procesador = platform.processor()
    return nombre_sistema, version_sistema, procesador

# Obtener el uso de CPU y memoria
def obtener_uso_cpu_memoria():
    cpu_uso = os.popen("wmic cpu get loadpercentage").read().strip()
    memoria_total = os.popen("systeminfo | findstr /C:\"Total Physical Memory\"").read().strip()
    memoria_disponible = os.popen("systeminfo | findstr /C:\"Available Physical Memory\"").read().strip()
    return cpu_uso, memoria_total, memoria_disponible

# Guardar los datos en la base de datos
def guardar_en_db(fecha_hora, cpu_uso, memoria_total, memoria_disponible, procesador, sistema_operativo):
    conexion = sqlite3.connect(DB_NAME)
    cursor = conexion.cursor()
    cursor.execute('''
        INSERT INTO registros (fecha_hora, cpu_uso, memoria_total, memoria_disponible, procesador, sistema_operativo)
        VALUES (?, ?, ?, ?, ?, ?)
    ''', (fecha_hora, cpu_uso, memoria_total, memoria_disponible, procesador, sistema_operativo))
    conexion.commit()
    conexion.close()

# Guardar los datos en un archivo de texto
def guardar_en_archivo(fecha_hora, cpu_uso, memoria_total, memoria_disponible, procesador, sistema_operativo):
    with open("log_monitoreo.txt", "a") as archivo:
        archivo.write(f"[{fecha_hora}] - CPU: {cpu_uso} - Memoria: {memoria_disponible} / {memoria_total} - Procesador: {procesador} - SO: {sistema_operativo}\n")

# Monitoreo automático cada 10 segundos
def monitoreo_automatico():
    try:
        for _ in range(5):
            fecha_hora = datetime.now().strftime("%Y-%m-%d %H:%M:%S")
            sistema_operativo, version_sistema, procesador = obtener_info_sistema()
            cpu_uso, memoria_total, memoria_disponible = obtener_uso_cpu_memoria()
            guardar_en_db(fecha_hora, cpu_uso, memoria_total, memoria_disponible, procesador, sistema_operativo)
            guardar_en_archivo(fecha_hora, cpu_uso, memoria_total, memoria_disponible, procesador, sistema_operativo)
            print(f"[{fecha_hora}] Monitoreo registrado con éxito.")
            time.sleep(10)
    except KeyboardInterrupt:
        print("\nMonitoreo interrumpido por el usuario.")
    except Exception as e:
        print(f"Error: {e}")

if __name__ == "__main__":
    crear_base_datos()
    monitoreo_automatico()
