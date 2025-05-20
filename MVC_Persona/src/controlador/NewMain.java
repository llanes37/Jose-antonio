/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package controlador;

import java.util.ArrayList;
import modelo.Poblacion;
import modelo.PoblacionDAO;

/**
 *
 * @author daw
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        PoblacionDAO pdao = new PoblacionDAO();
        ArrayList<Poblacion> poblaciones = pdao.getPoblaciones();
        for(Poblacion p: poblaciones){
            System.out.println(p);
        }
    }
    
}
