package edu.unicauca.optimovil.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import edu.unicauca.optimovil.Actividades.VentanaCarrito;
import edu.unicauca.optimovil.Actividades.VentanaClientes;
import edu.unicauca.optimovil.Actividades.VentanaInformacionProductos;
import edu.unicauca.optimovil.Actividades.VentanaPrincipal;
import edu.unicauca.optimovil.Actividades.VentanaProducto;
import edu.unicauca.optimovil.Actividades.VentanaProductosMeGusta;
import edu.unicauca.optimovil.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BotonesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BotonesFragment extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private LinearLayout llSearch, llHome, llLikes, llFilter, llSettings, llShare;
    private ImageButton ibtnSearch, ibtnHome, ibtnLikes, ibtnFilter, ibtnSettings, ibtnShare;


    public BotonesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BotonesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public BotonesFragment newInstance(String param1, String param2) {
        BotonesFragment fragment = new BotonesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_botones, container, false);
        llSearch = view.findViewById(R.id.layout_search);
        llHome = view.findViewById(R.id.layout_home);
        llLikes = view.findViewById(R.id.layout_likes);
        llFilter = view.findViewById(R.id.layout_filter);
        llSettings = view.findViewById(R.id.layout_settings);
        llShare = view.findViewById(R.id.layout_share);

        ibtnSearch = view.findViewById(R.id.ibtn_search);
        ibtnHome = view.findViewById(R.id.ibtn_home);
        ibtnLikes = view.findViewById(R.id.ibtn_likes);
        ibtnFilter = view.findViewById(R.id.ibtn_filter);
        ibtnSettings = view.findViewById(R.id.ibtn_settings);
        ibtnShare = view.findViewById(R.id.ibtn_settings);

        asignarCLick(view);

        cargarBontones(view);
        return view;
    }

    private void asignarCLick(View view) {
        ibtnSearch.setOnClickListener(this);
        ibtnHome.setOnClickListener(this);
        ibtnLikes.setOnClickListener(this);
        ibtnFilter.setOnClickListener(this);
        ibtnSettings.setOnClickListener(this);
        ibtnShare.setOnClickListener(this);
    }

    private void cargarBontones(View view){
        if (VentanaPrincipal.class.equals(view.getContext().getClass())) {
            llSearch.setVisibility(View.VISIBLE);
            llHome.setVisibility(View.GONE);
            llLikes.setVisibility(View.VISIBLE);
            llFilter.setVisibility(View.GONE);
            llSettings.setVisibility(View.VISIBLE);
            llShare.setVisibility(View.GONE);
        }
        if (VentanaProducto.class.equals(view.getContext().getClass())) {
            llSearch.setVisibility(View.VISIBLE);
            llHome.setVisibility(View.GONE);
            llLikes.setVisibility(View.VISIBLE);
            llFilter.setVisibility(View.VISIBLE);
            llSettings.setVisibility(View.VISIBLE);
            llShare.setVisibility(View.GONE);
        }
        if (VentanaInformacionProductos.class.equals(view.getContext().getClass())) {
            llSearch.setVisibility(View.GONE);
            llHome.setVisibility(View.VISIBLE);
            llLikes.setVisibility(View.VISIBLE);
            llFilter.setVisibility(View.GONE);
            llSettings.setVisibility(View.VISIBLE);
            llShare.setVisibility(View.VISIBLE);
        }
        if (VentanaProductosMeGusta.class.equals(view.getContext().getClass())) {
            llSearch.setVisibility(View.GONE);
            llHome.setVisibility(View.VISIBLE);
            llLikes.setVisibility(View.GONE);
            llFilter.setVisibility(View.GONE);
            llSettings.setVisibility(View.VISIBLE);
            llShare.setVisibility(View.VISIBLE);
        }
        if (VentanaCarrito.class.equals(view.getContext().getClass())) {
            llSearch.setVisibility(View.GONE);
            llHome.setVisibility(View.VISIBLE);
            llLikes.setVisibility(View.VISIBLE);
            llFilter.setVisibility(View.GONE);
            llSettings.setVisibility(View.VISIBLE);
            llShare.setVisibility(View.GONE);
        }
        Log.d("cargarBontones: ", view.getContext().toString() );

    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()){
            case R.id.ibtn_home:
                intent = new Intent(view.getContext(), VentanaPrincipal.class);
                startActivity(intent);
            break;
            case R.id.ibtn_likes:
                intent = new Intent(view.getContext(), VentanaProductosMeGusta.class);
                startActivity(intent);
                break;
            case R.id.ibtn_filter:
/*                intent = new Intent(view.getContext(), VentanaProductosMeGusta.class);
                startActivity(intent);*/
                break;
            case R.id.ibtn_settings:
                intent = new Intent(view.getContext(), VentanaClientes.class);
                startActivity(intent);
                break;
        }
    }
}