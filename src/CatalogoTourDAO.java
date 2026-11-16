import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CatalogoTourDAO {
    public void mostrarTour(){
       //Objeto para ejecutar la consulta en la base de datos
    	AcessoDatos accesoBD  = null;
    	Connection con = null;
    	Statement sentencia = null;
		ResultSet rs = null;
		
		
		// Se crea la consulta usando lenguaje SQL Estandar y almacenando en la variable query
		String query = "SELECT t.idTOUR         AS id_tour,\n" + //
                        "       t.nombre         AS nombre_tour,\n" + //
                        "       pr.fecha         AS fecha,\n" + //
                        "       pr.horario       AS horario,\n" + //
                        "       pr.estado        AS estado,\n" + //
                        "       pr.cupo_maximo   AS cupo_maximo,\n" + //
                        "       COALESCE(SUM(r.cantidad_personas), 0)                  AS cupos_ocupados,\n" + //
                        "       pr.cupo_maximo - COALESCE(SUM(r.cantidad_personas), 0) AS cupos_disponibles\n" + //
                        "FROM PROGRAMACIÓN_RESERVA pr\n" + //
                        "JOIN TOUR t ON t.idTOUR = pr.TOUR_idTOUR\n" + //
                        "LEFT JOIN RESERVA r\n" + //
                        "       ON r.PROGRAMACIÓN_RESERVA_idProgramacion = pr.idProgramacion\n" + //
                        "      AND r.estado <> 'Cancelada'\n" + //
                        "GROUP BY pr.idProgramacion, t.idTOUR, t.nombre, pr.fecha,\n" + //
                        "         pr.horario, pr.estado, pr.cupo_maximo\n" + //
                        "ORDER BY pr.fecha, pr.horario;";
		
        //En este bloque se ejecutan las sentencias necesarias para acceder a la BD y obtener la informacion
        try {
        	// Instancio un objeto de acceso a datos
        	accesoBD = new AcessoDatos();
			
            //Obtener la conexion para poder generar la sentencia de consulta
            con = accesoBD.getConexion();
            sentencia = con.createStatement();
            
            //Ejecuta la consulta y almacena el resultado en rs
            rs  = sentencia.executeQuery(query);
            
            // Procesa el resultSet y muestra la informacion obtenida desde la BD
            while(rs.next()){
				
                System.out.println("\n================================");
                System.out.println("ID Tour: " + rs.getInt("id_tour"));
                System.out.println("Nombre: " + rs.getString("nombre_tour"));
                System.out.println("Fecha: " + rs.getDate("fecha"));
                System.out.println("Horario: " + rs.getString("horario"));
                System.out.println("Estado: " + rs.getString("estado"));
                System.out.println("Cupo máximo: " + rs.getInt("cupo_maximo"));
                System.out.println("Cupos ocupados: " + rs.getInt("cupos_ocupados"));
                System.out.println("Cupos disponibles: " + rs.getInt("cupos_disponibles"));
       
			}    
            
        } catch (SQLException e) {
            System.err.println("Error al consultar los tours");
            e.printStackTrace();
        }
        finally
        {
        	try
        	{
        		// Cierra el ResultSet
        		if (rs!= null) rs.close();
        		// Cierra la sentencia
        		if (sentencia!= null) sentencia.close();
        		// Cierra la conexion
        		if (con != null) con.close();
        		
        	} catch (SQLException e)
        	{
        		System.err.println("Error al cerrar conexion");
        	}
        }

    }
}
