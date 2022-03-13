package com.example.msm.ui.UI_Admin.Change_Password_Requestes;

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
import com.example.msm.FirebaseMSMTest.Modles.Request;
import com.example.msm.FirebaseMSMTest.Service.RequestConnect;
import com.example.msm.R;

import java.util.ArrayList;


public class Change_Password_Requests_Fragment extends Fragment {

    RecyclerView change_password_request_RV;

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
        open_listener.onNewFragmentOpen("Password requests");
    }

    public Change_Password_Requests_Fragment() {
        // Required empty public constructor
    }

    ArrayList<Request> requests;

    public static Change_Password_Requests_Fragment newInstance(ArrayList<Request> requests) {
        Change_Password_Requests_Fragment fragment = new Change_Password_Requests_Fragment();
        Bundle args = new Bundle();
        args.putSerializable("Key",requests);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            requests = (ArrayList<Request>) getArguments().getSerializable("Key");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_change_password_requests, container, false);

        change_password_request_RV = view.findViewById(R.id.change_password_request_RV);


        final RecyclerView_Adapter_Request adapter = new RecyclerView_Adapter_Request(requests);

        adapter.setOnItemClickListener(new RecyclerView_Adapter_Request.OnItemClickListener() {
            @Override
            public void onOKClick(String position) {
                RequestConnect.responseOnChangeSpeedRequest(position,true);
            }

            @Override
            public void onNOClick(String position) {
                RequestConnect.responseOnChangeSpeedRequest(position,false);
            }
        });



        //  هاد السطر هو السطر يلي بحددلك طريقة عرض العناصر
        RecyclerView.LayoutManager lm = new GridLayoutManager(getContext(),1);
        change_password_request_RV.setHasFixedSize(true);
        change_password_request_RV.setLayoutManager(lm);
        change_password_request_RV.setAdapter(adapter);

        return view;
    }
}