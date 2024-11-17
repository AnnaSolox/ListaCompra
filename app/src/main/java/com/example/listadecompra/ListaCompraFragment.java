package com.example.listadecompra;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.listadecompra.adapter.ProductoAdapter;
import com.example.listadecompra.adapter.ProductoViewHolder;
import com.example.listadecompra.databinding.FragmentListaCompraBinding;

import java.util.ArrayList;

public class ListaCompraFragment extends Fragment {
    private RecyclerView recyclerView;
    private ProductoAdapter productoAdapter;
    private ProductoViewModel productoViewModel;

    private FragmentListaCompraBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentListaCompraBinding.inflate(inflater, container, false);

        recyclerView = binding.recycerView;
        productoAdapter = new ProductoAdapter(productoViewModel, (producto, isChecked) ->
        {
            if (isChecked) {
                productoViewModel.eliminarProducto(producto);
            } else {
                producto.setComprado(false);  // No eliminar, solo desmarcar
            }
        });
        recyclerView.setAdapter(productoAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        productoViewModel = new ViewModelProvider(this).get(ProductoViewModel.class);

        productoViewModel.getListaProductos().observe(getViewLifecycleOwner(), productos -> productoAdapter.setListaProductos(productos));

        binding.anyadir.setOnClickListener(p -> {
            String nombre = binding.nombreEdit.getText().toString();
            int cantidad = Integer.parseInt(binding.cantidad.getText().toString());
            Producto producto = new Producto(nombre, cantidad);

            productoViewModel.agregarProducto(producto, new ProductoViewModel.Callback() {
                @Override
                public void onProductoExistente() {
                    Toast.makeText(getContext(), "El producto ya está en la lista con la misma cantidad.", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onProductoActualizado() {
                    Toast.makeText(getContext(), "Producto actualizado.", Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onProductoAgregado() {
                    Toast.makeText(getContext(), "Producto agregado.", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onError(String mensaje) {
                    Toast.makeText(getContext(), mensaje, Toast.LENGTH_SHORT).show();
                }
            });
        });


        // Inflate the layout for this fragment
        return binding.getRoot();
    }
}