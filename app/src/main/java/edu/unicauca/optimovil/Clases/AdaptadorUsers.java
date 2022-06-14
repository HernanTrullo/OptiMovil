package edu.unicauca.optimovil.Clases;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import edu.unicauca.optimovil.R;

public class AdaptadorUsers  extends RecyclerView.Adapter<AdaptadorUsers.ViewHoldeProducto> {
    ArrayList<Producto> listProducto;

    public ArrayList<Producto> getListUsers() {
        return listProducto;
    }

    @NonNull
    @Override
    public ViewHoldeProducto onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_productos, null, false);
        return new ViewHoldeProducto(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoldeProducto holder, int position) {
        holder.tvNombre.setText(listProducto.get(position).getNombre());
        holder.tvDescripcion.setText(listProducto.get(position).getDescription());
        holder.ivFoto.setImageResource(listProducto.get(position).getFoto());
    }

    @Override
    public int getItemCount() {
        return listProducto.size();
    }

    public class ViewHoldeProducto extends  RecyclerView.ViewHolder{

        TextView tvNombre;
        TextView tvDescripcion;
        ImageView ivFoto;
        public ViewHoldeProducto(@NonNull View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.nameUser);
            tvDescripcion = itemView.findViewById(R.id.descriptionUser);
            ivFoto = itemView.findViewById(R.id.usersImage);
        }
    }
}
