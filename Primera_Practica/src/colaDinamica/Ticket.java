package colaDinamica;

public class Ticket {

    //atributos

    private byte id;
    private String descripcion;
    private String nombreCompleto;
    private String fechaCreacion;
    private String fechaResolucion = null;
    private byte prioridad;

    //metodos / consturctor

    public Ticket(byte id, String descripcion, String nombreCompleto,String fechaCreacion, String fechaResolucion, byte prioridad){
        this.id = id;
        this.descripcion = descripcion;
        this.nombreCompleto = nombreCompleto;
        this.fechaCreacion = fechaCreacion;
        this.fechaResolucion = fechaResolucion;
        this.prioridad = prioridad;

    }


    public byte getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public String getFechaResolucion() {
        return fechaResolucion;
    }

    public byte getPrioridad() {
        return prioridad;
    }

    public void setId(byte id) {
        this.id = id;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public void setFechaResolucion(String fechaResolucion) {
        this.fechaResolucion = fechaResolucion;
    }

    public void setPrioridad(byte prioridad) {
        this.prioridad = prioridad;
    }

    @Override
    public String toString() {
        return "Tiket{" +
                "id=" + id +
                ", descripcion='" + descripcion + '\'' +
                ", nombreCompleto='" + nombreCompleto + '\'' +
                ", fechaCreacion='" + fechaCreacion + '\'' +
                ", fechaResolucion='" + fechaResolucion + '\'' +
                '}';
    }
}
