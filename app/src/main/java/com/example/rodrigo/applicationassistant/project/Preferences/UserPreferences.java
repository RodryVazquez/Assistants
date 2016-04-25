package com.example.rodrigo.applicationassistant.project.Preferences;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.rodrigo.applicationassistant.project.LoginActivity;

/**
 * Preferencias del usuario
 */
public class UserPreferences {

    private Context context;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    int PRIVATE_MODE = 0;
    
    private static final String PREF_NAME ="UserPreferences_ApplicationAssistant";
    private static final String IS_FIRST_TIME = "IsFirstTime";


    /**
     *
     * @param context
     */
    public UserPreferences(Context context){
        this.context = context;
        this.preferences = context.getSharedPreferences(PREF_NAME,PRIVATE_MODE);
        this.editor = preferences.edit();
    }

    /**
     * Consultamos si es la primera vez que inicia sesion
     * @return
     */
    public boolean isFirstTime(){
        return preferences.getBoolean(IS_FIRST_TIME,true);
    }

    /**
     * Creamos las preferencias del usuario
     * @param login
     */
    public void createLogin(boolean login){
        if(login){
            editor.putBoolean(IS_FIRST_TIME,false);
            editor.commit();
        }
    }

    /**
     * Limpiamos las preferencias del usuario
     */
    public void logoutUser(){
        editor.clear();
        editor.commit();
        Intent intent = new Intent(context, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);

    }
}
