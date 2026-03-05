
package cr.ac.ufidelitas.proyecto.busnovatech;

/**
 *
 * @author samu-mena
 */
public class NodoBus {
    public Bus bus;
    public NodoBus siguiente;

    public NodoBus(Bus bus) {
        this.bus = bus;
        this.siguiente = null;
    }
}
