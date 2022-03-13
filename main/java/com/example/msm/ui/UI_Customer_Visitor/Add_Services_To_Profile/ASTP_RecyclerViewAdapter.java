package com.example.msm.ui.UI_Customer_Visitor.Add_Services_To_Profile;

import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.msm.FirebaseMSMTest.Modles.Services;
import com.example.msm.R;

import java.util.ArrayList;


public class ASTP_RecyclerViewAdapter extends RecyclerView.Adapter<ASTP_RecyclerViewAdapter.ASViewHolder>{
    ArrayList<Services> servicesArrayList;
    OnItemClickListener listener;

    public ASTP_RecyclerViewAdapter(ArrayList<Services> servicesArrayList, OnItemClickListener listener) {
        this.servicesArrayList = servicesArrayList;
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(Services item);
    }

    //  holder class for recycler view
    class ASViewHolder extends RecyclerView.ViewHolder{
        TextView custom_csr_tv_ID;
        TextView custom_csr_tv_ID_Text;
        TextView custom_csr_tv_Title;
        TextView custom_csr_tv_Title_Text;
        TextView custom_csr_tv_Subject;
        TextView custom_csr_tv_Subject_Text;
        TextView custom_csr_tv_Cost;
        TextView custom_csr_tv_Cost_Text;
        Button custom_csr_btn_Add;

        SharedPreferences sharedPreferences;


        Services item;

        public ASViewHolder(@NonNull View itemView) {
            super(itemView);
            custom_csr_tv_ID = itemView.findViewById(R.id.custom_csr_tv_ID);
            custom_csr_tv_ID_Text = itemView.findViewById(R.id.custom_csr_tv_ID_Text);
            custom_csr_tv_Title = itemView.findViewById(R.id.custom_csr_tv_Title);
            custom_csr_tv_Title_Text = itemView.findViewById(R.id.custom_csr_tv_Title_Text);
            custom_csr_tv_Subject = itemView.findViewById(R.id.custom_csr_tv_Subject);
            custom_csr_tv_Subject_Text = itemView.findViewById(R.id.custom_csr_tv_Subject_Text);
            custom_csr_tv_Cost = itemView.findViewById(R.id.custom_csr_tv_Cost);
            custom_csr_tv_Cost_Text = itemView.findViewById(R.id.custom_csr_tv_Cost_Text);
            custom_csr_btn_Add = itemView.findViewById(R.id.custom_csr_btn_Add);

            custom_csr_btn_Add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(item);
                }
            });

        }

        void bind (Services item){
            this.item = item;
            custom_csr_tv_ID_Text.setText(item.getSerial_Number());
            custom_csr_tv_Title_Text.setText(item.getTitle());
            custom_csr_tv_Subject_Text.setText(item.getSubject());
            custom_csr_tv_Cost_Text.setText(item.getCost());
        }
    }


    @NonNull
    @Override
    public ASViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_astp_recycler_layout,null,false);
        ASViewHolder rtpViewHolder = new ASViewHolder(view);
        return rtpViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ASViewHolder holder, int position) {
        Services items = servicesArrayList.get(position);
        holder.bind(items);
    }

    @Override
    public int getItemCount() {
        return servicesArrayList.size();
    }






}
