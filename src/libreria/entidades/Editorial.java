/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Hernan
 */
@Entity
public class Editorial {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre; 
    private Boolean alta;

    public Editorial() {
    }

    public Editorial(Integer id, String nombre, Boolean alta) {
        this.id = id;
        this.nombre = nombre;
        this.alta = alta;
    }

    // Es muy importante notar que no está permitido modificar la clave primaria de una entidad gestionada.
    // Si intentamos hacerlo, en el momento de hacer un commit la transacción lanzará una excepción RollbackException.
    // Para reforzar esta idea, es conveniente definir las entidades sin un método set de la clave primaria. 
    
    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getAlta() {
        return alta;
    }

    public void setAlta(Boolean alta) {
        this.alta = alta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Editorial)) {
            return false;
        }
        Editorial other = (Editorial) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

//    @Override
//    public String toString() {
//        return "libreria.entidades.Editorial[ id=" + id + " ]";
//    }

    @Override
    public String toString() {
        return "Editorial{" + "id=" + id + ", nombre=" + nombre + ", alta=" + alta + '}';
    }
}
