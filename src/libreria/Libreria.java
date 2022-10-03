package libreria;

import libreria.servicios.MenuServicio;

/**
 *
 * @author Hernan
 */
public class Libreria {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       MenuServicio ms = new MenuServicio();
       ms.menuPrincipal();
    } 
}
