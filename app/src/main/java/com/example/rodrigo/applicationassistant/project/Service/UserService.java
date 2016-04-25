package com.example.rodrigo.applicationassistant.project.Service;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.service.voice.VoiceInteractionSession;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.rodrigo.applicationassistant.project.Activitys.MainActivity;
import com.example.rodrigo.applicationassistant.project.App.AppController;
import com.example.rodrigo.applicationassistant.project.Models.UserModel;
import com.example.rodrigo.applicationassistant.project.Preferences.UserPreferences;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Servicio para el usuario
 */
public class UserService {

    private static final String TAG = "UserService";
    private static final String URL_LOGIN = "http://assistants.azurewebsites.net/api/user/login";
    private Context context;
    private UserPreferences userPreferences;

    /**
     * @param context
     */
    public UserService(Context context) {
        this.context = context;
        this.userPreferences = new UserPreferences(context);
    }

    /**
     * @param email
     * @param password
     */
    public void loginUser(String email, String password) {

        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Validando usuario");
        progressDialog.show();

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("Email", email);
            jsonObject.put("Password", password);
        } catch (JSONException e) {
            Log.e(TAG, e.getMessage());
        }

        JsonObjectRequest request =
                new JsonObjectRequest(Request.Method.POST, URL_LOGIN, jsonObject,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    String userId = response.getString("UserId");
                                    String firstName = response.getString("FirstName");
                                    String lastName = response.getString("LastName");
                                    String lastName2 = response.getString("LastName2");
                                    String email = response.getString("Email");
                                    String phoneNumber = response.getString("PhoneNumber");

                                    //Creamos al usuario y guardamos preferencias del inicio de sesion
                                    UserModel userModel = new UserModel(userId, firstName, lastName, lastName2, phoneNumber, email);
                                    userModel.save();
                                    userPreferences.createLogin(true);

                                    //Redireccionamos a la vista principal
                                    Intent intent = new Intent(context, MainActivity.class);
                                    context.startActivity(intent);
                                    ((Activity) context).finish();

                                } catch (JSONException e) {
                                    Log.e(TAG, e.getMessage());
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                VolleyLog.e("Response", "Error" + error.getMessage());
                                NetworkResponse networkResponse = error.networkResponse;
                                //Si el error proviene del servidor
                                if (networkResponse != null && networkResponse.data != null) {
                                    try {
                                        String stringData = new String(networkResponse.data);
                                        JsonParser parser = new JsonParser();
                                        JsonObject json = parser.parse(stringData).getAsJsonObject();
                                        String messge = json.get("Message").toString();
                                        Toast.makeText(context, messge, Toast.LENGTH_SHORT).show();
                                    } catch (JsonParseException ex) {
                                        Log.e(TAG, ex.getMessage());
                                    }
                                } else {
                                    Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                                if (progressDialog != null && progressDialog.isShowing()) {
                                    progressDialog.dismiss();
                                }
                            }
                        });
        AppController.getInstance().addToRequestQueue(request);
    }
}
