package com.example.rodrigo.applicationassistant.project.Activitys;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.rodrigo.applicationassistant.R;
import com.example.rodrigo.applicationassistant.project.Fragments.AssistanceCreateFragment;
import com.example.rodrigo.applicationassistant.project.Fragments.AssistancesListFragment;
import com.example.rodrigo.applicationassistant.project.Models.UserModel;
import com.example.rodrigo.applicationassistant.project.Preferences.UserPreferences;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //Variables globales
    private static  final String TAG="MainActivity";
    private UserPreferences userPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Indicamos el nombre  y correo del usuario en el navigation View
        View header = navigationView.getHeaderView(0);
        TextView txtNameUser = (TextView)header.findViewById(R.id.txtNameUser);
        TextView txtEmailUser = (TextView)header.findViewById(R.id.txtEmailUser);

        UserModel user =  UserModel.first(UserModel.class);
        if(user != null){
            txtNameUser.setText(user.getFirstName() +" "+ user.getLastName() +" "+user.getLastName2());
            txtEmailUser.setText(user.getEmail());
        }
        //Indicamos el item por defecto al cargar el main
        if(savedInstanceState == null){
            MenuItem item = navigationView.getMenu().getItem(0);
            onNavigationItemSelected(item);
        }
    }

    /**
     *
     */
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            moveTaskToBack(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment = null;
        switch (id){

            case R.id.nav_list_assistances:
                fragment = new AssistancesListFragment();
                break;
            case R.id.nav_assistant:
                fragment = new AssistanceCreateFragment();
                break;
            case R.id.nav_close_session:
                //Preferencias
                userPreferences = new UserPreferences(MainActivity.this);
                userPreferences.logoutUser();
                this.finish();
        }

        if(fragment != null){
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.container, fragment).commit();
            item.setChecked(true);

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;
        }else{
            Log.e(TAG,"Error al crear el fragmento");
        }

        return false;
    }
}
