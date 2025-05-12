package com.jose.dispositivos;

/**
 * Esta clase representa un dispositivo electrónico genérico.
 * Se utiliza como clase base para otros dispositivos más concretos.
 */
public abstract class Dispositivo {
    protected String marca;
    protected String modelo;

    /**
     * Constructor para la clase Dispositivo.
     * Aquí asignamos marca y modelo al crear el objeto.
     * @param marca Marca del dispositivo
     * @param modelo Modelo del dispositivo
     */
    public Dispositivo(String marca, String modelo) {
        this.marca = marca;
        this.modelo = modelo;
    }

    /**
     * Método abstracto que todos los dispositivos deben implementar para encenderse.
     */
    public abstract void encender();

    /**
     * Devuelve una cadena con la marca y modelo del dispositivo.
     * @return marca y modelo como texto
     */
    public String obtenerInfo() {
        return marca + " " + modelo;
    }
}
