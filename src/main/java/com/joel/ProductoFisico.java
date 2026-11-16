package com.joel;

public class ProductoFisico extends Producto {
    private double pesoKg;

    public ProductoFisico(String codigo, String nombre, double precio, double pesoKg) {
        super(codigo, nombre, precio);
        this.pesoKg = pesoKg;
    }

    // Sobrecarga de constructor: llama al otro con peso 1.0 kg por defecto
    public ProductoFisico(String codigo, String nombre, double precio) {
        this(codigo, nombre, precio, 1.0);
    }

    public double getPesoKg() {
        return pesoKg;
    }

    public void setPesoKg(double pesoKg) {
        this.pesoKg = pesoKg;
    }

    @Override
    public double calcularPrecioFinal() {
        return getPrecio() + 2.50 + (0.80 * pesoKg);
    }

    // Retorno covariante: devuelve ProductoFisico
    @Override
    public ProductoFisico crearVariante(String nuevoCodigo, double nuevoPrecio) {
        return new ProductoFisico(nuevoCodigo, getNombre(), nuevoPrecio, this.pesoKg);
    }

    @Override
    public String toString() {
        return "[Fisico] " + super.toString() + ", " + pesoKg + " kg, Precio final: $" + String.format("%.2f", calcularPrecioFinal());
    }
}
