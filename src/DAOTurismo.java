import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class DAOTurismo{

    public void imprimirListadoTurismo()
	{
	
       //Objeto para ejecutar la consulta en la base de datos
    	AcessoDatos accesoBD  = null;
    	Connection con = null;
    	Statement sentencia = null;
		ResultSet rs = null;
		
		
		// Se crea la consulta usando lenguaje SQL Estandar y almacenando en la variable query
		String query = "";
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
            		           
			}    
            
        } catch (SQLException e) {
            System.err.println("Error al CARGAR DATOS");
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