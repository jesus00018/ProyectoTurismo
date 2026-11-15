public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hola sistemaGestion");

        DAOReservaCliente dao = new DAOReservaCliente();

        // Ejemplo: supone que en la base existen la reserva 1
        // y los turistas 1 y 2 (con sus personas 1 y 2)
        System.out.println("Clientes de la reserva 1:");
        for (Cliente c : dao.buscarClientesDeReserva(1)) {
            System.out.println("  " + c);
        }

        System.out.println("Reservas del cliente 1:");
        for (Reserva r : dao.buscarReservasDeCliente(1)) {
            System.out.println("  " + r);
        }
    }
}
