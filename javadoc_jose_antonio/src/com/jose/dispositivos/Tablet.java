package com.jose.dispositivos;

/**
 * Clase que representa una tablet.
 * Se parece al smartphone pero con una propiedad nueva: stylus.
 */
public class Tablet extends Dispositivo {
    private boolean tieneStylus;

    /**
     * Constructor para la tablet.
     * Aquí indicamos si la tablet incluye stylus o no.
     * @param marca Marca de la tablet
     * @param modelo Modelo de la tablet
     * @param tieneStylus true si tiene stylus, false si no
     */
    public Tablet(String marca, String modelo, boolean tieneStylus) {
        super(marca, modelo);
        this.tieneStylus = tieneStylus;
    }

    @Override
    public void encender() {
        if (tieneStylus) {
            System.out.println("Encendiendo tablet " + modelo + " con stylus.");
        } else {
            System.out.println("Encendiendo tablet " + modelo);
        }
    }

    /**
     * Indica si la tablet tiene stylus.
     * @return true si tiene stylus
     */
    public boolean isTieneStylus() {
        return tieneStylus;
    }
}
