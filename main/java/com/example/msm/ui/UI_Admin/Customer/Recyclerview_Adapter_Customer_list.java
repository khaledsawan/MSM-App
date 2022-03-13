package com.example.msm.ui.UI_Admin.Customer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.msm.FirebaseMSMTest.Modles.Customer;
import com.example.msm.R;

import java.util.ArrayList;

public class Recyclerview_Adapter_Customer_list extends RecyclerView.Adapter<Recyclerview_Adapter_Customer_list.Customer_List_ViewHolder> {

    private ArrayList<Customer> Customer;

    private OnItemClickListener mListener;
    public interface OnItemClickListener {
        void onItemClick(String position);
    }

    public void setOnItemClickListener(OnItemClickListener listener)
    {
        mListener=listener;
    }

    public Recyclerview_Adapter_Customer_list(ArrayList<com.example.msm.FirebaseMSMTest.Modles.Customer> Customer) {
        this.Customer = Customer;
    }

    public static class  Customer_List_ViewHolder  extends RecyclerView.ViewHolder
    {
        TextView Firstname;
        TextView Lastname;
        TextView phone_number;
        TextView activate_state;
        TextView speed_Adsl;
        TextView user_name;

        public Customer_List_ViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            Firstname=itemView.findViewById(R.id.C_C_firstname);
            Lastname=itemView.findViewById(R.id.C_C_lastname);
            phone_number=itemView.findViewById(R.id.C_C_phonenumber);
            activate_state=itemView.findViewById(R.id.C_C_activate_state);
            speed_Adsl=itemView.findViewById(R.id.C_C_speed_adsl);
            user_name=itemView.findViewById(R.id.C_C_username);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(user_name.getText().toString());

                }
            });
        }
    }


    @NonNull
    @Override
    public Customer_List_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_customer, parent, false);
      Customer_List_ViewHolder evh = new Customer_List_ViewHolder(v,mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull Customer_List_ViewHolder holder, int position) {

        Customer currentItem = Customer.get(position);

        holder.Firstname.setText(currentItem.getfName());
        holder.Lastname.setText(currentItem.getlName());
        holder.phone_number.setText(currentItem.getPhone_Number());
        if(currentItem.isAccount_Status()==true)
           {
                holder.activate_state.setText("true");
           }

        else
            {
                holder.activate_state.setText("false");
            }
        holder.speed_Adsl.setText(currentItem.getInternet_Speed()+"");
        holder.user_name.setText(currentItem.getE_Mail());

    }
    @Override
    public int getItemCount() {
        return Customer.size();
    }

    public Customer getItem(int position) {
        return Customer.get(position);
    }
}
