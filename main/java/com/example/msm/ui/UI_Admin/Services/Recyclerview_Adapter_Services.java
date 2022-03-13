package com.example.msm.ui.UI_Admin.Services;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.msm.FirebaseMSMTest.Modles.Services;
import com.example.msm.R;

import java.util.ArrayList;

public class Recyclerview_Adapter_Services extends RecyclerView.Adapter<Recyclerview_Adapter_Services.ServicesViewHolder> {

    private ArrayList<com.example.msm.FirebaseMSMTest.Modles.Services> Services;

    private OnItemClickListener mListener;
    public interface OnItemClickListener {
        void onItemClick(int position);
        void onDeleteClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener)
    {
        mListener=listener;
    }

    public  static class ServicesViewHolder extends RecyclerView.ViewHolder{

        TextView serial_Number;
        TextView title;
        TextView subject;
        TextView  cost;
        ImageView image_delete;

        public ServicesViewHolder(@NonNull View itemView,final OnItemClickListener listener) {
            super(itemView);
            serial_Number=itemView.findViewById(R.id.C_S_serial_number);
            title=itemView.findViewById(R.id.C_S_title);
            subject=itemView.findViewById(R.id.C_S_subject);
            cost=itemView.findViewById(R.id.C_S_coust);
            image_delete=itemView.findViewById(R.id.C_S_delete_image);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }

                }
            });

            image_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onDeleteClick(position);
                        }
                    }
                }
            });
        }
    }

    public Recyclerview_Adapter_Services(ArrayList<Services> Services) {
        this.Services = Services;
    }
    @NonNull
    @Override
    public ServicesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_services, parent, false);
        ServicesViewHolder evh = new ServicesViewHolder(v,mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder( ServicesViewHolder holder, int position) {

        Services currentItem = Services.get(position);
        holder.serial_Number.setText(currentItem.getSerial_Number());
        holder.title.setText(currentItem.getTitle());
        holder.subject.setText(currentItem.getSubject());
        holder.cost.setText(currentItem.getCost());

    }

    @Override
    public int getItemCount() {
        return Services.size();
    }
}
