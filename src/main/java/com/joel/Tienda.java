package com.joel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Tienda {
    private HashMap<String, Producto> catalogo = new HashMap<>();
    private ArrayList<Producto> carrito = new ArrayList<>();
    private HashSet<String> cuponesValidos = new HashSet<>();
    private HashSet<String> cuponesUsados = new HashSet<>();

    public Tienda() {
        cuponesValidos.add("ESFOT10");
        cuponesValidos.add("POO10");
    }

    public static void mostrarMenu() {
        System.out.println("\n--- MENU SHOPESFOT ---");
        System.out.println("1. Registrar producto fisico");
        System.out.println("2. Registrar producto digital");
        System.out.println("3. Crear variante de producto");
        System.out.println("4. Mostrar catalogo");
        System.out.println("5. Agregar al carrito");
        System.out.println("6. Pagar");
        System.out.println("7. Salir");
        System.out.print("Ingrese opcion: ");
    }

    public void registrarProductoFisico(String codigo, String nombre, double precio, Double pesoKg) {
        if (codigo == null || codigo.isBlank() || nombre == null || nombre.isBlank()) {
            System.out.println("Error: Codigo y nombre son obligatorios.");
            return;
        }
        if (precio < 0) {
            System.out.println("Error: El precio no puede ser negativo.");
            return;
        }
        if (pesoKg != null && pesoKg < 0) {
            System.out.println("Error: El peso no puede ser negativo.");
            return;
        }
        String codUpper = codigo.toUpperCase();
        if (catalogo.containsKey(codUpper)) {
            System.out.println("Error: El codigo " + codUpper + " ya esta registrado.");
            return;
        }

        ProductoFisico pf;
        if (pesoKg == null) {
            pf = new ProductoFisico(codUpper, nombre, precio);
        } else {
            pf = new ProductoFisico(codUpper, nombre, precio, pesoKg);
        }
        catalogo.put(codUpper, pf);
        System.out.println("Producto fisico registrado exitosamente.");
    }

    public void registrarProductoDigital(String codigo, String nombre, double precio, double tamanoMb) {
        if (codigo == null || codigo.isBlank() || nombre == null || nombre.isBlank()) {
            System.out.println("Error: Codigo y nombre son obligatorios.");
            return;
        }
        if (precio < 0 || tamanoMb < 0) {
            System.out.println("Error: El precio o el tamano no pueden ser negativos.");
            return;
        }
        String codUpper = codigo.toUpperCase();
        if (catalogo.containsKey(codUpper)) {
            System.out.println("Error: El codigo " + codUpper + " ya esta registrado.");
            return;
        }

        ProductoDigital pd = new ProductoDigital(codUpper, nombre, precio, tamanoMb);
        catalogo.put(codUpper, pd);
        System.out.println("Producto digital registrado exitosamente.");
    }

    public void crearVarianteProducto(String codigoOriginal, String nuevoCodigo, double nuevoPrecio) {
        if (catalogo.isEmpty()) {
            System.out.println("Error: El catalogo esta vacio.");
            return;
        }
        String origUpper = codigoOriginal.toUpperCase();
        Producto original = catalogo.get(origUpper);
        if (original == null) {
            System.out.println("Error: El codigo " + origUpper + " no existe.");
            return;
        }
        if (nuevoCodigo == null || nuevoCodigo.isBlank()) {
            System.out.println("Error: El nuevo codigo no puede estar vacio.");
            return;
        }
        if (nuevoPrecio < 0) {
            System.out.println("Error: El precio no puede ser negativo.");
            return;
        }
        String nuevoUpper = nuevoCodigo.toUpperCase();
        if (catalogo.containsKey(nuevoUpper)) {
            System.out.println("Error: El codigo " + nuevoUpper + " ya esta registrado.");
            return;
        }

        Producto variante = original.crearVariante(nuevoUpper, nuevoPrecio);
        catalogo.put(nuevoUpper, variante);
        System.out.println("Variante " + nuevoUpper + " creada a partir de " + origUpper + ".");
    }

    public void mostrarCatalogo() {
        if (catalogo.isEmpty()) {
            System.out.println("El catalogo esta vacio.");
            return;
        }
        for (Producto p : catalogo.values()) {
            System.out.println(p);
        }
    }

    public void agregarAlCarrito(String codigo) {
        agregarAlCarrito(codigo, 1);
    }

    public void agregarAlCarrito(String codigo, int cantidad) {
        if (cantidad <= 0) {
            System.out.println("Error: La cantidad debe ser mayor a 0.");
            return;
        }
        Producto p = catalogo.get(codigo.toUpperCase());
        if (p == null) {
            System.out.println("Error: El codigo " + codigo + " no existe.");
            return;
        }
        for (int i = 0; i < cantidad; i++) {
            carrito.add(p);
        }
        if (cantidad == 1) {
            System.out.println("Se agrego 1 unidad de " + p.getNombre() + ".");
        } else {
            System.out.println("Se agregaron " + cantidad + " unidades de " + p.getNombre() + ".");
        }
    }

    public double calcularTotalCarrito() {
        double total = 0.0;
        for (Producto p : carrito) {
            total += p.calcularPrecioFinal();
        }
        return total;
    }

    public boolean carritoEstaVacio() {
        return carrito.isEmpty();
    }

    // Sobrecarga 1: pagar sin cupon
    public void pagar() {
        if (carrito.isEmpty()) {
            System.out.println("Error: El carrito esta vacio.");
            return;
        }
        double total = calcularTotalCarrito();
        System.out.println("Total a pagar: $" + String.format("%.2f", total));
        carrito.clear();
        System.out.println("Gracias por su compra. El carrito quedo vacio.");
    }

    // Sobrecarga 2: pagar con cupon
    public void pagar(String cupon) {
        if (carrito.isEmpty()) {
            System.out.println("Error: El carrito esta vacio.");
            return;
        }
        double total = calcularTotalCarrito();
        String cupUpper = cupon.toUpperCase();

        if (!cuponesValidos.contains(cupUpper)) {
            System.out.println("El cupon " + cupUpper + " no es valido.");
            System.out.println("Total a pagar: $" + String.format("%.2f", total));
        } else if (cuponesUsados.contains(cupUpper)) {
            System.out.println("El cupon " + cupUpper + " ya fue utilizado.");
            System.out.println("Total a pagar: $" + String.format("%.2f", total));
        } else {
            cuponesUsados.add(cupUpper);
            double totalConDescuento = total * 0.90;
            System.out.println("Descuento aplicado: 10 %");
            System.out.println("Total a pagar: $" + String.format("%.2f", totalConDescuento));
        }

        carrito.clear();
        System.out.println("Gracias por su compra. El carrito quedo vacio.");
    }
}
