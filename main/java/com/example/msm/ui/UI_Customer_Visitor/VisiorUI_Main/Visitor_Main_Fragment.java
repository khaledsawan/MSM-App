package com.example.msm.ui.UI_Customer_Visitor.VisiorUI_Main;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.msm.FirebaseMSMTest.Modles.Advertisements;
import com.example.msm.FirebaseMSMTest.Service.AdvertisementsConnect;
import com.example.msm.FirebaseMSMTest.Service.AppBarListener;
import com.example.msm.FirebaseMSMTest.Modles.Center;
import com.example.msm.FirebaseMSMTest.Service.CenterConnect;
import com.example.msm.FirebaseMSMTest.Modles.Services;
import com.example.msm.FirebaseMSMTest.Service.ServicesConnect;
import com.example.msm.R;
import com.example.msm.ui.Common.Connect_With_Us.Connect_With_Us_Fragment;
import com.example.msm.ui.UI_Customer_Visitor.Availability_Of_Gates.Availability_Of_Gates_Fragment;
import com.example.msm.ui.UI_Customer_Visitor.Send_Message.Send_Message_Fragment;
import com.example.msm.ui.UI_Customer_Visitor.Show_All_Advertisements.Show_All_Advertisements_Fragment;
import com.example.msm.ui.UI_Customer_Visitor.Show_All_Services.Show_All_Services_Fragment;
import com.example.msm.ui.UI_Customer_Visitor.center_list_fragment_for_customer_visiter;

import java.util.ArrayList;


public class Visitor_Main_Fragment extends Fragment {

    Button btn_av_adsl;
    Button btn_center_pay;
    Button btn_myservice;
    Button btn_offers;
    Button btn_regist;
    Button btn_support;

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
        open_listener.onNewFragmentOpen("Visitor");
    }

    public Visitor_Main_Fragment() {
        // Required empty public constructor
    }


    public static Visitor_Main_Fragment newInstance() {
        Visitor_Main_Fragment fragment = new Visitor_Main_Fragment();
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
        View view =  inflater.inflate(R.layout.fragment_visitor_main, container, false);

        btn_av_adsl  = view.findViewById(R.id.btn_av_adsl);
        btn_center_pay  = view.findViewById(R.id.btn_center_pay);
        btn_myservice  = view.findViewById(R.id.btn_myservice);
        btn_offers  = view.findViewById(R.id.btn_offers);
        btn_regist  = view.findViewById(R.id.btn_regist);
        btn_support  = view.findViewById(R.id.btn_support);

        btn_av_adsl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.nav_host_fragment,new Availability_Of_Gates_Fragment());
                fr.addToBackStack(null);
                fr.commit();
            }
        });

        btn_center_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CenterConnect.getAll_lItems(new CenterConnect.CenterConnectCallBackAll() {
                    @Override
                    public void getItemsFinished(ArrayList<Center> center) {
                        center_list_fragment_for_customer_visiter recharge_the_package_fragment = new center_list_fragment_for_customer_visiter().newInstance(center);
                        FragmentTransaction fr = getFragmentManager().beginTransaction();
                        fr.replace(R.id.nav_host_fragment,recharge_the_package_fragment);
                        fr.addToBackStack(null);
                        fr.commit();
                    }
                });
            }
        });

        btn_myservice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ServicesConnect.getAllServices(new ServicesConnect.ServicesConnectCallBackAllServices() {
                    @Override
                    public void getItemsFinished(ArrayList<Services> services) {
                        Show_All_Services_Fragment add_services_fragment = new Show_All_Services_Fragment().newInstance(services);
                        FragmentTransaction fr = getFragmentManager().beginTransaction();
                        fr.replace(R.id.nav_host_fragment,add_services_fragment);
                        fr.addToBackStack(null);
                        fr.commit();
                    }
                });

            }
        });

        btn_offers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdvertisementsConnect.getAllAdvertisements(new AdvertisementsConnect.AdvertisementsConnectCallBackAllServices() {
                    @Override
                    public void getItemsFinished(ArrayList<Advertisements> advertisement) {
                        Show_All_Advertisements_Fragment recharge_the_package_fragment = new Show_All_Advertisements_Fragment().newInstance(advertisement);
                        FragmentTransaction fr = getFragmentManager().beginTransaction();
                        fr.replace(R.id.nav_host_fragment,recharge_the_package_fragment);
                        fr.addToBackStack(null);
                        fr.commit();
                    }
                });
            }
        });

        btn_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Send_Message_Fragment recharge_the_package_fragment = new Send_Message_Fragment();
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.nav_host_fragment,recharge_the_package_fragment);
                fr.addToBackStack(null);
                fr.commit();
            }
        });

        btn_support.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.nav_host_fragment,new Connect_With_Us_Fragment());
                fr.addToBackStack(null);
                fr.commit();
            }
        });




        return view;
    }
}