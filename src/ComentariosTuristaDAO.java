
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ComentariosTuristaDAO {

    public void mostrarComentariosTurista() {

        AcessoDatos accesoBD = null;
        Connection con = null;
        Statement sentencia = null;
        ResultSet rs = null;

        String query =
            "SELECT " +
            "c.idCOMENTARIO AS idComentario, " +
            "c.fecha AS FechaComentario, " +
            "c.tipo AS TipoComentario, " +
            "c.contenido AS ContenidoComentario, " +
            "p.nombre AS NombreTurista, " +
            "p.apellido AS ApellidoTurista, " +
            "a.nombre AS NombreAgencia " +

            "FROM COMENTARIO c " +

            "INNER JOIN TURISTA t " +
            "ON c.TURISTA_idTURISTA = t.idTURISTA " +

            "INNER JOIN PERSONA p " +
            "ON t.PERSONA_idPERSONA = p.idPERSONA " +

            "INNER JOIN AGENCIA a " +
            "ON c.AGENCIA_idAGENCIA = a.idAGENCIA " +

            "ORDER BY c.fecha DESC";

        try {

            accesoBD = new AcessoDatos();

            con = accesoBD.getConexion();
            sentencia = con.createStatement();

            rs = sentencia.executeQuery(query);

            System.out.println("\n========== COMENTARIOS DE TURISTAS ==========");

            while (rs.next()) {

                System.out.println("-----------------------------------------------");

                System.out.println("ID Comentario: " +
                    rs.getInt("idComentario"));

                System.out.println("Fecha: " +
                    rs.getString("FechaComentario"));

                System.out.println("Tipo: " +
                    rs.getString("TipoComentario"));

                System.out.println("Contenido: " +
                    rs.getString("ContenidoComentario"));

                System.out.println("Nombre Turista: " +
                    rs.getString("NombreTurista"));

                System.out.println("Apellido Turista: " +
                    rs.getString("ApellidoTurista"));

                System.out.println("Agencia: " +
                    rs.getString("NombreAgencia"));
            }

            System.out.println("-----------------------------------------------");

        } catch (SQLException e) {

            System.err.println("Error al consultar los comentarios: " +
                e.getMessage());

        } finally {

            try {

                if (rs != null) rs.close();
                if (sentencia != null) sentencia.close();
                if (con != null) con.close();

            } catch (SQLException e) {

                System.err.println("Error al cerrar los recursos: " +
                    e.getMessage());
            }
        }
    }
}

