public class Tour {
    private String nombre;
    private String descripcion;
    private String lugar;
    private int duracion;
    private double precio;
    private String disponibilidad;
    private int idAgencia;
    
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public String getLugar() {
        return lugar;
    }
    public void setLugar(String lugar) {
        this.lugar = lugar;
    }
    public int getDuracion() {
        return duracion;
    }
    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }
    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    public String getDisponibilidad() {
        return disponibilidad;
    }
    public void setDisponibilidad(String disponibilidad) {
        this.disponibilidad = disponibilidad;
    }
    public int getIdAgencia() {
        return idAgencia;
    }
    public void setIdAgencia(int idAgencia) {
        this.idAgencia = idAgencia;
    }
    
    public Tour(String nombre, String descripcion, String lugar, int duracion, double precio, String disponibilidad,
            int idAgencia) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.lugar = lugar;
        this.duracion = duracion;
        this.precio = precio;
        this.disponibilidad = disponibilidad;
        this.idAgencia = idAgencia;
    }


}