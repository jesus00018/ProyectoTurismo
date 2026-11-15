import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DAOTurismo{

    // Muestra por consola todos los tours, con el nombre de la agencia que los ofrece
    public void imprimirListadoTurismo()
	{

       //Objeto para ejecutar la consulta en la base de datos
    	AcessoDatos accesoBD  = null;
    	Connection con = null;
    	Statement sentencia = null;
		ResultSet rs = null;


		// Se crea la consulta usando lenguaje SQL Estandar y almacenando en la variable query
		// t.* trae las 11 columnas de TOUR y a.nombre (columna 12) el nombre de la agencia
		String query = "SELECT t.*, a.nombre "
		             + "FROM TOUR t "
		             + "JOIN AGENCIA a ON a.idAGENCIA = t.AGENCIA_idAGENCIA "
		             + "ORDER BY t.fecha";
        //En este bloque se ejecutan las sentencias necesarias para acceder a la BD y obtener la informacion
        try {
        	// Instancio un objeto de acceso a datos
        	accesoBD = new AcessoDatos();

            //Obtener la conexion para poder generar la sentencia de consulta
            con = accesoBD.getConexion();
            sentencia = con.createStatement();

            //Ejecuta la consulta y almacena el resultado en rs
            rs  = sentencia.executeQuery(query);

            System.out.println("========== LISTADO DE TOURS ==========");

            // Procesa el resultSet y muestra la informacion obtenida desde la BD
            // Se lee por posicion porque varias columnas tienen tilde
            while(rs.next()){
            	System.out.println("Tour Nro     : " + rs.getInt(1));      // idTOUR
            	System.out.println("Nombre       : " + rs.getString(2));   // nombre
            	System.out.println("Descripcion  : " + rs.getString(3));   // descripción
            	System.out.println("Lugar        : " + rs.getString(4));   // lugar
            	System.out.println("Fecha        : " + rs.getDate(5));     // fecha
            	System.out.println("Horario      : " + rs.getTime(6));     // horario
            	System.out.println("Duracion     : " + rs.getTime(7));     // duración
            	System.out.println("Precio       : $" + rs.getBigDecimal(8)); // precio
            	System.out.println("Disponible   : " + rs.getString(9));   // disponibilidad
            	System.out.println("Agencia      : " + rs.getString(12));  // a.nombre
            	System.out.println("--------------------------------------");
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
