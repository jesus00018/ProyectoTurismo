import java.util.Scanner;

public class Menu{
    public static void main(String[] args) {
        CatalogoTourDAO catalogoTourDAO = new CatalogoTourDAO();
        ReservaClienteDAO reservaClienteDAO = new ReservaClienteDAO();
        EmpleadoRolAgenciaDAO empleadoRolAgenciaDAO = new EmpleadoRolAgenciaDAO();
        TourMasSolicitadoDAO tourMasSolicitadoDAO = new TourMasSolicitadoDAO();
        ComentariosTuristaDAO comentariosTuristaDAO = new ComentariosTuristaDAO();

        Scanner teclado = new Scanner(System.in);
        int opcion;

        do{
            System.out.println("\n ========================================== ");
            System.out.println("        SISTEMA DE TURISMO                   ");
            System.out.println(" ========================================== ");
            System.out.println("1. Catalogos de Tours ");
            System.out.println("2. Reservas de los turistas ");
            System.out.println("3. Empleados con su rol ");
            System.out.println("4. Tour más solicitados");
            System.out.println("5. Comentarios de los turistas");
            System.out.println("6. Salir");
            System.out.print("Elija una opcion: ");

            // Si se escribe algo que no es un numero, se descarta y se vuelve a pedir
            while (!teclado.hasNextInt()) {
                System.out.println("Ingrese un numero del 1 al 6");
                teclado.next();
            }
            opcion = teclado.nextInt();

            switch(opcion){
               
                case 1:
                        System.out.println("Mostrando los tours");
                        catalogoTourDAO.mostrarTour();
                        break;
                case 2 :
                        System.out.println("Mostrando las reservas de los clientes");
                        reservaClienteDAO.mostrarReservaCliente();
                        break;
                case 3 :
                        System.out.println("Mostrando los empleados");
                        empleadoRolAgenciaDAO.mostrarEmpleadoRol();
                        break;
                case 4: 
                        System.out.println("Mostrando los tours mas solicitados");
                        tourMasSolicitadoDAO.mostrarEstadisticasTours();
                        break;
                case 5 :
                        System.out.println("Mostrando los comentarios");
                        comentariosTuristaDAO.mostrarComentariosTurista();
                        break;
                case 6 :
                        System.out.println("Saliendo del sistema");
                        break;
                default:
                        System.out.println("Opción no válida");
                

            }
        }  while(opcion != 6);
        teclado.close(); 
    }
}
