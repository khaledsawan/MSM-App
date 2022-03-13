package com.example.msm.ui.UI_Admin.Change_Password_Requestes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.msm.FirebaseMSMTest.Modles.Request;
import com.example.msm.R;

import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;

public class RecyclerView_Adapter_Request extends RecyclerView.Adapter<RecyclerView_Adapter_Request.RequestViewHolder> {

    private ArrayList<Request> Request;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {

        void onOKClick(String  position);
        void onNOClick(String position);
    }

    public void setOnItemClickListener(OnItemClickListener listener)
    {
        mListener=listener;
    }
    public static class RequestViewHolder extends RecyclerView.ViewHolder
    {


        TextView serial_Number;
        TextView from;
        TextView to;
        TextView message;
        TextView type;
        TextView e_Mail;
        TextView date;
        Button Ok;
        Button No;


        public RequestViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            serial_Number=itemView.findViewById(R.id.serial_Number);
            from=itemView.findViewById(R.id.from);
            to=itemView.findViewById(R.id.to);
            type=itemView.findViewById(R.id.type);
            e_Mail=itemView.findViewById(R.id.e_Mail);
            date=itemView.findViewById(R.id.date);
            message=itemView.findViewById(R.id.Message123);
            Ok=itemView.findViewById(R.id.Cancel);
            No=itemView.findViewById(R.id.Confirm);

            Ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onOKClick(serial_Number.getText().toString());
                        }
                    }
                }
            });
            No.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onNOClick(serial_Number.getText().toString());
                        }
                    }
                }
            });
        }


    }

    public RecyclerView_Adapter_Request(ArrayList<Request> Request) {
        this.Request = Request;
    }
    @NonNull
    @NotNull
    @Override
    public RequestViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_request_adapter, parent, false);
        RequestViewHolder evh = new RequestViewHolder(v,mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RequestViewHolder holder, int position) {

        com.example.msm.FirebaseMSMTest.Modles.Request currentItem = Request.get(position);
        holder.serial_Number.setText(currentItem.getSerial_Number());
        holder.from.setText("From : "+currentItem.getFrom());
        holder.to.setText("To : "+currentItem.getTo());
        holder.type.setText("Type : "+currentItem.getType());
        holder.e_Mail.setText("E_Mail : "+currentItem.getE_Mail());
        holder.date.setText("Date : "+currentItem.getDate());
        holder.message.setText("Message : "+currentItem.getMessage());
    }

    @Override
    public int getItemCount() {
        return Request.size();
    }
}
