package com.example.msm.ui.UI_Admin.AdminUI_Main;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.msm.FirebaseMSMTest.Service.AdvertisementsConnect;
import com.example.msm.FirebaseMSMTest.Service.AppBarListener;
import com.example.msm.FirebaseMSMTest.Modles.Bill;
import com.example.msm.FirebaseMSMTest.Service.BillConnect;
import com.example.msm.FirebaseMSMTest.Modles.Center;
import com.example.msm.FirebaseMSMTest.Service.CenterConnect;
import com.example.msm.FirebaseMSMTest.Modles.Customer;
import com.example.msm.FirebaseMSMTest.Service.CustomerConnect;
import com.example.msm.FirebaseMSMTest.Modles.Advertisements;
import com.example.msm.FirebaseMSMTest.Modles.Request;
import com.example.msm.FirebaseMSMTest.Service.RequestConnect;
import com.example.msm.FirebaseMSMTest.Modles.Services;
import com.example.msm.FirebaseMSMTest.Service.ServicesConnect;
import com.example.msm.R;
import com.example.msm.ui.UI_Admin.Add_Centers.Center_fragment;
import com.example.msm.ui.UI_Admin.Advertisements.Offers_And_Ads_Fragment;

import com.example.msm.ui.UI_Admin.Bills.Bills_Fragment;
import com.example.msm.ui.UI_Admin.Change_Password_Requestes.Change_Password_Requests_Fragment;
import com.example.msm.ui.UI_Admin.Customer.Customers_List_Fragment;
import com.example.msm.ui.UI_Admin.Payment.Payment_Fragment;
import com.example.msm.ui.UI_Admin.Revenues.Revenues_Fragment;
import com.example.msm.ui.UI_Admin.Services.Services_fragment;
import com.example.msm.ui.UI_Center.Message_Rquestes.Message_Requests_Fragment;

import java.util.ArrayList;


public class main_Fragment_manager extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    Button MFM_revenues_btn;
    Button MFM_bills_btn;
    Button MFM_Services_btn;
    Button MFM_Offers_And_Ads_btn;
    Button MFM_payment;
    Button MFM_Customer;
    Button addcenterad;

    Button chang_speed;
    Button centers_requests;

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
        open_listener.onNewFragmentOpen("Manager");
    }

    public main_Fragment_manager() {
        // Required empty public constructor
    }


    public static main_Fragment_manager newInstance(String param1, String param2) {
        main_Fragment_manager fragment = new main_Fragment_manager();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main__manager, container, false);
        MFM_revenues_btn=v.findViewById(R.id.M_F_M_revenues_btn);
        MFM_bills_btn=v.findViewById(R.id.M_F_M_bills_btn);
        MFM_Services_btn=v.findViewById(R.id.M_F_Services_btn);
        MFM_Offers_And_Ads_btn=v.findViewById(R.id.M_F_Offers_And_Ads_btn);
        MFM_payment=v.findViewById(R.id.M_F_payment);
        MFM_Customer=v.findViewById(R.id.M_F_Customer);
        chang_speed = v.findViewById(R.id.chang_speed);
        centers_requests = v.findViewById(R.id.centers_requests);
        addcenterad = v.findViewById(R.id.addcenterad);
        ///////////////////////////////////////////
        addcenterad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CenterConnect.getAll_lItems(new CenterConnect.CenterConnectCallBackAll() {
                    @Override
                    public void getItemsFinished(ArrayList<Center> center) {
                        FragmentTransaction fr = getFragmentManager().beginTransaction();
                        Center_fragment Customers_List_Fragment=new Center_fragment().newInstance( center);
                        ///هون لاو يكون فيه كونتينر
                        fr.replace(R.id.nav_host_fragment,Customers_List_Fragment);
                        fr.addToBackStack(null);
                        fr.commit();
                    }
                });
            }
        });

        chang_speed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestConnect.getChangeSpeedRequests(new RequestConnect.AllRequestsCallBack() {
                    @Override
                    public void getItemsFinished(ArrayList<Request> requests) {
                        FragmentTransaction fr = getFragmentManager().beginTransaction();
                        Change_Password_Requests_Fragment Customers_List_Fragment=new Change_Password_Requests_Fragment().newInstance( requests);
                        ///هون لاو يكون فيه كونتينر
                        fr.replace(R.id.nav_host_fragment,Customers_List_Fragment);
                        fr.addToBackStack(null);
                        fr.commit();
                    }
                });
            }
        });

        centers_requests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestConnect.getMessageFromCenterAdminRequest(new RequestConnect.AllRequestsCallBack() {
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

        MFM_Customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CustomerConnect.getAllCustomers(new CustomerConnect.CustomersConnectCallBackAllServices() {
                    @Override
                    public void getItemsFinished(ArrayList<Customer> customer) {
                        FragmentTransaction fr = getFragmentManager().beginTransaction();
                        Customers_List_Fragment Customers_List_Fragment=new Customers_List_Fragment().newInstance( customer);
                        ///هون لاو يكون فيه كونتينر
                        fr.replace(R.id.nav_host_fragment,Customers_List_Fragment);
                        fr.addToBackStack(null);
                        fr.commit();
                    }
                });

            }
        });


        MFM_payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction fr = getFragmentManager().beginTransaction();
                Payment_Fragment Payment_Fragment=new Payment_Fragment().newInstance("30");
                ///هون لاو يكون فيه كونتينر
                  fr.replace(R.id.nav_host_fragment,Payment_Fragment);
                fr.addToBackStack(null);
                fr.commit();
            }
        });



        MFM_revenues_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                CustomerConnect.getCustomersCount(new CustomerConnect.CustomerConnectCustomerCount() {
                    @Override
                    public void getItemFinished(Long customerCount) {
                        if(customerCount ==null){
                            customerCount = new Long(0);
                        }
                        Long finalCustomerCount = customerCount;
                        BillConnect.getTotal_Bills(new BillConnect.BillsConnectCallBackTotal_Bills() {
                            @Override
                            public void getItemsFinished(Integer sum) {
                                Revenues_Fragment revenues_fragment=new Revenues_Fragment().newInstance(finalCustomerCount,sum);
                                ///هون لاو يكون فيه كونتينر
                                fr.replace(R.id.nav_host_fragment,revenues_fragment);
                                fr.addToBackStack(null);
                                fr.commit();
                            }
                        });

                    }
                });
            }
        });


        MFM_bills_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BillConnect.getAllBills(new BillConnect.BillsConnectCallBackAllServices() {
                    @Override
                    public void getItemsFinished(ArrayList<Bill> bills) {
                        Bills_Fragment Bills_Fragment = new Bills_Fragment().newInstance(bills);
                        ///هون لاو يكون فيه كونتينر
                        FragmentTransaction fr = getFragmentManager().beginTransaction();
                        fr.replace(R.id.nav_host_fragment,Bills_Fragment);
                        fr.addToBackStack(null);
                        fr.commit();
                    }
                });
            }
        });


        MFM_Services_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ServicesConnect.getAllServices(new ServicesConnect.ServicesConnectCallBackAllServices() {
                    @Override
                    public void getItemsFinished(ArrayList<Services> services) {
                        Services_fragment Services_fragment = new Services_fragment().newInstance(services);
                        FragmentTransaction fr = getFragmentManager().beginTransaction();
                        //    ///هون لاو يكون فيه كونتينر
                        fr.replace(R.id.nav_host_fragment,Services_fragment);
                        fr.addToBackStack(null);
                        fr.commit();
                    }
                });
                }
        });


        MFM_Offers_And_Ads_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AdvertisementsConnect.getAllAdvertisements(new AdvertisementsConnect.AdvertisementsConnectCallBackAllServices() {
                    @Override
                    public void getItemsFinished(ArrayList<Advertisements> advertisement) {
                        Offers_And_Ads_Fragment Offers_And_Ads_Fragment = new Offers_And_Ads_Fragment().newInstance(advertisement);
                        FragmentTransaction ft = getFragmentManager().beginTransaction();
                        ///هون لاو يكون فيه كونتينر
                        ft.replace(R.id.nav_host_fragment, Offers_And_Ads_Fragment);
                        ft.addToBackStack(null);
                        ft.commit();
                    }
                });

            }
        });
        return v;
    }
}