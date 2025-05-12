package com.jose.dispositivos;

/**
 * Clase que representa un smartphone (teléfono móvil inteligente).
 * Hereda de Dispositivo y añade el sistema operativo como diferencia.
 */
public class Smartphone extends Dispositivo {
    private String sistemaOperativo;

    /**
     * Constructor del smartphone.
     * Aquí añadimos además el sistema operativo que usa el móvil.
     * @param marca Marca del smartphone
     * @param modelo Modelo del smartphone
     * @param sistemaOperativo Android, iOS, etc.
     */
    public Smartphone(String marca, String modelo, String sistemaOperativo) {
        super(marca, modelo);
        this.sistemaOperativo = sistemaOperativo;
    }

    @Override
    public void encender() {
        System.out.println("Encendiendo smartphone " + modelo + " con " + sistemaOperativo);
    }

    /**
     * Devuelve el sistema operativo del smartphone.
     * @return sistema operativo como texto
     */
    public String getSistemaOperativo() {
        return sistemaOperativo;
    }
}
