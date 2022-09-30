/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria.servicios;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;
import libreria.entidades.Autor;
import libreria.entidades.Editorial;

/**
 *
 * @author Hernan
 */
public class MenuServicio {

    private Scanner leer;
    private final LibroServicio libroServ;
    private AutorServicio autorServ;
    private EditorialServicio editorialServ;

    public MenuServicio() {
        this.leer = new Scanner(System.in).useDelimiter("\n");
        this.libroServ = new LibroServicio();
        this.autorServ = new AutorServicio();
        this.editorialServ = new EditorialServicio();
    }

    public void menuPrincipal() {
        boolean salir = true;
        int opcion;
        Long isbn;

        do {

            try {

                System.out.println("Bienvenido al menu: ");

                System.out.println("1-. Cargar un Autor.");
                System.out.println("2-. Cargar una Editorial.");
                System.out.println("3-. Cargar un Libro.");

                System.out.println("4-. Dar de baja/alta un libro. ");
                System.out.println("5-. Buscar un Autor por su nombre.");
                System.out.println("6-. Buscar un Libro por su ISBN.");
                System.out.println("7-. Buscar un Libro por su Titulo");
                System.out.println("8-. Buscar libro/s por su nombre de Autor. ");
                System.out.println("9-. Buscar libro/s por su nombre de Editorial. ");
                System.out.println("10-. Salir");
                opcion = leer.nextInt();

                switch (opcion) {
                    case 1:
                        System.out.println("Ingrese el nombre del Autor");
                        String nombreAutor = leer.next();
                        System.out.println("Ingrese el tipo de Alta que posee el Autor");
                        Boolean altaAutor = leer.nextBoolean();
                        cargarAutor(nombreAutor, altaAutor);
                        break;
                    case 2:
                        System.out.println("Ingrese el nombre de la Editorial");
                        String nombreEditorial = leer.next();
                        System.out.println("Ingrese el tipo de Alta que posee la Editorial");
                        Boolean altaEditorial = leer.nextBoolean();
                        cargarEditorial(nombreEditorial, altaEditorial);
                        break;
                    case 3:
                        cargarLibro();
                        break;
                    case 4:
                        System.out.println("Inserte el ISBN del libro al que desea dar de baja/alta.");
                        isbn = leer.nextLong();
                        libroServ.modificarAlta(isbn);
                        break;
                    case 5:
                        System.out.println("Ingrese el nombre del Autor que desea buscar: ");
                        String nombre = leer.next();
                        autorServ.mostarAutor(autorServ.buscarPorNombre(nombre));
                        break;
                    case 6:
                        System.out.println("Ingrese el ISBN del libro que desea buscar.");
                        isbn = leer.nextLong();
                        libroServ.mostarLibroPorIsbn(libroServ.buscarPorIsbn(isbn));
                        break;
                    case 7:
                        System.out.println("Ingrese el Titulo del Libro que desea buscar: ");
                        String titulo = leer.next();
                        libroServ.mostarLibroPorTitulo(libroServ.buscarPorTitulo(titulo));
                        break;
                    case 8:
                        System.out.println("Ingrese el Nombre del Autor que desea buscar: ");
                        String nombreAutorb = leer.next();
                        libroServ.mostrarLibrosPorNombreDeAutor(nombreAutorb);
                        break;
                    case 9:
                        System.out.println("Ingrese el Nombre de la Editorial que desea buscar: ");
                        String nombresEditorial = leer.next();
                        libroServ.mostrarLibrosPorNombreDeAutor(nombresEditorial);
                        break;
                    case 10:
                        salir = false;
                        break;
                    default:
                        System.out.println("Debe ingresar un numero entre 1 y 10.");
                }
            } catch (InputMismatchException im) {
                System.out.println(im.getMessage());
                leer.next();
                System.out.println("Debe ingresar un Número.");

            }
        } while (salir);
    }

    public void cargarAutor(String nombre, Boolean alta) {
        try {
            autorServ.guardarAutor(nombre, alta);
        } catch (InputMismatchException im) {
            System.out.println("Debe ingresar un valor Booleano: true o false.");
            System.out.println(im.getMessage());
        } catch (Exception e) {
            System.out.println("Error inesperado");
            System.out.println(e.getMessage());
        }
    }

    public void cargarLibro() {
        try {

            System.out.println("Ingrese el nombre del libro");
            String titulo = leer.next();
            System.out.println("Ingrese el año del libro");
            Integer anio = leer.nextInt();
            System.out.println("Ingrese el alta del libro: ");
            Boolean alta = leer.nextBoolean();
            System.out.println("Ingrese el nombre del Autor");
            String nombreAutor = leer.next();
            System.out.println("Ingrese el nombre de la editorial: ");
            String editorialNombre = leer.next();

            // buscar autor por nombre en el autor servicio; 
            if(autorServ.buscarPorNombre(nombreAutor) == null){
                cargarAutor(nombreAutor, false);
                Autor autor = new Autor();
                autor.setNombre(nombreAutor);
                autor.setAlta(alta);
            } 
            
            // buscar editorial por nombre.
            if(editorialServ.buscarPorNombre(editorialNombre)== null){
                cargarEditorial(editorialNombre, false);
            }
            
            

            libroServ.guardarLibro(titulo, anio, alta, autorServ.buscarPorNombre(nombreAutor), editorialServ.buscarPorNombre(editorialNombre));

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void cargarEditorial(String nombre, Boolean alta) {

        try {
            editorialServ.guardarEditorial(nombre, alta);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Error inesperado");
        }
    }

}
