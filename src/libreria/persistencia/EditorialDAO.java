package libreria.persistencia;

import java.util.Objects;
import libreria.entidades.Editorial;

/**
 *
 * @author Hernan
 */
public class EditorialDAO extends DAO<Editorial> {

    @Override
    public void guardar(Editorial editorial) {
        if (editorial == null) {
            System.out.println("Debe indicar una Editorial");

        } else {
            Editorial editorialAux = new Editorial();
            editorialAux = buscarPorNombre(editorial.getNombre());
            if (editorialAux == null) {
                super.guardar(editorial);
                System.out.println("Editorial guardado exitosamenete");
            }
        }
    }

    public Editorial buscarPorNombre(String nombre) {
        conectar();
        Editorial editorial;

        try {
            editorial = (Editorial) em.createQuery("SELECT e FROM Editorial e WHERE e.nombre = :nombre")
                    .setParameter("nombre", nombre).getSingleResult();
            return editorial;
        } catch (Exception e) {
            System.out.println("La editorial no se encuentra");
            editorial = null;
            return editorial;
        } finally {
            desconectar();
        }
    }
}
