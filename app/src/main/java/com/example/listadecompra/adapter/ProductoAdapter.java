package com.example.listadecompra.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.listadecompra.Producto;
import com.example.listadecompra.ProductoViewModel;
import com.example.listadecompra.databinding.ItemCompraBinding;

import java.util.ArrayList;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoViewHolder> {
    private ArrayList<Producto> listaproductos;
    private OnProductoCheckedChangeListener listener;
    private ProductoViewModel productoViewModel;

    public ProductoAdapter(ProductoViewModel productoViewModel, OnProductoCheckedChangeListener listener){
        this.productoViewModel = productoViewModel;
        this.listener = listener;
        this.listaproductos = new ArrayList<>();
    }

    @NonNull
    @Override
    public ProductoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCompraBinding binding = ItemCompraBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ProductoViewHolder(binding, productoViewModel);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductoViewHolder holder, int position) {
        Producto producto = listaproductos.get(position);
        holder.render(producto, listener);
    }

    @Override
    public int getItemCount() {
        return listaproductos.size();
    }

    public void setListaProductos (ArrayList<Producto> listaproductos) {
        this.listaproductos = listaproductos;
        notifyDataSetChanged();
    }

    public interface OnProductoCheckedChangeListener {
        void onCheckedChange(Producto producto, boolean isChecked);
    }

}
