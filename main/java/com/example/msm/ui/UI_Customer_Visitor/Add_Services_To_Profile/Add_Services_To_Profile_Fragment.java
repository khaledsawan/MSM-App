package com.example.msm.ui.UI_Customer_Visitor.Add_Services_To_Profile;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.msm.FirebaseMSMTest.Service.AppBarListener;
import com.example.msm.FirebaseMSMTest.Modles.Customer;
import com.example.msm.FirebaseMSMTest.Service.CustomerConnect;
import com.example.msm.FirebaseMSMTest.Modles.Services;
import com.example.msm.R;

import java.util.ArrayList;
import java.util.HashMap;


public class Add_Services_To_Profile_Fragment extends Fragment {


    RecyclerView add_services_rv;
    private ArrayList<Services> items;
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
        open_listener.onNewFragmentOpen("Add Services");
    }

    public Add_Services_To_Profile_Fragment() {
        // Required empty public constructor
    }

    public static Add_Services_To_Profile_Fragment newInstance(ArrayList<Services> items) {
        Add_Services_To_Profile_Fragment fragment = new Add_Services_To_Profile_Fragment();
        Bundle args = new Bundle();
        args.putSerializable(Add_Services_To_Profile_Fragment.items_Key, items);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            items = (ArrayList<Services>) bundle.getSerializable(Add_Services_To_Profile_Fragment.items_Key);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_services, container, false);

        add_services_rv = view.findViewById(R.id.add_services_rv);
        sharedPreferences = getActivity().getSharedPreferences("LogIN", Context.MODE_PRIVATE);

        ASTP_RecyclerViewAdapter adapter = new ASTP_RecyclerViewAdapter(items, new ASTP_RecyclerViewAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(Services item) {
                CustomerConnect.getItem(sharedPreferences.getString("e_Mail", null), new CustomerConnect.CustomerConnectCallBack() {
                    @Override
                    public void getItemFinished(Customer customer) {
                        ArrayList<Integer> serviList = new ArrayList<>();
                        try {
                            for (Integer services : customer.getServices()){
                                serviList.add(services);
                            }
                        }catch (NullPointerException  nullPointerException){

                        }
                        if(serviList.contains(Integer.valueOf(item.getSerial_Number()))){
                            Toast.makeText(getActivity(), "You are already subscribed to this service", Toast.LENGTH_SHORT).show();
                        }else{
                            serviList.add(Integer.valueOf(item.getSerial_Number()));
                            HashMap hashMap = new HashMap();
                            hashMap.put("services",serviList);
                            hashMap.put("invoice_Value",customer.getInvoice_Value()+Integer.valueOf(item.getCost()));
                            CustomerConnect.upDate(sharedPreferences.getString("e_Mail", null),hashMap);
                            Toast.makeText(getActivity(), "This service has been subscribed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        //  هاد السطر هو السطر يلي بحددلك طريقة عرض العناصر
        RecyclerView.LayoutManager lm = new GridLayoutManager(getContext(),1);
        add_services_rv.setHasFixedSize(true);
        add_services_rv.setLayoutManager(lm);
        add_services_rv.setAdapter(adapter);
        return view;
    }
}