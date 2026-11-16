import java.util.Scanner;

public class Menu{
    public static void MostrarMenu(){

        Scanner teclado = new Scanner(System.in);
        int opcion;

        do{
            System.out.println("\n ==========================================");
            System.out.println("        SISTEMA DE TURISMO         ");
            System.out.println(" ==========================================");
            System.out.println("1. Mostrar turistas ");
            System.out.println("2. Mostrar empleados ");
            System.out.println("3. Mostrar los tours ");
            System.out.println("4. Mostrar reservas ");
            System.out.println("5. Lugares disponibles");
            System.out.println("6. Mostrar programaciones de las reservas");
            System.out.println("7. Salir");
            System.out.print("Elija una opcion: ");

            // Si se escribe algo que no es un numero, se descarta y se vuelve a pedir
            while (!teclado.hasNextInt()) {
                System.out.println("Ingrese un numero del 1 al 7");
                teclado.next();
            }
            opcion = teclado.nextInt();
        
            

            switch(opcion){

                case 1 :
                        System.out.println("Mostrando turistas");
                        break;
                case 2 :
                        System.out.println("Mostrando empleados");
                        break;
                case 3 :
                        System.out.println("Mostrando los tours");
                        new DAOTurismo().imprimirListadoTurismo();
                        break;
                case 4 :
                        System.out.println("Mostrando reservas");
                        break;
                case 5 :
                        System.out.println("Mostrando lugares disponibles");
                        break;
                case 6 :
                        System.out.println("Mostrando programaciones de las reservas");
                        break;
                case 7 :
                        System.out.println("Saliendo del sistema");
                        break;
                default:
                        System.out.println("Opción no válida");
                

            }
        }  while(opcion != 7); 
    }
}
