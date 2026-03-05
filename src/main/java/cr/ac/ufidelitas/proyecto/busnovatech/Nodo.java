package cr.ac.ufidelitas.proyecto.busnovatech;

/**
 *
 * @author gerald-hf
 */

//Nodo para enlazar
public class Nodo<T> {
    
    private T dato;
    private Nodo<T> siguiente;

    public Nodo() { }

    public Nodo(T dato) {
        this.dato = dato;
        this.siguiente = null;
    }

    public T getDato() { return dato; }
    public void setDato(T dato) { this.dato = dato; }

    public Nodo<T> getSiguiente() { return siguiente; }
    public void setSiguiente(Nodo<T> siguiente) { this.siguiente = siguiente; }

    @Override
    public String toString() {
        return dato.toString();
    }
}
