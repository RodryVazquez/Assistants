package com.example.rodrigo.applicationassistant.project.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.rodrigo.applicationassistant.R;
import com.example.rodrigo.applicationassistant.project.Models.AssistanceModel;
import java.util.List;

/**
 * Adapter para el listado de asistentes
 */
public class AssistanceAdapter extends RecyclerView.Adapter<AssistanceAdapter.AssistancesViewHolder> {

    /**
     *
     */
    List<AssistanceModel> assistanceModels;

    /**
     *
     * @param assistanceModels
     */
    public AssistanceAdapter(List<AssistanceModel> assistanceModels) {
        this.assistanceModels = assistanceModels;
    }

    /**
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public AssistancesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.sync_list_adapter,parent,false);
        AssistancesViewHolder holder = new AssistancesViewHolder(itemView);
        return holder;
    }

    /**
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(AssistancesViewHolder holder, int position) {
        AssistanceModel assistanceModel = assistanceModels.get(position);
        holder.bindAssistance(assistanceModel);
    }

    /**
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return assistanceModels.size();
    }

    /**
     *
     */
    static class AssistancesViewHolder extends RecyclerView.ViewHolder{

        private TextView txtNameUser;
        private TextView txtAddress;
        private TextView txtPhoneNumber;

        /**
         *
         * @param itemView
         */
        public AssistancesViewHolder(View itemView) {
            super(itemView);
            txtNameUser = (TextView)itemView.findViewById(R.id.txtCompleteName);
            txtAddress = (TextView)itemView.findViewById(R.id.txtAddress);
            txtPhoneNumber = (TextView)itemView.findViewById(R.id.txtPhoneNumber);
        }

        /**
         *
         * @param assistanceModel
         */
        public void bindAssistance(AssistanceModel assistanceModel){
            txtNameUser.setText(assistanceModel.getAssistantName());
            txtAddress.setText(assistanceModel.getAddress());
            txtPhoneNumber.setText(String.valueOf(assistanceModel.getPhoneNumber()));
        }
    }

}
