import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DAOReservaCliente {

    // ==================== AGREGAR ====================
    public void agregarCliente(Reserva reserva, Cliente cliente) {
        String query = "INSERT INTO RESERVA_TURISTA "
                + "(RESERVA_idRESERVA, TURISTA_idTURISTA, TURISTA_PERSONA_idPERSONA) "
                + "VALUES (?, ?, ?)";

        Connection con = null;
        PreparedStatement sentencia = null;
        try {
            con = new AcessoDatos().getConexion();
            sentencia = con.prepareStatement(query);
            sentencia.setInt(1, reserva.getIdReserva());
            sentencia.setInt(2, cliente.getIdCliente());
            sentencia.setInt(3, cliente.getIdPersona());
            sentencia.executeUpdate();

            // Si se guardó en la base, se actualizan las listas en Java
            reserva.agregarCliente(cliente);

        } catch (SQLException e) {
            System.err.println("Error al AGREGAR el cliente a la reserva");
            e.printStackTrace();
        } finally {
            cerrar(null, sentencia, con);
        }
    }

    // ==================== QUITAR ====================
    public void quitarCliente(Reserva reserva, Cliente cliente) {
        String query = "DELETE FROM RESERVA_TURISTA "
                + "WHERE RESERVA_idRESERVA = ? AND TURISTA_idTURISTA = ? "
                + "AND TURISTA_PERSONA_idPERSONA = ?";

        Connection con = null;
        PreparedStatement sentencia = null;
        try {
            con = new AcessoDatos().getConexion();
            sentencia = con.prepareStatement(query);
            sentencia.setInt(1, reserva.getIdReserva());
            sentencia.setInt(2, cliente.getIdCliente());
            sentencia.setInt(3, cliente.getIdPersona());
            sentencia.executeUpdate();

            reserva.quitarCliente(cliente);

        } catch (SQLException e) {
            System.err.println("Error al QUITAR el cliente de la reserva");
            e.printStackTrace();
        } finally {
            cerrar(null, sentencia, con);
        }
    }

    // ============ CLIENTES DE UNA RESERVA ============
    public List<Cliente> buscarClientesDeReserva(int idReserva) {
        String query = "SELECT p.*, t.* FROM RESERVA_TURISTA rt "
                + "JOIN TURISTA t ON t.idTURISTA = rt.TURISTA_idTURISTA "
                + " AND t.PERSONA_idPERSONA = rt.TURISTA_PERSONA_idPERSONA "
                + "JOIN PERSONA p ON p.idPERSONA = t.PERSONA_idPERSONA "
                + "WHERE rt.RESERVA_idRESERVA = ?";

        List<Cliente> lista = new ArrayList<>();
        Connection con = null;
        PreparedStatement sentencia = null;
        ResultSet rs = null;
        try {
            con = new AcessoDatos().getConexion();
            sentencia = con.prepareStatement(query);
            sentencia.setInt(1, idReserva);
            rs = sentencia.executeQuery();

            while (rs.next()) {
                // Columnas de PERSONA: 1 a 10 | Columnas de TURISTA: 11 a 15
                Cliente c = new Cliente(
                        rs.getInt(1),      // idPERSONA
                        rs.getString(2),   // nombre
                        rs.getString(3),   // apellido
                        rs.getInt(4),      // DNI
                        rs.getString(5),   // email
                        rs.getString(6),   // nacionalidad
                        rs.getString(7),   // provincia
                        rs.getString(8),   // ciudad
                        rs.getString(9),   // género
                        rs.getString(10),  // dirección
                        rs.getInt(11),     // idTURISTA
                        rs.getString(12),  // patalogía
                        rs.getString(13),  // incapacidad
                        rs.getString(14)); // alergía
                lista.add(c);
            }
        } catch (SQLException e) {
            System.err.println("Error al CARGAR los clientes de la reserva");
            e.printStackTrace();
        } finally {
            cerrar(rs, sentencia, con);
        }
        return lista;
    }

    // ============ RESERVAS DE UN CLIENTE ============
    public List<Reserva> buscarReservasDeCliente(int idCliente) {
        String query = "SELECT r.* FROM RESERVA_TURISTA rt "
                + "JOIN RESERVA r ON r.idRESERVA = rt.RESERVA_idRESERVA "
                + "WHERE rt.TURISTA_idTURISTA = ?";

        List<Reserva> lista = new ArrayList<>();
        Connection con = null;
        PreparedStatement sentencia = null;
        ResultSet rs = null;
        try {
            con = new AcessoDatos().getConexion();
            sentencia = con.prepareStatement(query);
            sentencia.setInt(1, idCliente);
            rs = sentencia.executeQuery();

            while (rs.next()) {
                Date fecha = rs.getDate(5);
                Reserva r = new Reserva(
                        rs.getInt(1),      // idRESERVA
                        rs.getInt(2),      // cod_reserva
                        rs.getInt(3),      // cantidad_personas
                        rs.getString(4),   // estado
                        fecha != null ? fecha.toLocalDate() : null, // PROGRAMACIÓN_RESERVA_fecha
                        rs.getInt(6));     // PROGRAMACIÓN_RESERVA_TOUR_idTOUR
                lista.add(r);
            }
        } catch (SQLException e) {
            System.err.println("Error al CARGAR las reservas del cliente");
            e.printStackTrace();
        } finally {
            cerrar(rs, sentencia, con);
        }
        return lista;
    }

    // Carga en el objeto Reserva los clientes que tiene en la base
    public void cargarClientes(Reserva reserva) {
        for (Cliente c : buscarClientesDeReserva(reserva.getIdReserva())) {
            reserva.agregarCliente(c);
        }
    }

    // ==================== CERRAR ====================
    private void cerrar(ResultSet rs, PreparedStatement sentencia, Connection con) {
        try {
            if (rs != null) rs.close();
            if (sentencia != null) sentencia.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            System.err.println("Error al cerrar conexion");
        }
    }
}
