package com.example.msm.ui.UI_Admin.Bills;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.msm.FirebaseMSMTest.Modles.Bill;
import com.example.msm.R;

import java.util.ArrayList;

public class Recyclerview_Adapter_Bills extends RecyclerView.Adapter<Recyclerview_Adapter_Bills.BillsViewHolder> {

    private ArrayList<com.example.msm.FirebaseMSMTest.Modles.Bill> Bill;

    public Recyclerview_Adapter_Bills(ArrayList<Bill> Bill) {
        this.Bill = Bill;
    }

    public static class BillsViewHolder extends RecyclerView.ViewHolder
    {
       TextView serial_number;
       TextView data;
       TextView invoice_value;
       TextView email;

        public BillsViewHolder(@NonNull View itemView) {
            super(itemView);
            serial_number=itemView.findViewById(R.id.cbm_serial_number_tv);
            data=itemView.findViewById(R.id.cbm_invoice_issue_date_tv);
            invoice_value=itemView.findViewById(R.id.cbm_invoice_value_tv);
            email=itemView.findViewById(R.id.cbm_email_tv);

        }
    }

    @NonNull
    @Override
    public BillsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_bill_manager, parent, false);
        BillsViewHolder evh = new BillsViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull BillsViewHolder holder, int position) {

        Bill currentItem = Bill.get(position);
        holder.serial_number.setText(new StringBuilder().append("Serial number: ").append(currentItem.getSerial_Number()).toString());
        holder.data.setText(new StringBuilder().append("Date: ").append(currentItem.getDate()).toString());
        holder.email.setText(new StringBuilder().append("Email: ").append(currentItem.getE_Mail()).toString());
        holder.invoice_value.setText(new StringBuilder().append("Invoice value: ").append(currentItem.getInvoice_Value()).toString());
    }

    @Override
    public int getItemCount() {
        return Bill.size();
    }
}
