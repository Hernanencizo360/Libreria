/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria.persistencia;

import java.util.List;
import java.util.Objects;
import libreria.entidades.Autor;
import libreria.entidades.Libro;

/**
 *
 * @author Hernan
 */
public class LibroDAO extends DAO<Libro> {

    @Override
    public void guardar(Libro libro) {
        if (libro == null) {
            System.out.println("Debe indicar un libro");

        } else {
            Libro libroAux = new Libro();
            libroAux = buscarPorIsbn(libro.getIsbn());
            if (libroAux == null) {
                super.guardar(libro);
                System.out.println("Libro guardado exitosamenete");   
            } else {
                 System.out.println("El libro ya existe");
            }
        }
    }

    // todas las busquedas pueden lanzar getSingleResult() si no se encuentra el dato que se busca
    public Libro buscarPorIsbn(Long isbn) {
        conectar();
        Libro libro;
        try {
            
            libro = (Libro) em.createQuery("SELECT lib FROM Libro lib WHERE lib.isbn = :isbn")
                    .setParameter("isbn", isbn).getSingleResult();
            
            return libro;
        } catch (Exception e) {
            libro = null;
            System.out.println("No se encontro ese libro.");
            return libro;
        }finally{
            desconectar();
        }
    }

    public Libro buscarPorTitulo(String titulo) {
        conectar();
        Libro libro = (Libro) em.createQuery("SELECT lib FROM Libro lib WHERE lib.titulo = :titulo")
                .setParameter("titulo", titulo).getSingleResult();
        desconectar();
        return libro;
    }

    public Libro buscarPorAutor(Autor autor) {
        conectar();
        // esto creo que deberia hacer una subconsulta
        Libro libro = (Libro) em.createQuery("SELECT lib FROM Libro lib WHERE lib.autor = :autor")
                .setParameter("autor", autor).getSingleResult();
        desconectar();
        return libro;
    }

    public void eliminar(Long isbn) {
        Libro libro = buscarPorIsbn(isbn);
        // le paso el objeto la clase padre es la encargada de realizar los cambios pertinentes. 
        super.eliminar(libro);
    }

    public List<Libro> buscarLibrosPorAutor(String nombreAutor) {
        conectar();
        //Opcion 1 sin JOIN
        //        List<Persona> personas = em.createQuery("SELECT p FROM Persona p WHERE p.direccion.pais LIKE :pais AND p.direccion.provincia LIKE :provincia ")
        //                .setParameter("pais", pais).setParameter("provincia", provincia).getResultList();
        //Opcion 2 con JOIN
        List<Libro> libros = em.createQuery("SELECT lib FROM Libro lib JOIN lib.autor a WHERE a.nombre LIKE :nombreAutor")
                .setParameter("nombre", nombreAutor).setParameter("nombre", nombreAutor).getResultList();
        desconectar();
        return libros;
    }

    public List<Libro> buscarLibrosPorEditorial(String nombreEditorial) {
        conectar();
        //Opcion 1 sin JOIN
        //        List<Persona> personas = em.createQuery("SELECT p FROM Persona p WHERE p.direccion.pais LIKE :pais AND p.direccion.provincia LIKE :provincia ")
        //                .setParameter("pais", pais).setParameter("provincia", provincia).getResultList();
        //Opcion 2 con JOIN
        List<Libro> libros = em.createQuery("SELECT lib FROM Libro lib JOIN lib.editorial e WHERE e.nombre LIKE :nombreEditorial")
                .setParameter("nombre", nombreEditorial).setParameter("nombre", nombreEditorial).getResultList();
        desconectar();
        return libros;
    }
}
