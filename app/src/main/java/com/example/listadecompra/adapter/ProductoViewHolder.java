package com.example.listadecompra.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.listadecompra.Producto;
import com.example.listadecompra.ProductoViewModel;
import com.example.listadecompra.databinding.ItemCompraBinding;

public class ProductoViewHolder extends RecyclerView.ViewHolder {
   private final ItemCompraBinding binding;

    public ProductoViewHolder(@NonNull ItemCompraBinding binding, ProductoViewModel productoViewModel) {
        super(binding.getRoot());
        this.binding = binding;

    }

    public void render(Producto producto, ProductoAdapter.OnProductoCheckedChangeListener listener){
        binding.checkProducto.setText(producto.getNombre());
        binding.cantidad.setText(String.valueOf(producto.getCantidad()));

        binding.checkProducto.setChecked(producto.getComprado());

        binding.checkProducto.setOnCheckedChangeListener((check, isChecked) -> {
            listener.onCheckedChange(producto, isChecked);
        });
    }
}
