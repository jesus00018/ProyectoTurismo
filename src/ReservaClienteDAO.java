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

            "ORDER BY pr.fecha DESC, p.apellido";;
		
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