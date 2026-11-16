package com.joel;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Tienda tienda = new Tienda();

        while (true) {
            Tienda.mostrarMenu();
            String opStr = sc.nextLine();
            int op;
            try {
                op = Integer.parseInt(opStr);
            } catch (NumberFormatException e) {
                System.out.println("Opcion no valida.");
                continue;
            }

            if (op == 1) {
                System.out.print("Codigo: ");
                String codigo = sc.nextLine();
                System.out.print("Nombre: ");
                String nombre = sc.nextLine();
                System.out.print("Precio: ");
                double precio = Double.parseDouble(sc.nextLine());
                System.out.print("¿Desea indicar el peso? (s/n): ");
                String respPeso = sc.nextLine();
                Double peso = null;
                if (respPeso.equalsIgnoreCase("s")) {
                    System.out.print("Peso (kg): ");
                    peso = Double.parseDouble(sc.nextLine());
                }
                tienda.registrarProductoFisico(codigo, nombre, precio, peso);

            } else if (op == 2) {
                System.out.print("Codigo: ");
                String codigo = sc.nextLine();
                System.out.print("Nombre: ");
                String nombre = sc.nextLine();
                System.out.print("Precio: ");
                double precio = Double.parseDouble(sc.nextLine());
                System.out.print("Tamano (MB): ");
                double tamano = Double.parseDouble(sc.nextLine());
                tienda.registrarProductoDigital(codigo, nombre, precio, tamano);

            } else if (op == 3) {
                System.out.print("Codigo del producto original: ");
                String codOrig = sc.nextLine();
                System.out.print("Nuevo codigo: ");
                String nuevoCod = sc.nextLine();
                System.out.print("Nuevo precio: ");
                double nuevoPrecio = Double.parseDouble(sc.nextLine());
                tienda.crearVarianteProducto(codOrig, nuevoCod, nuevoPrecio);

            } else if (op == 4) {
                tienda.mostrarCatalogo();

            } else if (op == 5) {
                System.out.print("Codigo: ");
                String codigo = sc.nextLine();
                System.out.print("¿Cuantas unidades? (Enter para 1): ");
                String cantStr = sc.nextLine();
                if (cantStr.isBlank()) {
                    tienda.agregarAlCarrito(codigo);
                } else {
                    int cantidad = Integer.parseInt(cantStr);
                    tienda.agregarAlCarrito(codigo, cantidad);
                }

            } else if (op == 6) {
                if (tienda.carritoEstaVacio()) {
                    System.out.println("Error: El carrito esta vacio.");
                } else {
                    System.out.println("Total: $" + String.format("%.2f", tienda.calcularTotalCarrito()));
                    System.out.print("¿Tiene cupon? (s/n): ");
                    String tieneCupon = sc.nextLine();
                    if (tieneCupon.equalsIgnoreCase("s")) {
                        System.out.print("Cupon: ");
                        String cupon = sc.nextLine();
                        tienda.pagar(cupon);
                    } else {
                        tienda.pagar();
                    }
                }

            } else if (op == 7) {
                System.out.println("Saliendo...");
                break;

            } else {
                System.out.println("Opcion no valida.");
            }
        }
    }
}
