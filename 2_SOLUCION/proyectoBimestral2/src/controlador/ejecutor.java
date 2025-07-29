package controlador;

import java.util.*;
import modelo.*;
import modelo.Cliente;
import modelo.ClienteDAO;

public class ejecutor {
    public static void pe() {
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n=== MENÚ PRINCIPAL ===");
            System.out.println("1. Crear cliente");
            System.out.println("2. Listar clientes");
            System.out.println("3. Calcular plan de un cliente");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt(); 
            sc.nextLine();
            
            switch (opcion) {
                case 1:
                    crearCliente(sc);
                    break;
                case 2:
                    listarClientes();
                    break;
                case 3:
                    calcularPlanCliente(sc);
                    break;
                case 4:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no válida!");
            }
        } while (opcion != 4);
    }

    private static void crearCliente(Scanner sc) {
        System.out.println("\n=== CREAR NUEVO CLIENTE ===");
        System.out.print("Nombre: "); String nombre = sc.nextLine();
        System.out.print("Cédula: "); String cedula = sc.nextLine();
        System.out.print("Ciudad: "); String ciudad = sc.nextLine();
        System.out.print("Celular: "); String celular = sc.nextLine();
        System.out.print("Marca teléfono: "); String marca = sc.nextLine();
        System.out.print("Modelo teléfono: "); String modelo = sc.nextLine();
        System.out.print("Pago mensual estimado: "); double pago = sc.nextDouble();
        System.out.print("Edad: "); int edad = sc.nextInt(); sc.nextLine();
        System.out.print("País: "); String pais = sc.nextLine();
        
        System.out.println("\nTipos de plan disponibles:");
        System.out.println("1. PostPago Minutos");
        System.out.println("2. PostPago Megas");
        System.out.println("3. PostPago Minutos y Megas");
        System.out.println("4. PostPago Minutos y Megas Económico");
        System.out.print("Seleccione tipo de plan (1-4): ");
        int tipo = sc.nextInt();

        Cliente nuevo = new Cliente(nombre, cedula, ciudad, celular, marca, modelo, pago, edad, pais, tipo);
        ClienteDAO.insertarCliente(nuevo);
        System.out.println("\nCliente creado exitosamente!");
    }

    private static void listarClientes() {
        System.out.println("\n=== LISTADO DE CLIENTES ===");
        List<Cliente> clientes = ClienteDAO.listarClientes();
        
        if (clientes.isEmpty()) {
            System.out.println("No hay clientes registrados.");
        } else {
            for (Cliente c : clientes) {
                System.out.println(c);
                System.out.println("----------------------");
            }
        }
    }

    private static void calcularPlanCliente(Scanner sc) {
        System.out.println("\n=== CALCULAR PLAN DE CLIENTE ===");
        System.out.print("Ingrese cédula del cliente: ");
        String cedula = sc.nextLine();
        
        Cliente cliente = ClienteDAO.buscarCliente(cedula);
        if (cliente == null) {
            System.out.println("Cliente no encontrado!");
            return;
        }

        // Mostrar datos del cliente
        System.out.println("\nDatos del cliente:");
        System.out.println("Nombre: " + cliente.getNombre());
        System.out.println("Tipo de plan: " + cliente.getTipoPlan());
        
        // Calcular plan según el tipo registrado
        Plan plan = crearPlanSegunTipo(cliente.getTipoPlan(), sc);
        if (plan == null) {
            System.out.println("Tipo de plan no válido!");
            return;
        }

        double pagoCalculado = plan.calcularPagoMensual();
        
        System.out.println("\n=== RESULTADO DEL CÁLCULO ===");
        System.out.printf("Pago mensual calculado: $%.2f%n", pagoCalculado);
        System.out.printf("Pago registrado: $%.2f%n", cliente.getPagoMensual());
        System.out.printf("Diferencia: $%.2f%n", (pagoCalculado - cliente.getPagoMensual()));
    }

    private static Plan crearPlanSegunTipo(int tipoPlan, Scanner sc) {
        System.out.println("\nIngrese los detalles del plan:");
        
        switch (tipoPlan) {
            case 1: // PostPago Minutos
                System.out.print("Minutos consumidos: "); double minutos = sc.nextDouble();
                System.out.print("Costo por minuto: "); double costoMinuto = sc.nextDouble();
                System.out.print("Tarifa base: "); int tarifaBase = sc.nextInt();
                sc.nextLine(); // Limpiar buffer
                return new PlanPostPagoMinutos(minutos, costoMinuto, tarifaBase, 1);
                
            case 2: // PostPago Megas
                System.out.print("Minutos consumidos: "); int mins = sc.nextInt();
                System.out.print("Costo por minuto: "); double costoMin = sc.nextDouble();
                System.out.print("Gigas consumidos: "); int gigas = sc.nextInt();
                System.out.print("Costo por giga: "); double costoGiga = sc.nextDouble();
                sc.nextLine(); // Limpiar buffer
                return new PlanPostPagoMegas(mins, costoMin, gigas, costoGiga, 2);
                
            case 3: // PostPago Minutos y Megas
                System.out.print("Minutos nacionales: "); double minNac = sc.nextDouble();
                System.out.print("Costo minuto nacional: "); double costoMinNac = sc.nextDouble();
                System.out.print("Minutos internacionales: "); double minInt = sc.nextDouble();
                System.out.print("Costo minuto internacional: "); double costoMinInt = sc.nextDouble();
                sc.nextLine(); // Limpiar buffer
                return new PlanPostPagoMinutosMegas(minNac, costoMinNac, minInt, costoMinInt, 3);
                
            case 4: // PostPago Minutos y Megas Económico
                System.out.print("Minutos consumidos: "); int minEco = sc.nextInt();
                System.out.print("Costo por minuto: "); double costoMinEco = sc.nextDouble();
                System.out.print("Megas consumidos: "); double megasEco = sc.nextDouble();
                System.out.print("Costo por giga: "); double costoGigaEco = sc.nextDouble();
                System.out.print("Porcentaje descuento: "); double descuento = sc.nextDouble();
                sc.nextLine(); // Limpiar buffer
                return new PlanPostPagoMinutosMegasEconomico(minEco, costoMinEco, megasEco, costoGigaEco, descuento, 4);
                
            default:
                return null;
        }
    }
}