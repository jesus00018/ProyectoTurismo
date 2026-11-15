public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hola sistemaGestion");

        DAOReservaCliente dao = new DAOReservaCliente();

        System.out.println("Clientes de la reserva :");
        for (Cliente c : dao.buscarClientesDeReserva(1)) {
            System.out.println("  " + c);
        }

        System.out.println("Reservas del cliente 1:");
        for (Reserva r : dao.buscarReservasDeCliente(1)) {
            System.out.println("  " + r);
        }
    }
}
