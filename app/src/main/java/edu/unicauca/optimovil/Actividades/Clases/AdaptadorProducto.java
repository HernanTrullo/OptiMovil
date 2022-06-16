package edu.unicauca.optimovil.Actividades.Clases;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import edu.unicauca.optimovil.Actividades.VentanaInformacionProductos;
import edu.unicauca.optimovil.Actividades.VentanaPrincipal;
import edu.unicauca.optimovil.Actividades.VentanaProducto;
import edu.unicauca.optimovil.R;

public class AdaptadorProducto extends RecyclerView.Adapter<AdaptadorProducto.ViewHolderProductos> {

    public static final String EXTRA_MENSAJE = "edu.unicauca.optimovil.PRODUCTO.INFORMACION";
    ArrayList<Producto> listaProductos;
    Context mContext;
    public AdaptadorProducto(ArrayList<Producto> listaProductos, Context context) {
        this.listaProductos = listaProductos;
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolderProductos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.lista_productos,parent,false);
        return new ViewHolderProductos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderProductos holder, int position) {
        holder.tvNombre.setText(listaProductos.get(position).getNombre());
        holder.tvDescripcion.setText(listaProductos.get(position).getDescripcion());
        holder.ivFoto.setImageResource(listaProductos.get(position).getFoto());
        holder.tvNombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Pasa el contexto en el primer parametro
                Intent intent = new Intent(holder.itemView.getContext(),VentanaInformacionProductos.class);
                String mensaje = "Información producto";
                intent.putExtra(EXTRA_MENSAJE, mensaje);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaProductos.size();
    }

    public class ViewHolderProductos extends RecyclerView.ViewHolder {
        TextView tvNombre;
        TextView tvDescripcion;
        ImageView ivFoto;
        public ViewHolderProductos(@NonNull View itemView) {
            super(itemView);
            tvNombre = (TextView) itemView.findViewById(R.id.nombreProducto);
            tvDescripcion = (TextView) itemView.findViewById(R.id.descripcionProducto);
            ivFoto = (ImageView) itemView.findViewById(R.id.fotoProducto);
        }
    }
}
