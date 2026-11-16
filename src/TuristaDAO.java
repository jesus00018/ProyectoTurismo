import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class TuristaDAO{

    public void mostrarTurista()
	{
	
       //Objeto para ejecutar la consulta en la base de datos
    	AcessoDatos accesoBD  = null;
    	Connection con = null;
    	Statement sentencia = null;
		ResultSet rs = null;
		
		
		// Se crea la consulta usando lenguaje SQL Estandar y almacenando en la variable query
		String query = "use proy_turismo;\n" + //
						"SELECT \n" + //
						"    t.idTURISTA,\n" + //
						"    p.nombre,\n" + //
						"    p.apellido,\n" + //
						"    p.DNI,\n" + //
						"    p.email,\n" + //
						"    p.nacionalidad,\n" + //
						"    p.provincia,\n" + //
						"    p.ciudad,\n" + //
						"    p.género,\n" + //
						"    t.patología,\n" + //
						"    t.alergía\n" + //
						"FROM TURISTA t\n" + //
						"INNER JOIN PERSONA p\n" + //
						"    ON t.PERSONA_idPERSONA = p.idPERSONA;\n" + //
						"";
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
				System.out.println("------------------------------");
                System.out.println("ID Turista: " + rs.getInt("idTURISTA"));
                System.out.println("Nombre: " + rs.getString("nombre"));
                System.out.println("Apellido: " + rs.getString("apellido"));
                System.out.println("DNI: " + rs.getInt("DNI"));
                System.out.println("Email: " + rs.getString("email"));
                System.out.println("Nacionalidad: " + rs.getString("nacionalidad"));
                System.out.println("Provincia: " + rs.getString("provincia"));
                System.out.println("Ciudad: " + rs.getString("ciudad"));
                System.out.println("Género: " + rs.getString("género"));
                System.out.println("Patología: " + rs.getString("patología"));
                System.out.println("Alergía: " + rs.getString("alergía"));
            		           
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