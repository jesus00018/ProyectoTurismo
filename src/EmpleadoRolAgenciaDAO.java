import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EmpleadoRolAgenciaDAO {
    public void mostrarEmpleadoRol(){
        //Objeto para ejecutar la consulta en la base de datos
    	AcessoDatos accesoBD  = null;
    	Connection con = null;
    	Statement sentencia = null;
		ResultSet rs = null;
		
		
		// Se crea la consulta usando lenguaje SQL Estandar y almacenando en la variable query
		String query = "SELECT\n" + //
                        "    e.idEmpleado AS idEmpleado,\n" + //
                        "    p.nombre AS NombreEmpleado,\n" + //
                        "    p.apellido AS ApellidoEmpleado,\n" + //
                        "    p.DNI AS DNIEmpleado,\n" + //
                        "    r.rol AS RolEmpleado,\n" + //
                        "    p.género AS GeneroEmpleado,\n" + //
                        "    a.nombre AS NombreAgencia\n" + //
                        "\n" + //
                        "FROM EMPLEADO e\n" + //
                        "\n" + //
                        "INNER JOIN PERSONA p\n" + //
                        "    ON e.PERSONA_idPERSONA = p.idPERSONA\n" + //
                        "\n" + //
                        "INNER JOIN AGENCIA a\n" + //
                        "    ON e.AGENCIA_idAGENCIA = a.idAGENCIA\n" + //
                        "\n" + //
                        "INNER JOIN ROL_EMPLEADO r\n" + //
                        "    ON e.idEMPLEADO = r.EMPLEADO_idEMPLEADO\n" + //
                        "    AND e.PERSONA_idPERSONA = r.EMPLEADO_PERSONA_idPERSONA\n" + //
                        "\n" + //
                        "ORDER BY e.idEMPLEADO ASC;\n" + //
                        "    \n" + //
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
                
                System.out.println("\n================================");
                System.out.println("ID Empleado: " + rs.getInt("idEmpleado"));
                System.out.println("Nombre: " + rs.getString("NombreEmpleado"));
                System.out.println("Apellido: " + rs.getString("ApellidoEmpleado"));
                System.out.println("DNI: " + rs.getInt("DNIEmpleado"));
                System.out.println("Rol: " + rs.getString("RolEmpleado"));
                System.out.println("Género: " + rs.getString("GeneroEmpleado"));
                System.out.println("Agencia: " + rs.getString("NombreAgencia"));

           
            		           
			}    
            
        } catch (SQLException e) {
            System.err.println("Error al consultar empleados");
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
