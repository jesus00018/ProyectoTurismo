
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CatalogoTourDAO {

    public void mostrarTour() {

        AcessoDatos accesoBD = null;
        Connection con = null;
        Statement sentencia = null;
        ResultSet rs = null;

        String query =
            "SELECT " +
            "t.idTOUR AS id_tour, " +
            "t.nombre AS nombre_tour, " +
            "a.nombre AS nombre_agencia, " +
            "t.precio AS precio, " +
            "pr.fecha AS fecha, " +
            "pr.horario AS horario, " +
            "pr.cupo_maximo AS cupo_maximo, " +

            "COALESCE(SUM(CASE " +
            "WHEN r.idRESERVA IS NOT NULL " +
            "AND r.estado <> 'CANCELADA' " +
            "THEN r.cantidad_personas " +
            "ELSE 0 END), 0) AS cupos_reservados, " +

            "pr.cupo_maximo - COALESCE(SUM(CASE " +
            "WHEN r.idRESERVA IS NOT NULL " +
            "AND r.estado <> 'CANCELADA' " +
            "THEN r.cantidad_personas " +
            "ELSE 0 END), 0) AS cupos_disponibles " +

            "FROM PROGRAMACIÓN_RESERVA pr " +

            "INNER JOIN TOUR t " +
            "ON t.idTOUR = pr.TOUR_idTOUR " +

            "INNER JOIN AGENCIA a " +
            "ON t.AGENCIA_idAGENCIA = a.idAGENCIA " +

            "LEFT JOIN RESERVA r " +
            "ON r.`PROGRAMACIÓN_RESERVA_idProgramacion` = pr.idProgramacion " +

            "GROUP BY " +
            "pr.idProgramacion, " +
            "t.idTOUR, " +
            "t.nombre, " +
            "a.nombre, " +
            "t.precio, " +
            "pr.fecha, " +
            "pr.horario, " +
            "pr.cupo_maximo " +

            "ORDER BY t.nombre, pr.fecha, pr.horario";

        try {

            // Instancio un objeto de acceso a datos
            accesoBD = new AcessoDatos();

            // Obtener la conexión
            con = accesoBD.getConexion();

            System.out.println("Base de datos utilizada por Java: " +
                con.getCatalog());

            sentencia = con.createStatement();

            // Ejecutar la consulta
            rs = sentencia.executeQuery(query);

            System.out.println("\n========== CATÁLOGO DE TOURS ==========");

            boolean hayResultados = false;

            while (rs.next()) {

                hayResultados = true;

                System.out.println("--------------------------------------");

                System.out.println("ID Tour: " +
                    rs.getInt("id_tour"));

                System.out.println("Nombre del tour: " +
                    rs.getString("nombre_tour"));

                System.out.println("Agencia: " +
                    rs.getString("nombre_agencia"));

                System.out.println("Precio: $" +
                    rs.getBigDecimal("precio"));

                System.out.println("Fecha: " +
                    rs.getDate("fecha"));

                System.out.println("Horario: " +
                    rs.getString("horario"));

                System.out.println("Cupo máximo: " +
                    rs.getInt("cupo_maximo"));

                System.out.println("Cupos reservados: " +
                    rs.getInt("cupos_reservados"));

                System.out.println("Cupos disponibles: " +
                    rs.getInt("cupos_disponibles"));
            }

            if (!hayResultados) {
                System.out.println("No hay tours programados.");
            }

            System.out.println("--------------------------------------");

        } catch (SQLException e) {

            System.out.println("Error al consultar los tours: " +
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

                System.err.println(
                    "Error al cerrar el catálogo de tours: " +
                    e.getMessage());
            }
        }
    }
}

