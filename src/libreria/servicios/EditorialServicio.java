/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria.servicios;

import libreria.entidades.Editorial;
import libreria.persistencia.EditorialDAO;

/**
 *
 * @author Hernan
 */
public class EditorialServicio {

    private EditorialDAO DAO;

    public EditorialServicio() {
        this.DAO = new EditorialDAO();
    }

    

    public void guardarEditorial(String nombre, Boolean alta) {
        Editorial editorial = new Editorial();

        editorial.setNombre(nombre);
        editorial.setAlta(alta);

        if (DAO.buscarPorNombre(nombre)== null) {
             DAO.guardar(editorial);
        } else {
            System.out.println("El nombre de la editorial ya existe en la BBDD");
        }
    }

    public Editorial buscarPorNombre(String nombre) {
        return DAO.buscarPorNombre(nombre);
    }

}
