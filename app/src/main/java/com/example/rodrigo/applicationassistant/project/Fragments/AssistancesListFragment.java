package com.example.rodrigo.applicationassistant.project.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rodrigo.applicationassistant.R;
import com.example.rodrigo.applicationassistant.project.Adapters.AssistanceAdapter;
import com.example.rodrigo.applicationassistant.project.Models.AssistanceModel;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class AssistancesListFragment extends Fragment {

    //
    private RecyclerView assistanceView;

    /**
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_assistances_list, container, false);
    }

    /**
     *
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //Obtenemos la refecncia del toolbar y asignamos su titulo
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.mainToolbar);
        toolbar.setTitle("Asistentes");

        //Habiliatmos las opciones del action bar
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);

        //Obtenemos la referencia del drawer y habilitamos el click en el tolbar para el navigationView
        DrawerLayout drawer = (DrawerLayout)getActivity().findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                getActivity(), drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();

        //RecyclerView
        assistanceView = (RecyclerView)getActivity().findViewById(R.id.lstAssistances);
        assistanceView.setHasFixedSize(true);
        assistanceView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));

        //Asignamos el adapter al recyclerView
        AssistanceAdapter assistanceAdapter = new AssistanceAdapter(setData());
        assistanceView.setAdapter(assistanceAdapter);

        //Referenciamos al floatActionButton de la vista
        FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fltSyncronize);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    /**
     *
     * @return
     */
    public List<AssistanceModel> setData()
    {
        List<AssistanceModel>assistanceModels = new ArrayList<>();
        assistanceModels.add(new AssistanceModel("Rodry vazquez","Calle 23 #1519",1,"Rodrigo.vazquez@intelectix.com","TODO","TODO",true,"asdasd"));
        assistanceModels.add(new AssistanceModel("Rodry vazquez","Calle 23 #1519",1,"Rodrigo.vazquez@intelectix.com","TODO","TODO",true,"asdasd"));
        assistanceModels.add(new AssistanceModel("Rodry vazquez","Calle 23 #1519",1,"Rodrigo.vazquez@intelectix.com","TODO","TODO",true,"asdasd"));
        assistanceModels.add(new AssistanceModel("Rodry vazquez","Calle 23 #1519",1,"Rodrigo.vazquez@intelectix.com","TODO","TODO",true,"asdasd"));
        assistanceModels.add(new AssistanceModel("Rodry vazquez","Calle 23 #1519",1,"Rodrigo.vazquez@intelectix.com","TODO","TODO",true,"asdasd"));
        assistanceModels.add(new AssistanceModel("Rodry vazquez","Calle 23 #1519",1,"Rodrigo.vazquez@intelectix.com","TODO","TODO",true,"asdasd"));
        assistanceModels.add(new AssistanceModel("Rodry vazquez","Calle 23 #1519",1,"Rodrigo.vazquez@intelectix.com","TODO","TODO",true,"asdasd"));
        assistanceModels.add(new AssistanceModel("Rodry vazquez","Calle 23 #1519",1,"Rodrigo.vazquez@intelectix.com","TODO","TODO",true,"asdasd"));
        assistanceModels.add(new AssistanceModel("Rodry vazquez","Calle 23 #1519",1,"Rodrigo.vazquez@intelectix.com","TODO","TODO",true,"asdasd"));
        assistanceModels.add(new AssistanceModel("Rodry vazquez","Calle 23 #1519",1,"Rodrigo.vazquez@intelectix.com","TODO","TODO",true,"asdasd"));
        assistanceModels.add(new AssistanceModel("Rodry vazquez","Calle 23 #1519",1,"Rodrigo.vazquez@intelectix.com","TODO","TODO",true,"asdasd"));

        return assistanceModels;

    }
}
