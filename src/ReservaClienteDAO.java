import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ReservaClienteDAO{

    public void mostrarReservaCliente()
	{
	
       //Objeto para ejecutar la consulta en la base de datos
    	AcessoDatos accesoBD  = null;
    	Connection con = null;
    	Statement sentencia = null;
		ResultSet rs = null;
		
		
		// Se crea la consulta usando lenguaje SQL Estandar y almacenando en la variable query
		String query = "SELECT\n" + //
						"    r.idRESERVA AS ID_Reserva,\n" + //
						"\n" + //
						"    p.nombre AS Nombre_Turista,\n" + //
						"    p.apellido AS Apellido_Turista,\n" + //
						"    p.DNI AS DNI_Turista,\n" + //
						"\n" + //
						"    t.nombre AS Nombre_Tour,\n" + //
						"    t.precio AS Precio_Por_Persona,\n" + //
						"\n" + //
						"    r.cantidad_personas AS Cantidad_Personas,\n" + //
						"\n" + //
						"    (t.precio * r.cantidad_personas) AS Precio_Total,\n" + //
						"\n" + //
						"    pr.fecha AS Fecha_Tour,\n" + //
						"    pr.horario AS Horario_Tour,\n" + //
						"\n" + //
						"    r.estado AS Estado_Reserva\n" + //
						"\n" + //
						"FROM RESERVA r\n" + //
						"\n" + //
						"INNER JOIN TURISTA tu\n" + //
						"    ON r.TURISTA_TITULAR_idTURISTA = tu.idTURISTA\n" + //
						"\n" + //
						"INNER JOIN PERSONA p\n" + //
						"    ON tu.PERSONA_idPERSONA = p.idPERSONA\n" + //
						"\n" + //
						"INNER JOIN PROGRAMACIÓN_RESERVA pr\n" + //
						"    ON r.`PROGRAMACIÓN_RESERVA_idProgramacion` = pr.idProgramacion\n" + //
						"\n" + //
						"INNER JOIN TOUR t\n" + //
						"    ON pr.TOUR_idTOUR = t.idTOUR\n" + //
						"\n" + //
						"ORDER BY pr.fecha DESC, p.apellido;";
		
        //En este bloque se ejecutan las sentencias necesarias para acceder a la BD y obtener la informacion
        try {
        	// Instancio un objeto de acceso a datos
        	accesoBD = new AcessoDatos();
			
            //Obtener la conexion para poder generar la sentencia de consulta
            con = accesoBD.getConexion();
            sentencia = con.createStatement();
            
            //Ejecuta la consulta y almacena el resultado en rs
            rs  = sentencia.executeQuery(query);
            
            System.out.println("\n========== RESERVAS DE TURISTAS ==========");

            while (rs.next()) {

                System.out.println("-----------------------------------------------");

                System.out.println("ID Reserva: " +
                    rs.getInt("ID_Reserva"));

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

                System.out.println("Estado de la reserva: " +
                    rs.getString("Estado_Reserva"));
            } 
            
        } catch (SQLException e) {
            System.err.println("Error al Consultar Turistas");
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