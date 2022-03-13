package com.example.msm.ui.UI_Customer_Visitor.Show_All_Services;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.msm.FirebaseMSMTest.Service.AppBarListener;
import com.example.msm.FirebaseMSMTest.Modles.Services;
import com.example.msm.R;

import java.util.ArrayList;

public class Show_All_Services_Fragment extends Fragment {


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
        open_listener.onNewFragmentOpen("Services");
    }

    public Show_All_Services_Fragment() {
        // Required empty public constructor
    }

    ArrayList<Services> services;
    RecyclerView show_services_rv;

    public static Show_All_Services_Fragment newInstance(ArrayList<Services> services) {
        Show_All_Services_Fragment fragment = new Show_All_Services_Fragment();
        Bundle args = new Bundle();
        args.putSerializable("services", services);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            services = (ArrayList<Services>) getArguments().getSerializable("services");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_show_all_services, container, false);
        show_services_rv = view.findViewById(R.id.show_services_rv);

        Show_All_Services_Adapter adapter = new Show_All_Services_Adapter(services);
//        Toast.makeText(getActivity(), item.getSerial_Number(), Toast.LENGTH_SHORT).show();
//
        //  هاد السطر هو السطر يلي بحددلك طريقة عرض العناصر
        RecyclerView.LayoutManager lm = new GridLayoutManager(getContext(),1);
        show_services_rv.setHasFixedSize(true);
        show_services_rv.setLayoutManager(lm);
        show_services_rv.setAdapter(adapter);
        return view;
    }
}
