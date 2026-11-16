import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Reserva {

    private int idReserva;
    private int codReserva;
    private int cantidadPersonas;
    private String estado;

    private LocalDate fechaProgramacion;
    private int idTour;

    // Relación muchos a muchos con Cliente (tabla RESERVA_TURISTA):
    // una reserva puede tener muchos clientes
    private List<Cliente> clientes = new ArrayList<>();

    // Agrega el cliente a la reserva y la reserva al cliente (los dos lados)
    public void agregarCliente(Cliente cliente) {
        if (cliente != null && !clientes.contains(cliente)) {
            clientes.add(cliente);
            cliente.agregarReserva(this);
        }
    }

    public void quitarCliente(Cliente cliente) {
        if (clientes.remove(cliente)) {
            cliente.quitarReserva(this);
        }
    }

    // Devuelve una copia: la lista solo se cambia con agregar/quitar
    public List<Cliente> getClientes() {
        return new ArrayList<>(clientes);
    }

    public int getIdReserva() {
        return idReserva;
    }
    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }
    public int getCodReserva() {
        return codReserva;
    }
    public void setCodReserva(int codReserva) {
        this.codReserva = codReserva;
    }
    public int getCantidadPersonas() {
        return cantidadPersonas;
    }
    public void setCantidadPersonas(int cantidadPersonas) {
        this.cantidadPersonas = cantidadPersonas;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public LocalDate getFechaProgramacion() {
        return fechaProgramacion;
    }
    public void setFechaProgramacion(LocalDate fechaProgramacion) {
        this.fechaProgramacion = fechaProgramacion;
    }
    public int getIdTour() {
        return idTour;
    }
    public void setIdTour(int idTour) {
        this.idTour = idTour;
    }
    public Reserva(int idReserva, int codReserva, int cantidadPersonas, String estado, LocalDate fechaProgramacion,
            int idTour) {
        this.idReserva = idReserva;
        this.codReserva = codReserva;
        this.cantidadPersonas = cantidadPersonas;
        this.estado = estado;
        this.fechaProgramacion = fechaProgramacion;
        this.idTour = idTour;
    }

    @Override
    public String toString() {
        String nombres = "";
        for (Cliente c : clientes) {
            if (!nombres.isEmpty()) nombres += ", ";
            nombres += c.getNombre() + " " + c.getApellido();
        }
        return "Reserva{idReserva=" + idReserva + ", codReserva=" + codReserva
                + ", cantidadPersonas=" + cantidadPersonas + ", estado=" + estado
                + ", fechaProgramacion=" + fechaProgramacion + ", idTour=" + idTour
                + ", clientes=[" + nombres + "]}";
    }
}
