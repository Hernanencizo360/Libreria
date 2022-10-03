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

    public void cargarEditorial(String nombre, Boolean alta) {
        try {
            guardarEditorial(nombre, alta);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Error inesperado");
        }
    }

    public void guardarEditorial(String nombre, Boolean alta) {
        Editorial editorial = new Editorial();

        editorial.setNombre(nombre);
        editorial.setAlta(alta);

        if (DAO.buscarPorNombre(nombre) == null) {
            DAO.guardar(editorial);
        } else {
            System.out.println("El nombre de la editorial ya existe en la BBDD");
        }
    }

    public Editorial buscarPorNombre(String nombre) {
        return DAO.buscarPorNombre(nombre);
    }

    public void modificarAlta(Integer id) {

        Editorial editorial = new Editorial();
        editorial = DAO.buscarPorId(id);

        if (!editorial.getId().equals(id)) {
            System.out.println("La Editorial no se encuentra en la BBDD");
        } else {
            if (editorial.getAlta().equals(false)) {
                editorial.setAlta(true);
            } else {
                editorial.setAlta(false);
            }
            DAO.modificarAlta(editorial);
            System.out.println("El ALTA de la Editorial se modifico satisfactoriamente a: " + editorial.getAlta());
            System.out.println("");
        }
    }
}
