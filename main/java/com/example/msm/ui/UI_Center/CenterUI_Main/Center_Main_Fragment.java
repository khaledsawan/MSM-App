package com.example.msm.ui.UI_Center.CenterUI_Main;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.msm.FirebaseMSMTest.Service.AppBarListener;
import com.example.msm.FirebaseMSMTest.Modles.Request;
import com.example.msm.FirebaseMSMTest.Service.RequestConnect;
import com.example.msm.R;
import com.example.msm.ui.UI_Center.Add_Customer.Add_Customer_Fragment;
import com.example.msm.ui.UI_Center.Add_Offer.Add_Offer_Fragment;
import com.example.msm.ui.UI_Center.Customer_Service_Interface.Customer_Services_Interface_Fragment;
import com.example.msm.ui.UI_Center.Message_Rquestes.Message_Requests_Fragment;
import com.example.msm.ui.UI_Center.Message_To_Admin.Message_To_Admin_Fragment;

import java.util.ArrayList;


public class Center_Main_Fragment extends Fragment {


    Button center_Main_btn_add_customer;

    Button center_Main_btn_add_service;
    Button center_Main_btn_send_request_to_admin;
    Button center_Main_btn_add_offer;
    Button    center_Main_btn_Messages;

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
        open_listener.onNewFragmentOpen("Center");
    }

    public Center_Main_Fragment() {
        // Required empty public constructor
    }


    public static Center_Main_Fragment newInstance() {
        Center_Main_Fragment fragment = new Center_Main_Fragment();
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
        View view = inflater.inflate(R.layout.fragment_center_main, container, false);

        center_Main_btn_add_customer = view.findViewById(R.id.center_Main_btn_add_customer);
        center_Main_btn_add_service = view.findViewById(R.id.center_Main_btn_add_service);
        center_Main_btn_send_request_to_admin = view.findViewById(R.id.center_Main_btn_send_request_to_admin);


        center_Main_btn_add_offer = view.findViewById(R.id.center_Main_btn_add_offer);
        center_Main_btn_Messages  = view.findViewById(R.id.center_Main_btn_Messages);


        center_Main_btn_Messages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestConnect.getMessageFromVisitorCustomerRequest(new RequestConnect.AllRequestsCallBack() {
                    @Override
                    public void getItemsFinished(ArrayList<Request> requests) {
                        FragmentTransaction fr = getFragmentManager().beginTransaction();
                        Message_Requests_Fragment Customers_List_Fragment=new Message_Requests_Fragment().newInstance( requests);
                        ///هون لاو يكون فيه كونتينر
                        fr.replace(R.id.nav_host_fragment,Customers_List_Fragment);
                        fr.addToBackStack(null);
                        fr.commit();
                    }
                });
            }
        });


        center_Main_btn_add_customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.nav_host_fragment,new Add_Customer_Fragment());
                fr.addToBackStack(null);
                fr.commit();
            }
        });


        center_Main_btn_add_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.nav_host_fragment,new Customer_Services_Interface_Fragment());
                fr.addToBackStack(null);
                fr.commit();
            }
        });

        center_Main_btn_send_request_to_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.nav_host_fragment,new Message_To_Admin_Fragment());
                fr.addToBackStack(null);
                fr.commit();
            }
        });

        center_Main_btn_add_offer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.nav_host_fragment,new Add_Offer_Fragment());
                fr.addToBackStack(null);
                fr.commit();
            }
        });

        return view;
    }
}