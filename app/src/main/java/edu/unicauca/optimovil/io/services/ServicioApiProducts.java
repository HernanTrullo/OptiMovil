package edu.unicauca.optimovil.io.services;

import edu.unicauca.optimovil.io.interfaces.ProductsInterface;

public class ServicioApiProducts extends Servicio {
    private static ProductsInterface APP_SERVICIO;
    public static ProductsInterface getAppServicio(){
        if(APP_SERVICIO == null){
            APP_SERVICIO = getAppRetrofit().create(ProductsInterface.class);
        }
        return APP_SERVICIO;
    }
}
