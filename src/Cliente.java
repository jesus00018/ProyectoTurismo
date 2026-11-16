
public class Cliente extends Persona {
    private int idCliente;
    private String patologia;
    private String incapacidad;
    private String alergia;

    public int getIdCliente() {
        return idCliente;
    }
    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
    public String getPatologia() {
        return patologia;
    }
    public void setPatologia(String patologia) {
        this.patologia = patologia;
    }
    public String getIncapacidad() {
        return incapacidad;
    }
    public void setIncapacidad(String incapacidad) {
        this.incapacidad = incapacidad;
    }
    public String getAlergia() {
        return alergia;
    }
    public void setAlergia(String alergia) {
        this.alergia = alergia;
    }
    
    public Cliente(int idPersona, String nombre, String apellido, int dni, String email, String nacionalidad,
            String provincia, String ciudad, String genero, String direccion, int idCliente, String patologia,
            String incapacidad, String alergia) {
        super(idPersona, nombre, apellido, dni, email, nacionalidad, provincia, ciudad, genero, direccion);
        this.idCliente = idCliente;
        this.patologia = patologia;
        this.incapacidad = incapacidad;
        this.alergia = alergia;
    }

    @Override
    public String toString() {
        // Solo la cantidad de reservas, para no entrar en un bucle infinito
        // (la reserva imprime clientes y el cliente imprime reservas)
        return "Cliente{idCliente=" + idCliente + ", nombre=" + getNombre()
                + ", apellido=" + getApellido() + ", patologia=" + patologia
                + ", incapacidad=" + incapacidad + ", alergia=" + alergia;
    }
}
