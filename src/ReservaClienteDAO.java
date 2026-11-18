
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ReservaClienteDAO {

    public void mostrarReservaCliente() {

        // Objeto para ejecutar la consulta en la base de datos
        AcessoDatos accesoBD = null;
        Connection con = null;
        Statement sentencia = null;
        ResultSet rs = null;

        // Consulta SQL
        String query = "SELECT " +
            "r.idRESERVA AS ID_Reserva, " +
            "r.cod_reserva AS Codigo_Reserva, " +

            "p.nombre AS Nombre_Turista, " +
            "p.apellido AS Apellido_Turista, " +
            "p.DNI AS DNI_Turista, " +

            "t.nombre AS Nombre_Tour, " +
            "t.precio AS Precio_Por_Persona, " +

            "r.cantidad_personas AS Cantidad_Personas, " +

            "(t.precio * r.cantidad_personas) AS Precio_Total, " +

            "pr.fecha AS Fecha_Tour, " +
            "pr.horario AS Horario_Tour, " +

            "pg.nombre AS Nombre_Guia, " +
            "pg.apellido AS Apellido_Guia, " +

            "r.estado AS Estado_Reserva " +

            "FROM RESERVA r " +

            "INNER JOIN TURISTA tu " +
            "ON r.TURISTA_TITULAR_idTURISTA = tu.idTURISTA " +

            "INNER JOIN PERSONA p " +
            "ON tu.PERSONA_idPERSONA = p.idPERSONA " +

            "INNER JOIN PROGRAMACIÓN_RESERVA pr " +
            "ON r.`PROGRAMACIÓN_RESERVA_idProgramacion` = pr.idProgramacion " +

            "INNER JOIN TOUR t " +
            "ON pr.TOUR_idTOUR = t.idTOUR " +

            "INNER JOIN EMPLEADO e " +
            "ON pr.EMPLEADO_idEMPLEADO = e.idEMPLEADO " +
            "AND pr.EMPLEADO_PERSONA_idPERSONA = e.PERSONA_idPERSONA " +

            "INNER JOIN PERSONA pg " +
            "ON e.PERSONA_idPERSONA = pg.idPERSONA " +

            "ORDER BY pr.fecha DESC, p.apellido";

        // Ejecutar la consulta y obtener la información
        try {

            // Instancio un objeto de acceso a datos
            accesoBD = new AcessoDatos();

            // Obtener la conexión
            con = accesoBD.getConexion();

            sentencia = con.createStatement();

            // Ejecutar la consulta
            rs = sentencia.executeQuery(query);

            System.out.println("\n========== RESERVAS DE TURISTAS ==========");

            boolean hayResultados = false;

            while (rs.next()) {

                hayResultados = true;

                System.out.println("-----------------------------------------------");

                System.out.println("ID Reserva: " +
                    rs.getInt("ID_Reserva"));

                System.out.println("Código de reserva: " +
                    rs.getString("Codigo_Reserva"));

                System.out.println("Nombre del turista: " +
                    rs.getString("Nombre_Turista"));

                System.out.println("Apellido del turista: " +
                    rs.getString("Apellido_Turista"));

                System.out.println("DNI: " +
                    rs.getString("DNI_Turista"));

                System.out.println("Tour reservado: " +
                    rs.getString("Nombre_Tour"));

                System.out.println("Precio por persona: $" +
                    rs.getBigDecimal("Precio_Por_Persona"));

                System.out.println("Cantidad de personas: " +
                    rs.getInt("Cantidad_Personas"));

                System.out.println("Precio total: $" +
                    rs.getBigDecimal("Precio_Total"));

                System.out.println("Fecha del tour: " +
                    rs.getDate("Fecha_Tour"));

                System.out.println("Horario: " +
                    rs.getString("Horario_Tour"));

                System.out.println("Guía asignado: " +
                    rs.getString("Nombre_Guia") + " " +
                    rs.getString("Apellido_Guia"));

                System.out.println("Estado de la reserva: " +
                    rs.getString("Estado_Reserva"));
            }

            if (!hayResultados) {
                System.out.println("No hay reservas registradas.");
            }

            System.out.println("-----------------------------------------------");

        } catch (SQLException e) {

            System.err.println("Error al consultar las reservas: " +
                e.getMessage());

        } finally {

            try {

                // Cerrar ResultSet
                if (rs != null) rs.close();

                // Cerrar sentencia
                if (sentencia != null) sentencia.close();

                // Cerrar conexión
                if (con != null) con.close();

            } catch (SQLException e) {

                System.err.println("Error al cerrar conexión: " +
                    e.getMessage());
            }
        }
    }
}
