package com.example.msm.ui.UI_Customer_Visitor.Change_Speed;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.msm.FirebaseMSMTest.Service.AppBarListener;
import com.example.msm.FirebaseMSMTest.Modles.Request;
import com.example.msm.FirebaseMSMTest.Service.RequestConnect;
import com.example.msm.R;

import java.util.ArrayList;


public class Change_Speed_Fragment extends Fragment {

    RecyclerView rv;
    Button cs_btn_OK;
    private ArrayList<CS_Custom_Recycler_Item> items;
    private static final String items_Key = "items_Key";
    private String choice ="";



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
        open_listener.onNewFragmentOpen("Change Speed");
    }

    public Change_Speed_Fragment() {
        // Required empty public constructor
    }

    public static Change_Speed_Fragment newInstance(ArrayList<CS_Custom_Recycler_Item> items) {
        Change_Speed_Fragment fragment = new Change_Speed_Fragment();
        Bundle args = new Bundle();
        args.putSerializable(Change_Speed_Fragment.items_Key,items);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if ( bundle!= null) {
            items = (ArrayList<CS_Custom_Recycler_Item>) bundle.getSerializable(Change_Speed_Fragment.items_Key);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_change_speed_fragment, container, false);
        rv = view.findViewById(R.id.cs_rv);
        cs_btn_OK = view.findViewById(R.id.cs_btn_OK);
        CS_Recycler_View_Adapter adapter = new CS_Recycler_View_Adapter(items, new CS_Recycler_View_Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(CS_Custom_Recycler_Item items) {
//                Toast.makeText(getContext(), "dfdsfadsafdsa", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRadioButtonClick(String speedselected) {
                choice = speedselected;
            }

        });
        cs_btn_OK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(choice!= ""){
                    RequestConnect.setChangeSpeedRequest(new Request("Customer","Admin OR Center",choice,"Change Speed",getActivity()));
                }
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