
package com.example.msm.ui.UI_Customer_Visitor;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.msm.FirebaseMSMTest.Modles.Center;
import com.example.msm.R;
import com.example.msm.ui.Common.Recyclerview_Adapter_centrs;

import java.util.ArrayList;


public class center_list_fragment_for_customer_visiter extends Fragment {


    RecyclerView rv;
    ArrayList<com.example.msm.FirebaseMSMTest.Modles.Center> Center=new ArrayList<>();


    private static final String ARG_PARAM1 = "param1";
    private String mParam1;

    public center_list_fragment_for_customer_visiter() {
        // Required empty public constructor
    }

    public static center_list_fragment_for_customer_visiter newInstance(ArrayList<Center> Center) {
        center_list_fragment_for_customer_visiter fragment = new center_list_fragment_for_customer_visiter();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, Center);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (getArguments() != null) {
            Center = (ArrayList<Center>) bundle.getSerializable(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_center_list_for_customer_visiter, container, false);
        rv =v.findViewById(R.id.center_list_fragment_for_customer_visiter_rv);
        Recyclerview_Adapter_centrs adapter=new Recyclerview_Adapter_centrs(Center);
        RecyclerView.LayoutManager lm=new LinearLayoutManager(getActivity());
        rv.setHasFixedSize(true);
        rv.setLayoutManager(lm);
        rv.setAdapter(adapter);

        return v;
    }
}