
package cr.ac.ufidelitas.proyecto.busnovatech;

/**
 *
 * @author samu-mena
 */
public class Bus {
    private String idBus;
    private String tipo;
    private String estado;

    public Bus(String idBus, String tipo) {
        this.idBus = idBus;
        this.tipo = tipo;
        this.estado = "Disponible";
    }

    public String getIdBus() { return idBus; }
    public String getTipo() { return tipo; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
