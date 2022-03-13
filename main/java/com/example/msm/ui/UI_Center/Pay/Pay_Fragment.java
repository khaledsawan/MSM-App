package com.example.msm.ui.UI_Center.Pay;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.msm.FirebaseMSMTest.Service.AppBarListener;
import com.example.msm.FirebaseMSMTest.Modles.Bill;
import com.example.msm.FirebaseMSMTest.Service.BillConnect;
import com.example.msm.FirebaseMSMTest.Modles.Customer;
import com.example.msm.FirebaseMSMTest.Service.CustomerConnect;
import com.example.msm.R;

import java.util.HashMap;


public class Pay_Fragment extends Fragment {


    private TextView pos_pay_bill_bill_amount;
    private EditText pos_pay_bill_value_of_cash;
    private Button pos_pay_bill_btn_confirm;

    private Customer current_Customer;
    private String e_Mail;

    private AppBarListener open_listener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof AppBarListener){
            open_listener = (AppBarListener) context;
        }else{
            throw new ClassCastException("Your activity doesn't implements AppBarListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        open_listener= null;
    }

    @Override
    public void onResume() {
        super.onResume();
        open_listener.onNewFragmentOpen("Pay");
    }

    public Pay_Fragment() {
        // Required empty public constructor
    }


    public static Pay_Fragment newInstance(Customer customer) {
        Pay_Fragment fragment = new Pay_Fragment();
        Bundle args = new Bundle();



        args.putString("e_Mail",customer.getE_Mail());

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle  = getArguments();
        if (bundle != null) {
            e_Mail = bundle.getString("e_Mail");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pay, container, false);

        pos_pay_bill_bill_amount = view.findViewById(R.id.pos_pay_bill_bill_amount);
        pos_pay_bill_value_of_cash = view.findViewById(R.id.pos_pay_bill_value_of_cash);
        pos_pay_bill_btn_confirm = view.findViewById(R.id.pos_pay_bill_btn_confirm);



        CustomerConnect.getItem(e_Mail, new CustomerConnect.CustomerConnectCallBack() {
            @Override
            public void getItemFinished(Customer customer) {
                Integer f ;
                switch (customer.getInternet_Speed()){
                    case 1:
                        customer.setInvoice_Value(customer.getInvoice_Value()+1000);
                        break;
                    case 2:
                        customer.setInvoice_Value(customer.getInvoice_Value()+2000);
                        break;
                    case 4:
                        customer.setInvoice_Value(customer.getInvoice_Value()+4000);
                        break;
                    case 8:
                        customer.setInvoice_Value(customer.getInvoice_Value()+8000);
                        break;

                }
                pos_pay_bill_bill_amount.setText(customer.getInvoice_Value()+"");
                current_Customer = customer;
            }
        });

        pos_pay_bill_btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap hashMap = new HashMap();
                if(pos_pay_bill_value_of_cash.getText().toString().equals("")){
                    Toast.makeText(getActivity(), "Wrong input", Toast.LENGTH_SHORT).show();
                }else{
                    Integer New = Integer.valueOf(pos_pay_bill_value_of_cash.getText().toString());
                    Integer Old = current_Customer.getInvoice_Value();
                    if(Old-New == 0){
                        hashMap.put("invoice_Value",0);
                        CustomerConnect.upDate(e_Mail,hashMap);
                        BillConnect.setItem(new Bill(current_Customer.getInvoice_Value()+"",current_Customer.getE_Mail()));

                    }else{
                        Toast.makeText(getActivity(), "Wrong input", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        return view;
    }
}