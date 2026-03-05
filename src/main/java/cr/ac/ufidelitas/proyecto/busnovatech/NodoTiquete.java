package cr.ac.ufidelitas.proyecto.busnovatech;

/**
 *
 * @author gerald-hf
 */
public class NodoTiquete {
    //Usamos private para proteger la informacion
    private String nombre;
    private int id;
    private int edad;
    private double moneda; // double para que sea precios de 9.99 por ejenmplo
    private String horaCompra;
    private String horaAbordaje;
    private String servicio;
    private String tipoBus;
    
    
    //Constructor 
    public NodoTiquete(String nombre,int id, int edad, double moneda, String horaCompra,
                       String horaAbordaje, String servicio, String tipoBus){
        this.nombre = nombre;
        this.id = id;
        this.edad = edad;
        this.moneda = moneda;
        this.horaCompra = horaCompra;
        this.horaAbordaje = horaAbordaje;
        this.servicio = servicio;
        this.tipoBus = tipoBus;
    }

    //Getters
    public String getNombre() {
        return nombre;
    }

    public int getId() {
        return id;
    }

    public int getEdad() {
        return edad;
    }

    public double getMoneda() {
        return moneda;
    }

    public String getHoraCompra() {
        return horaCompra;
    }

    public String getHoraAbordaje() {
        return horaAbordaje;
    }

    public String getServicio() {
        return servicio;
    }

    public String getTipoBus() {
        return tipoBus;
    }

    
    
    //Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setMoneda(double moneda) {
        this.moneda = moneda;
    }

    public void setHoraCompra(String horaCompra) {
        this.horaCompra = horaCompra;
    }

    public void setHoraAbordaje(String horaAbordaje) {
        this.horaAbordaje = horaAbordaje;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public void setTipoBus(String tipoBus) {
        this.tipoBus = tipoBus;
    }

    //toString para reimprimir de manera senscilla 
    @Override
    public String toString() {
        return "NodoTiquete{" +
                "nombre='" + nombre + '\'' +
                ", id=" + id +
                ", edad=" + edad +
                ", moneda=" + moneda +
                ", horaCompra='" + horaCompra + '\'' +
                ", horaAbordaje='" + horaAbordaje + '\'' +
                ", servicio='" + servicio + '\'' +
                ", tipoBus='" + tipoBus + '\'' +
                '}';
        
        
    }
}
