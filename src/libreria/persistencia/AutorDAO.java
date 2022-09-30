/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria.persistencia;

import libreria.entidades.Autor;

/**
 *
 * @author Hernan
 */
public class AutorDAO extends DAO<Autor> {

    @Override
    public void guardar(Autor autor) {
        if (autor == null) {
            System.out.println("Debe indicar un Autor");
        } else {
            super.guardar(autor);
            System.out.println("Autor guardado exitosamenete");

        }
    }

    // si no encuentro nada devuelvo un Autor null;
    public Autor buscarPorNombre(String nombre) {
        conectar();
        Autor autor;

        try {
            autor = (Autor) em.createQuery("SELECT a FROM Autor a WHERE a.nombre = :nombre")
                    .setParameter("nombre", nombre).getSingleResult();
            return autor;
        } catch (Exception e) {
            System.out.println("No hay autor con ese nombre");
            autor = null;
            return autor;
        } finally {
            desconectar();
        }
    }
}
