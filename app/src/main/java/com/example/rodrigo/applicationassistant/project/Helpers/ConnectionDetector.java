package com.example.rodrigo.applicationassistant.project.Helpers;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Clase helper para detectar la conexion a internet
 */
public class ConnectionDetector {

    //Contexto de la aplicacion
    private Context context;

    /**
     * Seteamos el contexto
     * @param context
     */
    public ConnectionDetector(Context context){
        this.context = context;
    }

    /**
     * Retorna true si existe conexion a internet, de lo contrario falso
     * @return
     */
    public boolean isConnectingToInternet(){
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return  activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}
