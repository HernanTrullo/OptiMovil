package edu.unicauca.optimovil.io.services;

import edu.unicauca.optimovil.io.interfaces.CategoriesInterface;

public class ServicioApiCategories extends Servicio{
    private static CategoriesInterface APP_SERVICIO;
    public static CategoriesInterface getAppServicio(){
        if(APP_SERVICIO == null){
            APP_SERVICIO = getAppRetrofit().create(CategoriesInterface.class);
        }
        return APP_SERVICIO;
    }
}
