package com.example.msm.ui.UI_Admin.Customer;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.msm.FirebaseMSMTest.Service.AppBarListener;
import com.example.msm.FirebaseMSMTest.Modles.Customer;
import com.example.msm.FirebaseMSMTest.Service.CustomerConnect;
import com.example.msm.R;


public class Customer_Fragment extends Fragment {

    ImageView imageView;
    TextView Firstname;
    TextView Lastname;
    TextView username;
    TextView phonenumber;
    TextView password;
    TextView activitystate;
    TextView speed_adsl;
    TextView account_point_value;

    private static final String ARG_PARAM1 = "e_Mail";
    private String e_Mail = new String();

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
        open_listener.onNewFragmentOpen("Customer");
    }

    public Customer_Fragment() {
        // Required empty public constructor
    }

    public static Customer_Fragment newInstance(String e_Mail) {
        Customer_Fragment fragment = new Customer_Fragment();

        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, e_Mail);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            e_Mail = (String) bundle.getSerializable("w");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_customer, null, false);

        imageView=v.findViewById(R.id.C_F_imageview);
        Firstname=v.findViewById(R.id.C_F_Firstname_tv);
        Lastname=v.findViewById(R.id.C_F_Lastname_tv);
        username=v.findViewById(R.id.C_F_user_name);
        phonenumber=v.findViewById(R.id.C_F_phone_number);
        password=v.findViewById(R.id.C_F_password);
        activitystate=v.findViewById(R.id.C_F_activae_state);
        speed_adsl=v.findViewById(R.id.C_F_speed_adsl);
        account_point_value=v.findViewById(R.id.C_F_account_point_value);



        CustomerConnect.getItem(e_Mail, new CustomerConnect.CustomerConnectCallBack() {
            @Override
            public void getItemFinished(Customer customer) {

                if(customer != null){
                    Firstname.setText(new StringBuilder().append("First name :").append(customer.getfName()).toString());
                    Lastname .setText(new StringBuilder().append("Last name :").append(customer.getlName()).toString());
                    username.setText(new StringBuilder().append("User name :").append(customer.getE_Mail()).toString());
                    password.setText(new StringBuilder().append("password  :").append(customer.getPassword()).toString());
                    activitystate.setText(new StringBuilder().append("Activity state :").append(customer.isAccount_Status()).toString());
                    speed_adsl.setText(new StringBuilder().append("Speed_ADSL :").append(customer.getInternet_Speed()).toString());
                    account_point_value.setText(new StringBuilder().append("invoice value:").append(customer.getInvoice_Value()).toString());
                    phonenumber.setText(new StringBuilder().append("Phone Number :").append(customer.getPhone_Number()).toString());
                }

            }
        });


        return v;
    }
}