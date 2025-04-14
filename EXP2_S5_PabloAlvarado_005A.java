
package exp2_s5_pabloalvarado_005a;

/**Experiencia de aprendizaje Nro 2. Semana 05, Fundamentos de Programación 005A
 * @author Pablo Ignacio Alvarado Hernandez 005A
 */


//CLASES Y VARIABLES PARA EL ALGORITMO
//01. Definicion de Clases
//01.1 Importacion y definicion de clase principal


import java.util.ArrayList;
import java.util.Scanner;

public class EXP2_S5_PabloAlvarado_005A {

    //01.2 Definicion de variables estaticas
    
    static int totalEntradasVendidas = 0;
    static double ingresosTotales = 0;
    static int contadorEntradas = 1;

    
    //01.3 Definicion de Variables de Instancia
    
    int numeroEntrada;
    String ubicacion;
    double precioFinal;
    String tipoCliente;

    
    
    //02. Construccion de la Clase
    
    public EXP2_S5_PabloAlvarado_005A(String ubicacion, double precioFinal, String tipoCliente) {
        this.numeroEntrada = contadorEntradas++;
        this.ubicacion = ubicacion;
        this.precioFinal = precioFinal;
        this.tipoCliente = tipoCliente;
    }

    public void mostrarEntrada() {
        System.out.println("Entrada #" + numeroEntrada +
                " | Ubicacion: " + ubicacion +
                " | Cliente: " + tipoCliente +
                " | Precio: $" + precioFinal);
    }

    
    //02.1 Para mostrar los datos de la entrada
    
    public static void main(String[] args) {
        
        
        //02.2 Inicializacion del Algoritmo
        
        Scanner scanner = new Scanner(System.in);
        ArrayList<EXP2_S5_PabloAlvarado_005A> entradasVendidas = new ArrayList<>();

        String nombreTeatro = "Teatro Moro";
        int capacidadSala = 100;
        int entradasDisponibles = capacidadSala;

        
        //ALGORITMO EN PROCESO
        //03. Inicio del Algoritmo
        int opcion;

        do {
            System.out.println("\nBienvenido al " + nombreTeatro);
            System.out.println("1. Venta de entradas");
            System.out.println("2. Ver promociones");
            System.out.println("3. Buscar entrada");
            System.out.println("4. Eliminar entrada");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opcion: ");

            while (!scanner.hasNextInt()) {
                System.out.print("Ingrese un numero valido: ");
                scanner.next();
            }
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                
                
                //03.1 Venta de entradas
                
                case 1:
                    if (entradasDisponibles <= 0) {
                        System.out.println("No hay entradas disponibles.");
                        break;
                    }

                    System.out.print("Ubicacion (VIP, Platea, General): ");
                    String ubicacion = scanner.nextLine();

                    System.out.print("Tipo de cliente (estudiante / tercera edad / ninguno): ");
                    String tipoCliente = scanner.nextLine().toLowerCase();

                    double precioBase;
                    switch (ubicacion.toLowerCase()) {
                        case "vip":
                            precioBase = 30000;
                            break;
                        case "platea":
                            precioBase = 15000;
                            break;
                        default:
                            precioBase = 13000;
                    }

                    double descuento = 0.0;
                    if (tipoCliente.equals("estudiante")) {
                        descuento = 0.10;
                    } else if (tipoCliente.equals("tercera edad")) {
                        descuento = 0.15;
                    }

                    double precioFinal = precioBase - (precioBase * descuento);

                    EXP2_S5_PabloAlvarado_005A nuevaEntrada = new EXP2_S5_PabloAlvarado_005A(ubicacion, precioFinal, tipoCliente);
                    entradasVendidas.add(nuevaEntrada);
                    totalEntradasVendidas++;
                    ingresosTotales += precioFinal;
                    entradasDisponibles--;

                    System.out.println("Entrada vendida correctamente:");
                    nuevaEntrada.mostrarEntrada();
                    break;

                    
                    
                    //03.2 Mostrar Promociones
                    
                case 2:
                    System.out.println("\nPromociones disponibles:");
                    System.out.println("- 10% de descuento para estudiantes");
                    System.out.println("- 15% de descuento para tercera edad");
                    break;

                    
                    
                    //03.3 Busqueda de Entradas
                    
                case 3:
                    System.out.print("Buscar por numero, ubicacion o tipo de cliente: ");
                    String criterio = scanner.nextLine().toLowerCase();

                    boolean encontrado = false;
                    for (EXP2_S5_PabloAlvarado_005A entrada : entradasVendidas) {
                        if (String.valueOf(entrada.numeroEntrada).equals(criterio)
                                || entrada.ubicacion.toLowerCase().equals(criterio)
                                || entrada.tipoCliente.toLowerCase().equals(criterio)) {
                            entrada.mostrarEntrada();
                            encontrado = true;
                        }
                    }

                    if (!encontrado) {
                        System.out.println("No se encontraron entradas con ese valor.");
                    }
                    break;

                    
                    
                    //03.4 Eliminar entradas
                case 4:
                    System.out.print("Numero de entrada a eliminar: ");
                    while (!scanner.hasNextInt()) {
                        System.out.print("Ingrese un numero valido: ");
                        scanner.next();
                    }
                    int numEliminar = scanner.nextInt();
                    scanner.nextLine();

                    boolean eliminado = false;
                    for (int i = 0; i < entradasVendidas.size(); i++) {
                        if (entradasVendidas.get(i).numeroEntrada == numEliminar) {
                            ingresosTotales -= entradasVendidas.get(i).precioFinal;
                            entradasVendidas.remove(i);
                            totalEntradasVendidas--;
                            entradasDisponibles++;
                            eliminado = true;
                            System.out.println("Entrada eliminada.");
                            break;
                        }
                    }

                    if (!eliminado) {
                        System.out.println("Entrada no encontrada.");
                    }
                    break;

                    
                    //SALIDA
                    //04. Proceso para salir del Algoritmo
                case 5:
                    System.out.println("Gracias por preferir Teatro Moro.");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcion != 5);

        scanner.close();
    }
}