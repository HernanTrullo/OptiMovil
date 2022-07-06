package edu.unicauca.optimovil.io.services;

import edu.unicauca.optimovil.io.interfaces.ClientsInterface;

public class ServicioApiClients extends Servicio {
    private static ClientsInterface APP_SERVICIO;
    public static ClientsInterface getAppServicio(){
        if(APP_SERVICIO == null){
            APP_SERVICIO = getAppRetrofit().create(ClientsInterface.class);
        }
        return APP_SERVICIO;
    }
}
