import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class AcessoDatos{
	 
	private String  maquina     = "localhost";
	private String  usuario     = "root";
	private String  clave       = "****";
	private int puerto          = 3306;
	private String bd			= "Proy_Turismo";
	private String  servidor    = "";
	private Connection conexion = null;
	 
	    //
	public AcessoDatos(){
		
		this.servidor="jdbc:mysql://"+this.maquina+":"+ this.puerto+"/"+this.bd;
	 
	        //Carga el driver mysql en memoria
	    try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	        }
	    catch (ClassNotFoundException e) {
	            
				e.printStackTrace();
				System.out.println(e.getMessage());
				System.err.println("ERROR AL REGISTRAR EL DRIVER");
				
	            System.exit(0); //parar la ejecuci�n
	        }
	 
	        //Establecer la conexi�n con el servidor(base de datos) y almacenarla en el atributo conexion, que le pasa el servidor, el usuario y clave de esa base datos
	     try {
	            conexion = DriverManager.getConnection(this.servidor,this.usuario, this.clave); //establecer una conexion con la base de datos
	        } catch (SQLException e) {
	            System.err.println("ERROR AL CONECTAR CON EL SERVIDOR");
	            e.printStackTrace();
	            System.exit(0); //parar la ejecuci�n
	        }
	        
	    }
	 
	    //Devuelve el objeto Connection que se usar� en la clase Controlador
	    public  Connection getConexion() {
	        return conexion;
	    }

}

    

