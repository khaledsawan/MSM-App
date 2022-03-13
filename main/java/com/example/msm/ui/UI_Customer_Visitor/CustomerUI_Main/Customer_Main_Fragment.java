package com.example.msm.ui.UI_Customer_Visitor.CustomerUI_Main;

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
import com.example.msm.ui.UI_Customer_Visitor.Add_Services_To_Profile.Add_Services_To_Profile_Fragment;
import com.example.msm.ui.UI_Customer_Visitor.Availability_Of_Gates.Availability_Of_Gates_Fragment;
import com.example.msm.ui.UI_Customer_Visitor.Change_Password.Change_Password_Fragment;
import com.example.msm.ui.UI_Customer_Visitor.Change_Speed.CS_Custom_Recycler_Item;
import com.example.msm.ui.UI_Customer_Visitor.Change_Speed.Change_Speed_Fragment;
import com.example.msm.ui.UI_Customer_Visitor.Profile.Profile_Fragment;
import com.example.msm.ui.UI_Customer_Visitor.Recharge_The_Package.RTP_Custom_Recycler_Item;
import com.example.msm.ui.UI_Customer_Visitor.Recharge_The_Package.Recharge_The_Package_Fragment;
import com.example.msm.ui.UI_Customer_Visitor.Send_Message.Send_Message_Fragment;
import com.example.msm.ui.UI_Customer_Visitor.Show_All_Advertisements.Show_All_Advertisements_Fragment;
import com.example.msm.ui.UI_Customer_Visitor.center_list_fragment_for_customer_visiter;

import java.util.ArrayList;


public class Customer_Main_Fragment extends Fragment {

    Button main_btn_Profile;
    Button main_btn_availability_of_gates;
    Button main_btn_Change_Password;
    Button main_btn_Change_Speed;
    Button main_btn_center_pay;
    Button main_btn_recharge_the_packagee;
    Button main_btn_myservice;
    Button btn_offers;
    Button main_btn_regist;
    Button main_btn_support;

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

    public Customer_Main_Fragment() {
        // Required empty public constructor
    }


    public static Customer_Main_Fragment newInstance(String param1, String param2) {
        Customer_Main_Fragment fragment = new Customer_Main_Fragment();
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
        View view = inflater.inflate(R.layout.fragment_customer_main, container, false);
        main_btn_Profile = view.findViewById(R.id.main_btn_Profile);
        main_btn_availability_of_gates = view.findViewById(R.id.main_btn_availability_of_gates);
        main_btn_Change_Password = view.findViewById(R.id.main_btn_Change_Password);
        main_btn_Change_Speed = view.findViewById(R.id.main_btn_Change_Speed);
        main_btn_center_pay = view.findViewById(R.id.main_btn_center_pay);
        main_btn_recharge_the_packagee = view.findViewById(R.id.main_btn_recharge_the_packagee);
        main_btn_myservice = view.findViewById(R.id.main_btn_myservice);
        btn_offers = view.findViewById(R.id.btn_offers);
        main_btn_regist = view.findViewById(R.id.main_btn_regist);
        main_btn_support = view.findViewById(R.id.main_btn_support);


        main_btn_Profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.nav_host_fragment,new Profile_Fragment());
                fr.addToBackStack(null);
                fr.commit();
            }
        });

        main_btn_availability_of_gates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.nav_host_fragment,new Availability_Of_Gates_Fragment());
                fr.addToBackStack(null);
                fr.commit();
            }
        });

        main_btn_Change_Password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.nav_host_fragment,new Change_Password_Fragment());
                fr.addToBackStack(null);
                fr.commit();
            }
        });

        main_btn_Change_Speed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            /**                 fragment_Change_Speed                 **/
                ArrayList<CS_Custom_Recycler_Item> items = new ArrayList<>();
                items.add(new CS_Custom_Recycler_Item("1","1000"));
                items.add(new CS_Custom_Recycler_Item("2","1500"));
                items.add(new CS_Custom_Recycler_Item("3","2000"));
                items.add(new CS_Custom_Recycler_Item("4","2500"));
                items.add(new CS_Custom_Recycler_Item("5","3000"));
                Change_Speed_Fragment change_speed = new Change_Speed_Fragment().newInstance(items);
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.nav_host_fragment,change_speed);
                fr.addToBackStack(null);
                fr.commit();
            }
        });

        main_btn_center_pay.setOnClickListener(new View.OnClickListener() {
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

        main_btn_recharge_the_packagee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<RTP_Custom_Recycler_Item> items = new ArrayList<>();

                items.add(new RTP_Custom_Recycler_Item("35","1000","Ok"));
                items.add(new RTP_Custom_Recycler_Item("100","1500","Ok"));
                items.add(new RTP_Custom_Recycler_Item("150","2000","Ok"));
                items.add(new RTP_Custom_Recycler_Item("200","2500","Ok"));
                items.add(new RTP_Custom_Recycler_Item("250","3000","Ok"));


                Recharge_The_Package_Fragment recharge_the_package_fragment = new Recharge_The_Package_Fragment().newInstance(items);
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.nav_host_fragment,recharge_the_package_fragment);
                fr.addToBackStack(null);
                fr.commit();
            }
        });

        main_btn_myservice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                ServicesConnect.getAllServices(new ServicesConnect.ServicesConnectCallBackAllServices() {
                    @Override
                    public void getItemsFinished(ArrayList<Services> services) {
                        Add_Services_To_Profile_Fragment add_services_fragment = new Add_Services_To_Profile_Fragment().newInstance(services);
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

        main_btn_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Send_Message_Fragment recharge_the_package_fragment = new Send_Message_Fragment();
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.nav_host_fragment,recharge_the_package_fragment);
                fr.addToBackStack(null);
                fr.commit();
            }
        });

        main_btn_support.setOnClickListener(new View.OnClickListener() {
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