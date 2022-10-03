package libreria.persistencia;

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
            System.out.println("Buscando editorial...");
            editorialAux = buscarPorNombre(editorial.getNombre());
            if (editorialAux == null) {
                super.guardar(editorial);
                System.out.println("-- Editorial guardado exitosamenete --");
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
            System.out.println("La editorial no se encuentra en la BBDD");
            editorial = null;
            return editorial;
        } finally {
            desconectar();
        }
    }

    public Editorial buscarPorId(Integer id) {
        conectar();
        Editorial editorial;
        try {

            editorial = (Editorial) em.createQuery("SELECT e FROM Editorial e WHERE e.id = :id")
                    .setParameter("id", id).getSingleResult();

            return editorial;
        } catch (Exception e) {
            editorial = null;
            System.out.println("No se encontro la Editorial.");
            return editorial;
        } finally {
            desconectar();
        }
    }

    public void modificarAlta(Editorial editorial) {
        // le paso el objeto la clase padre es la encargada de realizar los cambios pertinentes. 
        super.editar(editorial);
    }
}
