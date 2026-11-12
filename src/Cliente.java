public class Cliente extends Persona {
    private int idCliente;
    private String patalogia;
    private String incapacidad;
    private String alergia;
    
    public int getIdCliente() {
        return idCliente;
    }
    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
    public String getPatalogia() {
        return patalogia;
    }
    public void setPatalogia(String patalogia) {
        this.patalogia = patalogia;
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
            String provincia, String ciudad, String genero, String direccion, int idCliente, String patalogia,
            String incapacidad, String alergia) {
        super(idPersona, nombre, apellido, dni, email, nacionalidad, provincia, ciudad, genero, direccion);
        this.idCliente = idCliente;
        this.patalogia = patalogia;
        this.incapacidad = incapacidad;
        this.alergia = alergia;
    }

    
    
}