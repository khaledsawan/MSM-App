package com.example.msm.ui.UI_Center.Message_Rquestes;

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
import com.example.msm.ui.UI_Admin.Change_Password_Requestes.RecyclerView_Adapter_Request;

import java.util.ArrayList;


public class Message_Requests_Fragment extends Fragment {




    RecyclerView Message_Req_rv;
    ArrayList<Request> requests;

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
        open_listener.onNewFragmentOpen("Messages");
    }


    public Message_Requests_Fragment() {
        // Required empty public constructor
    }



    public static Message_Requests_Fragment newInstance(ArrayList<Request>  requests) {
        Message_Requests_Fragment fragment = new Message_Requests_Fragment();
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
        View view  =  inflater.inflate(R.layout.fragment_message_requests, container, false);



        Message_Req_rv = view.findViewById(R.id.Message_Req_rv);


        final RecyclerView_Adapter_Request adapter = new RecyclerView_Adapter_Request(requests);

        adapter.setOnItemClickListener(new RecyclerView_Adapter_Request.OnItemClickListener() {
            @Override
            public void onOKClick(String position) {
                RequestConnect.responseOnMessageFromCenterAdminRequest(position,true);
            }

            @Override
            public void onNOClick(String position) {
                RequestConnect.responseOnMessageFromCenterAdminRequest(position,false);
            }
        });



        //  هاد السطر هو السطر يلي بحددلك طريقة عرض العناصر
        RecyclerView.LayoutManager lm = new GridLayoutManager(getContext(),1);
        Message_Req_rv.setHasFixedSize(true);
        Message_Req_rv.setLayoutManager(lm);
        Message_Req_rv.setAdapter(adapter);

        return view;
    }
}