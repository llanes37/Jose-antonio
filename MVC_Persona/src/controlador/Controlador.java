package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.Persona;
import modelo.PersonaDAO;
import modelo.Poblacion;
import modelo.PoblacionDAO;
import vista.VentanaPrincipal;

public class Controlador {

    private PoblacionDAO poblacionDAO;
    private VentanaPrincipal ventana;
    private PersonaDAO personaDAO;

    public Controlador() {
        poblacionDAO = new PoblacionDAO();
        ventana = new VentanaPrincipal();
        personaDAO = new PersonaDAO();
        //cargamos poblaciones
        this.ventana.addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent e) {
                //cargar las poblaciones
                cargarPoblaciones();
            }

        });
        this.ventana.setVisible(true);

        //REgistrar la accion inserta
        ventana.btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombre = ventana.txtNombre.getText();
                String strEdad = ventana.txtEdad.getText();
                int edad = Integer.parseInt(strEdad);
                int idPoblacion = ventana.poblaciones.getSelectedIndex();
                System.out.println(idPoblacion);
                Persona persona = new Persona(nombre, edad, (idPoblacion + 1));
                if (personaDAO.insertar(persona)) {
                    JOptionPane.showMessageDialog(ventana, "Registro insertado", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(ventana, "Error", "Error", JOptionPane.ERROR_MESSAGE);
                }
                limpiarCampos();
            }

        });

        //funcion buscar
        ventana.btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombreABuscar = ventana.txtBusqueda.getText();
                Persona persona = personaDAO.buscar(nombreABuscar);
                if (persona != null) {
                    JOptionPane.showMessageDialog(ventana, "Registro encontrado", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    ventana.txtNombre.setText(persona.getNombre());
                    ventana.txtEdad.setText(String.valueOf(persona.getEdad()));
                    cargarPoblaciones();
                    ventana.poblaciones.setSelectedIndex(persona.getIdPoblacion()-1);
                } else {
                    JOptionPane.showMessageDialog(ventana, "Registro NO encontrado", "Error", JOptionPane.ERROR_MESSAGE);
                    ventana.txtBusqueda.setText("");
                }
                ventana.txtBusqueda.setText("");
                
            }
        });
        //funcion eliminar
         this.ventana.btnEliminar.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){
             String nombreABuscar = ventana.txtEliminar.getText();
             int res = personaDAO.eliminar(nombreABuscar);
             if(res > 0) JOptionPane.showMessageDialog(ventana, "Registro eliminado", "Éxito", JOptionPane.INFORMATION_MESSAGE);
             else   JOptionPane.showMessageDialog(ventana, "Registro no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
             ventana.txtBusqueda.setText("");
             ventana.txtEliminar.setText("");

         }
        
        });
    }

    private void limpiarCampos() {
        ventana.txtNombre.setText("");
        ventana.txtEdad.setText("");
         
        cargarPoblaciones();
    }

    private void cargarPoblaciones() {
        ArrayList<Poblacion> poblaciones = poblacionDAO.getPoblaciones();
        ventana.poblaciones.removeAllItems();
        for (Poblacion p : poblaciones) {
            ventana.poblaciones.addItem(p.getNombre());
        }
    }

}
