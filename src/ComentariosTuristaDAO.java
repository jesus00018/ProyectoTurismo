import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ComentariosTuristaDAO{

    // Muestra por consola todos los tours, con el nombre de la agencia que los ofrece
    public void mostrarComentariosTurista()
	{

       //Objeto para ejecutar la consulta en la base de datos
    	AcessoDatos accesoBD  = null;
    	Connection con = null;
    	Statement sentencia = null;
		ResultSet rs = null;
		
		String query = 
						"SELECT\n" + //
						"    c.idCOMENTARIO AS idComentario,\n" + //
						"    c.fecha AS FechaComentario,\n" + //
						"    c.tipo AS TipoComentario,\n" + //
						"    c.contenido AS ContenidoComentario,\n" + //
						"    p.nombre AS NombreTurista,\n" + //
						"    p.apellido AS ApellidoTurista,\n" + //
						"    a.nombre AS NombreAgencia\n" + //
						"\n" + //
						"FROM COMENTARIO c\n" + //
						"\n" + //
						"INNER JOIN TURISTA t\n" + //
						"    ON c.TURISTA_idTURISTA = t.idTURISTA\n" + //
						"\n" + //
						"INNER JOIN PERSONA p\n" + //
						"    ON t.PERSONA_idPERSONA = p.idPERSONA\n" + //
						"\n" + //
						"INNER JOIN AGENCIA a\n" + //
						"    ON c.AGENCIA_idAGENCIA = a.idAGENCIA\n" + //
						"\n" + //
						"ORDER BY c.fecha DESC;";
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
            // Se lee por posicion porque varias columnas tienen tilde
            while(rs.next()){
				System.out.println("-----------------------------------------------");
				System.out.println("ID Comentario: " + rs.getInt("idCOMENTARIO"));
				System.out.println("FechaComentario: " + rs.getString("fecha"));
				System.out.println("TipoComentario: " + rs.getString("tipo"));
				System.out.println("ContenidoComentario: " + rs.getString("contenido"));
				System.out.println("NombreTurista: " + rs.getString("nombre"));
				System.out.println("ApellidoTurista: " + rs.getString("apellido"));
				System.out.println("NombreAgencia: " + rs.getString("nombre"));
			}   
            
        } catch (SQLException e) {
            System.err.println("Error al consultar a los comentarios de los turistas hacia la agencia");
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
        		System.err.println("Error al consultar comentarios del turista");
        	}
        }
	}
}
