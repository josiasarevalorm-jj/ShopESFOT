package com.joel;

public class ProductoDigital extends Producto {
    private double tamanoMb;

    public ProductoDigital(String codigo, String nombre, double precio, double tamanoMb) {
        super(codigo, nombre, precio);
        this.tamanoMb = tamanoMb;
    }

    public double getTamanoMb() {
        return tamanoMb;
    }

    public void setTamanoMb(double tamanoMb) {
        this.tamanoMb = tamanoMb;
    }

    @Override
    public double calcularPrecioFinal() {
        return getPrecio();
    }

    // Retorno covariante: devuelve ProductoDigital
    @Override
    public ProductoDigital crearVariante(String nuevoCodigo, double nuevoPrecio) {
        return new ProductoDigital(nuevoCodigo, getNombre(), nuevoPrecio, this.tamanoMb);
    }

    @Override
    public String toString() {
        String tamanoStr = (tamanoMb % 1 == 0) ? String.format("%.0f", tamanoMb) : String.valueOf(tamanoMb);
        return "[Digital] " + super.toString() + ", " + tamanoStr + " MB, Precio final: $" + String.format("%.2f", calcularPrecioFinal());
    }
}