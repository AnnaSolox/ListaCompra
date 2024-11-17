package com.example.listadecompra;

public class Producto {
    private String nombre;
    private int cantidad;
    private boolean comprado;


    public Producto(String nombre, int cantidad){
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.comprado = false;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantidad(){return cantidad;}


    public void setCantidad(int cantidad){this.cantidad = cantidad;}

    public boolean getComprado() {return comprado;}

    public void setComprado(boolean comprado) {this.comprado = comprado;}
}
