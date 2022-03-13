package com.example.msm.ui.UI_Center.Customer_Service_Interface;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.msm.FirebaseMSMTest.Service.AppBarListener;
import com.example.msm.FirebaseMSMTest.Modles.Customer;
import com.example.msm.FirebaseMSMTest.Service.CustomerConnect;
import com.example.msm.R;
import com.example.msm.ui.UI_Center.Edit_Customer.Edit_Customer_Fragment;
import com.example.msm.ui.UI_Center.Pay.Pay_Fragment;

import java.util.HashMap;


public class Customer_Services_Interface_Fragment extends Fragment {


    EditText et_username,et_password;
    TextView tv_result;
    Button btn_deactivate_the_account,btn_deactivate_a_service,btn_pay_bill,btn_search_on_account,btn_edit_profile;
    Customer current_customer;

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
        open_listener.onNewFragmentOpen("Customer services");
    }

    public Customer_Services_Interface_Fragment() {
        // Required empty public constructor
    }

    public static Customer_Services_Interface_Fragment newInstance() {
        Customer_Services_Interface_Fragment fragment = new Customer_Services_Interface_Fragment();
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
        View view = inflater.inflate(R.layout.fragment_customer__services__interface_, container, false);

        et_username = view.findViewById(R.id.pos_pos_service_et_username);
        et_password = view.findViewById(R.id.pos_pos_service_et_password);
        btn_deactivate_the_account = view.findViewById(R.id.pos_pos_service_btn_deactivate_account);
        btn_deactivate_a_service = view.findViewById(R.id.pos_pos_service_btn_deactivate_service);
        btn_pay_bill = view.findViewById(R.id.pos_pos_service_btn_pay_cash);
        btn_search_on_account = view.findViewById(R.id.pos_pos_service_btn_deactivate_search_on_account);
        tv_result = view.findViewById(R.id.pos_pos_service_deactivate_tv_result);
        btn_edit_profile = view.findViewById(R.id.pos_pos_service_btn_edit_profile);






        btn_search_on_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomerConnect.getItem(et_username.getText().toString(), new CustomerConnect.CustomerConnectCallBack() {
                    @Override
                    public void getItemFinished(Customer customer) {
                        if(customer != null && customer.getPassword().equals(et_password.getText().toString())){
                            current_customer = customer;
                            tv_result.setText("Account found");
                            btn_deactivate_a_service.setVisibility(View.VISIBLE);
                            btn_pay_bill.setVisibility(View.VISIBLE);
                            btn_deactivate_the_account.setVisibility(View.VISIBLE);
                            btn_edit_profile.setVisibility(View.VISIBLE);
                        }else{
                            tv_result.setText("This Account not found");
                            btn_deactivate_a_service.setVisibility(View.INVISIBLE);
                            btn_pay_bill.setVisibility(View.INVISIBLE);
                            btn_deactivate_the_account.setVisibility(View.INVISIBLE);
                            btn_edit_profile.setVisibility(View.INVISIBLE);
                        }
                    }
                });
            }
        });

        btn_pay_bill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.nav_host_fragment,new Pay_Fragment().newInstance(current_customer));
                fr.addToBackStack(null);
                fr.commit();
            }
        });

        btn_deactivate_a_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                FragmentTransaction fr = getFragmentManager().beginTransaction();
//                fr.replace(R.id.nav_host_fragment,new fragment_customer_service());
//                fr.addToBackStack(null);
//                fr.commit();
            }
        });

        btn_deactivate_the_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(current_customer.isAccount_Status()){
                    HashMap hashMap = new HashMap();
                    hashMap.put("account_Status",false);
                    CustomerConnect.upDate(et_username.getText().toString(),hashMap);
                    Toast.makeText(getActivity(), "تم تعطيل الحساب", Toast.LENGTH_SHORT).show();
                }else {
                    HashMap hashMap = new HashMap();
                    hashMap.put("account_Status",true);
                    CustomerConnect.upDate(et_username.getText().toString(),hashMap);
                    Toast.makeText(getActivity(), "تم إعادة تفعيل الحساب الحساب", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btn_edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.nav_host_fragment,new Edit_Customer_Fragment().newInstance(current_customer.getE_Mail()));
                fr.addToBackStack(null);
                fr.commit();
            }
        });


        return view;
    }
}