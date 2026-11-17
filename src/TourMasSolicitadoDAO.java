
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TourMasSolicitadoDAO {

    public void mostrarEstadisticasTours() {

        AcessoDatos accesoBD = null;
        Connection con = null;
        Statement sentencia = null;
        ResultSet rs = null;

        String query =
            "SELECT " +
            "t.idTOUR AS ID_Tour, " +
            "t.nombre AS Nombre_Tour, " +
            "COUNT(DISTINCT r.idRESERVA) AS Cantidad_Reservas, " +
            "COALESCE(SUM(r.cantidad_personas), 0) AS Total_Personas " +
            "FROM TOUR t " +
            "LEFT JOIN PROGRAMACIÓN_RESERVA pr " +
            "ON t.idTOUR = pr.TOUR_idTOUR " +
            "LEFT JOIN RESERVA r " +
            "ON r.`PROGRAMACIÓN_RESERVA_idProgramacion` = pr.idProgramacion " +
            "AND r.estado <> 'CANCELADA' " +
            "GROUP BY t.idTOUR, t.nombre " +
            "ORDER BY Cantidad_Reservas DESC, Total_Personas DESC";

        try {

            accesoBD = new AcessoDatos();
            con = accesoBD.getConexion();

            sentencia = con.createStatement();
            rs = sentencia.executeQuery(query);

            System.out.println("\n========== TOURS MÁS SOLICITADOS ==========");

            boolean hayResultados = false;

            while (rs.next()) {

                hayResultados = true;

                System.out.println("------------------------------------------");
                System.out.println("ID Tour: " + rs.getInt("ID_Tour"));
                System.out.println("Nombre del tour: " + rs.getString("Nombre_Tour"));
                System.out.println("Cantidad de reservas: " + rs.getInt("Cantidad_Reservas"));
                System.out.println("Total de personas: " + rs.getInt("Total_Personas"));
            }

            if (!hayResultados) {
                System.out.println("No hay tours registrados.");
            }

            System.out.println("------------------------------------------");

        } catch (SQLException e) {

            System.err.println("Error al consultar los tours más solicitados: " + e.getMessage());

        } finally {

            try {

                if (rs != null) {
                    rs.close();
                }

                if (sentencia != null) {
                    sentencia.close();
                }

                if (con != null) {
                    con.close();
                }

            } catch (SQLException e) {
                System.err.println("Error al cerrar los recursos: " + e.getMessage());
            }
        }
    }
}
