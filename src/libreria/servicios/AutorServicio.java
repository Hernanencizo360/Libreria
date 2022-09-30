/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria.servicios;

import libreria.entidades.Autor;
import libreria.persistencia.AutorDAO;

/**
 *
 * @author Hernan
 */
public class AutorServicio {

    private AutorDAO DAO;

    public AutorServicio() {
        this.DAO = new AutorDAO();
    }

    public void guardarAutor(String nombre, Boolean alta) {

        try {
            Autor autor = new Autor();
            
            autor.setNombre(nombre);
            autor.setAlta(alta);
 
            if(DAO.buscarPorNombre(nombre) == null){
                DAO.guardar(autor);
            }else{
                System.out.println("El nombre del Autor ya existe en la BBDD.");
            }    
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // buscar en al autor por nombre en el DAO
    public Autor buscarPorNombre(String nombre) {
        return DAO.buscarPorNombre(nombre);
    }

    public void mostarAutor(Autor autor) {
        //Autor autor = new Autor();
        //autor = buscarPorNombre(nombre);
        if (autor.getNombre() != null) {
            System.out.println(autor.toString());
        } else {
            System.out.println("El nombre del Autor no existe en la BBDD");
        }
    }

}
