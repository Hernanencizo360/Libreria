/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria.servicios;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import libreria.entidades.Autor;
import libreria.entidades.Editorial;
import libreria.entidades.Libro;
import libreria.persistencia.LibroDAO;

/**
 *
 * @author Hernan
 */
public class LibroServicio {

    private Scanner leer;
    private LibroDAO DAO;

    public LibroServicio() {
        this.leer = new Scanner(System.in).useDelimiter("\n");
        this.DAO = new LibroDAO();
    }

    public void guardarLibro(String titulo, Integer anio, Boolean alta, Autor autor, Editorial editorial) {

        try {
            Libro libro = new Libro(titulo, anio, alta, autor, editorial);
            DAO.guardar(libro);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public Libro buscarPorIsbn(Long isbn) {
        Libro libro = new Libro();
        try {

            libro = DAO.buscarPorIsbn(isbn);

            if (libro.getIsbn() == null) {
                System.out.println("El libro no se encuentra en la base de datos.");
            }

        } catch (Exception e) {
            System.out.println("Error inesperado");
            System.out.println(e.getMessage());

        } finally {
            return libro;
        }
    }

    public Libro buscarPorTitulo(String titulo) {
        try {
            return DAO.buscarPorTitulo(titulo);
        } catch (Exception e) {
            System.out.println("Error inesperado");
            e.getMessage();
            return null;
        }
    }

    public void eliminarLibro() {

        boolean continua = false;

        do {
            try {
                System.out.println("Ingrese el ISBN del libro que desea eliminar: ");
                Long isbn = leer.nextLong();

                DAO.eliminar(isbn);
            } catch (InputMismatchException ex) {
                System.out.println("El ISBN es un número boleta...");
                leer.next();
                continua = true;
            } catch (Exception e) {
                System.out.println("Error inesperado");
                e.getMessage();
            }
        } while (continua);
    }

    public void modificarAlta(Long isbn) {
        Libro libro = new Libro();
        libro = DAO.buscarPorIsbn(isbn);

        if (!libro.getIsbn().equals(isbn)) {
            System.out.println("El libro no se encuentra en la base de datos");
        } else {
            if (libro.getAlta().equals(false)) {
                libro.setAlta(true);
            } else {
                libro.setAlta(false);
            }
        }
    }

    public void mostarLibroPorIsbn(Libro libro) {
        if (libro.getIsbn() == null) {
            System.out.println("El libro no existe en la BBDD");
        } else {
            System.out.println(libro.toString());
        }
    }

    public void mostarLibroPorTitulo(Libro libro) {
        if (libro.getTitulo() == null) {
            System.out.println("El libro no existe en la BBDD");
        } else {
            System.out.println(libro.toString());
        }
    }
    
    public void mostrarLibrosPorNombreDeAutor(String nombreAutor){
        List<Libro> libros = DAO.buscarLibrosPorAutor(nombreAutor);
        
        libros.forEach((libro) -> {
            System.out.println(libro.toString());
        });
    }
    
     public void mostrarLibrosPorNombreDeEditorial(String nombreEditorial){
        List<Libro> libros = DAO.buscarLibrosPorEditorial(nombreEditorial);
        
        libros.forEach((libro) -> {
            System.out.println(libro.toString());
        });
    }
    

}
