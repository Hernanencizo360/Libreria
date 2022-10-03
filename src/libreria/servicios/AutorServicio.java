package libreria.servicios;

import java.util.InputMismatchException;
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

    // pido los datos del autor 
    public void cargarAutor(String nombre, Boolean alta) {
        try {
            guardarAutor(nombre, alta);
        } catch (InputMismatchException im) {
            System.out.println("Debe ingresar un valor Booleano: true o false.");
            System.out.println(im.getMessage());
        } catch (Exception e) {
            System.out.println("Error inesperado");
            System.out.println(e.getMessage());
        }
    }

    // gurado los datos del autor.
    public void guardarAutor(String nombre, Boolean alta) {

        try {
            Autor autor = new Autor();

            autor.setNombre(nombre);
            autor.setAlta(alta);

            System.out.println("Buscando autor...");
            if (DAO.buscarPorNombre(nombre) == null) {
                DAO.guardar(autor);
            } else {
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
    

    public void modificarAlta(Integer id) {

        Autor autor = new Autor();
        autor = DAO.buscarPorId(id);

        if (!autor.getId().equals(id)) {
            System.out.println("El autor no se encuentra en la BBDD");
        } else {
            if (autor.getAlta().equals(false)) {
                autor.setAlta(true);
            } else {
                autor.setAlta(false);
            }
            DAO.modificarAlta(autor);
            System.out.println("El ALTA del Autor se modifico satisfactoriamente a: " + autor.getAlta());
            System.out.println("");
        }
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
