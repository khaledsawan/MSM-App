package com.example.msm.ui.UI_Customer_Visitor.Recharge_The_Package;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.msm.FirebaseMSMTest.Service.AppBarListener;
import com.example.msm.FirebaseMSMTest.Modles.Customer;
import com.example.msm.FirebaseMSMTest.Service.CustomerConnect;
import com.example.msm.R;

import java.util.ArrayList;
import java.util.HashMap;


public class Recharge_The_Package_Fragment extends Fragment {

    RecyclerView rv;
    private ArrayList<RTP_Custom_Recycler_Item> items;
    private static final String items_Key = "items_Key";
    SharedPreferences sharedPreferences;

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
        open_listener.onNewFragmentOpen("Recharger the package");
    }

    public Recharge_The_Package_Fragment() {
        // Required empty public constructor
    }


    public static Recharge_The_Package_Fragment newInstance(ArrayList<RTP_Custom_Recycler_Item> items) {
        Recharge_The_Package_Fragment fragment = new Recharge_The_Package_Fragment();
        Bundle args = new Bundle();
        args.putSerializable(Recharge_The_Package_Fragment.items_Key, items);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            items = (ArrayList<RTP_Custom_Recycler_Item>) bundle.getSerializable(Recharge_The_Package_Fragment.items_Key);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recharge_the_package, container, false);

        rv = view.findViewById(R.id.rtp_rv);



        RTP_Recycler_View_Adapter adapter = new RTP_Recycler_View_Adapter(items, new RTP_Recycler_View_Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(RTP_Custom_Recycler_Item item) {
//                Toast.makeText(getActivity(),item.getPrice(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onbtnOKClick(RTP_Custom_Recycler_Item item) {
                sharedPreferences = getActivity().getSharedPreferences("LogIN", Context.MODE_PRIVATE);
                String e_Mail = sharedPreferences.getString("e_Mail", "null");
                CustomerConnect.getItem(e_Mail, new CustomerConnect.CustomerConnectCallBack() {
                    @Override
                    public void getItemFinished(Customer customer) {
                        HashMap hashMap = new HashMap();
                        if(customer.getC_package()==null){
                            customer.setC_package(0);
                        }
                        hashMap.put("c_package",customer.getC_package()+Integer.parseInt(item.getSize()));
                        hashMap.put("invoice_Value",customer.getInvoice_Value()+Integer.parseInt(item.getPrice()));
                        CustomerConnect.upDate(e_Mail,hashMap);
                    }
                });
            }
        });
        //  هاد السطر هو السطر يلي بحددلك طريقة عرض العناصر
        RecyclerView.LayoutManager lm = new GridLayoutManager(getContext(),1);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(lm);
        rv.setAdapter(adapter);
        return view;
    }
}