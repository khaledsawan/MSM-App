package com.example.msm.ui.UI_Customer_Visitor.Profile;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.msm.FirebaseMSMTest.Service.AppBarListener;
import com.example.msm.R;


public class Profile_Fragment extends Fragment {


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
        open_listener.onNewFragmentOpen("Profile");
    }


    public Profile_Fragment() {
        // Required empty public constructor
    }

    private TextView p_tv_UserName;
    private TextView p_tv_Password;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;


    public static Profile_Fragment newInstance() {
        Profile_Fragment fragment = new Profile_Fragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        p_tv_UserName = view.findViewById(R.id.p_tv_UserName);
        p_tv_Password = view.findViewById(R.id.p_tv_Password);

        sharedPreferences = this.getActivity().getSharedPreferences("LogIN", Context.MODE_PRIVATE);
        String e_Mail = sharedPreferences.getString("e_Mail", "null");
        String password = sharedPreferences.getString("password", "null");

        p_tv_UserName.setText(e_Mail);
        p_tv_Password.setText(password);


        return view;
    }
}