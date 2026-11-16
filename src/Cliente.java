import java.util.ArrayList;
import java.util.List;

public class Cliente extends Persona {
    private int idCliente;
    private String patalogia;
    private String incapacidad;
    private String alergia;

    // Relación muchos a muchos con Reserva (tabla RESERVA_TURISTA):
    // un cliente puede estar en muchas reservas
    private List<Reserva> reservas = new ArrayList<>();

    // Agrega la reserva al cliente y el cliente a la reserva (los dos lados)
    public void agregarReserva(Reserva reserva) {
        if (reserva != null && !reservas.contains(reserva)) {
            reservas.add(reserva);
            reserva.agregarCliente(this);
        }
    }

    public void quitarReserva(Reserva reserva) {
        if (reservas.remove(reserva)) {
            reserva.quitarCliente(this);
        }
    }

    // Devuelve una copia: la lista solo se cambia con agregar/quitar
    public List<Reserva> getReservas() {
        return new ArrayList<>(reservas);
    }

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

    @Override
    public String toString() {
        // Solo la cantidad de reservas, para no entrar en un bucle infinito
        // (la reserva imprime clientes y el cliente imprime reservas)
        return "Cliente{idCliente=" + idCliente + ", nombre=" + getNombre()
                + ", apellido=" + getApellido() + ", patalogia=" + patalogia
                + ", incapacidad=" + incapacidad + ", alergia=" + alergia
                + ", reservas=" + reservas.size() + "}";
    }
}
