package com.example.msm.ui.UI_Admin.Bills;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.msm.FirebaseMSMTest.Service.AppBarListener;
import com.example.msm.FirebaseMSMTest.Modles.Bill;
import com.example.msm.R;

import java.util.ArrayList;


public class Bills_Fragment extends Fragment {

    RecyclerView rv;

    private static final String ARG_PARAM1 = "ArrayList";

    ArrayList<com.example.msm.FirebaseMSMTest.Modles.Bill> Bill=new ArrayList<>();
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
        open_listener.onNewFragmentOpen("Bills");
    }


    public Bills_Fragment() {
        // Required empty public constructor
    }

    public static Bills_Fragment newInstance( ArrayList<Bill> Bill) {
        Bills_Fragment fragment = new Bills_Fragment();
        Bundle args = new Bundle();
        args.putSerializable(Bills_Fragment.ARG_PARAM1, Bill);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            Bill = (ArrayList<Bill>) bundle.getSerializable(Bills_Fragment.ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.fragment_bills, container, false);
        rv=v.findViewById(R.id.B_F_Recyclerview);

        Recyclerview_Adapter_Bills adapter=new Recyclerview_Adapter_Bills(Bill);

        RecyclerView.LayoutManager lm=new LinearLayoutManager(getActivity());
        rv.setHasFixedSize(true);
        rv.setLayoutManager(lm);
        rv.setAdapter(adapter);
        return v;
    }
}