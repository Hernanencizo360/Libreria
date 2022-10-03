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

    public void cargarLibro() {
        try {
            AutorServicio autorServ = new AutorServicio();
            EditorialServicio editorialServ = new EditorialServicio();

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
            if (autorServ.buscarPorNombre(nombreAutor) == null) {
                autorServ.cargarAutor(nombreAutor, false);
                Autor autor = new Autor();
                autor.setNombre(nombreAutor);
                autor.setAlta(alta);
            }

            // buscar editorial por nombre.
            if (editorialServ.buscarPorNombre(editorialNombre) == null) {
                editorialServ.cargarEditorial(editorialNombre, false);
            }
            guardarLibro(titulo, anio, alta, autorServ.buscarPorNombre(nombreAutor), editorialServ.buscarPorNombre(editorialNombre));
        } catch (InputMismatchException im) {
            System.out.println(im.getMessage());
            System.out.println("Error en el tipo de dato");
            im.printStackTrace(System.out);
            System.out.println("");
            leer.next();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace(System.out);
            System.out.println("");
        }
    }

    // para mejorar el diagram el servicio deberia pasarle todos los datos al LibroDAO y dejar que el se encargue 
    // de las validaciones y creaciones correspondientes. 
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
            DAO.modificarAlta(libro);
            System.out.println("El ALTA del libro se modifico satisfactoriamente a: " + libro.getAlta());
            System.out.println("");
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

    public void mostrarLibrosPorNombreDeAutor(String nombreAutor) {
        List<Libro> libros = DAO.buscarLibrosPorAutor(nombreAutor);

        System.out.println("Libros encontrados del Autor: " + libros.size());
        libros.forEach((libro) -> {
            System.out.println(libro.toString());
        });
    }

    public void mostrarLibrosPorNombreDeEditorial(String nombreEditorial) {
        List<Libro> libros = DAO.buscarLibrosPorEditorial(nombreEditorial);

        System.out.println("Libros encontrados de la Editorial: " + libros.size());
        libros.forEach((libro) -> {
            System.out.println(libro.toString());
        });
    }
}
