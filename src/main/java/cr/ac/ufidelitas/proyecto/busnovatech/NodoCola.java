
package cr.ac.ufidelitas.proyecto.busnovatech;

/**
 *
 * @author samu-mena
 */
public class NodoCola {
    public Bus bus;          // El bus original
    public int cantidad;     // Cantidad en cola
    public NodoCola siguiente;

    public NodoCola(Bus bus, int cantidad) {
        this.bus = bus;
        this.cantidad = cantidad;
        this.siguiente = null;
    }
}
