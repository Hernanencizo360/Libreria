package libreria.servicios;

import java.util.InputMismatchException;
import java.util.Scanner;

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

    // falta resolver opcion 10 y 11; 
    public void menuPrincipal() {
        boolean salir = true;
        int opcion;
        Long isbn;
        Integer idAutor, idEditorial;

        do {

            try {

                System.out.println("Bienvenido al menu: ");

                System.out.println("1 -. Cargar un Autor.");
                System.out.println("2 -. Cargar una Editorial.");
                System.out.println("3 -. Cargar un Libro.");

                // podria crear un sub menu de editar libro autor y editorial para luego en un switch pedir los datos que desea modificar.
                System.out.println("4 -. Dar de baja/alta un libro. ");
                System.out.println("5 -. Dar de baja/alta un autor. ");
                System.out.println("6 -. Dar de baja/alta una editorial. ");

                System.out.println("7 -. Buscar un Autor por su nombre.");
                System.out.println("8 -. Buscar un Libro por su ISBN.");
                System.out.println("9 -. Buscar un Libro por su Titulo");
                System.out.println("10-. Buscar libro/s por su nombre de Autor. ");
                System.out.println("11-. Buscar libro/s por su nombre de Editorial. ");
                System.out.println("12-. Salir");
                opcion = leer.nextInt();

                switch (opcion) {
                    case 1:
                        System.out.println("Ingrese el nombre del Autor que desea cargar");
                        String nombreAutor = leer.next();
                        System.out.println("Ingrese el tipo de Alta que posee el Autor");
                        Boolean altaAutor = leer.nextBoolean();
                        autorServ.cargarAutor(nombreAutor, altaAutor);
                        break;
                    case 2:
                        System.out.println("Ingrese el nombre de la Editorial");
                        String nombreEditorial = leer.next();
                        System.out.println("Ingrese el tipo de Alta que posee la Editorial");
                        Boolean altaEditorial = leer.nextBoolean();
                        editorialServ.cargarEditorial(nombreEditorial, altaEditorial);
                        break;
                    case 3:
                        libroServ.cargarLibro();
                        break;
                    case 4:
                        System.out.println("Inserte el ISBN del libro al que desea dar de baja/alta.");
                        isbn = leer.nextLong();
                        libroServ.modificarAlta(isbn);
                        break;
                    case 5:
                        System.out.println("Inserte el ID del Autor al que desea dar de baja/alta.");
                        idAutor = leer.nextInt();
                        autorServ.modificarAlta(idAutor);
                        break;
                    case 6:
                        System.out.println("Inserte el ID de la Editorial al que desea dar de baja/alta.");
                        idEditorial = leer.nextInt();
                        editorialServ.modificarAlta(idEditorial);
                        break;
                    case 7:
                        System.out.println("Ingrese el nombre del Autor que desea buscar: ");
                        String nombre = leer.next();
                        autorServ.mostarAutor(autorServ.buscarPorNombre(nombre));
                        break;
                    case 8:
                        System.out.println("Ingrese el ISBN del libro que desea buscar.");
                        isbn = leer.nextLong();
                        libroServ.mostarLibroPorIsbn(libroServ.buscarPorIsbn(isbn));
                        break;
                    case 9:
                        System.out.println("Ingrese el Titulo del Libro que desea buscar: ");
                        String titulo = leer.next();
                        libroServ.mostarLibroPorTitulo(libroServ.buscarPorTitulo(titulo));
                        break;
                    case 10:
                        System.out.println("Ingrese el Nombre del Autor que desea buscar: ");
                        String nombreAutorb = leer.next();
                        libroServ.mostrarLibrosPorNombreDeAutor(nombreAutorb);
                        break;
                    case 11:
                        System.out.println("Ingrese el Nombre de la Editorial que desea buscar: ");
                        String nombresEditorial = leer.next();
                        libroServ.mostrarLibrosPorNombreDeEditorial(nombresEditorial);
                        break;
                    case 12:
                        salir = false;
                        break;
                    default:
                        System.out.println("Debe ingresar un numero entre 1 y 12.");
                }
            } catch (InputMismatchException im) {
                System.out.println(im.getMessage());
                leer.next();
                System.out.println("Error el tipo de dato no cohincide");

            } catch (Exception e) {
                System.out.println("Error en el menu.");
            }
        } while (salir);
    }
}
