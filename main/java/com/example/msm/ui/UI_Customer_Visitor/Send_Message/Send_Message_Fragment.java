package com.example.msm.ui.UI_Customer_Visitor.Send_Message;

import android.content.Context;
import android.content.SharedPreferences;
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


public class Send_Message_Fragment extends Fragment {


    public Send_Message_Fragment() {
        // Required empty public constructor
    }

    private EditText et_note;
    private Button btn_send_note;
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
        open_listener.onNewFragmentOpen("Send message");
    }

    public static Send_Message_Fragment newInstance() {
        Send_Message_Fragment fragment = new Send_Message_Fragment();
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
        View root = inflater.inflate(R.layout.fragment_send_message, null, false);
        et_note = root.findViewById(R.id.et_note);
        btn_send_note = root.findViewById(R.id.btn_send_note);


        sharedPreferences = getActivity().getSharedPreferences("LogIN", Context.MODE_PRIVATE);

        btn_send_note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestConnect.setMessageToCenterRequest(new Request("Customer","Admin",et_note.getText().toString(),"Message",getActivity()));
            }
        });
        return root;
    }
}