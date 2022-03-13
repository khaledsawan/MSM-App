package com.example.msm.ui.UI_Center.Message_To_Admin;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.msm.FirebaseMSMTest.Service.AppBarListener;
import com.example.msm.FirebaseMSMTest.Modles.Request;
import com.example.msm.FirebaseMSMTest.Service.RequestConnect;
import com.example.msm.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Message_To_Admin_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Message_To_Admin_Fragment extends Fragment {



    EditText message_et;
    Button message_btn_send;

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
        open_listener.onNewFragmentOpen("Send message");
    }

    public Message_To_Admin_Fragment() {
        // Required empty public constructor
    }


    public static Message_To_Admin_Fragment newInstance() {
        Message_To_Admin_Fragment fragment = new Message_To_Admin_Fragment();
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
        View view =  inflater.inflate(R.layout.fragment_message_to_admin, container, false);
        message_et  =view.findViewById(R.id.message_et);
        message_btn_send=view.findViewById(R.id.message_btn_send);

        message_btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestConnect.setMessageToAdminRequest(new Request("Center","Admin",message_et.getText().toString(),"Message",getActivity()));
            }
        });
        return view ;
    }
}