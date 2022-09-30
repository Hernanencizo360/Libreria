
package libreria.persistencia;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Hernan
 * @param <T>
 *
 */
public abstract class DAO<T> {
    protected final EntityManagerFactory EMF = Persistence.createEntityManagerFactory("LibreriaPU");
    protected EntityManager em = EMF.createEntityManager();
    
    protected void conectar(){
        if (!em.isOpen()) {
            em = EMF.createEntityManager();
        } 
    }
    
    protected void desconectar(){
        if (em.isOpen()) {
            em.close();
        }
    }
    
    protected void guardar(T objeto){
        conectar();
        em.getTransaction().begin();
        
        em.persist(objeto);
        em.getTransaction().commit();
        desconectar();
    }
    
    
    protected void editar(T objeto){
        conectar();
        em.getTransaction().begin();
       
        em.merge(objeto); // para editar usamos un merge en vez de un persist().
        em.getTransaction().commit();
        desconectar();
    }
    
    protected void eliminar(T objeto){
        conectar();
        em.getTransaction().begin();
        
        em.remove(objeto); // Remove en vez de persist();
        em.getTransaction().commit();
        desconectar();
    }
}
