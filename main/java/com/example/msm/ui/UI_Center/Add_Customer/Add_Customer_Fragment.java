package com.example.msm.ui.UI_Center.Add_Customer;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.msm.FirebaseMSMTest.Service.AppBarListener;
import com.example.msm.FirebaseMSMTest.Modles.Customer;
import com.example.msm.FirebaseMSMTest.Service.CustomerConnect;
import com.example.msm.R;


public class Add_Customer_Fragment extends Fragment {


    private EditText et_firstname,et_lastname,et_username,et_password,et_customer_phone,et_speed_adsl,et_account_point;
    private RadioButton rb_male,rb_female;
    private Button btn_add;

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
        open_listener.onNewFragmentOpen("Add customer");
    }

    public Add_Customer_Fragment() {
        // Required empty public constructor
    }



    public static Add_Customer_Fragment newInstance() {
        Add_Customer_Fragment fragment = new Add_Customer_Fragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_customer, container, false);

        et_firstname = view.findViewById(R.id.pos_add_cus_et_firstname);
        et_lastname = view.findViewById(R.id.pos_add_cus_et_lastname);
        et_username = view.findViewById(R.id.pos_add_cus_et_user_name_in_system);
        et_password = view.findViewById(R.id.pos_add_cus_et_password);
        et_customer_phone = view.findViewById(R.id.pos_add_cus_et_phone);
        et_speed_adsl = view.findViewById(R.id.pos_add_cus_et_speed_adsl);
        et_account_point = view.findViewById(R.id.pos_add_cus_et_account_point_value);
        rb_male = view.findViewById(R.id.pos_add_cus_RB_male);
        rb_female = view.findViewById(R.id.pos_add_cus_RB_female);
        btn_add = view.findViewById(R.id.pos_add_cus_btn_ADD);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstname = et_firstname.getText().toString();
                String lastname = et_lastname.getText().toString();
                String e_mail = et_username.getText().toString();
                String password = et_password.getText().toString();
                String phone = et_customer_phone.getText().toString();
                int speed_adsl = new Integer(et_speed_adsl.getText().toString());
                double account_point = new Double(et_account_point.getText().toString());
                boolean male = rb_male.isChecked();
                boolean female = rb_female.isChecked();
                Customer customer = null;
                if (male) {
                    customer = new Customer(e_mail,firstname,lastname,password,"Male",phone,speed_adsl,null , true,0,null,null);
                } else if (female) {
                    customer = new Customer(e_mail,firstname,lastname,password,"Female",phone,speed_adsl,null,true,0,null,null);
                }
                CustomerConnect.setItem(customer);
            }
        });

        return view;
    }
}