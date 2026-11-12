public class Empleado extends Persona{
    private int idEmpleado;
    private String usuario;
    private String contraseña;

    public int getIdEmpleado() {
        return idEmpleado;
    }
    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }
    public String getUsuario() {
        return usuario;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    public String getContraseña() {
        return contraseña;
    }
    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
    
    public Empleado(int idPersona, String nombre, String apellido, int dni, String email, String nacionalidad,
            String provincia, String ciudad, String genero, String direccion, int idEmpleado, String usuario,
            String contraseña) {
        super(idPersona, nombre, apellido, dni, email, nacionalidad, provincia, ciudad, genero, direccion);
        this.idEmpleado = idEmpleado;
        this.usuario = usuario;
        this.contraseña = contraseña;
    }

    
}