package com.example.listadecompra;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class ProductoViewModel extends ViewModel {
    private MutableLiveData<ArrayList<Producto>> listaProductos;

    public ProductoViewModel() {
        listaProductos = new MutableLiveData<>(new ArrayList<>());
    }

    public LiveData<ArrayList<Producto>> getListaProductos(){
        return listaProductos;
    }

    public void agregarProducto(Producto producto, Callback callback) {
        if (producto.getCantidad() <= 0) {
            callback.onError("La cantidad debe ser mayor a 0");
            return;
        }

        ArrayList<Producto> productos = listaProductos.getValue();
        if (productos != null) {
            boolean existe = false;
            for(Producto p : productos){
                if(p.getNombre().equals(producto.getNombre())){
                    if(p.getCantidad()==producto.getCantidad()){
                        callback.onProductoExistente();
                        return;
                    }

                    p.setCantidad(producto.getCantidad());
                    callback.onProductoActualizado();
                    existe = true;
                    break;
                }
            }

            if (!existe) {
                productos.add(producto);
                callback.onProductoAgregado();
            }

            listaProductos.setValue(productos);
        }
    }

    public void eliminarProducto(Producto producto){
        ArrayList<Producto> productos = listaProductos.getValue();
        if (productos != null) {
            productos.remove(producto);
            listaProductos.setValue(productos);
        }
    }



    public interface Callback {
        void onProductoExistente();
        void onProductoActualizado();
        void onProductoAgregado();
        void onError(String mensaje);
    }
}
