package com.example.msm.ui.UI_Admin.Advertisements;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.msm.FirebaseMSMTest.Modles.Advertisements;
import com.example.msm.R;

import java.util.ArrayList;

public class Recyclerview_Adapter_Advertisement_And_Display extends RecyclerView.Adapter<Recyclerview_Adapter_Advertisement_And_Display.Ad_and_Di_ViewHolder> {
    private final ArrayList<com.example.msm.FirebaseMSMTest.Modles.Advertisements> Advertisements;

    private OnItemClickListener  mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
        void onDeleteClick(String Serial_number);
    }

    public void setOnItemClickListener(OnItemClickListener listener)
    {
        mListener=listener;
    }

    public static class Ad_and_Di_ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView subject;
        TextView Serial_number;
        ImageView imagedelet;

        public Ad_and_Di_ViewHolder(View itemView , final OnItemClickListener listener) {
            super(itemView);
            title = itemView.findViewById(R.id.Ad_title);
            subject = itemView.findViewById(R.id.Ad_text);
            Serial_number = itemView.findViewById(R.id.Ad_serial_number_tv);
            imagedelet=itemView.findViewById(R.id.ic_delete_forever);

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
            imagedelet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                            listener.onDeleteClick(Serial_number.getText().toString());
                }
            });
        }
    }

    public Recyclerview_Adapter_Advertisement_And_Display(ArrayList<Advertisements> Advertisements) {
        this.Advertisements = Advertisements;
    }

    @NonNull
    @Override
    public Ad_and_Di_ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_advertisement_and_displa, parent, false);
        Ad_and_Di_ViewHolder evh = new Ad_and_Di_ViewHolder(v,mListener);
        return evh;
    }
    @Override
    public void onBindViewHolder( Ad_and_Di_ViewHolder holder, int position) {

        Advertisements currentItem = Advertisements.get(position);
        holder.title.setText(new StringBuilder().append("title: ").append(currentItem.getTitle()).toString());
        holder.subject.setText(new StringBuilder().append("Subject: ").append(currentItem.getSubject()).toString());
        holder.Serial_number.setText(new StringBuilder().append("Serial number: ").append(currentItem.getSerial_Number()).toString());
    }
    @Override
    public int getItemCount() {
        return Advertisements.size();
    }

}







