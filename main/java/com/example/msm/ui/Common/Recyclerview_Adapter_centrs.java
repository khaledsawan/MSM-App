package com.example.msm.ui.Common;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.msm.FirebaseMSMTest.Modles.Center;
import com.example.msm.R;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;


public class Recyclerview_Adapter_centrs extends RecyclerView.Adapter<Recyclerview_Adapter_centrs.CenterViewHolder> {

    private ArrayList<Center> Center;

    private OnItemClickListener mListener;
    public interface OnItemClickListener {
        void onItemClick(int position);
        void onDeleteClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener)
    {
        mListener=listener;
    }

    public  static class CenterViewHolder extends RecyclerView.ViewHolder{

        TextView email;
        TextView password;
        TextView location;

        public CenterViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            email=itemView.findViewById(R.id.center_fragment_email_et);
            password=itemView.findViewById(R.id.center_fragment_password_et);
            location=itemView.findViewById(R.id.center_fragment_location_et);

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


        }
    }

    public Recyclerview_Adapter_centrs(ArrayList<Center> Center) {
        this.Center = Center;
    }
    
    @NonNull
    @Override
    public CenterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_center, parent, false);
        CenterViewHolder evh = new CenterViewHolder(v,mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull CenterViewHolder holder, int position) {


        Center currentItem = Center.get(position);
        holder.email.setText(currentItem.getE_Mail());
        holder.password.setText(currentItem.getPassword());
        holder.location.setText(currentItem.getLocation());

    }



    @Override
    public int getItemCount() {
        return Center.size();
    }
}