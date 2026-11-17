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
            "t.precio AS precio, " +
            "pr.fecha AS fecha, " +
            "pr.horario AS horario, " +
            "pr.cupo_maximo AS cupo_maximo, " +
            "COALESCE(SUM(r.cantidad_personas), 0) AS cupos_ocupados, " +
            "pr.cupo_maximo - COALESCE(SUM(r.cantidad_personas), 0) AS cupos_disponibles " +

            "FROM PROGRAMACIÓN_RESERVA pr " +

            "INNER JOIN TOUR t " +
            "ON t.idTOUR = pr.TOUR_idTOUR " +

            "LEFT JOIN RESERVA r " +
            "ON r.PROGRAMACIÓN_RESERVA_idProgramacion = pr.idProgramacion " +
            "AND r.estado <> 'Cancelada' " +

            "GROUP BY " +
            "pr.idProgramacion, " +
            "t.idTOUR, " +
            "t.nombre, " +
            "t.precio, " +
            "pr.fecha, " +
            "pr.horario, " +
            "pr.cupo_maximo " +

            "ORDER BY pr.fecha, pr.horario";

        try {

            // Instancio un objeto de acceso a datos
        	accesoBD = new AcessoDatos();

            //Obtener la conexion para poder generar la sentencia de consulta
            con = accesoBD.getConexion();
            sentencia = con.createStatement();

            //Ejecuta la consulta y almacena el resultado en rs
            rs  = sentencia.executeQuery(query);
            
            System.out.println("\n========== CATÁLOGO DE TOURS ==========");

            while (rs.next()) {

                System.out.println("--------------------------------------");

                System.out.println("ID Tour: " +
                    rs.getInt("id_tour"));

                System.out.println("Nombre del tour: " +
                    rs.getString("nombre_tour"));

                System.out.println("Precio: $" +
                    rs.getBigDecimal("precio"));

                System.out.println("Fecha: " +
                    rs.getDate("fecha"));

                System.out.println("Horario: " +
                    rs.getString("horario"));

                System.out.println("Cupo máximo: " +
                    rs.getInt("cupo_maximo"));

                System.out.println("Cupos ocupados: " +
                    rs.getInt("cupos_ocupados"));

                System.out.println("Cupos disponibles: " +
                    rs.getInt("cupos_disponibles"));
            }

            System.out.println("--------------------------------------");

        } catch (SQLException e) {

            System.out.println("Error al consultar los tours: " +
                e.getMessage());

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

                System.out.println("Error al cerrar la conexión: " +
                    e.getMessage());
            }
        }
    }
}
